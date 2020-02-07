package org.bitcoinj.core;

public class PrunedException extends Exception {
    private Sha256Hash hash;

    public PrunedException(Sha256Hash sha256Hash) {
        super(sha256Hash.toString());
        this.hash = sha256Hash;
    }

    public Sha256Hash getHash() {
        return this.hash;
    }
}
