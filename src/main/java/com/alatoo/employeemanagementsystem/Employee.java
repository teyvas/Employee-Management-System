package com.alatoo.employeemanagementsystem;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Employee {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final DoubleProperty salary = new SimpleDoubleProperty();

    public Employee(String name, String type) {
        this.name.set(name);
        this.type.set(type);
    }

    public abstract void calculateSalary();

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getType() { return type.get(); }
    public StringProperty typeProperty() { return type; }

    public double getSalary() { return salary.get(); }
    public DoubleProperty salaryProperty() { return salary; }

    protected void setSalary(double salary) { this.salary.set(salary); }
}

class FullTimeEmployee extends Employee {
    private final double annualSalary;

    public FullTimeEmployee(String name, double annualSalary) {
        super(name, "Full-time");
        this.annualSalary = annualSalary;
    }

    @Override
    public void calculateSalary() {
        setSalary(annualSalary);
    }
}

class PartTimeEmployee extends Employee {
    private final double hourlyRate;
    private final double hoursWorked;

    public PartTimeEmployee(String name, double hourlyRate, double hoursWorked) {
        super(name, "Part-time");
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public void calculateSalary() {
        setSalary(hourlyRate * hoursWorked);
    }
}

class Contractor extends Employee {
    private final double hourlyRate;
    private final double maxHours;

    public Contractor(String name, double hourlyRate, double maxHours) {
        super(name, "Contractor");
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    @Override
    public void calculateSalary() {
        setSalary(hourlyRate * maxHours);
    }
}
