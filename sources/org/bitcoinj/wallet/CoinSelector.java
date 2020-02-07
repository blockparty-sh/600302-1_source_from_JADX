package org.bitcoinj.wallet;

import java.util.List;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.TransactionOutput;

public interface CoinSelector {
    CoinSelection select(Coin coin, List<TransactionOutput> list);
}
