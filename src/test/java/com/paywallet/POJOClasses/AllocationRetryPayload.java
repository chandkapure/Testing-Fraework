package com.paywallet.POJOClasses;

public class AllocationRetryPayload {

    String loanAmount;
    String installmentAmount;
    String numberOfInstallment;
    String firstRepaymentDate;

    public AllocationRetryPayload(String loanAmount, String installmentAmount, String numberOfInstallment, String firstRepaymentDate )
    {
        this.loanAmount=loanAmount;
        this.installmentAmount=installmentAmount;
        this.numberOfInstallment=numberOfInstallment;
        this.firstRepaymentDate=firstRepaymentDate;

    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getNumberOfInstallment() {
        return numberOfInstallment;
    }

    public void setNumberOfInstallment(String numberOfInstallment) {
        this.numberOfInstallment = numberOfInstallment;
    }

    public String getFirstRepaymentDate() {
        return firstRepaymentDate;
    }

    public void setFirstRepaymentDate(String firstRepaymentDate) {
        this.firstRepaymentDate = firstRepaymentDate;
    }



}
