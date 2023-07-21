package com.paywallet.UItestCase;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.AffordabilityDashboard;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.pageObject.CC_CallBack_DashBoard_Page;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TC_DashBoard_Callback_Affordebility_AtomicFI extends BaseClass {

    String emailid = "affordabilityAtomicFI" + randomNum + "@paywalletllc.com";
    CC_CallBack_DashBoard_Page dashBoard;

    String ArgAto_ClearRequestid;

    @Test(priority = 1 , dataProvider = "AtomicFITestData" ,dataProviderClass = CustomDataProvider.class)
    public void affordabilityVerification(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                          String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                          String username, String password,String Client1 ,String Client2,String Client3) throws InterruptedException {

        dashBoard =new CC_CallBack_DashBoard_Page(getDriver());
        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        customercontext.setAffordabilitycheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        ArgAto_ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + ArgAto_ClearRequestid);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2) || Username.startsWith(Client3))) {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            logger.info("OTP Entered Successfully and Verified");
            UtilityClass.AtomicFISDKLoginFlow(username, password);
            Thread.sleep(25000);

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.setAffordabilityinfo();
            logger.info("Identity Details Displayed Successfully");
            customercontext.DownloadExcel();
            logger.info("Excel Download was Success");

        }
        else
        {

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            UtilityClass.AtomicFISDKLoginFlow(username, password);
            Thread.sleep(30000);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.setThankYou();
        }

        getDriver().get(AffordabilityURLPath);
        System.out.println("Affordebility call back Path URL AtomicFI : "+AffordabilityURLPath);
        List<WebElement> elementss = dashBoard.listofWebElement();
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(CallbackJson, JsonObject.class);
            String requestId = jsonObject.get("requestId").getAsString();
            if(requestId.equals(ArgAto_ClearRequestid))
            {
                strr = CallbackJson;
                System.out.println(strr);
                break;
            }
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(strr, JsonObject.class);
        String callbackDataType = jsonObject.get("callbackDataType").getAsString();
        System.out.println(callbackDataType);
        String Callback_requestId = jsonObject.get("requestId").getAsString();
        String Callback_Employer = jsonObject.get("data").getAsJsonObject().get("employer").getAsString();
        String Callback_Firstname = jsonObject.get("data").getAsJsonObject().get("firstName").getAsString();
        String Callback_Lastname = jsonObject.get("data").getAsJsonObject().get("lastName").getAsString();
        String Callback_SalaryFrequency = jsonObject.get("data").getAsJsonObject().get("salaryFrequency").getAsString();
        String Callback_NetSalaryPaid = jsonObject.get("data").getAsJsonObject().get("netSalaryLastPaid").getAsString();
        String Callback_LastSalaryPaymentDate = jsonObject.get("data").getAsJsonObject().get("lastSalaryPaymentDate").getAsString();
        String Callback_FundsAvailableForAllocation = jsonObject.get("data").getAsJsonObject().get("fundsAvailableForAllocation").getAsString();


        getDriver().get(Dashboard+"/requests");
        Thread.sleep(2000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ArgAto_ClearRequestid);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        AffordabilityDashboard affodash = new AffordabilityDashboard();
        for (int i=0; i<elements.size(); i++)
        {
            WebElement ele = null;
            String txt= null;
            switch (i){
                case 0:
                    ele = elements.get(0);
                    txt = ele!=null?ele.getText():null;
                    affodash.setRequestID(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele!=null?ele.getText():null;
                    affodash.setEmployer(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele!=null?ele.getText():null;
                    affodash.setFirstname(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele!=null?ele.getText():null;
                    affodash.setLastname(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele!=null?ele.getText():null;
                    affodash.setSalaryfrequency(txt);
                    break;
                case 5:
                    ele = elements.get(5);
                    txt = ele!=null?ele.getText():null;
                    affodash.setNetsalarylastpaid(txt);
                    break;
                case 6:
                    ele = elements.get(6);
                    txt = ele!=null?ele.getText():null;
                    affodash.setLastsalarypaymentdate(txt);
                    break;
                case 7:
                    ele = elements.get(7);
                    txt = ele!=null?ele.getText():null;
                    affodash.setFundsavailabelforallocation(txt);
                    break;
            }

        }
        String Dashboard_requestid = affodash.getRequestID();
        String Dashboard_employer = affodash.getEmployer();
        String Dashboard_Firstname = affodash.getFirstname();
        String Dashboard_Lastname = affodash.getLastname();
        String Dashboard_Salaryfreq = affodash.getSalaryfrequency();
        String Dashboard_NetSalary = affodash.getNetsalarylastpaid();
        String Dashboard_LastSalaryPayment = affodash.getLastsalarypaymentdate();
        String Dashboard_FundsavailableforAllocation = affodash.getFundsavailabelforallocation();

        Assert.assertEquals(Callback_requestId,Dashboard_requestid);
        Assert.assertEquals(Callback_Employer,Dashboard_employer);
        Assert.assertEquals(Callback_Firstname,Dashboard_Firstname);
        Assert.assertEquals(Callback_Lastname,Dashboard_Lastname);
        Assert.assertEquals(Callback_SalaryFrequency,Dashboard_Salaryfreq);
        Assert.assertEquals(Callback_NetSalaryPaid,Dashboard_NetSalary);
        Assert.assertEquals(Callback_LastSalaryPaymentDate,Dashboard_LastSalaryPayment);
        Assert.assertEquals(Callback_FundsAvailableForAllocation,Dashboard_FundsavailableforAllocation);

        getDriver().get(Dashboard+"/settings");
        dashBoard.clickAPIMatrixReport();
        dashBoard.clickSearchButton();
        dashBoard.checkrequestID(ArgAto_ClearRequestid);
    }

}
