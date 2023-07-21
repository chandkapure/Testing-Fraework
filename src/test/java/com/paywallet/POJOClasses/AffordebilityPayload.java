package com.paywallet.POJOClasses;

public class AffordebilityPayload {
    String employerId;
    String firstName;
    String lastName;
    String emailId;
    String cellPhone;
    AffordebilityCallBack callbackURLs;


    public AffordebilityPayload(String employerId, String firstName, String lastName, String emailId, String cellPhone, AffordebilityCallBack callbackURLs)
    {
        this.employerId = employerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId=emailId;
        this.cellPhone=cellPhone;
        this.callbackURLs= callbackURLs;

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

    public AffordebilityCallBack getCallbackURLs() {
        return callbackURLs;
    }

    public void setCallbackURLs(AffordebilityCallBack callbackURLs) {
        this.callbackURLs = callbackURLs;
    }
}
