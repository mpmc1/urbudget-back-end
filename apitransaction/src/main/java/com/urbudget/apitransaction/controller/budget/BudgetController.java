package com.urbudget.apitransaction.controller.budget;

import com.urbudget.apitransaction.domain.Budget;
import com.urbudget.apitransaction.domain.Patch;
import com.urbudget.apitransaction.domain.Person;
import com.urbudget.apitransaction.domain.Response;
import com.urbudget.apitransaction.service.BudgetService;
import com.urbudget.apitransaction.service.PersonService;
import com.urbudget.apitransaction.service.TransactionService;
import com.urbudget.apitransaction.util.CustomException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/transaction")
public class BudgetController {

    @Autowired
    PersonService personService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    BudgetService budgetService;

    private static final String NOT_VALID="Not valid operation";
    private static final String SUCCESS="Success";
    private static final String REMOVE="remove";
    private static final String REPLACE="replace";

    @PostMapping("users/{email}/budgets")
    public Response<Budget> createBudget(@PathVariable("email") String personEmail, @RequestBody Budget budget) {
        Response<Budget> response = new Response<>();
        try {
            Person person = personService.getById(personEmail);
            budget.setUser(person);
            Budget responseBudget = budgetService.save(budget);
            response.setData(responseBudget);
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PatchMapping("users/{email}/budgets/{budgetId}")
    public Response<Budget> patchBudget(@PathVariable("email") String email,
                                        @PathVariable("budgetId") String id, @RequestBody Patch patch) {
        Response<Budget> response = new Response<>();
        try {
            Budget budgetToUpdate = budgetService.getOne(email,id);
            Object person = Hibernate.unproxy(budgetToUpdate.getUser());
            budgetToUpdate.setUser((Person) person);
            switch (patch.getOp()) {
                case REPLACE -> budgetToUpdate.setByKey(patch.getKey(), patch.getValue());
                case REMOVE -> budgetToUpdate.setByKey(patch.getKey(), null);
                case "test" -> {
                    boolean comparisonResult = budgetToUpdate.getByKey(patch.getKey()).equals(patch.getValue());
                    response.addMessage(String.valueOf(comparisonResult));
                }
                default -> throw new CustomException(NOT_VALID);
            }
            if (response.getMessages().isEmpty()) {
                Budget responseBudget = budgetService.save(budgetToUpdate);
                response.setData(responseBudget);
                response.addMessage(SUCCESS);
            }
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}/budgets/{budgetId}")
    public Response<Budget> getBudget(@PathVariable("email") String email, @PathVariable("budgetId") String id) {
        Response<Budget> response = new Response<>();
        try {
            Budget budget = budgetService.getOne(email, id);
            Object person = Hibernate.unproxy(budget.getUser());
            budget.setUser((Person) person);
            response.setData(budget);
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

}
