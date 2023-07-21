package com.paywallet.POJOClasses;

import java.util.List;

public class CCCancel {
    List<String> profiles;

    public CCCancel(List<String> profile)
    {
        this.profiles=profile;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }
}
