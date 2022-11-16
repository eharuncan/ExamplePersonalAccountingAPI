package com.personalaccounting.api;

import com.personalaccounting.api.services.ExpenseCategoryService;
import com.personalaccounting.api.services.ExpenseService;
import com.personalaccounting.api.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static ExpenseCategoryService expenseCategoryService;
	public static UserService userService;
	public static ExpenseService expenseService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
