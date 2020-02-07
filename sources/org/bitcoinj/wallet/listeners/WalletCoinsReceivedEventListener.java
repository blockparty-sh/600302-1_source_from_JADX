package org.bitcoinj.wallet.listeners;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.wallet.C3530Wallet;

public interface WalletCoinsReceivedEventListener {
    void onCoinsReceived(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2);
}
