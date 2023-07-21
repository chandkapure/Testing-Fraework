package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.ReadExcel;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_SDK_Okinus_Customer_Context_Affordability_AtomicFI extends BaseClass {

    String emailid = "affordabilityAtomicFI" + randomNum + "@paywalletllc.com";

    @DataProvider(name="AffordablityData")
    public Object[][] getData() throws IOException
    {
        String excelpath = System.getProperty("user.dir");
        ReadExcel excel =new ReadExcel(excelpath+"/TestData/Customer_Context_Argyle_Test_Data.xlsx");
        int totalrows=excel.getRowCount("OkinusAffordebilityAtomicFI");
        int totalcols=excel.getCellCount("OkinusAffordebilityAtomicFI",1);
        String loginData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                loginData[i-1][j]=excel.getCellData("OkinusAffordebilityAtomicFI", i, j);
            }
        }
        return loginData;
    }
    @Test(priority = 1 , dataProvider = "AffordablityData")
    public void affordabilityVerification(String firstname, String lastname, String employer, String numberofinstalment, String Installmentamout,
                                          String firstpaymentdate, String howoftenyougetpaid, String lender, String otp1, String otp2, String otp3, String otp4, String otp5, String otp6,
                                          String username, String password) throws InterruptedException {
        getDriver().get(CustomerContextURL);
        SDKLoginFlow(firstname, lastname, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid, lender, otp1, otp2, otp3, otp4, otp5, otp6, username, password);

    }

    private void SDKLoginFlow(String firstname, String lastname, String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid, String lender, String otp1, String otp2, String otp3, String otp4, String otp5, String otp6, String username, String password) throws InterruptedException {
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
        customercontext.setAffordabilitycheckbox();
        customercontext.setSelectlender(lender);
        customercontext.setScroll();
        Thread.sleep(3000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and Clicked Submit");
        Thread.sleep(5000);
        getDriver().switchTo().frame(0);
        customercontext.setSelectemployer();
        customercontext.setSubmit();
        logger.info("Employer Selected Successfully and clicked on Submit");
        Thread.sleep(6000);
        customercontext.setOTP1(otp1);
        customercontext.setOTP2(otp2);
        customercontext.setOTP3(otp3);
        customercontext.setOTP4(otp4);
        customercontext.setOTP5(otp5);
        customercontext.setOTP6(otp6);
        customercontext.setVerify();
        logger.info("OTP Entered Successfully and Verified");
        Thread.sleep(10000);
        getDriver().switchTo().frame(0);
        customercontext.setsdkUsername(username);
        Thread.sleep(2000);
        customercontext.clickSDKcontinue();
        Thread.sleep(2000);
        customercontext.setsdkPassword(password);
        customercontext.clickSDKsignin();
        logger.info("SDK Login Done Successfully");
        Thread.sleep(25000);
        getDriver().switchTo().frame(0);
        customercontext.setDownload();
        logger.info("Excel Download was Success");
        customercontext.setAffordabilityinfo();
        logger.info("Identity Details Displayed Successfully");
    }


}
