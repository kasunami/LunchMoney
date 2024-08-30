package com.automata.lunchmoney.mappers;

import com.automata.lunchmoney.entities.Transaction;
import com.automata.lunchmoney.dtos.TransactionDTO;

public class TransactionMapper {
    
    public static TransactionDTO toDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        
        TransactionDTO dto = new TransactionDTO();
        dto.setDate(transaction.getDate());
        dto.setItem(transaction.getItem());
        dto.setAmount(transaction.getAmount());
        dto.setQuantity(transaction.getQuantity());

        return dto;
    }

    public static Transaction toEntity(TransactionDTO transactionDTO) {
        if (transactionDTO == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setDate(transactionDTO.getDate());
        transaction.setItem(transactionDTO.getItem());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setQuantity(transactionDTO.getQuantity());

        return transaction;
    }
}