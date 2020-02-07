package org.bitcoinj.jni;

import java.util.List;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.listeners.WalletEventListener;

public class NativeWalletEventListener implements WalletEventListener {
    public long ptr;

    public native void onCoinsReceived(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2);

    public native void onCoinsSent(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2);

    public native void onKeysAdded(List<ECKey> list);

    public native void onReorganize(C3530Wallet wallet);

    public native void onScriptsChanged(C3530Wallet wallet, List<Script> list, boolean z);

    public native void onTransactionConfidenceChanged(C3530Wallet wallet, Transaction transaction);

    public native void onWalletChanged(C3530Wallet wallet);
}
