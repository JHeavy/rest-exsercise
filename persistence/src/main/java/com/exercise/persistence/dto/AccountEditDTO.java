package com.exercise.persistence.dto;

import com.exercise.persistence.enumeration.Currency;

/*
 * The properties can be null because this DTO will be used for multiple transactions
 * */
public class AccountEditDTO {
	
	String name;

	Currency currency;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
