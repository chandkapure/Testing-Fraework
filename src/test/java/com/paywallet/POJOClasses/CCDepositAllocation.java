package com.paywallet.POJOClasses;

public class CCDepositAllocation {

    String numberOfInstallments;
    String installmentAmount;
    String loanAmount;
    String repaymentFrequency;
    String firstDateOfPayment;
    String clientContractReference;

    public  CCDepositAllocation(String numberOfInstallments, String installmentAmount, String loanAmount, String repaymentFrequency,
    String firstDateOfPayment, String clientContractReference)
    {
        this.numberOfInstallments = numberOfInstallments;
        this.installmentAmount = installmentAmount;
        this.loanAmount = loanAmount;
        this.repaymentFrequency = repaymentFrequency;
        this.firstDateOfPayment = firstDateOfPayment;
        this.clientContractReference = clientContractReference;
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

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getRepaymentFrequency() {
        return repaymentFrequency;
    }

    public void setRepaymentFrequency(String repaymentFrequency) {
        this.repaymentFrequency = repaymentFrequency;
    }

    public String getFirstDateOfPayment() {
        return firstDateOfPayment;
    }

    public void setFirstDateOfPayment(String firstDateOfPayment) {
        this.firstDateOfPayment = firstDateOfPayment;
    }

    public String getClientContractReference() {
        return clientContractReference;
    }

    public void setClientContractReference(String clientContractReference) {
        this.clientContractReference = clientContractReference;
    }
}
