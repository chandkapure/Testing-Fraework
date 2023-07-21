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

public class TC_SDK_Client_Context_Employment_Argyle extends BaseClass {
    String emailid = emailstring+ randomNum +"@c5f3nqco.mailosaur.net";
    WebDriver driver = getDriver();

    @DataProvider(name="EmploymentData")
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
        int totalrows=excel.getS3RowCount("Employment");
        int totalcols=excel.getS3CellCount("Employment",1);
        String EmploymentData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                EmploymentData[i-1][j]=excel.getS3CellData("Employment", i, j);
            }
        }
        return EmploymentData;
    }

    String ClearrequestID;
    @Test(priority = 1 ,dataProvider = "EmploymentData")
    public void employmentVerificationArgyle(String employer,String firstname, String lastname,String username, String password,String un, String pass, String ver) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockandCustomerCaptureFlow(employer, firstname, lastname);

        getDriver().get(employmentargyle);
        SDKLoginFlow(username, password, un, pass, ver);

        UtilityClass.compareEmploymentCallbackDashboardData(EmploymentURLPath,ClearrequestID);

        UtilityClass.metricReportValidation(ClearrequestID);

    }


    private void SDKLoginFlow(String username, String password, String un, String pass, String ver) throws InterruptedException {
        CC_SDKLoginPage_Client Employment_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Employment_SDKLoginLink.setmailosaurEmail(username);
        Employment_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Employment_SDKLoginLink.setsetmailosaurPassword(password);
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle Mailasour login screen");
        Employment_SDKLoginLink.clickmailosaurLogin();
        logger.info(" Maila-sour login was successful");
        Thread.sleep(3000);
        Employment_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Employment_SDKLoginLink.selectSDKEmail();
        Thread.sleep(2000);
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle SDK email screen");
        Employment_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK email selected, and clicked on SDK login link");
        UtilityClass.ClientOTP();
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle SDK OTP Screen");
        Thread.sleep(2000);
        Employment_SDKLoginLink.setSDKUsernameArgyle(un);
        Employment_SDKLoginLink.setSDKPasswordArgyle(pass);
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle SDK login screen");
        Employment_SDKLoginLink.clickConformbutton();
        logger.info("SDK username and password entered successfully and conformed");
        Thread.sleep(2000);
        Employment_SDKLoginLink.setVerificationcode(ver);
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle Verification Code screen");
        Employment_SDKLoginLink.clicksdkVerify();
        logger.info("Verification code entered and verified");
        CC_ProfileDataLinkPage_Client Employment_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Employment_ProfileDataLink.verifyEmploymentInfodisp();
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle Employee info Display Screen");
        logger.info("Employee details displayed successfully");
        Employment_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successfully");
    }

    private void KeyClockandCustomerCaptureFlow(String employer, String firstname, String lastname) throws InterruptedException {
        CC_CustomerCapturePage_Client Employment_VerificationPage = new CC_CustomerCapturePage_Client(getDriver());
        ClearrequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Employment_Argyle request ID is");
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated");
        UtilityClass.selectEmployerClient(employer);
        Employment_VerificationPage.setVerification();
        Employment_VerificationPage.setEmploymentCheckbox();
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle Employer search successful");
        Employment_VerificationPage.clickServiceSelectionSubmit();
        logger.info("selected Employment Checkbox and clicked on submit");
        Employment_VerificationPage.setCellphone(mobile);
        Employment_VerificationPage.cellphoneSearch();
        Employment_VerificationPage.setFirstname(firstname);
        Employment_VerificationPage.setLastName(lastname);
        Employment_VerificationPage.setEmailid(emailid);
        getScreenshot("Client_Context_Employment_Argyle","employmentVerificationArgyle Customer capture screen");
        Employment_VerificationPage.clickCustomercaptureSubmit();
        logger.info("Entered details in Customer capture and clicked on submit");
        Employment_VerificationPage.clickGotoHome();
        logger.info("Clicked on go to homepage button and landed on Homepage");
    }

}
