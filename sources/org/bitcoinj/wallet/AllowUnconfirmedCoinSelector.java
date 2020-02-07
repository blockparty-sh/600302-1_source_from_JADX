package org.bitcoinj.wallet;

import org.bitcoinj.core.Transaction;

public class AllowUnconfirmedCoinSelector extends DefaultCoinSelector {
    private static AllowUnconfirmedCoinSelector instance;

    /* access modifiers changed from: protected */
    public boolean shouldSelect(Transaction transaction) {
        return true;
    }

    public static AllowUnconfirmedCoinSelector get() {
        if (instance == null) {
            instance = new AllowUnconfirmedCoinSelector();
        }
        return instance;
    }
}
