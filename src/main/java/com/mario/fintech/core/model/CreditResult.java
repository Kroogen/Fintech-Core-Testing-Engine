package com.mario.fintech.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mario.fintech.core.model.enums.RejectionReason;

public class CreditResult {

    private final boolean approved;
    private final double assignedLimit;
    private final double interestRate;
    private final RejectionReason rejectionReason;

    @JsonCreator
    public CreditResult(
            @JsonProperty("approved") boolean approved,
            @JsonProperty("assignedLimit") double assignedLimit,
            @JsonProperty("interestRate") double interestRate,
            @JsonProperty("rejectionReason") RejectionReason rejectionReason) {
        this.approved = approved;
        this.assignedLimit = assignedLimit;
        this.interestRate = interestRate;
        this.rejectionReason = rejectionReason;
    }

    public boolean isApproved() {
        return approved;
    }

    public double getAssignedLimit() {
        return assignedLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }

}
