package com.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.api.exception.NotLoggedUserException;
import com.api.model.Customer;
import com.api.repository.CustomerJpaRepository;
import com.api.repository.UserJpaRepository;
import com.api.service.S3Service;

@RestController
@RequestMapping(path="/api/customers")
public class CustomerController {

	@Autowired
	private S3Service s3Service;
 
	@Autowired
	private CustomerJpaRepository customerJpaRepository;
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	private final String rootPath = "http://localhost:8080/api";
	private final String bucketRootPath = "https://s3.eu-west-2.amazonaws.com/avatarphotoscrm2/";
	
	@GetMapping
	public @ResponseBody Iterable<Customer> getAllCustomers(){
		return customerJpaRepository.findAll();
	}
	
    @GetMapping(path = "/{customerId}")
    public @ResponseBody
    Optional<Customer> getCustomer(@PathVariable Long  customerId){
        return customerJpaRepository.findById(customerId);
    }
	
    @PostMapping
    public @ResponseBody
    Customer addCustomer(@RequestBody Customer customer) {
    	customer.setCreator(rootPath + "/users/" + getCurrentUserId());
    	customer.setLastModifier(rootPath + "/users/" + getCurrentUserId());
        customerJpaRepository.saveAndFlush(customer);
        return customer;
    }
    
    @DeleteMapping(path="/{customerId}")
    public @ResponseBody
    void deleteCustomer(@PathVariable Long customerId) {
        customerJpaRepository.deleteById(customerId);
    }

    @PutMapping("/{customerId}")
    public @ResponseBody
    Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) throws NotLoggedUserException{
		Long currentUserId = getCurrentUserId();
		if(currentUserId == -1L)throw new NotLoggedUserException();
    	Customer customerToUpdate = customerJpaRepository.findById(customerId).get(); 
    	customerToUpdate = customer;
    	customerToUpdate.setLastModifier(rootPath + "/users/" + currentUserId);
        customerJpaRepository.saveAndFlush(customerToUpdate);
        return customerToUpdate;
    }
    
	@PostMapping("/{customerId}/photo")
	public @ResponseBody 
	ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long customerId) {
		String message = "";
		try {
			String destFilename = getImagePath(customerId.toString());
			updatePhoto(customerId, file, destFilename);
			message = "You successfully uploaded " + file.getOriginalFilename() + "! It is saved as " + destFilename;
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "! Try it again changing the name.";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@DeleteMapping(path="/{customerId}/photo")
	public @ResponseBody void deletePhoto(@PathVariable Long customerId) {
		Customer customer = customerJpaRepository.findById(customerId).get();
		s3Service.deletePhoto(getImagePath(customerId.toString()));
		customer.setPhoto(null);
		customerJpaRepository.saveAndFlush(customer);
	}
	
	
// ======================================Private Methods================================================ //
	
	private Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
	    	return -1L;
		} else {
			String username = authentication.getName();
			Long id = userJpaRepository.findByUsername(username).getId();
			return id;
		}
	}
	
	private void updatePhoto(Long customerId, MultipartFile file, String destFilename) {
		Customer customer = customerJpaRepository.findById(customerId).get();
		try {
			s3Service.upload(file, destFilename);
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (AmazonClientException e) {
			e.printStackTrace();
		}
    	customer.setPhoto(bucketRootPath + destFilename);
        customerJpaRepository.saveAndFlush(customer); 
	}
	
	private String getImagePath(String customerId) {
		return "avatarOfCustomer" + customerId.toString() + ".jpg";
	}
}
