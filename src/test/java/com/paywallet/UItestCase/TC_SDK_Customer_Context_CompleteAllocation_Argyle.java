package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import org.testng.annotations.Test;
import com.paywallet.pageObject.CC_Customer_ContextFlow;

public class TC_SDK_Customer_Context_CompleteAllocation_Argyle extends BaseClass {

    String emailid = "completeallocationArgyle" + randomNum + "@paywalletllc.com";
    String ClearRequestid;
    @Test(priority = 1,dataProvider = "ArgyleCompleteAllocation",dataProviderClass = CustomDataProvider.class)
    public void completeAllocation(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                   String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                   String username, String password, String code, String accountnumber, String ABAnumber,
                                   String bank,String bankuserid,String bankpassword) throws InterruptedException {

        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        customercontext.setDepositallocationcheckbox();
        customercontext.setAccountvalidationcheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ClearRequestid);
        UtilityClass.CustomerOTP();
        customercontext.setVerify();
        logger.info("OTP Entered Successfully and Verified");
        customercontext.Downloadexcel();
        logger.info("Excel Download Successful");
        Thread.sleep(2000);
        customercontext.clickConsentConform();
        logger.info("Clicked Consent OK Successfully");
        UtilityClass.ArgyleSDKLoginFlow(username, password, code);
        customercontext.setScroll();
        Thread.sleep(5000);
        customercontext.setClickconformArgyle();
        customercontext.setVerificationcode(code);
        Thread.sleep(2000);
        customercontext.setVerificationcontinue();
        customercontext.setSuccess();
        logger.info("Success Screen Displayed Successfully");
        customercontext.setVerifyaccount();
        logger.info("Clicked on Verify Account ");
        customercontext.setAccountnumber(accountnumber);
        logger.info("Account number Entered Successfully");
        customercontext.setAbanumber(ABAnumber);
        logger.info("ABANumber entered Successfully");
        customercontext.setSubmit();
        logger.info("Clicked on Submit Successfully");
        UtilityClass.finicityFlow(accountnumber,bank,bankuserid,bankpassword);

        UtilityClass.compareAllocationCallbackDashboardData(DepositAllocationURLPath,ClearRequestid);

        UtilityClass.metricReportValidation(ClearRequestid);

    }


}
