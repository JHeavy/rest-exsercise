package com.exercise.persistence.exception;

public class CustomLogicException extends RuntimeException {

	private static final long serialVersionUID = -6670921989628686245L;

	public CustomLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomLogicException(String message) {
		super(message);
	}
}
