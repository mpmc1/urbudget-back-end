package com.urbudget.apitransactions.repositories;

import com.urbudget.apitransactions.domain.Budget;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, String> {
}
