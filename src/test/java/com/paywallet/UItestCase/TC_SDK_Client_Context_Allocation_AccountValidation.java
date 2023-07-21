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

public class TC_SDK_Client_Context_Allocation_AccountValidation extends BaseClass
{
    String email = "Accountvalidation"+ randomNum +"@gbrrjess.mailosaur.net";
    @DataProvider(name = "AccountValidationdata")
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
        int totalrows = excel.getS3RowCount("AccountValidation");
        int totalcols = excel.getS3CellCount("AccountValidation",1);
        String AccountValidation[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AccountValidation[i-1][j]=excel.getS3CellData("AccountValidation", i, j);
            }
        }
        return AccountValidation;
    }

    @Test(priority=1 , dataProvider = "AccountValidationdata"  )
    public void AccountValidation(String employer, String fname, String lname, String SAnumber, String ABAnumber) throws InterruptedException {

        getDriver().get(ClientContextURL);
        KeyClockLoginandCompleteFlow(employer, fname, lname, SAnumber, ABAnumber);

        CC_SDKLoginPage_Client AccountValidation_SDKLoginpage = new CC_SDKLoginPage_Client(getDriver());
        Thread.sleep(2000);
    }

    private void KeyClockLoginandCompleteFlow(String employer, String fname, String lname, String SAnumber, String ABAnumber) throws InterruptedException {
        CC_CustomerCapturePage_Client AccountVaidation_Allocation = new CC_CustomerCapturePage_Client(getDriver());
        AccountVaidation_Allocation.setUsername(Username);
        AccountVaidation_Allocation.setPassword(Password);
        AccountVaidation_Allocation.KeyclockclickSubmit();
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        AccountVaidation_Allocation.setSearchfield(employer);
        AccountVaidation_Allocation.selectEmployer();
        AccountVaidation_Allocation.clickEmployerSearchSubmit();
        AccountVaidation_Allocation.setDepositAllocation();
        AccountVaidation_Allocation.setAccValidationCheckbox();
        AccountVaidation_Allocation.clickCustomercaptureSubmit();
        AccountVaidation_Allocation.setFirstname(fname);
        AccountVaidation_Allocation.setLastName(lname);
        AccountVaidation_Allocation.setCellphone(mobile);
        AccountVaidation_Allocation.setEmailid(email);
        Thread.sleep(10000);
        AccountVaidation_Allocation.setSANumber(SAnumber);
        AccountVaidation_Allocation.setABANumber(ABAnumber);
        Thread.sleep(5000);
        AccountVaidation_Allocation.clickCustomercaptureSubmit();
        UtilityClass.lyonsFlow(SAnumber);

    }
}
