package com.exercise.persistence.service;

import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.enumeration.Currency;
import com.exercise.persistence.exception.CustomPersistenceException;

public interface ITransactionBO extends BaseService<Transaction, Long> {
	
	Transaction findByCurrency(Currency arg) throws CustomPersistenceException;

}
