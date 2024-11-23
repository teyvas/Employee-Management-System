package com.alatoo.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeController {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> typeColumn;
    @FXML
    private TableColumn<Employee, String> salaryColumn;
    @FXML
    private TextField nameField;
    @FXML
    private ChoiceBox<String> typeChoice;
    @FXML
    private TextField hourlyRateField;
    @FXML
    private TextField hoursWorkedField;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up table columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        salaryColumn.setCellValueFactory(cellData ->
                cellData.getValue().salaryProperty().asString("%.2f"));

        employeeTable.setItems(employeeList);

        // Set default type choice
        typeChoice.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleAddEmployee() {
        try {
            String name = nameField.getText().trim();
            String type = typeChoice.getValue();
            if (name.isEmpty() || type == null) {
                showAlert("Validation Error", "Name and Type must be provided.");
                return;
            }
            double hourlyRate = parseDoubleField(hourlyRateField, "Hourly Rate");
            double hoursWorked = parseDoubleField(hoursWorkedField, "Hours Worked");

            Employee employee;
            switch (type) {
                case "Full-time" -> employee = new FullTimeEmployee(name, hourlyRate); // Treat hourlyRate as salary for simplicity.
                case "Part-time" -> employee = new PartTimeEmployee(name, hourlyRate, hoursWorked);
                case "Contractor" -> employee = new Contractor(name, hourlyRate, hoursWorked);
                default -> throw new IllegalArgumentException("Invalid employee type.");
            }

            employeeList.add(employee);
            clearFields();
        } catch (IllegalArgumentException e) {
            showAlert("Validation Error", e.getMessage());
        }
    }

    private double parseDoubleField(TextField field, String fieldName) {
        try {
            return Double.parseDouble(field.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }


    @FXML
    private void handleCalculateSalaries() {
        for (Employee employee : employeeList) {
            employee.calculateSalary();
        }
        employeeTable.refresh();
    }

    @FXML
    private void handleRemoveEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeeList.remove(selectedEmployee);
        } else {
            showAlert("No Selection", "Please select an employee to remove.");
        }
    }

    private void clearFields() {
        nameField.clear();
        hourlyRateField.clear();
        hoursWorkedField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
