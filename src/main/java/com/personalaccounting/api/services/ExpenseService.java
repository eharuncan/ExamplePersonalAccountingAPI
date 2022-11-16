package com.personalaccounting.api.services;

import com.personalaccounting.api.domain.Expense;
import com.personalaccounting.api.exceptions.ExpenseNotFoundException;
import com.personalaccounting.api.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Expense addExpense(Expense newExpense) {
        return expenseRepository.save(newExpense);
    }

    public Expense editExpense(Expense newExpense, Long id, Long userId) {
        Expense foundExpense = expenseRepository.findByIdAndUserId(id, userId);
        foundExpense.setUserId(newExpense.getUserId());
        foundExpense.setName(newExpense.getName());
        foundExpense.setAmount(newExpense.getAmount());
        foundExpense.setDate(newExpense.getDate());
        foundExpense.setCategoryId(newExpense.getCategoryId());
        expenseRepository.save(foundExpense);
        return foundExpense;
    }

    public void deleteExpense(Long userId, Long id) {
        expenseRepository.deleteById(id);
    }

    public Double getSumOfUserExpensesOfDay(Long userId, Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        List<Expense> currentUsersExpenseList = getExpensesByUserId(userId);
        List<Expense> resultList = currentUsersExpenseList.stream()
                .filter(expense ->
                        Objects.equals(expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(), year)
                                && Objects.equals(expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue(), month)
                                && Objects.equals(expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth(), day)
                )
                .collect(Collectors.toList());
        return resultList.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public Double getSumOfUserExpensesOfMonth(Long userId, java.util.Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();

        List<Expense> currentUsersExpenseList = getExpensesByUserId(userId);
        List<Expense> resultList = currentUsersExpenseList.stream()
                .filter(expense ->
                        Objects.equals(expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(), year)
                                && Objects.equals(expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue(), month)
                )
                .collect(Collectors.toList());
        return resultList.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public Double getSumOfUserExpensesOfYear(Long userId, java.util.Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();

        List<Expense> currentUsersExpenseList = getExpensesByUserId(userId);
        List<Expense> resultList = currentUsersExpenseList.stream()
                .filter(expense ->
                        Objects.equals(expense.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(), year)
                )
                .collect(Collectors.toList());
        return resultList.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
