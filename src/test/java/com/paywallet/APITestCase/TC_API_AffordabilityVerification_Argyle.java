package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.AffordebilityCallBack;
import com.paywallet.POJOClasses.AffordebilityPayload;
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

import java.util.List;

public class TC_API_AffordabilityVerification_Argyle extends BaseClass {

    String emailid =  "AffordebilityAPIArgyle"+ randomNum +"@vu6n4okz.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "AffordebilityArgyle",dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void APIaffordabilityVerificationArgyle(String employer,String firstname, String lastname, String username, String password, String un, String pass,
                                                   String ver,String NotificationURL, String AffordebilityURL, String Client1, String Client2) throws InterruptedException {

//      Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);


//      Affordebility Verification
        AffoedabilityVerificationArgyle(firstname, lastname, NotificationURL, AffordebilityURL, requestID, employerid);

//      SDK Login Starts
        SDKLoginFLow(username, password, un, pass, ver, Client1, Client2);


//     Fetch RequestID
        AffordabilityFetchRequestID(requestID);

    }

    private void AffordabilityFetchRequestID(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        System.out.println(FetchReqBody);
        String AffordebilityStatus = fetchresponse.jsonPath().get("data.affordability").toString();
        System.out.println(AffordebilityStatus);
        int AffordebilityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(AffordebilityStatusCode,200);
        Assert.assertEquals(AffordebilityStatus,"PUSHED");
    }

    private void ProfileDataLinkFlow() throws InterruptedException {
        getDriver().get(APIAffordebilityVerificationurlArgyle);
        CC_ProfileDataLinkPage_Client Affordebility_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(90000);
        Affordebility_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Affordebility_ProfileDataLink.selectProfileDataEmail();
        logger.info("Profile data link Email is received and email is selected");
        Affordebility_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Clicked on profile data link successfully");
        UtilityClass.ClientOTP();
        logger.info("OTP Entered successfully");
        Affordebility_ProfileDataLink.clickProfiledataVerify();
        Affordebility_ProfileDataLink.verifyAffordablityInfodisp();
        Affordebility_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed successfully");
    }

    private void SDKLoginFLow(String username, String password, String un, String pass, String ver,String Client1, String Client2) throws InterruptedException {
        getDriver().get(APIAffordebilityVerificationurlArgyle);
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
        UtilityClass.ClientOTP();
        logger.info("OTP Entered successfully");
        Thread.sleep(5000);
        Affordebility_SDKLoginLink.setSDKUsernameArgyle(un);
        Affordebility_SDKLoginLink.setSDKPasswordArgyle(pass);
        Affordebility_SDKLoginLink.clickConformbutton();
        logger.info("SDK Login Done Successfully");
        Affordebility_SDKLoginLink.setVerificationcode(ver);
        Affordebility_SDKLoginLink.clicksdkVerify();
        Thread.sleep(10000);
        CC_ProfileDataLinkPage_Client Affordebility_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        if(((clientid.startsWith(Client1))  || clientid.startsWith(Client2)))
        {
            Affordebility_ProfileDataLink.verifyAffordablityInfodisp();
            Affordebility_ProfileDataLink.verifyExcelDownload();
            logger.info("Profile data details are displayed successfully");

        }
        else
        {
            CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
            customercontext.setThankYou();
        }
    }

    private void AffoedabilityVerificationArgyle(String firstname, String lastname, String NotificationURL, String AffordebilityURL, String requestID, String employerid) throws InterruptedException {
        List<String> notificationUrls = List.of(NotificationURL);
        List<String> AffordebilityURLs = List.of(AffordebilityURL);
        AffordebilityCallBack affordebilityCallBack = new AffordebilityCallBack(AffordebilityURLs,notificationUrls);
        AffordebilityPayload affordabilitypayload = new AffordebilityPayload(employerid, firstname, lastname,emailid,mobile, affordebilityCallBack);
        RequestSpecification AffordebilityVerification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(affordabilitypayload);
        logger.info("Affordebility verification Request is sent");
        Response AffordebilityVer = AffordebilityVerification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/affordability");
        String AffordebilityVerresponse = AffordebilityVer.getBody().asString();
        logger.info("Affordebility verification Responce is received");
        System.out.println(AffordebilityVerresponse);
        int statuscode = AffordebilityVer.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
        Thread.sleep(5000);
    }
}
