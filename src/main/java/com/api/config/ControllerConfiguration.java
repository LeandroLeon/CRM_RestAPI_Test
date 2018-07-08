package com.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.exception.ForbiddenActionException;
import com.api.exception.IllegalDeleteOperationException;
import com.api.exception.MoreThanOneOwnerException;
import com.api.exception.NotLoggedUserException;

@ControllerAdvice
public class ControllerConfiguration {

	@ExceptionHandler(MoreThanOneOwnerException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid data sent. Just one owner permitted")
	public void notValid() {
		
	}
	
	@ExceptionHandler(ForbiddenActionException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="You can neither manage an admin and nor the owner")
	public void deletingOwner() {
		
	}
	
	@ExceptionHandler(NotLoggedUserException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="You must be logged In")
	public void userNotLogged() {
		
	}
	
	@ExceptionHandler(IllegalDeleteOperationException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="It is forbidden to delete the owner")
	public void forbiddenDeletingOwner() {
		
	}
	
}
