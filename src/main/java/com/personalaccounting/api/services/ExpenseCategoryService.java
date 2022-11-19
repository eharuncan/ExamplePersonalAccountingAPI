package com.personalaccounting.api.services;

import com.personalaccounting.api.domain.ExpenseCategory;
import com.personalaccounting.api.exceptions.ExpenseCategoryNotFoundException;
import com.personalaccounting.api.repositories.ExpenseCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryService {

    private static final Logger log = LoggerFactory.getLogger(ExpenseCategoryService.class);

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final String[] defaultExpenseCategoryNames = new String[]{"Çocuk", "Güvenlik", "Kitap", "Sağlık"};

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    public List<ExpenseCategory> getExpenseCategoriesByUserId(Long userId) {
        return expenseCategoryRepository.findByUserId(userId);
    }

    public ExpenseCategory getExpenseCategoryById(Long id) {
        return expenseCategoryRepository.findById(id)
                .orElseThrow(() -> new ExpenseCategoryNotFoundException(id));
    }

    public ExpenseCategory addExpenseCategory(ExpenseCategory newExpenseCategory) {
        return expenseCategoryRepository.save(newExpenseCategory);
    }

    public ExpenseCategory editExpenseCategory(ExpenseCategory newExpenseCategory, Long id) {
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

    public void deleteExpenseCategory(Long id) {
        expenseCategoryRepository.deleteById(id);
    }

    public void addDefaultExpenseCategoriesOfUser(Long userId) {
        ExpenseCategory newExpenseCategory;
        for (String defaultExpenseCategoryName : defaultExpenseCategoryNames) {
            newExpenseCategory = new ExpenseCategory(userId, defaultExpenseCategoryName);
            addExpenseCategory(newExpenseCategory);
        }
    }

}

