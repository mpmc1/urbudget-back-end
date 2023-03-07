package com.urbudget.apitransactions.controller;

import com.urbudget.apitransactions.domain.Budget.Budget;
import com.urbudget.apitransactions.domain.Transaction.Transaction;
import com.urbudget.apitransactions.domain.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/transaction")

public class TransactionController {

    @GetMapping("users")
    public User getUsers(){
        return new User();
    }
    @PostMapping("users")
    public User createUser(@RequestBody User user){
        return user;
    }
    @PatchMapping("users/{email}")
    public User updateUser(@PathVariable String email){
        return new User();
    }
    @GetMapping("user/{email}")
    public User getUser(@PathVariable String email){
        return new User();
    }
    @GetMapping("users/{email}/budgets")
    public Budget getBudgets(){
        return new Budget();
    }
    @PostMapping("users/{email}/budgets/{budgetId}")
    public Budget createBudget(@RequestBody Budget budget){
        return budget;
    }
    @PatchMapping("users/{email}/budgets/{budgetId}")
    public Budget updateBudget(@PathVariable String id){
        return new Budget();
    }
    @GetMapping("users/{email}/budgets/{budgetId}")
    public Budget getBudget(@PathVariable String id){
        return new Budget();
    }
    @GetMapping("users/{email}/budgets/{budgetId}/transactions")
    public Transaction getTransactionsByBudget(){
        return new Transaction();
    }
    @PostMapping("users/{email}/budgets/{budgetId}/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transaction;
    }
    @PatchMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Transaction updateTransaction(@PathVariable String transactionId){
        return new Transaction();
    }
    @GetMapping("user/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Transaction getTransaction(@PathVariable String transactionId){
        return new Transaction();
    }
}
