package com.mario.fintech.core.services;

import com.mario.fintech.core.exceptions.InvalidApplicantException;
import com.mario.fintech.core.model.Applicant;
import com.mario.fintech.core.model.CreditResult;
import com.mario.fintech.core.model.enums.EmploymentType;
import com.mario.fintech.core.model.enums.RejectionReason;

public class CreditService {

    private final FraudService fraudService;

    private final int MIN_AGE = 18;
    private final int MAX_AGE = 75;
    private final int MIN_INCOME = 1000;
    private final int MIN_CREDIT_SCORE = 500;
    private final int GOOD_CREDIT_SCORE = 700;
    private final int LOW_CREDIT_MULTIPLIER = 2;
    private final int HIGH_CREDIT_MULTIPLIER = 5;
    private final double FREELANCE_CREDIT_MULTIPLIER = 1.5;
    private final double MAX_INTEREST_RATE = 15.0;
    private final double MIN_INTEREST_RATE = 8.0;
    private final double HIGH_DEBT_RATIO = 0.45;

    public CreditService() {
        this.fraudService = new FraudService();
    }

    public CreditResult evaluateCredit(Applicant applicant) {
        // Validate the applicant is able to be analized
        validateApplicant(applicant);

        // Validate the applicant name is on the Blacklist
        if (fraudService.isBlacklisted(applicant.getName())) {
            return rejectedApplicant(RejectionReason.BLACKLISTED);
        }

        // Validate the applicant is in a valid age for the credit
        if (applicant.getAge() < MIN_AGE || applicant.getAge() > MAX_AGE) {
            return rejectedApplicant(RejectionReason.INVALID_AGE);
        }

        // Validate if the applicant have the necessary income for the credit
        // Validate if the applicant is employed
        if (applicant.getMonthlyIncome() < MIN_INCOME ||
                applicant.getEmploymentType().equals(EmploymentType.UNEMPLOYED)) {
            return rejectedApplicant(RejectionReason.LOW_INCOME);
        }

        // Validate id the applicant have the necessary score for credit
        if (applicant.getCreditScore() < MIN_CREDIT_SCORE) { //Not enought credit score
            return rejectedApplicant(RejectionReason.LOW_CREDIT_SCORE);
        }

        // Validate if the applicant have good Debt Ratio
        if (applicant.getDebtAmount() > applicant.getMonthlyIncome() * HIGH_DEBT_RATIO) { // Hight debt ratio
            return rejectedApplicant(RejectionReason.HIGH_DEBT_RATIO);
        }

        // Approve scenarios
        // Calculating for Freelance workers
        if (applicant.getEmploymentType().equals(EmploymentType.FREELANCE)) {
            return approveApplicant(
                    applicant.getMonthlyIncome() * FREELANCE_CREDIT_MULTIPLIER,
                    MAX_INTEREST_RATE);
        }

        // Calculateing for applicants 500 - 700 credit score
        if (applicant.getCreditScore() <= GOOD_CREDIT_SCORE) { // Good credit score
            return approveApplicant(
                    applicant.getMonthlyIncome() * LOW_CREDIT_MULTIPLIER,
                    MAX_INTEREST_RATE);
        }

        // Calculateing for applicants with up to 700 credit score
        return approveApplicant(
                applicant.getMonthlyIncome() * HIGH_CREDIT_MULTIPLIER,
                MIN_INTEREST_RATE);

    }

    private CreditResult rejectedApplicant(RejectionReason rejectionReason) {
        return new CreditResult(false,
                0.0,
                0.0,
                rejectionReason);
    }

    private CreditResult approveApplicant(double assignedLimit, double interestRate) {
        return new CreditResult(true,
                assignedLimit,
                interestRate,
                RejectionReason.NONE);
    }

    private void validateApplicant(Applicant applicant) {
        if (applicant == null) {
            throw new InvalidApplicantException("Applicant can't be null");
        }
        if (applicant.getName() == null || applicant.getName().isBlank()) {
            throw new InvalidApplicantException("Applicant name is required");
        }
    }

}

