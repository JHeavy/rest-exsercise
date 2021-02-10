package com.exercise.persistence;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.enumeration.Currency;
import com.exercise.persistence.exception.CustomNotFoundException;
import com.exercise.persistence.exception.CustomPersistenceException;
import com.exercise.persistence.repository.TransactionRespository;
import com.exercise.persistence.service.impl.TransactionBOImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBOTest {
	
	final Transaction transaction = new Transaction();
	
	@Mock
	TransactionRespository transactionRespository;
	
	@InjectMocks
	TransactionBOImpl transactionBO;
	
	@Before
	public void init() {
		transaction.setFrom(Currency.EUR);
		transaction.setTo(Currency.USD);
		transaction.setValue(1.1);
	}
	
	@Test
	public void findByCurrencyTest() {
		when(transactionRespository.findByCurrency(Currency.EUR)).thenReturn(Optional.of(transaction));
		
		assertNotNull(transactionBO.findByCurrency(Currency.EUR));
	}
	
	@Test(expected = CustomNotFoundException.class)
	public void findByCurrencyTestEmpty() {
		when(transactionRespository.findByCurrency(Currency.EUR)).thenReturn(Optional.empty());
		
		assertNotNull(transactionBO.findByCurrency(Currency.EUR));
	}
	
	@Test(expected = CustomPersistenceException.class)
	public void findByCurrencyTestKo() {
		when(transactionRespository.findByCurrency(Currency.EUR)).thenThrow(RuntimeException.class);
		
		transactionBO.findByCurrency(Currency.EUR);
	}

}
