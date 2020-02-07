package org.bitcoinj.core;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.bitcoinj.core.TransactionConfidence.Listener;
import org.bitcoinj.core.TransactionConfidence.Listener.ChangeReason;
import org.bitcoinj.core.listeners.PreMessageReceivedEventListener;
import org.bitcoinj.utils.Threading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionBroadcast {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(TransactionBroadcast.class);
    @VisibleForTesting
    public static Random random = new Random();
    @Nullable
    private ProgressCallback callback;
    /* access modifiers changed from: private */
    public final SettableFuture<Transaction> future;
    /* access modifiers changed from: private */
    public int minConnections;
    private boolean mined;
    private int numSeemPeers;
    /* access modifiers changed from: private */
    public int numWaitingFor;
    /* access modifiers changed from: private */
    public final PeerGroup peerGroup;
    @Nullable
    private Executor progressCallbackExecutor;
    /* access modifiers changed from: private */
    public PreMessageReceivedEventListener rejectionListener;
    /* access modifiers changed from: private */
    public Map<Peer, RejectMessage> rejects;
    /* access modifiers changed from: private */

    /* renamed from: tx */
    public final Transaction f801tx;

    private class ConfidenceChange implements Listener {
        private ConfidenceChange() {
        }

        public void onConfidenceChanged(TransactionConfidence transactionConfidence, ChangeReason changeReason) {
            int numBroadcastPeers = transactionConfidence.numBroadcastPeers() + TransactionBroadcast.this.rejects.size();
            boolean z = TransactionBroadcast.this.f801tx.getAppearsInHashes() != null;
            Logger access$400 = TransactionBroadcast.log;
            Object[] objArr = new Object[4];
            objArr[0] = changeReason;
            objArr[1] = TransactionBroadcast.this.f801tx.getHashAsString();
            objArr[2] = Integer.valueOf(numBroadcastPeers);
            objArr[3] = z ? " and mined" : "";
            access$400.info("broadcastTransaction: {}:  TX {} seen by {} peers{}", objArr);
            TransactionBroadcast.this.invokeAndRecord(numBroadcastPeers, z);
            if (numBroadcastPeers >= TransactionBroadcast.this.numWaitingFor || z) {
                TransactionBroadcast.log.info("broadcastTransaction: {} complete", (Object) TransactionBroadcast.this.f801tx.getHash());
                TransactionBroadcast.this.peerGroup.removePreMessageReceivedEventListener(TransactionBroadcast.this.rejectionListener);
                transactionConfidence.removeEventListener(this);
                TransactionBroadcast.this.future.set(TransactionBroadcast.this.f801tx);
            }
        }
    }

    private class EnoughAvailablePeers implements Runnable {
        private EnoughAvailablePeers() {
        }

        public void run() {
            List connectedPeers = TransactionBroadcast.this.peerGroup.getConnectedPeers();
            if (TransactionBroadcast.this.minConnections > 1) {
                TransactionBroadcast.this.f801tx.getConfidence().addEventListener(new ConfidenceChange());
            }
            int size = connectedPeers.size();
            int max = (int) Math.max(1, Math.round(Math.ceil(((double) connectedPeers.size()) / 2.0d)));
            TransactionBroadcast.this.numWaitingFor = (int) Math.ceil(((double) (connectedPeers.size() - max)) / 2.0d);
            Collections.shuffle(connectedPeers, TransactionBroadcast.random);
            List<Peer> subList = connectedPeers.subList(0, max);
            TransactionBroadcast.log.info("broadcastTransaction: We have {} peers, adding {} to the memory pool", (Object) Integer.valueOf(size), (Object) TransactionBroadcast.this.f801tx.getHashAsString());
            TransactionBroadcast.log.info("Sending to {} peers, will wait for {}, sending to: {}", Integer.valueOf(max), Integer.valueOf(TransactionBroadcast.this.numWaitingFor), Joiner.m96on(",").join((Iterable<?>) subList));
            for (Peer peer : subList) {
                try {
                    peer.sendMessage(TransactionBroadcast.this.f801tx);
                } catch (Exception e) {
                    TransactionBroadcast.log.error("Caught exception sending to {}", (Object) peer, (Object) e);
                }
            }
            if (TransactionBroadcast.this.minConnections == 1) {
                TransactionBroadcast.this.peerGroup.removePreMessageReceivedEventListener(TransactionBroadcast.this.rejectionListener);
                TransactionBroadcast.this.future.set(TransactionBroadcast.this.f801tx);
            }
        }
    }

    public interface ProgressCallback {
        void onBroadcastProgress(double d);
    }

    TransactionBroadcast(PeerGroup peerGroup2, Transaction transaction) {
        this.future = SettableFuture.create();
        this.rejects = Collections.synchronizedMap(new HashMap());
        this.rejectionListener = new PreMessageReceivedEventListener() {
            public Message onPreMessageReceived(Peer peer, Message message) {
                if (message instanceof RejectMessage) {
                    RejectMessage rejectMessage = (RejectMessage) message;
                    if (TransactionBroadcast.this.f801tx.getHash().equals(rejectMessage.getRejectedObjectHash())) {
                        TransactionBroadcast.this.rejects.put(peer, rejectMessage);
                        int size = TransactionBroadcast.this.rejects.size();
                        long round = Math.round(((double) TransactionBroadcast.this.numWaitingFor) / 2.0d);
                        if (((long) size) > round) {
                            TransactionBroadcast.log.warn("Threshold for considering broadcast rejected has been reached ({}/{})", (Object) Integer.valueOf(size), (Object) Long.valueOf(round));
                            TransactionBroadcast.this.future.setException(new RejectedTransactionException(TransactionBroadcast.this.f801tx, rejectMessage));
                            TransactionBroadcast.this.peerGroup.removePreMessageReceivedEventListener(this);
                        }
                    }
                }
                return message;
            }
        };
        this.peerGroup = peerGroup2;
        this.f801tx = transaction;
        this.minConnections = Math.max(1, peerGroup2.getMinBroadcastConnections());
    }

    private TransactionBroadcast(Transaction transaction) {
        this.future = SettableFuture.create();
        this.rejects = Collections.synchronizedMap(new HashMap());
        this.rejectionListener = new PreMessageReceivedEventListener() {
            public Message onPreMessageReceived(Peer peer, Message message) {
                if (message instanceof RejectMessage) {
                    RejectMessage rejectMessage = (RejectMessage) message;
                    if (TransactionBroadcast.this.f801tx.getHash().equals(rejectMessage.getRejectedObjectHash())) {
                        TransactionBroadcast.this.rejects.put(peer, rejectMessage);
                        int size = TransactionBroadcast.this.rejects.size();
                        long round = Math.round(((double) TransactionBroadcast.this.numWaitingFor) / 2.0d);
                        if (((long) size) > round) {
                            TransactionBroadcast.log.warn("Threshold for considering broadcast rejected has been reached ({}/{})", (Object) Integer.valueOf(size), (Object) Long.valueOf(round));
                            TransactionBroadcast.this.future.setException(new RejectedTransactionException(TransactionBroadcast.this.f801tx, rejectMessage));
                            TransactionBroadcast.this.peerGroup.removePreMessageReceivedEventListener(this);
                        }
                    }
                }
                return message;
            }
        };
        this.peerGroup = null;
        this.f801tx = transaction;
    }

    @VisibleForTesting
    public static TransactionBroadcast createMockBroadcast(Transaction transaction, final SettableFuture<Transaction> settableFuture) {
        return new TransactionBroadcast(transaction) {
            public ListenableFuture<Transaction> broadcast() {
                return settableFuture;
            }

            public ListenableFuture<Transaction> future() {
                return settableFuture;
            }
        };
    }

    public ListenableFuture<Transaction> future() {
        return this.future;
    }

    public void setMinConnections(int i) {
        this.minConnections = i;
    }

    public ListenableFuture<Transaction> broadcast() {
        this.peerGroup.addPreMessageReceivedEventListener(Threading.SAME_THREAD, this.rejectionListener);
        log.info("Waiting for {} peers required for broadcast, we have {} ...", (Object) Integer.valueOf(this.minConnections), (Object) Integer.valueOf(this.peerGroup.getConnectedPeers().size()));
        this.peerGroup.waitForPeers(this.minConnections).addListener(new EnoughAvailablePeers(), Threading.SAME_THREAD);
        return this.future;
    }

    /* access modifiers changed from: private */
    public void invokeAndRecord(int i, boolean z) {
        synchronized (this) {
            this.numSeemPeers = i;
            this.mined = z;
        }
        invokeProgressCallback(i, z);
    }

    private void invokeProgressCallback(int i, boolean z) {
        final ProgressCallback progressCallback;
        Executor executor;
        double d;
        synchronized (this) {
            progressCallback = this.callback;
            executor = this.progressCallbackExecutor;
        }
        if (progressCallback != null) {
            if (z) {
                d = 1.0d;
            } else {
                d = ((double) i) / ((double) this.numWaitingFor);
            }
            final double min = Math.min(1.0d, d);
            Preconditions.checkState(min >= 0.0d && min <= 1.0d, Double.valueOf(min));
            if (executor == null) {
                try {
                    progressCallback.onBroadcastProgress(min);
                } catch (Throwable th) {
                    log.error("Exception during progress callback", th);
                }
            } else {
                executor.execute(new Runnable() {
                    public void run() {
                        progressCallback.onBroadcastProgress(min);
                    }
                });
            }
        }
    }

    public void setProgressCallback(ProgressCallback progressCallback) {
        setProgressCallback(progressCallback, Threading.USER_THREAD);
    }

    public void setProgressCallback(ProgressCallback progressCallback, @Nullable Executor executor) {
        int i;
        boolean z;
        boolean z2;
        synchronized (this) {
            this.callback = progressCallback;
            this.progressCallbackExecutor = executor;
            i = this.numSeemPeers;
            z = this.mined;
            z2 = this.numWaitingFor > 0;
        }
        if (z2) {
            invokeProgressCallback(i, z);
        }
    }
}
