package org.bitcoinj.core;

public class PeerException extends Exception {
    public PeerException(String str) {
        super(str);
    }

    public PeerException(Exception exc) {
        super(exc);
    }

    public PeerException(String str, Exception exc) {
        super(str, exc);
    }
}
