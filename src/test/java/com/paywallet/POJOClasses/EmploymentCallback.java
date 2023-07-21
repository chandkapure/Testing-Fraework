package com.paywallet.POJOClasses;

import java.util.List;

public class EmploymentCallback {

    List<String> employmentCallbackUrls;
    List<String> notificationUrls;

    public EmploymentCallback(List<String> employmentcallbk, List<String> notify)
    {
        this.employmentCallbackUrls = employmentcallbk;
        this.notificationUrls = notify;

    }

    public List<String> getEmploymentCallbackUrls() {
        return employmentCallbackUrls;
    }

    public void setEmploymentCallbackUrls(List<String> employmentCallbackUrls) {
        this.employmentCallbackUrls = employmentCallbackUrls;
    }

    public List<String> getNotificationUrls() {
        return notificationUrls;
    }

    public void setNotificationUrls(List<String> notificationUrls) {
        this.notificationUrls = notificationUrls;
    }
}
