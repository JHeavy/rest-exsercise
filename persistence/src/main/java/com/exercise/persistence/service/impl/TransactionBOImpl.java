package com.exercise.persistence.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.exercise.persistence.constants.I18nContants;
import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.enumeration.Currency;
import com.exercise.persistence.exception.CustomNotFoundException;
import com.exercise.persistence.exception.CustomPersistenceException;
import com.exercise.persistence.repository.TransactionRespository;
import com.exercise.persistence.service.ITransactionBO;

@Service
@Transactional
public class TransactionBOImpl implements ITransactionBO {
	
	@Autowired
	TransactionRespository transactionRespository;

	@Override
	public JpaRepository<Transaction, Long> repository() {
		return transactionRespository;
	}

	@Override
	public Transaction findByCurrency(Currency arg) throws CustomPersistenceException {
		try {
			Optional<Transaction> op = transactionRespository.findByCurrency(arg);
			
			if(!op.isPresent()) {
				throw new IllegalArgumentException();
			}
			
			return op.get();
		} catch(IllegalArgumentException e) {
			throw new CustomNotFoundException(I18nContants.NOT_FOUND_ERROR);
		} catch (Exception e) {
			LOG.error(I18nContants.FIND_ERROR, e);
			throw new CustomPersistenceException(I18nContants.FIND_ERROR);
		}
	}

}
