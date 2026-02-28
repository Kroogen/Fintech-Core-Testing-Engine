package com.mario.fintech.tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mario.fintech.core.model.Applicant;
import com.mario.fintech.core.model.CreditResult;

public class CreditTestData {

    @JsonProperty("description")
    private String description;
    @JsonProperty("applicant")
    private Applicant applicant;
    @JsonProperty("creditResult")
    private CreditResult creditResult;

    public String getDescription() {
        return description;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public CreditResult getCreditResult() {
        return creditResult;
    }

}
