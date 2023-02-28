package com.urbudget.apitransactions.controller;

import com.urbudget.apitransactions.dto.TransactionDto;
import com.urbudget.apitransactions.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/transaction")

public class TransactionController {

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
