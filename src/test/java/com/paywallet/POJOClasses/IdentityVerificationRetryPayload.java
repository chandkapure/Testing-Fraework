package com.paywallet.POJOClasses;

public class IdentityVerificationRetryPayload {

    String employerId;
    String emailId;
    String cellPhone;

    public IdentityVerificationRetryPayload(String employerId, String emailId, String cellPhone)
    {
        this.employerId = employerId;
        this.emailId = emailId;
        this.cellPhone = cellPhone;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
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
