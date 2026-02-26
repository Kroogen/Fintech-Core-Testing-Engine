package com.mario.fintech.core.services;

import com.mario.fintech.core.model.Applicant;
import com.mario.fintech.core.model.CreditResult;

public class CreditService {
    private final int MIN_AGE = 18;
    private final int MAX_AGE = 75;
    private final int MIN_INCOME = 1000;
    private final int MIN_CREDIT_SCORE = 500;
    private final int GOOD_CREDIT_SCORE = 700;
    private final int LOW_CREDIT_MULTIPLIER = 2;
    private final int HIGH_CREDIT_MULTIPLIER = 5;
    private final double MAX_INTEREST_RATE = 15.0;
    private final double MIN_INTEREST_RATE = 8.0;
    private final double HIGH_DEBT_RATIO = 0.45;
    private final String INVALID_AGE = "Invalid age";
    private final String INVALID_LOW_INCOME = "Low income";
    private final String INVALID_HIGH_DEBT = "High Debt Ratio";
    private final String INVALID_REJECTED = "Rejected";
    private final String VALID_APPROVED = "Approved";

    public CreditResult evaluateCredit(Applicant applicant) {
        // Validate the applicant is in a valid age for the credit
        if (applicant.getAge() < MIN_AGE || applicant.getAge() > MAX_AGE) {
            return rejectedApplicant(INVALID_AGE);
        }

        // Validate if the applicant have the necessary income for the credit
        if (applicant.getMonthlyIncome() < MIN_INCOME) {
            return rejectedApplicant(INVALID_LOW_INCOME);
        }

        // Validate id the applicant have the necessary score for credit
        if (applicant.getCreditScore() < MIN_CREDIT_SCORE) { //Not enought credit score
            return rejectedApplicant(INVALID_REJECTED);
        } else if (applicant.getDebtAmount() > applicant.getMonthlyIncome() * HIGH_DEBT_RATIO) { // Hight debt ratio
            return rejectedApplicant(INVALID_HIGH_DEBT);
        } else if (applicant.getCreditScore() <= GOOD_CREDIT_SCORE) { // Good credit score
            return approveApplicant(
                    applicant.getMonthlyIncome() * LOW_CREDIT_MULTIPLIER,
                    MAX_INTEREST_RATE);
        } else { // Hight credit score
            return approveApplicant(
                    applicant.getMonthlyIncome() * HIGH_CREDIT_MULTIPLIER,
                    MIN_INTEREST_RATE);
        }
    }

    private CreditResult rejectedApplicant(String remark) {
        CreditResult creditResult = new CreditResult();
        creditResult.setApproved(false);
        creditResult.setAssignedLimit(0);
        creditResult.setInterestRate(0);
        creditResult.setRemarks(remark);
        return creditResult;
    }

    private CreditResult approveApplicant(double assignedLimit, double interestRate) {
        CreditResult creditResult = new CreditResult();
        creditResult.setApproved(true);
        creditResult.setAssignedLimit(assignedLimit);
        creditResult.setInterestRate(interestRate);
        creditResult.setRemarks(VALID_APPROVED);
        return creditResult;
    }

}

