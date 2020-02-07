package org.bitcoinj.wallet;

import java.util.Collection;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.TransactionOutput;

public class CoinSelection {
    public Collection<TransactionOutput> gathered;
    public Coin valueGathered;

    public CoinSelection(Coin coin, Collection<TransactionOutput> collection) {
        this.valueGathered = coin;
        this.gathered = collection;
    }
}
