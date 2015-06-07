package com.epam.training.exception;

/* exception is thrown when an illegal value of a set parameter is used */
public class IllegalSetValueException extends Exception {
	private static final long serialVersionUID = 1L;
	private String description;

	/* constructor with a description */
	public IllegalSetValueException(String description) {
		this.description = description;
	}

	public String getMessage() {
		return description;
	}
}
