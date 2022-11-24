package com.personalaccounting.api.exceptions;

public class ExpenseCategoryNotFoundException extends RuntimeException {

    public ExpenseCategoryNotFoundException(Long id) {
        super("Could not find expenseCategory " + id);
    }
}
