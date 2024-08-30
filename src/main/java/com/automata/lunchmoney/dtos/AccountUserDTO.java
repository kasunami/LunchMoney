package com.automata.lunchmoney.dtos;

import com.automata.lunchmoney.entities.Transaction;
import lombok.Data;

import java.util.Set;

@Data
public class AccountUserDTO {
    private String accountBalanceId;
    private String firstName;
    private String lastName;
    private Set<Transaction> transactions;
}
