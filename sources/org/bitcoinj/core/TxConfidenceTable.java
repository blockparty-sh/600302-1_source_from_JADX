package org.bitcoinj.core;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import org.bitcoinj.core.TransactionConfidence.Listener.ChangeReason;
import org.bitcoinj.utils.Threading;

public class TxConfidenceTable {
    public static final int MAX_SIZE = 1000;
    protected ReentrantLock lock;
    private ReferenceQueue<TransactionConfidence> referenceQueue;
    private LinkedHashMap<Sha256Hash, WeakConfidenceReference> table;

    private static class WeakConfidenceReference extends WeakReference<TransactionConfidence> {
        public Sha256Hash hash;

        public WeakConfidenceReference(TransactionConfidence transactionConfidence, ReferenceQueue<TransactionConfidence> referenceQueue) {
            super(transactionConfidence, referenceQueue);
            this.hash = transactionConfidence.getTransactionHash();
        }
    }

    public TxConfidenceTable(final int i) {
        this.lock = Threading.lock("txconfidencetable");
        this.table = new LinkedHashMap<Sha256Hash, WeakConfidenceReference>() {
            /* access modifiers changed from: protected */
            public boolean removeEldestEntry(Entry<Sha256Hash, WeakConfidenceReference> entry) {
                return size() > i;
            }
        };
        this.referenceQueue = new ReferenceQueue<>();
    }

    public TxConfidenceTable() {
        this(1000);
    }

    private void cleanTable() {
        this.lock.lock();
        while (true) {
            try {
                Reference poll = this.referenceQueue.poll();
                if (poll != null) {
                    this.table.remove(((WeakConfidenceReference) poll).hash);
                } else {
                    return;
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public int numBroadcastPeers(Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            cleanTable();
            WeakConfidenceReference weakConfidenceReference = (WeakConfidenceReference) this.table.get(sha256Hash);
            if (weakConfidenceReference != null) {
                TransactionConfidence transactionConfidence = (TransactionConfidence) weakConfidenceReference.get();
                if (transactionConfidence == null) {
                    this.table.remove(sha256Hash);
                } else {
                    int numBroadcastPeers = transactionConfidence.numBroadcastPeers();
                    this.lock.unlock();
                    return numBroadcastPeers;
                }
            }
            return 0;
        } finally {
            this.lock.unlock();
        }
    }

    public TransactionConfidence seen(Sha256Hash sha256Hash, PeerAddress peerAddress) {
        this.lock.lock();
        cleanTable();
        TransactionConfidence orCreate = getOrCreate(sha256Hash);
        boolean markBroadcastBy = orCreate.markBroadcastBy(peerAddress);
        this.lock.unlock();
        if (markBroadcastBy) {
            orCreate.queueListeners(ChangeReason.SEEN_PEERS);
        }
        return orCreate;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r0 != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bitcoinj.core.TransactionConfidence getOrCreate(org.bitcoinj.core.Sha256Hash r5) {
        /*
            r4 = this;
            com.google.common.base.Preconditions.checkNotNull(r5)
            java.util.concurrent.locks.ReentrantLock r0 = r4.lock
            r0.lock()
            java.util.LinkedHashMap<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.TxConfidenceTable$WeakConfidenceReference> r0 = r4.table     // Catch:{ all -> 0x0032 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x0032 }
            org.bitcoinj.core.TxConfidenceTable$WeakConfidenceReference r0 = (org.bitcoinj.core.TxConfidenceTable.WeakConfidenceReference) r0     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0020
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0032 }
            org.bitcoinj.core.TransactionConfidence r0 = (org.bitcoinj.core.TransactionConfidence) r0     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0020
        L_0x001a:
            java.util.concurrent.locks.ReentrantLock r5 = r4.lock
            r5.unlock()
            return r0
        L_0x0020:
            org.bitcoinj.core.TransactionConfidence r0 = new org.bitcoinj.core.TransactionConfidence     // Catch:{ all -> 0x0032 }
            r0.<init>(r5)     // Catch:{ all -> 0x0032 }
            java.util.LinkedHashMap<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.TxConfidenceTable$WeakConfidenceReference> r1 = r4.table     // Catch:{ all -> 0x0032 }
            org.bitcoinj.core.TxConfidenceTable$WeakConfidenceReference r2 = new org.bitcoinj.core.TxConfidenceTable$WeakConfidenceReference     // Catch:{ all -> 0x0032 }
            java.lang.ref.ReferenceQueue<org.bitcoinj.core.TransactionConfidence> r3 = r4.referenceQueue     // Catch:{ all -> 0x0032 }
            r2.<init>(r0, r3)     // Catch:{ all -> 0x0032 }
            r1.put(r5, r2)     // Catch:{ all -> 0x0032 }
            goto L_0x001a
        L_0x0032:
            r5 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = r4.lock
            r0.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.TxConfidenceTable.getOrCreate(org.bitcoinj.core.Sha256Hash):org.bitcoinj.core.TransactionConfidence");
    }

    @Nullable
    public TransactionConfidence get(Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            WeakConfidenceReference weakConfidenceReference = (WeakConfidenceReference) this.table.get(sha256Hash);
            if (weakConfidenceReference != null) {
                TransactionConfidence transactionConfidence = (TransactionConfidence) weakConfidenceReference.get();
                if (transactionConfidence != null) {
                    this.lock.unlock();
                    return transactionConfidence;
                }
            }
            return null;
        } finally {
            this.lock.unlock();
        }
    }
}
