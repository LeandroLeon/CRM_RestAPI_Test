package com.api.exception;

public class ForbiddenActionException extends RuntimeException{

	private static final long serialVersionUID = 150L;

	public ForbiddenActionException() {
		super();
	}
	
	public ForbiddenActionException(String message) {
		super(message);
	}
}
