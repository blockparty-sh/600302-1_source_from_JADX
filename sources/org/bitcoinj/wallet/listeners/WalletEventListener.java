package org.bitcoinj.wallet.listeners;

import org.bitcoinj.core.listeners.TransactionConfidenceEventListener;

@Deprecated
public interface WalletEventListener extends KeyChainEventListener, WalletChangeEventListener, WalletCoinsReceivedEventListener, WalletCoinsSentEventListener, WalletReorganizeEventListener, ScriptsChangeEventListener, TransactionConfidenceEventListener {
}
