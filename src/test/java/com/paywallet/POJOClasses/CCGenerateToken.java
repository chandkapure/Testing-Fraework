package com.paywallet.POJOClasses;

public class CCGenerateToken {
    public String key;
    public String secret;

    public CCGenerateToken(String ClientID, String ClientSecret)
    {
        this.key = ClientID;
        this.secret = ClientSecret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
