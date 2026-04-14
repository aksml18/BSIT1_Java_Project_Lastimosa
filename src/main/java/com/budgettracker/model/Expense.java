package com.budgettracker.model;

// Represents a single expense entry logged by the student
// Demonstrates OOP: private fields with getters and setters (encapsulation)
public class Expense {

    private int id;
    private double amount;
    private String category;
    private String date;
    private String note;

    public Expense() {}

    public Expense(double amount, String category, String date, String note) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
