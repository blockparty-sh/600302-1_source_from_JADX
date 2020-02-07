package org.bitcoinj.jni;

import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.listeners.WalletChangeEventListener;

public class NativeWalletChangeEventListener implements WalletChangeEventListener {
    public long ptr;

    public native void onWalletChanged(C3530Wallet wallet);
}
