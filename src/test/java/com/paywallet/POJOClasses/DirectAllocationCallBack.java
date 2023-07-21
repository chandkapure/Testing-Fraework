package com.paywallet.POJOClasses;

import java.util.List;

public class DirectAllocationCallBack {
    List<String> allocationCallbackUrls;
    List<String> notificationUrls;

    public DirectAllocationCallBack(List<String> allocationCallbackUrls ,List<String> notificationUrls )
    {
        this.allocationCallbackUrls = allocationCallbackUrls;
        this.notificationUrls = notificationUrls;
    }

    public List<String> getAllocationCallbackUrls() {
        return allocationCallbackUrls;
    }

    public void setAllocationCallbackUrls(List<String> allocationCallbackUrls) {
        this.allocationCallbackUrls = allocationCallbackUrls;
    }

    public List<String> getNotificationUrls() {
        return notificationUrls;
    }

    public void setNotificationUrls(List<String> notificationUrls) {
        this.notificationUrls = notificationUrls;
    }
}
