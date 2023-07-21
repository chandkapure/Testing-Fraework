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

public class TC_SDK_Client_Context_Identity_Argyle extends BaseClass {


    String emailid = emailstring+ randomNum +"@smdwzsvd.mailosaur.net";

    @DataProvider(name="IdentityData")
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
        int totalrows=excel.getS3RowCount("Identity");
        int totalcols=excel.getS3CellCount("Identity",1);
        String LoginData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                LoginData[i-1][j]=excel.getS3CellData("Identity", i, j);
            }
        }
        return LoginData;
    }

    String ClearRequestID;
    @Test(priority = 1, dataProvider = "IdentityData" )
    public void identityVerification(String employer,String firstname, String lastname, String address, String city,String ZIP, String fortin ,
                                     String DOB, String state,String username, String password, String un, String pass, String ver) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockandCustomerCaptureFlow(employer, firstname, lastname, address, city, ZIP, fortin, DOB, state);

        getDriver().get(identityargyle);
        SDKLoginFlow(username, password, un, pass, ver);

        UtilityClass.compareIdentityCallbackDashboardData(IdentityURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);

    }

    private void ProfileDataLinkFlow() throws InterruptedException {
        CC_ProfileDataLinkPage_Client Identity_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(100000);
        Identity_ProfileDataLink.setRefresh();
        Thread.sleep(2000);
        Identity_ProfileDataLink.selectProfileDataEmail();
        Identity_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Profile data email selected, and clicked on ProfileData link");
        UtilityClass.ClientOTP();
        Identity_ProfileDataLink.verifyIdentityInfoDisplayed();
        logger.info("Employee Service displayed Successfully");
        Identity_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successfully");
    }

    private void SDKLoginFlow(String username, String password, String un, String pass, String ver) throws InterruptedException {
        CC_SDKLoginPage_Client Identity_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Identity_SDKLoginLink.setmailosaurEmail(username);
        Identity_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Identity_SDKLoginLink.setsetmailosaurPassword(password);
        Identity_SDKLoginLink.clickmailosaurLogin();
        logger.info(" Mailosour login was successful");
        Thread.sleep(3000);
        Identity_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Identity_SDKLoginLink.selectSDKEmail();
        Identity_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK email selected, and clicked on SDK login link");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Identity_SDKLoginLink.setSDKUsernameArgyle(un);
        Identity_SDKLoginLink.setSDKPasswordArgyle(pass);
        Identity_SDKLoginLink.clickConformbutton();
        logger.info("SDK username and password entered successfully and conformed");
        Thread.sleep(2000);
        Identity_SDKLoginLink.setVerificationcode(ver);
        Identity_SDKLoginLink.clicksdkVerify();
        logger.info("Verification code entered and verified");
        CC_ProfileDataLinkPage_Client Identity_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Identity_ProfileDataLink.verifyIdentityInfoDisplayed();
        logger.info("Employee Service displayed Successfully");
        Identity_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successfully");

    }

    private void KeyClockandCustomerCaptureFlow(String employer, String firstname, String lastname, String address, String city, String ZIP, String fortin, String DOB, String state) throws InterruptedException {
        CC_CustomerCapturePage_Client Identity_VerificationPage = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Identity_Argyle Request ID is ");
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated Successfully");
        UtilityClass.selectEmployerClient(employer);
        Identity_VerificationPage.setVerification();
        Identity_VerificationPage.identityVerification();
        Identity_VerificationPage.clickServiceSelectionSubmit();
        logger.info("selected Identity service and clicked on submit");
        Identity_VerificationPage.setCellphone(mobile);
        Identity_VerificationPage.cellphoneSearch();
        Identity_VerificationPage.setFirstname(firstname);
        Identity_VerificationPage.setLastName(lastname);
        Identity_VerificationPage.setEmailid(emailid);
        Identity_VerificationPage.setAddress(address);
        Identity_VerificationPage.setCity(city);
        Identity_VerificationPage.setZipCode(ZIP);
        Identity_VerificationPage.setFourtin(fortin);
        Identity_VerificationPage.setCalander(DOB);
        Identity_VerificationPage.setSelectState(state);
        Identity_VerificationPage.clickCustomercaptureSubmit();
        logger.info("Entered details in Customer capture and clicked on submit");
        Identity_VerificationPage.clickGotoHome();
        logger.info("Clicked on go to homepage button and landed on Homepage");
    }
}
