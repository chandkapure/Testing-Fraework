package com.paywallet.POJOClasses;

import java.util.List;

public class IncomeCallBack {

    List<String> incomeCallbackUrls;
    List<String> notificationUrls;

    public IncomeCallBack(List<String> identitycallbk, List<String> notify)
    {
        this.incomeCallbackUrls = identitycallbk;
        this.notificationUrls = notify;

    }

    public List<String> getIncomeCallbackUrls() {
        return incomeCallbackUrls;
    }

    public void setIncomeCallbackUrls(List<String> incomeCallbackUrls) {
        this.incomeCallbackUrls = incomeCallbackUrls;
    }

    public List<String> getNotificationUrls() {
        return notificationUrls;
    }

    public void setNotificationUrls(List<String> notificationUrls) {
        this.notificationUrls = notificationUrls;
    }


}
