package com.mario.fintech.tests.credit;

import com.mario.fintech.core.model.Applicant;
import com.mario.fintech.core.model.CreditResult;
import com.mario.fintech.core.services.CreditService;
import com.mario.fintech.tests.models.CreditTestData;
import com.mario.fintech.tests.utils.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class AgeBoundaryTest {

    private CreditService creditService;

    @DataProvider
    public Object[][] jsonDataProvider() throws IOException {
        List<CreditTestData> testData = JsonDataReader.getTestData("ages_scenarios.json");
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @BeforeClass(groups = "smoke")
    public void setup() {
        creditService = new CreditService();
    }

    @Test(dataProvider = "jsonDataProvider", groups = "smoke")
    public void testAgeBoundary(CreditTestData testData) {
        Applicant applicant = testData.getApplicant();
        CreditResult creditResult = creditService.evaluateCredit(applicant);
        Assert.assertEquals(creditResult.isApproved(),
                testData.getCreditResult().isApproved(),
                "The credit result is: " + creditResult.isApproved() + " but should be: " + testData.getCreditResult().isApproved());
    }
}