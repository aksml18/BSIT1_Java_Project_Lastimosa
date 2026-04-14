package com.budgettracker.service;

import com.budgettracker.model.Budget;
import com.budgettracker.model.Expense;
import com.budgettracker.repository.DatabaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implements the BudgetService interface
// Demonstrates: interface implementation, ArrayList, and HashMap
@Service
public class BudgetServiceImpl implements BudgetService {

    private final DatabaseRepository repository;

    public BudgetServiceImpl(DatabaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Budget getBudget() {
        Budget budget = repository.getBudget();

        // If no budget is saved yet, return a default one
        if (budget == null) {
            Budget defaultBudget = new Budget();
            defaultBudget.setType("monthly");
            defaultBudget.setAmount(0.0);
            return defaultBudget;
        }

        return budget;
    }

    @Override
    public Budget setBudget(Budget budget) {
        // Validate input before saving
        if (budget.getAmount() <= 0) {
            throw new IllegalArgumentException("Budget amount must be greater than zero.");
        }
        if (!budget.getType().equals("weekly") && !budget.getType().equals("monthly")) {
            throw new IllegalArgumentException("Budget type must be weekly or monthly.");
        }
        return repository.saveBudget(budget);
    }

    // Returns all expenses using ArrayList (demonstrates Collections)
    @Override
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(repository.getAllExpenses());
    }

    @Override
    public Expense addExpense(Expense expense) {
        // Validate before saving
        if (expense.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (expense.getCategory() == null || expense.getCategory().isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }
        if (expense.getDate() == null || expense.getDate().isBlank()) {
            throw new IllegalArgumentException("Date cannot be empty.");
        }
        return repository.saveExpense(expense);
    }

    @Override
    public boolean deleteExpense(int id) {
        return repository.deleteExpense(id);
    }

    // Groups expenses by category using HashMap (demonstrates Collections)
    @Override
    public Map<String, Double> getCategoryBreakdown() {
        ArrayList<Expense> expenses = new ArrayList<>(repository.getAllExpenses());
        HashMap<String, Double> breakdown = new HashMap<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();

            // If category already exists, add to it; otherwise start fresh
            if (breakdown.containsKey(category)) {
                breakdown.put(category, breakdown.get(category) + expense.getAmount());
            } else {
                breakdown.put(category, expense.getAmount());
            }
        }

        return breakdown;
    }

    @Override
    public double getTotalSpent() {
        double total = 0.0;
        for (Expense expense : getAllExpenses()) {
            total += expense.getAmount();
        }
        return total;
    }

    @Override
    public double getRemainingBalance() {
        Budget budget = repository.getBudget();
        if (budget == null || budget.getAmount() == 0) {
            return 0.0;
        }
        return budget.getAmount() - getTotalSpent();
    }
}
