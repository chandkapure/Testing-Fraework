package com.paywallet.UItestCase;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.UtilityClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import com.paywallet.pageObject.CC_CustomerCapturePage_Client;
import com.paywallet.Utilities.ReadExcel;

import java.io.IOException;

public class TC_SDK_Client_Context_Complete_Verification_Argyle extends BaseClass {


    String email = emailstring+ randomNum +"@0p0qg5ci.mailosaur.net";

    String ClearRequestID;
    @DataProvider(name="CompleteverificationData")
    public Object[][] getData() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = S3ArgyleTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel =new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("Completeverification");
        int totalcols=excel.getS3CellCount("Completeverification",1);
        String CompleteverificationData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                CompleteverificationData[i-1][j]=excel.getS3CellData("Completeverification", i, j);
            }
        }
        return CompleteverificationData;
    }

    @Test(priority = 1, dataProvider = "CompleteverificationData")
    public void CompleteVerification(String employer,String firstname, String lastname, String address, String city,String ZIP, String fortin ,
                                     String DOB, String state, String mon, String username, String password, String un, String pass, String ver) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyclockandCustomerCaptureFlow(employer, firstname, lastname, address, city, ZIP, fortin, DOB, state, mon);

        getDriver().get(completeverificationargyle);
        SDKLoginFlow(username, password, un, pass, ver);

        UtilityClass.compareIncomeCallbackDashboardData(IncomeURLPath,ClearRequestID);
        UtilityClass.compareIdentityCallbackDashboardData(IdentityURLPath,ClearRequestID);
        UtilityClass.compareEmploymentCallbackDashboardData(EmploymentURLPath,ClearRequestID);
        UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestID);


        UtilityClass.metricReportValidation(ClearRequestID);

    }


    private void SDKLoginFlow(String username, String password, String un, String pass, String ver) throws InterruptedException {
        CC_SDKLoginPage_Client CompleteVerification_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        CompleteVerification_SDKLoginLink.setmailosaurEmail(username);
        CompleteVerification_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        CompleteVerification_SDKLoginLink.setsetmailosaurPassword(password);
        CompleteVerification_SDKLoginLink.clickmailosaurLogin();
        logger.info(" Mailosour login was successful");
        Thread.sleep(3000);
        CompleteVerification_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        CompleteVerification_SDKLoginLink.selectSDKEmail();
        CompleteVerification_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK email selected, and clicked on SDK login link");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        CompleteVerification_SDKLoginLink.setSDKUsernameArgyle(un);
        CompleteVerification_SDKLoginLink.setSDKPasswordArgyle(pass);
        CompleteVerification_SDKLoginLink.clickConformbutton();
        logger.info("SDK username and password entered successfully and conformed");
        CompleteVerification_SDKLoginLink.setVerificationcode(ver);
        CompleteVerification_SDKLoginLink.clicksdkVerify();
        logger.info("Verification code entered and verified");
        CC_ProfileDataLinkPage_Client CompleteVerification_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        CompleteVerification_ProfileDataLink.verifyEmploymentInfodisp();
        CompleteVerification_ProfileDataLink.verifyIdentityInfoDisplayed();
        CompleteVerification_ProfileDataLink.verifyAffordablityInfodisp();
        CompleteVerification_ProfileDataLink.verifySalaryInfodisp();
        logger.info("All Employee details displayed Successfully");
        CompleteVerification_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successfully");

    }

    private void KeyclockandCustomerCaptureFlow(String employer, String firstname, String lastname, String address, String city, String ZIP, String fortin, String DOB, String state, String mon) throws InterruptedException {
        CC_CustomerCapturePage_Client CompleteVerification = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Complete_Verification_Argyle Request ID is :");
        Thread.sleep(5000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated Successfully");
        UtilityClass.selectEmployerClient(employer);
        CompleteVerification.setVerification();
        CompleteVerification.incomeVerification();
        CompleteVerification.setEmploymentCheckbox();
        CompleteVerification.identityVerification();
        CompleteVerification.setAffordability();
        CompleteVerification.clickServiceSelectionSubmit();
        logger.info("selected Complete Verification service and clicked on submit");
        CompleteVerification.setCellphone(mobile);
        CompleteVerification.cellphoneSearch();
        CompleteVerification.setFirstname(firstname);
        CompleteVerification.setLastName(lastname);
        CompleteVerification.setEmailid(email);
        CompleteVerification.setAddress(address);
        CompleteVerification.setCity(city);
        CompleteVerification.setZipCode(ZIP);
        CompleteVerification.setFourtin(fortin);
        CompleteVerification.setCalander(DOB);
        CompleteVerification.setSelectState(state);
        CompleteVerification.clickCustomercaptureSubmit();
        logger.info("Entered details in Customer capture and clicked on submit");
        CompleteVerification.clickGotoHome();
        logger.info("Clicked on go to homepage button and landed on Homepage");
    }

}
