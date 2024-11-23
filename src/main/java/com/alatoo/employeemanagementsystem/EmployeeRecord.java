package com.alatoo.employeemanagementsystem;

public class EmployeeRecord {
    private final int id;
    private final String name, type, position, hireDate;
    private final double salary;

    public EmployeeRecord(int id, String name, String type, String position, double salary, String hireDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }

    public String getHireDate() {
        return hireDate;
    }

    public double getSalary() {
        return salary;
    }
}
