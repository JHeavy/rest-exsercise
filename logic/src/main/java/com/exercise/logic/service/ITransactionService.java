package com.exercise.logic.service;

import com.exercise.persistence.entity.Transaction;

public interface ITransactionService {
	
	boolean existsById(Long arg);

	Transaction findById(Long arg);

	Transaction save(Transaction arg);

	void delete(Long arg);
}
