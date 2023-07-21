package com.paywallet.UItestCase;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.EmploymentDashboard;
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

public class TC_DashBoard_Callback_Employment_AtomicFI extends BaseClass {
    String emailid = "employmentAtomicFI" + randomNum + "@paywalletllc.com";
    CC_CallBack_DashBoard_Page dashBoard;

    String EmpAto_ClearRequestid;
    @Test(priority = 1, dataProvider = "AtomicFITestData" ,dataProviderClass = CustomDataProvider.class)
    public void employmentVerification(String firstname, String lastname, String address, String city, String state, String zipcode, String DOB, String fourtin,
                                       String employer, String numberofinstalment, String Installmentamout, String firstpaymentdate, String howoftenyougetpaid,
                                       String username, String password,String Client1,String Client2,String Client3) throws InterruptedException {

        dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
        getDriver().get(CustomerContextURL);
        UtilityClass.keyClockLogin();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setCellphone(mobile);
        customercontext.setEmail(emailid);
        UtilityClass.LoanApplicationForm(firstname, lastname, address, city, state, zipcode, DOB , fourtin, employer, numberofinstalment, Installmentamout, firstpaymentdate, howoftenyougetpaid);
        customercontext.setEmploymentcheckbox();
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Entered All the required details in the Homepage and clicked Submit");
        UtilityClass.selectEmployerCustomer();
        EmpAto_ClearRequestid = customercontext.getclearRequestIdtext();
        logger.info("Request id is : " + EmpAto_ClearRequestid);
        if((Username.startsWith(Client1)) || (Username.startsWith(Client2) || Username.startsWith(Client3)))
        {
            UtilityClass.CustomerOTP();
            customercontext.setVerify();
            logger.info("OTP Entered Successfully and Verified");
            UtilityClass.AtomicFISDKLoginFlow(username, password);
            Thread.sleep(30000);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.setEmployementinfo();
            logger.info("Employment Details Displayed Successfully");
            customercontext.DownloadExcel();
            logger.info("Excel Download was Success");

        }
        else
        {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            UtilityClass.AtomicFISDKLoginFlow(username, password);
            Thread.sleep(30000);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
            customercontext.setThankYou();
        }

        getDriver().get(EmploymentURLPath);
        System.out.println("Call back  URL Path for Employment AtomicFI : " + EmploymentURLPath);
        List<WebElement> elementss = dashBoard.listofWebElement();
        String strr = null;
        for (int i = 0; i < elementss.size(); i++) {
            elementss.get(i).click();
            String CallbackJson = dashBoard.getJsonResponse();
            logger.info("Callback Json Data received Successfully");
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(CallbackJson, JsonObject.class);
            String requestId = jsonObject.get("requestId").getAsString();
            if(requestId.equals(EmpAto_ClearRequestid))
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
        String Callback_Employer = jsonObject.get("employmentData").getAsJsonObject().get("employer").getAsString();
        String Callback_Firstname = jsonObject.get("employmentData").getAsJsonObject().get("firstName").getAsString();
        String Callback_Lastname = jsonObject.get("employmentData").getAsJsonObject().get("lastName").getAsString();
        String Callback_Middlename = jsonObject.get("employmentData").getAsJsonObject().get("middleName").getAsString();
        String Callback_Last4tin = jsonObject.get("employmentData").getAsJsonObject().get("last4Tin").getAsString();
        String Callback_Hiredate = jsonObject.get("employmentData").getAsJsonObject().get("hireDate").getAsString();
        String Callback_TypeofEmployemnt = jsonObject.get("employmentData").getAsJsonObject().get("typeOfEmployment").getAsString();
        String Callback_EmploymentCategory = jsonObject.get("employmentData").getAsJsonObject().get("employmentCategory").isJsonObject()? jsonObject.get("employmentData").getAsJsonObject().get("employmentCategory").getAsString():null;
        String Callback_Designation = jsonObject.get("employmentData").getAsJsonObject().get("designation").getAsString();
        String Callback_TerminationDate = jsonObject.get("employmentData").getAsJsonObject().get("terminationDate").isJsonNull() ? null: jsonObject.get("employmentData").getAsJsonObject().get("terminationDate").getAsString();

        getDriver().get(Dashboard+"/requests");
        Thread.sleep(6000);
        dashBoard.searchForRequestIDAndSelectTheRequest(EmpAto_ClearRequestid);
        List<WebElement> elements = getDriver().findElements(By.xpath("//h4"));
        Thread.sleep(5000);
        EmploymentDashboard Empdashboard = new EmploymentDashboard();
        for (int i = 0; i < elements.size(); i++) {
            WebElement ele = null;
            String txt = null;
            switch (i) {
                case 0:
                    ele = elements.get(0);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setRequestID(txt);
                    break;
                case 1:
                    ele = elements.get(1);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setEmployer(txt);
                    break;
                case 2:
                    ele = elements.get(2);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setLender(txt);
                    break;
                case 3:
                    ele = elements.get(3);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setFirstname(txt);
                    break;
                case 4:
                    ele = elements.get(4);
                    txt = ele != null  ? ele.getText() : null;
                    Empdashboard.setMiddlename(txt);
                    break;
                case 5:
                    ele = elements.get(5);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setLastname(txt);
                    break;
                case 6:
                    ele = elements.get(6);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setHiredate(txt);
                    break;
                case 7:
                    ele = elements.get(7);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setTypeofemployment(txt);
                    break;
                case 8:
                    ele = elements.get(8);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
                    Empdashboard.setEmploymentCategory(txt);
                    break;
                case 9:
                    ele = elements.get(9);
                    txt = ele != null  ? ele.getText() : null;
                    Empdashboard.setDesignation(txt);
                    break;
                case 10:
                    ele = elements.get(10);
                    txt = ele != null  ? ele.getText() : null;
                    Empdashboard.setLast4tin(txt);
                    break;
                case 11:
                    ele = elements.get(11);
                    txt = ele != null  ? (ele.getText().isEmpty() ? null: ele.getText()) : null;
//                    txt = ele.getText();
                    Empdashboard.setTerminationDate(txt);
                    break;
            }
        }

        String DashboardRequestID = Empdashboard.getRequestID();
        String DashboardEmployer = Empdashboard.getEmployer();
        String DashFirstname = Empdashboard.getFirstname();
        String DashLastnaem = Empdashboard.getLastname();
        String DashMiddlename = Empdashboard.getMiddlename();
        String DashLast4tin = Empdashboard.getLast4tin();
        String DashHiredate = Empdashboard.getHiredate();
        String DashTypeofEmployment = Empdashboard.getTypeofemployment();
        String DashEmploymentCatogery = Empdashboard.getEmploymentCategory();
        String DashDesignation = Empdashboard.getDesignation();
        String DashTerminationdate = Empdashboard.getTerminationDate();

        Assert.assertEquals(Callback_requestId,DashboardRequestID);
        Assert.assertEquals(Callback_Employer,DashboardEmployer);
        Assert.assertEquals(Callback_Firstname,DashFirstname);
        Assert.assertEquals(Callback_Lastname,DashLastnaem);
        Assert.assertEquals(Callback_Middlename,DashMiddlename);
        Assert.assertEquals(Callback_Last4tin,DashLast4tin);
        Assert.assertEquals(Callback_Hiredate,DashHiredate);
        Assert.assertEquals(Callback_TypeofEmployemnt,DashTypeofEmployment);
        System.out.println("Actual Value : " + Callback_EmploymentCategory);
        System.out.println("Expected Vale: " + DashEmploymentCatogery);
        Assert.assertEquals(Callback_EmploymentCategory,DashEmploymentCatogery);
        Assert.assertEquals(Callback_Designation,DashDesignation);
        Assert.assertEquals(Callback_TerminationDate,DashTerminationdate);


        getDriver().get(Dashboard+"/settings");
        dashBoard.clickAPIMatrixReport();
        dashBoard.clickSearchButton();
        dashBoard.checkrequestID(EmpAto_ClearRequestid);
    }
}
