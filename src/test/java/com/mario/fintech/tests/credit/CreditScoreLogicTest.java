package com.mario.fintech.tests.credit;

import com.mario.fintech.core.model.Applicant;
import com.mario.fintech.core.model.CreditResult;
import com.mario.fintech.core.services.CreditService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreditScoreLogicTest {

    private CreditService creditService;

    private final int VALID_AGE = 40;
    private final int VALID_INCOME = 4000;
    private final int VALID_SCORE = 700;
    private final double VALID_DEBT = 0.0;

    @DataProvider
    public Object[][] creditDataProvider() {
        // Data structure
        // int creditScore, double income, double expectedLimit, double expectedInterest, boolean approved
        return new Object[][]{
                // Income under 1000
                {499, 999.99999, 0.0, 0.0, false},
                {500, 999.99999, 0.0, 0.0, false},
                {501, 999.99999, 0.0, 0.0, false},
                {699, 999.99999, 0.0, 0.0, false},
                {700, 999.99999, 0.0, 0.0, false},
                {701, 999.99999, 0.0, 0.0, false},
                {702, 999.99999, 0.0, 0.0, false},
                // Income equal to 1000
                {499, 1000, 0.0, 0.0, false},
                {500, 1000, 2000.0, 15.0, true},
                {501, 1000, 2000.0, 15.0, true},
                {699, 1000, 2000.0, 15.0, true},
                {700, 1000, 2000.0, 15.0, true},
                {701, 1000, 5000.0, 8.0, true},
                {702, 1000, 5000.0, 8.0, true},
                // Income equal to 1001
                {499, 1001, 0.0, 0.0, false},
                {500, 1001, 2002.0, 15.0, true},
                {501, 1001, 2002.0, 15.0, true},
                {699, 1001, 2002.0, 15.0, true},
                {700, 1001, 2002.0, 15.0, true},
                {701, 1001, 5005.0, 8.0, true},
                {702, 1001, 5005.0, 8.0, true}
        };
    }

    @BeforeClass(groups = "regression")
    public void setup() {
        creditService = new CreditService();
    }

    @Test(dataProvider = "creditDataProvider", groups = "regression")
    public void testLimitAndInterest(int creditScore, double income, double expectedLimit, double expectedInterest, boolean expectedApproved) {
        Applicant applicant = createValidApplicant();
        applicant.setCreditScore(creditScore);
        applicant.setMonthlyIncome(income);

        CreditResult creditResult = creditService.evaluateCredit(applicant);
        SoftAssert softAssert = new SoftAssert();
        // Assert if the credit was approved
        softAssert.assertEquals(creditResult.isApproved(),
                expectedApproved,
                "The Approved is: " + creditResult.isApproved() + " but was expected:" + expectedApproved);

        // Assert the credit Limit
        softAssert.assertEquals(creditResult.getAssignedLimit(),
                expectedLimit,
                "The Obtained limit is: " + creditResult.getAssignedLimit() + " but was expected: " + expectedLimit);

        // Assert the Interest Rate
        softAssert.assertEquals(creditResult.getInterestRate(),
                expectedInterest,
                "The Interest rate is: " + creditResult.getInterestRate() + " but was expected: " + expectedInterest);
        softAssert.assertAll();
    }

    private Applicant createValidApplicant() {
        Applicant applicant = new Applicant();
        applicant.setAge(VALID_AGE);
        applicant.setMonthlyIncome(VALID_INCOME);
        applicant.setCreditScore(VALID_SCORE);
        applicant.setDebtAmount(VALID_DEBT);
        return applicant;
    }

}