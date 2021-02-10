package com.exercise.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.logic.service.IAccountService;
import com.exercise.persistence.dto.AccountDTO;
import com.exercise.persistence.dto.AccountEditDTO;
import com.exercise.persistence.dto.AccountSaveDTO;
import com.exercise.persistence.dto.ListResponseDTO;
import com.exercise.persistence.dto.PageableDTO;
import com.exercise.persistence.dto.TrasantionDTO;

@RestController
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	IAccountService accountService;
	
	@PostMapping("transaction")
	public ResponseEntity<?> doTransaction(@Valid @RequestBody TrasantionDTO arg) {
		this.accountService.doTransaction(arg);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("balance/{id}")
	public ResponseEntity<AccountDTO> balance(@Valid @RequestBody AccountEditDTO arg, @PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(this.accountService.balance(arg, id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<AccountDTO> update(@Valid @RequestBody AccountEditDTO arg, @PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(this.accountService.update(arg, id));
	}
	
	@PostMapping
	public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountSaveDTO arg) {
		return ResponseEntity.ok(this.accountService.save(arg));
	}

	@PostMapping("find-all")
	public ResponseEntity<ListResponseDTO<AccountDTO>> findAll(@RequestBody(required = true) PageableDTO arg) {
		return ResponseEntity.ok(this.accountService.findAll(arg));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AccountDTO> find(@PathVariable("id") Long arg) {
		return ResponseEntity.ok(this.accountService.findById(arg));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long arg) {
		this.accountService.delete(arg);
		return ResponseEntity.ok().build();
	}
	
}
