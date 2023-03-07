package com.urbudget.apiyear.repository.budget;

import com.urbudget.apiyear.domain.budget.Budget;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BudgetRepository extends ReactiveCrudRepository<Budget,String> {
}
