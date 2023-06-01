package com.urbudget.mailsender.mailsender.domain.transaction;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.urbudget.mailsender.mailsender.domain.budget.Budget;
import com.urbudget.mailsender.mailsender.utils.CustomException;

import java.util.Date;


public class Transaction {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateOfTransaction;
    private float ammount;
    private String description;
    private Budget budget;

    public Transaction(String id, Date dateOfTransaction, float ammount, String description, Budget budget) {
        this.id = id;
        this.dateOfTransaction = dateOfTransaction;
        this.ammount = ammount;
        this.description = description;
        this.budget = budget;
    }

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}


