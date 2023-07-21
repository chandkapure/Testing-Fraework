package com.paywallet.Base;

import com.paywallet.Utilities.TestConfig;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.pageObject.CC_CallBack_DashBoard_Page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ParentBaseClass {
    static TestConfig readConfig = ConfigFactory.create(TestConfig.class);
    public static String Dashboard = readConfig.Dashboard();
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

    public String browser = readConfig.browser();
    public static Logger logger;
    ArrayList<String> callbackURLlist = new ArrayList<>();
    ArrayList<String> callbackURLPathlist = new ArrayList<>();


    public static String AffordabilityURLPath;
    public static String AffordabilityURL;

    public static String EmploymentURLPath;
    public static String EmploymentURL;

    public static String IncomeURLPath;
    public static String IncomeURL;

    public static String IdentityURLPath;
    public static String IdentityURL;


    public static String DepositAllocationURLPath;
    public static String DepositAllocationURL;

    public static String NotificationURLPath;
    public static String NotificationURL;
    CC_CallBack_DashBoard_Page dashBoard;


    @BeforeSuite
    public void setUpBeforeSuite() throws InterruptedException {
        logger = Logger.getLogger("Paywallet logs");
        PropertyConfigurator.configure("log4j.properties");
        logger.info("Selected Browser is info : " + browser);
        switch (browser) {
            case "chrome":
                final ChromeOptions Chromeoptions = new ChromeOptions();
                Chromeoptions.addArguments("--window-size=1920,1080");
                Chromeoptions.addArguments("--headless");
                Chromeoptions.addArguments("--no-sandbox");
                Chromeoptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver.set(ThreadGuard.protect(new ChromeDriver()));
                logger.info("Selected Browser chrome is launched");
                getDriver().manage().window().maximize();
                getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
                getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                for (int i = 0; i < 6; i++) {
                    getDriver().get("https://webhook.site");
                    CC_CallBack_DashBoard_Page dashboard = new CC_CallBack_DashBoard_Page(getDriver());
                    Thread.sleep(10000);
                    dashboard.clickNewLink();
                    logger.info("Clicked on new link");
                    Thread.sleep(5000);
                    dashboard.clickCreatLink();
                    logger.info("Click on CreateLink");
                    Thread.sleep(5000);
                    dashboard.clickCrossheaders();
                    Thread.sleep(2000);
                    logger.info("Clicked on cross header");
                    String callbackURL = dashboard.getWebhookLink();
                    System.out.println(callbackURL);
                    String callbackURLPath = getDriver().getCurrentUrl();
                    System.out.println(callbackURLPath);
                    callbackURLlist.add(callbackURL);
                    callbackURLPathlist.add(callbackURLPath);

                }
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(ThreadGuard.protect(new FirefoxDriver()));
                logger.info("Selected Browser firefox is launched");
                break;
            case "edge":

                final EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("-window-size=1920,1080");
                edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.edgedriver().setup();
                driver.set(ThreadGuard.protect(new EdgeDriver(edgeOptions)));
                logger.info("Selected Browser edge is launched");
                getDriver().manage().window().maximize();
                getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
                getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                for (int i = 0; i < 6; i++) {
                    getDriver().get("https://webhook.site");
                    CC_CallBack_DashBoard_Page dashboard = new CC_CallBack_DashBoard_Page(getDriver());
                    Thread.sleep(15000);
                    dashboard.clickNewLink();
                    logger.info("Clicked on new link");
                    Thread.sleep(2000);
                    dashboard.clickCreatLink();
                    logger.info("Click on CreateLink");
                    Thread.sleep(3000);
                    dashboard.clickCrossheaders();
                    Thread.sleep(5000);
                    logger.info("Clicked on cross header");
                    String callbackURL = dashboard.getWebhookLink();
                    System.out.println(callbackURL);
                    String callbackURLPath = getDriver().getCurrentUrl();
                    System.out.println(callbackURLPath);
                    callbackURLlist.add(callbackURL);
                    callbackURLPathlist.add(callbackURLPath);
                }
                break;
            default:
                logger.info("Browser not supported");
                break;
        }

            AffordabilityURL = callbackURLlist.get(0);
            AffordabilityURLPath = callbackURLPathlist.get(0);
            EmploymentURL = callbackURLlist.get(1);
            EmploymentURLPath = callbackURLPathlist.get(1);
            IncomeURL = callbackURLlist.get(2);
            IncomeURLPath = callbackURLPathlist.get(2);
            IdentityURL = callbackURLlist.get(3);
            IdentityURLPath = callbackURLPathlist.get(3);
            DepositAllocationURL = callbackURLlist.get(4);
            DepositAllocationURLPath = callbackURLPathlist.get(4);
            NotificationURL = callbackURLlist.get(5);
            NotificationURLPath = callbackURLPathlist.get(5);



            getDriver().get(Dashboard);
            UtilityClass.keyClockLogin();
            dashBoard = new CC_CallBack_DashBoard_Page(getDriver());
            dashBoard.clickSettings();
            dashBoard.clickCallbackURL();
            dashBoard.setAffordabilitycallbackdata(AffordabilityURL);
            dashBoard.setEmploymentcallbackdata(EmploymentURL);
            dashBoard.setIncomecallbackdata(IncomeURL);
            dashBoard.setIdentitycallbackdata(IdentityURL);
            dashBoard.setDepositAllocationcallbackdata(DepositAllocationURL);
            dashBoard.setNotificationcallbackdata(NotificationURL);
            Thread.sleep(3000);
            dashBoard.clickcSubmit();
            System.out.println("Clicked on submit");
            Thread.sleep(2000);

    }

    @AfterSuite
    public void cleanupAfterSuite() {
        getDriver().quit();
        driver.remove();
    }

}
