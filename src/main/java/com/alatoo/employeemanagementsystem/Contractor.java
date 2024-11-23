package com.alatoo.employeemanagementsystem;

public class Contractor extends Employee {
    private double hourlyRate;
    private int maxHours;

    public Contractor(String name, String position, double hourlyRate, int maxHours) {
        super(name, position);
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * maxHours;
    }
}
