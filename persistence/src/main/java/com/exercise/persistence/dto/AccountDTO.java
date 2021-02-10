package com.exercise.persistence.dto;

import com.exercise.persistence.entity.Balance;
import com.exercise.persistence.enumeration.Currency;

public class AccountDTO {

	long id;

	String name;

	Currency currency;

	Balance balance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

}
