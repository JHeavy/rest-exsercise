package com.exercise.persistence.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Balance {

	double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
