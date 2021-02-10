package com.exercise.persistence.exception;

public class CustomPersistenceException extends RuntimeException {

	private static final long serialVersionUID = -4566201744346965901L;

	public CustomPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomPersistenceException(String message) {
		super(message);
	}
}
