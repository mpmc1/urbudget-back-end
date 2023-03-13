package com.urbudget.apitransactions.services;

import com.urbudget.apitransactions.domain.Budget.Budget;
import com.urbudget.apitransactions.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;


    public Budget create(Budget budget){
        return budgetRepository.save(budget);
    }
    public Iterable<Budget> getAll(){
        return budgetRepository.findAll();
    }
    public Optional<Budget> getOne(String id){
        return budgetRepository.findById(id);
    }
    public Budget update(Budget budget){
        return budgetRepository.save(budget);
    }
    public boolean delete(String id){
        try {
            budgetRepository.deleteById(id);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
