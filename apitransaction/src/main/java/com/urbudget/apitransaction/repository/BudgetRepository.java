package com.urbudget.apitransaction.repository;

import com.urbudget.apitransaction.domain.Budget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends CrudRepository<Budget, String> {
}

