package com.budgettracker.repository;

import com.budgettracker.model.Budget;
import com.budgettracker.model.Expense;
import jakarta.annotation.PostConstruct;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// Handles all database operations using JDBC
// Demonstrates: JDBC with JdbcTemplate, RowMapper, and try-catch exception handling
@Repository
public class DatabaseRepository {

    private final JdbcTemplate jdbc;

    public DatabaseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Creates the tables when the app starts (runs once automatically)
    @PostConstruct
    public void initSchema() {
        try {
            jdbc.execute(
                "CREATE TABLE IF NOT EXISTS budget (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  type TEXT NOT NULL," +
                "  amount REAL NOT NULL" +
                ")"
            );
            jdbc.execute(
                "CREATE TABLE IF NOT EXISTS expenses (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  amount REAL NOT NULL," +
                "  category TEXT NOT NULL," +
                "  date TEXT NOT NULL," +
                "  note TEXT" +
                ")"
            );
        } catch (Exception e) {
            throw new RuntimeException("Could not create database tables: " + e.getMessage());
        }
    }

    // RowMapper converts a database row into a Budget object
    private final RowMapper<Budget> budgetRowMapper = (rs, rowNum) -> {
        Budget b = new Budget();
        b.setId(rs.getInt("id"));
        b.setType(rs.getString("type"));
        b.setAmount(rs.getDouble("amount"));
        return b;
    };

    // RowMapper converts a database row into an Expense object
    private final RowMapper<Expense> expenseRowMapper = (rs, rowNum) -> {
        Expense e = new Expense();
        e.setId(rs.getInt("id"));
        e.setAmount(rs.getDouble("amount"));
        e.setCategory(rs.getString("category"));
        e.setDate(rs.getString("date"));
        e.setNote(rs.getString("note"));
        return e;
    };

    public Budget getBudget() {
        try {
            return jdbc.queryForObject("SELECT * FROM budget LIMIT 1", budgetRowMapper);
        } catch (EmptyResultDataAccessException e) {
            // No budget saved yet
            return null;
        }
    }

    public Budget saveBudget(Budget budget) {
        try {
            jdbc.update("DELETE FROM budget");
            jdbc.update("INSERT INTO budget (type, amount) VALUES (?, ?)",
                    budget.getType(), budget.getAmount());
            Integer id = jdbc.queryForObject("SELECT last_insert_rowid()", Integer.class);
            budget.setId(id != null ? id : 0);
            return budget;
        } catch (Exception e) {
            throw new RuntimeException("Could not save budget: " + e.getMessage());
        }
    }

    public List<Expense> getAllExpenses() {
        try {
            return jdbc.query(
                "SELECT * FROM expenses ORDER BY date DESC, id DESC",
                expenseRowMapper
            );
        } catch (Exception e) {
            throw new RuntimeException("Could not get expenses: " + e.getMessage());
        }
    }

    public Expense saveExpense(Expense expense) {
        try {
            jdbc.update(
                "INSERT INTO expenses (amount, category, date, note) VALUES (?, ?, ?, ?)",
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate(),
                expense.getNote()
            );
            Integer id = jdbc.queryForObject("SELECT last_insert_rowid()", Integer.class);
            expense.setId(id != null ? id : 0);
            return expense;
        } catch (Exception e) {
            throw new RuntimeException("Could not save expense: " + e.getMessage());
        }
    }

    public boolean deleteExpense(int id) {
        try {
            int rows = jdbc.update("DELETE FROM expenses WHERE id = ?", id);
            return rows > 0;
        } catch (Exception e) {
            throw new RuntimeException("Could not delete expense: " + e.getMessage());
        }
    }
}
