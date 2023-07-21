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

public class TC_SDK_Customer_Context_ReStart_Verification_AtomicFI extends BaseClass {

//    Abandoned after employer selection and restart done @OTP for Okinus and Luthersales, for LCO restart done @Payroll Login

    String emailid = "VerificationReStartAtomicFI" + randomNum + "@paywalletllc.com";
    String ClearRequestid;
    @Test(priority = 1, dataProvider = "AtomicFITestData" ,dataProviderClass = CustomDataProvider.class)
    public void ReStartVerification(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                       String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                       String username, String password,String Client1,String Client2,String Client3) throws InterruptedException {
        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        customercontext.setAffordabilitycheckbox();
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        String requestid = customercontext.getRequestIdtext();
        logger.info("Request id is : " + requestid);
        ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ClearRequestid);
        Thread.sleep(2000);
        getDriver().get(CustomerContextURL);
        logger.info("Abandoned ");
        Thread.sleep(2000);
        customercontext.setScrollIntoView();
        customercontext.sendrequestID(requestid);
        logger.info("Request id Entered for Restart");
        customercontext.setScroll();
        Thread.sleep(2000);
        customercontext.clickCheckbox();
        logger.info("Clicked on CheckBox");
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Clicked on Submit");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2) || Username.startsWith(Client3)))
        {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            logger.info("OTP Entered and Verified");
            UtilityClass.AtomicFISDKLoginFlow(username, password);
            Thread.sleep(25000);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.DownloadExcel();
            customercontext.setAffordabilityinfo();

        }
        else
        {
            WebDriverWait waitt = new WebDriverWait(getDriver(), Duration.ofSeconds(80));
            waitt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            CC_Customer_ContextFlow CustomerContext = new CC_Customer_ContextFlow(getDriver());
            Thread.sleep(20000);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='atomic-transact-iframe']")));
            CustomerContext.setsdkUsernamea(username);
            Thread.sleep(6000);
            CustomerContext.clickSDKcontinue();
            Thread.sleep(6000);
            CustomerContext.setsdkPassword(password);
            CustomerContext.clickSDKsignin();
            logger.info("SDK Login Done Successfully");
            Thread.sleep(25000);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.setThankYou();
        }

        UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestid);

        UtilityClass.metricReportValidation(ClearRequestid);


    }


}
