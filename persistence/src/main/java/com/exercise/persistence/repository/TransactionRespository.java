package com.exercise.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exercise.persistence.entity.Transaction;
import com.exercise.persistence.enumeration.Currency;

import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRespository extends JpaRepository<Transaction, Long> {
	
	@Query("select c from Transaction c where c.from = :arg or c.to = :arg")
	Optional<Transaction> findByCurrency(@Param("arg") Currency arg);

}
