package com.urbudget.apitransaction.controller;

import com.urbudget.apitransaction.domain.budget.Budget;
import com.urbudget.apitransaction.domain.patch.Patch;
import com.urbudget.apitransaction.domain.person.Person;
import com.urbudget.apitransaction.domain.response.Response;
import com.urbudget.apitransaction.domain.transaction.Transaction;
import com.urbudget.apitransaction.domain.transactiondto.TransactionDTO;
import com.urbudget.apitransaction.service.BudgetService;
import com.urbudget.apitransaction.service.TransactionService;
import com.urbudget.apitransaction.service.PersonService;
import com.urbudget.apitransaction.util.CustomException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

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
    @GetMapping("/users")
    public Iterable<Person> getUsers() {
        return personService.getAll();
    }

    @PostMapping("users")
    public Response<Person> createUser(@RequestBody Person person) {
        Response<Person> response = new Response<>();
        try {
            Person responsePerson = personService.save(person);
            response.setData(responsePerson);
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PatchMapping("users/{email}")
    public Response<Person> patchUser(@PathVariable String email, @RequestBody Patch patch) {
        Response<Person> response = new Response<>();
        try {
            Person personToUpdate = personService.getById(email);
            switch (patch.getOp()) {
                case REPLACE -> personToUpdate.setByKey(patch.getKey(), patch.getValue());
                case REMOVE -> personToUpdate.setByKey(patch.getKey(), null);
                case "test" -> {
                    boolean comparisonResult = personToUpdate.getByKey(patch.getKey()).equals(patch.getValue());
                    response.addMessage(String.valueOf(comparisonResult));
                }
                default -> throw new CustomException(NOT_VALID);
            }
            if (response.getMessages().isEmpty()) {
                Person responsePerson = personService.update(personToUpdate);
                response.setData(responsePerson);
                response.addMessage(SUCCESS);
            }
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}")
    public Response<Person> getUser(@PathVariable String email) {
        Response<Person> response = new Response<>();
        try {
            Person responsePerson = personService.getById(email);
            response.setData(responsePerson);
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}/budgets")
    public Response<Iterable<Budget>> getBudgets(@PathVariable("email") String email) {
        Response<Iterable<Budget>> response = new Response<>();
        try {
            personService.getById(email);
            Iterable<Budget> responseBudget = budgetService.getAll(email);
            response.setData(responseBudget);
                response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;

    }

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

    @GetMapping("users/{email}/budgets/{budgetId}/transactions")
    public Response<Iterable<Transaction>> getTransactionsByBudget(@PathVariable("email") String email,
                                                                   @PathVariable("budgetId") String budgetId) {
        Response<Iterable<Transaction>> response = new Response<>();
        try {
            Budget budget = budgetService.getOne(email,budgetId);
            response.setData(transactionService.getAllByBudget(budget));
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("users/{email}/budgets/{budgetId}/transactions")
    public Response<Transaction> saveTransaction(@PathVariable("email") String email,
                                                 @PathVariable("budgetId") String budgetId,
                                                 @RequestBody TransactionDTO transactionDTO) {
        Response<Transaction> response = new Response<>();
        try {
            Budget budget = budgetService.getOne(email,budgetId);
            Transaction transaction =
                    new Transaction(transactionDTO.getAmmount(),budget, transactionDTO.getDescription());
            response.setData(transactionService.save(transaction));
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PatchMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Response<Transaction> patchTransaction(@PathVariable("email") String email,
                                                  @PathVariable("budgetId") String budgetId,
                                                  @PathVariable("transactionId") String transactionId,
                                                  @RequestBody Patch patch) {
        Response<Transaction> response = new Response<>();
        try {
            Budget budget = budgetService.getOne(email,budgetId);
            Transaction transactionToUpdate = transactionService.getOneByBudgetAndId(budget,transactionId);
            switch (patch.getOp()) {
                case REPLACE -> transactionToUpdate.setByKey(patch.getKey(), patch.getValue());
                case REMOVE -> transactionToUpdate.setByKey(patch.getKey(), null);
                case "test" -> {
                    boolean comparisonResult = transactionToUpdate.getByKey(patch.getKey()).equals(patch.getValue());
                    response.addMessage(String.valueOf(comparisonResult));
                }
                default -> throw new CustomException(NOT_VALID);
            }
            if (response.getMessages().isEmpty()) {
                response.setData(transactionService.save(transactionToUpdate));
                response.addMessage(SUCCESS);
            }
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Response<Transaction> getTransaction(@PathVariable("email") String email,
                                                @PathVariable("budgetId") String budgetId,
                                                @PathVariable String transactionId) {
        Response<Transaction> response = new Response<>();
        try {
            Budget budget= budgetService.getOne(email,budgetId);
            response.setData(transactionService.getOneByBudgetAndId(budget,transactionId));
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }
}
