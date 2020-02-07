package org.bitcoinj.uri;

public class RequiredFieldValidationException extends BitcoinURIParseException {
    public RequiredFieldValidationException(String str) {
        super(str);
    }

    public RequiredFieldValidationException(String str, Throwable th) {
        super(str, th);
    }
}
