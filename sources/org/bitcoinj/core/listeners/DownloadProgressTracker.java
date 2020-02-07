package org.bitcoinj.core.listeners;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.FilteredBlock;
import org.bitcoinj.core.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadProgressTracker extends AbstractPeerDataEventListener {
    private static final Logger log = LoggerFactory.getLogger(DownloadProgressTracker.class);
    private boolean caughtUp = false;
    private SettableFuture<Long> future = SettableFuture.create();
    private int lastPercent = 0;
    private int originalBlocksLeft = -1;

    /* access modifiers changed from: protected */
    public void doneDownload() {
    }

    public void onChainDownloadStarted(Peer peer, int i) {
        if (i > 0 && this.originalBlocksLeft == -1) {
            startDownload(i);
        }
        if (this.originalBlocksLeft == -1) {
            this.originalBlocksLeft = i;
        } else {
            log.info("Chain download switched to {}", (Object) peer);
        }
        if (i == 0) {
            doneDownload();
            this.future.set(Long.valueOf(peer.getBestHeight()));
        }
    }

    public void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int i) {
        if (!this.caughtUp) {
            if (i == 0) {
                this.caughtUp = true;
                doneDownload();
                this.future.set(Long.valueOf(peer.getBestHeight()));
            }
            if (i >= 0) {
                int i2 = this.originalBlocksLeft;
                if (i2 > 0) {
                    double d = 100.0d - ((((double) i) / ((double) i2)) * 100.0d);
                    int i3 = (int) d;
                    if (i3 != this.lastPercent) {
                        progress(d, i, new Date(block.getTimeSeconds() * 1000));
                        this.lastPercent = i3;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void progress(double d, int i, Date date) {
        log.info(String.format(Locale.US, "Chain download %d%% done with %d blocks to go, block date %s", new Object[]{Integer.valueOf((int) d), Integer.valueOf(i), C3447Utils.dateTimeFormat(date)}));
    }

    /* access modifiers changed from: protected */
    public void startDownload(int i) {
        Logger logger = log;
        StringBuilder sb = new StringBuilder();
        sb.append("Downloading block chain of size ");
        sb.append(i);
        sb.append(". ");
        sb.append(i > 1000 ? "This may take a while." : "");
        logger.info(sb.toString());
    }

    public void await() throws InterruptedException {
        try {
            this.future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public ListenableFuture<Long> getFuture() {
        return this.future;
    }
}
