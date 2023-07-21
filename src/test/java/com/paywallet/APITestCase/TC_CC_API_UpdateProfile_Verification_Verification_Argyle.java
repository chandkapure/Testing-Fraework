package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.API_RequestIDclass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CC_API_UpdateProfile_Verification_Verification_Argyle extends BaseClass {
    String emailid =  "TC_CC_API_UpdateProfile_Verification_Verification_Argyle"+ randomNum +"@mrldxdby.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "CCAPIArgyleData",dataProviderClass = CustomDataProvider.class)
    public void UpdateProfileAPI(String provider, String context, String firstName, String middleName, String lastName,
                                 String numberofInstalment, String installmentAmount, String loanAmount, String repaymentFrequency, String firstDateofPayment, String ClientContextReference,
                                 String addressLine1, String addressLine2, String city, String state, String zip, String dateofBirth, String last4tin,
                                 String identitycallback, String employmentcallback, String incomecallback, String allocationcallback, String insufficientfundcallback,
                                 String notificationUrls, String affordabilityCallback, String username, String password, String un, String pass,String code,
                                 String Client1 ,String Client2) throws InterruptedException {

        System.out.println("TC_CC_API_UpdateProfile_Verification_Verification_Argyle email id is "+emailid);
        System.out.println("TC_CC_API_UpdateProfile_Verification_Verification_Argyle phone number is "+mobile);
//  Employer Type Ahead
        String employerID = methodforAPI.employerTypeAhead(provider);

        String profiles = "AFFORDABILITY";

//  RegisterAPI
        API_RequestIDclass requestid = methodforAPI.RegisterAPI(employerID, profiles, context, firstName, middleName, lastName, emailid, mobile,
                numberofInstalment, installmentAmount, loanAmount, repaymentFrequency, firstDateofPayment, ClientContextReference,
                addressLine1, addressLine2, city, state, zip, dateofBirth, last4tin, IdentityURL,EmploymentURL,IncomeURL,DepositAllocationURL,
                NotificationURL,NotificationURL,AffordabilityURL);

        String RequestID = requestid.getRequestID();
        String ClearRequestID = requestid.getClearRequestID();

//  SDK Login Starts

        getDriver().get(APILCOSkipMultipleLoginArgyle);
        UtilityClass.mailosaurLoginandandEmailSelection(username,password);
        UtilityClass.ClientOTP();
        UtilityClass.ArgyleSDKLoginFlow(un,pass,code);
        Thread.sleep(25000);
        CC_ProfileDataLinkPage_Client Affordebility_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        if(clientid.startsWith(Client1) || clientid.startsWith(Client2)) {
            Affordebility_ProfileDataLink.verifyAffordablityInfodisp();
            Affordebility_ProfileDataLink.verifyExcelDownload();
            logger.info("Profile data details are displayed successfully");

        } else {
            CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
            customercontext.setThankYou();
        }

        getDriver().get(Dashboard);
        UtilityClass.keyClockLogin();
        Thread.sleep(2000);
        UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestID);
        UtilityClass.metricReportValidation(ClearRequestID);

//    Fetch Request ID
        AffordabilityFetchRequestID(RequestID);



//    UpdateProfile API Flow
        String profiless = "AFFORDABILITY";

        methodforAPI.UpdateProfileAPI(RequestID,employerID,profiless, context, numberofInstalment, installmentAmount, loanAmount, repaymentFrequency, firstDateofPayment, ClientContextReference,
                addressLine1, addressLine2, city, state, zip, dateofBirth, last4tin, IdentityURL,EmploymentURL,IncomeURL,DepositAllocationURL,
                NotificationURL,NotificationURL,AffordabilityURL);

//   SDK Login Flow

        getDriver().get(APILCOSkipMultipleLoginArgyle);
        CC_SDKLoginPage_Client Affordebility_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Thread.sleep(10000);
        Affordebility_SDKLoginLink.setRefresh();
        Thread.sleep(2000);
        Affordebility_SDKLoginLink.selectSDKEmail();
        logger.info("SDK Email is received and email is selected");
        Affordebility_SDKLoginLink.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
        UtilityClass.ClientOTP();
        if(clientid.startsWith(Client1) || clientid.startsWith(Client2)) {
            Affordebility_ProfileDataLink.verifyAffordablityInfodisp();
            Affordebility_ProfileDataLink.verifyExcelDownload();
            logger.info("Profile data details are displayed successfully");

        } else {
            CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
            customercontext.setThankYou();
        }

        UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);




    }
    private void AffordabilityFetchRequestID(String requestID) throws InterruptedException {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", CodeConvergenceToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        logger.info("Fetch Request ID Request Sent");
        logger.info(FetchReqBody);
        System.out.println(FetchReqBody);
        logger.info("Fetch RequestID Response Received");
        Thread.sleep(3000);
        String FetchStatus = fetchresponse.jsonPath().get("data.affordability").toString();
        logger.info(FetchStatus);
        int FetchIdentityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(FetchIdentityStatusCode,200);
        Assert.assertEquals(FetchStatus,"PUSHED");
    }


}
