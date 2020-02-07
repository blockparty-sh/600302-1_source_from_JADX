package org.bitcoinj.wallet.listeners;

import org.bitcoinj.wallet.C3530Wallet;

public interface WalletChangeEventListener {
    void onWalletChanged(C3530Wallet wallet);
}
