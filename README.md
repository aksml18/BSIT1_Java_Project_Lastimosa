# Student Budget Tracker

A simple web app that helps students track their weekly or monthly budget.
You can log expenses, see how much you have left, and view a breakdown by category.

Built with Java Spring Boot for the backend, plain HTML/CSS/JavaScript for the frontend, and SQLite for the database.

---

## How to Run

### Requirements

Before running, make sure you have **Java 17** and **Maven** installed.

**Check if they're installed:**
```
java -version
mvn -version
```

---

### Installing Java (JDK 17)

#### Windows
1. Download the installer from [https://adoptium.net](https://adoptium.net)
2. Run the installer — on the **Custom Setup** screen, enable **"Add to PATH"** and **"Set JAVA_HOME"**
3. If you skipped that, add it manually:
   - Press **Win + S** → search **"Environment Variables"**
   - Click **Environment Variables...**
   - Under **System variables**, click **New**:
     - Variable name: `JAVA_HOME`
     - Variable value: your JDK folder, e.g. `C:\Program Files\Eclipse Adoptium\jdk-17.0.x`
   - Find `Path` → **Edit** → **New** → type `%JAVA_HOME%\bin`
   - Click OK on everything, then open a new terminal
4. Verify: `java -version`

#### macOS
```bash
brew install openjdk@17
# Add this line to ~/.zshrc
export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"
source ~/.zshrc
java -version
```

#### Linux
```bash
sudo apt update && sudo apt install openjdk-17-jdk
java -version
```

---

### Installing Maven

#### Windows
1. Download the **Binary zip** from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
2. Extract it, e.g. to `C:\Program Files\Apache\maven`
3. Add to PATH:
   - Environment Variables → **New** system variable:
     - Name: `MAVEN_HOME`
     - Value: `C:\Program Files\Apache\maven`
   - Edit `Path` → **New** → `%MAVEN_HOME%\bin`
   - Click OK, open a new terminal
4. Verify: `mvn -version`

   > **Tip:** Not sure of the exact folder name? Run `dir "C:\Program Files" | findstr /i maven` in your terminal.

#### macOS
```bash
brew install maven
mvn -version
```

#### Linux
```bash
sudo apt install maven
mvn -version
```

---

### Starting the App

```bash
# Go to the project folder
cd BSIT1_Java_Project_Lastimosa

# Run the app
mvn spring-boot:run
```

Then open your browser and go to:
```
http://localhost:8080
```

The database file (`budget_tracker.db`) is created automatically — no setup needed.

To stop the app press **Ctrl + C**.

---

## Features

- Set a weekly or monthly budget
- Log expenses with amount, category, date, and note
- See remaining balance, total spent, and budget in real time
- Category breakdown with a bar chart
- Expense history table with delete

---

## Java Lessons Used

| Lesson | Where | What it does |
|---|---|---|
| Lesson 1.1. if Statement | `BudgetServiceImpl.java` | Used to check for null values or basic validation (e.g., checking if a budget exists before trying to update it). |
| Lesson 2.3. For Loop| `BudgetServiceImpl.java` `getTotalSpent() method` | Specifically a for-each loop used to iterate through the list of expenses to calculate a sum. |
| Lesson 3.2. One Dimensional Arrays | `BudgetServiceImpl.java` `getCategoryBreakdown()`   |  The ArrayList<Expense> acts as a dynamic 1D array of objects. |
| Lesson 4.3. No Parameters but With Return Values | `BudgetServiceImpl.java` `getBudget() and getTotalSpent()` | Takes no input and returns data to the caller. |
| Lesson 4.5. With Parameters and Return Values | `DatabaseRepository.java` `addExpense(Expense expense)` |  Takes an object as input, processes/saves it, and returns the saved object. |

---

## Project Structure

```
src/main/java/com/budgettracker/
├── BudgetTrackerApplication.java   - starts the app
├── model/
│   ├── Expense.java                - expense data class
│   └── Budget.java                 - budget data class
├── service/
│   ├── BudgetService.java          - interface
│   └── BudgetServiceImpl.java      - implementation
├── repository/
│   └── DatabaseRepository.java     - database operations
└── controller/
    └── BudgetController.java       - API endpoints

src/main/resources/
├── application.properties          - app config
└── static/index.html               - frontend (HTML + CSS + JS)
```

---

## Team Members

| Name | Role |
|---|---|
| *(Lastimosa, Angel Kael)* | Backend / Spring Boot / HTML-CSS-JS |
| *(Palma, Nikki Jvence)* | Frontend /  Documentation / Testing  |
| *(Torres, Julianna Michaela)* | Database / Documentation |

---

*BSIT1 Java Project — Lastimosa Group*
