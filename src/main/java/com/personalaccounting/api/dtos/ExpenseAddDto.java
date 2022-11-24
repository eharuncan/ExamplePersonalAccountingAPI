package com.personalaccounting.api.dtos;

import java.util.Date;

public class ExpenseAddDto {

    private Long userId;
    private String name;
    private Double amount;
    private Date date;
    private Long categoryId;

    public ExpenseAddDto(Long userId, String name, Double amount, Date date, Long categoryId) {
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
