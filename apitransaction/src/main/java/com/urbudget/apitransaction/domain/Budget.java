package com.urbudget.apitransaction.domain;

import com.urbudget.apitransaction.util.CustomException;
import jakarta.persistence.*;

import java.time.Year;
import java.util.UUID;

@Entity
@Table(name="budget")
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
    @ManyToOne
    @JoinColumn(name = "user_email")
    private Person person;


    public Budget() {
        this.id = UUID.randomUUID().toString();
        this.year = 0;
        this.ammount = 0;
        this.monthIncomes = 0;
        this.monthOutcomes = 0;
        this.person =new Person();
    }
    public Budget(int year, float ammount, float monthIncomes, float monthOutcomes) {
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

    public Person getUser() {
        return person;
    }

    public void setUser(Person person) {
        this.person = person;
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
