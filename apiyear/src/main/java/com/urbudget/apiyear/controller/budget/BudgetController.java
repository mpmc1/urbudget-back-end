package com.urbudget.apiyear.controller.budget;

import com.urbudget.apiyear.domain.budget.Budget;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/budget")
public class BudgetController {

    @GetMapping("users/{email}/budgets")
    public Budget getBudgetsByUser() {
        return new Budget();
    }

    @PostMapping("users/{email}/budgets")
    public Budget createBudget(@RequestBody Budget budget) {
        return budget;
    }

    @PatchMapping("users/{email}/budgets/{budgetId}")
    public Budget updateBudget(@PathVariable String email, @PathVariable String budgetId) {
        return new Budget();
    }

    @GetMapping("user/{email}/budgets/{budgetId}")
    public Budget getBudget(@PathVariable String budgetId) {
        return new Budget();
    }
}