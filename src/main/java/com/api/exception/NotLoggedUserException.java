package com.api.exception;

public class NotLoggedUserException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NotLoggedUserException() {
		super();
	}

	public NotLoggedUserException(String message) {
		super(message);
	}
}
