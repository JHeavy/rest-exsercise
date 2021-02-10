package com.exercise.persistence.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.exercise.persistence.entity.Balance;
import com.exercise.persistence.enumeration.Currency;

public class AccountSaveDTO {

	@NotBlank(message = "account.name.blank")
	String name;

	@NotNull(message = "account.currency.null")
	Currency currency;

	@NotNull(message = "balance.currency.null")
	Balance balance;

	@NotNull(message = "treasury.currency.null")
	Boolean treasury;

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

	public Boolean getTreasury() {
		return treasury;
	}

	public void setTreasury(Boolean treasury) {
		this.treasury = treasury;
	}
}
