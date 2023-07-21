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

public class TC_SDK_Client_Context_Income_AtomicFI extends BaseClass {

    String email = emailstring+ randomNum +"@uif32kup.mailosaur.net";

    @DataProvider(name="IncomeData")
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
        int totalrows=excel.getS3RowCount("Income");
        int totalcols=excel.getS3CellCount("Income",1);
        String IncomeData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                IncomeData[i-1][j]=excel.getS3CellData("Income", i, j);
                System.out.println("Test Date is "+ IncomeData[i-1][j]);
            }
        }
        return IncomeData;
    }

    String ClearRequestID;
    @Test( priority=1 , dataProvider = "IncomeData")
    public void incomeVerification(String employer,String firstname, String lastname, String mon, String username, String password,String un, String pass) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockandCustomerCaptureFlow(employer, firstname, lastname, mon);

        getDriver().get(incomeatomicFI);
        SDKLoginFlow(username, password, un, pass);

        UtilityClass.compareIncomeCallbackDashboardData(IncomeURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);

    }


    private void SDKLoginFlow(String username, String password, String un, String pass) throws InterruptedException {
        CC_SDKLoginPage_Client Income_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Income_SDKLoginLink.setmailosaurEmail(username);
        Income_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Income_SDKLoginLink.setsetmailosaurPassword(password);
        Income_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur login was done successfully");
        Thread.sleep(3000);
        Income_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Income_SDKLoginLink.selectSDKEmail();
        Income_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK Email got selected and clicked on the Link");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        getDriver().switchTo().frame("atomic-transact-iframe");
        Income_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Income_SDKLoginLink.clickSDKcontinue();
        logger.info("Entered Username and clicked on continue");
        Thread.sleep(2000);
        Income_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Income_SDKLoginLink.clickSDKSigninbutton();
        logger.info("Entered Password and clicked on Signing Button");
        Thread.sleep(25000);
        CC_ProfileDataLinkPage_Client Income_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Income_ProfileDataLink.verifySalaryInfodisp();
        logger.info("Salary Details Displayed ");
        Income_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successfully");
        Thread.sleep(2000);
    }

    private void KeyClockandCustomerCaptureFlow(String employer, String firstname, String lastname, String mon) throws InterruptedException {
        CC_CustomerCapturePage_Client Income_verification_Page = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Income_AtomicFI Request Id is :");
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page Title Validated successfully");
        Thread.sleep(20000);
        Income_verification_Page.setSearchfield(employer);
        Income_verification_Page.selectEmployer();
        Income_verification_Page.clickEmployerSearchSubmit();
        logger.info("Employer selected Successfully");
        Income_verification_Page.setVerification();
        Income_verification_Page.incomeVerification();
        Income_verification_Page.clickServiceSelectionSubmit();
        logger.info("Income Verification selected Successfully and Clicked on Submit");
        Income_verification_Page.setCellphone(mobile);
        Income_verification_Page.cellphoneSearch();
        Income_verification_Page.setFirstname(firstname);
        Income_verification_Page.setLastName(lastname);
        Income_verification_Page.setEmailid(email);
        Income_verification_Page.clickCustomercaptureSubmit();
        logger.info("Details added successfully in Customer Capture screen");
        Income_verification_Page.clickGotoHome();
        logger.info("Clicked on Goto Home Page");
    }


    @Test(enabled = false,priority = 2, dataProvider = "IncomeData")
    public void existingCustomer(String employer,String firstname, String lastname, String mon, String username, String password, String un, String pass) throws InterruptedException {
        
        getDriver().get(ClientContextURL);
        KeyClockandCustomerCaptureFloww(employer, firstname, lastname, mon);

        getDriver().get(incomeatomicFI);
        SkipSDKLoginFlow(username, password);
    }

    private void SkipSDKLoginFlow(String username, String password) throws InterruptedException {
        CC_SDKLoginPage_Client Income_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Income_SDKLoginLink.setmailosaurEmail(username);
        Income_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Income_SDKLoginLink.setsetmailosaurPassword(password);
        Income_SDKLoginLink.clickmailosaurLogin();
        Thread.sleep(20000);
        logger.info("Mailosur login was done Succesfully");
        CC_ProfileDataLinkPage_Client Income_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Income_ProfileDataLink.selectProfileDataEmail();
        Income_ProfileDataLink.clickOnProfiledataLink();
        logger.info("Profile data link received and clicked for existing customer");
        UtilityClass.ClientOTP();
        Income_ProfileDataLink.clickProfiledataVerify();
        logger.info("OTP entered succesfully and verified");
        Income_ProfileDataLink.verifySalaryInfodisp();
        logger.info("Income Details displayed Succesfully");
        Income_ProfileDataLink.verifyExcelDownload();
        logger.info("Employer Data Download Successfully");
    }

    private void KeyClockandCustomerCaptureFloww(String employer, String firstname, String lastname, String mon) throws InterruptedException {
        CC_CustomerCapturePage_Client Income_verification_Page = new CC_CustomerCapturePage_Client(getDriver());
        Income_verification_Page.setUsername(Username);
        Income_verification_Page.setPassword(Password);
        Income_verification_Page.KeyclockclickSubmit();
        logger.info("Clicked on Keyclock submit");
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page Title Validated successfully");
        UtilityClass.selectEmployerClient(employer);
        Income_verification_Page.setVerification();
        Income_verification_Page.incomeVerification();
        Income_verification_Page.clickServiceSelectionSubmit();
        logger.info("Employer selected successfully and clicked on Submit");
        Income_verification_Page.setFirstname(firstname);
        Income_verification_Page.setLastName(lastname);
        Income_verification_Page.setCellphone(mobile);
        Income_verification_Page.setEmailid(email);
        Income_verification_Page.clickCustomercaptureSubmit();
        logger.info("Details entered successfully in Customer Capture Screen");
        Income_verification_Page.clickGotoHome();
        logger.info("Clicked on Goto HomePage");
    }

}
