package com.paywallet.pageObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;


public class CC_Customer_ContextFlow {
    WebDriver ldriver;
    public CC_Customer_ContextFlow(WebDriver rdriver)
    {
        ldriver = rdriver;
        PageFactory.initElements(rdriver,this);
    }


    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstname;
    public  void setFirstname(String str)
    {
        firstname.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastname;
    public void setLastname(String str)
    {
        lastname.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='cellPhone']")
    WebElement cellphone;
    public void setCellphone(String str)
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(cellphone));
        cellphone.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;
    public void setEmail(String str)
    {
        email.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='employer']")
    WebElement employer;
    public void setEmployer(String str)
    {
        employer.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='numberOfInstallments']")
    WebElement numberOfInstallments;
    public void setNumberOfInstallments(String str)
    {
        numberOfInstallments.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='installmentAmount']")
    WebElement installmentAmount;
    public void setInstallmentAmount(String str)
    {
        installmentAmount.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='firstDateOfPayment']")
    WebElement firstDateOfPayment;
    public void setFirstDateOfPayment(String str)
    {
        firstDateOfPayment.sendKeys(str);
    }

    @FindBy(xpath = "//select[@name='repaymentFrequency']")
    WebElement GetPaid;
    public void setRepaymentFrequency(String value)
    {
        Select select = new Select(GetPaid);
        select.selectByVisibleText(value);
    }

    @FindBy(xpath = "//input[@id='check0']")
    WebElement employmentcheckbox;
    public void setEmploymentcheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(employmentcheckbox));
        employmentcheckbox.click();
    }

    @FindBy(xpath = "//input[@id='check1']")
    WebElement identitycheckbox;
    public void setIdentitycheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(identitycheckbox));
        identitycheckbox.click();
    }

    @FindBy(xpath = "//input[@id='check2']")
    WebElement incomecheckbox;
    public void setIncomecheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(incomecheckbox));
        incomecheckbox.click();
    }

    @FindBy(xpath = "//input[@id='check3']")
    WebElement depositallocation;
    public void setDepositallocationcheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(depositallocation));
        depositallocation.click();
    }

    @FindBy(xpath = "//input[@id='check4']")
    WebElement affordability;
    public void setAffordabilitycheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(affordability));
        affordability.click();
    }

    @FindBy(xpath = "//input[@id='check5']")
    WebElement accountvalidation;
    public void setAccountvalidationcheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(accountvalidation));
        accountvalidation.click();
    }

    @FindBy(xpath = "//input[@id='check6']")
    WebElement updateAllocation;
    public void setUpdateAllocationcheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(updateAllocation));
        updateAllocation.click();
    }

    @FindBy(xpath = "//input[@id='check7']")
    WebElement CancelAllocation;
    public void setCancelAllocationcheckbox()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(CancelAllocation));
        CancelAllocation.click();
    }

    @FindBy(xpath = "//select[@class='lenderSelect']")
    WebElement selectlender;
    public void setSelectlender(String str)
    {
        Select select = new Select(selectlender);
        select.selectByVisibleText(str);
    }

    @FindBy(xpath = "(//div[@class='l-employer-single'])[1]")
    WebElement selectemployer;
    public void setSelectemployer()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(100));
        wait.until(ExpectedConditions.elementToBeClickable(selectemployer));
        selectemployer.click();
    }

    @FindBy(xpath = "//button[contains(text(),'Submit') or contains(text(),'SUBMIT') ] ")
    WebElement submit;
    public void setSubmit()
    {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView(true);", submit);
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(20));
        WebElement SuBmit = wait.until(ExpectedConditions.elementToBeClickable(submit));
        SuBmit.click();
    }

    @FindBy(xpath = "//button[contains(text(),'Submit') or contains(text(),'SUBMIT') ] ")
    WebElement submitt;
    public void setScrollIntoView()
    {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView();", submit);

    }

    @FindBy(xpath = "//input[@name='otp1']")
    WebElement OTP1;
    public void setOTP1(String otp1){OTP1.sendKeys(otp1);}

    @FindBy(xpath = "//input[@name='otp2']")
    WebElement OTP2;
    public void setOTP2(String otp2){OTP2.sendKeys(otp2);}

    @FindBy(xpath = "//input[@name='otp3']")
    WebElement OTP3;
    public void setOTP3(String otp3){OTP3.sendKeys(otp3);}

    @FindBy(xpath = "//input[@name='otp4']")
    WebElement OTP4;
    public void setOTP4(String otp4){OTP4.sendKeys(otp4);}

    @FindBy(xpath = "//input[@name='otp5']")
    WebElement OTP5;
    public void setOTP5(String otp5){OTP5.sendKeys(otp5);}

    @FindBy(xpath = "//input[@name='otp6']")
    WebElement OTP6;
    public void setOTP6(String otp6){OTP6.sendKeys(otp6);}

    @FindBy(xpath = "//button[contains(text(),'Verify')]")
    WebElement Verify;
    public void setVerify() throws InterruptedException {
        Thread.sleep(2000);
        Verify.click();
    }

    @FindBy(xpath = "//input[@id='flexCheckDefault']")
    WebElement consentbox;
    public void setConsentcheckbox(){consentbox.click();}

    @FindBy(xpath = "//input[@id='check']")
    WebElement allocationconsentcheckbox;
    public void setallocationconsentcheckbox(){allocationconsentcheckbox.click();}

    @FindBy(xpath = "//button[contains(text(),'Agree')]")
    WebElement consentagree;
    public void setConsentagree(){consentagree.click();}

    @FindBy(xpath = "//button[contains(text(),'Disagree')]")
    WebElement consentdisagree;
    public void setConsentdisagree(){consentdisagree.click();}

    @FindBy(xpath = "//button[@type='submit' or contains(text(),'Confirm')]")
    WebElement consentok;
    public void clickConsentConform(){consentok.click();}

    @FindBy(xpath = "//input[@name='username']")
    WebElement sdkusername;
    public void setsdkUsername(String str)
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(120));
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
        username.sendKeys(str);
    }

    public void setsdkUsernamea(String str)
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(120));
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
        username.sendKeys(str);
    }


    @FindBy(xpath = "//input[@name='password']")
    WebElement sdkpassword;
    public void setsdkPassword(String str)
    {
        sdkpassword.sendKeys(str);
    }

    @FindBy(xpath = "//button[@class='BigButton__Button-sc-1xj619h-0 dPEqhC']")
    WebElement sdkconnect;
    public void setsdkConnect() throws InterruptedException {
        Thread.sleep(2000);
        sdkconnect.click();
    }

    @FindBy(xpath = "//input[@name='twoFaToken']")
    WebElement verificationcode;
    public void setVerificationcode(String str){

        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(verificationcode));
        verificationcode.sendKeys(str);
    }


    @FindBy(xpath = "//button[@class='btn content-only touchable focus-visible-width']")
    WebElement sdksignin;
    public void setSdksignin(){sdksignin.click();}


    @FindBy(xpath = "//button[contains(text(),'Download')]")
    WebElement Download;
    public void setDownload() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(200));
        wait.until(ExpectedConditions.elementToBeClickable(Download));
        Actions actions = new Actions(ldriver);
        actions.moveToElement(Download).build().perform();
        Download.click();
    }

    @FindBy(xpath = "(//span[@data-hook='visible-label'])[1]")
    WebElement Clickconform;
    public void Clickconform() {Clickconform.click();}

    @FindBy(xpath = "//input[@name='numberOfMonthsRequested']")
    WebElement numberofmonthrequired;
    public void setNumberofmonthrequired(String str)
    {
        numberofmonthrequired.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='addressLine1']")
    WebElement address;
    public void setAddress(String str)
    {
        address.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='city']")
    WebElement city;
    public void setCity(String str)
    {
        city.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='zip']")
    WebElement zipcode;
    public void setZipcode(String str)
    {
        zipcode.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='last4TIN']")
    WebElement fourtin;
    public void setFourtin(String str)
    {
        fourtin.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='dateOfBirth']")
    WebElement dateofbirth;
    public void setDateofbirth(String str)
    {
        dateofbirth.sendKeys(str);
    }

    @FindBy(xpath = "//select[@name='state']")
    WebElement selectstate;
    public void setSelectstate(String str)
    {
        Select select = new Select(selectstate);
        select.selectByVisibleText(str);
    }

    public void setScroll()
    {
        JavascriptExecutor j = (JavascriptExecutor)ldriver;
        j.executeScript("window.scrollBy(0,2000)");
    }

    @FindBy(xpath = "//button[contains(text(),'Employment')]")
    WebElement employementinfo;
    public void setEmployementinfo()
    {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(employementinfo));
        employementinfo.click();
        Assert.assertTrue(employementinfo.isDisplayed());

    }

    @FindBy(xpath = "//button[contains(text(),'Salary')]")
    WebElement salaryinfo;
    public void VerifySalaryinfo()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOf(salaryinfo));
        salaryinfo.click();
        Assert.assertTrue(salaryinfo.isDisplayed());

    }

    @FindBy(xpath = "//button[contains(text(),'Identity')]")
    WebElement identityinfo;
    public void setIdentityinfo()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(120));
                wait.until(ExpectedConditions.visibilityOf(identityinfo));
        identityinfo.click();
        Assert.assertTrue(identityinfo.isDisplayed());

    }

    @FindBy(xpath = "//button[contains(text(),'Affordability')]")
    WebElement affordabilityinfo;
    public void setAffordabilityinfo()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOf(affordabilityinfo));
        affordabilityinfo.click();
        Assert.assertTrue(affordabilityinfo.isDisplayed());


    }

    @FindBy(xpath = "//button[@class='BigButton__Button-sc-1xj619h-0 kbmTGE']")
    WebElement sdkcontinue;
    public void setSdkcontinue()
    {
        sdkcontinue.click();
    }


    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement clickcontinue;

    public void setVerificationcontinue()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(clickcontinue));
        clickcontinue.click();
    }

    @FindBy(xpath = "//button[@class='BigButton__Button-sc-1xj619h-0 ceEBdj AllocationTypeSettings__ConfirmButton-sc-1hcxd1t-0 sONif' or @class='BigButton__Button-sc-1xj619h-0 dPEqhC AllocationTypeSettings__ConfirmButton-sc-1hcxd1t-0 fdSbiG']")
    WebElement clickconform;
    public void setClickconformArgyle()  {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(clickconform));
        Actions action = new Actions(ldriver);
        action.moveToElement(clickconform).click(clickconform).build().perform();

    }

    @FindBy(xpath = "//button[@class='btn content-only touchable focus-visible-width mt-2']")
    WebElement clickconforme;
    public void clickAtomicFIConform()
    {
        Actions actions = new Actions(ldriver);
        actions.moveToElement(clickconforme).click(clickconforme).build().perform();

    }

    @FindBy(xpath = "//div[@class='successText' or @class='failureText']")
    WebElement foo1;
    public void setSuccess()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(200));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(foo1)).isDisplayed());
    }

    @FindBy(xpath = "//button[contains(text(),'VERIFY ACCOUNT')]")
    WebElement verifyaccount;
    public void setVerifyaccount() throws InterruptedException
    {
        Thread.sleep(20000);
        verifyaccount.click();
    }

    @FindBy(xpath = "//input[@name='salaryAccountNumber']")
    WebElement accountnumber;
    public void setAccountnumber(String str) throws InterruptedException {

        Thread.sleep(15000);
        accountnumber.sendKeys(str);
    }

    @FindBy(xpath = "//input[@name='accountAbaNumber']")
    WebElement abanumber;
    public void setAbanumber(String str)
    {
        abanumber.sendKeys(str);
    }

    @FindBy(xpath = "//h3[contains(text(), 'Something went wrong')]")
    WebElement avsuccess;
    public void SomethingWentWrong()
    {
        Assert.assertTrue(avsuccess.isDisplayed());
    }

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement clicksdkcontinue;
    public void clickSDKcontinue()
    {

        Actions actions = new Actions(ldriver);
        actions.moveToElement(clicksdkcontinue).click(clicksdkcontinue).build().perform();

    }

    @FindBy(xpath = "//span[contains(text(),'Continue') or contains(text(),'Sign in')]")
    WebElement clicksdksignin;
    public void clickSDKsignin()
    {
        Actions actions = new Actions(ldriver);
        actions.moveToElement(clicksdksignin).click(clicksdksignin).build().perform();
    }


    @FindBy(xpath = "//button[contains(text(),'Download')]")
    WebElement DownloadExcel;
    public void DownloadExcel() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.elementToBeClickable(DownloadExcel));
        Thread.sleep(4000);
        Actions actions = new Actions(ldriver);
        actions.moveToElement(DownloadExcel).click().build().perform();
    }

    @FindBy(xpath = "//input[@id='formInput']")
    WebElement ssnnumber;
    public void setSSNnumber(String str)
    {
        ssnnumber.sendKeys(str);
    }

    @FindBy(xpath = "//h2[contains(text(),'Allocation Pending')]")
    WebElement allocationstatus;
    public void verifyPDSupportStatus()
    {
        Assert.assertTrue(allocationstatus.isDisplayed());
    }

    @FindBy(xpath = "//button[@class='btn']")
    WebElement allocationdownload;
    public void allocationDownload()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(160));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='client-app']")));
        wait.until(ExpectedConditions.elementToBeClickable(allocationdownload));
        allocationdownload.click();
    }

    public void Downloadexcel()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(160));
        wait.until(ExpectedConditions.elementToBeClickable(allocationdownload));
        allocationdownload.click();
    }




    @FindBy(xpath = "//input[@class='otpInput']")
    WebElement OTP;
    public void setOTP(String otp)
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(90));
        WebElement Otp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='otpInput']")));
        Otp.click();
        Otp.sendKeys(otp);
    }

    @FindBy(xpath = "//h2[contains(text(),'Thank you!')]")
    WebElement ThankYou;
    public void setThankYou()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(240));
        wait.until(ExpectedConditions.visibilityOf(ThankYou));
        Assert.assertTrue(ThankYou.isDisplayed());
    }

    @FindBy(xpath = "(//textarea[@class='npm__react-simple-code-editor__textarea'])[1]")
    WebElement requestIdtext;
    public String getRequestIdtext()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOf(requestIdtext));
        String requestid = requestIdtext.getText();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestid, JsonObject.class);
        String requestidnew = jsonObject.get("data").getAsJsonObject().get("requestId").getAsString();
        return requestidnew;
    }

    @FindBy(xpath = "(//textarea[@class='npm__react-simple-code-editor__textarea'])[1]")
    WebElement clearrequestIdtext;
    public String getclearRequestIdtext()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(120));
        String requestid = clearrequestIdtext.getText();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestid, JsonObject.class);
        String requestidnew = jsonObject.get("data").getAsJsonObject().get("clearRequestId").getAsString();
        return requestidnew;
    }

    @FindBy(xpath = "//input[@name='requestId']")
    WebElement requestID;
    public  void sendrequestID(String str)
    {
//        JavascriptExecutor js = (JavascriptExecutor) ldriver;
//        js.executeScript("arguments[0].scrollIntoView();", requestID);
        requestID.sendKeys(str);
    }

    @FindBy(xpath = "//input[@id='check']")
    WebElement checkbox;
    public  void clickCheckbox()
    {
        checkbox.click();
    }

    @FindBy(xpath = "//h6[starts-with(text(),'Pay distribution is not supported for the employer')]")
    WebElement pdsupportstatus;
    public void getPdsupportstatus()
    {
        pdsupportstatus.isDisplayed();
    }

    @FindBy(xpath = "//input[@name='revisedNumberOfInstallments']")
    WebElement revisenumberofInstalment;
    public void getrevisenumberofinstalment(String instamnet){revisenumberofInstalment.sendKeys(instamnet);}

    @FindBy(xpath = "//input[@name='revisedInstallmentAmount']")
    WebElement revisedInstallmentAmount;
    public void getrevisenumberofinstalmentamount(String amount){revisedInstallmentAmount.sendKeys(amount);}

    @FindBy(xpath = "(//button[@class='BigButton__Button-sc-1xj619h-0 hdETTt' or @class='BigButton__Button-sc-1xj619h-0 lgLYAA'])[1]")
    WebElement UpdateDirectDeposit;
    public void clickUpdateDirectDeposit() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(UpdateDirectDeposit));
        Thread.sleep(4000);
        Actions action = new Actions(ldriver);
        action.moveToElement(UpdateDirectDeposit).click().build().perform();

    }

    @FindBy(xpath = "//input[@name='Search for your bank']")
    WebElement searchforbank;
    public void enterBank(String Bank) throws InterruptedException {
        Thread.sleep(5000);
        searchforbank.sendKeys(Bank);
    }

    @FindBy(xpath = "(//div[@role='button'])[1]")
    WebElement selectbank;
    public void SelectBank(){selectbank.click();}

    @FindBy(xpath = "//app-button[@class='full-width m-2-0']")
    WebElement nextbutton;
    public void clickNextButton() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView(true);", nextbutton);
        js.executeScript("arguments[0].click();", nextbutton);
    }

    @FindBy(xpath = "//input[@name='Banking Userid']")
    WebElement bankUserid;
    public void enterBankuserid(String Bank){bankUserid.sendKeys(Bank);}

    @FindBy(xpath = "//input[@class='browser-default fs-exclude ng-untouched ng-pristine ng-invalid']")
    WebElement bankPassword;
    public void enterBankPassword(String Bank){bankPassword.sendKeys(Bank);}

    @FindBy(xpath = "//app-button[@class='full-width round m-t-5 m-b-2']")
    WebElement banksubmit;
    public void clickbanksubmit()
    {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].click();", banksubmit);
    }


    @FindBy(xpath = "(//div[@class='checkbox-container'])[7]")
    WebElement selecLoanAccount;
    public void selectloanaccount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(90));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='title']")));
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView(true);", selecLoanAccount);
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", selecLoanAccount);
    }

    @FindBy(xpath = "//app-button[@class='full-width round m-t-5']")
    WebElement clickSave;
    public void ClickSave() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView(true);", clickSave);
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", clickSave);
    }

    @FindBy(xpath = "//app-button[@class='round full-width submit-accounts m-t-1']")
    WebElement finsubmit;
    public void clickfinsubmit() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", finsubmit);
    }

    @FindBy(xpath = "//h3[contains(text(),'Match!')]")
    WebElement match;
    public void matched() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView(true);", match);
        Thread.sleep(5000);
        Assert.assertTrue(match.isDisplayed());
    }

    @FindBy(xpath = "//h3[contains(text(),'Not a Match!')]")
    WebElement notmatch;
    public void notMatched() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView(true);", notmatch);
        Thread.sleep(5000);
        Assert.assertTrue(notmatch.isDisplayed());
    }

    @FindBy(xpath = "//img[@class='info-linkImg']")
    WebElement ibutton;
    public void clickIbutton()
    {
        ibutton.click();
    }

    @FindBy(xpath = "//button[@class='btn-close modal-close-btn']")
    WebElement popup;
    public void handlePopup()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(160));
        wait.until(ExpectedConditions.elementToBeClickable(popup));
        popup.click();
    }

    @FindBy(xpath = "//div[@class='provider_img_coll']")
    WebElement providerimageColumn;

    public void providersDisplayed()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(80));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(providerimageColumn)).isDisplayed());

    }



}
