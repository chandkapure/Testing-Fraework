package com.paywallet.UItestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.paywallet.pageObject.CC_Customer_ContextFlow;

import java.time.Duration;

public class TC_SDK_Customer_Context_AccountValidation_Finicity extends BaseClass {

    String emailid = "AccountvalidationFinicity" + randomNum + "@paywalletllc.com";

    @Test(dataProvider = "AccountValidationFinicity",dataProviderClass = CustomDataProvider.class)
    public void acountValidation(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                 String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                 String accnumber, String abanumber, String bank, String bankuserid, String bankpassword) throws InterruptedException {

        String aba = "0"+abanumber;
        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB, fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        Thread.sleep(2000);
        customercontext.setAccountvalidationcheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and Clicked Submit");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
        logger.info("Switched to the available frame");
        customercontext.setAccountnumber(accnumber);
        logger.info("Account number Entered Successfully");
        customercontext.setAbanumber(aba);
        logger.info("ABANumber entered Successfully");
        Thread.sleep(6000);
        customercontext.setSubmit();
        logger.info("Clicked on Submit Successfully");
        UtilityClass.finicityFlow(aba,bank,bankuserid,bankpassword);


    }


}


