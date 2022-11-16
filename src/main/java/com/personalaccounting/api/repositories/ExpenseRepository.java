package com.personalaccounting.api.repositories;

import com.personalaccounting.api.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    Expense findByIdAndUserId(Long id, Long userId);
}
