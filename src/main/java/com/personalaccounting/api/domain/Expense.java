package com.personalaccounting.api.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.personalaccounting.api.enums.UserTypes;

@Entity
@Table(name = "expenses")
public class Expense {

    private @Id @GeneratedValue Long id;
    private Long userId;
    private String name;
    private Double amount;
    private Date date;
    private Long categoryId;

    public Expense() {}

    public Expense(Long userId, String name, Double amount, Date date, Long categoryId) {
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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Expense))
            return false;
        Expense expense = (Expense) o;
        return Objects.equals(this.id, expense.id)
                && Objects.equals(this.userId, expense.userId)
                && Objects.equals(this.name, expense.name)
                && Objects.equals(this.amount, expense.amount)
                && Objects.equals(this.date, expense.date)
                && Objects.equals(this.categoryId, expense.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userId, this.name, this.amount, this.date, this.categoryId);
    }

    @Override
    public String toString() {
        return "Expense{" + "id=" + this.id + ", userId='" + this.userId + '\'' + ", name='" + this.name + '\'' + ", amount='" + this.amount + '\'' + ", date='" + this.date + '\'' + ", categoryId='" + this.categoryId + '\'' + '}';
    }
}
