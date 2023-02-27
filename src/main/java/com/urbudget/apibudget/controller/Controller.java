package com.urbudget.apibudget.controller;

import com.urbudget.apibudget.dto.BudgetDto;
import com.urbudget.apibudget.dto.TransactionDto;
import com.urbudget.apibudget.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("api/v1/budget")
public class Controller {

    @GetMapping("users")
    public UserDto getUsers(){
        return new UserDto();
    }
    @PostMapping("users")
    public UserDto createUser(@RequestBody UserDto user){
        return user;
    }
    @PatchMapping("users/{email}")
    public UserDto updateUser(@PathVariable String email){
        return new UserDto();
    }
    @GetMapping("user/{email}")
    public UserDto getUser(@PathVariable String email){
        return new UserDto();
    }

    @GetMapping("users/{email}/budgets")
    public BudgetDto getBudgetsByUser(){
        return new BudgetDto();
    }
    @PostMapping("users/{email}/budgets")
    public BudgetDto createBudget(@RequestBody BudgetDto budget){
        return budget;
    }
    @PatchMapping("users/{email}/budgets/{budgetId}")
    public BudgetDto updateBudget(@PathVariable String email, @PathVariable String budgetId){
        return new BudgetDto();
    }
    @GetMapping("user/{email}/budgets/{budgetId}")
    public BudgetDto getBudget(@PathVariable String budgetId){
        return new BudgetDto();
    }

    @GetMapping("users/{email}/budgets/{budgetId}/transactions")
    public TransactionDto getTransactionsByBudget(){
        return new TransactionDto();
    }
    @PostMapping("users/{email}/budgets/{budgetId}/transactions")
    public TransactionDto createTransaction(@RequestBody TransactionDto transaction){
        return transaction;
    }
    @PatchMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public TransactionDto updateTransaction(@PathVariable String transactionId){
        return new TransactionDto();
    }
    @GetMapping("user/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public TransactionDto getTransaction(@PathVariable String transactionId){
        return new TransactionDto();
    }
}
