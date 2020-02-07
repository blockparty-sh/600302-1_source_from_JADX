package org.bitcoinj.jni;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;

public class NativeWalletCoinsReceivedEventListener implements WalletCoinsReceivedEventListener {
    public long ptr;

    public native void onCoinsReceived(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2);
}
