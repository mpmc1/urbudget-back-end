package com.urbudget.apitransactions.domain.user;

import com.urbudget.apitransactions.utils.CustomException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
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
    public void setByKey(String key, String value){
        if(key.equals("name")){
            setName(value);
        } else if (key.equals("lastname")) {
            setLastname(value);
        }else{
            throw new CustomException(key + " can't be changed");
        }
    }
    public String getByKey(String key){
        return switch (key) {
            case "name" -> String.valueOf(getName());
            case "lastname" -> String.valueOf(getLastname());
            default -> throw new CustomException(key + " can't be tested");
        };
    }
}