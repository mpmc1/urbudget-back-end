package com.urbudget.apitransaction.service.transaction;

import com.urbudget.apitransaction.domain.budget.Budget;
import com.urbudget.apitransaction.domain.transaction.Transaction;
import com.urbudget.apitransaction.repository.transaction.TransactionRepository;
import com.urbudget.apitransaction.util.CustomException;
import com.urbudget.apitransaction.util.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    private final MessageSender<Transaction> messageSenderClient;

    public TransactionService(MessageSender<Transaction> messageSenderClient) {
        this.messageSenderClient = messageSenderClient;
    }
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
        Transaction saveTransaction = transactionRepository.save(transaction);
        messageSenderClient.execute(saveTransaction, UUID.randomUUID().toString());
        return saveTransaction;
    }
}
