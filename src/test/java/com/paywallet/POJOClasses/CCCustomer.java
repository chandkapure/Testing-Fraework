package com.paywallet.POJOClasses;

public class CCCustomer {

    String firstName;
    String middleName;
    String lastName;
    String emailId;
    String cellPhone;

    public CCCustomer(String firstName,String middleName, String lastName, String emailId, String cellPhone)
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.cellPhone = cellPhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
}
