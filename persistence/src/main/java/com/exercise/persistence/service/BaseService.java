package com.exercise.persistence.service;

import java.io.Serializable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.persistence.constants.I18nContants;
import com.exercise.persistence.exception.CustomNotFoundException;
import com.exercise.persistence.exception.CustomPersistenceException;

public interface BaseService<E, F extends Serializable> {
	
	Logger LOG = LoggerFactory.getLogger(BaseService.class);
	
	public JpaRepository<E, F> repository();
	
	/*
	 * Basic crud methods
	 * */
	default boolean existsById(F arg) {
		try {
			return repository().existsById(arg);
		} catch (Exception e) {
			LOG.error(I18nContants.FIND_ERROR, e);
			throw new CustomPersistenceException(I18nContants.FIND_ERROR);
		}
	}
	
	default E findById(F arg) {
		try {
			Optional<E> op = repository().findById(arg);
			
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
	
	default E save(E arg) {
		try {
			return repository().save(arg);
		} catch (Exception e) {
			LOG.error(I18nContants.SAVE_ERROR, e);
			throw new CustomPersistenceException(I18nContants.SAVE_ERROR);
		}
	}
	
	default void delete(F arg) {
		if(!repository().existsById(arg)) {
			throw new CustomNotFoundException(I18nContants.NOT_FOUND_ERROR);
		}
		
		try {
			repository().deleteById(arg);
		} catch (Exception e) {
			LOG.error(I18nContants.DELETE_ERROR, e);
			throw new CustomPersistenceException(I18nContants.DELETE_ERROR);
		}
	}
	
	default Page<E> findAll(Pageable arg) {
		try {
			return repository().findAll(arg);
		} catch (Exception e) {
			LOG.error(I18nContants.FIND_ALL_ERROR, e);
			throw new CustomPersistenceException(I18nContants.FIND_ALL_ERROR);
		}
	}

}
