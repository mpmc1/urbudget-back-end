package com.urbudget.apitransactions.domain.User;

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
}