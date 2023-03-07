package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.Transaction.Transaction;
import com.urbudget.apitransactions.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Iterable<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
    public Optional<Transaction> getOneTransaction(String id){
        return transactionRepository.findById(id);
    }
}
