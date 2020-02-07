package org.bitcoinj.core.listeners;

import javax.annotation.Nullable;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.FilteredBlock;
import org.bitcoinj.core.Peer;

public interface BlocksDownloadedEventListener {
    void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int i);
}
