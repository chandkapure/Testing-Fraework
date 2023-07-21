package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.API_RequestIDclass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CC_API_EmploymentVerification_AtomicFI extends BaseClass {

    String emailid =  "TC_CC_API_EmploymentVerification_AtomicFI"+randomNum+"@lt4kuvk3.mailosaur.net";

    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "CCAPIAtomicFIData",dataProviderClass = CustomDataProvider.class)
    public void EmploymentAPI(String provider, String context, String firstName, String middleName, String lastName,
                          String numberofInstalment, String installmentAmount, String loanAmount, String repaymentFrequency, String firstDateofPayment, String ClientContextReference,
                          String addressLine1, String addressLine2, String city, String state, String zip, String dateofBirth, String last4tin,
                          String identitycallback, String employmentcallback, String incomecallback, String allocationcallback, String insufficientfundcallback,
                          String notificationUrls, String affordabilityCallback, String username, String password, String un, String pass,
                              String Client1,String Client2) throws InterruptedException {


        System.out.println("TC_CC_API_EmploymentVerification_AtomicFI email id is "+emailid);
        System.out.println("TC_CC_API_EmploymentVerification_AtomicFI phone number is "+mobile);
//  Employer Type Ahead
        String employerID = methodforAPI.employerTypeAhead(provider);

        String profiles = "EMPLOYMENT_VERIFICATION";

//  RegisterAPI
        API_RequestIDclass requestid  = methodforAPI.RegisterAPI(employerID, profiles, context, firstName, middleName, lastName, emailid, mobile,
                numberofInstalment, installmentAmount, loanAmount, repaymentFrequency, firstDateofPayment, ClientContextReference,
                addressLine1, addressLine2, city, state, zip, dateofBirth, last4tin, IdentityURL,EmploymentURL,IncomeURL,DepositAllocationURL,
                NotificationURL,NotificationURL,AffordabilityURL);

        String RequestID = requestid.getRequestID();
        String ClearRequestID = requestid.getClearRequestID();
//  SDK Login Starts

        getDriver().get(APIEmploymentVerificationurlAtomicFI);
        UtilityClass.mailosaurLoginandandEmailSelection(username,password);
        UtilityClass.ClientOTP();
        UtilityClass.AtomicFISDKLoginFlow(un,pass);
        Thread.sleep(25000);
        CC_ProfileDataLinkPage_Client Affordebility_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        if(((clientid.startsWith(Client1))  || clientid.startsWith(Client2)))
        {
            Affordebility_ProfileDataLink.verifyEmploymentInfodisp();
            Affordebility_ProfileDataLink.verifyExcelDownload();
            logger.info("Profile data details are displayed successfully");

        }
        else
        {
            CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
            customercontext.setThankYou();
        }

        getDriver().get(Dashboard);
        UtilityClass.keyClockLogin();
        Thread.sleep(2000);
        UtilityClass.compareEmploymentCallbackDashboardData(EmploymentURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);

//    Fetch Request ID
        EmploymentFetchRequestID(RequestID);
    }
    private void EmploymentFetchRequestID(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", CodeConvergenceToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        logger.info("Fetch Request ID Request Sent");
        logger.info(FetchReqBody);
        System.out.println(FetchReqBody);
        logger.info("Fetch RequestID Response Received");
        String FetchStatus = fetchresponse.jsonPath().get("data.employmentStatus").toString();
        logger.info(FetchStatus);
        int FetchIdentityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(FetchIdentityStatusCode,200);
        Assert.assertEquals(FetchStatus,"PUSHED");
    }



}
