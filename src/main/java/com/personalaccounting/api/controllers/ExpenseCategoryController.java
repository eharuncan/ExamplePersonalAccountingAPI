package com.personalaccounting.api.controllers;

import java.util.List;

import com.personalaccounting.api.domain.ExpenseCategory;
import com.personalaccounting.api.services.ExpenseCategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.personalaccounting.api.utils.Utils.apiURL;

@RestController
class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping(apiURL + "/users/{userId}/categories")
    List<ExpenseCategory> all() {
        return expenseCategoryService.getExpenseCategories();
    }

    @GetMapping(apiURL + "/users/{userId}/categories/{id}")
    ExpenseCategory one(@PathVariable Long userId, @PathVariable Long id) {
        return expenseCategoryService.getExpenseCategoryByUserIdAndExpenseCategoryId(userId, id);
    }

    @PostMapping(apiURL + "/users/{userId}/categories")
    ExpenseCategory newExpenseCategory(@PathVariable Long userId, @RequestBody ExpenseCategory newExpenseCategory) {
        return expenseCategoryService.addExpenseCategory(userId, newExpenseCategory);
    }

    @PutMapping(apiURL + "/users/{userId}/categories/{id}")
    ExpenseCategory replaceExpenseCategory(@RequestBody ExpenseCategory newExpenseCategory, @PathVariable Long id, @PathVariable Long userId) {
        return expenseCategoryService.editExpenseCategory(newExpenseCategory, id, userId);
    }

    @DeleteMapping(apiURL + "/users/{userId}/categories/{id}")
    void deleteExpenseCategory(@PathVariable Long userId, @PathVariable Long id) {
        expenseCategoryService.deleteExpenseCategory(userId, id);
    }
}