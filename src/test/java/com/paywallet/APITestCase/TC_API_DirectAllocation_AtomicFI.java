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

public class TC_API_DirectAllocation_AtomicFI extends BaseClass {
    String emailid =  "DirectAllocationAPIAtomicFI+"+ randomNum +"@lebn3xqj.mailosaur.net";

    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test(dataProvider = "DirectAllocationAtomicFI" ,dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void directAllocation(String employer, String firstName, String lastName, String numberofInstalment, String loanamount,
                                          String CCreference, String ACHpull, String AccountVerifiction, String username, String password,
                                          String un, String pass,String NotificationURL, String directallocationURL) throws InterruptedException {

//      Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);


//      Direct Allocation
        DirectAllocationAtomicFI(firstName, lastName, numberofInstalment, loanamount, CCreference, ACHpull, AccountVerifiction, NotificationURL, directallocationURL, requestID, employerid);

//      SDK Login Starts
        SDKLoginFlow(username, password, un, pass);



//      Fetch Request ID
        DirectAllocationFetchRequestId(requestID);
    }

    private void DirectAllocationFetchRequestId(String requestID) {
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

    private void ProfileDataLinkFlow() throws InterruptedException {
        getDriver().get(APIDirectAllocationAtomicFI);
        CC_ProfileDataLinkPage_Client Allocation_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(90000);
        Allocation_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Allocation_ProfileDataLink.selectAllocationCompleteemail();
        Allocation_ProfileDataLink.verifyAllocDetailsInfoDisplayed();
    }

    private void SDKLoginFlow(String username, String password, String un, String pass) throws InterruptedException {
        getDriver().get(APIDirectAllocationAtomicFI);
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
        Thread.sleep(2000);
        Allocation_SDKLoginLink.DownloadExcelData();
        Allocation_SDKLoginLink.clickAllocationConform();
        getDriver().switchTo().frame("atomic-transact-iframe");
        Allocation_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Thread.sleep(3000);
        Allocation_SDKLoginLink.clickSDKcontinue();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Thread.sleep(3000);
        Allocation_SDKLoginLink.clickSDKSigninbutton();
        Allocation_SDKLoginLink.clickSDKConform();
        logger.info("Clicked on PayDistribution Conform");
        Thread.sleep(25000);
        Allocation_SDKLoginLink.verifySDKSuccessScreen();
        logger.info("Thank you screen displayed successfully");
    }

    private void DirectAllocationAtomicFI(String firstName, String lastName, String numberofInstalment, String loanamount, String CCreference, String ACHpull, String AccountVerifiction, String NotificationURL, String directallocationURL, String requestID, String employerid) throws InterruptedException {
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
