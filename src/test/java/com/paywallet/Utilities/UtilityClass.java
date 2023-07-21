package com.paywallet.Utilities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.*;
import com.paywallet.pageObject.CC_CallBack_DashBoard_Page;
import com.paywallet.pageObject.CC_CustomerCapturePage_Client;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v113.emulation.Emulation;
import org.openqa.selenium.devtools.v113.network.Network;
import org.openqa.selenium.devtools.v113.network.model.RequestId;
import org.openqa.selenium.devtools.v113.network.model.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class UtilityClass extends BaseClass {


    public static void keyClockLogin() throws InterruptedException {
        CC_CustomerCapturePage_Client customercapture = new CC_CustomerCapturePage_Client(getDriver());
        customercapture.setUsername(Username);
        customercapture.setPassword(Password);
        Thread.sleep(2000);
        customercapture.KeyclockclickSubmit();



    }

    public static void LoanApplicationForm(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                           String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid) throws InterruptedException {

        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setFirstname(firstname);
        customercontext.setLastname(lastname);
        customercontext.setAddress(address);
        customercontext.setCity(city);
        customercontext.setSelectstate(state);
        customercontext.setZipcode(zipcode);
        customercontext.setDateofbirth(DOB);
        customercontext.setFourtin(fourtin);
        customercontext.setEmployer(employer);
        customercontext.setScrollIntoView();
        Thread.sleep(3000);
        customercontext.setNumberOfInstallments(numberofinstalment);
        customercontext.setInstallmentAmount(Installmentamout);
        customercontext.setFirstDateOfPayment(firstpaymentdate);
        customercontext.setRepaymentFrequency(howoftenyougetpaid);
    }


    public static void ArgyleSDKLoginFlow(String username, String password, String code) throws InterruptedException {

        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setsdkUsername(username);
        customercontext.setsdkPassword(password);
        Thread.sleep(2000);
        customercontext.setsdkConnect();
        logger.info("SDK Login Done successfully");
        customercontext.setVerificationcode(code);
        Thread.sleep(2000);
        customercontext.setVerificationcontinue();
        logger.info("Verification Code Entered and clicked Continue");

    }

    public static void AtomicFISDKLoginFlow(String username, String password) throws InterruptedException {
        CC_Customer_ContextFlow CustomerContext = new CC_Customer_ContextFlow(getDriver());
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(220));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='atomic-transact-iframe']")));
        CustomerContext.setsdkUsername(username);
        Thread.sleep(6000);
        CustomerContext.clickSDKcontinue();
        Thread.sleep(6000);
        CustomerContext.setsdkPassword(password);
        CustomerContext.clickSDKsignin();
        Thread.sleep(20000);
        logger.info("SDK Login Done Successfully");
    }


    public static void ClientOTP() throws InterruptedException {
        CC_SDKLoginPage_Client client = new CC_SDKLoginPage_Client(getDriver());
        Thread.sleep(4000);
        client.setOTP("123456");
        Thread.sleep(2000);
        client.clickConcentVerify();
        logger.info("OTP Entered Successfully and clicked on Verify");

    }

    public static void enterOTP(String otp) throws InterruptedException {
        CC_SDKLoginPage_Client client = new CC_SDKLoginPage_Client(getDriver());
        client.setOTP(otp);
        Thread.sleep(2000);
        client.clickConcentVerify();
        logger.info("OTP Entered Successfully and clicked on Verify");
    }

    public static void CustomerOTP() throws InterruptedException {
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(240));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
        Thread.sleep(5000);
        customercontext.setOTP("123456");
    }

    public static void mailosaurLoginandandEmailSelection(String username, String password) throws InterruptedException {
        CC_SDKLoginPage_Client Affordebility_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Affordebility_SDKLoginLink.setmailosaurEmail(username);
        Affordebility_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Affordebility_SDKLoginLink.setsetmailosaurPassword(password);
        Affordebility_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur login done Successfully");
        Thread.sleep(8000);
        Affordebility_SDKLoginLink.setRefresh();
        Thread.sleep(2000);
        Affordebility_SDKLoginLink.selectSDKEmail();
        logger.info("SDK Email is received and email is selected");
        Affordebility_SDKLoginLink.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
    }

    public static void finicityFlow(String aba, String bank, String bankuserid, String bankpassword) throws InterruptedException {
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='finicityConnectIframe']")));
        customercontext.enterBank(bank);
        logger.info("Bank name entered Successfully");
        customercontext.SelectBank();
        logger.info("Bank Selected Successfully");
        customercontext.clickNextButton();
        logger.info("Clicked on next button");
        customercontext.enterBankuserid(bankuserid);
        logger.info("Entered bank userid");
        customercontext.enterBankPassword(bankpassword);
        logger.info("Entered bank password");
        customercontext.clickbanksubmit();
        logger.info("Clicked on Submit Button");
        customercontext.selectloanaccount();
        logger.info("Loan Account Selected");
        customercontext.ClickSave();
        customercontext.clickfinsubmit();
        Thread.sleep(10000);
        if (aba.contains("091000019")) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.matched();
            logger.info("Request Matched Successfully");
        } else {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.notMatched();
            logger.info("Request Not Matched Successfully");
        }
    }

    public static void ClientfinicityFlow(String accnumber, String bank, String bankuserid, String bankpassword) throws InterruptedException {
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='finicityConnectIframe']")));
        customercontext.enterBank(bank);
        logger.info("Bank name entered Successfully");
        customercontext.SelectBank();
        logger.info("Bank Selected Successfully");
        customercontext.clickNextButton();
        logger.info("Clicked on next button");
        customercontext.enterBankuserid(bankuserid);
        logger.info("Entered bank userid");
        customercontext.enterBankPassword(bankpassword);
        logger.info("Entered bank password");
        customercontext.clickbanksubmit();
        logger.info("Clicked on Submit Button");
        customercontext.selectloanaccount();
        logger.info("Loan Account Selected");
        customercontext.ClickSave();
        customercontext.clickfinsubmit();
        Thread.sleep(10000);
        if (accnumber.contains("1000002222")) {
            customercontext.matched();
            logger.info("Request Matched Successfully");
        } else {
            customercontext.notMatched();
            logger.info("Request Not Matched Successfully");
        }
    }

    public static void APIfinicityFlow(String aba, String bank, String bankuserid, String bankpassword) throws InterruptedException {
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='finicityConnectIframe']")));
        customercontext.enterBank(bank);
        logger.info("Bank name entered Successfully");
        customercontext.SelectBank();
        logger.info("Bank Selected Successfully");
        customercontext.clickNextButton();
        logger.info("Clicked on next button");
        customercontext.enterBankuserid(bankuserid);
        logger.info("Entered bank userid");
        customercontext.enterBankPassword(bankpassword);
        logger.info("Entered bank password");
        customercontext.clickbanksubmit();
        logger.info("Clicked on Submit Button");
        customercontext.selectloanaccount();
        logger.info("Loan Account Selected");
        customercontext.ClickSave();
        customercontext.clickfinsubmit();
        Thread.sleep(10000);
        if (aba.equals("091000019")) {

            customercontext.matched();
            logger.info("Request Matched Successfully");
        } else {
            customercontext.notMatched();
            logger.info("Request Not Matched Successfully");
        }
    }

    public static void lyonsFlow(String accnumber) throws InterruptedException {

        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        Thread.sleep(10000);

        if (accnumber.contains("1234567890")) {
            customercontext.matched();
            logger.info("Request Matched Successfully");
        } else {
            customercontext.notMatched();
            logger.info("Request Not Matched Successfully");
        }

    }

    public static void selectEmployerCustomer() throws InterruptedException {
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        Thread.sleep(20000);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(150));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
        customercontext.setSelectemployer();
        customercontext.clickIbutton();
        logger.info("Clicked on Ibutton");
        Thread.sleep(5000);
        customercontext.handlePopup();
        logger.info("Info Popup handled in employer screen");
        customercontext.providersDisplayed();
        logger.info("Common provider are displayed successfully");
        customercontext.setSubmit();
        logger.info("Employer Selected Successfully and clicked on Submit");
        getDriver().switchTo().defaultContent();
        Thread.sleep(5000);
    }

    public static void selectEmployerClient(String employer) throws InterruptedException {
        CC_CustomerCapturePage_Client ClientContext = new CC_CustomerCapturePage_Client(getDriver());
        ClientContext.providersDisplayed();
        logger.info("Most coman Providers are displayed Successfully");
        ClientContext.setSearchfield(employer);
        Thread.sleep(3000);
        ClientContext.selectEmployer();
        Thread.sleep(2000);
        ClientContext.clickIbutton();
        logger.info("Clicked on Ibutton");
        ClientContext.handlePopup();
        logger.info("Info Popup handled in employer screen");
        ClientContext.clickEmployerSearchSubmit();
        logger.info("Employer selected Successfully and clicked on submit");
    }


    public static void compareEmploymentCallbackDashboardData(String callbackURL, String ClearRequestid) throws InterruptedException {
        getDriver().get(callbackURL);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        List<WebElement> elementss = dashBoard.listofWebElement();
        Thread.sleep(10000);
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            if(CallbackJson.contains(ClearRequestid))
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
        String Callback_RequestID = jsonObject.get("requestId").getAsString();
        String Callback_Employer = jsonObject.get("employmentData").getAsJsonObject().get("employer").getAsString();
        String Callback_Firstname = jsonObject.get("employmentData").getAsJsonObject().get("firstName").getAsString();
        String Callback_Lastname = jsonObject.get("employmentData").getAsJsonObject().get("lastName").getAsString();
        String Callback_Middlename = jsonObject.get("employmentData").getAsJsonObject().get("middleName").getAsString();
        String Callback_Last4tin = jsonObject.get("employmentData").getAsJsonObject().get("last4Tin").getAsString();
        String Callback_Hiredate = jsonObject.get("employmentData").getAsJsonObject().get("hireDate").getAsString();
        String Callback_TypeofEmployemnt = jsonObject.get("employmentData").getAsJsonObject().get("typeOfEmployment").getAsString();
        String Callback_Designation = jsonObject.get("employmentData").getAsJsonObject().get("designation").getAsString();
//        String Callback_TerminationDate = jsonObject.get("employmentData").getAsJsonObject().get("terminationDate").isJsonNull() ? null :jsonObject.get("employmentData").getAsJsonObject().get("terminationDate").getAsString();
        String Callback_Employmentstatus = jsonObject.get("employmentData").getAsJsonObject().get("employmentStatus").getAsString();

        getDriver().get(Dashboard +"/requests");
        Thread.sleep(5000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickEmploymentDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        EmploymentDashboard Empdashboard = new EmploymentDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setRequestID(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setEmployer(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setLender(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setFirstname(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setMiddlename(txt);
                    break;
                case 5:
                    ele = elements.get(5);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setLastname(txt);
                    break;
                case 6:
                    ele = elements.get(6);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setHiredate(txt);
                    break;
                case 7:
                    ele = elements.get(7);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setTypeofemployment(txt);
                    break;
                case 8:
                    ele = elements.get(8);
                    txt = ele != null ? (ele.getText().isEmpty() ? null : ele.getText()) : null;
                    Empdashboard.setEmploymentCategory(txt);
                    break;
                case 9:
                    ele = elements.get(9);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setDesignation(txt);
                    break;
                case 10:
                    ele = elements.get(10);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setLast4tin(txt);
                    break;
                case 11:
                    ele = elements.get(11);
                    txt = ele != null ? (ele.getText().isEmpty() ? null : ele.getText()) : null;
                    Empdashboard.setTerminationDate(txt);
                    break;
                case 12:
                    ele = elements.get(12);
                    txt = ele != null ? ele.getText() : null;
                    Empdashboard.setEmploymentstatus(txt);
                    break;
            }
        }
        String Dashboard_RequestID = Empdashboard.getRequestID();
        String Dashboard_Employer = Empdashboard.getEmployer();
        String Dashboard_Firstname = Empdashboard.getFirstname();
        String Dashboard_Lastname = Empdashboard.getLastname();
        String Dashboard_Middlename = Empdashboard.getMiddlename();
        String Dashboard_Last4tin = Empdashboard.getLast4tin();
        String Dashboard_Hiredate = Empdashboard.getHiredate();
        String Dashboard_Typeofemployment = Empdashboard.getTypeofemployment();
        String Dashboard_EmploymentCategory = Empdashboard.getEmploymentCategory();
        String Dashboard_Designation = Empdashboard.getDesignation();
        String Dashboard_TerminationDate = Empdashboard.getTerminationDate();
        String Dashboard_Employmentstatus = Empdashboard.getEmploymentstatus();

        Assert.assertEquals(Callback_RequestID,Dashboard_RequestID);
        Assert.assertEquals(Callback_Employer,Dashboard_Employer);
        Assert.assertEquals(Callback_Firstname,Dashboard_Firstname);
        Assert.assertEquals(Callback_Lastname,Dashboard_Lastname);
        Assert.assertEquals(Callback_Middlename,Dashboard_Middlename);
        Assert.assertEquals(Callback_Last4tin,Dashboard_Last4tin);
        Assert.assertEquals(Callback_Hiredate,Dashboard_Hiredate);
        Assert.assertEquals(Callback_TypeofEmployemnt,Dashboard_Typeofemployment);
        Assert.assertEquals(Callback_Designation,Dashboard_Designation);
        Assert.assertEquals(Callback_Employmentstatus,Dashboard_Employmentstatus);

    }



    public static void compareIdentityCallbackDashboardData(String callbackURL, String ClearRequestid) throws InterruptedException {
        getDriver().get(callbackURL);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        List<WebElement> elementss = dashBoard.listofWebElement();
        Thread.sleep(10000);
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            if (CallbackJson.contains(ClearRequestid)) {
                strr = CallbackJson;
                System.out.println(strr);
                break;
            }
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(strr, JsonObject.class);
        String callbackDataType = jsonObject.get("callbackDataType").getAsString();
        System.out.println(callbackDataType);
        String Callback_Firstname = jsonObject.get("identityData").getAsJsonObject().get("firstName").getAsString();
        String Callback_Lastname = jsonObject.get("identityData").getAsJsonObject().get("lastName").getAsString();
        String Callback_Middlename = jsonObject.get("identityData").getAsJsonObject().get("middleName").getAsString();
        String Callback_Address1 = jsonObject.get("identityData").getAsJsonObject().get("addressLine1").getAsString();
        String Callback_Address2 = jsonObject.get("identityData").getAsJsonObject().get("addressLine2").getAsString();
        String Callback_City = jsonObject.get("identityData").getAsJsonObject().get("city").getAsString();
        String Callback_State = jsonObject.get("identityData").getAsJsonObject().get("state").getAsString();
        String Callback_Zipcode = jsonObject.get("identityData").getAsJsonObject().get("zipCode").getAsString();
        String Callback_Telephone = jsonObject.get("identityData").getAsJsonObject().get("telePhone").getAsString();
        String Callback_email = jsonObject.get("identityData").getAsJsonObject().get("email").getAsString();
        String Callback_last4tin = jsonObject.get("identityData").getAsJsonObject().get("last4Tin").getAsString();
        String Callback_DOB = jsonObject.get("identityData").getAsJsonObject().get("dateOfBirth").getAsString();

        getDriver().get(Dashboard +"/requests");
        Thread.sleep(5000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickIdentityDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//legend"));
        IdentityDashboard identityDashboard = new IdentityDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setFirstName(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setMiddleName(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setLastName(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setAddressLine1(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setAddressLine2(txt);
                    break;
                case 5:
                    ele = elements.get(5);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setCity(txt);
                    break;
                case 6:
                    ele = elements.get(6);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setState(txt);
                    break;
                case 7:
                    ele = elements.get(7);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setZipCode(txt);
                    break;
                case 8:
                    ele = elements.get(8);
                    txt = ele != null ? ele.getText(): null;
                    identityDashboard.setTelePhone(txt);
                    break;
                case 9:
                    ele = elements.get(9);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setEmail(txt);
                    break;
                case 10:
                    ele = elements.get(10);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setDob(txt);
                    break;
                case 11:
                    ele = elements.get(11);
                    txt = ele != null ? ele.getText() : null;
                    identityDashboard.setLast4tin(txt);
                    break;

            }
        }

        String Dashboard_Firstname = identityDashboard.getFirstName();
        String Dashboard_Lastname = identityDashboard.getLastName();
        String Dashboard_Middlename = identityDashboard.getMiddleName();
        String Dashboard_Address1 = identityDashboard.getAddressLine1();
        String Dashboard_Address2 = identityDashboard.getAddressLine2();
        String Dashboard_City = identityDashboard.getCity();
        String Dashboard_State = identityDashboard.getState();
        String Dashboard_ZipCode = identityDashboard.getZipCode();
        String Dashboard_Telephone = identityDashboard.getTelePhone();
        String Dashboard_Email = identityDashboard.getEmail();
        String Dashboard_Last4tin = identityDashboard.getLast4tin();
        String Dashboard_DOB = identityDashboard.getDob();

        Assert.assertEquals(Callback_Firstname,Dashboard_Firstname);
        Assert.assertEquals(Callback_Lastname,Dashboard_Lastname);
        Assert.assertEquals(Callback_Middlename,Dashboard_Middlename);
        Assert.assertEquals(Callback_Address1,Dashboard_Address1);
        Assert.assertEquals(Callback_Address2,Dashboard_Address2);
        Assert.assertEquals(Callback_City,Dashboard_City);
        Assert.assertEquals(Callback_State,Dashboard_State);
        Assert.assertEquals(Callback_Zipcode,Dashboard_ZipCode);
        Assert.assertEquals(Callback_Telephone,Dashboard_Telephone);
        Assert.assertEquals(Callback_email,Dashboard_Email);
        Assert.assertEquals(Callback_last4tin,Dashboard_Last4tin);
        Assert.assertEquals(Callback_DOB,Dashboard_DOB);

    }



    public static void compareIncomeCallbackDashboardData(String callbackURL, String ClearRequestid) throws InterruptedException {
        getDriver().get(callbackURL);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        List<WebElement> elementss = dashBoard.listofWebElement();
        Thread.sleep(10000);
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            if (CallbackJson.contains(ClearRequestid)) {
                strr = CallbackJson;
                System.out.println(strr);
                break;
            }
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(strr, JsonObject.class);
        String callbackDataType = jsonObject.get("callbackDataType").getAsString();
        System.out.println(callbackDataType);
        String Callback_RequestId = jsonObject.get("requestId").getAsString();
        String Callback_Lender = jsonObject.get("lender").getAsString();
        String Callback_employer = jsonObject.get("incomeData").getAsJsonObject().get("employer").getAsString();
        String Callback_Firstname = jsonObject.get("incomeData").getAsJsonObject().get("firstName").getAsString();
        String Callback_LastName = jsonObject.get("incomeData").getAsJsonObject().get("lastName").getAsString();
        String Callback_MiddleName = jsonObject.get("incomeData").getAsJsonObject().get("middleName").getAsString();
        String Callback_Last4tin = jsonObject.get("incomeData").getAsJsonObject().get("last4Tin").getAsString();
        String Callback_BaseSalaryRate = jsonObject.get("incomeData").getAsJsonObject().get("baseSalaryRate").getAsString();
        String Callback_BaseSalaryRateUnit = jsonObject.get("incomeData").getAsJsonObject().get("baseSalaryRateUnit").getAsString();
        String Callback_GrossSalaryLastPaid = jsonObject.get("incomeData").getAsJsonObject().get("grossSalaryLastPaid").getAsString();
        String Callback_NetsalaryLastpaid = jsonObject.get("incomeData").getAsJsonObject().get("netSalaryLastPaid").getAsString();
        String Callback_LastSalaryPmtDate = jsonObject.get("incomeData").getAsJsonObject().get("lastSalaryPmtDate").getAsString();
        String Callback_MaxSalaryEarnedInPeriod = jsonObject.get("incomeData").getAsJsonObject().get("maxSalaryEarnedInPeriod").getAsString();
        String Callback_MinSalaryEarnedInPeriod = jsonObject.get("incomeData").getAsJsonObject().get("minSalaryEarnedInPeriod").getAsString();
        String Callback_AverageSalaryEarnedInPeriod = jsonObject.get("incomeData").getAsJsonObject().get("averageSalaryEarnedInPeriod").getAsString();
        String Callback_PaymentFrequency = jsonObject.get("incomeData").getAsJsonObject().get("paymentFrequency").getAsString();
        JsonElement salary_account= jsonObject.get("incomeData").getAsJsonObject().get("salaryAccountNumber");
        String Callback_SalaryAccountNumber =(salary_account!=null?salary_account.getAsString():"");
        JsonElement abaNumber= jsonObject.get("incomeData").getAsJsonObject().get("abaNumber");
        String Callback_ABANumber = (abaNumber!=null?abaNumber.getAsString():"");

        getDriver().get(Dashboard+"/requests");
        Thread.sleep(5000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickIncomeDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        IncomeDashboard incomeDashboard = new IncomeDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setRequestID(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setLender(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setEmployer(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setFirstName(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setLastName(txt);
                    break;
                case 5:
                    ele = elements.get(5);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setMiddleName(txt);
                    break;
                case 6:
                    ele = elements.get(6);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setLast4tin(txt);
                    break;
                case 7:
                    ele = elements.get(7);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setBaseSalaryRate(txt);
                    break;
                case 8:
                    ele = elements.get(8);
                    txt = ele != null ? ele.getText(): null;
                    incomeDashboard.setBaseSalaryRateUnit(txt);
                    break;
                case 9:
                    ele = elements.get(9);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setGrossSalaryLastPaid(txt);
                    break;
                case 10:
                    ele = elements.get(10);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setNetSalaryLastPaid(txt);
                    break;
                case 11:
                    ele = elements.get(11);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setLastSalaryPmtDate(txt);
                    break;
                case 12:
                    ele = elements.get(12);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setMaxSalaryEarnedInPeriod(txt);
                    break;
                case 13:
                    ele = elements.get(13);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setMinSalaryEarnedInPeriod(txt);
                    break;
                case 14:
                    ele = elements.get(14);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setAverageSalaryEarnedInPeriod(txt);
                    break;
                case 15:
                    ele = elements.get(15);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setPaymentFrequency(txt);
                    break;
                case 16:
                    ele = elements.get(16);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setSalaryAccNumber(txt);
                    break;
                case 17:
                    ele = elements.get(17);
                    txt = ele != null ? ele.getText() : null;
                    incomeDashboard.setABANumber(txt);
                    break;

            }
        }

        String Dashboard_Requestid = incomeDashboard.getRequestID();
        String Dashboard_Lender = incomeDashboard.getLender();
        String Dashboard_Employer = incomeDashboard.getEmployer();
        String Dashboard_Firstname = incomeDashboard.getFirstName();
        String Dashboard_Lastname = incomeDashboard.getLastName();
        String Dashboard_Middlename = incomeDashboard.getMiddleName();
        String Dashboard_Last4tin = incomeDashboard.getLast4tin();
        String Dashboard_BaseSalaryRate = incomeDashboard.getBaseSalaryRate();
        String Dashboard_BaseSalaryRateUnit = incomeDashboard.getBaseSalaryRateUnit();
        String Dashboard_GrossSalaryLastPaid = incomeDashboard.getGrossSalaryLastPaid();
        String Dashboard_NetsalaryLastPaid = incomeDashboard.getNetSalaryLastPaid();
        String Dashboard_LastSalaryPaymentDate = incomeDashboard.getLastSalaryPmtDate();
        String Dashboard_MaxSalaryEarnedInPeriod = incomeDashboard.getMaxSalaryEarnedInPeriod();
        String Dashboard_MinSalaryEarnedInPeriod = incomeDashboard.getMinSalaryEarnedInPeriod();
        String Dashboard_AverageSalaryEarnedInPeriod = incomeDashboard.getAverageSalaryEarnedInPeriod();
        String Dashboard_PaymentFrequency = incomeDashboard.getPaymentFrequency();
        String Dashboard_SalaryAccountNumber = incomeDashboard.getSalaryAccNumber();
        String Dashboard_ABANumber = incomeDashboard.getABANumber();

        Assert.assertEquals(Callback_RequestId,Dashboard_Requestid);
        Assert.assertEquals(Callback_Lender,Dashboard_Lender);
        Assert.assertEquals(Callback_employer,Dashboard_Employer);
        Assert.assertEquals(Callback_Firstname,Dashboard_Firstname);
        Assert.assertEquals(Callback_LastName,Dashboard_Lastname);
        Assert.assertEquals(Callback_MiddleName,Dashboard_Middlename);
        Assert.assertEquals(Callback_Last4tin,Dashboard_Last4tin);
        Assert.assertEquals(Callback_BaseSalaryRate,Dashboard_BaseSalaryRate);
        Assert.assertEquals(Callback_BaseSalaryRateUnit,Dashboard_BaseSalaryRateUnit);
        Assert.assertEquals(Callback_GrossSalaryLastPaid,Dashboard_GrossSalaryLastPaid);
        Assert.assertEquals(Callback_NetsalaryLastpaid,Dashboard_NetsalaryLastPaid);
        Assert.assertEquals(Callback_LastSalaryPmtDate,Dashboard_LastSalaryPaymentDate);
        Assert.assertEquals(Callback_MaxSalaryEarnedInPeriod,Dashboard_MaxSalaryEarnedInPeriod);
        Assert.assertEquals(Callback_MinSalaryEarnedInPeriod, Dashboard_MinSalaryEarnedInPeriod);
        Assert.assertEquals(Callback_AverageSalaryEarnedInPeriod,Dashboard_AverageSalaryEarnedInPeriod);
        Assert.assertEquals(Callback_PaymentFrequency,Dashboard_PaymentFrequency);
        Assert.assertEquals(Callback_SalaryAccountNumber,Dashboard_SalaryAccountNumber);
        Assert.assertEquals(Callback_ABANumber,Dashboard_ABANumber);

    }



    public static void compareAffordebilityCallbackDashboardData(String callbackURL,String ClearRequestid) throws InterruptedException {
        getDriver().get(callbackURL);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        List<WebElement> elementss = dashBoard.listofWebElement();
        Thread.sleep(10000);
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            if(CallbackJson.contains(ClearRequestid))
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
        String Callback_NetSalaryLastPaid = jsonObject.get("data").getAsJsonObject().get("netSalaryLastPaid").getAsString();
        String Callback_LastSalaryPaymentDate = jsonObject.get("data").getAsJsonObject().get("lastSalaryPaymentDate").getAsString();
        String Callback_FundsAvailableForAllocation = jsonObject.get("data").getAsJsonObject().get("fundsAvailableForAllocation").getAsString();

        getDriver().get(Dashboard+"/requests");
        Thread.sleep(5000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickAffordabilityDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        AffordabilityDashboard affdashboard = new AffordabilityDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setRequestID(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setEmployer(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setFirstname(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setLastname(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setSalaryfrequency(txt);
                    break;
                case 5:
                    ele = elements.get(5);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setNetsalarylastpaid(txt);
                    break;
                case 6:
                    ele = elements.get(6);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setLastsalarypaymentdate(txt);
                    break;
                case 7:
                    ele = elements.get(7);
                    txt = ele != null ? ele.getText() : null;
                    affdashboard.setFundsavailabelforallocation(txt);
                    break;
            }
        }

        String Dashboard_RequestID = affdashboard.getRequestID();
        String Dashbaord_Employer = affdashboard.getEmployer();
        String Dashboard_Firstname = affdashboard.getFirstname();
        String Dashboard_Lastname = affdashboard.getLastname();
        String Dashboard_SalaryFrequency = affdashboard.getSalaryfrequency();
        String Dashboard_NetsalaryLastPaid = affdashboard.getNetsalarylastpaid();
        String Dashboard_LastSalaryPaymentDate = affdashboard.getLastsalarypaymentdate();
        String Dashboard_FundsAvailableforAllocation = affdashboard.getFundsavailabelforallocation();

        Assert.assertEquals(Callback_requestId,Dashboard_RequestID);
        Assert.assertEquals(Callback_Employer,Dashbaord_Employer);
        Assert.assertEquals(Callback_Firstname,Dashboard_Firstname);
        Assert.assertEquals(Callback_Lastname,Dashboard_Lastname);
        Assert.assertEquals(Callback_SalaryFrequency,Dashboard_SalaryFrequency);
        Assert.assertEquals(Callback_NetSalaryLastPaid,Dashboard_NetsalaryLastPaid);
        Assert.assertEquals(Callback_LastSalaryPaymentDate,Dashboard_LastSalaryPaymentDate);
        Assert.assertEquals(Callback_FundsAvailableForAllocation,Dashboard_FundsAvailableforAllocation);

    }



    public static void compareAllocationCallbackDashboardData(String callbackURL,String ClearRequestid) throws InterruptedException {
        getDriver().get(callbackURL);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        List<WebElement> elementss = dashBoard.listofWebElement();
        Thread.sleep(10000);
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            if(CallbackJson.contains(ClearRequestid))
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
        String Callback_Installmentamount = jsonObject.get("installmentAmount").getAsString();
        String Callback_Status = jsonObject.get("status").getAsString();
        String Callback_RequestID = jsonObject.get("requestId").getAsString();

        getDriver().get(Dashboard+"/requests");
        Thread.sleep(5000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickAllocationDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        AllocationDashboard allocationDashboard = new AllocationDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setInstallmentAmount(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setFinanceAmount(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setRoutingCode(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setVirtualAccNumber(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setAllocationStatus(txt);
                    break;
            }
        }

        String Dashboard_InstallmentAmount = allocationDashboard.getInstallmentAmount();
        String Dashboard_AllocationStatus = allocationDashboard.getAllocationStatus();

        Assert.assertEquals(Callback_Installmentamount,Dashboard_InstallmentAmount);
        Assert.assertEquals(Callback_Status,"Success");
        Assert.assertEquals("ACTIVE",Dashboard_AllocationStatus);

    }


    public static void compareAllocationCallbackUpdateDashboardData(String callbackURL, String ClearRequestid) throws InterruptedException {
        getDriver().get(callbackURL);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        List<WebElement> elementss = dashBoard.listofWebElement();
        Thread.sleep(10000);
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            if(CallbackJson.contains(ClearRequestid))
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
        String Callback_Installmentamount = jsonObject.get("installmentAmount").getAsString();
        String Callback_Status = jsonObject.get("status").getAsString();
        String Callback_RequestID = jsonObject.get("requestId").getAsString();
        String Callback_revisedinstallmentamount = jsonObject.get("revisedInstallmentAmount").getAsString();

        getDriver().get(Dashboard+"/requests");
        Thread.sleep(5000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickAllocationDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        AllocationDashboard allocationDashboard = new AllocationDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setInstallmentAmount(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setFinanceAmount(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setRoutingCode(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setVirtualAccNumber(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setAllocationStatus(txt);
                    break;
            }
        }

        String Dashboard_InstallmentAmount = allocationDashboard.getInstallmentAmount();
        String Dashboard_AllocationStatus = allocationDashboard.getAllocationStatus();

        Assert.assertEquals(Callback_revisedinstallmentamount,Dashboard_InstallmentAmount);
        Assert.assertEquals(Callback_Status,"Updated");
        Assert.assertEquals("ACTIVE",Dashboard_AllocationStatus);

    }


    public static void compareAllocationCallbackCancelDashboardData(String callbackURL, String ClearRequestid) throws InterruptedException {
        getDriver().get(callbackURL);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        List<WebElement> elementss = dashBoard.listofWebElement();
        Thread.sleep(10000);
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            System.out.println(CallbackJson);
            if(CallbackJson.contains(ClearRequestid))
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
        String Callback_Installmentamount = jsonObject.get("installmentAmount").getAsString();
        String Callback_Status = jsonObject.get("status").getAsString();
        String Callback_RequestID = jsonObject.get("requestId").getAsString();


        getDriver().get(Dashboard+"/requests");
        Thread.sleep(5000);
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickAllocationDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        AllocationDashboard allocationDashboard = new AllocationDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setInstallmentAmount(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setFinanceAmount(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setRoutingCode(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setVirtualAccNumber(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setAllocationStatus(txt);
                    break;
            }
        }

        String Dashboard_InstallmentAmount = allocationDashboard.getInstallmentAmount();
        String Dashboard_AllocationStatus = allocationDashboard.getAllocationStatus();

        Assert.assertEquals(Callback_Installmentamount,Dashboard_InstallmentAmount);
        Assert.assertEquals(Callback_Status,"Cancelled");
        Assert.assertEquals("CANCELLED",Dashboard_AllocationStatus);

    }
    public static void PDsupportfalseallocationdashboard(String ClearRequestid) throws InterruptedException {
        getDriver().get(Dashboard+"/requests");
        Thread.sleep(5000);
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        dashBoard.searchForRequestIDAndSelectTheRequest(ClearRequestid);
        Thread.sleep(2000);
        dashBoard.clickAllocationDashboardDetails();
        Thread.sleep(4000);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        AllocationDashboard allocationDashboard = new AllocationDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setInstallmentAmount(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setFinanceAmount(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setRoutingCode(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setVirtualAccNumber(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null ? ele.getText() : null;
                    allocationDashboard.setAllocationStatus(txt);
                    break;
            }
        }
        String Dashboard_Allocation = allocationDashboard.getAllocationStatus();

        Assert.assertEquals("CREATED",Dashboard_Allocation);
    }

    public static void metricReportValidation(String ClearRequestid) throws InterruptedException {
        getDriver().get(Dashboard+"/settings");
        CC_CallBack_DashBoard_Page dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        dashBoard.clickAPIMatrixReport();
        dashBoard.clickSearchButton();
        Thread.sleep(3000);
        dashBoard.checkrequestID(ClearRequestid);
    }

    public static void clickAllocationConformArgyle(String code)  {
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setClickconformArgyle();
        customercontext.setVerificationcode(code);
        customercontext.setVerificationcontinue();
        logger.info("Verification Code Entered and clicked Continue");
    }

    public static String keyClockLoginWithClearRequestID(String str) throws InterruptedException {
        Requestid reqid = new Requestid();
        DevTools devtools = ((HasDevTools)getDriver()).getDevTools();
        devtools.createSession();
         RequestId[] requestIds = new RequestId[1];
        devtools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devtools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            requestIds[0] = responseReceived.getRequestId();
            if(response.getUrl().equalsIgnoreCase("https://services-uat.paywalletllc.com/api/v1/request"))
            {
                System.out.println("Status Code is" + " " +response.getStatus() + " " + "Response URL is"+ " " +response.getUrl());
                String responsebody = (devtools.send(Network.getResponseBody(requestIds[0])).getBody());
                reqid.setReuqestid(responsebody);
            }
        });
        CC_CustomerCapturePage_Client customercapture = new CC_CustomerCapturePage_Client(getDriver());
        customercapture.setUsername(Username);
        customercapture.setPassword(Password);
        Thread.sleep(2000);
        customercapture.KeyclockclickSubmit();
        logger.info("added UN and password and Key Clock Login done Successfully");
        Thread.sleep(8000);
        String requestBody = reqid.getReuqestid();
        System.out.println("Request Body received outside the lambda method is"+" "+requestBody);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        String ClearRequestID = jsonObject.get("data").getAsJsonObject().get("clearRequestId").getAsString();
        System.out.println(str+" "+ClearRequestID);
        return ClearRequestID;
    }
}