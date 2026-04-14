package com.budgettracker.controller;

import com.budgettracker.model.Budget;
import com.budgettracker.model.Expense;
import com.budgettracker.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// REST controller - handles HTTP requests from the frontend
// Demonstrates: exception handling (try-catch, returning proper HTTP responses)
@RestController
@RequestMapping("/api")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // GET /api/budget
    @GetMapping("/budget")
    public ResponseEntity<Budget> getBudget() {
        try {
            return ResponseEntity.ok(budgetService.getBudget());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // POST /api/budget
    @PostMapping("/budget")
    public ResponseEntity<?> setBudget(@RequestBody Budget budget) {
        try {
            Budget saved = budgetService.setBudget(budget);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/expenses
    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getExpenses() {
        try {
            return ResponseEntity.ok(budgetService.getAllExpenses());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // POST /api/expenses
    @PostMapping("/expenses")
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
        try {
            Expense saved = budgetService.addExpense(expense);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE /api/expenses/{id}
    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable int id) {
        try {
            boolean deleted = budgetService.deleteExpense(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/summary - used by the dashboard and bar chart
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        try {
            Map<String, Object> summary = new HashMap<>();
            summary.put("budget", budgetService.getBudget());
            summary.put("totalSpent", budgetService.getTotalSpent());
            summary.put("remainingBalance", budgetService.getRemainingBalance());
            summary.put("categoryBreakdown", budgetService.getCategoryBreakdown());
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }
}
