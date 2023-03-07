package com.urbudget.apiyear.domain.transaction;

import java.util.Date;

public class Transaction {
    private String id;
    private Date dateOfTransaction;
    private float ammount;
    private String description;

    public Transaction(String id, Date dateOfTransaction, float ammount, String description) {
        this.id = id;
        this.dateOfTransaction = dateOfTransaction;
        this.ammount = ammount;
        this.description = description;
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
}
