package com.asp.exceptions;

/**
 * Runtime Exception occurs when the path given for excel sheet is incorrect.
 * 
 * @author Anjan S P
 * @see com.asp.constants.FrameworkConstants
 */
@SuppressWarnings("serial")
public class InvalidPathForExcelException extends InvalidPathForFilesException {

	/**
	 * Pass the message that needs to be appended to the stack trace
	 * 
	 * @param message Details about the exception or custom message
	 */
	public InvalidPathForExcelException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause   Pass the enriched stack trace or customized stack trace
	 */
	public InvalidPathForExcelException(String message, Throwable cause) {
		super(message, cause);
	}
}
