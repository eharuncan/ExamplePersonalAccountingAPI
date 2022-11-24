package com.personalaccounting.api.controllers;

import java.util.List;

import com.personalaccounting.api.domain.Expense;
import com.personalaccounting.api.services.ExpenseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.personalaccounting.api.utils.Utils.apiURL;

@RestController
class ExpenseController {

    private final ExpenseService expenseService;

    ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(apiURL + "/users/{userId}/expenses")
    List<Expense> all(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

    @PostMapping(apiURL + "/users/{userId}/expenses")
    Expense newExpense(@RequestBody Expense newExpense) {
        return expenseService.addExpense(newExpense);
    }

    @PutMapping(apiURL + "/users/{userId}/expenses/{id}")
    Expense replaceExpense(@RequestBody Expense newExpense, @PathVariable Long id, @PathVariable Long userId ) {
        return expenseService.editExpense(newExpense, id, userId);
    }

    @DeleteMapping(apiURL + "/users/{userId}/expenses/{id}")
    void deleteExpense(@PathVariable Long userId, @PathVariable Long id) {
        expenseService.deleteExpense(userId, id);
    }
}