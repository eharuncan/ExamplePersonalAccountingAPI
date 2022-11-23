package com.personalaccounting.api.controllers;

import java.util.List;

import com.personalaccounting.api.domain.ExpenseCategory;
import com.personalaccounting.api.services.ExpenseCategoryService;
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
class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    private static final Logger log = LoggerFactory.getLogger(ExpenseCategoryController.class);

    ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping(API_URL + "/users/{userId}/categories")
    List<ExpenseCategory> all(@PathVariable Long userId) {
        return expenseCategoryService.getExpenseCategoriesByUserId(userId);
    }

    @GetMapping(API_URL + "/users/{userId}/categories/{id}")
    ExpenseCategory one(@PathVariable Long id) {
        return expenseCategoryService.getExpenseCategoryById(id);
    }

    @PostMapping(API_URL + "/users/{userId}/categories")
    ExpenseCategory newExpenseCategory(@RequestBody ExpenseCategory newExpenseCategory) {
        return expenseCategoryService.addExpenseCategory(newExpenseCategory);
    }

    @PutMapping(API_URL + "/users/{userId}/categories/{id}")
    ExpenseCategory replaceExpenseCategory(@RequestBody ExpenseCategory newExpenseCategory, @PathVariable Long id) {
        return expenseCategoryService.editExpenseCategory(newExpenseCategory, id);
    }

    @DeleteMapping(API_URL + "/users/{userId}/categories/{id}")
    void deleteExpenseCategory(@PathVariable Long id) {
        log.info("buraya girdi ve id: "+ id.toString());
        expenseCategoryService.deleteExpenseCategory(id);
    }
}