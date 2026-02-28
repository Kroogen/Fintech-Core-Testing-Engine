package com.mario.fintech.core.model.enums;

public enum EmploymentType {
    SALARIED(1),
    FREELANCE(2),
    UNEMPLOYED(3),
    RETIRED(4);

    private final int employeeType;

    EmploymentType(int employeeType) {
        this.employeeType = employeeType;
    }

    public int getEmployeeType() {
        return employeeType;
    }
}
