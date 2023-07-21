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

public class TC_SDK_Client_Context_Allocation_DepositAllocation_AtomicFI extends BaseClass
{
    String email = emailstring+ randomNum +"@bdngzwdf.mailosaur.net";
    @DataProvider(name = "Allocationdata")
    public Object[][] getData() throws IOException {

        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = S3AtomicFITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel =new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("Allocation");
        int totalcols=excel.getS3CellCount("Allocation",1);
        String Allocationdata[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                Allocationdata[i-1][j]=excel.getS3CellData("Allocation", i, j);
            }
        }
        return Allocationdata;
    }

    String ClearRequestID;
    @Test( dataProvider = "Allocationdata" )
    public void DepositAllocation(String employer,String firstname, String lastname, String mon,String amount, String getpaid, String paymentdate,String username, String password,
                            String un, String pass) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockLoginandCustomerCapture(employer, firstname, lastname, mon, amount, getpaid, paymentdate);

        getDriver().get(depositallocationatomicFI);
        SDKLoginFlow(username, password, un, pass);

        UtilityClass.compareAllocationCallbackDashboardData(DepositAllocationURLPath,ClearRequestID);

        UtilityClass.metricReportValidation(ClearRequestID);

    }


    private void SDKLoginFlow(String username, String password, String un, String pass) throws InterruptedException {
        CC_SDKLoginPage_Client Allocation_SDKLoginLink = new CC_SDKLoginPage_Client(getDriver());
        Allocation_SDKLoginLink.setmailosaurEmail(username);
        Allocation_SDKLoginLink.clickmailosaurContinue();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.setsetmailosaurPassword(password);
        Allocation_SDKLoginLink.clickmailosaurLogin();
        logger.info("Mailosaur Login Done successfully");
        Thread.sleep(3000);
        Allocation_SDKLoginLink.setRefresh();
        Thread.sleep(1000);
        Allocation_SDKLoginLink.SelectSDKEmail();
        Allocation_SDKLoginLink.clickOnSDKLink();
        logger.info("SDK Email for Deposit Allocation Selected and clicked on Link");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        Allocation_SDKLoginLink.DownloadExcelData();
        logger.info("Employer Data Downloaded Successfully");
        Allocation_SDKLoginLink.clickAllocationConform();
        logger.info("Clicked on Consent checkbox and clicked on agree");
        getDriver().switchTo().frame("atomic-transact-iframe");
        Allocation_SDKLoginLink.setSDKUsernameAtomicFI(un);
        Allocation_SDKLoginLink.clickSDKcontinue();
        logger.info("SDK email entered  Successfully and Clicked on Continue");
        Thread.sleep(2000);
        Allocation_SDKLoginLink.setSDKPasswordAtomicFI(pass);
        Allocation_SDKLoginLink.clickSDKSigninbutton();
        logger.info("SDK password entered Successfully and Clicked on Continue");
        Allocation_SDKLoginLink.clickSDKConform();
        logger.info("SDK Conform clicked Successfully");
        Thread.sleep(30000);
        Allocation_SDKLoginLink.verifySDKSuccessScreen();
        logger.info("SDK Success Screen Displayed and Validated");
    }

    private void KeyClockLoginandCustomerCapture(String employer, String firstname, String lastname, String mon, String amount, String getpaid, String paymentdate) throws InterruptedException {
        CC_CustomerCapturePage_Client DepositAllocation_Allocation = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_Allocation_DepositAllocation_AtomicFI Request ID is :" );
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated Successfully");
        UtilityClass.selectEmployerClient(employer);
        DepositAllocation_Allocation.setDepositAllocation();
        DepositAllocation_Allocation.setAllocationCheckbox();
        DepositAllocation_Allocation.clickCustomercaptureSubmit();
        logger.info("Allocation Service is been selected and clicked on Submit");
        DepositAllocation_Allocation.setCellphone(mobile);
        DepositAllocation_Allocation.cellphoneSearch();
        DepositAllocation_Allocation.setFirstname(firstname);
        DepositAllocation_Allocation.setLastName(lastname);
        DepositAllocation_Allocation.setEmailid(email);
        DepositAllocation_Allocation.setNumberOfInstallment(mon);
        DepositAllocation_Allocation.setInstallmentAmount(amount);
        DepositAllocation_Allocation.setGetPaid(getpaid);
        DepositAllocation_Allocation.setFirstPaymentDate(paymentdate);
        DepositAllocation_Allocation.clickCustomercaptureSubmit();
        logger.info("Entered Details in Customer Capture Screen and clicked on submit");
        DepositAllocation_Allocation.clickGotoHome();
        logger.info("Clicked on Goto HomePage");
    }

}
