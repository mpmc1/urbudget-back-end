package com.urbudget.apitransactions.repositories;

import com.urbudget.apitransactions.domain.Transaction.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository  extends CrudRepository<Transaction, String> {
}
