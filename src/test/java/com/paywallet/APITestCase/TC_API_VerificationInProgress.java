package com.paywallet.APITestCase;
import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.IdentityCallBack;
import com.paywallet.POJOClasses.IdentityPayload;
import com.paywallet.POJOClasses.IncomeCallBack;
import com.paywallet.POJOClasses.IncomePayload;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_API_VerificationInProgress extends BaseClass
{
    String emailid =  "SkipSDKLogin+"+ randomNum +"@obzzai2p.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "VerificationInProgress" ,dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void identityVerification(String employer,String firstname, String lastname, String lastfourtin, String address , String Zip,
                                     String city,String state,String DOB,String numberofmonthrequired,
                                     String NotificationURL, String IdentityURL) throws InterruptedException {
//       Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);


//      Identity Verification
        IdentityVerification(firstname, lastname, lastfourtin, address, Zip, city, state, DOB, NotificationURL, IdentityURL, requestID, employerid);


//      Income Verification
        IncomeVerification(firstname, lastname, numberofmonthrequired, NotificationURL, IdentityURL, requestID, employerid);

    }

    private void IncomeVerification(String firstname, String lastname, String numberofmonthrequired, String NotificationURL, String IdentityURL, String requestID, String employerid) {
        List<String> notificationUrl = List.of(NotificationURL);
        List<String> incomeCallbackUrls = List.of(IdentityURL);
        IncomeCallBack incomeCallBack = new IncomeCallBack(incomeCallbackUrls,notificationUrl);
        IncomePayload incomepayload = new IncomePayload(employerid, firstname, lastname,"",emailid,mobile,incomeCallBack , numberofmonthrequired);
        RequestSpecification IncomeVerification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(incomepayload);
        logger.info("Income verification Request is sent");
        Response IncomeVer = IncomeVerification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/income-verification");
        String IncomeVerresponse = IncomeVer.getBody().asString();
        logger.info("Income verification Responce is received");
        System.out.println(IncomeVerresponse);
        int incomestatuscode = IncomeVer.getStatusCode();
        Assert.assertEquals(incomestatuscode,400);
        logger.info("Status code is successfully validated");
        String incomemessage = IncomeVer.jsonPath().get("message").toString();
        Assert.assertEquals(incomemessage , "Request [IDENTITY_VERIFICATION] in progress for the requestId Please complete the request and re-try again");
    }

    private void IdentityVerification(String firstname, String lastname, String lastfourtin, String address, String Zip, String city, String state, String DOB, String NotificationURL, String IdentityURL, String requestID, String employerid) {
        List<String> notificationUrls = List.of(NotificationURL);
        List<String> identityCallbackUrls = List.of(IdentityURL);
        IdentityCallBack identityCallBack = new IdentityCallBack(identityCallbackUrls,notificationUrls);
        IdentityPayload identityPayload = new IdentityPayload(employerid, firstname, lastname, lastfourtin,emailid,mobile, identityCallBack, address,"", Zip, city, state, DOB);
        RequestSpecification IdentityVerification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(identityPayload);
        logger.info("Identity verification Request is sent");
        Response IdentityVer = IdentityVerification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/identity-verification");
        String IdentityVerresponse = IdentityVer.getBody().asString();
        logger.info("Identity verification Responce is received");
        System.out.println(IdentityVerresponse);
        int statuscode = IdentityVer.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
    }

}
