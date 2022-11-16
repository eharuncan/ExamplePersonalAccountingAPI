package com.personalaccounting.api.services;

import com.personalaccounting.api.domain.ExpenseCategory;
import com.personalaccounting.api.exceptions.ExpenseCategoryNotFoundException;
import com.personalaccounting.api.repositories.ExpenseCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final String[] defaultExpenseCategoryNames = new String[]{"Çocuk", "Güvenlik", "Kitap", "Sağlık"};

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    public List<ExpenseCategory> getExpenseCategories() {
        return expenseCategoryRepository.findAll();
    }

    public List<ExpenseCategory> getExpenseCategoriesByUserId(Long userId) {
        return expenseCategoryRepository.findAll();
    }

    public ExpenseCategory getExpenseCategoryByUserIdAndExpenseCategoryId(Long userId, Long id) {
        return expenseCategoryRepository.findById(id)
                .orElseThrow(() -> new ExpenseCategoryNotFoundException(id));
    }

    public ExpenseCategory addExpenseCategory(Long userId, ExpenseCategory newExpenseCategory) {
        return expenseCategoryRepository.save(newExpenseCategory);
    }

    public ExpenseCategory editExpenseCategory(ExpenseCategory newExpenseCategory, Long id, Long userId) {
        return expenseCategoryRepository.findById(id)
                .map(expenseCategory -> {
                    expenseCategory.setUserId(newExpenseCategory.getUserId());
                    expenseCategory.setName(newExpenseCategory.getName());
                    return expenseCategoryRepository.save(expenseCategory);
                })
                .orElseGet(() -> {
                    newExpenseCategory.setId(id);
                    return expenseCategoryRepository.save(newExpenseCategory);
                });
    }

    public void deleteExpenseCategory(Long userId, Long id) {
        expenseCategoryRepository.deleteById(id);
    }

    public boolean addDefaultExpenseCategories(Long userId) {
        ExpenseCategory expenseCategory;
        for (String defaultExpenseCategoryName : defaultExpenseCategoryNames) {
            expenseCategory = new ExpenseCategory(userId, defaultExpenseCategoryName);
            addExpenseCategory(userId, expenseCategory);
        }
        return true;
    }

}

