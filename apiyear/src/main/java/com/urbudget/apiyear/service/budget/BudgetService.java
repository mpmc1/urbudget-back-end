package com.urbudget.apiyear.service.budget;

import com.urbudget.apiyear.domain.budget.Budget;
import com.urbudget.apiyear.repository.budget.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;

    public Flux<Budget> get() {
        return budgetRepository.findAll();
    }

    /*
    private Budget update(Budget budget)
    {
        return budgetRepository.;
    }*/

    public Mono<Budget> save(Budget budget) {

        return budgetRepository.save(budget);
    }

    public Mono<Void> delete(String idBudget) {
        return budgetRepository.findById(idBudget).flatMap(existingBudget -> budgetRepository.deleteById(idBudget));
    }

    public Mono<Budget> getById(String idBudget){
        return budgetRepository.findById(idBudget);
    }
}
