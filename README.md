# Employee Management System

This JavaFX application manages a list of employees and their salaries. It demonstrates the use of abstract classes, polymorphism, and JavaFX for GUI development.

---

## Features

- **Employee Management**:
    - Add employees of different types: Full-time, Part-time, and Contractor.
    - Calculate and display monthly salaries based on employee type.
    - Update or remove employees from the list.

- **Dynamic Salary Calculation**:
    - Full-time employees: Salary is based on an annual fixed salary.
    - Part-time employees: Salary is calculated based on hourly rate and hours worked.
    - Contractors: Salary is calculated based on hourly rate and a predefined maximum number of hours.

- **User-Friendly Interface**:
    - Intuitive input forms for adding employees.
    - Table view for displaying employee details (name, type, salary, position, and hire date).
    - Buttons for calculating total salaries and managing the employee list.

---

## Technologies Used

- **JavaFX**: For building the GUI.
- **Java**: Core logic and object-oriented programming.
- **FXML**: For defining the UI layout.
- **Maven**: For dependency management.

---

## Requirements

- Java 17 or higher
- JavaFX 17 or higher
- Maven

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/teyvas/employee-management-system.git
   cd employee-management-system
    ```
2. Build and run the application:
   ```bash
   mvn clean javafx:run
    ```
3. Use the GUI to manage employees:
   - Fill in the required fields for employee details.
   - Click "Add an employee" to add the employee to the list.
   - View or edit employee details in the table.
   - Click "Calculate the salaries" to calculate and display total salaries.
   - Select an employee and click "Remove an employee" to delete the record.
     Application Structure
     Classes

   ## Abstract Class: Employee
   Base class with the calculateSalary method to be implemented by subclasses.

- **Subclasses**:
  - FullTimeEmployee: Implements salary as an annual fixed salary.
  - PartTimeEmployee: Implements salary based on hourly rate and hours worked.
  - Contractor: Implements salary based on hourly rate and maximum hours.

  - Controller: EmployeeController: Manages the interaction between the user interface and the backend logic.

- **Utility Class**:
  - EmployeeRecord: Represents data to be displayed in the table.

- **FXML**

    - Defines the user interface with components like:
        - Text fields for input
        - Choice boxes for employee types
        - Table view for displaying employee details
    