package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.transaction.Transaction;
import com.urbudget.apitransactions.repositories.TransactionRepository;
import com.urbudget.apitransactions.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BudgetService budgetService;

    public Iterable<Transaction> getAllByBudget(String idBudget) {
        budgetService.getOne(idBudget);
        return transactionRepository.getAllByBudget(idBudget);
    }
    public Transaction getOne(String id){
        return transactionRepository.findById(id).orElseThrow(()->new CustomException("Transaction Not Found"));
    }
    public void delete(String id){
        transactionRepository.deleteById(id);
    }
    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
