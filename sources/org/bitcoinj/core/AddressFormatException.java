package org.bitcoinj.core;

public class AddressFormatException extends IllegalArgumentException {
    public AddressFormatException() {
    }

    public AddressFormatException(String str) {
        super(str);
    }
}
