package com.exercise.logic;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.exercise.logic.service.impl.AccountServiceImpl;
import com.exercise.persistence.dto.AccountDTO;
import com.exercise.persistence.dto.AccountEditDTO;
import com.exercise.persistence.dto.AccountSaveDTO;
import com.exercise.persistence.dto.PageableDTO;
import com.exercise.persistence.dto.TrasantionDTO;
import com.exercise.persistence.entity.Account;
import com.exercise.persistence.entity.Balance;
import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.enumeration.Currency;
import com.exercise.persistence.enumeration.OrderDTO;
import com.exercise.persistence.exception.CustomLogicException;
import com.exercise.persistence.service.IAccountBO;
import com.exercise.persistence.service.ITransactionBO;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	
	@Mock
	IAccountBO accountBO;

	@Mock
	ModelMapper modelMapper;
	
	@Mock
	ITransactionBO transactionBO;
	
	@InjectMocks
	AccountServiceImpl accountService;
	
	final Account account = new Account();
	final Account account_2 = new Account();
	final Transaction transaction = new Transaction();
	final AccountDTO accountDTO = new AccountDTO();
	
	@Before
	public void init() {
		Balance balance = new Balance();
		balance.setValue(100d);
		
		accountDTO.setBalance(balance);
		accountDTO.setCurrency(Currency.EUR);
		accountDTO.setId(1l);
		accountDTO.setName("TEST");
		
		account.setBalance(balance);
		account.setCurrency(Currency.EUR);
		account.setId(1l);
		account.setName("TEST");
		account.setTreasury(true);
		
		account_2.setBalance(balance);
		account_2.setCurrency(Currency.USD);
		account_2.setId(2l);
		account_2.setName("TEST");
		account_2.setTreasury(true);
		
		transaction.setFrom(Currency.EUR);
		transaction.setTo(Currency.USD);
		transaction.setValue(20);
	}
	
	@Test
	public void balance() {
		when(accountBO.findById(ArgumentMatchers.anyLong())).thenReturn(account);
		when(accountBO.save(account)).thenReturn(account);
		when(transactionBO.findByCurrency(Currency.USD)).thenReturn(transaction);
		when(modelMapper.map(account, AccountDTO.class)).thenReturn(accountDTO);
		
		AccountEditDTO test = new AccountEditDTO();
		test.setCurrency(Currency.USD);
		test.setName("TEST");
		
		accountService.balance(test, 1l);
	}
	
	@Test
	public void balance0() {
		when(accountBO.findById(ArgumentMatchers.anyLong())).thenReturn(account);
		when(modelMapper.map(account, AccountDTO.class)).thenReturn(accountDTO);
		
		AccountEditDTO test = new AccountEditDTO();
		test.setCurrency(Currency.EUR);
		test.setName("TEST");
		
		accountService.balance(test, 1l);
	}
	
	@Test
	public void balance1() {
		transaction.setFrom(Currency.USD);
		transaction.setTo(Currency.EUR);
		
		when(accountBO.findById(ArgumentMatchers.anyLong())).thenReturn(account);
		when(accountBO.save(account)).thenReturn(account);
		when(transactionBO.findByCurrency(Currency.USD)).thenReturn(transaction);
		when(modelMapper.map(account, AccountDTO.class)).thenReturn(accountDTO);
		
		AccountEditDTO test = new AccountEditDTO();
		test.setCurrency(Currency.USD);
		test.setName("TEST");
		
		assertNotNull(accountService.balance(test, 1l));
	}
	
	@Test
	public void findByIdTest() {
		when(accountBO.findById(ArgumentMatchers.anyLong())).thenReturn(account);
		when(modelMapper.map(account, AccountDTO.class)).thenReturn(accountDTO);
		
		assertNotNull(accountService.findById(1l));
	}
	
	@Test
	public void saveTest() {
		Balance balance = new Balance();
		balance.setValue(100d);
		
		AccountSaveDTO accountSaveDTO = new AccountSaveDTO();
		accountSaveDTO.setBalance(balance);
		accountSaveDTO.setCurrency(Currency.EUR);
		accountSaveDTO.setName("TEST");
		accountSaveDTO.setTreasury(true);
		
		when(modelMapper.map(accountSaveDTO, Account.class)).thenReturn(account);
		when(modelMapper.map(account, AccountDTO.class)).thenReturn(accountDTO);
		when(accountBO.save(account)).thenReturn(account);
		
		assertNotNull(accountService.save(accountSaveDTO));
	}
	
	@Test
	public void deleteTest() {
		accountService.delete(1l);
	}
	
	@Test
	public void findAllTest() {
		PageableDTO dto = new PageableDTO();
		dto.setOrder(OrderDTO.ASC);
		dto.setPage(0);
		dto.setSize(10);
		dto.setSortBy("id");
		
		Page<Account> page = new PageImpl<Account>(Lists.newArrayList(account));
		
		when(accountBO.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);
		
		assertNotNull(accountService.findAll(dto));
	}
	
	@Test
	public void updateTest() {
		AccountEditDTO edit = new AccountEditDTO();
		edit.setCurrency(Currency.USD);
		edit.setName("SAMPLE");
		
		when(accountBO.findById(ArgumentMatchers.anyLong())).thenReturn(account);
		when(modelMapper.map(account, AccountDTO.class)).thenReturn(accountDTO);
		when(accountBO.save(account)).thenReturn(account);
		
		assertNotNull(accountService.update(edit, 1l));
	}
	
	@Test(expected = CustomLogicException.class)
	public void updateTest0() {
		AccountEditDTO edit = new AccountEditDTO();
		
		accountService.update(edit, 1l);
	}
	
	@Test
	public void doTransactionTest() {
		TrasantionDTO dto = new TrasantionDTO();
		dto.setFrom(1l);
		dto.setTo(2l);
		dto.setValue(20d);
		
		when(accountBO.findById(2l)).thenReturn(account_2);
		when(accountBO.findById(1l)).thenReturn(account);
		when(transactionBO.findByCurrency(ArgumentMatchers.any(Currency.class))).thenReturn(transaction);
		when(accountBO.save(account)).thenReturn(account);
		
		accountService.doTransaction(dto);
	}
	
	@Test
	public void doTransactionTest0() {
		TrasantionDTO dto = new TrasantionDTO();
		dto.setFrom(1l);
		dto.setTo(2l);
		dto.setValue(20d);
		
		transaction.setFrom(Currency.USD);
		
		when(accountBO.findById(2l)).thenReturn(account_2);
		when(accountBO.findById(1l)).thenReturn(account);
		when(transactionBO.findByCurrency(ArgumentMatchers.any(Currency.class))).thenReturn(transaction);
		when(accountBO.save(account)).thenReturn(account);
		
		accountService.doTransaction(dto);
	}
	
	@Test
	public void doTransactionTest1() {
		TrasantionDTO dto = new TrasantionDTO();
		dto.setFrom(1l);
		dto.setTo(2l);
		dto.setValue(20d);
		
		account_2.setCurrency(Currency.EUR);
		
		when(accountBO.findById(2l)).thenReturn(account_2);
		when(accountBO.findById(1l)).thenReturn(account);
		when(transactionBO.findByCurrency(ArgumentMatchers.any(Currency.class))).thenReturn(transaction);
		when(accountBO.save(account)).thenReturn(account);
		
		accountService.doTransaction(dto);
	}
	
	@Test(expected = CustomLogicException.class)
	public void doTransactionTestKo() {
		TrasantionDTO dto = new TrasantionDTO();
		dto.setFrom(1l);
		dto.setTo(2l);
		dto.setValue(2000d);
		
		account.setTreasury(false);
		
		when(accountBO.findById(2l)).thenReturn(account_2);
		when(accountBO.findById(1l)).thenReturn(account);
		when(transactionBO.findByCurrency(ArgumentMatchers.any(Currency.class))).thenReturn(transaction);
		when(accountBO.save(account)).thenReturn(account);
		
		accountService.doTransaction(dto);
	}

}
