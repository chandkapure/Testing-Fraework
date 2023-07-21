package com.paywallet.APITestCase;
import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.*;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_API_VerificationRetry extends BaseClass
{
    String emailid =  "VerificationRetry+"+ randomNum +"@9c2fob6v.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();

    @Test(dataProvider = "VerificationRetryArgyle",dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void RetryidentityVerification(String employer,String firstname, String lastname, String lastfourtin, String address , String Zip,
                                     String city,String state,String DOB, String username, String password,
                                     String un, String pass, String ver,String NotificationURL, String IdentityURL
    ) throws InterruptedException {


//      Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);

//      Identity Verification
        IdentityVerification(firstname, lastname, lastfourtin, address, Zip, city, state, DOB, NotificationURL, IdentityURL, requestID, employerid);


//        Identity Verification Retry
        IdentityVerificationRetry(requestID, employerid);

//      SDK Login Starts
        SDKLoginFlow(username, password, un, pass, ver);


    }

    private void ProfileDataLinkFlow() throws InterruptedException {
        getDriver().get(APIVerificationRetry);
        CC_ProfileDataLinkPage_Client Identity_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(30000);
        Identity_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Identity_ProfileDataLink.selectProfileDataEmail();
        logger.info("Profile data link Email is received and email is selected");
        Identity_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Clicked on profile data link successfully");
        UtilityClass.ClientOTP();
        logger.info("OTP Entered Successfully");
        Identity_ProfileDataLink.clickProfiledataVerify();
        Identity_ProfileDataLink.verifyIdentityInfoDisplayed();
        Identity_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed Successfully");
    }

    private void SDKLoginFlow(String username, String password, String un, String pass, String ver) throws InterruptedException {
        getDriver().get(APIVerificationRetry);
        CC_SDKLoginPage_Client Identity_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Identity_SDKLoginLink.setmailosaurEmail(username);
        Identity_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Identity_SDKLoginLink.setsetmailosaurPassword(password);
        Identity_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur login done Successfully");
        Thread.sleep(8000);
        Identity_SDKLoginLink.setRefresh();
        Thread.sleep(2000);
        Identity_SDKLoginLink.selectSDKEmail();
        logger.info("SDK Email is received and email is selected");
        Identity_SDKLoginLink.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Identity_SDKLoginLink.setSDKUsernameArgyle(un);
        Identity_SDKLoginLink.setSDKPasswordArgyle(pass);
        Identity_SDKLoginLink.clickConformbutton();
        logger.info("SDK Login Done Successfully");
        Identity_SDKLoginLink.setVerificationcode(ver);
        Identity_SDKLoginLink.clicksdkVerify();
        CC_ProfileDataLinkPage_Client Identity_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Identity_ProfileDataLink.verifyIdentityInfoDisplayed();
        Identity_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed Successfully");
    }

    private void IdentityVerificationRetry(String requestID, String employerid) throws InterruptedException {
        IdentityVerificationRetryPayload retryPayload = new IdentityVerificationRetryPayload(employerid,emailid,mobile);
        RequestSpecification Identityretry = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(retryPayload);
        logger.info("Identity verification Request is sent");
        Response retryresponse = Identityretry.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/identity/verification/retry");
        String Identityretryresponse = retryresponse.getBody().asString();
        logger.info("Identity verification Response is received");
        System.out.println(Identityretryresponse);
        int retrystatuscode = retryresponse.getStatusCode();
        Assert.assertEquals(retrystatuscode,200);
        logger.info("Status code is successfully validated");
        Thread.sleep(5000);
    }

    private void IdentityVerification(String firstname, String lastname, String lastfourtin, String address, String Zip, String city, String state, String DOB, String NotificationURL, String IdentityURL, String requestID, String employerid) throws InterruptedException {
        List<String> notificationUrls = List.of(NotificationURL);
        List<String> identityCallbackUrls = List.of(IdentityURL);
        IdentityCallBack identityCallBack = new IdentityCallBack(identityCallbackUrls,notificationUrls);
        IdentityPayload identityPayload = new IdentityPayload(employerid, firstname, lastname, lastfourtin,emailid,mobile, identityCallBack, address,"", Zip, city, state, DOB);
        RequestSpecification IdentityVerification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(identityPayload);
        logger.info("Identity verification Request is sent");
        Response IdentityVer = IdentityVerification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/identity-verification");
        String IdentityVerresponse = IdentityVer.getBody().asString();
        logger.info("Identity verification Response is received");
        System.out.println(IdentityVerresponse);
        int statuscode = IdentityVer.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
        Thread.sleep(3000);
    }

}
