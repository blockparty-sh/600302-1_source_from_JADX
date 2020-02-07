package org.bitcoinj.jni;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsSentEventListener;

public class NativeWalletCoinsSentEventListener implements WalletCoinsSentEventListener {
    public long ptr;

    public native void onCoinsSent(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2);
}
