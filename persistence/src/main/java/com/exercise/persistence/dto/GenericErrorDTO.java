package com.exercise.persistence.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GenericErrorDTO {
	
	public static final GenericErrorDTO of(Throwable e) {
		GenericErrorDTO res = new GenericErrorDTO();
		res.message = e.getMessage();
		res.description = e.getClass().getSimpleName();
		res.time = LocalDateTime.now();
		
		return res;
	}
	
	String message;
	
	String description;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime time;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
