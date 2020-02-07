package org.bitcoinj.wallet;

public class UnreadableWalletException extends Exception {

    public static class BadPassword extends UnreadableWalletException {
        public BadPassword() {
            super("Password incorrect");
        }
    }

    public static class FutureVersion extends UnreadableWalletException {
        public FutureVersion() {
            super("Unknown wallet version from the future.");
        }
    }

    public static class WrongNetwork extends UnreadableWalletException {
        public WrongNetwork() {
            super("Mismatched network ID");
        }
    }

    public UnreadableWalletException(String str) {
        super(str);
    }

    public UnreadableWalletException(String str, Throwable th) {
        super(str, th);
    }
}
