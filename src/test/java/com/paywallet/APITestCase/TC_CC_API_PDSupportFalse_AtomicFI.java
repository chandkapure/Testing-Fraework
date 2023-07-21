package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.API_RequestIDclass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_CC_API_PDSupportFalse_AtomicFI extends BaseClass {

    String emailid =  "TC_CC_API_PDSupportFalse_AtomicFI"+ randomNum +"@bohjyxeo.mailosaur.net";
    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();

    @Test(dataProvider = "CCAPIPdsupportAtomicFIData",dataProviderClass = CustomDataProvider.class)
    public void DepositAllocationAPI(String provider, String context, String firstName, String middleName, String lastName,
                                     String numberofInstalment, String installmentAmount, String loanAmount, String repaymentFrequency, String firstDateofPayment, String ClientContextReference,
                                     String addressLine1, String addressLine2, String city, String state, String zip, String dateofBirth, String last4tin,
                                     String identitycallback, String employmentcallback, String incomecallback, String allocationcallback, String insufficientfundcallback,
                                     String notificationUrls, String affordabilityCallback, String username, String password, String un, String pass) throws InterruptedException {

        System.out.println("TC_CC_API_PDSupportFalse_AtomicFI email id is "+emailid);
        System.out.println("TC_CC_API_PDSupportFalse_AtomicFI phone number is "+mobile);
//  Employer Type Ahead
        String employerID = methodforAPI.employerTypeAhead(provider);

        String profiles = "DEPOSIT_ALLOCATION";

//  RegisterAPI
        API_RequestIDclass requestid  = methodforAPI.RegisterAPI(employerID, profiles, context, firstName, middleName, lastName, emailid, mobile,
                numberofInstalment, installmentAmount, loanAmount, repaymentFrequency, firstDateofPayment, ClientContextReference,
                addressLine1, addressLine2, city, state, zip, dateofBirth, last4tin, IdentityURL,EmploymentURL,IncomeURL,DepositAllocationURL,
                NotificationURL,NotificationURL,AffordabilityURL);

        String RequestID = requestid.getRequestID();
        String ClearRequestID = requestid.getClearRequestID();

//  SDK Login Starts
        getDriver().get(APIPDsupporttfalseAtomicFI);
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
        Allocation_SDKLoginLink.selectSDKEmail();
        Allocation_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK Email selected and clicked on email Successfully");
        UtilityClass.ClientOTP();
        Thread.sleep(25000);
        getDriver().switchTo().frame(0);
        Allocation_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Thread.sleep(5000);
        Allocation_SDKLoginLink.clickSDKcontinue();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Allocation_SDKLoginLink.clickSDKSigninbutton();
        logger.info("SDK Login Done Successfully");
        Thread.sleep(30000);
        Allocation_SDKLoginLink.setPendingstatus();
        logger.info("PDSupport status verified");


        getDriver().get(APIPDsupporttfalseAtomicFI);
        CC_ProfileDataLinkPage_Client Allocation_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(5000);
        Allocation_ProfileDataLink.setRefresh();
        Thread.sleep(1000);
        Allocation_ProfileDataLink.setSelectEmail2();
        Allocation_ProfileDataLink.scroll();
        Thread.sleep(3000);
        Allocation_ProfileDataLink.clickOnProfiledataLink();
        String parent = getDriver().getWindowHandle();
        Set<String> allWindow = getDriver().getWindowHandles();
        for(String child: allWindow)
        {
            if(!parent.equalsIgnoreCase(child))
            {
                getDriver().switchTo().window(child);
                UtilityClass.ClientOTP();
                Thread.sleep(3000);
                Allocation_ProfileDataLink.selectCheckbox();
                Allocation_ProfileDataLink.setSubmit();
            }
        }

        getDriver().get(Dashboard);
        UtilityClass.keyClockLogin();
        Thread.sleep(2000);
        UtilityClass.PDsupportfalseallocationdashboard(ClearRequestID);


//    Fetch Request ID
        DepositAllocationFetchRequestID(RequestID);
    }

    private void DepositAllocationFetchRequestID(String requestID) {
        RequestSpecification FetchrequestID = RestAssured.given().header("Authorization", CodeConvergenceToken).header("X-request-Id", requestID);
        Response fetchresponse = FetchrequestID.accept("application/json").get(APIURL+"/api/v1/request");
        String FetchReqBody = fetchresponse.getBody().asString();
        logger.info("Fetch Request ID Request Sent");
        logger.info(FetchReqBody);
        System.out.println(FetchReqBody);
        logger.info("Fetch RequestID Response Received");
        String FetchStatus = fetchresponse.jsonPath().get("data.allocationStatus").toString();
        logger.info(FetchStatus);
        int FetchIdentityStatusCode = fetchresponse.getStatusCode();
        Assert.assertEquals(FetchIdentityStatusCode,200);
        Assert.assertEquals(FetchStatus,"PENDING");
    }

}
