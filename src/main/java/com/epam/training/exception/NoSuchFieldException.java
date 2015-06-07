package com.epam.training.exception;

/* 
 * exception is thrown when there is no such field in
 * the PassengerCar object 
 */
public class NoSuchFieldException extends Exception {
	private static final long serialVersionUID = 3L;
	private String description;

	/* constructor with a description */
	public NoSuchFieldException(String description) {
		this.description = description;
	}

	public String getMessage() {
		return description;
	}
}
