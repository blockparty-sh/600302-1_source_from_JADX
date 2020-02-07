package com.yakivmospan.scytale;

import java.math.BigInteger;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

public final class KeyProps {
    String mAlias;
    String mBlockModes;
    String mEncryptionPaddings;
    Date mEndDate;
    int mKeySize;
    String mKeyType;
    char[] mPassword;
    BigInteger mSerialNumber;
    String mSignatureAlgorithm;
    Date mStartDate;
    X500Principal mSubject;

    public static final class Builder {
        private KeyProps mProps = new KeyProps();

        public Builder setAlias(String str) {
            this.mProps.mAlias = str;
            return this;
        }

        public Builder setKeyType(String str) {
            this.mProps.mKeyType = str;
            return this;
        }

        public Builder setPassword(char[] cArr) {
            this.mProps.mPassword = cArr;
            return this;
        }

        public Builder setKeySize(int i) {
            this.mProps.mKeySize = i;
            return this;
        }

        public Builder setSerialNumber(BigInteger bigInteger) {
            this.mProps.mSerialNumber = bigInteger;
            return this;
        }

        public Builder setSubject(X500Principal x500Principal) {
            this.mProps.mSubject = x500Principal;
            return this;
        }

        public Builder setStartDate(Date date) {
            this.mProps.mStartDate = date;
            return this;
        }

        public Builder setEndDate(Date date) {
            this.mProps.mEndDate = date;
            return this;
        }

        public Builder setBlockModes(String str) {
            this.mProps.mBlockModes = str;
            return this;
        }

        public Builder setEncryptionPaddings(String str) {
            this.mProps.mEncryptionPaddings = str;
            return this;
        }

        public Builder setSignatureAlgorithm(String str) {
            this.mProps.mSignatureAlgorithm = str;
            return this;
        }

        public KeyProps build() {
            return this.mProps;
        }
    }
}
