package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import org.bitcoinj.core.Transaction;

public class WalletTransaction {
    private final Pool pool;
    private final Transaction transaction;

    public enum Pool {
        UNSPENT,
        SPENT,
        DEAD,
        PENDING
    }

    public WalletTransaction(Pool pool2, Transaction transaction2) {
        this.pool = (Pool) Preconditions.checkNotNull(pool2);
        this.transaction = transaction2;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public Pool getPool() {
        return this.pool;
    }
}
