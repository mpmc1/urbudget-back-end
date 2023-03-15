package com.urbudget.apitransaction.repository;

import com.urbudget.apitransaction.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends CrudRepository<Transaction, String> {

    @Query("SELECT t FROM Transaction t WHERE t.budget = ?1")
    public Iterable<Transaction> getAllByBudget(String idBudget);
}
