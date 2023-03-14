package com.urbudget.apitransactions.domain.budget;

import com.urbudget.apitransactions.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity()
public class Budget {
    @Id
    @Column(name = "id_budget")
    private String id;
    private int year;
    @Column(name = "value")
    private float ammount;
    @Column(name = "monthincomes")
    private float monthIncomes;
    @Column(name = "monthoutcomes")
    private float monthOutcomes;
    @Column(name = "user_email")
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
