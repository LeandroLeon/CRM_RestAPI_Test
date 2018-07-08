package com.api.exception;

public class MoreThanOneOwnerException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public MoreThanOneOwnerException(){
        super();
    }
    
    
    public MoreThanOneOwnerException(String message){
        super(message);
    }
}
