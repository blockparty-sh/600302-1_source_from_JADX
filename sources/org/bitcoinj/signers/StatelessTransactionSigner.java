package org.bitcoinj.signers;

public abstract class StatelessTransactionSigner implements TransactionSigner {
    public void deserialize(byte[] bArr) {
    }

    public byte[] serialize() {
        return new byte[0];
    }
}
