package com.paywallet.APITestCase;
import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.DirectAllocationCallBack;
import com.paywallet.POJOClasses.DirectAllocationPayload;
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

public class TC_API_DirectAllocation_Argyle extends BaseClass {
    String emailid =  "DirectAllocationAPIArgyle"+ randomNum +"@e8pifhkb.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "DirectAllocationArgyle",dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void APIdirectAllocationArgyle(String employer, String firstName, String lastName, String numberofInstalment, String loanamount,
                                          String CCreference, String ACHpull, String AccountVerifiction, String username, String password,
                                          String un, String pass, String ver,String NotificationURL, String directallocationURL) throws InterruptedException {


//       Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);


//      Direct Allocation
        DirectAllocationArgyle(firstName, lastName, numberofInstalment, loanamount, CCreference, ACHpull, AccountVerifiction, NotificationURL, directallocationURL, requestID, employerid);

//      SDK Login Starts
        SDKLoginFlowArgyle(username, password, un, pass, ver);


//        Fetch RequestID
        DirectAllocationFetchRequestID(requestID);

    }

    private void DirectAllocationFetchRequestID(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        System.out.println(FetchReqBody);
        String AffordebilityStatus = fetchresponse.jsonPath().get("data.allocationStatus").toString();
        System.out.println(AffordebilityStatus);
        int AffordebilityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(AffordebilityStatusCode,200);
        Assert.assertEquals(AffordebilityStatus,"ACTIVE");
    }

    private void ProfileDataLinkFlowArgyle() throws InterruptedException {
        getDriver().get(APIDirectAllocationArgyle);
        CC_ProfileDataLinkPage_Client Allocation_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(90000);
        Allocation_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Allocation_ProfileDataLink.selectAllocationCompleteemail();
        logger.info("Allocation Email selected Successfully");
        Allocation_ProfileDataLink.verifyAllocDetailsInfoDisplayed();
        logger.info("Allocation details Displayed Successfully");
    }

    private void SDKLoginFlowArgyle(String username, String password, String un, String pass, String ver) throws InterruptedException {
        getDriver().get(APIDirectAllocationArgyle);
        CC_SDKLoginPage_Client Allocation_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Allocation_SDKLoginLink.setmailosaurEmail(username);
        Allocation_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.setsetmailosaurPassword(password);
        Allocation_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur login Done Successfully ");
        Thread.sleep(3000);
        Allocation_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Allocation_SDKLoginLink.SelectSDKEmail();
        Allocation_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK Email selected and clicked on email Successfully");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.DownloadExcelData();
        Allocation_SDKLoginLink.clickAllocationConform();
        logger.info("Clicked on consent Agree");
        Allocation_SDKLoginLink.setSDKUsernameArgyle(un);
        Allocation_SDKLoginLink.setSDKPasswordArgyle(pass);
        Allocation_SDKLoginLink.clickConformbutton();
        logger.info("Username and Password Entered and SDK login done Successfully");
        Allocation_SDKLoginLink.setVerificationcode(ver);
        Allocation_SDKLoginLink.clicksdkVerify();
        logger.info("Verification code entered successfully and clicked on Verify");
        Allocation_SDKLoginLink.scroll();
        Allocation_SDKLoginLink.setConfirm();
        Allocation_SDKLoginLink.verifySDKSuccessScreen();
        logger.info("Allocation details Displayed Successfully");
    }

    private void DirectAllocationArgyle(String firstName, String lastName, String numberofInstalment, String loanamount, String CCreference, String ACHpull, String AccountVerifiction, String NotificationURL, String directallocationURL, String requestID, String employerid) throws InterruptedException {
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
