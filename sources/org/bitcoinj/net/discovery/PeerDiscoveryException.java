package org.bitcoinj.net.discovery;

public class PeerDiscoveryException extends Exception {
    private static final long serialVersionUID = -2863411151549391392L;

    public PeerDiscoveryException() {
    }

    public PeerDiscoveryException(String str) {
        super(str);
    }

    public PeerDiscoveryException(Throwable th) {
        super(th);
    }

    public PeerDiscoveryException(String str, Throwable th) {
        super(str, th);
    }
}
