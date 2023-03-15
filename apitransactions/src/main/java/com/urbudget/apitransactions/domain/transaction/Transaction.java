package com.urbudget.apitransactions.domain.transaction;

import com.urbudget.apitransactions.domain.budget.Budget;
import com.urbudget.apitransactions.utils.CustomException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @Column(name = "id_transaction")
    private String id;
    @Column(name = "dateoftransaction")
    private Date dateOfTransaction;
    @Column(name = "ammount")
    private float ammount;
    @Column(name = "budget_id_budget")
    private Budget budget;

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
        if(key.equals("dateOfTransaction")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
            try {
                setDateOfTransaction(formatter.parse(value));
            }catch (ParseException e){
                throw  new CustomException(e.getMessage());
            }
        } else if (key.equals("ammount")) {
            setAmmount(Float.parseFloat(value));
        }else{
            throw new CustomException(key + " can't be changed");
        }
    }
    public String getByKey(String key){
        return switch (key) {
            case "dateOfTransaction" -> String.valueOf(getDateOfTransaction());
            case "ammount" -> String.valueOf(getAmmount());
            default -> throw new CustomException(key + " can't be tested");
        };
    }
}
