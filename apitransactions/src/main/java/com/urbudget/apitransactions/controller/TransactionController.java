package com.urbudget.apitransactions.controller;

import com.urbudget.apitransactions.domain.transaction.Transaction;
import com.urbudget.apitransactions.domain.patch.Patch;
import com.urbudget.apitransactions.domain.response.Response;
import com.urbudget.apitransactions.domain.user.User;
import com.urbudget.apitransactions.domain.budget.Budget;
import com.urbudget.apitransactions.services.BudgetService;
import com.urbudget.apitransactions.services.TransactionService;
import com.urbudget.apitransactions.services.UserService;
import com.urbudget.apitransactions.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping("api/v1/transaction")

public class TransactionController {

    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    BudgetService budgetService;

    @GetMapping
    public Iterable<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("users")
    public Response<User> createUser(@RequestBody User user) {
        Response<User> response = new Response<>();
        try{
            User responseUser = userService.save(user);;
            response.setData(responseUser);
            response.addMessage("Success");
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("users")
    public Response<User> updateUser(@RequestBody User user) {
        Response<User> response = new Response<>();
        try{
            User responseUser = userService.update(user);
            response.setData(responseUser);
            response.addMessage("Success");
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;

    }

    @PatchMapping("users/{email}")
    public Response<User> patchUser(@PathVariable String email, @RequestBody Patch patch) {
        Response<User> response = new Response<>();
        try{
            User userToUpdate = userService.getById(email);
            switch (patch.getOp()) {
                case "replace" -> userToUpdate.setByKey(patch.getKey(), patch.getValue());
                case "remove" -> userToUpdate.setByKey(patch.getKey(), null);
                case "test" -> {
                    boolean comparisonResult = userToUpdate.getByKey(patch.getKey()).equals(patch.getValue());
                    response.addMessage(String.valueOf(comparisonResult));
                }
                default -> throw new CustomException("Not valid operation");
            }
            if(response.getMessages().isEmpty()){
                User responseUser = userService.update(userToUpdate);
                response.setData(responseUser);
                response.addMessage("Success");
            }
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("user/{email}")
    public Response<User> getUser(@PathVariable String email) {
        Response<User> response = new Response<>();
        try{
            User responseUser = userService.getById(email);
            response.setData(responseUser);
            response.addMessage("Success");
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}/budgets")
    public Response<Iterable<Budget>> getBudgets() {
        Response<Iterable<Budget>> response = new Response<>();
        try{
            Iterable<Budget> responseBudget = budgetService.getAll();
            response.setData(responseBudget);
            response.addMessage("Success");
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;

    }

    @PostMapping("users/{email}/budgets/{budgetId}")
    public Response<Budget> createBudget(@RequestBody Budget budget) {
        Response<Budget> response = new Response<>();
        try{
            Budget responseBudget = budgetService.save(budget);
            response.setData(responseBudget);
            response.addMessage("Success");
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("users/{email}/budgets/{budgetId}")
    public Response<Budget> updateBudget(@RequestBody Budget budget) {
        Response<Budget> response = new Response<>();
        try{
            budgetService.save(budget);
        }catch (Exception e){
        response.addMessage(e.getMessage());
    }
        return response;
    }

    @PatchMapping("users/{email}/budgets/{budgetId}")
    public Response<Budget> patchBudget(@PathVariable String id, @RequestBody Patch patch) {
        Response<Budget> response = new Response<>();
        try{
            Budget budgetToUpdate = budgetService.getOne(id);
            switch (patch.getOp()) {
                case "replace" -> budgetToUpdate.setByKey(patch.getKey(), patch.getValue());
                case "remove" -> budgetToUpdate.setByKey(patch.getKey(), null);
                case "test" -> {
                    boolean comparisonResult = budgetToUpdate.getByKey(patch.getKey()).equals(patch.getValue());
                    response.addMessage(String.valueOf(comparisonResult));
                }
                default -> throw new CustomException("Not valid operation");
            }
            if(response.getMessages().isEmpty()){
                Budget responseBudget = budgetService.save(budgetToUpdate);
                response.setData(responseBudget);
                response.addMessage("Success");
            }
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}/budgets/{budgetId}")
    public Response<Budget> getBudget(@PathVariable String id) {
        Response<Budget> response = new Response<>();
        try{
            budgetService.getOne(id);
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}/budgets/{budgetId}/transactions")
    public Response<Iterable<Transaction>> getTransactionsByBudget(@PathVariable String budgetId) {
        Response<Iterable<Transaction>> response = new Response<>();
        try{
            response.setData(transactionService.getAllByBudget(budgetId));
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("users/{email}/budgets/{budgetId}/transactions")
    public Response<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response<>();
        try{
            response.setData(transactionService.save(transaction));
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Response<Transaction> updateTransaction(@RequestBody Transaction transaction) {
        Response<Transaction> response = new Response<>();
        try{
            response.setData(transactionService.save(transaction));
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;

    }

    @PatchMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Response<Transaction> patchTransaction(@PathVariable String transactionId, @RequestBody Patch patch) {
        Response<Transaction> response = new Response<>();
        try{
            Transaction transactionToUpdate = transactionService.getOne(transactionId);
            switch (patch.getOp()) {
                case "replace" -> transactionToUpdate.setByKey(patch.getKey(), patch.getValue());
                case "remove" -> transactionToUpdate.setByKey(patch.getKey(), null);
                case "test" -> {
                    boolean comparisonResult = transactionToUpdate.getByKey(patch.getKey()).equals(patch.getValue());
                    response.addMessage(String.valueOf(comparisonResult));
                }
                default -> throw new CustomException("Not valid operation");
            }
            if(response.getMessages().isEmpty()){
                response.setData(transactionService.save(transactionToUpdate));
                response.addMessage("Success");
            }
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("user/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Response<Transaction> getTransaction(@PathVariable String transactionId) {
        Response<Transaction> response = new Response<>();
        try{
            response.setData(transactionService.getOne(transactionId));
        }catch (Exception e){
            response.addMessage(e.getMessage());
        }
        return response;
    }
}
