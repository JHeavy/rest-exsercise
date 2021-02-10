package com.exercise.persistence.exception;

public class CustomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5870426904456435613L;

	public CustomNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomNotFoundException(String message) {
		super(message);
	}
}
