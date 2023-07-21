package com.paywallet.APITestCase;
import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.IdentityCallBack;
import com.paywallet.POJOClasses.IdentityPayload;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.Utilities.WrapperAPITestData;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_API_IdentityVerification_Argyle extends BaseClass {
    String emailid =  "IdentityAPIArgyle+"+ randomNum +"@s97df8y5.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();

    @Test(dataProvider = "IdentityArgyle",dataProviderClass = WrapperAPITestData.class)
    public void APIidentityVerificationArgyle(String employer,String firstname, String lastname, String lastfourtin, String address , String Zip,
                                     String city,String state,String DOB, String username, String password,
                                     String un, String pass, String ver,String NotificationURL, String IdentityURL
                                     ) throws InterruptedException {

//        Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);


//      Identity Verification
        IdentityVerificationArgyle(firstname, lastname, lastfourtin, address, Zip, city, state, DOB, NotificationURL, IdentityURL, requestID, employerid);

//      SDK Login Starts
        SDKLoginFlow(username, password, un, pass, ver);


//      Fetch RequestID
        IdentityFetchRequestIdArgyle(requestID);


    }

    private void IdentityFetchRequestIdArgyle(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        logger.info(FetchReqBody);
        String FetchStatus = fetchresponse.jsonPath().get("data.identityStatus").toString();
        logger.info(FetchStatus);
        int FetchIdentityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(FetchIdentityStatusCode,200);
        Assert.assertEquals(FetchStatus,"PUSHED");
    }

    private void ProfileDataLinkFlow() throws InterruptedException {
        getDriver().get(APIIdentityVerificationurlArgyle);
        CC_ProfileDataLinkPage_Client Identity_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(120000);
        Identity_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Identity_ProfileDataLink.selectProfileDataEmail();
        logger.info("Profile data link Email is received and email is selected");
        Identity_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Clicked on profile data link successfully");
        UtilityClass.ClientOTP();
        logger.info("OTP Entered successfully");
        Identity_ProfileDataLink.clickProfiledataVerify();
        Identity_ProfileDataLink.verifyIdentityInfoDisplayed();
        Identity_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed successfully");
    }

    private void SDKLoginFlow(String username, String password, String un, String pass, String ver) throws InterruptedException {
        getDriver().get(APIIdentityVerificationurlArgyle);
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
        logger.info("Profile data details are displayed successfully");

    }

    private void IdentityVerificationArgyle(String firstname, String lastname, String lastfourtin, String address, String Zip, String city, String state, String DOB, String NotificationURL, String IdentityURL, String requestID, String employerid) throws InterruptedException {
        List<String> notificationUrls = List.of(NotificationURL);
        List<String> identityCallbackUrls = List.of(IdentityURL);
        IdentityCallBack identityCallBack = new IdentityCallBack(identityCallbackUrls,notificationUrls);
        IdentityPayload identityPayload = new IdentityPayload(employerid, firstname, lastname, lastfourtin,emailid,mobile, identityCallBack, address,"", Zip, city, state, DOB);
        RequestSpecification IdentityVerification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(identityPayload);
        logger.info("Identity verification Request is sent");
        Response IdentityVer = IdentityVerification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/identity-verification");
        String IdentityVerresponse = IdentityVer.getBody().asString();
        logger.info("Identity verification Response is received");
        logger.info(IdentityVerresponse);
        int statuscode = IdentityVer.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
        Thread.sleep(5000);
    }
}
