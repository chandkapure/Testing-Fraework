package com.paywallet.POJOClasses;

public class DirectAllocationPayload {
    String employerId;
    String firstName;
    String lastName;
    String emailId;
    String cellPhone;
    String numberOfInstallments;
    String installmentAmount;
    String clientContractReference;
    String firstDateOfPayment;
    String repaymentFrequency;
    DirectAllocationCallBack callbackURLs;
    String achPullRequest;
    String accountVerificationOverride;
    String lender;
    String externalVirtualAccount;
    String externalVirtualAccountABANumber;

    public DirectAllocationPayload(String employerId, String firstName, String lastName, String emailId, String cellPhone, String NumberofInstalment, String loanamount,
                                   String CCreference, String FirstDOP, String repayment, DirectAllocationCallBack callbackURLs, String ACHpull, String AccountVerifiction, String Lender,
                                   String externalVirtualaccount, String externalVirAccABAnumber)

    {
        this.employerId = employerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId=emailId;
        this.cellPhone=cellPhone;
        this.numberOfInstallments =NumberofInstalment;
        this.installmentAmount = loanamount;
        this.clientContractReference = CCreference;
        this.firstDateOfPayment = FirstDOP;
        this.repaymentFrequency = repayment;
        this.callbackURLs= callbackURLs;
        this.achPullRequest = ACHpull;
        this.accountVerificationOverride = AccountVerifiction;
        this.lender = Lender;
        this.externalVirtualAccount = externalVirtualaccount;
        this.externalVirtualAccountABANumber = externalVirAccABAnumber;

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

    public String getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(String numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getClientContractReference() {
        return clientContractReference;
    }

    public void setClientContractReference(String clientContractReference) {
        this.clientContractReference = clientContractReference;
    }

    public String getFirstDateOfPayment() {
        return firstDateOfPayment;
    }

    public void setFirstDateOfPayment(String firstDateOfPayment) {
        this.firstDateOfPayment = firstDateOfPayment;
    }

    public String getRepaymentFrequency() {
        return repaymentFrequency;
    }

    public void setRepaymentFrequency(String repaymentFrequency) {
        this.repaymentFrequency = repaymentFrequency;
    }

    public DirectAllocationCallBack getCallbackURLs() {
        return callbackURLs;
    }

    public void setCallbackURLs(DirectAllocationCallBack callbackURLs) {
        this.callbackURLs = callbackURLs;
    }

    public String getAchPullRequest() {
        return achPullRequest;
    }

    public void setAchPullRequest(String achPullRequest) {
        this.achPullRequest = achPullRequest;
    }

    public String getAccountVerificationOverride() {
        return accountVerificationOverride;
    }

    public void setAccountVerificationOverride(String accountVerificationOverride) {
        this.accountVerificationOverride = accountVerificationOverride;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getExternalVirtualAccount() {
        return externalVirtualAccount;
    }

    public void setExternalVirtualAccount(String externalVirtualAccount) {
        this.externalVirtualAccount = externalVirtualAccount;
    }

    public String getExternalVirtualAccountABANumber() {
        return externalVirtualAccountABANumber;
    }

    public void setExternalVirtualAccountABANumber(String externalVirtualAccountABANumber) {
        this.externalVirtualAccountABANumber = externalVirtualAccountABANumber;
    }
}
