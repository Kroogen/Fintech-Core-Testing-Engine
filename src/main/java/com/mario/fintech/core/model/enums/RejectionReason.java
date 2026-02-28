package com.mario.fintech.core.model.enums;

public enum RejectionReason {
    INVALID_AGE(1),
    LOW_INCOME(2),
    HIGH_DEBT_RATIO(3),
    BLACKLISTED(4),
    NONE(5),
    LOW_CREDIT_SCORE(6);

    private final int rejectionReasonValue;

    RejectionReason(int rejectionReasonValue) {
        this.rejectionReasonValue = rejectionReasonValue;
    }

    public int getRejectionReasonValue() {
        return rejectionReasonValue;
    }
}
