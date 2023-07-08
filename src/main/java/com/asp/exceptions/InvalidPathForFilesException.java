package com.asp.exceptions;

/**
 * Runtime Exception occurs when the path given for any of the files is
 * incorrect.
 * 
 * @author Anjan S P
 * @see com.asp.exceptions.InvalidPathForExcelException
 * @see com.asp.exceptions.InvalidPathForPropertyFileException
 */
@SuppressWarnings("serial")
public class InvalidPathForFilesException extends FrameworkException {

	/**
	 * Pass the message that needs to be appended to the stack trace
	 * 
	 * @param message Details about the exception or custom message
	 */
	public InvalidPathForFilesException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause   Pass the enriched stack trace or customized stack trace
	 */
	public InvalidPathForFilesException(String message, Throwable cause) {
		super(message, cause);
	}

}
