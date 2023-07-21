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


public class TC_SDK_Client_Context_Income_Argyle extends BaseClass {

    String email = emailstring+ randomNum + "@dz3lrci6.mailosaur.net";

    @DataProvider(name="IncomeData")
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
        int totalRows=excel.getS3RowCount("Income");
        int totalCols=excel.getS3CellCount("Income",1);

        String incomeData[][]=new String[totalRows][totalCols];
        for(int i=1;i<=totalRows;i++) //1
        {
            for(int j=0;j<totalCols;j++) //0
            {
                incomeData[i-1][j]=excel.getS3CellData("Income", i, j);

            }
        }
        return incomeData;
    }


    @Test( priority = 1, dataProvider = "IncomeData")
    public void incomeVerification(String employer,String firstname, String lastname, String mon, String username, String password,
                                   String un, String pass, String ver) throws InterruptedException, IOException {


        // Keycloak Login screen
        getDriver().get(ClientContextURL);
        CC_CustomerCapturePage_Client Income_verification_Page = new CC_CustomerCapturePage_Client(getDriver());
        String ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Income_Argyle Clear Request ID is ");
        // Validate Page Title
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        Thread.sleep(20000);
        searchEmployer(employer, Income_verification_Page);
        incomeServiceSelection(Income_verification_Page);
        captureCustomerDetails(firstname, lastname, mon, Income_verification_Page);
        logger.info("Customer details entered in customer capture screen");
        Income_verification_Page.clickGotoHome();
        logger.info("Clicked on go to home page");

        // SDK Login to mailbox to click on the link
        getDriver().get(incomeargyle);
        CC_SDKLoginPage_Client Income_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        clickEmailLink(username, password, Income_SDKLoginLink);
        logger.info("Clicked on SDK login link ");
        verifyOTP(Income_SDKLoginLink);
        logger.info("Entered OTP and clicked on Verify");
        Thread.sleep(2000);
        logger.info("Clicked on consent Agree");
        sdkLoginwithMFA(un, pass, ver, Income_SDKLoginLink);
        logger.info("Entered SDK username and password");
        Thread.sleep(4000);
        CC_ProfileDataLinkPage_Client Income_ProfileDataLink = new CC_ProfileDataLinkPage_Client(getDriver());
        Income_ProfileDataLink.verifySalaryInfodisp();
        logger.info("Salary info displayed");
        Income_ProfileDataLink.verifyExcelDownload();
        logger.info("Clicked on Download");

        UtilityClass.compareIncomeCallbackDashboardData(IncomeURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);




    }

    private void sdkLoginwithMFA(String userName, String pass, String verificationCode, CC_SDKLoginPage_Client sdkLogin) {
        sdkLogin.setSDKUsernameArgyle(userName);
        sdkLogin.setSDKPasswordArgyle(pass);
        sdkLogin.clickConformbutton();
        sdkLogin.setVerificationcode(verificationCode);
        sdkLogin.clicksdkVerify();
    }


    private void verifyOTP( CC_SDKLoginPage_Client sdkLogin) throws InterruptedException {
        UtilityClass.ClientOTP();

    }

    private void clickEmailLink(String username, String password, CC_SDKLoginPage_Client mailBoxLogin) throws InterruptedException {
        mailBoxLogin.setmailosaurEmail(username);
        mailBoxLogin.clickmailosaurContinue();
        Thread.sleep(2000);
        mailBoxLogin.setsetmailosaurPassword(password);
        mailBoxLogin.clickmailosaurLogin();
        Thread.sleep(3000);
        mailBoxLogin.setRefresh();
        Thread.sleep(1000);
        mailBoxLogin.selectSDKEmail();
        mailBoxLogin.clickOnSDKLink();
    }

    private void captureCustomerDetails(String firstname, String lastname, String mon, CC_CustomerCapturePage_Client verificationPageClient) throws InterruptedException {

        logger.info("Applying customer data");
        verificationPageClient.setCellphone(mobile);
        verificationPageClient.cellphoneSearch();
        verificationPageClient.setFirstname(firstname);
        verificationPageClient.setLastName(lastname);
        verificationPageClient.setEmailid(email);
        verificationPageClient.clickCustomercaptureSubmit();
    }

    private void incomeServiceSelection(CC_CustomerCapturePage_Client verificationPageClient) {
        verificationPageClient.setVerification();
        logger.info("Clicked on service selection verification button");
        verificationPageClient.incomeVerification();
        logger.info("Clicked on income verification checkbox");
        verificationPageClient.clickServiceSelectionSubmit();
        logger.info("Clicked on service selection submit button ");
    }

    private void searchEmployer(String employer, CC_CustomerCapturePage_Client verificationPageClient) {
        verificationPageClient.setSearchfield(employer);
        verificationPageClient.selectEmployer();
        logger.info("selected employer: " + employer);
        verificationPageClient.clickEmployerSearchSubmit();
    }


}
