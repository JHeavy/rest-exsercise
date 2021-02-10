package com.exercise.logic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.logic.service.ITransactionService;
import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.service.ITransactionBO;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	ITransactionBO transactionBO;

	public boolean existsById(Long arg) {
		return transactionBO.existsById(arg);
	}

	public Transaction findById(Long arg) {
		return transactionBO.findById(arg);
	}

	public Transaction save(Transaction arg) {
		return transactionBO.save(arg);
	}

	public void delete(Long arg) {
		transactionBO.delete(arg);
	}
}
