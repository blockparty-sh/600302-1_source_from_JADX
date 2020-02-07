package org.bitcoinj.core;

public class UTXOProviderException extends Exception {
    public UTXOProviderException() {
    }

    public UTXOProviderException(String str) {
        super(str);
    }

    public UTXOProviderException(String str, Throwable th) {
        super(str, th);
    }

    public UTXOProviderException(Throwable th) {
        super(th);
    }
}
