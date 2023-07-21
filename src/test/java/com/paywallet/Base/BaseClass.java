package com.paywallet.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.paywallet.POJOClasses.CCGenerateToken;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass extends ParentBaseClass {

    public ExtentReports extent;
    public ExtentSparkReporter spark;
    public ExtentTest log ;
    public static RequestSpecification httpRequest;

//    ---------------------UI-------------------------------
    public String incomeatomicFI = readConfig.mailosaurIncomeURLAtomicFI();
    public String incomeargyle = readConfig.mailosaurIncomeURLArgyle();
    public String employmentatomicFI = readConfig.mailosaurEmploymentURLAtomicFI();
    public String employmentargyle = readConfig.mailosaurEmploymentURLArgyle();
    public String identityatomicFI = readConfig.mailosaurIdentityURLAtomicFI();
    public String identityargyle = readConfig.mailosaurIdentityURLArgyle();
    public String affordebilityatomicFI = readConfig.mailosaurAffordabilityURLAtomicFI();
    public String affordebilityargyle = readConfig.mailosaurAffordabilityURLArgyle();
    public String completeverificationatomicFI = readConfig.mailosaurcompleteVerificationAtomicFI();
    public String completeverificationargyle = readConfig.mailosaurcompleteVerificationArgyle();
    public String completedepositallocationatomicFI = readConfig.mailosaurcompleteAllocationAtomicFI();
    public String completedepositallocationargyle = readConfig.mailosaurcompleteAllocationArgyle();
    public String depositallocationatomicFI = readConfig.mailosaurDepositAllocationAtomicFI();
    public String depositallocationargyle = readConfig.mailosaurDepositAllocationArgyle();
    public String verificationdisagree = readConfig.mailosaurverificationDisagree();
    public String PostrestartArgyle = readConfig.mailosaurallocationDisagree();
    public String ClientpdsupportfalseallocationArgyle = readConfig.mailosaurClientpdsupportfalseallocationArgyle();

    public String ClientpdsupportfalseallocationAtomicFI = readConfig.mailosaurClientpdsupportfalseallocationAtomicFI();

    public String CustomerpdsupportfalseallocationArgyle = readConfig.mailosaurCustomerpdsupportfalseAllocationArgyle();

    public String CustomerpdsupportfalseallocationAtomicFI = readConfig.mailosaurCustomerpdsupportfalseAllocationAtomicFI();

//    ---------------API Request ------------------------
    public String RequestIdd = readConfig.APIGenerateRequestID();
    public String EmployerTypeAhead = readConfig.APIEmployerTypeAhead();
    public String SeleectEmployer = readConfig.APISelectEmployer();

    public String RegisterAPI = readConfig.APIRegister();
    public String UpdateAPI = readConfig.APIUpdate();

    public static String APIURL =readConfig.APIURL();

    public static String APIKey = readConfig.APIKey();



//    --------------- S3 Bucket Details -----------------
    public String S3AccessKey = readConfig.S3AccessKey();

    public String S3SecretKey = readConfig.S3SecretKey();

    public String S3Bucketname = readConfig.S3Bucketname();

    public String CustomerContextTestData = readConfig.S3CustomerContextTestData();

    public String WrapperAPITestData = readConfig.S3WrapperAPITestData();

    public String CodeConvergenceAPITestData = readConfig.S3CodeConvergenceTestData();

    public String S3ArgyleTestData = readConfig.S3ClientArgyleTestData();

    public String S3AtomicFITestData = readConfig.S3ClientAtomicFITestData();

//    ------------------API-------------------------

    public  String APIIdentityVerificationurlArgyle = readConfig.mailosaurAPIIdentityURLArgyle();
    public  String APIIncomeVerificationurlArgyle = readConfig.mailosaurAPIIncomeURLArgyle();
    public  String APIEmploymentVerificationurlArgyle = readConfig.mailosaurAPIEmploymentURLArgyle();
    public  String APIAffordebilityVerificationurlArgyle = readConfig.mailosaurAPIAffordabilityURLArgyle();
    public String APIDirectAllocationArgyle =  readConfig.mailosaurAPIDirectAllocationURLArgyle();
    public String APIDirectAllocationAtomicFI = readConfig.mailosaurAPIDirectAllocationURLAtomicFI();
    public  String APIIdentityVerificationurlAtomicFI = readConfig.mailosaurAPIIdentityURLAtomicFI();
    public  String APIIncomeVerificationurlAtomicFI = readConfig.mailosaurAPIIncomeURLAtomicFI();
    public  String APIEmploymentVerificationurlAtomicFI = readConfig.mailosaurAPIEmploymentURLAtomicFI();
    public  String APIAffordebilityVerificationurlAtomicFI = readConfig.mailosaurAPIAffordabilityURLAtomicFI();

    public String APIaccownershipfinicity = readConfig.mailosaurAPIAccountOwnershipFinicity();

    public String OkinusAPISkipSDKLoginArgyle = readConfig.mailosaurAPIOkinusSkipSDKLoginArgyle();

    public String OkinusAPISkipSDKLoginAtomicFI = readConfig.mailosaurAPIOkinusSkipSDKLoginAtomicFI();

    public String OkinusAPIReuseRequestArgyle = readConfig.mailosaurAPIOkinusReuserequestArgyle();

    public String OkinusAPIReuseRequestAtomicFI = readConfig.mailosaurAPIOkinusReuserequestAtomicFI();

    public String PostrestartAtomicFI = readConfig.mailosaurAPILCOEmploymentAtomicFI();

    public String LCOAPICancelAtomicFI = readConfig.mailosaurAPILCOCancelAtomicFI();

    public String APIUpdateProfileVVAtomicFI = readConfig.mailosaurAPILCOSkipMultipleLoginAtomicFI();

    public String PostRestartAtomicFI = readConfig.mailosaurAPILCORequestReuseAtomicFI();
    public String APIVerificationRetry =readConfig.mailosaurAPIVerificationRetry();

    public String APIUpdateCancelAtomicFI = readConfig.mailosaurAPIUpdateandCancelAtomicFI();

    public String APIUpdateCancelArgyle = readConfig.mailosaurAPIUpdateandCancelArgyle();
    public String PostRestartArgyle = readConfig.mailosaurAPILCOEmploymentArgyle();

    public String APIAllocationCancelArgyle = readConfig.mailosaurAPILCOCancelArgyle();

    public String APILCOSkipMultipleLoginArgyle = readConfig.mailosaurAPILCOSkipMultipleLoginArgyle();

    public String APIAccOwnershipLyons = readConfig.mailosaurAPIAccountOwnershipLyons();
    public String APIPDsupportfalseArgyle = readConfig.mailosaurAPIPDsupportFalseArgyle();
    public String APIPDsupporttfalseAtomicFI = readConfig.mailosaurAPIPDsupportFalseAtomicFI();

    public String APIAllocationRetry = readConfig.mailosaurAPIAllocationRetry();
    public static String TokenURL = readConfig.TokenURL();
    public static String clientid =readConfig.ClientID();
    public static String clientsecret = readConfig.Client_secret();


    public static String Username = readConfig.Username();
    public static String Password = readConfig.Password();
    public String CustomerContextURL = readConfig.CustomerContextURL();
    public String ClientContextURL = readConfig.ClientContextURL();



    public static Logger logger;


    public  String mobile = getRandomNumber()+getRunID();
    public  String emailstring = getRandomemailname();
    public  static String randomNum = getRunID();
    public  static final String getRunID()
    {
//        return (randomEmailIdNumber!=null)?randomEmailIdNumber:RandomStringUtils.randomNumeric(5);
        if(randomNum !=null)
        {
            return randomNum;
        }
        else
        {
            return RandomStringUtils.randomNumeric(4);
        }
    }

    public static String getRandomNumber()
    {
        return  RandomStringUtils.randomNumeric(6);
    }


    public static String getRandomemailname(){return RandomStringUtils.randomAlphabetic(4);}

    public static final String WrapperauthToken = generateWrapperToken();
    public static String generateWrapperToken()
    {
        logger = Logger.getLogger("Paywallet logs");
        PropertyConfigurator.configure("log4j.properties");

        if(WrapperauthToken !=null)
        {
            return WrapperauthToken;
        }
        else
        {
//        Generate WrapperAPI Token
           RequestSpecification httpRequest = RestAssured.given();
            logger.info("Client id is "+clientid);
            logger.info("Client secret is "+clientsecret);
            logger.info("Selected environment is" + TokenURL );

            Response tokenresponse = httpRequest.contentType("application/x-www-form-urlencoded; charset=utf-8")
                    .formParam("grant_type", "client_credentials")
                    .formParam("client_id",clientid )
                    .formParam("client_secret", clientsecret)
                    .when().accept("application/json").post(TokenURL+"/auth/realms/paywallet/protocol/openid-connect/token");
            String body = tokenresponse.getBody().asString();
            logger.info(body);
            String Token = tokenresponse.jsonPath().get("access_token").toString();
            String authToken = "Bearer " + Token;
            int GenerateTokenstatuscode = tokenresponse.getStatusCode();
            logger.info(String.valueOf(GenerateTokenstatuscode));
            Assert.assertEquals(GenerateTokenstatuscode, 200);
            return authToken;
        }
    }

    public static final String CodeConvergenceToken = generateCodeConvergenceToken();


    public static String generateCodeConvergenceToken()
    {
        logger = Logger.getLogger("Paywallet logs");
        PropertyConfigurator.configure("log4j.properties");

        if(CodeConvergenceToken !=null)
        {
            return CodeConvergenceToken;
        }
        else
        {
//        Generate CodeConvergence Token

            logger.info("Client id is "+clientid);
            logger.info("Client secreat is "+clientsecret);
            logger.info("Selected environment is" + TokenURL );

            CCGenerateToken tokenbody = new CCGenerateToken(clientid,clientsecret);
            RequestSpecification httpRequest = RestAssured.given();
            Response tokenresponse = httpRequest.header("x-api-key", APIKey)
                    .contentType("application/json")
                    .body(tokenbody)
                    .post(APIURL+"/api/v1/sdk/auth");
            String body = tokenresponse.getBody().asString();
            logger.info("Body is "+body);
            String Token = tokenresponse.jsonPath().get("data").toString();
            String authToken = "Bearer " + Token;
            int GenerateTokenstatuscode = tokenresponse.getStatusCode();
            logger.info(String.valueOf(GenerateTokenstatuscode));
            Assert.assertEquals(GenerateTokenstatuscode, 200);
            return authToken;
        }
    }

    public String browser = readConfig.browser();



    public static DevTools devtools;
    @BeforeMethod
    public void setup()
    {

        logger.info("Selected Browser is info : " + browser);
        switch (browser){
            case "chrome":
                final ChromeOptions Chromeoptions = new ChromeOptions();
                Chromeoptions.addArguments("--window-size=1920,1080");
                Chromeoptions.addArguments("--headless");
                Chromeoptions.addArguments("--no-sandbox");
                Chromeoptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver.set(ThreadGuard.protect(new ChromeDriver(Chromeoptions)));
                logger.info("Selected Browser chrome is launched"+ " " + "Run ID is : " + " " + randomNum);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(ThreadGuard.protect(new FirefoxDriver()));
                logger.info("Selected Browser firefox is launched" + " " + "Run ID is : " + " " + randomNum);
                break;
            case "edge":
                final EdgeOptions edgeOptions =new EdgeOptions();
                edgeOptions.addArguments("-window-size=1920,1080");
                edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.edgedriver().setup();
                driver.set(ThreadGuard.protect(new EdgeDriver(edgeOptions)));
                logger.info("Selected Browser edge is launched" + " " + "Run ID is : " + " " + randomNum);
                break;
            default:
                logger.info("Browser not supported");
                break;
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    @AfterMethod
    public void tearDown(ITestResult result)
    {
        getDriver().quit();
    }

    public String getScreenshot(String testName) {

        File sourceScreenshotFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        File destinationScreenshotFile = new File(System.getProperty("user.dir")+"/Screenshots/"+testName+".png");
        logger.info("Screenshot taken for testcase: " + testName);
        try {
            FileUtils.copyFile(sourceScreenshotFile, destinationScreenshotFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String path = destinationScreenshotFile.getAbsolutePath();
        byte[] file = new byte[0];
        try {
            file = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64Image = Base64.encodeBase64String(file);

        return base64Image;

    }

    public String getScreenshot(String nameoffolder,String testName) {


        File sourceScreenshotFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        File F = new File("Screenshots/"+nameoffolder);
        F.mkdir();
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir")).append("/Screenshots/").append(nameoffolder).append("/").append(testName).append(".png");
        File destinationScreenshotFile = new File(sb.toString());
        logger.info("Screenshot taken for testcase: " + testName);
        try {
            FileUtils.copyFile(sourceScreenshotFile, destinationScreenshotFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String path = destinationScreenshotFile.getAbsolutePath();
        byte[] file = new byte[0];
        try {
            file = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64Image = Base64.encodeBase64String(file);

        return base64Image;

    }



    public String randomString()
    {
        String generatedstring= RandomStringUtils.randomAlphabetic(4);
        return(generatedstring);
    }

    public static String randomNumber() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }

}

