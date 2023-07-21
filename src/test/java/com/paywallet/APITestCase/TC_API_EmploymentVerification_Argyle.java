package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.EmploymentCallback;
import com.paywallet.POJOClasses.EmploymentPayload;
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

public class TC_API_EmploymentVerification_Argyle extends BaseClass {
    String emailid =  "EmploymentAPIArgyle"+ randomNum +"@twzar5qu.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();





    @Test(dataProvider = "EmploymentArgyle",dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void APIemploymentVerificationArgyle(String employer,String firstname, String lastname, String username, String password,
                                     String un, String pass, String ver,String notificationUrls, String employmentCallbackUrls,
    String Client1,String Client2) throws InterruptedException {



//      Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);

//      EmploymentVerification Verification
        EmploymentVerificationArgyle(firstname, lastname, notificationUrls, employmentCallbackUrls, requestID, employerid);

//      SDK Login Starts
        SDKLoginFlowArgyle(username, password, un, pass, ver,Client1,Client2);



//       Fetch RequestID
        EmploymentFetchRequestID(requestID);

    }

    private void EmploymentFetchRequestID(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        System.out.println(FetchReqBody);
        String AffordebilityStatus = fetchresponse.jsonPath().get("data.employmentStatus").toString();
        System.out.println(AffordebilityStatus);
        int AffordebilityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(AffordebilityStatusCode,200);
        Assert.assertEquals(AffordebilityStatus,"PUSHED");
    }

    private void ProfileDataLinkFlowArgyle() throws InterruptedException {
        getDriver().get(APIEmploymentVerificationurlArgyle);
        CC_ProfileDataLinkPage_Client Employment_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(90000);
        Employment_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Employment_ProfileDataLink.selectProfileDataEmail();
        logger.info("Profile data link Email is received and email is selected");
        Employment_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Clicked on profile data link successfully");
        UtilityClass.ClientOTP();
        logger.info("OTP Entered successfully");
        Employment_ProfileDataLink.clickProfiledataVerify();
        Employment_ProfileDataLink.verifyEmploymentInfodisp();
        Employment_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed succesfully");
    }

    private void SDKLoginFlowArgyle(String username, String password, String un, String pass, String ver,String Client1,String Client2) throws InterruptedException {
        getDriver().get(APIEmploymentVerificationurlArgyle);
        CC_SDKLoginPage_Client Employment_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Employment_SDKLoginLink.setmailosaurEmail(username);
        Employment_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Employment_SDKLoginLink.setsetmailosaurPassword(password);
        Employment_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur login done Successfully");
        Thread.sleep(8000);
        Employment_SDKLoginLink.setRefresh();
        Thread.sleep(2000);
        Employment_SDKLoginLink.selectSDKEmail();
        logger.info("SDK Email is received and email is selected");
        Employment_SDKLoginLink.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Employment_SDKLoginLink.setSDKUsernameArgyle(un);
        Employment_SDKLoginLink.setSDKPasswordArgyle(pass);
        Employment_SDKLoginLink.clickConformbutton();
        logger.info("SDK Login Done Successfully");
        Employment_SDKLoginLink.setVerificationcode(ver);
        Employment_SDKLoginLink.clicksdkVerify();
        Thread.sleep(10000);
        CC_ProfileDataLinkPage_Client Employment_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        logger.info("Profile data details are displayed Succesfully");
        if(((clientid.startsWith(Client1))  || clientid.startsWith(Client2)))
        {
            Employment_ProfileDataLink.verifyEmploymentInfodisp();
            Employment_ProfileDataLink.verifyExcelDownload();
            logger.info("Profile data details are displayed successfully");
        }
        else
        {
            CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
            customercontext.setThankYou();
        }
    }

    private void EmploymentVerificationArgyle(String firstname, String lastname, String notificationUrls, String employmentCallbackUrls, String requestID, String employerid) throws InterruptedException {
        List<String> notificationUrl = List.of(notificationUrls);
        List<String> employmentCallbackUrl = List.of(employmentCallbackUrls);
        EmploymentCallback employmentCallBack = new EmploymentCallback(employmentCallbackUrl,notificationUrl);
        EmploymentPayload employmentypayload = new EmploymentPayload(employerid, firstname, lastname,"",emailid,mobile, employmentCallBack);
        RequestSpecification EmploymentVerification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(employmentypayload);
        logger.info("Employemnt verification Request is sent");
        Response EmploymentVer = EmploymentVerification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/employment-verification");
        String EmploymentVerresponse = EmploymentVer.getBody().asString();
        logger.info("Employemnt verification Responce is received");
        System.out.println(EmploymentVerresponse);
        int statuscode = EmploymentVer.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
        Thread.sleep(5000);
    }

}
