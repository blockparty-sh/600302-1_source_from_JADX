package org.bitcoinj.crypto;

public class KeyCrypterException extends RuntimeException {
    private static final long serialVersionUID = -4441989608332681377L;

    public KeyCrypterException(String str) {
        super(str);
    }

    public KeyCrypterException(String str, Throwable th) {
        super(str, th);
    }
}
