package com.urbudget.apitransactions.repositories;

import com.urbudget.apitransactions.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository  extends CrudRepository<Transaction, String> {

    @Query("SELECT t FROM Transaction t WHERE t.budget = ?1")
    public Iterable<Transaction> getAllByBudget(String idBudget);
}
