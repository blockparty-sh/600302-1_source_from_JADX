package org.bitcoinj.wallet.listeners;

import java.util.List;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.C3530Wallet;

@Deprecated
public abstract class AbstractWalletEventListener extends AbstractKeyChainEventListener implements WalletEventListener {
    public void onChange() {
    }

    public void onCoinsReceived(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2) {
        onChange();
    }

    public void onCoinsSent(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2) {
        onChange();
    }

    public void onReorganize(C3530Wallet wallet) {
        onChange();
    }

    public void onTransactionConfidenceChanged(C3530Wallet wallet, Transaction transaction) {
        onChange();
    }

    public void onKeysAdded(List<ECKey> list) {
        onChange();
    }

    public void onScriptsChanged(C3530Wallet wallet, List<Script> list, boolean z) {
        onChange();
    }

    public void onWalletChanged(C3530Wallet wallet) {
        onChange();
    }
}
