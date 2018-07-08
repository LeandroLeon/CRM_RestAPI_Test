package com.api.exception;

public class IllegalDeleteOperationException extends RuntimeException {
	 private static final long serialVersionUID = 1L;
	 
	    public IllegalDeleteOperationException(){
	        super();
	    }
	    
	    
	    public IllegalDeleteOperationException(String message){
	        super(message);
	    }
}
