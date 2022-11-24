package com.personalaccounting.api.repositories;

import com.personalaccounting.api.domain.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

}
