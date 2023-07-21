package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import org.testng.annotations.Test;
import com.paywallet.pageObject.CC_Customer_ContextFlow;

public class TC_SDK_Customer_Context_CompleteVerification_Argyle extends BaseClass {

    String emailid = "completeverificationArgyle" + randomNum +"@paywalletllc.com";
    String ClearRequestid;
    @Test(priority = 1, dataProvider = "ArgyleTestData",dataProviderClass = CustomDataProvider.class)
    public void completeVerification(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                     String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                     String username, String password, String code,String Client1,String Client2,String Client3) throws InterruptedException {
        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        customercontext.setIncomecheckbox();
        customercontext.setEmploymentcheckbox();
        customercontext.setIdentitycheckbox();
        customercontext.setAffordabilitycheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ClearRequestid);
        UtilityClass.CustomerOTP();
        Thread.sleep(2000);
        customercontext.setVerify();
        logger.info("OTP Entered Successfully and Verified");
        UtilityClass.ArgyleSDKLoginFlow(username, password, code);
        customercontext.setDownload();
        logger.info("Excel Download Successful");
        customercontext.setIdentityinfo();
        logger.info("Identity Details Displayed Successfully");
        customercontext.setEmployementinfo();
        logger.info("Employment Details Displayed Successfully");
        customercontext.VerifySalaryinfo();
        logger.info("Income Details Displayed Successfully");
        customercontext.setAffordabilityinfo();
        logger.info("Affordability Details Displayed Successfully");

        UtilityClass.compareIncomeCallbackDashboardData(IncomeURLPath,ClearRequestid);
        UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestid);
        UtilityClass.compareIdentityCallbackDashboardData(IdentityURLPath,ClearRequestid);
        UtilityClass.compareEmploymentCallbackDashboardData(EmploymentURLPath,ClearRequestid);

        UtilityClass.metricReportValidation(ClearRequestid);
    }

}
