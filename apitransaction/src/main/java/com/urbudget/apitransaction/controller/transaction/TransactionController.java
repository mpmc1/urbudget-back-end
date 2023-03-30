package com.urbudget.apitransaction.controller.transaction;

import com.urbudget.apitransaction.domain.budget.Budget;
import com.urbudget.apitransaction.domain.patch.Patch;
import com.urbudget.apitransaction.domain.response.Response;
import com.urbudget.apitransaction.domain.transaction.Transaction;
import com.urbudget.apitransaction.domain.transactiondto.TransactionDTO;
import com.urbudget.apitransaction.service.budget.BudgetService;
import com.urbudget.apitransaction.service.transaction.TransactionService;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    BudgetService budgetService;

    private static final String NOT_VALID="Not valid operation";
    private static final String SUCCESS="Success";
    private static final String REMOVE="remove";
    private static final String REPLACE="replace";
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
