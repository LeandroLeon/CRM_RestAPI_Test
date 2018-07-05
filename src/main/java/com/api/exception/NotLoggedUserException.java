package com.api.exception;

public class NotLoggedUserException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NotLoggedUserException() {
		super();
	}

	public NotLoggedUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotLoggedUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotLoggedUserException(String message) {
		super(message);
	}

	public NotLoggedUserException(Throwable cause) {
		super(cause);
	}

}
