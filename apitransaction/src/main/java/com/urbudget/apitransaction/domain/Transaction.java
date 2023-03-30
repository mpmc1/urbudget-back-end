package com.urbudget.apitransaction.domain;

import com.urbudget.apitransaction.util.CustomException;
import jakarta.persistence.*;


import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "id_transaction")
    private String id;
    @Column(name = "dateoftransaction")
    private Date dateOfTransaction;
    @Column(name = "ammount")
    private float ammount;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "budget_id_budget")
    private Budget budget;

    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.dateOfTransaction = new Date();
        this.ammount = 0;
        this.description = "";
        this.budget = new Budget();
    }

    public Transaction(float ammount, Budget budget, String description) {
        this.id = UUID.randomUUID().toString();
        this.dateOfTransaction = new Date();
        this.ammount = ammount;
        this.budget = budget;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void setByKey(String key, String value) {
        if(key.equals("description")){
                setDescription(value);
        } else{
            throw new CustomException(key + " can't be changed");
        }
    }
    public String getByKey(String key){
        return switch (key) {
            case "dateOfTransaction" -> String.valueOf(getDateOfTransaction());
            case "ammount" -> String.valueOf(getAmmount());
            case "description" -> String.valueOf(getDescription());
            default -> throw new CustomException(key + " can't be tested");
        };
    }
}

