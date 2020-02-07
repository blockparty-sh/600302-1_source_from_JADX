package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.bitcoinj.utils.ListenerRegistration;
import org.bitcoinj.utils.Threading;

public class TransactionConfidence {
    private static final Set<TransactionConfidence> pinnedConfidenceObjects = Collections.synchronizedSet(new HashSet());
    private int appearedAtChainHeight = -1;
    private CopyOnWriteArrayList<PeerAddress> broadcastBy = new CopyOnWriteArrayList<>();
    private ConfidenceType confidenceType = ConfidenceType.UNKNOWN;
    private int depth;
    private final Sha256Hash hash;
    private Date lastBroadcastedAt;
    private CopyOnWriteArrayList<ListenerRegistration<Listener>> listeners = new CopyOnWriteArrayList<>();
    private Transaction overridingTransaction;
    private Source source = Source.UNKNOWN;

    /* renamed from: org.bitcoinj.core.TransactionConfidence$3 */
    static /* synthetic */ class C34453 {

        /* renamed from: $SwitchMap$org$bitcoinj$core$TransactionConfidence$ConfidenceType */
        static final /* synthetic */ int[] f802xc05c502d = new int[ConfidenceType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                org.bitcoinj.core.TransactionConfidence$ConfidenceType[] r0 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f802xc05c502d = r0
                int[] r0 = f802xc05c502d     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoinj.core.TransactionConfidence$ConfidenceType r1 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f802xc05c502d     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoinj.core.TransactionConfidence$ConfidenceType r1 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.DEAD     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f802xc05c502d     // Catch:{ NoSuchFieldError -> 0x002a }
                org.bitcoinj.core.TransactionConfidence$ConfidenceType r1 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.PENDING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f802xc05c502d     // Catch:{ NoSuchFieldError -> 0x0035 }
                org.bitcoinj.core.TransactionConfidence$ConfidenceType r1 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.IN_CONFLICT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f802xc05c502d     // Catch:{ NoSuchFieldError -> 0x0040 }
                org.bitcoinj.core.TransactionConfidence$ConfidenceType r1 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.BUILDING     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.TransactionConfidence.C34453.<clinit>():void");
        }
    }

    public enum ConfidenceType {
        BUILDING(1),
        PENDING(2),
        DEAD(4),
        IN_CONFLICT(5),
        UNKNOWN(0);
        
        private int value;

        private ConfidenceType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public interface Listener {

        public enum ChangeReason {
            TYPE,
            DEPTH,
            SEEN_PEERS
        }

        void onConfidenceChanged(TransactionConfidence transactionConfidence, ChangeReason changeReason);
    }

    public enum Source {
        UNKNOWN,
        NETWORK,
        SELF
    }

    public TransactionConfidence(Sha256Hash sha256Hash) {
        this.hash = sha256Hash;
    }

    public void addEventListener(Executor executor, Listener listener) {
        Preconditions.checkNotNull(listener);
        this.listeners.addIfAbsent(new ListenerRegistration(listener, executor));
        pinnedConfidenceObjects.add(this);
    }

    public void addEventListener(Listener listener) {
        addEventListener(Threading.USER_THREAD, listener);
    }

    public boolean removeEventListener(Listener listener) {
        Preconditions.checkNotNull(listener);
        boolean removeFromList = ListenerRegistration.removeFromList(listener, this.listeners);
        if (this.listeners.isEmpty()) {
            pinnedConfidenceObjects.remove(this);
        }
        return removeFromList;
    }

    public synchronized int getAppearedAtChainHeight() {
        if (getConfidenceType() == ConfidenceType.BUILDING) {
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Confidence type is ");
            sb.append(getConfidenceType());
            sb.append(", not BUILDING");
            throw new IllegalStateException(sb.toString());
        }
        return this.appearedAtChainHeight;
    }

    public synchronized void setAppearedAtChainHeight(int i) {
        if (i >= 0) {
            this.appearedAtChainHeight = i;
            this.depth = 1;
            setConfidenceType(ConfidenceType.BUILDING);
        } else {
            throw new IllegalArgumentException("appearedAtChainHeight out of range");
        }
    }

    public synchronized ConfidenceType getConfidenceType() {
        return this.confidenceType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setConfidenceType(org.bitcoinj.core.TransactionConfidence.ConfidenceType r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r0 = r1.confidenceType     // Catch:{ all -> 0x0020 }
            if (r2 != r0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            r1.confidenceType = r2     // Catch:{ all -> 0x0020 }
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r0 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.DEAD     // Catch:{ all -> 0x0020 }
            if (r2 == r0) goto L_0x0010
            r0 = 0
            r1.overridingTransaction = r0     // Catch:{ all -> 0x0020 }
        L_0x0010:
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r0 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.PENDING     // Catch:{ all -> 0x0020 }
            if (r2 == r0) goto L_0x0018
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r0 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.IN_CONFLICT     // Catch:{ all -> 0x0020 }
            if (r2 != r0) goto L_0x001e
        L_0x0018:
            r2 = 0
            r1.depth = r2     // Catch:{ all -> 0x0020 }
            r2 = -1
            r1.appearedAtChainHeight = r2     // Catch:{ all -> 0x0020 }
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.TransactionConfidence.setConfidenceType(org.bitcoinj.core.TransactionConfidence$ConfidenceType):void");
    }

    public boolean markBroadcastBy(PeerAddress peerAddress) {
        this.lastBroadcastedAt = C3447Utils.now();
        if (!this.broadcastBy.addIfAbsent(peerAddress)) {
            return false;
        }
        synchronized (this) {
            if (getConfidenceType() == ConfidenceType.UNKNOWN) {
                this.confidenceType = ConfidenceType.PENDING;
            }
        }
        return true;
    }

    public int numBroadcastPeers() {
        return this.broadcastBy.size();
    }

    public Set<PeerAddress> getBroadcastBy() {
        return Sets.newHashSet((Iterator<? extends E>) this.broadcastBy.listIterator());
    }

    public boolean wasBroadcastBy(PeerAddress peerAddress) {
        return this.broadcastBy.contains(peerAddress);
    }

    public Date getLastBroadcastedAt() {
        return this.lastBroadcastedAt;
    }

    public void setLastBroadcastedAt(Date date) {
        this.lastBroadcastedAt = date;
    }

    public synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        int numBroadcastPeers = numBroadcastPeers();
        if (numBroadcastPeers > 0) {
            sb.append("Seen by ");
            sb.append(numBroadcastPeers);
            sb.append(numBroadcastPeers > 1 ? " peers" : " peer");
            if (this.lastBroadcastedAt != null) {
                sb.append(" (most recently: ");
                sb.append(C3447Utils.dateTimeFormat(this.lastBroadcastedAt));
                sb.append(")");
            }
            sb.append(". ");
        }
        int i = C34453.f802xc05c502d[getConfidenceType().ordinal()];
        if (i == 1) {
            sb.append("Unknown confidence level.");
        } else if (i == 2) {
            sb.append("Dead: overridden by double spend and will not confirm.");
        } else if (i == 3) {
            sb.append("Pending/unconfirmed.");
        } else if (i == 4) {
            sb.append("In conflict.");
        } else if (i == 5) {
            sb.append(String.format(Locale.US, "Appeared in best chain at height %d, depth %d.", new Object[]{Integer.valueOf(getAppearedAtChainHeight()), Integer.valueOf(getDepthInBlocks())}));
        }
        if (this.source != Source.UNKNOWN) {
            sb.append(" Source: ");
            sb.append(this.source);
        }
        return sb.toString();
    }

    public synchronized int incrementDepthInBlocks() {
        int i;
        i = this.depth + 1;
        this.depth = i;
        return i;
    }

    public synchronized int getDepthInBlocks() {
        return this.depth;
    }

    public synchronized void setDepthInBlocks(int i) {
        this.depth = i;
    }

    public void clearBroadcastBy() {
        Preconditions.checkState(getConfidenceType() != ConfidenceType.PENDING);
        this.broadcastBy.clear();
        this.lastBroadcastedAt = null;
    }

    public synchronized Transaction getOverridingTransaction() {
        if (getConfidenceType() == ConfidenceType.DEAD) {
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Confidence type is ");
            sb.append(getConfidenceType());
            sb.append(", not DEAD");
            throw new IllegalStateException(sb.toString());
        }
        return this.overridingTransaction;
    }

    public synchronized void setOverridingTransaction(@Nullable Transaction transaction) {
        this.overridingTransaction = transaction;
        setConfidenceType(ConfidenceType.DEAD);
    }

    public TransactionConfidence duplicate() {
        TransactionConfidence transactionConfidence = new TransactionConfidence(this.hash);
        transactionConfidence.broadcastBy.addAll(this.broadcastBy);
        transactionConfidence.lastBroadcastedAt = this.lastBroadcastedAt;
        synchronized (this) {
            transactionConfidence.confidenceType = this.confidenceType;
            transactionConfidence.overridingTransaction = this.overridingTransaction;
            transactionConfidence.appearedAtChainHeight = this.appearedAtChainHeight;
        }
        return transactionConfidence;
    }

    public void queueListeners(final ChangeReason changeReason) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            listenerRegistration.executor.execute(new Runnable() {
                public void run() {
                    ((Listener) listenerRegistration.listener).onConfidenceChanged(TransactionConfidence.this, changeReason);
                }
            });
        }
    }

    public synchronized Source getSource() {
        return this.source;
    }

    public synchronized void setSource(Source source2) {
        this.source = source2;
    }

    public synchronized ListenableFuture<TransactionConfidence> getDepthFuture(final int i, Executor executor) {
        final SettableFuture create;
        create = SettableFuture.create();
        if (getDepthInBlocks() >= i) {
            create.set(this);
        }
        addEventListener(executor, new Listener() {
            public void onConfidenceChanged(TransactionConfidence transactionConfidence, ChangeReason changeReason) {
                if (TransactionConfidence.this.getDepthInBlocks() >= i) {
                    TransactionConfidence.this.removeEventListener(this);
                    create.set(transactionConfidence);
                }
            }
        });
        return create;
    }

    public synchronized ListenableFuture<TransactionConfidence> getDepthFuture(int i) {
        return getDepthFuture(i, Threading.USER_THREAD);
    }

    public Sha256Hash getTransactionHash() {
        return this.hash;
    }
}
