package com.paywallet.Listeners;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.paywallet.Base.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners extends BaseClass implements ITestListener {



        public void onStart(ITestContext testContext)
        {
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
//            String repName="PaywalletExtentReport"+timeStamp+".html";
//            htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");

            extent=new ExtentReports();
            spark= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+"PaywalletExtentReport.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("PayWallet Automation Report");
            extent.setSystemInfo("OS : ", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version : ", System.getProperty("java.version"));
            extent.setSystemInfo("OS Architecture :" , System.getProperty("os.arch"));
            extent.attachReporter(spark);

        }


        public void onTestSuccess(ITestResult tr)
        {
            String PassTest = String.valueOf(tr.getTestClass());
            log =extent.createTest(PassTest); // create new entry in th report
            log.log(Status.PASS, MarkupHelper.createLabel(PassTest, ExtentColor.GREEN));
            log.log(Status.PASS, PassTest + "  " +"got Passed");

        }

        public void onTestFailure( ITestResult tr)
        {
            String FailTest = String.valueOf(tr.getTestClass());
            log =extent.createTest(FailTest); // create new entry in the report
            log.log(Status.FAIL,MarkupHelper.createLabel(FailTest,ExtentColor.RED)); // send the failed information to the report with RED color highlighted
            log.fail(tr.getThrowable() , MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot(FailTest)).build());
            log.log(Status.FAIL, FailTest + "  " +"got Failed");
        }

        public void onTestSkipped(ITestResult trd)
        {
            String SkipTest = String.valueOf(trd.getTestClass());
            log =extent.createTest(SkipTest); // create new entry in th report
            log.log(Status.SKIP,MarkupHelper.createLabel(SkipTest,ExtentColor.ORANGE));
            log.log(Status.SKIP, SkipTest + " " +"got Skipped");
        }

        public void onFinish(ITestContext testContext)
        {
            extent.flush();
        }
    }



