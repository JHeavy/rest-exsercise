package com.exercise.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.exercise.persistence.enumeration.Currency;

@Entity
@Table(name = "ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
	
	@Id
	@SequenceGenerator(name = "SEQ_ACCOUNT", sequenceName = "SEQ_ACCOUNT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNT")
	@Column(name = "ID", unique = true, nullable = false, precision = 8)
	long id;
	
	@Column(name = "ACCOUNT_NAME", nullable = false, length = 255)
	String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "currency", nullable = false)
	Currency currency;

	@Embedded
	Balance balance;
	
	@Column(name = "TREASURY", nullable = false, columnDefinition="tinyint(1) default 0", length = 1)
	Boolean treasury;

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

	public Boolean getTreasury() {
		return treasury;
	}

	public void setTreasury(Boolean treasury) {
		this.treasury = treasury;
	}
}
