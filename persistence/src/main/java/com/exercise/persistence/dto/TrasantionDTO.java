package com.exercise.persistence.dto;

import javax.validation.constraints.Min;

public class TrasantionDTO {
	
	@Min(value = 0, message = "transaction.from")
	long from;
	
	@Min(value = 0, message = "transaction.to")
	long to;
	
	@Min(value = 0, message = "transaction.value")
	double value;

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
