package org.bitcoinj.jni;

import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.listeners.WalletReorganizeEventListener;

public class NativeWalletReorganizeEventListener implements WalletReorganizeEventListener {
    public long ptr;

    public native void onReorganize(C3530Wallet wallet);
}
