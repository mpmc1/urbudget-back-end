package com.urbudget.apiyear.service.budget;

import com.urbudget.apiyear.domain.budget.Budget;
import com.urbudget.apiyear.repository.budget.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;
    private Optional<Budget> get(String id){
        return budgetRepository.findById(id);

    }
    private Budget create(Budget budget){
        return null;
    }

    private Budget update(Budget budget){
        return null;
    }

    private List<Budget> getAll(){

        return null;

    }
}
