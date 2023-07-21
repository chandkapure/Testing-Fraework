package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.IncomeCallBack;
import com.paywallet.POJOClasses.IncomePayload;
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

public class TC_API_IncomeVerification_AtomicFI extends BaseClass {

    String emailid =  "IncomeverificationAPIAtomicFI+"+ randomNum +"@w3nau2hy.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "IncomeAtomicFI",dataProviderClass = WrapperAPITestData.class)
    public void APIincomeVerificationAtomicFI(String employer,String firstname, String lastname, String numberofmonthrequired, String username, String password,
                                     String un, String pass,String NotificationURL, String IdentityURL) throws InterruptedException {


        //      Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);

//      Income Verification
        IncomeVerificationAtomicFI(firstname, lastname, numberofmonthrequired, NotificationURL, IdentityURL, requestID, employerid);

//      SDK Login Starts
        SDKLoginFlow(username, password, un, pass);



//      Fetch Request ID
        IncomeFetchRequestIDAtomicFI(requestID);

    }

    private void IncomeFetchRequestIDAtomicFI(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        System.out.println(FetchReqBody);
        String AffordebilityStatus = fetchresponse.jsonPath().get("data.incomeStatus").toString();
        System.out.println(AffordebilityStatus);
        int AffordebilityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(AffordebilityStatusCode,200);
        Assert.assertEquals(AffordebilityStatus,"PUSHED");
    }

    private void ProfileDataLinkFlow() throws InterruptedException {
        getDriver().get(APIIncomeVerificationurlAtomicFI);
        CC_ProfileDataLinkPage_Client Income_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(90000);
        Income_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Income_ProfileDataLink.selectProfileDataEmail();
        logger.info("Profile data link Email is received and email is selected");
        Income_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Clicked on profile data link successfully");
        UtilityClass.ClientOTP();
        logger.info("OTP Entered succesfully");
        Income_ProfileDataLink.clickProfiledataVerify();
        Income_ProfileDataLink.verifySalaryInfodisp();
        Income_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed succesfully");
    }

    private void SDKLoginFlow(String username, String password, String un, String pass) throws InterruptedException {
        getDriver().get(APIIncomeVerificationurlAtomicFI);
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
        getDriver().switchTo().frame("atomic-transact-iframe");
        Income_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Thread.sleep(3000);
        Income_SDKLoginLink.clickSDKcontinue();
        Thread.sleep(2000);
        Income_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Thread.sleep(3000);
        Income_SDKLoginLink.clickSDKSigninbutton();
        logger.info("SDK Login Done Successfully");
        Thread.sleep(25000);
        CC_ProfileDataLinkPage_Client Income_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Income_ProfileDataLink.verifySalaryInfodisp();
        Income_ProfileDataLink.verifyExcelDownload();
        logger.info("Profile data details are displayed succesfully");
    }

    private void IncomeVerificationAtomicFI(String firstname, String lastname, String numberofmonthrequired, String NotificationURL, String IdentityURL, String requestID, String employerid) throws InterruptedException {
        List<String> notificationUrls = List.of(NotificationURL);
        List<String> incomeCallbackUrls = List.of(IdentityURL);
        IncomeCallBack incomecallback= new IncomeCallBack(incomeCallbackUrls,notificationUrls);
        IncomePayload incomepayload = new IncomePayload(employerid, firstname, lastname,"",emailid,mobile, incomecallback, numberofmonthrequired);
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
