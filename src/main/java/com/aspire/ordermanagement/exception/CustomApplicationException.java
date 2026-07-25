package com.aspire.ordermanagement.exception;

public class CustomApplicationException extends RuntimeException{
	
	public CustomApplicationException() {
		super();
	}
	
	public CustomApplicationException(String message) {
		super(message);
	}

}
