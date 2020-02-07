package org.bitcoinj.jni;

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
import org.bitcoinj.core.listeners.OnTransactionBroadcastListener;
import org.bitcoinj.core.listeners.PeerConnectionEventListener;
import org.bitcoinj.core.listeners.PeerDataEventListener;

public class NativePeerEventListener implements PeerConnectionEventListener, PeerDataEventListener, OnTransactionBroadcastListener {
    public long ptr;

    public native List<Message> getData(Peer peer, GetDataMessage getDataMessage);

    public native void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int i);

    public native void onChainDownloadStarted(Peer peer, int i);

    public native void onPeerConnected(Peer peer, int i);

    public native void onPeerDisconnected(Peer peer, int i);

    public native void onPeersDiscovered(Set<PeerAddress> set);

    public native Message onPreMessageReceived(Peer peer, Message message);

    public native void onTransaction(Peer peer, Transaction transaction);
}
