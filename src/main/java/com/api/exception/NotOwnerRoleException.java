package com.api.exception;

public class NotOwnerRoleException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NotOwnerRoleException() {
        super();
    }
    public NotOwnerRoleException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotOwnerRoleException(String message) {
        super(message);
    }
    public NotOwnerRoleException(Throwable cause) {
        super(cause);
    }
	
}
