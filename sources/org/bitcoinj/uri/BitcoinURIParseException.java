package org.bitcoinj.uri;

public class BitcoinURIParseException extends Exception {
    public BitcoinURIParseException(String str) {
        super(str);
    }

    public BitcoinURIParseException(String str, Throwable th) {
        super(str, th);
    }
}
