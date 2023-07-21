package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import org.testng.annotations.Test;

public class TC_SDK_Customer_Context_ReStart_Allocation_Argyle extends BaseClass {

//    Abandoned @ Consent Screen restart done @OTP Screen for Okinus and Luthersales, for LCO restart doen @Conscent Screen
    String emailid = emailstring + randomNum + "@paywalletllc.com";

    String ClearRequestid;
    @Test(priority = 1, dataProvider = "ArgyleTestData",dataProviderClass = CustomDataProvider.class )
    public void ReStartAllocation(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                  String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                  String username, String password, String code,String Client1,String Client2,String Client3) throws InterruptedException {

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
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2) || Username.startsWith(Client3)))
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
        getDriver().get(CustomerContextURL);
        Thread.sleep(2000);
        customercontext.setScrollIntoView();
        customercontext.sendrequestID(requestid);
        customercontext.setScroll();
        Thread.sleep(4000);
        customercontext.clickCheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2) || Username.startsWith(Client3)))
        {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
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
        UtilityClass.ArgyleSDKLoginFlow(username, password, code);
        customercontext.setScroll();
        UtilityClass.clickAllocationConformArgyle(code);
        customercontext.setSuccess();
        logger.info("Success Screen Displayed Successfully");

        UtilityClass.compareAllocationCallbackDashboardData(DepositAllocationURLPath,ClearRequestid);

        UtilityClass.metricReportValidation(ClearRequestid);
    }

}
