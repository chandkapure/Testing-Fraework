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

public class TC_SDK_Customer_Context_ReEntryVerification_Verification_Argyle extends BaseClass {

    String emailid =  emailstring +""+ randomNum + "@paywalletllc.com";

    String ClearRequestid;
    @Test(priority = 1, dataProvider = "ArgyleTestData",dataProviderClass = CustomDataProvider.class)
    public void ReEntry(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                        String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                        String username, String password, String code,String Client1,String Client2,String Client3) throws InterruptedException {
        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2)))
        {
            customercontext.setEmploymentcheckbox();
        }
        else {
            customercontext.setAffordabilitycheckbox();
        }
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        String requestid = customercontext.getRequestIdtext();
        logger.info("Request id is : " + requestid);
        ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ClearRequestid);
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2)))
        {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            UtilityClass.ArgyleSDKLoginFlow(username, password, code);
            customercontext.DownloadExcel();
            customercontext.setEmployementinfo();

        } else if (Username.startsWith(Client3)) {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            UtilityClass.ArgyleSDKLoginFlow(username, password, code);
            customercontext.DownloadExcel();
            customercontext.setAffordabilityinfo();
        } else
        {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            UtilityClass.ArgyleSDKLoginFlow(username, password, code);
            customercontext.setThankYou();
        }

        if((Username.startsWith(Client1)) || (Username.startsWith(Client2)))
        {
           UtilityClass.compareEmploymentCallbackDashboardData(EmploymentURLPath,ClearRequestid);
           UtilityClass.metricReportValidation(ClearRequestid);
        }
         else
        {
            UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestid);
            UtilityClass.metricReportValidation(ClearRequestid);
        }


        Thread.sleep(3000);
        getDriver().get(CustomerContextURL);
        customercontext.setScrollIntoView();
        Thread.sleep(5000);
        customercontext.setAffordabilitycheckbox();
        customercontext.sendrequestID(requestid);
        customercontext.setSubmit();
        logger.info("Entered requestId and Clicked on Submit");
        if(Username.startsWith(Client1) || Username.startsWith(Client2) || Username.startsWith(Client3))
        {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            customercontext.setDownload();
            logger.info("Excel Download Successful");
            customercontext.setAffordabilityinfo();
            logger.info("Affordability Details Entered Successfully");

        }

        else {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.setThankYou();
        }

        UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestid);
        UtilityClass.metricReportValidation(ClearRequestid);


    }

}
