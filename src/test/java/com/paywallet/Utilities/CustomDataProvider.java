package com.paywallet.Utilities;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.paywallet.Base.BaseClass;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class CustomDataProvider extends BaseClass {


    @DataProvider(name="ArgyleTestData")
    public Object[][] getArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("ArgyleData");
        int totalcols=excel.getS3CellCount("ArgyleData",1);
        String ArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                ArgyleTestData[i-1][j]=excel.getS3CellData("ArgyleData", i, j);
            }
        }
        return ArgyleTestData;

    }

    @DataProvider(name="AtomicFITestData")
    public Object[][] getAtomicFI() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AtomicFIData");
        int totalcols=excel.getS3CellCount("AtomicFIData",1);
        String AtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AtomicFITestData[i-1][j]=excel.getS3CellData("AtomicFIData", i, j);
            }
        }
        return AtomicFITestData;

    }

    @DataProvider(name="ArgylePDsupport")
    public Object[][] argylePDsupport() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("ArgylePDFalse");
        int totalcols=excel.getS3CellCount("ArgylePDFalse",1);
        String argylePDsupportTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                argylePDsupportTestData[i-1][j]=excel.getS3CellData("ArgylePDFalse", i, j);
            }
        }
        return argylePDsupportTestData;

    }

    @DataProvider(name="AtomicFIPDsupport")
    public Object[][] atomicFIPDsupport() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AtomicFIPDFalse");
        int totalcols=excel.getS3CellCount("AtomicFIPDFalse",1);
        String atomicFIPDsupportTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                atomicFIPDsupportTestData[i-1][j]=excel.getS3CellData("AtomicFIPDFalse", i, j);
            }
        }
        return atomicFIPDsupportTestData;

    }


    @DataProvider(name="ArgyleCompleteAllocation")
    public Object[][] argyleCompleteAllocation() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("ArgyleCompleteAllocation");
        int totalcols=excel.getS3CellCount("ArgyleCompleteAllocation",1);
        String ArgyleCompleteAllocationTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                ArgyleCompleteAllocationTestData[i-1][j]=excel.getS3CellData("ArgyleCompleteAllocation", i, j);
            }
        }
        return ArgyleCompleteAllocationTestData;

    }

    @DataProvider(name="AtomicFICompleteAllocation")
    public Object[][] atomicFICompleteallocation() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AtomicFICompleteAllocation");
        int totalcols=excel.getS3CellCount("AtomicFICompleteAllocation",1);
        String AtomicFICompleteAllocationTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AtomicFICompleteAllocationTestData[i-1][j]=excel.getS3CellData("AtomicFICompleteAllocation", i, j);
            }
        }
        return AtomicFICompleteAllocationTestData;

    }

    @DataProvider(name="AccountValidationFinicity")
    public Object[][] accountValidationFinicity() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AccountValidationFinicity");
        int totalcols=excel.getS3CellCount("AccountValidationFinicity",1);
        String AccountValidationFinicity[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AccountValidationFinicity[i-1][j]=excel.getS3CellData("AccountValidationFinicity", i, j);
            }
        }
        return AccountValidationFinicity;

    }

    @DataProvider(name="AccountValidationLyons")
    public Object[][] accountValidationLyons() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CustomerContextTestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AccountValidationLyons");
        int totalcols=excel.getS3CellCount("AccountValidationLyons",1);
        String AccountValidationLyons[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AccountValidationLyons[i-1][j]=excel.getS3CellData("AccountValidationLyons", i, j);
            }
        }
        return AccountValidationLyons;

    }

    @DataProvider(name="CCAPIArgyleData")
    public Object[][] CCAPIArgyleData() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CodeConvergenceAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("CCAPIArgyleData");
        int totalcols=excel.getS3CellCount("CCAPIArgyleData",1);
        String CCAPIArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                CCAPIArgyleTestData[i-1][j]=excel.getS3CellData("CCAPIArgyleData", i, j);
            }
        }
        return CCAPIArgyleTestData;

    }

    @DataProvider(name="CCAPIAtomicFIData")
    public Object[][] CCAPIAtomicFIData() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CodeConvergenceAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("CCAPIAtomicFIData");
        int totalcols=excel.getS3CellCount("CCAPIAtomicFIData",1);
        String CCAPIAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                CCAPIAtomicFITestData[i-1][j]=excel.getS3CellData("CCAPIAtomicFIData", i, j);
            }
        }
        return CCAPIAtomicFITestData;

    }

    @DataProvider(name="CCAPIPdsupportArgyleData")
    public Object[][] CCAPIPdsupportArgyleData() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CodeConvergenceAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("CCAPIPdsupportArgyleData");
        int totalcols=excel.getS3CellCount("CCAPIPdsupportArgyleData",1);
        String CCAPIPdsupportArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                CCAPIPdsupportArgyleTestData[i-1][j]=excel.getS3CellData("CCAPIPdsupportArgyleData", i, j);
            }
        }
        return CCAPIPdsupportArgyleTestData;

    }

    @DataProvider(name="CCAPIPdsupportAtomicFIData")
    public Object[][] CCAPIPdsupportAtomicFIData() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CodeConvergenceAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("CCAPIPdsupportAtomicFIData");
        int totalcols=excel.getS3CellCount("CCAPIPdsupportAtomicFIData",1);
        String CCAPIPdsupportAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                CCAPIPdsupportAtomicFITestData[i-1][j]=excel.getS3CellData("CCAPIPdsupportAtomicFIData", i, j);
            }
        }
        return CCAPIPdsupportAtomicFITestData;

    }
    @DataProvider(name="CCAPIFinicity")
    public Object[][] CCAPIFinicity() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CodeConvergenceAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("CCAPIFinicity");
        int totalcols=excel.getS3CellCount("CCAPIFinicity",1);
        String CCAPIFinicityTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                CCAPIFinicityTestData[i-1][j]=excel.getS3CellData("CCAPIFinicity", i, j);
            }
        }
        return CCAPIFinicityTestData;

    }

    @DataProvider(name="CCAPILyons")
    public Object[][] CCAPILyons() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = CodeConvergenceAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("CCAPILyons");
        int totalcols=excel.getS3CellCount("CCAPILyons",1);
        String CCAPILyonsTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                CCAPILyonsTestData[i-1][j]=excel.getS3CellData("CCAPILyons", i, j);
            }
        }
        return CCAPILyonsTestData;

    }


}
