package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import org.testng.annotations.Test;

public class TC_SDK_Customer_Context_RestartAllocation_Cancel_AtomicFI extends BaseClass {

    String emailid = emailstring + randomNum +"@paywalletllc.com";
    String ClearRequestid;
    @Test(priority = 1, dataProvider = "AtomicFITestData" ,dataProviderClass = CustomDataProvider.class)
    public void depostiAllocation(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                  String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                  String username, String password,String Client1,String Client2,String Client3) throws InterruptedException {

        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        customercontext.setDepositallocationcheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        String requestid = customercontext.getRequestIdtext();
        logger.info("Request id is : " + requestid);
        ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ClearRequestid);
        logger.info("Employer Selected Successfully and clicked on Submit");
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2) ))
        {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            logger.info("OTP Entered Successfully and Verified");
            customercontext.Downloadexcel();
            logger.info("Excel Download Successful");
        }
        else
        {
            customercontext.allocationDownload();
            logger.info("Excel Download Successful");
        }
        customercontext.setScroll();
        Thread.sleep(2000);
        customercontext.clickConsentConform();
        logger.info("Clicked Consent OK Successfully");
        UtilityClass.AtomicFISDKLoginFlow(username,password);
        customercontext.setScroll();
        Thread.sleep(5000);
        customercontext.clickAtomicFIConform();
        Thread.sleep(35000);
        getDriver().switchTo().frame(0);
        customercontext.setSuccess();
        logger.info("Success Screen Displayed Successfully");

        UtilityClass.compareAllocationCallbackDashboardData(DepositAllocationURLPath,ClearRequestid);
        UtilityClass.metricReportValidation(ClearRequestid);

        CancelAllocation(customercontext, requestid,Client1,Client2,Client3);

        UtilityClass.compareAllocationCallbackCancelDashboardData(DepositAllocationURLPath,ClearRequestid);
        UtilityClass.metricReportValidation(ClearRequestid);

    }

    private void CancelAllocation(CC_Customer_ContextFlow customercontext, String requestid,String Client1,String Client2,String Client3) throws InterruptedException {
        getDriver().get(CustomerContextURL);
        customercontext.setScrollIntoView();
        Thread.sleep(4000);
        customercontext.setCancelAllocationcheckbox();
        customercontext.sendrequestID(requestid);
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details for Cancel Allocation and clicked Submit");
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2) ))
        {

            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            logger.info("OTP Entered Successfully and Verified");
            Thread.sleep(2000);
            customercontext.Downloadexcel();
            logger.info("Excel Download Successful");
        }
        else
        {
            customercontext.allocationDownload();
            logger.info("Excel Download Successful");
        }
        customercontext.setScroll();
        Thread.sleep(2000);
        customercontext.clickConsentConform();
        logger.info("Clicked Consent OK Successfully");
        getDriver().get(CustomerContextURL);
        Thread.sleep(2000);
        logger.info("Restart Flow Started");
        customercontext.setScrollIntoView();
        customercontext.sendrequestID(requestid);
        logger.info("Request Id Entered");
        customercontext.setScroll();
        Thread.sleep(4000);
        customercontext.clickCheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Check box selected and Clicked on Submit");
        Thread.sleep(20000);
        getDriver().switchTo().frame(0);
        customercontext.setSuccess();
        logger.info("Success Screen Displayed Successfully");
    }

}
