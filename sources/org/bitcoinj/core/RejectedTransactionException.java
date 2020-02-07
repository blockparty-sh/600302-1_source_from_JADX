package org.bitcoinj.core;

public class RejectedTransactionException extends Exception {
    private RejectMessage rejectMessage;

    /* renamed from: tx */
    private Transaction f800tx;

    public RejectedTransactionException(Transaction transaction, RejectMessage rejectMessage2) {
        super(rejectMessage2.toString());
        this.f800tx = transaction;
        this.rejectMessage = rejectMessage2;
    }

    public Transaction getTransaction() {
        return this.f800tx;
    }

    public RejectMessage getRejectMessage() {
        return this.rejectMessage;
    }
}
