package com.asp.exceptions;

/**
 * Runtime Exception occurs when the path given for excel sheet is incorrect.
 * 
 * @author Anjan S P
 * @see com.asp.constants.FrameworkConstants
 */
@SuppressWarnings("serial")
public class InvalidPathForPropertyFileException extends InvalidPathForFilesException {

	/**
	 * Pass the message that needs to be appended to the stack trace
	 * 
	 * @param message Details about the exception or custom message
	 */
	public InvalidPathForPropertyFileException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause   Pass the enriched stack trace or customized stack trace
	 */
	public InvalidPathForPropertyFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
