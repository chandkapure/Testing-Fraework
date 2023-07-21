package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_SDK_Customer_Context_PDSupportFalseAllocation_Argyle extends BaseClass {


    String emailid = "PDsupportfalse" + randomNum + "@agt9thzl.mailosaur.net";

    String ClearRequestid;
    @Test(priority = 1, dataProvider = "ArgylePDsupport",dataProviderClass = CustomDataProvider.class)
    public void PDSupportFalse(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                               String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                               String username, String password, String code, String mailosauremail, String mailosaurpassword,String Client1,String Client2,String Client3) throws InterruptedException {

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
        ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ClearRequestid);
        if(Username.startsWith(Client1) || Username.startsWith(Client2) || Username.startsWith(Client3))
        {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            logger.info("OTP Entered Successfully and Verified");
            Thread.sleep(5000);
            UtilityClass.ArgyleSDKLoginFlow(username, password, code);
            customercontext.setScroll();
            Thread.sleep(5000);
            customercontext.verifyPDSupportStatus();
            logger.info("Pending Screen Displayed Successfully");

            getDriver().get(CustomerpdsupportfalseallocationArgyle);
            EmailVerificationFlow( mailosauremail, mailosaurpassword);
        }
        else
        {
            customercontext.getPdsupportstatus();
            logger.info("Pay Distribution not supported Displayed");
        }

        UtilityClass.PDsupportfalseallocationdashboard(ClearRequestid);

    }

    private void EmailVerificationFlow(String mailosauremail, String mailosaurpassword) throws InterruptedException {
        CC_ProfileDataLinkPage_Client profiledata = new CC_ProfileDataLinkPage_Client(getDriver());
        profiledata.setmailosaurEmail(mailosauremail);
        profiledata.clickmailosaurContinue();
        profiledata.setsetmailosaurPassword(mailosaurpassword);
        profiledata.clickmailosaurLogin();
        Thread.sleep(4000);
        profiledata.setRefresh();
        Thread.sleep(1000);
        profiledata.setSelectEmail2();
        profiledata.scroll();
        Thread.sleep(3000);
        profiledata.clickOnProfiledataLink();
        String parent = getDriver().getWindowHandle();
        Set<String> allWindow = getDriver().getWindowHandles();
        for(String child: allWindow)
        {
            if(!parent.equalsIgnoreCase(child))
            {
                getDriver().switchTo().window(child);
                UtilityClass.ClientOTP();
                profiledata.clickProfiledataVerify();
                profiledata.selectCheckbox();
                profiledata.setSubmit();
            }
        }
    }

}
