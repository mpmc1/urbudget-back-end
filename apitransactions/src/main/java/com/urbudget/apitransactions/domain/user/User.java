package com.urbudget.apitransactions.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User{

    private String name;
    private  String lastname;
    @Id
    private String email;

    public User() {
        this.name = "";
        this.lastname = "";
        this.email = "";
    }

    public User(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}