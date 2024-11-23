package com.alatoo.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.time.LocalDate;

public class EmployeeController {
    @FXML private TextField nameField, hourlyRateField, hoursWorkedField, maxHoursField, annualSalaryField, positionField;
    @FXML private ChoiceBox<String> employeeTypeChoiceBox;
    @FXML private DatePicker hireDatePicker;
    @FXML private TableView<com.alatoo.employeemanagementsystem.EmployeeRecord> employeeTable;
    @FXML private TableColumn<com.alatoo.employeemanagementsystem.EmployeeRecord, Integer> idColumn;
    @FXML private TableColumn<com.alatoo.employeemanagementsystem.EmployeeRecord, String> nameColumn, typeColumn, positionColumn, hireDateColumn;
    @FXML private TableColumn<com.alatoo.employeemanagementsystem.EmployeeRecord, Double> salaryColumn;
    @FXML private Label totalSalaryLabel;

    private final ObservableList<com.alatoo.employeemanagementsystem.EmployeeRecord> employees = FXCollections.observableArrayList();
    private int employeeIdCounter = 1;

    @FXML
    public void initialize() {
        // Initialize ChoiceBox values
        employeeTypeChoiceBox.setItems(FXCollections.observableArrayList("Full-time", "Part-time", "Contractor"));

        // Set up TableView columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        // Bind the employee list to the TableView
        employeeTable.setItems(employees);
    }

    @FXML
    private void addEmployee() {
        String name = nameField.getText();
        String type = employeeTypeChoiceBox.getValue();
        String position = positionField.getText();
        LocalDate hireDate = hireDatePicker.getValue();
        Employee employee;

        if (name.isEmpty() || type == null || position.isEmpty() || hireDate == null) {
            showAlert("Validation Error", "Please fill all fields.");
            return;
        }

        try {
            switch (type) {
                case "Full-time":
                    double annualSalary = Double.parseDouble(annualSalaryField.getText());
                    employee = new com.alatoo.employeemanagementsystem.FullTimeEmployee(name, position, annualSalary);
                    break;
                case "Part-time":
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                    int hoursWorked = Integer.parseInt(hoursWorkedField.getText());
                    employee = new com.alatoo.employeemanagementsystem.PartTimeEmployee(name, position, hourlyRate, hoursWorked);
                    break;
                case "Contractor":
                    double contractorHourlyRate = Double.parseDouble(hourlyRateField.getText());
                    int maxHours = Integer.parseInt(maxHoursField.getText());
                    employee = new com.alatoo.employeemanagementsystem.Contractor(name, position, contractorHourlyRate, maxHours);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown employee type.");
            }

            employees.add(new com.alatoo.employeemanagementsystem.EmployeeRecord(employeeIdCounter++, name, type, position, employee.calculateSalary(), hireDate.toString()));
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Please enter valid numeric values.");
        }
    }

    @FXML
    private void calculateSalaries() {
        double totalSalaries = employees.stream().mapToDouble(com.alatoo.employeemanagementsystem.EmployeeRecord::getSalary).sum();
        totalSalaryLabel.setText("Total Salaries: $" + totalSalaries);
        totalSalaryLabel.setVisible(true);
    }

    @FXML
    private void deleteSelectedEmployee() {
        com.alatoo.employeemanagementsystem.EmployeeRecord selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            employees.remove(selected);
        } else {
            showAlert("Selection Error", "No employee selected.");
        }
    }

    private void clearFields() {
        nameField.clear();
        hourlyRateField.clear();
        hoursWorkedField.clear();
        maxHoursField.clear();
        annualSalaryField.clear();
        positionField.clear();
        hireDatePicker.setValue(null);
        employeeTypeChoiceBox.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateSelectedEmployee(ActionEvent actionEvent) {
    }
}
