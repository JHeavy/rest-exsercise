package com.exercise.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exercise.persistence.constants.I18nContants;
import com.exercise.persistence.dto.GenericErrorDTO;
import com.exercise.persistence.exception.CustomLogicException;
import com.exercise.persistence.exception.CustomNotFoundException;
import com.exercise.persistence.exception.CustomPersistenceException;

@RestControllerAdvice
public class ExceptionController {
	
	static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<GenericErrorDTO> another(Exception ex) {
		LOG.error(I18nContants.GENERAL_ERROR, ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericErrorDTO.of(new IllegalArgumentException(I18nContants.GENERAL_ERROR)));
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<GenericErrorDTO> validation(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericErrorDTO.of(new IllegalArgumentException(ex.getBindingResult().getFieldError().getDefaultMessage())));
	}
	
	@ExceptionHandler(value = CustomNotFoundException.class)
	public ResponseEntity<GenericErrorDTO> notFound(CustomNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericErrorDTO.of(ex));
	}
	
	@ExceptionHandler(value = CustomPersistenceException.class)
	public ResponseEntity<GenericErrorDTO> persistenceException(CustomPersistenceException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericErrorDTO.of(ex));
	}
	
	@ExceptionHandler(value = CustomLogicException.class)
	public ResponseEntity<GenericErrorDTO> logicExceptionException(CustomLogicException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(GenericErrorDTO.of(ex));
	}

}
