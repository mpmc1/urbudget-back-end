package com.urbudget.mailsender.mailsender.domain.budget;

import com.urbudget.mailsender.mailsender.domain.Person.Person;

import java.util.UUID;

public class Budget {
    private String id;
    private int year;
    private float ammount;
    private float monthIncomes;
    private float monthOutcomes;
    private Person person;

    public Budget(String id, int year, float ammount, float monthIncomes, float monthOutcomes, Person person) {
        this.id = id;
        this.year = year;
        this.ammount = ammount;
        this.monthIncomes = monthIncomes;
        this.monthOutcomes = monthOutcomes;
        this.person = person;
    }

    public Budget() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
