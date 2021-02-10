package com.exercise.logic.service.impl;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.exercise.logic.service.IAccountService;
import com.exercise.persistence.constants.I18nContants;
import com.exercise.persistence.dto.AccountDTO;
import com.exercise.persistence.dto.AccountEditDTO;
import com.exercise.persistence.dto.AccountSaveDTO;
import com.exercise.persistence.dto.ListResponseDTO;
import com.exercise.persistence.dto.PageableDTO;
import com.exercise.persistence.dto.TrasantionDTO;
import com.exercise.persistence.entity.Account;
import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.exception.CustomLogicException;
import com.exercise.persistence.exception.CustomNotFoundException;
import com.exercise.persistence.service.IAccountBO;
import com.exercise.persistence.service.ITransactionBO;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountBO accountBO;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ITransactionBO transactionBO;
	
	public AccountDTO balance(AccountEditDTO arg, Long id) throws CustomLogicException {
		if(ObjectUtils.isEmpty(arg.getCurrency())) {
			throw new CustomLogicException(I18nContants.NOT_INVALID_ERROR);
		}
		
		Account saved = this.accountBO.findById(id);
		
		Transaction transaction = transactionBO.findByCurrency(arg.getCurrency());
		
		if(arg.getCurrency().equals(saved.getCurrency())) {
			return modelMapper.map(saved, AccountDTO.class);
		}
		
		double value;
		
		// change all the value of account
		if(arg.getCurrency().equals(transaction.getFrom())) {
			value = saved.getBalance().getValue() * transaction.getValue();
		} else {
			value = saved.getBalance().getValue() / transaction.getValue();
		}
		
		saved.getBalance().setValue(value);
		saved.setCurrency(arg.getCurrency());
		
		return modelMapper.map(accountBO.save(saved), AccountDTO.class);
	}

	public AccountDTO findById(Long arg) throws CustomNotFoundException {
		return modelMapper.map(accountBO.findById(arg), AccountDTO.class);
	}

	public AccountDTO save(AccountSaveDTO arg) {
		Account converted = modelMapper.map(arg, Account.class);
		return modelMapper.map(accountBO.save(converted), AccountDTO.class);
	}

	public void delete(Long arg) throws CustomNotFoundException {
		accountBO.delete(arg);
	}

	public ListResponseDTO<AccountDTO> findAll(PageableDTO arg) {
		Pageable pageable = PageRequest.of(arg.getPage(), arg.getSize(), arg.asSort());

		Page<Account> page = accountBO.findAll(pageable);

		return ListResponseDTO.of(
				page.getContent().stream().map(c -> modelMapper.map(c, AccountDTO.class)).collect(Collectors.toList()),
				page.getNumber(), page.getTotalPages(), page.getTotalElements());
	}

	@Override
	public AccountDTO update(AccountEditDTO arg, long id) throws CustomLogicException {
		if(ObjectUtils.isEmpty(arg.getName())) {
			throw new CustomLogicException(I18nContants.NOT_INVALID_ERROR);
		}
		
		Account saved = this.accountBO.findById(id);
		
		saved.setName(arg.getName());
		
		return modelMapper.map(accountBO.save(saved), AccountDTO.class);
	}

	@Override
	public void doTransaction(TrasantionDTO arg) throws CustomLogicException {
		Account from = this.accountBO.findById(arg.getFrom());
		Account to = this.accountBO.findById(arg.getTo());
		
		if(!from.getTreasury() && arg.getValue() > from.getBalance().getValue()) {
			throw new CustomLogicException(I18nContants.INVALID_TRANSACTION);
		}
		
		if(from.getCurrency().equals(to.getCurrency())) {
			from.getBalance().setValue(from.getBalance().getValue() - arg.getValue());
			to.getBalance().setValue(to.getBalance().getValue() + arg.getValue());
		} else {
			Transaction transaction = this.transactionBO.findByCurrency(from.getCurrency());
			from.getBalance().setValue(from.getBalance().getValue() - arg.getValue());
			
			double value;
			
			if(to.getCurrency().equals(transaction.getFrom())) {
				value = arg.getValue() * transaction.getValue();
			} else {
				value = arg.getValue() / transaction.getValue();
			}
			
			to.getBalance().setValue(to.getBalance().getValue() + value);
		}
		
		this.accountBO.save(from);
		this.accountBO.save(to);
	}
}
