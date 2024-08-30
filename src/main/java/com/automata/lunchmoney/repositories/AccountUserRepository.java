package com.automata.lunchmoney.repositories;

import com.automata.lunchmoney.entities.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, UUID> {
    AccountUser findAccountUserByAccountBalanceId(String accountBalanceId);
}