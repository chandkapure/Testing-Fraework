package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import com.paywallet.Utilities.ReadExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_SDK_Customer_Context_VerificationDisagree extends BaseClass {
    String emailid = "verificationdisagree" + randomNum + "@paywalletllc.com";

    @DataProvider(name = "VerificationDisagreeData")
    public Object[][] getData() throws IOException {
        String excelpath = System.getProperty("user.dir");
        ReadExcel excel =new ReadExcel(excelpath+"/TestData/Customer_Context_Argyle_Test_Data.xlsx");
        int totalrows = excel.getRowCount("VerificationDisagree");
        int totalcols = excel.getCellCount("VerificationDisagree", 1);
        String loginData[][] = new String[totalrows][totalcols];
        for (int i = 1; i <= totalrows; i++) //1
        {
            for (int j = 0; j < totalcols; j++) //0
            {
                loginData[i - 1][j] = excel.getCellData("Income", i, j);
            }
        }
        return loginData;
    }

    @Test(priority = 1 ,dataProvider = "VerificationDisagreeData")
    public void verificationDisagree(String firstname, String lastname, String employer, String numberofinstalment, String Installmentamout,
                                   String firstpaymentdate, String howoftenyougetpaid, String lender, String numberofmonth, String otp1, String otp2, String otp3, String otp4, String otp5, String otp6) throws InterruptedException {

        SDKLoginFlow(firstname, lastname, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid, lender, numberofmonth, otp1, otp2, otp3, otp4, otp5, otp6);
    }

    private void SDKLoginFlow(String firstname, String lastname, String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid, String lender, String numberofmonth, String otp1, String otp2, String otp3, String otp4, String otp5, String otp6) throws InterruptedException {
        getDriver().get(CustomerContextURL);
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setFirstname(firstname);
        customercontext.setLastname(lastname);
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        customercontext.setEmployer(employer);
        customercontext.setNumberOfInstallments(numberofinstalment);
        customercontext.setInstallmentAmount(Installmentamout);
        customercontext.setFirstDateOfPayment(firstpaymentdate);
        customercontext.setRepaymentFrequency(howoftenyougetpaid);
        customercontext.setIncomecheckbox();
        customercontext.setSelectlender(lender);
        customercontext.setScroll();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        Thread.sleep(5000);
        getDriver().switchTo().frame(0);
        customercontext.setSelectemployer();
        customercontext.setSubmit();
        logger.info("Employer Selected Successfully and clicked on Submit");
        customercontext.setNumberofmonthrequired(numberofmonth);
        customercontext.setSubmit();
        logger.info("All Details Entered in Customer Capture Screen and clicked Submit");
        Thread.sleep(5000);
        customercontext.setOTP1(otp1);
        customercontext.setOTP2(otp2);
        customercontext.setOTP3(otp3);
        customercontext.setOTP4(otp4);
        customercontext.setOTP5(otp5);
        customercontext.setOTP6(otp6);
        customercontext.setVerify();
        logger.info("OTP Entered Successfully and Verified");
        customercontext.setConsentcheckbox();
        customercontext.setConsentdisagree();
        logger.info("Consent Checkbox Handled and clicked on DisAgree");
    }
}
