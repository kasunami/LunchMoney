package com.automata.lunchmoney.services;

import com.automata.lunchmoney.dtos.TransactionDTO;
import com.automata.lunchmoney.entities.AccountUser;
import com.automata.lunchmoney.entities.Transaction;
import com.automata.lunchmoney.mappers.TransactionMapper;
import com.automata.lunchmoney.repositories.AccountUserRepository;
import com.automata.lunchmoney.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountUserRepository accountUserRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountUserRepository accountUserRepository) {
        this.transactionRepository = transactionRepository;
        this.accountUserRepository = accountUserRepository;
    }

    public boolean addTransactions(String accountId, List<TransactionDTO> transactionDTOS) {
        AccountUser accountUser = accountUserRepository.findAccountUserByAccountBalanceId(accountId);

        if(accountUser != null) {
            List<Transaction> transactions = transactionDTOS.stream().map(transactionDTO -> {
                Transaction transaction = TransactionMapper.toEntity(transactionDTO);
                transaction.setAccountUser(accountUser);
                return transaction;
            }).toList();
            List<Transaction> savedTransactions = transactionRepository.saveAll(transactions);
            return !savedTransactions.isEmpty();
        }
        return false;
    }

    public List<TransactionDTO> getTransactionsByAccount(String accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountUser_AccountBalanceId(accountId);
        return transactions.stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO updateTransaction(UUID transactionId, TransactionDTO transactionDTO) {
        Transaction existingTransaction = transactionRepository.findById(transactionId).orElse(null);

        if (existingTransaction != null) {
            existingTransaction.setDate(transactionDTO.getDate());
            existingTransaction.setItem(transactionDTO.getItem());
            existingTransaction.setAmount(transactionDTO.getAmount());
            existingTransaction.setQuantity(transactionDTO.getQuantity());

            Transaction updatedTransaction = transactionRepository.save(existingTransaction);
            return TransactionMapper.toDTO(updatedTransaction);
        } else {
            // Handle transaction not found case (e.g., throw an exception)
            return null;
        }
    }

    public void deleteTransaction(UUID transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public TransactionDTO getTransactionById(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        return TransactionMapper.toDTO(transaction);
    }
}