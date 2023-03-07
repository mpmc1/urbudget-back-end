package com.urbudget.apitransactions.domain.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Transaction {
    @Id
    private String id;
    private Date dateOfTransaction;
    private float ammount;
    private String description;
}
