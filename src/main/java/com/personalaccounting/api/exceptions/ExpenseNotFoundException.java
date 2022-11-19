package com.personalaccounting.api.exceptions;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(Long id) {
        super("Could not find expense " + id);
    }
}
