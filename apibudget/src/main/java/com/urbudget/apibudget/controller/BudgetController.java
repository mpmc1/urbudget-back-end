package com.urbudget.apibudget.controller;

import com.urbudget.apibudget.dto.BudgetDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping("api/v1/budget")
public class BudgetController {

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

}
