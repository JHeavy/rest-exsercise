package com.exercise.logic;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.exercise.logic.service.impl.TransactionServiceImpl;
import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.enumeration.Currency;
import com.exercise.persistence.service.ITransactionBO;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
	
	@Mock
	ITransactionBO transactionBO;
	
	@InjectMocks
	TransactionServiceImpl transactionService;
	
	Transaction transaction = new Transaction();
	
	@Before
	public void init() {
		transaction.setFrom(Currency.EUR);
		transaction.setTo(Currency.USD);
		transaction.setValue(10d);
	}
	
	@Test
	public void findByIdTest() {
		when(transactionBO.findById(ArgumentMatchers.anyLong())).thenReturn(transaction);
		
		assertNotNull(transactionService.findById(1l));
	}
	
	@Test
	public void existsByIdTest() {
		when(transactionBO.existsById(ArgumentMatchers.anyLong())).thenReturn(false);
		
		assertNotNull(transactionService.existsById(1l));
	}
	
	@Test
	public void saveTest() {
		when(transactionBO.save(transaction)).thenReturn(transaction);
		
		assertNotNull(transactionService.save(transaction));
	}
	
	@Test
	public void deleteTest() {
		transactionService.delete(1l);
	}

}
