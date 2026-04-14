package com.budgettracker.service;

import com.budgettracker.model.Budget;
import com.budgettracker.model.Expense;

import java.util.List;
import java.util.Map;

// Interface that defines what the budget service can do
// Demonstrates interfaces: only method signatures, no implementation here
public interface BudgetService {

    Budget getBudget();
    Budget setBudget(Budget budget);

    List<Expense> getAllExpenses();
    Expense addExpense(Expense expense);
    boolean deleteExpense(int id);

    Map<String, Double> getCategoryBreakdown();
    double getTotalSpent();
    double getRemainingBalance();
}
