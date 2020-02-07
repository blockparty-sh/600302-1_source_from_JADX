package org.bitcoinj.core.listeners;

import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.FilteredBlock;
import org.bitcoinj.core.GetDataMessage;
import org.bitcoinj.core.Message;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.Transaction;

@Deprecated
public abstract class AbstractPeerEventListener extends AbstractPeerDataEventListener implements PeerConnectionEventListener, OnTransactionBroadcastListener {
    public List<Message> getData(Peer peer, GetDataMessage getDataMessage) {
        return null;
    }

    public void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int i) {
    }

    public void onChainDownloadStarted(Peer peer, int i) {
    }

    public void onPeerConnected(Peer peer, int i) {
    }

    public void onPeerDisconnected(Peer peer, int i) {
    }

    public void onPeersDiscovered(Set<PeerAddress> set) {
    }

    public Message onPreMessageReceived(Peer peer, Message message) {
        return message;
    }

    public void onTransaction(Peer peer, Transaction transaction) {
    }
}
