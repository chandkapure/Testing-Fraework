package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.API_RequestIDclass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CC_API_Allocation_Update_Cancel_Argyle extends BaseClass {

    String emailid =  "TC_CC_API_Allocation_Update_Cancel_Argyle"+ randomNum +"@jjxnjnbz.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "CCAPIArgyleData",dataProviderClass = CustomDataProvider.class)
    public void AllocationUpdateCancelAPI(String provider, String context, String firstName, String middleName, String lastName,
                                     String numberofInstalment, String installmentAmount, String loanAmount, String repaymentFrequency, String firstDateofPayment, String ClientContextReference,
                                     String addressLine1, String addressLine2, String city, String state, String zip, String dateofBirth, String last4tin,
                                     String identitycallback, String employmentcallback, String incomecallback, String allocationcallback, String insufficientfundcallback,
                                     String notificationUrls, String affordabilityCallback, String username, String password, String un, String pass,String code,String Client1, String Client2) throws InterruptedException {

        System.out.println("TC_CC_API_Allocation_Update_Cancel_Argyle email id is "+emailid);
        System.out.println("TC_CC_API_Allocation_Update_Cancel_Argyle phone number is "+mobile);
//  Employer Type Ahead
        String employerID = methodforAPI.employerTypeAhead(provider);

        String profiles = "DEPOSIT_ALLOCATION";

//  RegisterAPI
        API_RequestIDclass requestid = methodforAPI.RegisterAPI(employerID, profiles, context, firstName, middleName, lastName, emailid, mobile,
                numberofInstalment, installmentAmount, loanAmount, repaymentFrequency, firstDateofPayment, ClientContextReference,
                addressLine1, addressLine2, city, state, zip, dateofBirth, last4tin, IdentityURL,EmploymentURL,IncomeURL,DepositAllocationURL,
                NotificationURL,NotificationURL,AffordabilityURL);

        String RequestID = requestid.getRequestID();
        String ClearRequestID = requestid.getClearRequestID();

//  SDK Login Starts

        AllocationSDKLogin(username, password, un, pass,code);

        getDriver().get(Dashboard);
        UtilityClass.keyClockLogin();
        Thread.sleep(2000);
        UtilityClass.compareAllocationCallbackDashboardData(DepositAllocationURLPath,ClearRequestID);
        UtilityClass.metricReportValidation(ClearRequestID);

//    Fetch Request ID
        DepositAllocationFetchRequestID(RequestID);


//        Update Profile
        String revisedAllocation = "100.69";
        String revisednumberofInstallment = "2";
        String profiless = "UPDATE_ALLOCATION";
        methodforAPI.UpdateAPI(profiless,revisedAllocation,revisednumberofInstallment,RequestID);
        UpdateSDKLogin(code);
        UtilityClass.compareAllocationCallbackUpdateDashboardData(DepositAllocationURLPath,ClearRequestID);
        UtilityClass.metricReportValidation(ClearRequestID);


//        Cancel Profile
        String profilesss = "CANCEL_ALLOCATION";
        methodforAPI.CancelAPI(profilesss,RequestID);
        CancelSDKLogin(code);
        UtilityClass.compareAllocationCallbackCancelDashboardData(DepositAllocationURLPath,ClearRequestID);
        UtilityClass.metricReportValidation(ClearRequestID);


    }

    private void AllocationSDKLogin(String username, String password, String un, String pass,String code) throws InterruptedException {
        getDriver().get(APIUpdateCancelArgyle);
        UtilityClass.mailosaurLoginandandEmailSelection(username, password);
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        CC_SDKLoginPage_Client Allocation_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Allocation_SDKLoginLink.DownloadExcelData();
        Allocation_SDKLoginLink.clickAllocationConform();
        logger.info("Clicked on consent Conform");
        UtilityClass.ArgyleSDKLoginFlow(un, pass,code);
        Allocation_SDKLoginLink.scroll();
        Allocation_SDKLoginLink.setConfirm();
        logger.info("Clicked on PayDistribution Conform");
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setVerificationcode(code);
        Thread.sleep(2000);
        customercontext.setVerificationcontinue();
        Allocation_SDKLoginLink.verifySDKSuccessScreen();
        logger.info("Allocation details Displayed Successfully");
    }

    private void CancelSDKLogin(String code) throws InterruptedException {
        getDriver().get(APIUpdateCancelArgyle);
        CC_SDKLoginPage_Client Cancel = new CC_SDKLoginPage_Client(getDriver());
        Thread.sleep(10000);
        Cancel.setRefresh();
        Thread.sleep(2000);
        Cancel.SelectCancelemail();
        logger.info("SDK Email is received and email is selected");
        Cancel.scroll();
        Cancel.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
        UtilityClass.ClientOTP();
        Cancel.DownloadExcelData();
        Cancel.clickConform();
        Thread.sleep(15000);
        Cancel.clickUpdateDirectDeposit();
        Cancel.setConfirm();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setVerificationcode(code);
        Thread.sleep(2000);
        customercontext.setVerificationcontinue();
        Cancel.verifySDKSuccessScreen();
        logger.info("Success Screen Validated Successfully");
    }

    private void UpdateSDKLogin(String code) throws InterruptedException {
        CC_SDKLoginPage_Client Update = new CC_SDKLoginPage_Client(getDriver());
        getDriver().get(APIUpdateCancelArgyle);
        Thread.sleep(4000);
        Update.setRefresh();
        Thread.sleep(2000);
        Update.selectSDKEmail();
        logger.info("SDK Email is received and email is selected");
        Update.scroll();
        Update.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
        UtilityClass.ClientOTP();
        Update.DownloadExcelData();
        Update.clickAllocationConform();
        logger.info("Clicked on Consent Conform");
        Thread.sleep(10000);
        Update.scroll();
        Update.clickUpdateDirectDeposit();
        Update.setConfirm();
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        customercontext.setVerificationcode(code);
        Thread.sleep(2000);
        customercontext.setVerificationcontinue();
        Update.verifySDKSuccessScreen();
        logger.info("Success Screen Validated Successfully");
    }

    private void DepositAllocationFetchRequestID(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", CodeConvergenceToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        logger.info("Fetch Request ID Request Sent");
        logger.info(FetchReqBody);
        System.out.println(FetchReqBody);
        logger.info("Fetch RequestID Response Received");
        String FetchStatus = fetchresponse.jsonPath().get("data.allocationStatus").toString();
        logger.info(FetchStatus);
        int FetchIdentityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(FetchIdentityStatusCode,200);
        Assert.assertEquals(FetchStatus,"ACTIVE");
    }


}
