package com.paywallet.POJOClasses;

public class CCIdentity {
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String zip;
    String dateOfBirth;
    String last4TIN;

    public CCIdentity (String addressLine1, String addressLine2, String city, String state,
                       String zip, String dateOfBirth,String last4TIN)
    {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zip  = zip;
        this.dateOfBirth = dateOfBirth;
        this.last4TIN = last4TIN;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLast4TIN() {
        return last4TIN;
    }

    public void setLast4TIN(String last4TIN) {
        this.last4TIN = last4TIN;
    }
}
