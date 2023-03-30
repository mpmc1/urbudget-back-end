package com.urbudget.apitransaction.repository.transaction;

import com.urbudget.apitransaction.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository  extends CrudRepository<Transaction, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM transaction t WHERE t.budget_id_budget = ?1")
    public Iterable<Transaction> getAllByBudget(String idBudget);
    @Query(nativeQuery = true,
            value = "SELECT * FROM transaction t WHERE t.budget_id_budget = ?1 AND t.id_transaction = ?2")
    public Optional<Transaction> getOneByBudgetAndId(String idBudget, String id);
}
