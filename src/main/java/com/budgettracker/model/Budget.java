package com.budgettracker.model;

// Represents the student's budget (weekly or monthly)
// Demonstrates OOP: encapsulation with private fields
public class Budget {

    private int id;
    private String type;   // "weekly" or "monthly"
    private double amount;

    public Budget() {}

    public Budget(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
