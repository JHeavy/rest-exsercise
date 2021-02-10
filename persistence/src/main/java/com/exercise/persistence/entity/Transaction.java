package com.exercise.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.exercise.persistence.enumeration.Currency;

@Entity
@Table(name = "TRANSACTION")
@Inheritance(strategy = InheritanceType.JOINED)
public class Transaction {
	
	@Id
	@SequenceGenerator(name = "SEQ_TRANSACTION", sequenceName = "SEQ_TRANSACTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACTION")
	@Column(name = "ID", unique = true, nullable = false, precision = 8)
	long id;
	
	@Column(name = "T_FROM", nullable = false)
	Currency from;
	
	@Column(name = "T_TO", nullable = false)
	Currency to;
	
	@Column(name = "T_VALUE", nullable = false)
	double value;

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
