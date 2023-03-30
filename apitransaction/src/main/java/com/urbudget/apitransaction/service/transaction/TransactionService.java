package com.urbudget.apitransaction.service.transaction;

import com.urbudget.apitransaction.domain.budget.Budget;
import com.urbudget.apitransaction.domain.transaction.Transaction;
import com.urbudget.apitransaction.repository.transaction.TransactionRepository;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Iterable<Transaction> getAllByBudget(Budget budget) {
        return transactionRepository.getAllByBudget(budget.getId());
    }
    public Transaction getOneByBudgetAndId(Budget budget, String id){
        return transactionRepository.getOneByBudgetAndId(budget.getId(),id).orElseThrow(()->new CustomException("Transaction Not Found"));
    }
    public void delete(String id){
        transactionRepository.deleteById(id);
    }
    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
