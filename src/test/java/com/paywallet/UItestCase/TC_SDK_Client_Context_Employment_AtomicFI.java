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

public class TC_SDK_Client_Context_Employment_AtomicFI extends BaseClass
{

    String email = emailstring+ randomNum +"@86qy4abm.mailosaur.net";

    @DataProvider(name="EmploymentData" )
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

    String ClearRequestID;
    @Test(priority = 1 ,dataProvider = "EmploymentData" )
    public void employmentVerification(String employer,String firstname, String lastname,String username, String password,String un, String pass) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyclockLoginandCustomerCaptureFlow(employer, firstname, lastname);

        getDriver().get(employmentatomicFI);
        SDKLoginFlow(username, password, un, pass);

        UtilityClass.compareEmploymentCallbackDashboardData(EmploymentURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);

    }


    private void SDKLoginFlow(String username, String password, String un, String pass) throws InterruptedException {
        CC_SDKLoginPage_Client Employment_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Employment_SDKLoginLink.setmailosaurEmail(username);
        Employment_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Employment_SDKLoginLink.setsetmailosaurPassword(password);
        Employment_SDKLoginLink.clickmailosaurLogin();
        logger.info(" Mailosour login was successfull");
        Thread.sleep(3000);
        Employment_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Employment_SDKLoginLink.selectSDKEmail();
        Employment_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK email selected, and clicked on SDK login link");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        getDriver().switchTo().frame("atomic-transact-iframe");
        Employment_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Employment_SDKLoginLink.clickSDKcontinue();
        Thread.sleep(2000);
        Employment_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Employment_SDKLoginLink.clickSDKSigninbutton();
        logger.info("SDK username and password entred succesfully and conformed");
        Thread.sleep(25000);
        CC_ProfileDataLinkPage_Client Employment_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Employment_ProfileDataLink.verifyEmploymentInfodisp();
        logger.info("Employee details displayed successfully");
        Employment_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successful");
    }

    private void KeyclockLoginandCustomerCaptureFlow(String employer, String firstname, String lastname) throws InterruptedException {
        CC_CustomerCapturePage_Client Employment_VerificationPage = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Employment_AtomicFI Request Id is :");
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated");
        UtilityClass.selectEmployerClient(employer);
        Employment_VerificationPage.setVerification();
        logger.info("Selected Verification Service");
        Employment_VerificationPage.setEmploymentCheckbox();
        Employment_VerificationPage.clickServiceSelectionSubmit();
        logger.info("selected Employment Checkbox and clicked on submit");
        Employment_VerificationPage.setCellphone(mobile);
        Employment_VerificationPage.cellphoneSearch();
        Employment_VerificationPage.setFirstname(firstname);
        Employment_VerificationPage.setLastName(lastname);
        Employment_VerificationPage.setEmailid(email);
        Employment_VerificationPage.clickCustomercaptureSubmit();
        logger.info("Entered details in Customer capture and clicked on submit");
        Employment_VerificationPage.clickGotoHome();
        logger.info("Clicked on go to homepage button and landed on Homepage");
    }
}
