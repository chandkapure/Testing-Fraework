package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.API_RequestIDclass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CC_API_PostRestart_Allocation_AtomicFI extends BaseClass {

//    Abandon @Consent screen restart @OTP and then Consent
    String emailid =  "TC_CC_API_PostRestart_Allocation_AtomicFI"+ randomNum +"@gkiho3ow.mailosaur.net";

    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "CCAPIAtomicFIData",dataProviderClass = CustomDataProvider.class)
    public void DepositAllocationAPI(String provider, String context, String firstName, String middleName, String lastName,
                                     String numberofInstalment, String installmentAmount, String loanAmount, String repaymentFrequency, String firstDateofPayment, String ClientContextReference,
                                     String addressLine1, String addressLine2, String city, String state, String zip, String dateofBirth, String last4tin,
                                     String identitycallback, String employmentcallback, String incomecallback, String allocationcallback, String insufficientfundcallback,
                                     String notificationUrls, String affordabilityCallback, String username, String password, String un, String pass,String Client1, String Client2) throws InterruptedException {

        System.out.println("TC_CC_API_PostRestart_Allocation_AtomicFI email id is "+emailid);
        System.out.println("TC_CC_API_PostRestart_Allocation_AtomicFI phone number is "+mobile);
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

        getDriver().get(PostrestartAtomicFI);
        UtilityClass.mailosaurLoginandandEmailSelection(username,password);
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        CC_SDKLoginPage_Client Allocation_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Allocation_SDKLoginLink.DownloadExcelData();
        Thread.sleep(2000);
        methodforAPI.PostRestart(RequestID);
        getDriver().get(PostrestartAtomicFI);
        Thread.sleep(10000);
        Allocation_SDKLoginLink.setRefresh();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.selectSDKEmail();
        logger.info("SDK Email is received and email is selected");
        Allocation_SDKLoginLink.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.DownloadExcelData();
        Allocation_SDKLoginLink.clickAllocationConform();
        logger.info("Clicked on consent Agree");
        UtilityClass.AtomicFISDKLoginFlow(un,pass);
        Allocation_SDKLoginLink.scroll();
        Allocation_SDKLoginLink.clickSDKConform();
        logger.info("Clicked on PayDistribution Conform");
        Thread.sleep(25000);
        Allocation_SDKLoginLink.verifySDKSuccessScreen();
        logger.info("Allocation details Displayed Successfully");

        getDriver().get(Dashboard);
        UtilityClass.keyClockLogin();
        Thread.sleep(2000);
        UtilityClass.compareAllocationCallbackDashboardData(DepositAllocationURLPath,ClearRequestID);
        UtilityClass.metricReportValidation(ClearRequestID);


//    Fetch Request ID
        DepositAllocationFetchRequestID(RequestID);
    }

    private void DepositAllocationFetchRequestID(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
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
