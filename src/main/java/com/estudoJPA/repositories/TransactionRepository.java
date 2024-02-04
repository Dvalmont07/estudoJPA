package com.estudoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudoJPA.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
