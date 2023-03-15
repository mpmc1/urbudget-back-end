package com.urbudget.apitransaction.service;

import com.urbudget.apitransaction.domain.Transaction;
import com.urbudget.apitransaction.repository.TransactionRepository;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
