package com.paywallet.pageObject;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class CC_SDKLoginPage_Client {

     WebDriver ldriver;

     public CC_SDKLoginPage_Client(WebDriver rdriver)
     {
         ldriver = rdriver;
         PageFactory.initElements(rdriver , this);
     }
     @FindBy(xpath = "(//p[contains(text(),'Request to login through your employer') or starts-with(text(),'Request to proceed with payment allocation') or contains(text(),'Request to confirm account details') or contains(text(),'Request to update pay allocation amount') or contains(text(),'Incomplete Request Notification.') or contains(text(),'Request for your employment information is complete') or contains(text(),'Request to login to your bank account') ])[1]")
     WebElement SelectEmail;

    @FindBy(xpath = "//a[contains(text(), 'Click here') or contains(text(), 'Click Here') ]")
    WebElement ClickOnLink;

    @FindBy(xpath = "//button[contains(text(),'Verify')]")
    WebElement Verify;


    @FindBy(xpath = "//button[ contains(text(),'OK') or contains(text(),'Confirm')]")
    WebElement conform1;

    @FindBy(xpath = "//button[contains(text(),'Disagree') or contains(text(),'DISAGREE')]")
    WebElement Disagree;

    @FindBy(xpath = "//input[@name='username']")
    WebElement Username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement Password;

    @FindBy(xpath = "//button[@data-hook='connect-button']")
    WebElement Conformbutton;

    @FindBy(xpath = "//input[@data-hook='mfa-code-input']")
    WebElement Verificationcode;

    @FindBy(xpath = "//button[@data-hook='connect-button']")
    WebElement Verify1;

    @FindBy(xpath = "//input[@id='flexCheckDefault']")
    WebElement Checkbox;

    @FindBy(xpath = "//input[@name='username']")
    WebElement username;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement clickcontinue;

    @FindBy(xpath ="//input[@name='password']")
    WebElement passInput;

    @FindBy(xpath = "//span[contains(text(),'Sign in') or contains(text(),'Continue')]")
    WebElement signinbutton;

    @FindBy(xpath = "//span[contains(text(),'Confirm')]")
    WebElement conform;

    @FindBy(xpath = "(//p[starts-with(text(),'Request to proceed with payment allocation')])[1]")
    WebElement SelectEmail1;

    @FindBy(xpath = "//input[@id='check']")
    WebElement Checkbox1;

    @FindBy(xpath = "//button[@data-hook='confirm-button']")
    WebElement Confirm;

    @FindBy(xpath = "(//p[contains(text(),'Request to confirm account details')])[1]")
    WebElement SelectEmail2;


    @FindBy(xpath = "//button[contains(text(),'SUBMIT')]")
    WebElement submit;

    @FindBy(xpath = "//h3[contains(text(),'Success')]")
    WebElement Success;

    @FindBy(xpath = "//h3[contains(text(),'Failure')]")
    WebElement Failure;

    @FindBy(xpath = "//h2[contains(text(),'Thank you!')]")
    WebElement sdkSuccess;



    @FindBy(xpath = "//button[@class='btn']")
    WebElement download;

    @FindBy(xpath = "//button[contains(text(),'VERIFY ACCOUNT')]")
    WebElement verifyaccount;

    @FindBy(xpath = "//input[@name='salaryAccountNumber']")
    WebElement SANumber;

    @FindBy(xpath = "//input[@name='accountAbaNumber']")
    WebElement ABANumber;

    @FindBy(xpath = "//input[@name='loginfmt']")
    WebElement Username1;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement next;

    @FindBy(xpath = "//input[@type='password']")
    WebElement password1;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement signin;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    WebElement clickyes;

    @FindBy(xpath = "//span[contains(text(),'Junk Email')]")
    WebElement junkemail;

    public void setJunkemail()
    {junkemail.click();}

    public void setsdkUN(String str)
    {Username1.sendKeys(str);}

    public void  clickNext()
    {next.click();}

    public void setPass1(String str)
    {password1.sendKeys(str);}

    public void setSiggn()
    {signin.click();}

    public void SetClickYes()
    {clickyes.click();}

    public void setSANumber(String str){SANumber.sendKeys(str);}

    public void setABANumber(String str){ABANumber.sendKeys(str);}

    public void setFailure(){Assert.assertTrue(Failure.isDisplayed());}
    public void setVerifyaccount(){verifyaccount.click();}

    public void clickSDKConform(){
        conform.click();
    }

    public void DownloadExcelData(){

        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(download));
        Actions actions = new Actions(ldriver);
        actions.moveToElement(download).click().build().perform();

    }



    public void setSuccess(){Assert.assertTrue(Success.isDisplayed());}

    @FindBy(xpath = "//div[@class='successText' or @class='failureText']")
    WebElement foo1;
    @FindBy(xpath = "//div[contains(text(),'Congratulations!') or contains(text(),'Confirmed!')]")
    WebElement foo2;
    public void verifySDKSuccessScreen()
    {

        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(200));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(foo1)).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(foo2)).isDisplayed());

    }

    public void setSubmit(){submit.click();}


    public void setSelectEmail2(){SelectEmail2.click();}

    public void setConfirm() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView(true);", Confirm);
        Thread.sleep(6000);
         Confirm.click();


    }

    public void clickAllocationConform(){
        conform1.click();}

    public void clickAllocationconsentCheckbox(){Checkbox1.click();}
    public void SelectSDKEmail(){SelectEmail1.click();}

    public void setSDKUsernameAtomicFI(String str){username.sendKeys(str);}

    public void clickSDKcontinue(){clickcontinue.click();}

    public void setSDKPasswordAtomicFI(String str){passInput.sendKeys(str);}

    public void clickSDKSigninbutton(){signinbutton.click();}

    public void selectSDKEmail()
    {
        SelectEmail.click();
    }

    public void clickOnSDKLink(){ClickOnLink.click();}

    @FindBy(xpath = "//input[@class='otpInput']")
    WebElement OTP;
    public void setOTP(String otp)
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(150));
        WebElement Otp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='otpInput']")));
        Otp.click();
        Otp.sendKeys(otp);
    }


    public void clickConcentVerify(){Verify.click();}


    public void setDisagree(){Disagree.click();}

    public void setSDKUsernameArgyle(String un){Username.sendKeys(un);}

    public void setSDKPasswordArgyle(String pw){Password.sendKeys(pw);}

    public void clickConformbutton(){Conformbutton.click();}

    public void setVerificationcode(String code){Verificationcode.sendKeys(code);}

    public void clicksdkVerify(){Verify1.click();}

    public void clickConsentCheckbox(){Checkbox.click();}

    @FindBy(xpath = "//h2[contains(text(),'Allocation Pending')]")
    WebElement pendingstatus;
    public void setPendingstatus()
    {
        Assert.assertTrue(pendingstatus.isDisplayed());
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;
    public void setmailosaurEmail(String str)
    {
        email.sendKeys(str);
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

    @FindBy(xpath = "(//button[@type='button'])[5]")
    WebElement refresh;
    public void setRefresh()
    {
        refresh.click();
    }

    public void scroll()
    {
        JavascriptExecutor j = (JavascriptExecutor)ldriver;
        j.executeScript("window.scrollBy(1104, 824)");
    }

    @FindBy(xpath = "(//p[starts-with(text(),'Request to update pay allocation amount')])[1]")
    WebElement updateemail;
    public void SelectUpdateemail()
    {
        updateemail.click();
    }

    @FindBy(xpath = "(//p[starts-with(text(),'Request to cancel pay allocation')])[1]")
    WebElement cancelemail;
    public void SelectCancelemail()
    {
        cancelemail.click();
    }

    @FindBy(xpath = "(//p[starts-with(text(),'Request to cancel your existing allocation through your payroll provider')])[1]")
    WebElement cancelemaill;
    public void SelectCancelSuccessemail()
    {
        cancelemaill.click();
    }

    @FindBy(xpath = "//button[contains(text(),'Confirm')]")
    WebElement cancelconform;
    public void clickConform(){cancelconform.click();}

    @FindBy(xpath = "(//button[@class='BigButton__Button-sc-1xj619h-0 hdETTt' or @class='BigButton__Button-sc-1xj619h-0 lgLYAA'])[1]")
    WebElement clickUpdate;
    public void clickUpdateDirectDeposit()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(90));
        wait.until(ExpectedConditions.elementToBeClickable(clickUpdate));
        clickUpdate.click();
    }

}
