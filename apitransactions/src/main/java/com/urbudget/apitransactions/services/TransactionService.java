package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.Transaction.Transaction;
import com.urbudget.apitransactions.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    private Iterable<Transaction> getAll() {
        return transactionRepository.findAll();
    }
    private Optional<Transaction> getOne(String id){
        return transactionRepository.findById(id);
    }
    private void delete(String id){
        transactionRepository.deleteById(id);
    }
    private Transaction update(Transaction transaction){
        return transactionRepository.save(transaction);
    }
    private Transaction create(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
