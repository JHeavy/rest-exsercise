package com.exercise.persistence.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.exercise.persistence.entity.Account;
import com.exercise.persistence.repository.AccountRepository;
import com.exercise.persistence.service.IAccountBO;

@Service
@Transactional
public class AccountBOImpl implements IAccountBO {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public JpaRepository<Account, Long> repository() {
		return accountRepository;
	}

}
