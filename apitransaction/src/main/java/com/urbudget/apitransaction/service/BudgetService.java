package com.urbudget.apitransaction.service;

import com.urbudget.apitransaction.domain.Budget;
import com.urbudget.apitransaction.repository.BudgetRepository;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    PersonService personService;


    public Budget save(Budget budget) {
        personService.getById(budget.getUser().getEmail());
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
