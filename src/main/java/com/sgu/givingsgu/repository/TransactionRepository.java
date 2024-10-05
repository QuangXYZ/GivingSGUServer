package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
