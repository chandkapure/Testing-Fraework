package com.paywallet.POJOClasses;

public class IncomePayload {
    String employerId;
    String firstName;
    String lastName;
    String lender;
    String emailId;
    String cellPhone;
    IncomeCallBack callbackURLs;
    String numberofmonthrequired;


    public IncomePayload(String employerId, String firstName, String lastName, String lender, String emailId, String cellPhone, IncomeCallBack callbackURLs,
                           String numberofmonthrequired)
    {
        this.employerId = employerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lender = lender;
        this.emailId=emailId;
        this.cellPhone=cellPhone;
        this.callbackURLs= callbackURLs;
        this.numberofmonthrequired=numberofmonthrequired;

    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getNumberofmonthrequired() {
        return numberofmonthrequired;
    }

    public void setNumberofmonthrequired(String numberofmonthrequired) {
        this.numberofmonthrequired = numberofmonthrequired;
    }

    public IncomeCallBack getCallbackURLs() {
        return callbackURLs;
    }

    public void setCallbackURLs(IncomeCallBack callbackURLs) {
        this.callbackURLs = callbackURLs;
    }
}
