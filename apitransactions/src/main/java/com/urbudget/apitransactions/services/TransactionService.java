package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.Transaction.Transaction;
import com.urbudget.apitransactions.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Iterable<Transaction> getAllByBudget(String idBudget) {
        return transactionRepository.getAllByBudget(idBudget);
    }
    public Optional<Transaction> getOne(String id){
        return transactionRepository.findById(id);
    }
    public void delete(String id){
        transactionRepository.deleteById(id);
    }
    public Transaction update(Transaction transaction){
        return transactionRepository.save(transaction);
    }
    public Transaction create(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
