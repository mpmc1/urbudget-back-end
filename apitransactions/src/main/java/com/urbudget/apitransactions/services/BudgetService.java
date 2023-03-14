package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.budget.Budget;
import com.urbudget.apitransactions.domain.user.User;
import com.urbudget.apitransactions.repositories.BudgetRepository;
import com.urbudget.apitransactions.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Year;
import java.util.Optional;

public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    UserService userService;


    public Budget save(Budget budget) {
            userService.getById(budget.getUser().getEmail());
            if (budget.getYear() > Year.now().getValue()) {
                return budgetRepository.save(budget);
            }else{
                throw new CustomException("Year has to be greater than actual");
            }
    }

    public Iterable<Budget> getAll() {
        return budgetRepository.findAll();
    }

    public Budget getOne(String id) {
        return budgetRepository.findById(id).orElseThrow(()->new CustomException("Budget Not Found"));
    }

    public void delete(String id) {
        budgetRepository.deleteById(id);
    }
}
