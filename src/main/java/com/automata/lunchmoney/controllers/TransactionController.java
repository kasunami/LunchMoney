package com.automata.lunchmoney.controllers;

import com.automata.lunchmoney.dtos.TransactionDTO;
import com.automata.lunchmoney.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/addTransactions/{accountId}")
    public ResponseEntity<Boolean> addTransactions(@PathVariable String accountId, @RequestBody List<TransactionDTO> transactions) {
        Boolean createdTransactions = transactionService.addTransactions(accountId, transactions);
        return ResponseEntity.ok(createdTransactions);
    }

    @GetMapping("/byAccount/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccount(@PathVariable String accountId) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable UUID transactionId, @RequestBody TransactionDTO transaction) {
        TransactionDTO updatedTransaction = transactionService.updateTransaction(transactionId, transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/deleteTransaction/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable UUID transactionId) {
        TransactionDTO transaction = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }
}