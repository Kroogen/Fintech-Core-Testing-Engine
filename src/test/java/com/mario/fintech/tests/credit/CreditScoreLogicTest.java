package com.mario.fintech.tests.credit;

import com.mario.fintech.core.model.Applicant;
import com.mario.fintech.core.model.CreditResult;
import com.mario.fintech.core.services.CreditService;
import com.mario.fintech.tests.models.CreditTestData;
import com.mario.fintech.tests.utils.JsonDataReader;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

@Feature("Credit Evaluation")
@Story("Financial Logic Validation")
public class CreditScoreLogicTest {

    private CreditService creditService;

    @DataProvider
    public Object[][] jsonDataProvider() throws IOException {
        List<CreditTestData> testData = JsonDataReader.getTestData("valid_applicants.json");
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @BeforeClass(groups = "regression")
    public void setup() {
        creditService = new CreditService();
    }

    @Test(dataProvider = "jsonDataProvider", groups = "regression")
    @Description("Validates that the assigned limit and interest rate match the business rules based on score and income")
    @Severity(SeverityLevel.CRITICAL)
    public void testLimitAndInterest(CreditTestData data) {
        Applicant applicant = data.getApplicant();

        CreditResult creditResult = creditService.evaluateCredit(applicant);
        SoftAssert softAssert = new SoftAssert();

        // Assert if the credit was approved
        softAssert.assertEquals(creditResult.isApproved(),
                data.getCreditResult().isApproved(),
                "The Approved is: " + creditResult.isApproved() + " but was expected:" + data.getCreditResult().isApproved());

        // Assert the credit Limit
        softAssert.assertEquals(creditResult.getAssignedLimit(),
                data.getCreditResult().getAssignedLimit(),
                "The Obtained limit is: " + creditResult.getAssignedLimit() + " but was expected: " + data.getCreditResult().getAssignedLimit());

        // Assert the Interest Rate
        softAssert.assertEquals(creditResult.getInterestRate(),
                data.getCreditResult().getInterestRate(),
                "The Interest rate is: " + creditResult.getInterestRate() + " but was expected: " + data.getCreditResult().getInterestRate());
        softAssert.assertAll();
    }
}