package com.paywallet.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CC_CallBack_DashBoard_Page {
    WebDriver ldriver;

    public CC_CallBack_DashBoard_Page(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "(//div[@class='MuiListItemIcon-root css-1op4pi2-MuiListItemIcon-root'])[2]")
    WebElement SettingButton;

    public void clickSettings() {
        SettingButton.click();
    }

    @FindBy(xpath = "//span[contains(text(),'Callback URLs')]")
    WebElement callbackURL;

    public void clickCallbackURL() {
        callbackURL.click();
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitbutton;

    public void clickcSubmit() {
        Actions actions = new Actions(ldriver);
        actions.moveToElement(submitbutton).click(submitbutton).build().perform();

    }

    @FindBy(xpath = "//input[@name='affordability']")
    WebElement Affordability;

    public void setAffordabilitycallbackdata(String CallbackURL) throws InterruptedException {
        Actions actions = new Actions(ldriver);
        Thread.sleep(4000);
        actions.moveToElement(Affordability).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(2000);
        actions.moveToElement(Affordability).sendKeys(CallbackURL).build().perform();

    }

    @FindBy(xpath = "//input[@name='income']")
    WebElement Income;
    public void setIncomecallbackdata(String CallbackURL) throws InterruptedException {
        Actions actions = new Actions(ldriver);
        Thread.sleep(4000);
        actions.moveToElement(Income).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(2000);
        actions.moveToElement(Income).sendKeys(CallbackURL).build().perform();
    }

    @FindBy(xpath = "//input[@name='employment']")
    WebElement Employment;

    public void setEmploymentcallbackdata(String CallbackURL) throws InterruptedException {
        Actions actions = new Actions(ldriver);
        Thread.sleep(4000);
        actions.moveToElement(Employment).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(2000);
        actions.moveToElement(Employment).sendKeys(CallbackURL).build().perform();

    }

    @FindBy(xpath = "//input[@name='identity']")
    WebElement Identity;

    public void setIdentitycallbackdata(String CallbackURL) throws InterruptedException {
        Actions actions = new Actions(ldriver);
        Thread.sleep(4000);
        actions.moveToElement(Identity).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(2000);
        actions.moveToElement(Identity).sendKeys(CallbackURL).build().perform();

    }

    @FindBy(xpath = "//input[@name='allocation']")
    WebElement Allocation;

    public void setDepositAllocationcallbackdata(String CallbackURL) throws InterruptedException {
        Actions actions = new Actions(ldriver);
        Thread.sleep(4000);
        actions.moveToElement(Allocation).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(2000);
        actions.moveToElement(Allocation).sendKeys(CallbackURL).build().perform();

    }

    @FindBy(xpath = "//input[@name='notification']")
    WebElement Notification;

    public void setNotificationcallbackdata(String CallbackURL) throws InterruptedException {
        Actions actions = new Actions(ldriver);
        Thread.sleep(4000);
        actions.moveToElement(Notification).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(2000);
        actions.moveToElement(Notification).sendKeys(CallbackURL).build().perform();

    }

    @FindBy(xpath = "//input[@placeholder='Search by request id & mobile number']")
    WebElement Searchfield;
    @FindBy(xpath = "(//span[@class='MuiTouchRipple-root css-8je8zh-MuiTouchRipple-root'])[3]")
    WebElement searchbutton;

    public void searchForRequestIDAndSelectTheRequest(String requestid) throws InterruptedException {
        System.out.println("Request ID entered is " + requestid);
        Actions actions = new Actions(ldriver);
        actions.moveToElement(Searchfield).click().sendKeys(requestid).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
        actions.moveToElement(searchbutton).click(searchbutton).build().perform();
        Thread.sleep(2000);
        WebElement elementwithrequestID = ldriver.findElement(By.xpath("//span[contains(text(),'"+requestid+"')]//parent::span//parent::div//parent::div[@class='MuiCardHeader-root css-185gdzj-MuiCardHeader-root']/following-sibling::div//child::div//child::div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 MuiGrid-grid-md-4 css-gj1fbr-MuiGrid-root']"));
        actions.moveToElement(elementwithrequestID).click().build().perform();
        System.out.println("Selected the RequestID");
        Thread.sleep(5000);

    }

    @FindBy(xpath = "//span[contains(text(),'Employment Details')]")
    WebElement DashEmployment;
    public void clickEmploymentDashboardDetails()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(DashEmployment));
        DashEmployment.click();
    }

    @FindBy(xpath = "//span[contains(text(),'Borrower Payroll')]")
    WebElement DashIdentity;
    public void clickIdentityDashboardDetails()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(DashIdentity));
        DashIdentity.click();
    }

    @FindBy(xpath = "//span[contains(text(),'Salary Information')]")
    WebElement DashIncome;
    public void clickIncomeDashboardDetails()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(DashIncome));
        DashIncome.click();
    }

    @FindBy(xpath = "//span[contains(text(),'Affordability')]")
    WebElement DashAffordability;
    public void clickAffordabilityDashboardDetails()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(DashAffordability));
        DashAffordability.click();
    }

    @FindBy(xpath = "//span[contains(text(),'Pay Allocation')]")
    WebElement DashAllocation;
    public void clickAllocationDashboardDetails()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(DashAllocation));
        DashAllocation.click();
    }

    @FindBy(xpath = "(//a[@class='select ng-binding'])[1]")
    WebElement SelectThecallback;

    public void selectCallback() throws InterruptedException {
        Thread.sleep(5000);
        SelectThecallback.click();

    }


    public String getJsonResponse() {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(60));
        String json = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@class='req-content ng-binding ng-scope wordwrapDisable']"))).getText();
        return json;

    }

    public void checkrequestID(String requestid) {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(60));
        WebElement ele = ldriver.findElement(By.xpath("//td[contains(text(),'" + requestid + "')]"));
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'"+requestid+"')]"))).isDisplayed());
    }

    @FindBy(xpath = "//span[contains(text(),'API Metrics Report')]")
    WebElement APIMatrixReport;

    public void clickAPIMatrixReport() throws InterruptedException {
        Thread.sleep(2000);
        APIMatrixReport.click();
    }

    @FindBy(xpath = "//button[contains(text(),'Search')]")
    WebElement Searchbutton;

    public void clickSearchButton() throws InterruptedException {
        Searchbutton.click();
        Thread.sleep(1000);
        Searchbutton.click();
        Thread.sleep(1000);
        Searchbutton.click();
    }

    public List<WebElement> listofWebElement()
    {
        List<WebElement> elements =ldriver.findElements(By.xpath("//span[contains(text(),'POST')]//parent::a[@class='select ng-binding']"));
        return elements;
    }

    @FindBy(xpath = "(//label[@class='small'])[2]")
    WebElement checkcrosshedder;
    public void clickCrossheaders()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(checkcrosshedder));
        checkcrosshedder.click();

        }
    @FindBy(xpath = "(//button[@class='btn btn-link openModal'])[2]/..")
    WebElement NewLink;
    public void clickNewLink() throws InterruptedException {
        Thread.sleep(5000);
        Actions actions = new Actions(ldriver);
//        actions.moveToElement(NewLink).click().build().perform();
        actions.click(NewLink).build().perform();
//        JavascriptExecutor j = (JavascriptExecutor) ldriver;
//        j.executeScript("arguments[0].click();", NewLink);

    }

    @FindBy(xpath = "(//button[@type='button'])[13]")
    WebElement creatLink;
    public void clickCreatLink()
    {
        Actions action = new Actions(ldriver);
        action.click(creatLink).build().perform();
    }

    @FindBy(xpath = "(//code[@class='ng-binding'])[1]")
    WebElement webhooksit;
    public String getWebhookLink()
    {
       String webhooklink =  webhooksit.getText();
       return webhooklink;
    }
}
