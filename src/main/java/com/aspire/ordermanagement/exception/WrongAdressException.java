package com.aspire.ordermanagement.exception;

public class WrongAdressException extends RuntimeException{
	
	 public WrongAdressException(String message) {
		 super(message);
		
	}
	 
	 public WrongAdressException(String message, Throwable cause) {
		 super(message, cause);
	 }
	
}
