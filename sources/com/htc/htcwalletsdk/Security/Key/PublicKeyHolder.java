package com.htc.htcwalletsdk.Security.Key;

public class PublicKeyHolder extends KeyBase {
    public String getKey() {
        return this.mPublicKey;
    }

    public String getKeyPath() {
        return this.mKeyPath;
    }

    public void setKey(PublicKeyHolder publicKeyHolder) {
        this.mPublicKey = publicKeyHolder.mPublicKey;
        this.mKeyPath = publicKeyHolder.mKeyPath;
    }

    public void setKey(String str) {
        this.mPublicKey = str;
    }

    public void setKeyPath(String str) {
        this.mKeyPath = str;
    }
}
