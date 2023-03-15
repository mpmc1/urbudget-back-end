package com.urbudget.apitransactions.domain;

import com.urbudget.apitransactions.utils.CustomException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Year;

@Entity
@Table
public class Budget {
    @Id
    @Column(name = "id_budget")
    private String id;
    @Column
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
    public void setByKey(String key, String value){
        switch (key) {
            case "year" -> {
                int yearNumber = Integer.parseInt(value);
                if (yearNumber > Year.now().getValue()) setYear(yearNumber);
            }
            case "ammount" -> setAmmount(Float.parseFloat(value));
            case "monthIncomes" -> setMonthIncomes(Float.parseFloat(value));
            case "monthOutcomes" -> setMonthOutcomes(Float.parseFloat(value));
            default -> throw new CustomException(key + " can't be changed");
        }
    }
    public String getByKey(String key){
        return switch (key) {
            case "year" -> String.valueOf(getYear());
            case "ammount" -> String.valueOf(getAmmount());
            case "monthIncomes" -> String.valueOf(getMonthIncomes());
            case "monthOutcomes" -> String.valueOf(getMonthOutcomes());
            default -> throw new CustomException(key + " can't be tested");
        };
    }
}

