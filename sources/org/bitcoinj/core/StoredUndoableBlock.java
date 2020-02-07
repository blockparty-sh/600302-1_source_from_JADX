package org.bitcoinj.core;

import java.util.List;

public class StoredUndoableBlock {
    Sha256Hash blockHash;
    private List<Transaction> transactions;
    private TransactionOutputChanges txOutChanges;

    public StoredUndoableBlock(Sha256Hash sha256Hash, TransactionOutputChanges transactionOutputChanges) {
        this.blockHash = sha256Hash;
        this.transactions = null;
        this.txOutChanges = transactionOutputChanges;
    }

    public StoredUndoableBlock(Sha256Hash sha256Hash, List<Transaction> list) {
        this.blockHash = sha256Hash;
        this.txOutChanges = null;
        this.transactions = list;
    }

    public TransactionOutputChanges getTxOutChanges() {
        return this.txOutChanges;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public Sha256Hash getHash() {
        return this.blockHash;
    }

    public int hashCode() {
        return this.blockHash.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return getHash().equals(((StoredUndoableBlock) obj).getHash());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Undoable Block ");
        sb.append(this.blockHash);
        return sb.toString();
    }
}
