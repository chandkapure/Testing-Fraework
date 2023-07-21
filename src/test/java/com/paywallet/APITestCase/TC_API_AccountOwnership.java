package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.Accountownershippayload;
import com.paywallet.Utilities.ReadExcel;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.Utilities.WrapperAPITestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;


public class TC_API_AccountOwnership extends BaseClass {

    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();


    @Test( dataProvider = "AccountOwnership",dataProviderClass = com.paywallet.Utilities.WrapperAPITestData.class)
    public void directAllocation(String employer, String firstName, String lastName, String bankABA, String bankAccountNumber) throws InterruptedException {


//       Generate RequestId
        String requestID = methodforAPI.getRequestID();

//      Employer TypeAhed
        String EmpID = methodforAPI.getEmpID(requestID,employer);

//      Select Employer
        String employerid = methodforAPI.selectEmployer(requestID,EmpID);

//      Account Ownership
        AccountOwnership(firstName, lastName, bankABA, bankAccountNumber, requestID);

//      Fetch RequestID
        FetchRequestID(requestID);

    }

    private void FetchRequestID(String requestID) {
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

    private void AccountOwnership(String firstName, String lastName, String bankABA, String bankAccountNumber, String requestID) {
        Accountownershippayload AccountPayload = new Accountownershippayload(firstName, lastName, bankABA, bankAccountNumber,mobile);
        RequestSpecification specification = RestAssured.given().log().all().header("Authorization", WrapperauthToken).header("X-request-Id", requestID).body(AccountPayload);
        logger.info("Account Ownership verification Request is sent");
        Response AccountOwnershipResponse = specification.contentType( "application/json; charset=utf-8").post(APIURL+"/api/v1/underwriting/validate/accountOwnership");
        String AccountOwnershipResponsebody = AccountOwnershipResponse.getBody().asString();
        logger.info("Account Ownership verification Response is received");
        System.out.println(AccountOwnershipResponsebody);
        int statuscode = AccountOwnershipResponse.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
    }

}
