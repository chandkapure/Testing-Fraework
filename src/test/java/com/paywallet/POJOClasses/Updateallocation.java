package com.paywallet.POJOClasses;

public class Updateallocation {

    String revisedAllocationAmount;
    String revisedNumberOfInstallment;

    public  Updateallocation(String revisedAllocationAmount , String revisedNumberOfInstallment)
    {
        this.revisedAllocationAmount = revisedAllocationAmount;
        this.revisedNumberOfInstallment = revisedNumberOfInstallment;

    }

    public String getRevisedAllocationAmount() {
        return revisedAllocationAmount;
    }

    public void setRevisedAllocationAmount(String revisedAllocationAmount) {
        this.revisedAllocationAmount = revisedAllocationAmount;
    }

    public String getRevisedNumberOfInstallment() {
        return revisedNumberOfInstallment;
    }

    public void setRevisedNumberOfInstallment(String revisedNumberOfInstallment) {
        this.revisedNumberOfInstallment = revisedNumberOfInstallment;
    }
}
