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

public class WrapperAPITestData extends BaseClass {


    @DataProvider(name="IdentityArgyle")
    public Object[][] IdentityArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("IdentityArgyle");
        int totalcols=excel.getS3CellCount("IdentityArgyle",1);
        String IdentityArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                IdentityArgyleTestData[i-1][j]=excel.getS3CellData("IdentityArgyle", i, j);
            }
        }
        return IdentityArgyleTestData;

    }

    @DataProvider(name="IdentityAtomicFI")
    public Object[][] IdentityAtomicFI() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("IdentityAtomicFI");
        int totalcols=excel.getS3CellCount("IdentityAtomicFI",1);
        String IdentityAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                IdentityAtomicFITestData[i-1][j]=excel.getS3CellData("IdentityAtomicFI", i, j);
            }
        }
        return IdentityAtomicFITestData;

    }

    @DataProvider(name="IncomeArgyle")
    public Object[][] IncomeArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("IncomeArgyle");
        int totalcols=excel.getS3CellCount("IncomeArgyle",1);
        String IncomeArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                IncomeArgyleTestData[i-1][j]=excel.getS3CellData("IncomeArgyle", i, j);
            }
        }
        return IncomeArgyleTestData;

    }

    @DataProvider(name="IncomeAtomicFI")
    public Object[][] IncomeAtomicFI() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("IncomeAtomicFI");
        int totalcols=excel.getS3CellCount("IncomeAtomicFI",1);
        String IncomeAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                IncomeAtomicFITestData[i-1][j]=excel.getS3CellData("IncomeAtomicFI", i, j);
            }
        }
        return IncomeAtomicFITestData;

    }

    @DataProvider(name="EmploymentArgyle")
    public Object[][] EmploymentArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("EmploymentArgyle");
        int totalcols=excel.getS3CellCount("EmploymentArgyle",1);
        String EmploymentArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                EmploymentArgyleTestData[i-1][j]=excel.getS3CellData("EmploymentArgyle", i, j);
            }
        }
        return EmploymentArgyleTestData;

    }

    @DataProvider(name="EmploymentAtomicFI")
    public Object[][] EmploymentAtomicFI() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("EmploymentAtomicFI");
        int totalcols=excel.getS3CellCount("EmploymentAtomicFI",1);
        String EmploymentAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                EmploymentAtomicFITestData[i-1][j]=excel.getS3CellData("EmploymentAtomicFI", i, j);
            }
        }
        return EmploymentAtomicFITestData;

    }

    @DataProvider(name="AffordebilityArgyle")
    public Object[][] AffordebilityArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AffordebilityArgyle");
        int totalcols=excel.getS3CellCount("AffordebilityArgyle",1);
        String AffordebilityArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AffordebilityArgyleTestData[i-1][j]=excel.getS3CellData("AffordebilityArgyle", i, j);
            }
        }
        return AffordebilityArgyleTestData;

    }

    @DataProvider(name="AffordebilityAtomicFI")
    public Object[][] AffordebilityAtomicFI() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AffordebilityAtomicFI");
        int totalcols=excel.getS3CellCount("AffordebilityAtomicFI",1);
        String AffordebilityAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AffordebilityAtomicFITestData[i-1][j]=excel.getS3CellData("AffordebilityAtomicFI", i, j);
            }
        }
        return AffordebilityAtomicFITestData;

    }

    @DataProvider(name="DirectAllocationArgyle")
    public Object[][] DirectAllocationArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("DirectAllocationArgyle");
        int totalcols=excel.getS3CellCount("DirectAllocationArgyle",1);
        String DirectAllocationArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                DirectAllocationArgyleTestData[i-1][j]=excel.getS3CellData("DirectAllocationArgyle", i, j);
            }
        }
        return DirectAllocationArgyleTestData;

    }

    @DataProvider(name="DirectAllocationAtomicFI")
    public Object[][] DirectAllocationAtomicFI() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("DirectAllocationAtomicFI");
        int totalcols=excel.getS3CellCount("DirectAllocationAtomicFI",1);
        String DirectAllocationAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                DirectAllocationAtomicFITestData[i-1][j]=excel.getS3CellData("DirectAllocationAtomicFI", i, j);
            }
        }
        return DirectAllocationAtomicFITestData;

    }

    @DataProvider(name="VerificationRetryArgyle")
    public Object[][] VerificationRetryArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("VerificationRetryArgyle");
        int totalcols=excel.getS3CellCount("VerificationRetryArgyle",1);
        String VerificationRetryArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                VerificationRetryArgyleTestData[i-1][j]=excel.getS3CellData("VerificationRetryArgyle", i, j);
            }
        }
        return VerificationRetryArgyleTestData;

    }

    @DataProvider(name="AllocationRetry")
    public Object[][] AllocationRetry() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AllocationRetry");
        int totalcols=excel.getS3CellCount("AllocationRetry",1);
        String AllocationRetryArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AllocationRetryArgyleTestData[i-1][j]=excel.getS3CellData("AllocationRetry", i, j);
            }
        }
        return AllocationRetryArgyleTestData;

    }

    @DataProvider(name="UpdateCanceArgyle")
    public Object[][] UpdateCanceArgyle() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("UpdateCanceArgyle");
        int totalcols=excel.getS3CellCount("UpdateCanceArgyle",1);
        String UpdateCanceArgyleTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                UpdateCanceArgyleTestData[i-1][j]=excel.getS3CellData("UpdateCanceArgyle", i, j);
            }
        }
        return UpdateCanceArgyleTestData;

    }

    @DataProvider(name="UpdateCancelAtomicFI")
    public Object[][] UpdateCancelAtomicFI() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("UpdateCancelAtomicFI");
        int totalcols=excel.getS3CellCount("UpdateCancelAtomicFI",1);
        String UpdateCancelAtomicFITestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                UpdateCancelAtomicFITestData[i-1][j]=excel.getS3CellData("UpdateCancelAtomicFI", i, j);
            }
        }
        return UpdateCancelAtomicFITestData;

    }

    @DataProvider(name="AccountOwnership")
    public Object[][] AccountOwnership() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("AccountOwnership");
        int totalcols=excel.getS3CellCount("AccountOwnership",1);
        String AccountOwnershipTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                AccountOwnershipTestData[i-1][j]=excel.getS3CellData("AccountOwnership", i, j);
            }
        }
        return AccountOwnershipTestData;

    }

    @DataProvider(name="VerificationInProgress")
    public Object[][] VerificationInProgress() throws IOException
    {
        String accessKey = S3AccessKey;
        String secretKey = S3SecretKey;
        String bucketname = S3Bucketname;
        String filename = WrapperAPITestData;

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1").build();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketname, filename));
        S3ObjectInputStream Objectdata = object.getObjectContent();
        ReadExcel excel = new ReadExcel(Objectdata);
        int totalrows=excel.getS3RowCount("VerificationInProgress");
        int totalcols=excel.getS3CellCount("VerificationInProgress",1);
        String VerificationInProgressTestData[][]=new String[totalrows][totalcols];
        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                VerificationInProgressTestData[i-1][j]=excel.getS3CellData("VerificationInProgress", i, j);
            }
        }
        return VerificationInProgressTestData;

    }



}
