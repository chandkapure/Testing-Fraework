package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.DirectAllocationCallBack;
import com.paywallet.POJOClasses.DirectAllocationPayload;
import com.paywallet.POJOClasses.Updateallocation;
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

public class TC_API_Update_Cancel_Argyle extends BaseClass {

    String emailid = "CancelUpdateArgyle+" + randomNum + "@jjxnjnbz.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();



    @Test(dataProvider = "UpdateCanceArgyle",dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void APIUpdateCancelArgyle(String employer, String firstName, String lastName, String numberofInstalment, String loanamount,
                                              String CCreference, String ACHpull, String AccountVerifiction, String username, String password,
                                              String un, String pass, String Ver ,String NotificationURL, String directallocationURL, String revisedAllocationAmount, String revisedNumberOfInstallment) throws InterruptedException {

//       Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);

//      Direct Allocation
        DirectAllocationUpdateCancel(firstName, lastName, numberofInstalment, loanamount, CCreference, ACHpull, AccountVerifiction, NotificationURL, directallocationURL, requestID, employerid);

//      SDK Login Starts
        SDKLoginFlowCancelUpdate(username, password, un, pass, Ver);


//      Fetch Request ID
        UpdateCancelFetchRequestId(requestID);

//      Update API
        UpdateAPI(revisedAllocationAmount, revisedNumberOfInstallment, requestID);

        SDKLoginFlowUpdate();


//       Cancel API
        CancelAPI(requestID);

        SDKLoginFlowCancel();

    }

    private void SDKLoginFlowCancel() throws InterruptedException {
        getDriver().get(APIUpdateCancelArgyle);
        CC_SDKLoginPage_Client Cancel = new CC_SDKLoginPage_Client(getDriver());
        Thread.sleep(10000);
        Cancel.setRefresh();
        Thread.sleep(2000);
        Cancel.SelectCancelemail();
        Cancel.scroll();
        Cancel.clickOnSDKLink();
        Thread.sleep(5000);
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Cancel.DownloadExcelData();
        Cancel.clickConform();
        Thread.sleep(15000);
        Cancel.clickUpdateDirectDeposit();
        Cancel.setConfirm();
        Cancel.verifySDKSuccessScreen();


//        getDriver().get(APIUpdateCancelArgyle);
//        Thread.sleep(40000);
//        Cancel.setRefresh();
//        Thread.sleep(2000);
//        Cancel.SelectCancelSuccessemail();
//        Cancel.scroll();
    }

    private void CancelAPI(String requestID) throws InterruptedException {
        RequestSpecification cancel = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response cancelresponse = cancel.contentType("application/json; charset=utf-8").delete(APIURL+"/api/v1/deposit/cancel-deposit");
        logger.info("Allocation delete Request is sent");
        String body = cancelresponse.getBody().asString();
        logger.info("Allocation delete Response is received");
        logger.info("Response body is" + body);
        int statuscod = cancelresponse.getStatusCode();
        logger.info("Status code is " + statuscod);
        Assert.assertEquals(statuscod,200);
        logger.info("Status code is Successfully validated");
        Thread.sleep(5000);
    }

    private void SDKLoginFlowUpdate() throws InterruptedException {
        getDriver().get(APIUpdateCancelArgyle);
        CC_SDKLoginPage_Client Update = new CC_SDKLoginPage_Client(getDriver());
        Thread.sleep(15000);
        Update.setRefresh();
        Thread.sleep(1000);
        Update.SelectUpdateemail();
        Update.scroll();
        Update.clickOnSDKLink();
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Update.DownloadExcelData();
        Update.clickAllocationConform();
        Thread.sleep(10000);
        Update.clickUpdateDirectDeposit();
        Update.setConfirm();
        Thread.sleep(25000);
        Update.verifySDKSuccessScreen();

        getDriver().get(APIUpdateCancelArgyle);
        Thread.sleep(45000);
        Update.setRefresh();
        Thread.sleep(1000);
        Update.SelectUpdateemail();
        Update.scroll();
    }

    private void UpdateAPI(String revisedAllocationAmount, String revisedNumberOfInstallment, String requestID) throws InterruptedException {
        Updateallocation upallocation = new Updateallocation(revisedAllocationAmount, revisedNumberOfInstallment);
        RequestSpecification updatebody = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(upallocation);
        logger.info("Allocation update Request is sent");
        Response updateresponse = updatebody.contentType("application/json; charset=utf-8").put(APIURL+"/api/v1/deposit/update-deposit");
        String allocationupdateresponse = updateresponse.getBody().asString();
        logger.info("Allocation update Response is received");
        logger.info(allocationupdateresponse);
        int statuscodee = updateresponse.getStatusCode();
        Assert.assertEquals(statuscodee , 200);
        logger.info("Status code is Successfully validated");
        Thread.sleep(5000);
    }

    private void UpdateCancelFetchRequestId(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        logger.info("Fetch request Id body is " + FetchReqBody);
        String AffordebilityStatus = fetchresponse.jsonPath().get("data.allocationStatus").toString();
        logger.info("Affordebility Status is " + AffordebilityStatus);
        int AffordebilityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(AffordebilityStatusCode,200);
        Assert.assertEquals(AffordebilityStatus,"ACTIVE");
    }

    private void ProfileDataLinkFlow() throws InterruptedException {
        getDriver().get(APIUpdateCancelArgyle);
        CC_ProfileDataLinkPage_Client Allocation_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(60000);
        Allocation_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Allocation_ProfileDataLink.selectAllocationCompleteemail();
        Allocation_ProfileDataLink.verifyAllocDetailsInfoDisplayed();
    }

    private void SDKLoginFlowCancelUpdate(String username, String password, String un, String pass,String Ver) throws InterruptedException {
        getDriver().get(APIUpdateCancelArgyle);
        CC_SDKLoginPage_Client Allocation_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Allocation_SDKLoginLink.setmailosaurEmail(username);
        Allocation_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.setsetmailosaurPassword(password);
        Allocation_SDKLoginLink.clickmailosaurLogin();
        Thread.sleep(3000);
        Allocation_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Allocation_SDKLoginLink.SelectSDKEmail();
        Allocation_SDKLoginLink.clickOnSDKLink();
        UtilityClass.ClientOTP();
        Thread.sleep(5000);
        Allocation_SDKLoginLink.DownloadExcelData();
        Allocation_SDKLoginLink.clickAllocationConform();
        Allocation_SDKLoginLink.setSDKUsernameArgyle(un);
        Allocation_SDKLoginLink.setSDKPasswordArgyle(pass);
        Allocation_SDKLoginLink.clickConformbutton();
        logger.info("SDK Login Done Successfully");
        Allocation_SDKLoginLink.setVerificationcode(Ver);
        Allocation_SDKLoginLink.clicksdkVerify();
        Allocation_SDKLoginLink.setConfirm();
        Thread.sleep(25000);
        Allocation_SDKLoginLink.verifySDKSuccessScreen();
    }

    private void DirectAllocationUpdateCancel(String firstName, String lastName, String numberofInstalment, String loanamount, String CCreference, String ACHpull, String AccountVerifiction, String NotificationURL, String directallocationURL, String requestID, String employerid) throws InterruptedException {
        List<String> notificationUrls = List.of(NotificationURL);
        List<String> DirectCallbackUrls = List.of(directallocationURL);
        DirectAllocationCallBack directCallBack = new DirectAllocationCallBack(DirectCallbackUrls,notificationUrls);
        DirectAllocationPayload directallocation = new DirectAllocationPayload(employerid, firstName, lastName,emailid,mobile, numberofInstalment, loanamount, CCreference,"","", directCallBack, ACHpull, AccountVerifiction,"","","");
        RequestSpecification DirectAllocation = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(directallocation);
        logger.info("DirectAllocation verification Request is sent");
        Response DirectAllocationResponse = DirectAllocation.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/deposit/update-deposit");
        String DirectAllocationbody = DirectAllocationResponse.getBody().asString();
        logger.info("DirectAllocation verification Response is received");
        System.out.println(DirectAllocationbody);
        int statuscode = DirectAllocationResponse.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
        Thread.sleep(5000);
    }
}
