package com.urbudget.apitransactions.domain.Budget;

import com.urbudget.apitransactions.domain.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Budget {
    @Id
    private String id;
    private int year;
    private float ammount;
    private float monthIncomes;
    private float monthOutcomes;
    private User user;

    public Budget() {
        this.id="";
        this.year = 0;
        this.ammount = 0;
        this.monthIncomes = 0;
        this.monthOutcomes = 0;
        this.user=new User();
    }
    public Budget(String id,int year, float ammount, float monthIncomes, float monthOutcomes) {
        this.id = id;
        this.year = year;
        this.ammount = ammount;
        this.monthIncomes = monthIncomes;
        this.monthOutcomes = monthOutcomes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    public float getMonthIncomes() {
        return monthIncomes;
    }

    public void setMonthIncomes(float monthIncomes) {
        this.monthIncomes = monthIncomes;
    }

    public float getMonthOutcomes() {
        return monthOutcomes;
    }

    public void setMonthOutcomes(float monthOutcomes) {
        this.monthOutcomes = monthOutcomes;
    }
}
