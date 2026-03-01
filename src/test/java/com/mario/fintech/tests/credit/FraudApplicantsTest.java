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

@Feature("Credit Engine")
@Story("Fraud Prevention and Blacklist")
public class FraudApplicantsTest {

    private CreditService creditService;

    @DataProvider
    public Object[][] jsonDataProvider() throws IOException {
        List<CreditTestData> testData = JsonDataReader.getTestData("fraud_cases.json");
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
    @Description("Ensures that applicants on the blacklist are automatically rejected with the correct reason (BLACKLISTED).")
    @Severity(SeverityLevel.CRITICAL)
    public void testFraudApplicants(CreditTestData creditTestData) {
        Applicant applicant = creditTestData.getApplicant();
        CreditResult creditResult = creditService.evaluateCredit(applicant);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(creditResult.isApproved(),
                creditTestData.getCreditResult().isApproved(),
                "The credit approvation is: " + creditResult.isApproved() + " but should be: " + creditTestData.getCreditResult().isApproved());

        softAssert.assertEquals(creditResult.getAssignedLimit(),
                creditTestData.getCreditResult().getAssignedLimit(),
                "The credit limit is: " + creditResult.getAssignedLimit() + " but should be: " + creditTestData.getCreditResult().getAssignedLimit());

        softAssert.assertEquals(creditResult.getInterestRate(),
                creditTestData.getCreditResult().getInterestRate(),
                "The interest rate is: " + creditResult.getInterestRate() + " but should be: " + creditTestData.getCreditResult().getInterestRate());

        softAssert.assertEquals(creditResult.getRejectionReason(),
                creditTestData.getCreditResult().getRejectionReason(),
                "The rejection reason is: " + creditResult.getRejectionReason() + " but should be: " + creditTestData.getCreditResult().getRejectionReason());

        softAssert.assertAll();
    }
}
