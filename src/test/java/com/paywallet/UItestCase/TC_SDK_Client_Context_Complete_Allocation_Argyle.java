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
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import com.paywallet.pageObject.CC_CustomerCapturePage_Client;
import com.paywallet.Utilities.ReadExcel;

import java.io.IOException;

public class TC_SDK_Client_Context_Complete_Allocation_Argyle extends BaseClass {

    String email = emailstring+ randomNum +"@bi9tlz8h.mailosaur.net";

    @DataProvider(name = "Completeallocationdata")
    public Object[][] getData() throws IOException {

        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = S3ArgyleTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel =new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("Completeallocation");
        int totalcols=excel.getS3CellCount("Completeallocation",1);
        String Completeallocation[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                Completeallocation[i-1][j]=excel.getS3CellData("Completeallocation", i, j);
            }
        }
        return Completeallocation;
    }

    String ClearRequestID;
    @Test(priority=1 , dataProvider = "Completeallocationdata")
    public void CompleteAllocation(String employer,String firstname, String lastname, String mon,String amount,
                           String getpaid, String paymentdate, String username, String password, String un, String pass,
                           String ver1, String ver2, String accnum, String abanum,String bank,String bankuserid,String bankpassword) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockandCustomerCaptureScreen(employer, firstname, lastname, mon, amount, getpaid, paymentdate);


        getDriver().get(completedepositallocationargyle);
        SDKLoginFlow(username, password, un, pass, ver1, accnum, abanum,bank,bankuserid,bankpassword);

        UtilityClass.compareAllocationCallbackDashboardData(DepositAllocationURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);



    }

    private void SDKLoginFlow(String username, String password, String un, String pass, String ver1, String accnum, String abanum,
                              String bank,String bankuserid,String bankpassword) throws InterruptedException {
        CC_SDKLoginPage_Client CompleteAllocation_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        CompleteAllocation_SDKLoginLink.setmailosaurEmail(username);
        CompleteAllocation_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        CompleteAllocation_SDKLoginLink.setsetmailosaurPassword(password);
        CompleteAllocation_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur Login Done successfully");
        Thread.sleep(3000);
        CompleteAllocation_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        CompleteAllocation_SDKLoginLink.SelectSDKEmail();
        CompleteAllocation_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK Email for Deposit Allocation Selected and clicked on Link");
        Thread.sleep(4000);
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        CompleteAllocation_SDKLoginLink.DownloadExcelData();
        logger.info("Employer Data Validated Successfully");
        CompleteAllocation_SDKLoginLink.clickAllocationConform();
        logger.info("Clicked on Consent checkbox and clicked on agree");
        CompleteAllocation_SDKLoginLink.setSDKUsernameArgyle(un);
        CompleteAllocation_SDKLoginLink.setSDKPasswordArgyle(pass);
        logger.info("SDK email and password entered  Successfully");
        CompleteAllocation_SDKLoginLink.clickConformbutton();
        logger.info("SDK login Done Successfully");
        CompleteAllocation_SDKLoginLink.setVerificationcode(ver1);
        CompleteAllocation_SDKLoginLink.clicksdkVerify();
        CompleteAllocation_SDKLoginLink.setConfirm();
        logger.info("Verification Code Entered Successfully and Clicked on Conform");
        Thread.sleep(25000);
        CompleteAllocation_SDKLoginLink.verifySDKSuccessScreen();
        logger.info("SDK Success Screen Validated");
        CompleteAllocation_SDKLoginLink.setVerifyaccount();
        logger.info("Clicked on SetVerifyAccount");
        Thread.sleep(2000);
        CompleteAllocation_SDKLoginLink.setSANumber(accnum);
        CompleteAllocation_SDKLoginLink.setABANumber(abanum);
        CompleteAllocation_SDKLoginLink.setSubmit();
        logger.info("ABA and Acc Number Entered Successfully and clicked on Submit");
        UtilityClass.ClientfinicityFlow(accnum,bank,bankuserid,bankpassword);
    }

    private void KeyClockandCustomerCaptureScreen(String employer, String firstname, String lastname, String mon, String amount, String getpaid, String paymentdate) throws InterruptedException {
        CC_CustomerCapturePage_Client CompleteAllocation = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Complete_Allocation_Argyle Request ID is :");
        Thread.sleep(5000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated Successfully");
        UtilityClass.selectEmployerClient(employer);
        CompleteAllocation.setDepositAllocation();
        CompleteAllocation.setAllocationCheckbox();
        CompleteAllocation.setAccValidationCheckbox();
        CompleteAllocation.clickCustomercaptureSubmit();
        logger.info("Complete Allocation Service is been selected and clicked on Submit");
        CompleteAllocation.setCellphone(mobile);
        CompleteAllocation.cellphoneSearch();
        CompleteAllocation.setFirstname(firstname);
        CompleteAllocation.setLastName(lastname);
        CompleteAllocation.setEmailid(email);
        CompleteAllocation.setNumberOfInstallment(mon);
        CompleteAllocation.setInstallmentAmount(amount);
        CompleteAllocation.setGetPaid(getpaid);
        CompleteAllocation.setFirstPaymentDate(paymentdate);
        CompleteAllocation.clickCustomercaptureSubmit();
        logger.info("Entered Details in Customer Capture Screen and clicked on submit");
        CompleteAllocation.clickGotoHome();
        logger.info("Clicked on Goto HomePage");
    }

}
