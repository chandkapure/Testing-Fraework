package com.paywallet.UItestCase;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.ReadExcel;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.pageObject.CC_CustomerCapturePage_Client;
import com.paywallet.pageObject.CC_ProfileDataLinkPage_Client;
import com.paywallet.pageObject.CC_SDKLoginPage_Client;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class TC_SDK_Client_Context_PDSupportFalseAllocationAtomicFI extends BaseClass {

    String email = emailstring+ randomNum + "@rx56dvdx.mailosaur.net";

    String ClearRequestID;
    @DataProvider(name = "PDsupportFlasedata")
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
        int totalrows = excel.getS3RowCount("PDsupportFlase");
        int totalcols = excel.getS3CellCount("PDsupportFlase", 1);
        String PDsupportFlaseData[][] = new String[totalrows][totalcols];
        for (int i = 1; i <= totalrows; i++) //1
        {
            for (int j = 0; j < totalcols; j++) //0
            {
                PDsupportFlaseData[i - 1][j] = excel.getS3CellData("PDsupportFlase", i, j);
            }
        }
        return PDsupportFlaseData;
    }

    @Test(priority = 1, dataProvider = "PDsupportFlasedata")
    public void PDSupportFalse(String employer, String firstname, String lastname, String mon, String amount, String getpaid, String paymentdate, String username, String password, String un, String pass) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockLoginandCustomerCapureFlow(employer, firstname, lastname, mon, amount, getpaid, paymentdate);

        getDriver().get(ClientpdsupportfalseallocationAtomicFI);
        SDKLoginFlow(username, password, un, pass);

        getDriver().get(ClientpdsupportfalseallocationAtomicFI);
        EmailVerificationFlow();

    }

    private void EmailVerificationFlow() throws InterruptedException {
        CC_ProfileDataLinkPage_Client profiledata = new CC_ProfileDataLinkPage_Client(getDriver());
        Thread.sleep(4000);
        profiledata.setRefresh();
        Thread.sleep(1000);
        profiledata.setSelectEmail2();
        profiledata.scroll();
        Thread.sleep(3000);
        profiledata.clickOnProfiledataLink();
        String parent = getDriver().getWindowHandle();
        Set<String> allWindow = getDriver().getWindowHandles();
        for(String child: allWindow)
        {
            if(!parent.equalsIgnoreCase(child))
            {
                getDriver().switchTo().window(child);
                UtilityClass.ClientOTP();
                profiledata.selectCheckbox();
                profiledata.setSubmit();
            }
        }
    }

    private void SDKLoginFlow(String username, String password, String un, String pass) throws InterruptedException {
        CC_SDKLoginPage_Client PDSupportFalse_SDKLoginPage = new CC_SDKLoginPage_Client(getDriver());
        PDSupportFalse_SDKLoginPage.setmailosaurEmail(username);
        PDSupportFalse_SDKLoginPage.clickmailosaurContinue();
        Thread.sleep(2000);
        PDSupportFalse_SDKLoginPage.setsetmailosaurPassword(password);
        PDSupportFalse_SDKLoginPage.clickmailosaurLogin();
        logger.info(" Mailosour login was Successful");
        Thread.sleep(3000);
        PDSupportFalse_SDKLoginPage.setRefresh();
        Thread.sleep(1000);
        PDSupportFalse_SDKLoginPage.selectSDKEmail();
        PDSupportFalse_SDKLoginPage.clickOnSDKLink();
        logger.info("SDK email selected, and clicked on SDK login link");
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        getDriver().switchTo().frame("atomic-transact-iframe");
        PDSupportFalse_SDKLoginPage.setSDKUsernameAtomicFI(un);
        Thread.sleep(2000);
        PDSupportFalse_SDKLoginPage.clickSDKcontinue();
        logger.info("Entered Username and clicked on continue");
        Thread.sleep(2000);
        PDSupportFalse_SDKLoginPage.setSDKPasswordAtomicFI(pass);
        PDSupportFalse_SDKLoginPage.clickSDKSigninbutton();
        logger.info("Entered Password and clicked on Signing Button");
        Thread.sleep(30000);
        PDSupportFalse_SDKLoginPage.setPendingstatus();
        logger.info("Pending Status Verified");
    }

    private void KeyClockLoginandCustomerCapureFlow(String employer, String firstname, String lastname, String mon, String amount, String getpaid, String paymentdate) throws InterruptedException {
        CC_CustomerCapturePage_Client PDSupportFalse = new CC_CustomerCapturePage_Client(getDriver());
        ClearRequestID = UtilityClass.keyClockLoginWithClearRequestID("TC_SDK_Client_Context_PDSupportFalseAllocationAtomicFI Request ID is :");
        Thread.sleep(10000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated Successfully");
        UtilityClass.selectEmployerClient(employer);
        PDSupportFalse.setDepositAllocation();
        PDSupportFalse.setAllocationCheckbox();
        PDSupportFalse.clickCustomercaptureSubmit();
        logger.info("Affordability verification selected Successfully and clicked on Submit");
        PDSupportFalse.setCellphone(mobile);
        PDSupportFalse.cellphoneSearch();
        PDSupportFalse.setFirstname(firstname);
        PDSupportFalse.setLastName(lastname);
        PDSupportFalse.setEmailid(email);
        PDSupportFalse.setNumberOfInstallment(mon);
        PDSupportFalse.setInstallmentAmount(amount);
        PDSupportFalse.setGetPaid(getpaid);
        PDSupportFalse.setFirstPaymentDate(paymentdate);
        PDSupportFalse.clickCustomercaptureSubmit();
        logger.info("Details Added in customer capture and clicked on Submit");
        PDSupportFalse.clickGotoHome();
        logger.info("Clicked on Goto HomePage");
    }


}
