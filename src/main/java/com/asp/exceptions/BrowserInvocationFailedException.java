/**
 * 
 */
package com.asp.exceptions;

/**
 * @author Anjan S P
 */
@SuppressWarnings("serial")
public class BrowserInvocationFailedException extends FrameworkException {

	/**
	 * Pass the message that needs to be appended to the stack trace
	 * 
	 * @param message Details about the exception or custom message
	 */
	public BrowserInvocationFailedException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause   Pass the enriched stack trace or customized stack trace
	 */
	public BrowserInvocationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

}
