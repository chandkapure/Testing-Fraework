package com.paywallet.POJOClasses;

import java.util.List;

public class AffordebilityCallBack {
    List<String> affordabilityCallbackUrls;
    List<String> notificationUrls;

    public AffordebilityCallBack(List<String> affordabilityCallbackUrls,List<String> notificationUrls)
    {
        this.affordabilityCallbackUrls=affordabilityCallbackUrls;
        this.notificationUrls = notificationUrls;

    }
    public List<String> getAffordabilityCallbackUrls() {
        return affordabilityCallbackUrls;
    }

    public void setAffordabilityCallbackUrls(List<String> affordabilityCallbackUrls) {
        this.affordabilityCallbackUrls = affordabilityCallbackUrls;
    }

    public List<String> getNotificationUrls() {
        return notificationUrls;
    }

    public void setNotificationUrls(List<String> notificationUrls) {
        this.notificationUrls = notificationUrls;
    }
}
