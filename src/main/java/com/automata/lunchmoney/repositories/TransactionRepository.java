package com.automata.lunchmoney.repositories;

import com.automata.lunchmoney.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByAccountUser_AccountBalanceId(String accountBalanceId);
}
