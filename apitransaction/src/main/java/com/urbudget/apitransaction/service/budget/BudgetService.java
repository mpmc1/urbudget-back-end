package com.urbudget.apitransaction.service.budget;

import com.urbudget.apitransaction.domain.budget.Budget;
import com.urbudget.apitransaction.mensajeria.budget.MessageSenderBroker;
import com.urbudget.apitransaction.repository.budget.BudgetRepository;
import com.urbudget.apitransaction.service.person.PersonService;
import com.urbudget.apitransaction.util.CustomException;
import com.urbudget.apitransaction.util.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;
import java.util.UUID;

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
            if(budgetWithSameYear.isEmpty() || budgetWithSameYear.get().getId().equals(budget.getId())){
                Budget savedBudget = budgetRepository.save(budget);
                return savedBudget;}
            else throw new CustomException("Already exist a budget to that year");
        }else{
            throw new CustomException("Year has to be greater than actual");
        }
    }

    public Budget getIdByEmail(String personId) {
        personService.getById(personId);
        return budgetRepository.getIdByPerson(personId)
                .orElseThrow(()->new CustomException("Budget not found"));

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
