package com.exercise.logic.service;

import com.exercise.persistence.dto.AccountDTO;
import com.exercise.persistence.dto.AccountEditDTO;
import com.exercise.persistence.dto.AccountSaveDTO;
import com.exercise.persistence.dto.ListResponseDTO;
import com.exercise.persistence.dto.PageableDTO;
import com.exercise.persistence.dto.TrasantionDTO;
import com.exercise.persistence.exception.CustomLogicException;
import com.exercise.persistence.exception.CustomNotFoundException;

public interface IAccountService {
	
	AccountDTO balance(AccountEditDTO arg, Long id) throws CustomLogicException;
	
	ListResponseDTO<AccountDTO> findAll(PageableDTO arg);
	
	AccountDTO save(AccountSaveDTO arg);
	
	AccountDTO update(AccountEditDTO arg, long id) throws CustomLogicException;
	
	AccountDTO findById(Long arg) throws CustomNotFoundException;
	
	void delete(Long arg) throws CustomLogicException;
	
	void doTransaction(TrasantionDTO arg) throws CustomLogicException;

}
