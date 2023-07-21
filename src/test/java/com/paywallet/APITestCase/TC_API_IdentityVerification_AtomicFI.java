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

public class TC_API_IdentityVerification_AtomicFI extends BaseClass {
    String emailid =  "IdentityAPIAtomicFI+"+ randomNum +"@ty2cptgh.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();

    @Test(dataProvider = "IdentityAtomicFI",dataProviderClass = WrapperAPITestData.class)
    public void APIidentityVerificationAtomicFI(String employer,String firstname, String lastname, String lastfourtin, String address , String Zip,
                                     String city,String state,String DOB, String username, String password,
                                     String un, String pass,String NotificationURL, String IdentityURL
    ) throws InterruptedException {

//      Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);

//      Identity Verification
        IdentityVerificationAtomicFI(firstname, lastname, lastfourtin, address, Zip, city, state, DOB, NotificationURL, IdentityURL, requestID, employerid);


//      SDK Login Starts
        SDKLoginFlow(username, password, un, pass);



//        Fetch RequestID
        IdentityFetchRequestIDAtomicFI(requestID);

    }

    private void IdentityFetchRequestIDAtomicFI(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        logger.info(FetchReqBody);
        String FetchStatus = fetchresponse.jsonPath().get("data.identityStatus").toString();
        logger.info(FetchStatus);
        int FetchStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(FetchStatusCode,200);
        Assert.assertEquals(FetchStatus,"PUSHED");
    }

    private void ProfileDataLinkFlow() throws InterruptedException {
        getDriver().get(APIIdentityVerificationurlAtomicFI);
        CC_ProfileDataLinkPage_Client Identity_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(90000);
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

    private void SDKLoginFlow(String username, String password,String un, String pass) throws InterruptedException {
        getDriver().get(APIIdentityVerificationurlAtomicFI);
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
        getDriver().switchTo().frame("atomic-transact-iframe");
        Identity_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Identity_SDKLoginLink.clickSDKcontinue();
        Thread.sleep(2000);
        Identity_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Identity_SDKLoginLink.clickSDKSigninbutton();
        logger.info("SDK Login Done Successfully");
        Thread.sleep(25000);
        CC_ProfileDataLinkPage_Client Identity_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Identity_ProfileDataLink.verifyIdentityInfoDisplayed();
        Identity_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed successfully");
    }

    private void IdentityVerificationAtomicFI(String firstname, String lastname, String lastfourtin, String address, String Zip, String city, String state, String DOB, String NotificationURL, String IdentityURL, String requestID, String employerid) {
        List<String> notificationUrl = List.of(NotificationURL);
        List<String> identityCallbackUrl = List.of(IdentityURL);
        IdentityCallBack identityCallBack = new IdentityCallBack(identityCallbackUrl,notificationUrl);
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
    }

}
