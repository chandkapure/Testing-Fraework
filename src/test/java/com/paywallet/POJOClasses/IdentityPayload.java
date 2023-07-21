package com.paywallet.POJOClasses;

public class IdentityPayload {

    String employerId;
    String firstName;
    String lastName;
    String last4TIN;
    String emailId;
    String cellPhone;
    IdentityCallBack callbackURLs;
    String addressLine1;
    String addressLine2;
    String zip;
    String city;
    String state;
    String dateOfBirth;

    public IdentityPayload(String employerId, String firstName, String lastName, String last4TIN, String emailId, String cellPhone, IdentityCallBack callbackURLs,
                           String addressLine1, String addressLine2, String zip, String city, String state, String dateOfBirth)
    {
        this.employerId = employerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.last4TIN = last4TIN;
        this.emailId=emailId;
        this.cellPhone=cellPhone;
        this.callbackURLs= callbackURLs;
        this.addressLine1=addressLine1;
        this.addressLine2=addressLine2;
        this.zip=zip;
        this.city=city;
        this.state=state;
        this.dateOfBirth=dateOfBirth;


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

    public String getLast4TIN() {
        return last4TIN;
    }

    public void setLast4TIN(String last4TIN) {
        this.last4TIN = last4TIN;
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

    public IdentityCallBack getCallbackURLs() {
        return callbackURLs;
    }

    public void setCallbackURLs(IdentityCallBack callbackURLs) {
        this.callbackURLs = callbackURLs;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
