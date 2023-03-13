package com.urbudget.apitransactions.controller;

import com.urbudget.apitransactions.domain.Budget.Budget;
import com.urbudget.apitransactions.domain.Patch.Patch;
import com.urbudget.apitransactions.domain.Transaction.Transaction;
import com.urbudget.apitransactions.domain.User.User;
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
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("users")
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @PatchMapping("users/{email}")
    public User patchUser(@PathVariable String email, @RequestBody Patch patch) {
        Optional<User> userToUpdate = userService.getById(email);
        return userToUpdate.map(user -> userService.update(user)).orElse(null);
    }

    @GetMapping("user/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getById(email).orElse(null);
    }

    @GetMapping("users/{email}/budgets")
    public Iterable<Budget> getBudgets() {
        return budgetService.getAll();
    }

    @PostMapping("users/{email}/budgets/{budgetId}")
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.create(budget);
    }

    @PutMapping("users/{email}/budgets/{budgetId}")
    public Budget updateBudget(@RequestBody Budget budget) {
        return budgetService.update(budget);
    }

    @PatchMapping("users/{email}/budgets/{budgetId}")
    public Budget patchBudget(@PathVariable String id, @RequestBody Patch patch) {
        Optional<Budget> budgetToUpdate = budgetService.getOne(id);
        return budgetToUpdate.map(budget -> budgetService.update(budget)).orElse(null);
    }

    @GetMapping("users/{email}/budgets/{budgetId}")
    public Budget getBudget(@PathVariable String id) {
        return budgetService.getOne(id).orElse(null);
    }

    @GetMapping("users/{email}/budgets/{budgetId}/transactions")
    public Iterable<Transaction> getTransactionsByBudget(@PathVariable String budgetId) {
        return transactionService.getAllByBudget(budgetId);
    }

    @PostMapping("users/{email}/budgets/{budgetId}/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @PutMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.update(transaction);
    }

    @PatchMapping("users/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Transaction patchTransaction(@PathVariable String transactionId, @RequestBody Patch patch) {
        Optional<Transaction> transactionToUpdate = transactionService.getOne(transactionId);
        return transactionToUpdate.map(transaction -> transactionService.update(transaction)).orElse(null);
    }

    @GetMapping("user/{email}/budgets/{budgetId}/transactions/{transactionId}")
    public Transaction getTransaction(@PathVariable String transactionId) {
        return transactionService.getOne(transactionId).orElse(null);
    }
}
