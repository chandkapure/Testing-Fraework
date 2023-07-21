package com.paywallet.Utilities;


import com.paywallet.Base.BaseClass;
import com.paywallet.POJOClasses.*;
import com.paywallet.SetterAndGetterClasses.API_RequestIDclass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.util.List;

public class UtiltyMethodforAPI extends BaseClass {


//   Generate RequestId
    public String getRequestID()
    {
        RequestSpecification reqspecification = RestAssured.given().header("Authorization", WrapperauthToken);
        Response requestidresponse = reqspecification.accept("application/json").post(APIURL+RequestIdd);
        String reqBody = requestidresponse.getBody().asString();
        logger.info(reqBody);
        String requestid = requestidresponse.jsonPath().get("data.requestId").toString();
        logger.info(requestid);
        int GenerateRequestIdStatuscode = requestidresponse.getStatusCode();
        logger.info(String.valueOf(GenerateRequestIdStatuscode));
        Assert.assertEquals(GenerateRequestIdStatuscode,200);
        return requestid;
    }

//    Employer TypeAhead
    public String getEmpID(String requestid,String employer)
    {
        RequestSpecification Emptypeahead = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id",requestid );
        Response Empresponse = Emptypeahead.accept("application/json").get(APIURL+EmployerTypeAhead+employer);
        String EmpreqBody = Empresponse.getBody().asString();
        logger.info(EmpreqBody);
        String Empid = Empresponse.jsonPath().get("[0].id").toString();
        logger.info(Empid);
        int EmployerTypeAheadStatusCode = Empresponse.getStatusCode();
        Assert.assertEquals(EmployerTypeAheadStatusCode,200);
        return Empid;
    }

//    Select Employer
    public String selectEmployer(String requestID, String EmpID)
    {
        RequestSpecification selectemployer = RestAssured.given().header("Authorization", WrapperauthToken).header("X-request-Id",requestID );
        Response selectempresponse = selectemployer.accept("application/json").post(APIURL+SeleectEmployer+EmpID);
        String SelectEmpreqBody = selectempresponse.getBody().asString();
        logger.info(SelectEmpreqBody);
        String employerid = selectempresponse.jsonPath().get("id").toString();
        logger.info(employerid);
        int SelectEmployerStatusCode = selectempresponse.getStatusCode();
        logger.info(String.valueOf(SelectEmployerStatusCode));
        Assert.assertEquals(SelectEmployerStatusCode,200);
        return employerid;
    }

//    CodeConvergence Employer Type Ahead
    public String employerTypeAhead(String employer)
    {
        RequestSpecification Emptypeahead = RestAssured.given().header("Authorization", CodeConvergenceToken);
        logger.info("Employer Type Ahead Request Sent");
        Response Empresponse = Emptypeahead.accept("application/json").get(APIURL+EmployerTypeAhead+employer);
        logger.info("Employer Type Ahead Response Received");
        String EmpreqBody = Empresponse.getBody().asString();
        logger.info(EmpreqBody);
        System.out.println(EmpreqBody);
        String Empid = Empresponse.jsonPath().get("[0].id").toString();
        logger.info(Empid);
        int EmployerTypeAheadStatusCode = Empresponse.getStatusCode();
        Assert.assertEquals(EmployerTypeAheadStatusCode,200);
        return Empid;
    }

//    CodeConvergence Register API
    public API_RequestIDclass RegisterAPI(String employerID, String profiles, String context, String firstName, String middleName, String lastName, String emailid, String cellphone,
                                          String numberofInstalment, String installmentAmount, String loanAmount, String repaymentFrequency, String firstDateofPayment, String ClientContextReference,
                                          String addressLine1, String addressLine2, String city, String state, String zip, String dateofBirth, String last4tin,
                                          String identitycallback, String employmentcallback, String incomecallback, String allocationcallback, String insufficientfundcallback,
                                          String notificationUrls, String affordabilityCallback) throws InterruptedException {
        List<String> profile = List.of(profiles);
        CCCustomer customer = new CCCustomer(firstName,middleName,lastName,emailid,cellphone);
        CCDepositAllocation allocation = new CCDepositAllocation(numberofInstalment,installmentAmount,loanAmount,repaymentFrequency,firstDateofPayment,ClientContextReference);
        CCIdentity identity = new CCIdentity(addressLine1,addressLine2,city,state,zip,dateofBirth,last4tin);
        List<String> identitycall = List.of(identitycallback);
        List<String>  employment = List.of(employmentcallback);
        List<String>  incomecall = List.of(incomecallback);
        List<String>  allocationcall = List.of(allocationcallback);
        List<String> insufficient = List.of(insufficientfundcallback);
        List<String>  notification = List.of(notificationUrls);
        List<String>  affordebilitycall = List.of(affordabilityCallback);
        CCCallbackURLs callback = new CCCallbackURLs(identitycall,employment,incomecall,allocationcall,insufficient,notification,affordebilitycall);
        CCRegisterAPI regapi = new CCRegisterAPI(employerID,profile,context,customer,identity,allocation,callback);
        RequestSpecification registrationApi = RestAssured.given().header("Authorization", CodeConvergenceToken).contentType("application/json").body(regapi);
        logger.info("Register API Request Sent");
        Response registrationApiResponse = registrationApi.accept("application/json").post(APIURL+RegisterAPI);
        logger.info("Register API Response Received");
        String regAPIresponsebody = registrationApiResponse.getBody().asString();
        System.out.println(regAPIresponsebody);
        String requestID = registrationApiResponse.jsonPath().get("data.requestId").toString();
        String ClearRequestID = registrationApiResponse.jsonPath().get("data.clearRequestId").toString();
        logger.info(requestID);
        logger.info(ClearRequestID);
        int StatusCode = registrationApiResponse.statusCode();
        Assert.assertEquals(StatusCode,201);
        logger.info("Status code is successfully validated");
        API_RequestIDclass requestid = new API_RequestIDclass();
        requestid.setRequestID(requestID);
        requestid.setClearRequestID(ClearRequestID);
        return requestid;
    }

    public String  accountownershipregisterAPI(String employerID, String profiles, String context, String firstName, String middleName, String lastName, String emailid, String cellphone,
                                             String bankABA , String bankaccnumber)
    {
        List<String> profile = List.of(profiles);
        CCCustomer customer = new CCCustomer(firstName,middleName,lastName,emailid,cellphone);
        CCAccountValidation accvalidation = new CCAccountValidation(bankABA,bankaccnumber);
        CCAccountOwnershipRegisterAPI accountregister  = new CCAccountOwnershipRegisterAPI(employerID,profile,context,customer,accvalidation);
        RequestSpecification accregistrationApi = RestAssured.given().header("Authorization", CodeConvergenceToken).contentType("application/json").body(accountregister);
        logger.info("Register API for accOwnership Request Sent");
        Response accregistrationApiResponse = accregistrationApi.accept("application/json").post(APIURL+RegisterAPI);
        logger.info("Register API for accOwnership Response Received");
        String accregAPIresponsebody = accregistrationApiResponse.getBody().asString();
        logger.info(accregAPIresponsebody);
        String accrequestID = accregistrationApiResponse.jsonPath().get("data.requestId").toString();
        logger.info(accrequestID);
        int StatusCode = accregistrationApiResponse.statusCode();
        Assert.assertEquals(StatusCode,201);
        logger.info("Status code is successfully validated");
        return accrequestID;

    }

    public void UpdateProfileAPI(String requestID, String employerID, String profiles, String context, String numberofInstalment, String installmentAmount, String loanAmount, String repaymentFrequency,
                                   String firstDateofPayment, String ClientContextReference, String addressLine1, String addressLine2, String city, String state, String zip, String dateofBirth,
                                   String last4tin,String identitycallback, String employmentcallback, String incomecallback, String allocationcallback, String insufficientfundcallback,
                                   String notificationUrls, String affordabilityCallback)  {

        List<String> profile = List.of(profiles);
        CCDepositAllocation allocation = new CCDepositAllocation(numberofInstalment,installmentAmount,loanAmount,repaymentFrequency,firstDateofPayment,ClientContextReference);
        CCIdentity identity = new CCIdentity(addressLine1,addressLine2,city,state,zip,dateofBirth,last4tin);
        List<String> identitycall = List.of(identitycallback);
        List<String>  employment = List.of(employmentcallback);
        List<String>  incomecall = List.of(incomecallback);
        List<String>  allocationcall = List.of(allocationcallback);
        List<String> insufficient = List.of(insufficientfundcallback);
        List<String>  notification = List.of(notificationUrls);
        List<String>  affordebilitycall = List.of(affordabilityCallback);
        CCCallbackURLs callback = new CCCallbackURLs(identitycall,employment,incomecall,allocationcall,insufficient,notification,affordebilitycall);
        CCUpdateProfile updateprofile = new CCUpdateProfile(profile, employerID, context, identity, allocation,callback );
        RequestSpecification updateApi = RestAssured.given().header("Authorization", CodeConvergenceToken).contentType("application/json").header("x-request-id", requestID).body(updateprofile);
        Response updateApiResponse = updateApi.accept("application/json").put(APIURL+UpdateAPI);
        logger.info("UpdateProfile API Response Received");
        String regAPIresponsebody = updateApiResponse.getBody().asString();
        logger.info(regAPIresponsebody);
        int StatusCode = updateApiResponse.statusCode();
        Assert.assertEquals(StatusCode,201);
        logger.info("Status code is successfully validated");

    }

    public void UpdateAPI(String profiles, String revisedAllocation, String revisednumberofInstallment,String requestID)
    {

        List<String> profile = List.of(profiles);
        Updateallocation updateallocation = new Updateallocation(revisedAllocation,revisednumberofInstallment);
        CCUpdate update = new CCUpdate(profile,updateallocation);
        RequestSpecification  updateprof = RestAssured.given().header("Authorization", CodeConvergenceToken).header("x-request-id", requestID).contentType("application/json").body(update);
        Response updateresponse = updateprof.accept("application/json").put(APIURL+UpdateAPI);
        String updatebody = updateresponse.getBody().asString();
        logger.info("Body is :" + updatebody);
        int statusCode =  updateresponse.getStatusCode();
        Assert.assertEquals(statusCode,201);
        logger.info("Status code is successfully validated");

    }

    public void CancelAPI(String profiles,String requestID) throws InterruptedException {

        List<String> profile = List.of(profiles);
        CCCancel cancel= new CCCancel(profile);
        RequestSpecification  updateprof = RestAssured.given().header("Authorization", CodeConvergenceToken).header("x-request-id", requestID).contentType("Application/json").body(cancel);
        Response updateresponse = updateprof.accept("application/json").put(APIURL+UpdateAPI);
        Thread.sleep(3000);
        String updatebody = updateresponse.getBody().asString();
        logger.info("Body is :" + updatebody);
        int statusCode =  updateresponse.getStatusCode();
        Assert.assertEquals(statusCode,201);
        logger.info("Status code is successfully validated");

    }

    public void PostRestart(String requestID) throws InterruptedException {
        RequestSpecification postrestart = RestAssured.given().contentType("Application/json").header("Authorization", CodeConvergenceToken).header("x-request-id", requestID);
        Response postresponse = postrestart.accept("application/json").post(APIURL+"/api/v1/request/restart");
        Thread.sleep(3000);
        String postboday = postresponse.getBody().asString();
        logger.info("Body is " + postboday);
        int statuscode = postresponse.getStatusCode();
        Assert.assertEquals(statuscode,200);
        logger.info("Status code is successfully validated");
    }




}
