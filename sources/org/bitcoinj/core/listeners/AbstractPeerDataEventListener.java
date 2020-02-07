package org.bitcoinj.core.listeners;

import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.FilteredBlock;
import org.bitcoinj.core.GetDataMessage;
import org.bitcoinj.core.Message;
import org.bitcoinj.core.Peer;

@Deprecated
public abstract class AbstractPeerDataEventListener implements PeerDataEventListener {
    public List<Message> getData(Peer peer, GetDataMessage getDataMessage) {
        return null;
    }

    public void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int i) {
    }

    public void onChainDownloadStarted(Peer peer, int i) {
    }

    public Message onPreMessageReceived(Peer peer, Message message) {
        return message;
    }
}
