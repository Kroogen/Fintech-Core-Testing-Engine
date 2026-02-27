package com.mario.fintech.tests.credit;

import com.mario.fintech.core.model.Applicant;
import com.mario.fintech.core.model.CreditResult;
import com.mario.fintech.core.services.CreditService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AgeBoundaryTest {

    private CreditService creditService;

    private final int VALID_AGE = 40;
    private final int VALID_INCOME = 4000;
    private final int VALID_SCORE = 700;
    private final double VALID_Debt = 0.0;

    @DataProvider
    public Object[][] ageProvider() {
        // Structure { int age, boolean expected_result}
        return new Object[][]{
                {17, false},
                {18, true},
                {19, true},
                {74, true},
                {75, true},
                {76, false}
        };
    }

    @BeforeClass(groups = "smoke")
    public void setup() {
        creditService = new CreditService();
    }

    @Test(dataProvider = "ageProvider", groups = "smoke")
    public void testAgeBoundary(int age, boolean expectedResult) {
        Applicant applicant = createValidApplicant();
        applicant.setAge(age);
        CreditResult creditResult = creditService.evaluateCredit(applicant);
        Assert.assertEquals(creditResult.isApproved(),
                expectedResult,
                "Failed for age: " + age);
    }

    private Applicant createValidApplicant() {
        Applicant applicant = new Applicant();
        applicant.setAge(VALID_AGE);
        applicant.setMonthlyIncome(VALID_INCOME);
        applicant.setCreditScore(VALID_SCORE);
        applicant.setDebtAmount(VALID_Debt);
        return applicant;
    }
}