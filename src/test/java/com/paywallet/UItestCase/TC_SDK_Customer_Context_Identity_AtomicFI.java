package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_SDK_Customer_Context_Identity_AtomicFI extends BaseClass {

    String emailid = "identityAtomicFI" + randomNum + "@paywalletllc.com";
    String ClearRequestid;
    @Test(priority = 1,dataProvider = "AtomicFITestData" ,dataProviderClass = CustomDataProvider.class)
    public void identityVerification(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                     String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                     String username, String password,String Client1,String Client2,String Client3) throws InterruptedException {
        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        customercontext.setIdentitycheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ClearRequestid);
        UtilityClass.CustomerOTP();
        customercontext.setVerify();
        logger.info("OTP Entered Successfully and Verified");
        UtilityClass.AtomicFISDKLoginFlow(username,password);
        Thread.sleep(25000);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
        customercontext.setIdentityinfo();
        logger.info("Identity Details Displayed Successfully");
        customercontext.DownloadExcel();
        logger.info("Excel Download was Success");

        UtilityClass.compareIdentityCallbackDashboardData(IdentityURLPath,ClearRequestid);

        UtilityClass.metricReportValidation(ClearRequestid);

    }

}
