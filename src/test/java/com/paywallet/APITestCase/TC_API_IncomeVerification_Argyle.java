package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.IncomeCallBack;
import com.paywallet.POJOClasses.IncomePayload;
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

public class TC_API_IncomeVerification_Argyle extends BaseClass {
    String emailid =  "IncomeverificationAPIArgyle+"+ randomNum +"@vexse4el.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "IncomeArgyle",dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void APIincomeVerificationArgyle(String employer,String firstname, String lastname, String numberofmonthrequired, String username, String password,
                                     String un, String pass, String ver,String NotificationURL, String IncomeURL) throws InterruptedException {


//      Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);

//      Income Verification
        IncomeVerificationArgyle(firstname, lastname, numberofmonthrequired, NotificationURL, IncomeURL, requestID, employerid);

//      SDK Login Starts
        SDkLoginFlowArgyle(username, password, un, pass, ver);


//        Fetch Request ID
        FetchRequestIDIncomeArgyle(requestID);
    }

    private void FetchRequestIDIncomeArgyle(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        System.out.println(FetchReqBody);
        String FetchStatus = fetchresponse.jsonPath().get("data.incomeStatus").toString();
        System.out.println(FetchStatus);
        int FetchStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(FetchStatusCode,200);
        Assert.assertEquals(FetchStatus,"PUSHED");
    }

    private void ProfiledataLinkFlowArgyle() throws InterruptedException {
        getDriver().get(APIIncomeVerificationurlArgyle);
        CC_ProfileDataLinkPage_Client Income_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(120000);
        Income_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Income_ProfileDataLink.selectProfileDataEmail();
        logger.info("Profile data link Email is received and email is selected");
        Income_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Clicked on profile data link successfully");
        UtilityClass.ClientOTP();
        logger.info("OTP Entered successfully");
        Income_ProfileDataLink.clickProfiledataVerify();
        Income_ProfileDataLink.verifySalaryInfodisp();
        Income_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed succesfully");
    }

    private void SDkLoginFlowArgyle(String username, String password, String un, String pass, String ver) throws InterruptedException {
        getDriver().get(APIIncomeVerificationurlArgyle);
        CC_SDKLoginPage_Client Income_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Income_SDKLoginLink.setmailosaurEmail(username);
        Income_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Income_SDKLoginLink.setsetmailosaurPassword(password);
        Income_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur login done Successfully");
        Thread.sleep(8000);
        Income_SDKLoginLink.setRefresh();
        Thread.sleep(2000);
        Income_SDKLoginLink.selectSDKEmail();
        logger.info("SDK Email is received and email is selected");
        Income_SDKLoginLink.clickOnSDKLink();
        logger.info("Clicked on SDK Email");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Income_SDKLoginLink.setSDKUsernameArgyle(un);
        Income_SDKLoginLink.setSDKPasswordArgyle(pass);
        Income_SDKLoginLink.clickConformbutton();
        logger.info("SDK Login Done Successfully");
        Income_SDKLoginLink.setVerificationcode(ver);
        Income_SDKLoginLink.clicksdkVerify();
        Thread.sleep(10000);
        CC_ProfileDataLinkPage_Client Income_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Income_ProfileDataLink.verifySalaryInfodisp();
        Income_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed succesfully");
    }

    private void IncomeVerificationArgyle(String firstname, String lastname, String numberofmonthrequired, String NotificationURL, String IncomeURL, String requestID, String employerid) throws InterruptedException {
        List<String> notificationUrls = List.of(NotificationURL);
        List<String> incomeCallbackUrls = List.of(IncomeURL);
        IncomeCallBack incomeCallBack = new IncomeCallBack(incomeCallbackUrls,notificationUrls);
        IncomePayload incomepayload = new IncomePayload(employerid, firstname, lastname,"",emailid,mobile,incomeCallBack , numberofmonthrequired);
        RequestSpecification IncomeVerification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(incomepayload);
        logger.info("Income verification Request is sent");
        Response IncomeVer = IncomeVerification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/income-verification");
        String IncomeVerresponse = IncomeVer.getBody().asString();
        logger.info("Income verification Responce is received");
        System.out.println(IncomeVerresponse);
        int statuscode = IncomeVer.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
        Thread.sleep(5000);
    }
}
