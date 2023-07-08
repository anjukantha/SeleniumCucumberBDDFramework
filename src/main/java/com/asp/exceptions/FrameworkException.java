package com.asp.exceptions;

/**
 * BaseException for the framework. Extends Runtime Exception and can be thrown
 * anywhere to terminate a program
 * 
 * @author Anjan S P
 */
@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {

	/**
	 * Pass the message that needs to be appended to the stack trace
	 * 
	 * @param message Details about the exception or custom message
	 */
	public FrameworkException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause   Pass the enriched stack trace or customized stack trace
	 */
	public FrameworkException(String message, Throwable cause) {
		super(message, cause);
	}

}
