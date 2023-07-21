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
import com.paywallet.pageObject.CC_CustomerCapturePage_Client;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class TC_LCO_Client_Context_PDsupportFalse extends BaseClass {
    @DataProvider(name="LCOTestData")
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
        int totalrows=excel.getS3RowCount("LCODataPDSupportFalse");
        int totalcols=excel.getS3CellCount("LCODataPDSupportFalse",1);
        String LoginData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                LoginData[i-1][j]=excel.getS3CellData("LCODataPDSupportFalse", i, j);
            }
        }
        return LoginData;
    }


    @Test(dataProvider = "LCOTestData")
    public void lcoClient(String employer) throws InterruptedException {
        CC_CustomerCapturePage_Client Identity_VerificationPage = new CC_CustomerCapturePage_Client(getDriver());
        getDriver().get(ClientContextURL);
        Identity_VerificationPage.setUsername(Username);
        Identity_VerificationPage.setPassword(Password);
        Identity_VerificationPage.KeyclockclickSubmit();
        logger.info("Key Clock Login successful");
        Thread.sleep(20000);
        if (getDriver().getTitle().equalsIgnoreCase("Paywallet")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        logger.info("Page title validated Successfully");
        Identity_VerificationPage.setSearchfield(employer);
        Identity_VerificationPage.selectEmployer();
        Identity_VerificationPage.clickEmployerSearchSubmit();
        String parent = getDriver().getWindowHandle();
        Set<String> allWindow = getDriver().getWindowHandles();
        for(String child: allWindow)
        {
            if(!parent.equalsIgnoreCase(child))
            {
                getDriver().switchTo().window(child);
                Identity_VerificationPage.isNotSupported();
                logger.info("Entered employee is not supported for LCO");
                Identity_VerificationPage.clickok();
                logger.info("Clicked on OK");
            }
        }


    }
}
