package org.bitcoinj.core.listeners;

@Deprecated
public interface BlockChainListener extends NewBestBlockListener, TransactionReceivedInBlockListener, ReorganizeListener {
}
