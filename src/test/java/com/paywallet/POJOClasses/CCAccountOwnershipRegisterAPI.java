package com.paywallet.POJOClasses;

import java.util.List;

public class CCAccountOwnershipRegisterAPI {
    String employerId;
    List<String> profiles;
    String context;
    CCCustomer customer;

    CCAccountValidation accountValidation;

    public CCAccountOwnershipRegisterAPI(String employerId,List<String> profiles,String context,CCCustomer customer,CCAccountValidation accountValidation )
    {
        this.employerId=employerId;
        this.profiles=profiles;
        this.context=context;
        this.customer=customer;
        this.accountValidation=accountValidation;

    }

    public CCAccountValidation getAccountValidation() {
        return accountValidation;
    }

    public void setAccountValidation(CCAccountValidation accountValidation) {
        this.accountValidation = accountValidation;
    }


    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public CCCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CCCustomer customer) {
        this.customer = customer;
    }
}
