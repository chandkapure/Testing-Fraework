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
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import com.paywallet.pageObject.CC_CustomerCapturePage_Client;
import com.paywallet.Utilities.ReadExcel;

import java.io.IOException;

public class TC_SDK_Client_Context_Affordability_AtomicFI extends BaseClass {
    String email = emailstring+ randomNum +"@zmg3pufk.mailosaur.net";
    WebDriver driver = getDriver();
    @DataProvider(name="Affordablity")
    public Object[][] getData() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = S3AtomicFITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel =new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("Affordablity");
        int totalcols=excel.getS3CellCount("Affordablity",1);
        String Affordablity[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                Affordablity[i-1][j]=excel.getS3CellData("Affordablity", i, j);
            }
        }
        return Affordablity;
    }

    String ClearRequestID;
    @Test(priority = 1 , dataProvider = "Affordablity")
    public void AffordabilityVerification(String employer,String firstname, String lastname, String OTP, String username, String password, String un, String pass) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockandCustomerCaptureFlow(employer, firstname, lastname);

        getDriver().get(affordebilityatomicFI);
        SDKLoginFlow(username, password,OTP, un, pass);

        UtilityClass.compareAffordebilityCallbackDashboardData(AffordabilityURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);
    }


    private void SDKLoginFlow(String username, String password,String OTP, String un, String pass) throws InterruptedException {
        CC_SDKLoginPage_Client Affordebility_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Affordebility_SDKLoginLink.setmailosaurEmail(username);
        Affordebility_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Affordebility_SDKLoginLink.setsetmailosaurPassword(password);
        Affordebility_SDKLoginLink.clickmailosaurLogin();
        logger.info(" Mailosour login was successfull");
        Thread.sleep(3000);
        Affordebility_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Affordebility_SDKLoginLink.selectSDKEmail();
        Affordebility_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK email selected, and clicked on SDK login link");
        UtilityClass.enterOTP(OTP);
        Thread.sleep(2000);
        getDriver().switchTo().frame("atomic-transact-iframe");
        Affordebility_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Affordebility_SDKLoginLink.clickSDKcontinue();
        logger.info("Username Entered and Clicked on Continue");
        Thread.sleep(2000);
        Affordebility_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Affordebility_SDKLoginLink.clickSDKSigninbutton();
        logger.info("Password Entered and Clicked on Signin Button");
        Thread.sleep(25000);
        CC_ProfileDataLinkPage_Client Affordebility_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Affordebility_ProfileDataLink.verifyAffordablityInfodisp();
        logger.info("Affordability details displayed successfully");
        Affordebility_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successfully");
    }

    private void KeyClockandCustomerCaptureFlow(String employer, String firstname, String lastname) throws InterruptedException {
        CC_CustomerCapturePage_Client Affordebility_Verification = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Affordability_AtomicFI ClearRequest id is :");
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated Successfully");
        UtilityClass.selectEmployerClient(employer);
        Affordebility_Verification.setVerification();
        Affordebility_Verification.setAffordability();
        Affordebility_Verification.clickServiceSelectionSubmit();
        Affordebility_Verification.setCellphone(mobile);
        Affordebility_Verification.cellphoneSearch();
        Affordebility_Verification.setFirstname(firstname);
        Affordebility_Verification.setLastName(lastname);
        Affordebility_Verification.setEmailid(email);
        Affordebility_Verification.clickCustomercaptureSubmit();
        Affordebility_Verification.clickGotoHome();
    }
}
