package com.asp.exceptions;

/**
 * Runtime Exception occurs when the key or value fetched from the property file
 * is null
 * 
 * @author Anjan S P
 * @see com.asp.constants.FrameworkConstants
 * @see com.asp.utils.PropertyUtils
 */
@SuppressWarnings("serial")
public class PropertyFileUsageException extends FrameworkException {

	/**
	 * Pass the message that needs to be appended to the stack trace
	 * 
	 * @param message Details about the exception or custom message
	 */
	public PropertyFileUsageException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause   Pass the enriched stack trace or customized stack trace
	 */
	public PropertyFileUsageException(String message, Throwable cause) {
		super(message, cause);
	}

}
