package org.bitcoinj.core;

public class VerificationException extends RuntimeException {

    public static class BlockVersionOutOfDate extends VerificationException {
        public BlockVersionOutOfDate(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append("Block version #");
            sb.append(j);
            sb.append(" is outdated.");
            super(sb.toString());
        }
    }

    public static class CoinbaseHeightMismatch extends VerificationException {
        public CoinbaseHeightMismatch(String str) {
            super(str);
        }
    }

    public static class CoinbaseScriptSizeOutOfRange extends VerificationException {
        public CoinbaseScriptSizeOutOfRange() {
            super("Coinbase script size out of range");
        }
    }

    public static class DuplicatedOutPoint extends VerificationException {
        public DuplicatedOutPoint() {
            super("Duplicated outpoint");
        }
    }

    public static class EmptyInputsOrOutputs extends VerificationException {
        public EmptyInputsOrOutputs() {
            super("Transaction had no inputs or no outputs.");
        }
    }

    public static class ExcessiveValue extends VerificationException {
        public ExcessiveValue() {
            super("Total transaction output value greater than possible");
        }
    }

    public static class LargerThanMaxBlockSize extends VerificationException {
        public LargerThanMaxBlockSize() {
            super("Transaction larger than MAX_BLOCK_SIZE");
        }
    }

    public static class NegativeValueOutput extends VerificationException {
        public NegativeValueOutput() {
            super("Transaction output negative");
        }
    }

    public static class UnexpectedCoinbaseInput extends VerificationException {
        public UnexpectedCoinbaseInput() {
            super("Coinbase input as input in non-coinbase transaction");
        }
    }

    public VerificationException(String str) {
        super(str);
    }

    public VerificationException(Exception exc) {
        super(exc);
    }

    public VerificationException(String str, Throwable th) {
        super(str, th);
    }
}
