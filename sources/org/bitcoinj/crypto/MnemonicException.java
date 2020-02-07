package org.bitcoinj.crypto;

public class MnemonicException extends Exception {

    public static class MnemonicChecksumException extends MnemonicException {
    }

    public static class MnemonicLengthException extends MnemonicException {
        public MnemonicLengthException(String str) {
            super(str);
        }
    }

    public static class MnemonicWordException extends MnemonicException {
        public final String badWord;

        public MnemonicWordException(String str) {
            this.badWord = str;
        }
    }

    public MnemonicException() {
    }

    public MnemonicException(String str) {
        super(str);
    }
}
