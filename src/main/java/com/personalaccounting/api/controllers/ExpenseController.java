package com.personalaccounting.api.controllers;

import java.util.List;

import com.personalaccounting.api.domain.Expense;
import com.personalaccounting.api.dtos.ExpenseAddDto;
import com.personalaccounting.api.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.personalaccounting.api.utils.Utils.API_URL;

@RestController
class ExpenseController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final ExpenseService expenseService;

    ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(API_URL + "/users/{userId}/expenses")
    List<Expense> getExpenses(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

    @PostMapping(API_URL + "/users/{userId}/expenses")
    Expense addExpense(@RequestBody Expense newExpense) {
        return expenseService.addExpense(newExpense);
    }

    @PutMapping(API_URL + "/users/{userId}/expenses/{id}")
    Expense replaceExpense(@RequestBody ExpenseAddDto newExpense, @PathVariable Long id, @PathVariable Long userId ) {
        log.info(newExpense.getDate().toString());
        return expenseService.editExpense(newExpense, id, userId);
    }

    @DeleteMapping(API_URL + "/users/{userId}/expenses/{id}")
    void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}