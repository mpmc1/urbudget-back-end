package com.urbudget.apitransaction.service;

import com.urbudget.apitransaction.domain.Budget;
import com.urbudget.apitransaction.repository.BudgetRepository;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    PersonService personService;


    public Budget save(Budget budget) {
        personService.getById(budget.getUser().getEmail());
        if (budget.getYear() > Year.now().getValue()) {
            Optional<Budget> budgetWithSameYear = budgetRepository.
                    getOneByPersonAndYear(budget.getUser().getEmail(),budget.getYear());
            if(budgetWithSameYear.isEmpty()) return budgetRepository.save(budget);
            else throw new CustomException("Already exist a budget to that year");
        }else{
            throw new CustomException("Year has to be greater than actual");
        }
    }

    public Iterable<Budget> getAll(String email) {
        return budgetRepository.getAllByPerson(email);
    }

    public Budget getOne(String personId,String idBudget) {
        personService.getById(personId);
        return budgetRepository.getOneByPersonAndId(personId, idBudget)
                .orElseThrow(()->new CustomException("Budget not found"));
    }

    public void delete(String id) {
        budgetRepository.deleteById(id);
    }
}
