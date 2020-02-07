package org.bitcoinj.wallet.listeners;

import org.bitcoinj.wallet.C3530Wallet;

public interface WalletReorganizeEventListener {
    void onReorganize(C3530Wallet wallet);
}
