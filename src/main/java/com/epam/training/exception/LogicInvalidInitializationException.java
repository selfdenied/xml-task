package com.epam.training.exception;

/* 
 * exception is thrown when there is an attempt to initialize 
 * logic class using a 'null' value 
 */
public class LogicInvalidInitializationException extends Exception {
	private static final long serialVersionUID = 1L;
	private String description;

	/* constructor with a description */
	public LogicInvalidInitializationException(String description) {
		this.description = description;
	}

	public String getMessage() {
		return description;
	}
}
