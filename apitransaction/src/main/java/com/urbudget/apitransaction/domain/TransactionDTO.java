package com.urbudget.apitransaction.domain;

public class TransactionDTO {
    private float ammount;
    private String description;

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
