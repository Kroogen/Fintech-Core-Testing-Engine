package com.mario.fintech.core.model;

public class CreditResult {

    private boolean approved;
    private double assignedLimit;
    private double interestRate;
    private String remarks;

    public CreditResult() {
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public double getAssignedLimit() {
        return assignedLimit;
    }

    public void setAssignedLimit(double assignedLimit) {
        this.assignedLimit = assignedLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
