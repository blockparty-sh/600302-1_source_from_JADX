package org.bitcoinj.wallet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;

public class FilteringCoinSelector implements CoinSelector {
    protected CoinSelector delegate;
    protected HashSet<TransactionOutPoint> spent = new HashSet<>();

    public FilteringCoinSelector(CoinSelector coinSelector) {
        this.delegate = coinSelector;
    }

    public void excludeOutputsSpentBy(Transaction transaction) {
        for (TransactionInput outpoint : transaction.getInputs()) {
            this.spent.add(outpoint.getOutpoint());
        }
    }

    public CoinSelection select(Coin coin, List<TransactionOutput> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (this.spent.contains(((TransactionOutput) it.next()).getOutPointFor())) {
                it.remove();
            }
        }
        return this.delegate.select(coin, list);
    }
}
