package com.urbudget.apitransactions.controller;

import com.urbudget.apitransactions.domain.budget.Budget;
import com.urbudget.apitransactions.domain.patch.Patch;
import com.urbudget.apitransactions.domain.response.Response;
import com.urbudget.apitransactions.domain.transaction.Transaction;
import com.urbudget.apitransactions.domain.user.User;
import com.urbudget.apitransactions.services.BudgetService;
import com.urbudget.apitransactions.services.TransactionService;
import com.urbudget.apitransactions.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("users")
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
        User userToUpdate = userService.getById(email);
        Response<User> response = new Response<>();
        try{
            User responseUser = userService.update(userToUpdate);
            response.setData(responseUser);
            response.addMessage("Success");
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
            Budget responseBudget = budgetService.save(budgetToUpdate);
            response.setData(responseBudget);
            response.addMessage("Success");
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
            response.setData(transactionService.save(transactionToUpdate));
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
