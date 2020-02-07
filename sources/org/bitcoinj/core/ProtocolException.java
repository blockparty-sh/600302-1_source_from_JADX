package org.bitcoinj.core;

public class ProtocolException extends VerificationException {
    public ProtocolException(String str) {
        super(str);
    }

    public ProtocolException(Exception exc) {
        super(exc);
    }

    public ProtocolException(String str, Exception exc) {
        super(str, exc);
    }
}
