package com.paywallet.POJOClasses;

import java.util.List;

public class CCCallbackURLs {
    List<String> identityCallbackUrls;
    List<String> employmentCallbackUrls;
    List<String> incomeCallbackUrls;
    List<String> allocationCallbackUrls;
    List<String> insufficientFundCallbackUrls;
    List<String> notificationUrls;
    List<String> affordabilityCallbackUrls;

    public CCCallbackURLs(List<String> identityCallbackUrls, List<String> employmentCallbackUrls, List<String> incomeCallbackUrls,
    List<String> allocationCallbackUrls, List<String> insufficientFundCallbackUrls, List<String> notificationUrls, List<String> affordabilityCallbackUrls)
    {
        this.identityCallbackUrls = identityCallbackUrls;
        this.employmentCallbackUrls = employmentCallbackUrls;
        this.incomeCallbackUrls = incomeCallbackUrls;
        this.allocationCallbackUrls = allocationCallbackUrls;
        this.insufficientFundCallbackUrls = insufficientFundCallbackUrls;
        this.notificationUrls = notificationUrls;
        this.affordabilityCallbackUrls = affordabilityCallbackUrls;

    }

    public List<String> getIdentityCallbackUrls() {
        return identityCallbackUrls;
    }

    public void setIdentityCallbackUrls(List<String> identityCallbackUrls) {
        this.identityCallbackUrls = identityCallbackUrls;
    }

    public List<String> getEmploymentCallbackUrls() {
        return employmentCallbackUrls;
    }

    public void setEmploymentCallbackUrls(List<String> employmentCallbackUrls) {
        this.employmentCallbackUrls = employmentCallbackUrls;
    }

    public List<String> getIncomeCallbackUrls() {
        return incomeCallbackUrls;
    }

    public void setIncomeCallbackUrls(List<String> incomeCallbackUrls) {
        this.incomeCallbackUrls = incomeCallbackUrls;
    }

    public List<String> getAllocationCallbackUrls() {
        return allocationCallbackUrls;
    }

    public void setAllocationCallbackUrls(List<String> allocationCallbackUrls) {
        this.allocationCallbackUrls = allocationCallbackUrls;
    }

    public List<String> getInsufficientFundCallbackUrls() {
        return insufficientFundCallbackUrls;
    }

    public void setInsufficientFundCallbackUrls(List<String> insufficientFundCallbackUrls) {
        this.insufficientFundCallbackUrls = insufficientFundCallbackUrls;
    }

    public List<String> getNotificationUrls() {
        return notificationUrls;
    }

    public void setNotificationUrls(List<String> notificationUrls) {
        this.notificationUrls = notificationUrls;
    }

    public List<String> getAffordabilityCallbackUrls() {
        return affordabilityCallbackUrls;
    }

    public void setAffordabilityCallbackUrls(List<String> affordabilityCallbackUrls) {
        this.affordabilityCallbackUrls = affordabilityCallbackUrls;
    }
}
