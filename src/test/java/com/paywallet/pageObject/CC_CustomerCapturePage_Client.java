package com.paywallet.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;


public class CC_CustomerCapturePage_Client {

    WebDriver ldriver;

    public CC_CustomerCapturePage_Client(WebDriver rdriver)
    {
        ldriver = rdriver;
        PageFactory.initElements(rdriver ,this);
    }

    @FindBy(xpath="//input[@id='username']")
    WebElement username;

    @FindBy(xpath = "//input[@id='password']" )
    WebElement password;

    @FindBy(xpath = "//input[@type='submit']" )
    WebElement submit;

    @FindBy(xpath = "//input[@class='ant-input']")
    WebElement searchfield;

    @FindBy(xpath = "//button[@class= 'ant-btn ant-btn-primary ant-input-search-button']")
    WebElement searchbutton;

    @FindBy(xpath = "(//div[@class= 'l-employer-single'])[1]")
    WebElement selectemployer;




    @FindBy(xpath = "(//button[@type= 'button'])[2]")
    WebElement submitbutton;

    @FindBy(xpath = "//input[@name= 'incomeVerification']")
    WebElement IncomeCheckbox;

    @FindBy(xpath="//input[@name= 'employmentVerification']")
    WebElement EmploymentCheckbox;

    @FindBy(xpath="//input[@name= 'identityVerification']")
    WebElement IdentityCheckbox;

    @FindBy(xpath="//input[@name= 'affordability']")
    WebElement Affordability;

    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    WebElement submitbutton1;

    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstname;

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//input[@name='cellPhone']")
    WebElement Cellphone;

    @FindBy(xpath = "//input[@name='emailId']")
    WebElement Emailid;

    @FindBy(xpath = "//input[@name='numberOfMonthsRequested']")
    WebElement NumberOfMonths;

    @FindBy(xpath = "//input[@name='addressLine1']")
    WebElement Address;

    @FindBy(xpath = "//input[@name='city']")
    WebElement City;

    @FindBy(xpath = "//input[@name='zip']")
    WebElement ZipCode;

    @FindBy(xpath = "//input[@name='last4TIN']")
    WebElement Fourtin;

    @FindBy(xpath = "//select[@name='state']")
    WebElement selectState;

    @FindBy(xpath = "//select[@name='repaymentFrequency' or @name='payFrequency']")
    WebElement GetPaid;

    @FindBy(xpath = "(//input[@name='dateOfBirth'])[1]")
    WebElement Calender;

    @FindBy(xpath = "//button[starts-with(text(),'S')]")
    WebElement Submit;

    @FindBy(xpath="//button[contains(text(),'Go to Home Page')]")
    WebElement GoToHome;

    @FindBy(xpath="//button[contains(text(),'Verification')]")
    WebElement Verification;

    @FindBy(xpath="//button[contains(text(),'Deposit allocation')]")
    WebElement DepositAllocation;

    @FindBy(xpath="//input[@name='depositAllocation']")
    WebElement AllocationCheckbox;

    @FindBy(xpath="//input[@name='accountVerification']")
    WebElement AccValidationCheckbox;


    @FindBy(xpath = "//input[@name='numberOfInstallments']")
    WebElement NumberOfInstallment;

    @FindBy(xpath = "//input[@name='installmentAmount']")
    WebElement InstallmentAmount;

    @FindBy(xpath = "//input[@name='firstDateOfPayment' and @type='text' ]")
    WebElement FirstPaymentDate;

    @FindBy(xpath = "//input[@name='salaryAccountNumber']")
    WebElement SANumber;

    @FindBy(xpath = "//input[@name='accountAbaNumber']")
    WebElement ABANumber;

    public void setSANumber(String str){SANumber.sendKeys(str);}

    public void setABANumber(String str){ABANumber.sendKeys(str);}

    public void setFirstPaymentDate(String value)
    {
        FirstPaymentDate.sendKeys(value);
    }
    public void setNumberOfInstallment(String str)
    {
        Assert.assertTrue(NumberOfInstallment.isEnabled());
        NumberOfInstallment.sendKeys(str);
    }

    public void setInstallmentAmount(String str){InstallmentAmount.sendKeys(str);}


    public void setAllocationCheckbox(){AllocationCheckbox.click();}

    public void setAccValidationCheckbox(){AccValidationCheckbox.click();}

    public void setVerification(){Verification.click();}

    public void setDepositAllocation(){DepositAllocation.click();}

    public void setAddress(String address){Address.sendKeys(address);}

    public void setCity(String city){City.sendKeys(city);}

    public void setZipCode(String zipcode){ZipCode.sendKeys(zipcode);}

    public void setFourtin(String fourtin){Fourtin.sendKeys(fourtin);}

    public void setCalander(String str){Calender.sendKeys(str);}

    public void setSelectState(String value)
    {
        Select select = new Select(selectState);
        select.selectByVisibleText(value);
    }

    public void setGetPaid(String value)
    {
        Select select = new Select(GetPaid);
        select.selectByVisibleText(value);
    }

    public void setUsername(String uname)
    {
        username.sendKeys(uname);
    }

    public void setPassword(String pword)
    {
        password.sendKeys(pword);
    }

    public void KeyclockclickSubmit()
    {
        submit.click();
    }

    public void setSearchfield(String employer)
    {
        searchfield.sendKeys(employer);
    }

    public void clickSearchbutton()
    {
        searchbutton.click();
    }

    public void selectEmployer()
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(80));
        wait.until(ExpectedConditions.elementToBeClickable(selectemployer));
        selectemployer.click();
    }

    public void clickEmployerSearchSubmit()
    {
        submitbutton.click();
    }

    public void incomeVerification()
    {
        IncomeCheckbox.click();
    }

    public void identityVerification(){IdentityCheckbox.click();}

    public void setEmploymentCheckbox(){EmploymentCheckbox.click();}

    public void setAffordability(){Affordability.click();}

    public void clickServiceSelectionSubmit()
    {
        submitbutton1.click();
    }

    public void setFirstname(String fname)
    {
        WebDriverWait wait = new WebDriverWait(ldriver,Duration.ofSeconds(60));
//        WebElement fnamee =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
        wait.until(ExpectedConditions.elementToBeClickable(firstname));
        firstname.sendKeys(fname);
    }

    public void setLastName(String lname)
    {
        lastName.sendKeys(lname);
    }

    public void setCellphone(String number)
    {
        Cellphone.sendKeys(number);
    }

    public void setEmailid(String email)
    {
        Emailid.sendKeys(email);
    }

    public void setNumberOfMonths(String number)
    {
        NumberOfMonths.sendKeys(number);
    }

    public void clickCustomercaptureSubmit() {Submit.click();}

    @FindBy(xpath = "//button[contains(text(),'Go to Home Page')]")
    WebElement gotohomepage;
    public void clickGotoHome()
    {
        WebDriverWait wait = new WebDriverWait(ldriver ,Duration.ofSeconds(120));
        wait.until(ExpectedConditions.elementToBeClickable(gotohomepage));
        gotohomepage.click();
    }


    @FindBy(xpath = "//button[contains(text(),'Ok')]")
    WebElement ok;
    public void clickok()
    {
        ok.click();
    }

    @FindBy(xpath = "//p[contains(text(),'Supported')]")
    WebElement supported;
    public void isSupported()
    {
       Assert.assertTrue(supported.isDisplayed());
    }

    @FindBy(xpath = "//p[contains(text(),'Not Supported')]")
    WebElement notsupported;
    public void isNotSupported()
    {
       Assert.assertTrue(notsupported.isDisplayed());
    }

    @FindBy(xpath = "//button[@class='search-btn']")
    WebElement cellphonesearch;
    public void cellphoneSearch()
    {
        cellphonesearch.click();
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
