package com.urbudget.apitransactions.domain.transaction;

import com.urbudget.apitransactions.domain.budget.Budget;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
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
}
