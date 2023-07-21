package com.paywallet.POJOClasses;

import java.util.List;

public class IdentityCallBack {
    List<String> identityCallbackUrls;
    List<String> notificationUrls;


    public IdentityCallBack(List<String> identitycallbk, List<String> notify)
    {
        this.identityCallbackUrls = identitycallbk;
        this.notificationUrls = notify;

    }

    public List<String> getIdentityCallbackUrls() {
        return identityCallbackUrls;
    }

    public void setIdentityCallbackUrls(List<String> identityCallbackUrls) {
        this.identityCallbackUrls = identityCallbackUrls;
    }

    public List<String> getNotificationUrls() {
        return notificationUrls;
    }

    public void setNotificationUrls(List<String> notificationUrls) {
        this.notificationUrls = notificationUrls;
    }
}
