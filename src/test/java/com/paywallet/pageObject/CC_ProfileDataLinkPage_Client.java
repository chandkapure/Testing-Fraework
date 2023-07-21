package com.paywallet.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CC_ProfileDataLinkPage_Client {

      WebDriver ldriver;

      public CC_ProfileDataLinkPage_Client(WebDriver rdriver)
      {
          ldriver = rdriver;
          PageFactory.initElements(rdriver ,this);
      }

    @FindBy(xpath = "(//p[starts-with(text(),'Request for employment information')])[1]")
    WebElement SelectEmail;

    @FindBy(xpath = "(//p[starts-with(text(),'Request to complete allocation through your employer')])[1]")
    WebElement SelectEmail2;

    @FindBy(xpath = "//a[contains(text(), 'Click here') or contains(text(), 'Click Here')]")
    WebElement ClickOnLink;


    @FindBy(xpath = "//input[@name='otp']")
    WebElement OTP;

    @FindBy(xpath = "//button[contains(text(),'Verify')]")
    WebElement Verify;

    @FindBy(xpath = "//button[contains(text(),'Salary Information')")
    WebElement infodisplayed;

    @FindBy(xpath = "//div[@style='font-family: inherit; text-align: inherit']")
    WebElement AllocDetails;

    @FindBy(xpath = "(//p[contains(text(),'Request for pay allocation completed')])[1]")
    WebElement selectemail1;

    @FindBy(xpath = "//button[contains(text(),'Download')]")
    WebElement Download1;

    @FindBy(xpath = "//button[contains(text(),'Salary Information')]")
    WebElement Salarydisp;

    @FindBy(xpath = "//button[contains(text(),'Employment Details')]")
    WebElement Employmentdisp;

    @FindBy(xpath = "//button[contains(text(),'Identity Information')]")
    WebElement Identitydisp;

    @FindBy(xpath = "//button[contains(text(),'Affordability')]")
    WebElement Affordablitydisp;

    @FindBy(xpath = "//span[contains(text(),'Inbox')]")
    WebElement Indbox;

    @FindBy(xpath = "//input[@id='input1']")
    WebElement checkbox1;

    @FindBy(xpath = "//button[contains(text(),'Submit') or contains(text(),'SUBMIT')]")
    WebElement submit;

    @FindBy(xpath = "//div[@class='thankyoumsg']")
    WebElement thankyoumessage;

    public void setSelectEmail2()
    {
        SelectEmail2.click();
    }

    public void setThankyoumessage()
    {
        Assert.assertTrue(thankyoumessage.isDisplayed());
    }

    public void setSubmit()
    {
        submit.click();
    }
    public void setCheckbox1()
    {
        checkbox1.click();
    }

    public void setIndbox()
    {
        Indbox.click();
    }
    public void verifySalaryInfodisp()
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(180));
                wait.until(ExpectedConditions.visibilityOf(Salarydisp));
        Assert.assertTrue(Salarydisp.isDisplayed());
    }

    public void verifyEmploymentInfodisp()
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOf(Employmentdisp));
        Assert.assertTrue(Employmentdisp.isDisplayed());
    }
    public void verifyIdentityInfoDisplayed()
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOf(Identitydisp));
       Assert.assertTrue(Identitydisp.isDisplayed());
    }

    public void verifyAffordablityInfodisp()
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(240));
        wait.until(ExpectedConditions.visibilityOf(Affordablitydisp));
        Assert.assertTrue(Affordablitydisp.isDisplayed());
    }

    public void selectAllocationCompleteemail(){selectemail1.click();}

    public void verifyExcelDownload() {
        Download1.click();
    }

    public void verifyAllocDetailsInfoDisplayed()
    {
        Assert.assertTrue(AllocDetails.isDisplayed());
    }


    public void selectProfileDataEmail(){SelectEmail.click();}

    public void clickOnProfiledataLink()
    {
        ClickOnLink.click();
    }


    public void setOTP(String otp){OTP.sendKeys(otp);}


    public void clickProfiledataVerify(){Verify.click();}

    public void scroll()
    {
        JavascriptExecutor j = (JavascriptExecutor)ldriver;
        j.executeScript("window.scrollBy(0,600)");
    }

    @FindBy(xpath = "(//button[@type='button'])[5]")
    WebElement refresh;
    public void setRefresh()
    {
        refresh.click();
    }

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement Checkbox;
    public void selectCheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(Checkbox));
        Checkbox.click();
    }

    @FindBy(xpath = "//div[@class='thankyoumsg']")
    WebElement AllocationPending;
    public void finalAllocationPending()
    {
        AllocationPending.isDisplayed();
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement mailasouremail;
    public void setmailosaurEmail(String str)
    {
        mailasouremail.sendKeys(str);
    }

    @FindBy(xpath = "//div[contains(text(),'Continue')]")
    WebElement clickcontinue1;
    public void clickmailosaurContinue()
    {
        clickcontinue1.click();
    }

    @FindBy(xpath ="//input[@name='password']")
    WebElement password2;
    public void setsetmailosaurPassword(String str)
    {
        password2.sendKeys(str);
    }

    @FindBy(xpath = "//div[contains(text(),'Log in')]")
    WebElement login;
    public void clickmailosaurLogin()
    {
        login.click();
    }

    @FindBy(xpath = "//b[contains(text(),'Record Submitted Successfully')]")
    WebElement recordSuccess;
    public void recordSuccessScreen(){recordSuccess.isDisplayed();}

    @FindBy(xpath = "//h2[contains(text(),'Thank you!')]")
    WebElement ThankYou;
    public void ThankYou()
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOf(ThankYou));
        ThankYou.isDisplayed();
    }


}
