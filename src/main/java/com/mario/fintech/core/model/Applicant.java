package com.mario.fintech.core.model;

import com.mario.fintech.core.model.enums.EmploymentType;

public class Applicant {

    private String name;
    private int age;
    private double monthlyIncome;
    private int creditScore;
    private double debtAmount;
    private EmploymentType employmentType;

    public Applicant() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMonthlyIncome() {
        return this.monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getCreditScore() {
        return this.creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getDebtAmount() {
        return this.debtAmount;
    }

    public void setDebtAmount(double debtAmount) {
        this.debtAmount = debtAmount;
    }

    public EmploymentType getEmploymentType() {
        return this.employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

}
