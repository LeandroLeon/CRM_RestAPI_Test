package com.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotLoggedUserException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NotLoggedUserException() {
		super();
	}

	public NotLoggedUserException(String message) {
		super(message);
	}
}
