package org.bitcoinj.uri;

public class OptionalFieldValidationException extends BitcoinURIParseException {
    public OptionalFieldValidationException(String str) {
        super(str);
    }

    public OptionalFieldValidationException(String str, Throwable th) {
        super(str, th);
    }
}
