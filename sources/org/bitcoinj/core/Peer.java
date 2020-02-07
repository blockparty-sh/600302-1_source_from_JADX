package org.bitcoinj.core;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import net.jcip.annotations.GuardedBy;
import org.bitcoinj.core.InventoryItem.C3406Type;
import org.bitcoinj.core.NetworkParameters.ProtocolVersion;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.listeners.AbstractPeerEventListener;
import org.bitcoinj.core.listeners.BlocksDownloadedEventListener;
import org.bitcoinj.core.listeners.ChainDownloadStartedEventListener;
import org.bitcoinj.core.listeners.GetDataEventListener;
import org.bitcoinj.core.listeners.OnTransactionBroadcastListener;
import org.bitcoinj.core.listeners.PeerConnectedEventListener;
import org.bitcoinj.core.listeners.PeerDisconnectedEventListener;
import org.bitcoinj.core.listeners.PreMessageReceivedEventListener;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.utils.ListenerRegistration;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.C3530Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Peer extends PeerSocketHandler {
    private static final int PING_MOVING_AVERAGE_WINDOW = 20;
    private static final int RESEND_BLOOM_FILTER_BLOCK_COUNT = 25000;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(Peer.class);
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    @Nullable
    public List<Sha256Hash> awaitingFreshFilter;
    private final AbstractBlockChain blockChain;
    private final AtomicInteger blocksAnnounced;
    private final CopyOnWriteArrayList<ListenerRegistration<BlocksDownloadedEventListener>> blocksDownloadedEventListeners;
    private final CopyOnWriteArrayList<ListenerRegistration<ChainDownloadStartedEventListener>> chainDownloadStartedEventListeners;
    private final CopyOnWriteArrayList<ListenerRegistration<PeerConnectedEventListener>> connectedEventListeners;
    private final SettableFuture<Peer> connectionOpenFuture;
    private final Context context;
    private FilteredBlock currentFilteredBlock;
    private final CopyOnWriteArrayList<ListenerRegistration<PeerDisconnectedEventListener>> disconnectedEventListeners;
    @GuardedBy("lock")
    private boolean downloadBlockBodies;
    @GuardedBy("lock")
    private long fastCatchupTimeSecs;
    private int filteredBlocksReceived;
    @GuardedBy("getAddrFutures")
    private final LinkedList<SettableFuture<AddressMessage>> getAddrFutures;
    private final CopyOnWriteArrayList<ListenerRegistration<GetDataEventListener>> getDataEventListeners;
    private final CopyOnWriteArrayList<GetDataRequest> getDataFutures;
    @GuardedBy("lock")
    @Nullable
    private LinkedList<SettableFuture<UTXOsMessage>> getutxoFutures;
    private final SettableFuture<Peer> incomingVersionHandshakeFuture;
    @GuardedBy("lock")
    private Sha256Hash lastGetBlocksBegin;
    @GuardedBy("lock")
    private Sha256Hash lastGetBlocksEnd;
    @GuardedBy("lastPingTimesLock")
    private long[] lastPingTimes;
    private final ReentrantLock lastPingTimesLock;
    protected final ReentrantLock lock;
    private final CopyOnWriteArrayList<ListenerRegistration<OnTransactionBroadcastListener>> onTransactionEventListeners;
    private final SettableFuture<Peer> outgoingVersionHandshakeFuture;
    /* access modifiers changed from: private */
    public final NetworkParameters params;
    private final HashSet<Sha256Hash> pendingBlockDownloads;
    private final CopyOnWriteArrayList<PendingPing> pendingPings;
    private final HashSet<TransactionConfidence> pendingTxDownloads;
    private final CopyOnWriteArrayList<ListenerRegistration<PreMessageReceivedEventListener>> preMessageReceivedEventListeners;
    @GuardedBy("lock")
    private boolean useFilteredBlocks;
    private volatile BloomFilter vBloomFilter;
    private volatile boolean vDownloadData;
    private volatile int vDownloadTxDependencyDepth;
    private volatile int vMinProtocolVersion;
    private volatile VersionMessage vPeerVersionMessage;
    private final ListenableFuture<Peer> versionHandshakeFuture;
    private final VersionMessage versionMessage;
    private final CopyOnWriteArrayList<C3530Wallet> wallets;

    /* renamed from: org.bitcoinj.core.Peer$12 */
    static /* synthetic */ class C341112 {
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$core$InventoryItem$Type = new int[C3406Type.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                org.bitcoinj.core.InventoryItem$Type[] r0 = org.bitcoinj.core.InventoryItem.C3406Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bitcoinj$core$InventoryItem$Type = r0
                int[] r0 = $SwitchMap$org$bitcoinj$core$InventoryItem$Type     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoinj.core.InventoryItem$Type r1 = org.bitcoinj.core.InventoryItem.C3406Type.Transaction     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$bitcoinj$core$InventoryItem$Type     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoinj.core.InventoryItem$Type r1 = org.bitcoinj.core.InventoryItem.C3406Type.Block     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.Peer.C341112.<clinit>():void");
        }
    }

    private static class GetDataRequest {
        final SettableFuture future;
        final Sha256Hash hash;

        public GetDataRequest(Sha256Hash sha256Hash, SettableFuture settableFuture) {
            this.hash = sha256Hash;
            this.future = settableFuture;
        }
    }

    private class PendingPing {
        public SettableFuture<Long> future = SettableFuture.create();
        public final long nonce;
        public final long startTimeMsec;

        public PendingPing(long j) {
            this.nonce = j;
            this.startTimeMsec = C3447Utils.currentTimeMillis();
        }

        public void complete() {
            if (!this.future.isDone()) {
                Long valueOf = Long.valueOf(C3447Utils.currentTimeMillis() - this.startTimeMsec);
                Peer.this.addPingTimeData(valueOf.longValue());
                Peer.log.debug("{}: ping time is {} msec", (Object) Peer.this.toString(), (Object) valueOf);
                this.future.set(valueOf);
            }
        }
    }

    public Peer(NetworkParameters networkParameters, VersionMessage versionMessage2, @Nullable AbstractBlockChain abstractBlockChain, PeerAddress peerAddress) {
        this(networkParameters, versionMessage2, peerAddress, abstractBlockChain);
    }

    public Peer(NetworkParameters networkParameters, VersionMessage versionMessage2, PeerAddress peerAddress, @Nullable AbstractBlockChain abstractBlockChain) {
        this(networkParameters, versionMessage2, peerAddress, abstractBlockChain, Integer.MAX_VALUE);
    }

    public Peer(NetworkParameters networkParameters, VersionMessage versionMessage2, PeerAddress peerAddress, @Nullable AbstractBlockChain abstractBlockChain, int i) {
        super(networkParameters, peerAddress);
        this.lock = Threading.lock("peer");
        this.blocksDownloadedEventListeners = new CopyOnWriteArrayList<>();
        this.chainDownloadStartedEventListeners = new CopyOnWriteArrayList<>();
        this.connectedEventListeners = new CopyOnWriteArrayList<>();
        this.disconnectedEventListeners = new CopyOnWriteArrayList<>();
        this.getDataEventListeners = new CopyOnWriteArrayList<>();
        this.preMessageReceivedEventListeners = new CopyOnWriteArrayList<>();
        this.onTransactionEventListeners = new CopyOnWriteArrayList<>();
        this.blocksAnnounced = new AtomicInteger();
        boolean z = true;
        this.downloadBlockBodies = true;
        this.useFilteredBlocks = false;
        this.currentFilteredBlock = null;
        this.pendingBlockDownloads = new HashSet<>();
        this.pendingTxDownloads = new HashSet<>();
        this.lastPingTimesLock = new ReentrantLock();
        this.lastPingTimes = null;
        this.connectionOpenFuture = SettableFuture.create();
        this.outgoingVersionHandshakeFuture = SettableFuture.create();
        this.incomingVersionHandshakeFuture = SettableFuture.create();
        this.versionHandshakeFuture = Futures.transform(Futures.allAsList((ListenableFuture<? extends V>[]) new ListenableFuture[]{this.outgoingVersionHandshakeFuture, this.incomingVersionHandshakeFuture}), new Function<List<Peer>, Peer>() {
            @Nullable
            public Peer apply(@Nullable List<Peer> list) {
                Preconditions.checkNotNull(list);
                boolean z = true;
                if (!(list.size() == 2 && list.get(0) == list.get(1))) {
                    z = false;
                }
                Preconditions.checkState(z);
                return (Peer) list.get(0);
            }
        });
        this.params = (NetworkParameters) Preconditions.checkNotNull(networkParameters);
        this.versionMessage = (VersionMessage) Preconditions.checkNotNull(versionMessage2);
        if (abstractBlockChain == null) {
            i = 0;
        }
        this.vDownloadTxDependencyDepth = i;
        this.blockChain = abstractBlockChain;
        if (abstractBlockChain == null) {
            z = false;
        }
        this.vDownloadData = z;
        this.getDataFutures = new CopyOnWriteArrayList<>();
        this.getAddrFutures = new LinkedList<>();
        this.fastCatchupTimeSecs = networkParameters.getGenesisBlock().getTimeSeconds();
        this.pendingPings = new CopyOnWriteArrayList<>();
        this.vMinProtocolVersion = networkParameters.getProtocolVersionNum(ProtocolVersion.PONG);
        this.wallets = new CopyOnWriteArrayList<>();
        this.context = Context.get();
        this.versionHandshakeFuture.addListener(new Runnable() {
            public void run() {
                Peer.this.versionHandshakeComplete();
            }
        }, Threading.SAME_THREAD);
    }

    public Peer(NetworkParameters networkParameters, AbstractBlockChain abstractBlockChain, PeerAddress peerAddress, String str, String str2) {
        this(networkParameters, new VersionMessage(networkParameters, abstractBlockChain.getBestChainHeight()), abstractBlockChain, peerAddress);
        this.versionMessage.appendToSubVer(str, str2, null);
    }

    @Deprecated
    public void addEventListener(AbstractPeerEventListener abstractPeerEventListener) {
        addBlocksDownloadedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addChainDownloadStartedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addConnectedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addDisconnectedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addGetDataEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addOnTransactionBroadcastListener(Threading.USER_THREAD, abstractPeerEventListener);
        addPreMessageReceivedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
    }

    @Deprecated
    public void addEventListener(AbstractPeerEventListener abstractPeerEventListener, Executor executor) {
        addBlocksDownloadedEventListener(executor, abstractPeerEventListener);
        addChainDownloadStartedEventListener(executor, abstractPeerEventListener);
        addConnectedEventListener(executor, abstractPeerEventListener);
        addDisconnectedEventListener(executor, abstractPeerEventListener);
        addGetDataEventListener(executor, abstractPeerEventListener);
        addOnTransactionBroadcastListener(executor, abstractPeerEventListener);
        addPreMessageReceivedEventListener(executor, abstractPeerEventListener);
    }

    @Deprecated
    public void removeEventListener(AbstractPeerEventListener abstractPeerEventListener) {
        removeBlocksDownloadedEventListener(abstractPeerEventListener);
        removeChainDownloadStartedEventListener(abstractPeerEventListener);
        removeConnectedEventListener(abstractPeerEventListener);
        removeDisconnectedEventListener(abstractPeerEventListener);
        removeGetDataEventListener(abstractPeerEventListener);
        removeOnTransactionBroadcastListener(abstractPeerEventListener);
        removePreMessageReceivedEventListener(abstractPeerEventListener);
    }

    public void addBlocksDownloadedEventListener(BlocksDownloadedEventListener blocksDownloadedEventListener) {
        addBlocksDownloadedEventListener(Threading.USER_THREAD, blocksDownloadedEventListener);
    }

    public void addBlocksDownloadedEventListener(Executor executor, BlocksDownloadedEventListener blocksDownloadedEventListener) {
        this.blocksDownloadedEventListeners.add(new ListenerRegistration(blocksDownloadedEventListener, executor));
    }

    public void addChainDownloadStartedEventListener(ChainDownloadStartedEventListener chainDownloadStartedEventListener) {
        addChainDownloadStartedEventListener(Threading.USER_THREAD, chainDownloadStartedEventListener);
    }

    public void addChainDownloadStartedEventListener(Executor executor, ChainDownloadStartedEventListener chainDownloadStartedEventListener) {
        this.chainDownloadStartedEventListeners.add(new ListenerRegistration(chainDownloadStartedEventListener, executor));
    }

    public void addConnectedEventListener(PeerConnectedEventListener peerConnectedEventListener) {
        addConnectedEventListener(Threading.USER_THREAD, peerConnectedEventListener);
    }

    public void addConnectedEventListener(Executor executor, PeerConnectedEventListener peerConnectedEventListener) {
        this.connectedEventListeners.add(new ListenerRegistration(peerConnectedEventListener, executor));
    }

    public void addDisconnectedEventListener(PeerDisconnectedEventListener peerDisconnectedEventListener) {
        addDisconnectedEventListener(Threading.USER_THREAD, peerDisconnectedEventListener);
    }

    public void addDisconnectedEventListener(Executor executor, PeerDisconnectedEventListener peerDisconnectedEventListener) {
        this.disconnectedEventListeners.add(new ListenerRegistration(peerDisconnectedEventListener, executor));
    }

    public void addGetDataEventListener(GetDataEventListener getDataEventListener) {
        addGetDataEventListener(Threading.USER_THREAD, getDataEventListener);
    }

    public void addGetDataEventListener(Executor executor, GetDataEventListener getDataEventListener) {
        this.getDataEventListeners.add(new ListenerRegistration(getDataEventListener, executor));
    }

    public void addOnTransactionBroadcastListener(OnTransactionBroadcastListener onTransactionBroadcastListener) {
        addOnTransactionBroadcastListener(Threading.USER_THREAD, onTransactionBroadcastListener);
    }

    public void addOnTransactionBroadcastListener(Executor executor, OnTransactionBroadcastListener onTransactionBroadcastListener) {
        this.onTransactionEventListeners.add(new ListenerRegistration(onTransactionBroadcastListener, executor));
    }

    public void addPreMessageReceivedEventListener(PreMessageReceivedEventListener preMessageReceivedEventListener) {
        addPreMessageReceivedEventListener(Threading.USER_THREAD, preMessageReceivedEventListener);
    }

    public void addPreMessageReceivedEventListener(Executor executor, PreMessageReceivedEventListener preMessageReceivedEventListener) {
        this.preMessageReceivedEventListeners.add(new ListenerRegistration(preMessageReceivedEventListener, executor));
    }

    public boolean removeBlocksDownloadedEventListener(BlocksDownloadedEventListener blocksDownloadedEventListener) {
        return ListenerRegistration.removeFromList(blocksDownloadedEventListener, this.blocksDownloadedEventListeners);
    }

    public boolean removeChainDownloadStartedEventListener(ChainDownloadStartedEventListener chainDownloadStartedEventListener) {
        return ListenerRegistration.removeFromList(chainDownloadStartedEventListener, this.chainDownloadStartedEventListeners);
    }

    public boolean removeConnectedEventListener(PeerConnectedEventListener peerConnectedEventListener) {
        return ListenerRegistration.removeFromList(peerConnectedEventListener, this.connectedEventListeners);
    }

    public boolean removeDisconnectedEventListener(PeerDisconnectedEventListener peerDisconnectedEventListener) {
        return ListenerRegistration.removeFromList(peerDisconnectedEventListener, this.disconnectedEventListeners);
    }

    public boolean removeGetDataEventListener(GetDataEventListener getDataEventListener) {
        return ListenerRegistration.removeFromList(getDataEventListener, this.getDataEventListeners);
    }

    public boolean removeOnTransactionBroadcastListener(OnTransactionBroadcastListener onTransactionBroadcastListener) {
        return ListenerRegistration.removeFromList(onTransactionBroadcastListener, this.onTransactionEventListeners);
    }

    public boolean removePreMessageReceivedEventListener(PreMessageReceivedEventListener preMessageReceivedEventListener) {
        return ListenerRegistration.removeFromList(preMessageReceivedEventListener, this.preMessageReceivedEventListeners);
    }

    public String toString() {
        PeerAddress address = getAddress();
        if (address == null) {
            return "Peer()";
        }
        return address.toString();
    }

    /* access modifiers changed from: protected */
    public void timeoutOccurred() {
        super.timeoutOccurred();
        if (!this.connectionOpenFuture.isDone()) {
            connectionClosed();
        }
    }

    public void connectionClosed() {
        Iterator it = this.disconnectedEventListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            listenerRegistration.executor.execute(new Runnable() {
                public void run() {
                    ((PeerDisconnectedEventListener) listenerRegistration.listener).onPeerDisconnected(Peer.this, 0);
                }
            });
        }
    }

    public void connectionOpened() {
        PeerAddress address = getAddress();
        log.info("Announcing to {} as: {}", address == null ? "Peer" : address.toSocketAddress(), (Object) this.versionMessage.subVer);
        sendMessage(this.versionMessage);
        this.connectionOpenFuture.set(this);
    }

    public ListenableFuture<Peer> getConnectionOpenFuture() {
        return this.connectionOpenFuture;
    }

    public ListenableFuture<Peer> getVersionHandshakeFuture() {
        return this.versionHandshakeFuture;
    }

    /* access modifiers changed from: protected */
    public void processMessage(Message message) throws Exception {
        Iterator it = this.preMessageReceivedEventListeners.iterator();
        while (it.hasNext()) {
            ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            if (listenerRegistration.executor == Threading.SAME_THREAD) {
                message = ((PreMessageReceivedEventListener) listenerRegistration.listener).onPreMessageReceived(this, message);
                if (message == null) {
                    break;
                }
            }
        }
        if (message != null) {
            FilteredBlock filteredBlock = this.currentFilteredBlock;
            if (filteredBlock != null && !(message instanceof Transaction)) {
                endFilteredBlock(filteredBlock);
                this.currentFilteredBlock = null;
            }
            boolean z = message instanceof VersionMessage;
            if (z || (message instanceof VersionAck) || (this.versionHandshakeFuture.isDone() && !this.versionHandshakeFuture.isCancelled())) {
                if (message instanceof Ping) {
                    processPing((Ping) message);
                } else if (message instanceof Pong) {
                    processPong((Pong) message);
                } else if (message instanceof NotFoundMessage) {
                    processNotFoundMessage((NotFoundMessage) message);
                } else if (message instanceof InventoryMessage) {
                    processInv((InventoryMessage) message);
                } else if (message instanceof Block) {
                    processBlock((Block) message);
                } else if (message instanceof FilteredBlock) {
                    startFilteredBlock((FilteredBlock) message);
                } else if (message instanceof Transaction) {
                    processTransaction((Transaction) message);
                } else if (message instanceof GetDataMessage) {
                    processGetData((GetDataMessage) message);
                } else if (message instanceof AddressMessage) {
                    processAddressMessage((AddressMessage) message);
                } else if (message instanceof HeadersMessage) {
                    processHeaders((HeadersMessage) message);
                } else if (message instanceof AlertMessage) {
                    processAlert((AlertMessage) message);
                } else if (z) {
                    processVersionMessage((VersionMessage) message);
                } else if (message instanceof VersionAck) {
                    processVersionAck((VersionAck) message);
                } else if (message instanceof UTXOsMessage) {
                    processUTXOMessage((UTXOsMessage) message);
                } else if (message instanceof RejectMessage) {
                    log.error("{} {}: Received {}", this, getPeerVersionMessage().subVer, message);
                } else {
                    log.warn("{}: Received unhandled message: {}", (Object) this, (Object) message);
                }
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Received ");
            sb.append(message.getClass().getSimpleName());
            sb.append(" before version handshake is complete.");
            throw new ProtocolException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void processUTXOMessage(UTXOsMessage uTXOsMessage) {
        this.lock.lock();
        try {
            SettableFuture settableFuture = this.getutxoFutures != null ? (SettableFuture) this.getutxoFutures.pollFirst() : null;
            if (settableFuture != null) {
                settableFuture.set(uTXOsMessage);
            }
        } finally {
            this.lock.unlock();
        }
    }

    private void processAddressMessage(AddressMessage addressMessage) {
        synchronized (this.getAddrFutures) {
            SettableFuture settableFuture = (SettableFuture) this.getAddrFutures.poll();
            if (settableFuture != null) {
                settableFuture.set(addressMessage);
            }
        }
    }

    private void processVersionMessage(VersionMessage versionMessage2) throws ProtocolException {
        if (this.vPeerVersionMessage == null) {
            this.vPeerVersionMessage = versionMessage2;
            long j = this.vPeerVersionMessage.time * 1000;
            log.info("{}: Got version={}, subVer='{}', services=0x{}, time={}, blocks={}", this, Integer.valueOf(this.vPeerVersionMessage.clientVersion), this.vPeerVersionMessage.subVer, Long.valueOf(this.vPeerVersionMessage.localServices), String.format(Locale.US, "%tF %tT", new Object[]{Long.valueOf(j), Long.valueOf(j)}), Long.valueOf(this.vPeerVersionMessage.bestHeight));
            if (!this.vPeerVersionMessage.hasBlockChain() || (!this.params.allowEmptyPeerChain() && this.vPeerVersionMessage.bestHeight == 0)) {
                log.info("{}: Peer does not have a copy of the block chain.", (Object) this);
                close();
            } else if ((this.vPeerVersionMessage.localServices & 32) != 32) {
                log.info("{}: Peer follows an incompatible block chain.", (Object) this);
                close();
            } else if (this.vPeerVersionMessage.bestHeight >= 0) {
                sendMessage(new VersionAck());
                log.debug("{}: Incoming version handshake complete.", (Object) this);
                this.incomingVersionHandshakeFuture.set(this);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Peer reports invalid best height: ");
                sb.append(this.vPeerVersionMessage.bestHeight);
                throw new ProtocolException(sb.toString());
            }
        } else {
            throw new ProtocolException("Got two version messages from peer");
        }
    }

    private void processVersionAck(VersionAck versionAck) throws ProtocolException {
        if (this.vPeerVersionMessage == null) {
            throw new ProtocolException("got a version ack before version");
        } else if (!this.outgoingVersionHandshakeFuture.isDone()) {
            log.debug("{}: Outgoing version handshake complete.", (Object) this);
            this.outgoingVersionHandshakeFuture.set(this);
        } else {
            throw new ProtocolException("got more than one version ack");
        }
    }

    /* access modifiers changed from: private */
    public void versionHandshakeComplete() {
        log.debug("{}: Handshake complete.", (Object) this);
        setTimeoutEnabled(false);
        Iterator it = this.connectedEventListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            listenerRegistration.executor.execute(new Runnable() {
                public void run() {
                    ((PeerConnectedEventListener) listenerRegistration.listener).onPeerConnected(Peer.this, 1);
                }
            });
        }
        int i = this.vMinProtocolVersion;
        if (this.vPeerVersionMessage.clientVersion < i) {
            log.warn("Connected to a peer speaking protocol version {} but need {}, closing", (Object) Integer.valueOf(this.vPeerVersionMessage.clientVersion), (Object) Integer.valueOf(i));
            close();
        }
    }

    /* access modifiers changed from: protected */
    public void startFilteredBlock(FilteredBlock filteredBlock) {
        this.currentFilteredBlock = filteredBlock;
        this.filteredBlocksReceived++;
        if (this.filteredBlocksReceived % RESEND_BLOOM_FILTER_BLOCK_COUNT == 24999) {
            sendMessage(this.vBloomFilter);
        }
    }

    /* access modifiers changed from: protected */
    public void processNotFoundMessage(NotFoundMessage notFoundMessage) {
        Iterator it = this.getDataFutures.iterator();
        while (it.hasNext()) {
            GetDataRequest getDataRequest = (GetDataRequest) it.next();
            Iterator it2 = notFoundMessage.getItems().iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (((InventoryItem) it2.next()).hash.equals(getDataRequest.hash)) {
                        log.info("{}: Bottomed out dep tree at {}", (Object) this, (Object) getDataRequest.hash);
                        getDataRequest.future.cancel(true);
                        this.getDataFutures.remove(getDataRequest);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processAlert(AlertMessage alertMessage) {
        try {
            if (alertMessage.isSignatureValid()) {
                log.debug("Received alert from peer {}: {}", (Object) this, (Object) alertMessage.getStatusBar());
            } else {
                log.debug("Received alert with invalid signature from peer {}: {}", (Object) this, (Object) alertMessage.getStatusBar());
            }
        } catch (Throwable th) {
            log.error("Failed to check signature: bug in platform libraries?", th);
        }
    }

    /* access modifiers changed from: protected */
    public void processHeaders(HeadersMessage headersMessage) throws ProtocolException {
        this.lock.lock();
        try {
            if (this.blockChain == null) {
                log.warn("Received headers when Peer is not configured with a chain.");
                return;
            }
            long j = this.fastCatchupTimeSecs;
            boolean z = this.downloadBlockBodies;
            this.lock.unlock();
            try {
                Preconditions.checkState(!z, toString());
                int i = 0;
                while (i < headersMessage.getBlockHeaders().size()) {
                    Block block = (Block) headersMessage.getBlockHeaders().get(i);
                    boolean z2 = block.getTimeSeconds() >= j;
                    boolean z3 = ((long) this.blockChain.getBestChainHeight()) >= this.vPeerVersionMessage.bestHeight;
                    if (z2 || z3) {
                        this.lock.lock();
                        log.info("Passed the fast catchup time ({}) at height {}, discarding {} headers and requesting full blocks", C3447Utils.dateTimeFormat(j * 1000), Integer.valueOf(this.blockChain.getBestChainHeight() + 1), Integer.valueOf(headersMessage.getBlockHeaders().size() - i));
                        this.downloadBlockBodies = true;
                        this.lastGetBlocksBegin = Sha256Hash.ZERO_HASH;
                        blockChainDownloadLocked(Sha256Hash.ZERO_HASH);
                        this.lock.unlock();
                        return;
                    } else if (!this.vDownloadData) {
                        log.info("Lost download peer status, throwing away downloaded headers.");
                        return;
                    } else if (this.blockChain.add(block)) {
                        invokeOnBlocksDownloaded(block, null);
                        i++;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Got unconnected header from peer: ");
                        sb.append(block.getHashAsString());
                        throw new ProtocolException(sb.toString());
                    }
                }
                if (headersMessage.getBlockHeaders().size() >= 2000) {
                    this.lock.lock();
                    blockChainDownloadLocked(Sha256Hash.ZERO_HASH);
                    this.lock.unlock();
                }
            } catch (VerificationException e) {
                log.warn("Block header verification failed", (Throwable) e);
            } catch (PrunedException e2) {
                throw new RuntimeException(e2);
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void processGetData(GetDataMessage getDataMessage) {
        log.info("{}: Received getdata message: {}", (Object) getAddress(), (Object) getDataMessage.toString());
        ArrayList arrayList = new ArrayList();
        Iterator it = this.getDataEventListeners.iterator();
        while (it.hasNext()) {
            ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            if (listenerRegistration.executor == Threading.SAME_THREAD) {
                List data = ((GetDataEventListener) listenerRegistration.listener).getData(this, getDataMessage);
                if (data != null) {
                    arrayList.addAll(data);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            log.info("{}: Sending {} items gathered from listeners to peer", (Object) getAddress(), (Object) Integer.valueOf(arrayList.size()));
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                sendMessage((Message) it2.next());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processTransaction(final Transaction transaction) throws VerificationException {
        transaction.verify();
        this.lock.lock();
        try {
            log.debug("{}: Received tx {}", (Object) getAddress(), (Object) transaction.getHashAsString());
            TransactionConfidence confidence = transaction.getConfidence();
            confidence.setSource(Source.NETWORK);
            this.pendingTxDownloads.remove(confidence);
            if (!maybeHandleRequestedData(transaction)) {
                if (this.currentFilteredBlock == null) {
                    Iterator it = this.wallets.iterator();
                    while (it.hasNext()) {
                        final C3530Wallet wallet = (C3530Wallet) it.next();
                        if (wallet.isPendingTransactionRelevant(transaction)) {
                            if (this.vDownloadTxDependencyDepth > 0) {
                                Futures.addCallback(downloadDependencies(transaction), new FutureCallback<List<Transaction>>() {
                                    public void onSuccess(List<Transaction> list) {
                                        try {
                                            Peer.log.info("{}: Dependency download complete!", (Object) Peer.this.getAddress());
                                            wallet.receivePending(transaction, list);
                                        } catch (VerificationException e) {
                                            Peer.log.error("{}: Wallet failed to process pending transaction {}", (Object) Peer.this.getAddress(), (Object) transaction.getHash());
                                            Peer.log.error("Error was: ", (Throwable) e);
                                        }
                                    }

                                    public void onFailure(Throwable th) {
                                        Peer.log.error("Could not download dependencies of tx {}", (Object) transaction.getHashAsString());
                                        Peer.log.error("Error was: ", th);
                                    }
                                });
                            } else {
                                wallet.receivePending(transaction, null);
                            }
                        }
                    }
                    this.lock.unlock();
                    Iterator it2 = this.onTransactionEventListeners.iterator();
                    while (it2.hasNext()) {
                        final ListenerRegistration listenerRegistration = (ListenerRegistration) it2.next();
                        listenerRegistration.executor.execute(new Runnable() {
                            public void run() {
                                ((OnTransactionBroadcastListener) listenerRegistration.listener).onTransaction(Peer.this, transaction);
                            }
                        });
                    }
                    return;
                } else if (!this.currentFilteredBlock.provideTransaction(transaction)) {
                    endFilteredBlock(this.currentFilteredBlock);
                    this.currentFilteredBlock = null;
                }
            }
            this.lock.unlock();
        } catch (VerificationException e) {
            log.error("Wallet failed to verify tx", (Throwable) e);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public ListenableFuture<List<Transaction>> downloadDependencies(Transaction transaction) {
        Preconditions.checkArgument(transaction.getConfidence().getConfidenceType() != ConfidenceType.BUILDING);
        log.info("{}: Downloading dependencies of {}", (Object) getAddress(), (Object) transaction.getHashAsString());
        final LinkedList linkedList = new LinkedList();
        ListenableFuture downloadDependenciesInternal = downloadDependenciesInternal(this.vDownloadTxDependencyDepth, 0, transaction, new Object(), linkedList);
        final SettableFuture create = SettableFuture.create();
        Futures.addCallback(downloadDependenciesInternal, new FutureCallback<Object>() {
            public void onSuccess(Object obj) {
                create.set(linkedList);
            }

            public void onFailure(Throwable th) {
                create.setException(th);
            }
        });
        return create;
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<Object> downloadDependenciesInternal(int i, int i2, Transaction transaction, Object obj, List<Transaction> list) {
        SettableFuture create = SettableFuture.create();
        final Sha256Hash hash = transaction.getHash();
        CopyOnWriteArraySet<Sha256Hash> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        for (TransactionInput outpoint : transaction.getInputs()) {
            copyOnWriteArraySet.add(outpoint.getOutpoint().getHash());
        }
        this.lock.lock();
        try {
            ArrayList newArrayList = Lists.newArrayList();
            GetDataMessage getDataMessage = new GetDataMessage(this.params);
            if (copyOnWriteArraySet.size() > 1) {
                log.info("{}: Requesting {} transactions for depth {} dep resolution", getAddress(), Integer.valueOf(copyOnWriteArraySet.size()), Integer.valueOf(i2 + 1));
            }
            for (Sha256Hash sha256Hash : copyOnWriteArraySet) {
                getDataMessage.addTransaction(sha256Hash);
                GetDataRequest getDataRequest = new GetDataRequest(sha256Hash, SettableFuture.create());
                newArrayList.add(getDataRequest.future);
                this.getDataFutures.add(getDataRequest);
            }
            ListenableFuture successfulAsList = Futures.successfulAsList((Iterable<? extends ListenableFuture<? extends V>>) newArrayList);
            final List<Transaction> list2 = list;
            final int i3 = i2;
            final int i4 = i;
            final Object obj2 = obj;
            C34188 r11 = r1;
            final SettableFuture settableFuture = create;
            C34188 r1 = new FutureCallback<List<Transaction>>() {
                public void onSuccess(List<Transaction> list) {
                    LinkedList newLinkedList = Lists.newLinkedList();
                    for (Transaction transaction : list) {
                        if (transaction != null) {
                            Peer.log.info("{}: Downloaded dependency of {}: {}", Peer.this.getAddress(), hash, transaction.getHashAsString());
                            list2.add(transaction);
                            int i = i3;
                            int i2 = i + 1;
                            int i3 = i4;
                            if (i2 < i3) {
                                newLinkedList.add(Peer.this.downloadDependenciesInternal(i3, i + 1, transaction, obj2, list2));
                            }
                        }
                    }
                    if (newLinkedList.size() == 0) {
                        settableFuture.set(obj2);
                    } else {
                        Futures.addCallback(Futures.successfulAsList((Iterable<? extends ListenableFuture<? extends V>>) newLinkedList), new FutureCallback<List<Object>>() {
                            public void onSuccess(List<Object> list) {
                                settableFuture.set(obj2);
                            }

                            public void onFailure(Throwable th) {
                                settableFuture.setException(th);
                            }
                        });
                    }
                }

                public void onFailure(Throwable th) {
                    settableFuture.setException(th);
                }
            };
            Futures.addCallback(successfulAsList, r11);
            sendMessage(getDataMessage);
            return create;
        } catch (Exception e) {
            log.error("{}: Couldn't send getdata in downloadDependencies({})", this, transaction.getHash(), e);
            create.setException(e);
            return create;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void processBlock(Block block) {
        if (log.isDebugEnabled()) {
            log.debug("{}: Received broadcast block {}", (Object) getAddress(), (Object) block.getHashAsString());
        }
        if (!maybeHandleRequestedData(block)) {
            if (this.blockChain == null) {
                log.debug("Received block but was not configured with an AbstractBlockChain");
            } else if (!this.vDownloadData) {
                log.debug("{}: Received block we did not ask for: {}", (Object) getAddress(), (Object) block.getHashAsString());
            } else {
                this.pendingBlockDownloads.remove(block.getHash());
                try {
                    if (this.blockChain.add(block)) {
                        invokeOnBlocksDownloaded(block, null);
                    } else {
                        this.lock.lock();
                        if (this.downloadBlockBodies) {
                            blockChainDownloadLocked(((Block) Preconditions.checkNotNull(this.blockChain.getOrphanRoot(block.getHash()))).getHash());
                        } else {
                            log.info("Did not start chain download on solved block due to in-flight header download.");
                        }
                        this.lock.unlock();
                    }
                } catch (VerificationException e) {
                    log.warn("{}: Block verification failed", (Object) getAddress(), (Object) e);
                } catch (PrunedException e2) {
                    throw new RuntimeException(e2);
                } catch (Throwable th) {
                    this.lock.unlock();
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void endFilteredBlock(FilteredBlock filteredBlock) {
        if (log.isDebugEnabled()) {
            log.debug("{}: Received broadcast filtered block {}", (Object) getAddress(), (Object) filteredBlock.getHash().toString());
        }
        if (!this.vDownloadData) {
            log.debug("{}: Received block we did not ask for: {}", (Object) getAddress(), (Object) filteredBlock.getHash().toString());
        } else if (this.blockChain == null) {
            log.debug("Received filtered block but was not configured with an AbstractBlockChain");
        } else {
            this.pendingBlockDownloads.remove(filteredBlock.getBlockHeader().getHash());
            try {
                this.lock.lock();
                if (this.awaitingFreshFilter != null) {
                    log.info("Discarding block {} because we're still waiting for a fresh filter", (Object) filteredBlock.getHash());
                    this.awaitingFreshFilter.add(filteredBlock.getHash());
                    this.lock.unlock();
                } else if (checkForFilterExhaustion(filteredBlock)) {
                    log.info("Bloom filter exhausted whilst processing block {}, discarding", (Object) filteredBlock.getHash());
                    this.awaitingFreshFilter = new LinkedList();
                    this.awaitingFreshFilter.add(filteredBlock.getHash());
                    this.awaitingFreshFilter.addAll(this.blockChain.drainOrphanBlocks());
                    this.lock.unlock();
                } else {
                    this.lock.unlock();
                    if (this.blockChain.add(filteredBlock)) {
                        invokeOnBlocksDownloaded(filteredBlock.getBlockHeader(), filteredBlock);
                    } else {
                        this.lock.lock();
                        blockChainDownloadLocked(((Block) Preconditions.checkNotNull(this.blockChain.getOrphanRoot(filteredBlock.getHash()))).getHash());
                        this.lock.unlock();
                    }
                }
            } catch (VerificationException e) {
                log.warn("{}: FilteredBlock verification failed", (Object) getAddress(), (Object) e);
            } catch (PrunedException e2) {
                throw new RuntimeException(e2);
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
    }

    private boolean checkForFilterExhaustion(FilteredBlock filteredBlock) {
        Iterator it = this.wallets.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= ((C3530Wallet) it.next()).checkForFilterExhaustion(filteredBlock);
        }
        return z;
    }

    private boolean maybeHandleRequestedData(Message message) {
        Sha256Hash hash = message.getHash();
        Iterator it = this.getDataFutures.iterator();
        boolean z = false;
        while (it.hasNext()) {
            GetDataRequest getDataRequest = (GetDataRequest) it.next();
            if (hash.equals(getDataRequest.hash)) {
                getDataRequest.future.set(message);
                this.getDataFutures.remove(getDataRequest);
                z = true;
            }
        }
        return z;
    }

    private void invokeOnBlocksDownloaded(Block block, @Nullable FilteredBlock filteredBlock) {
        int max = Math.max(0, ((int) this.vPeerVersionMessage.bestHeight) - ((AbstractBlockChain) Preconditions.checkNotNull(this.blockChain)).getBestChainHeight());
        Iterator it = this.blocksDownloadedEventListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            Executor executor = listenerRegistration.executor;
            final Block block2 = block;
            final FilteredBlock filteredBlock2 = filteredBlock;
            final int i = max;
            C34209 r2 = new Runnable() {
                public void run() {
                    ((BlocksDownloadedEventListener) listenerRegistration.listener).onBlocksDownloaded(Peer.this, block2, filteredBlock2, i);
                }
            };
            executor.execute(r2);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void processInv(InventoryMessage inventoryMessage) {
        List<InventoryItem> items = inventoryMessage.getItems();
        LinkedList linkedList = new LinkedList();
        LinkedList<InventoryItem> linkedList2 = new LinkedList<>();
        for (InventoryItem inventoryItem : items) {
            int i = C341112.$SwitchMap$org$bitcoinj$core$InventoryItem$Type[inventoryItem.type.ordinal()];
            if (i == 1) {
                linkedList.add(inventoryItem);
            } else if (i == 2) {
                linkedList2.add(inventoryItem);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Not implemented: ");
                sb.append(inventoryItem.type);
                throw new IllegalStateException(sb.toString());
            }
        }
        boolean z = this.vDownloadData;
        boolean z2 = false;
        if (linkedList.size() == 0 && linkedList2.size() == 1) {
            if (z) {
                AbstractBlockChain abstractBlockChain = this.blockChain;
                if (abstractBlockChain != null) {
                    if (!abstractBlockChain.isOrphan(((InventoryItem) linkedList2.get(0)).hash)) {
                        this.blocksAnnounced.incrementAndGet();
                    }
                }
            }
            this.blocksAnnounced.incrementAndGet();
        }
        GetDataMessage getDataMessage = new GetDataMessage(this.params);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            InventoryItem inventoryItem2 = (InventoryItem) it.next();
            TransactionConfidence seen = this.context.getConfidenceTable().seen(inventoryItem2.hash, getAddress());
            if (seen.numBroadcastPeers() > 1) {
                it.remove();
            } else if (seen.getSource().equals(Source.SELF)) {
                it.remove();
            } else {
                log.debug("{}: getdata on tx {}", (Object) getAddress(), (Object) inventoryItem2.hash);
                getDataMessage.addItem(inventoryItem2);
                this.pendingTxDownloads.add(seen);
            }
        }
        this.lock.lock();
        try {
            if (linkedList2.size() > 0 && z && this.blockChain != null) {
                for (InventoryItem inventoryItem3 : linkedList2) {
                    if (this.blockChain.isOrphan(inventoryItem3.hash) && this.downloadBlockBodies) {
                        blockChainDownloadLocked(((Block) Preconditions.checkNotNull(this.blockChain.getOrphanRoot(inventoryItem3.hash))).getHash());
                    } else if (!this.pendingBlockDownloads.contains(inventoryItem3.hash)) {
                        if (!this.vPeerVersionMessage.isBloomFilteringSupported() || !this.useFilteredBlocks) {
                            getDataMessage.addItem(inventoryItem3);
                        } else {
                            getDataMessage.addFilteredBlock(inventoryItem3.hash);
                            z2 = true;
                        }
                        this.pendingBlockDownloads.add(inventoryItem3.hash);
                    }
                }
            }
            this.lock.unlock();
            if (!getDataMessage.getItems().isEmpty()) {
                sendMessage(getDataMessage);
            }
            if (z2) {
                sendMessage(new Ping((long) (Math.random() * 9.223372036854776E18d)));
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public ListenableFuture<Block> getBlock(Sha256Hash sha256Hash) {
        log.info("Request to fetch block {}", (Object) sha256Hash);
        GetDataMessage getDataMessage = new GetDataMessage(this.params);
        getDataMessage.addBlock(sha256Hash);
        return sendSingleGetData(getDataMessage);
    }

    public ListenableFuture<Transaction> getPeerMempoolTransaction(Sha256Hash sha256Hash) {
        log.info("Request to fetch peer mempool tx  {}", (Object) sha256Hash);
        GetDataMessage getDataMessage = new GetDataMessage(this.params);
        getDataMessage.addTransaction(sha256Hash);
        return sendSingleGetData(getDataMessage);
    }

    private ListenableFuture sendSingleGetData(GetDataMessage getDataMessage) {
        boolean z = true;
        if (getDataMessage.getItems().size() != 1) {
            z = false;
        }
        Preconditions.checkArgument(z);
        GetDataRequest getDataRequest = new GetDataRequest(((InventoryItem) getDataMessage.getItems().get(0)).hash, SettableFuture.create());
        this.getDataFutures.add(getDataRequest);
        sendMessage(getDataMessage);
        return getDataRequest.future;
    }

    public ListenableFuture<AddressMessage> getAddr() {
        SettableFuture create = SettableFuture.create();
        synchronized (this.getAddrFutures) {
            this.getAddrFutures.add(create);
        }
        sendMessage(new GetAddrMessage(this.params));
        return create;
    }

    public void setDownloadParameters(long j, boolean z) {
        this.lock.lock();
        if (j == 0) {
            try {
                this.fastCatchupTimeSecs = this.params.getGenesisBlock().getTimeSeconds();
                this.downloadBlockBodies = true;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        } else {
            this.fastCatchupTimeSecs = j;
            if (this.blockChain != null && this.fastCatchupTimeSecs > this.blockChain.getChainHead().getHeader().getTimeSeconds()) {
                this.downloadBlockBodies = false;
            }
        }
        this.useFilteredBlocks = z;
        this.lock.unlock();
    }

    public void addWallet(C3530Wallet wallet) {
        this.wallets.add(wallet);
    }

    public void removeWallet(C3530Wallet wallet) {
        this.wallets.remove(wallet);
    }

    @GuardedBy("lock")
    private void blockChainDownloadLocked(Sha256Hash sha256Hash) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        ArrayList arrayList = new ArrayList(51);
        BlockStore blockStore = ((AbstractBlockChain) Preconditions.checkNotNull(this.blockChain)).getBlockStore();
        StoredBlock chainHead = this.blockChain.getChainHead();
        Sha256Hash hash = chainHead.getHeader().getHash();
        if (!Objects.equal(this.lastGetBlocksBegin, hash) || !Objects.equal(this.lastGetBlocksEnd, sha256Hash)) {
            if (log.isDebugEnabled()) {
                log.debug("{}: blockChainDownloadLocked({}) current head = {}", this, sha256Hash, chainHead.getHeader().getHashAsString());
            }
            int i = 100;
            while (chainHead != null && i > 0) {
                arrayList.add(chainHead.getHeader().getHash());
                try {
                    chainHead = chainHead.getPrev(blockStore);
                    i--;
                } catch (BlockStoreException e) {
                    log.error("Failed to walk the block chain whilst constructing a locator");
                    throw new RuntimeException(e);
                }
            }
            if (chainHead != null) {
                arrayList.add(this.params.getGenesisBlock().getHash());
            }
            this.lastGetBlocksBegin = hash;
            this.lastGetBlocksEnd = sha256Hash;
            if (this.downloadBlockBodies) {
                sendMessage(new GetBlocksMessage(this.params, arrayList, sha256Hash));
            } else {
                sendMessage(new GetHeadersMessage(this.params, arrayList, sha256Hash));
            }
            return;
        }
        log.info("blockChainDownloadLocked({}): ignoring duplicated request: {}", (Object) sha256Hash, (Object) hash);
        Iterator it = this.pendingBlockDownloads.iterator();
        while (it.hasNext()) {
            log.info("Pending block download: {}", (Object) (Sha256Hash) it.next());
        }
        log.info(Throwables.getStackTraceAsString(new Throwable()));
    }

    public void startBlockChainDownload() {
        setDownloadData(true);
        final int peerBlockHeightDifference = getPeerBlockHeightDifference();
        if (peerBlockHeightDifference >= 0) {
            Iterator it = this.chainDownloadStartedEventListeners.iterator();
            while (it.hasNext()) {
                final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
                listenerRegistration.executor.execute(new Runnable() {
                    public void run() {
                        ((ChainDownloadStartedEventListener) listenerRegistration.listener).onChainDownloadStarted(Peer.this, peerBlockHeightDifference);
                    }
                });
            }
            this.lock.lock();
            try {
                blockChainDownloadLocked(Sha256Hash.ZERO_HASH);
            } finally {
                this.lock.unlock();
            }
        }
    }

    /* access modifiers changed from: private */
    public void addPingTimeData(long j) {
        this.lastPingTimesLock.lock();
        try {
            if (this.lastPingTimes == null) {
                this.lastPingTimes = new long[20];
                Arrays.fill(this.lastPingTimes, j);
            } else {
                System.arraycopy(this.lastPingTimes, 1, this.lastPingTimes, 0, this.lastPingTimes.length - 1);
                this.lastPingTimes[this.lastPingTimes.length - 1] = j;
            }
        } finally {
            this.lastPingTimesLock.unlock();
        }
    }

    public ListenableFuture<Long> ping() throws ProtocolException {
        return ping((long) (Math.random() * 9.223372036854776E18d));
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<Long> ping(long j) throws ProtocolException {
        VersionMessage versionMessage2 = this.vPeerVersionMessage;
        if (versionMessage2.isPingPongSupported()) {
            PendingPing pendingPing = new PendingPing(j);
            this.pendingPings.add(pendingPing);
            sendMessage(new Ping(pendingPing.nonce));
            return pendingPing.future;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Peer version is too low for measurable pings: ");
        sb.append(versionMessage2);
        throw new ProtocolException(sb.toString());
    }

    public long getLastPingTime() {
        this.lastPingTimesLock.lock();
        try {
            if (this.lastPingTimes == null) {
                return Long.MAX_VALUE;
            }
            long j = this.lastPingTimes[this.lastPingTimes.length - 1];
            this.lastPingTimesLock.unlock();
            return j;
        } finally {
            this.lastPingTimesLock.unlock();
        }
    }

    public long getPingTime() {
        long length;
        this.lastPingTimesLock.lock();
        try {
            if (this.lastPingTimes == null) {
                length = Long.MAX_VALUE;
            } else {
                long j = 0;
                for (long j2 : this.lastPingTimes) {
                    j += j2;
                }
                length = (long) (((double) j) / ((double) this.lastPingTimes.length));
            }
            return length;
        } finally {
            this.lastPingTimesLock.unlock();
        }
    }

    private void processPing(Ping ping) {
        if (ping.hasNonce()) {
            sendMessage(new Pong(ping.getNonce()));
        }
    }

    /* access modifiers changed from: protected */
    public void processPong(Pong pong) {
        Iterator it = this.pendingPings.iterator();
        while (it.hasNext()) {
            PendingPing pendingPing = (PendingPing) it.next();
            if (pong.getNonce() == pendingPing.nonce) {
                this.pendingPings.remove(pendingPing);
                pendingPing.complete();
                return;
            }
        }
    }

    public int getPeerBlockHeightDifference() {
        Preconditions.checkNotNull(this.blockChain, "No block chain configured");
        int bestHeight = (int) getBestHeight();
        Preconditions.checkState(this.params.allowEmptyPeerChain() || bestHeight > 0, "Connected to peer with zero/negative chain height", Integer.valueOf(bestHeight));
        return bestHeight - this.blockChain.getBestChainHeight();
    }

    private boolean isNotFoundMessageSupported() {
        return this.vPeerVersionMessage.clientVersion >= NotFoundMessage.MIN_PROTOCOL_VERSION;
    }

    public boolean isDownloadData() {
        return this.vDownloadData;
    }

    public void setDownloadData(boolean z) {
        this.vDownloadData = z;
    }

    public VersionMessage getPeerVersionMessage() {
        return this.vPeerVersionMessage;
    }

    public VersionMessage getVersionMessage() {
        return this.versionMessage;
    }

    public long getBestHeight() {
        return this.vPeerVersionMessage.bestHeight + ((long) this.blocksAnnounced.get());
    }

    public boolean setMinProtocolVersion(int i) {
        this.vMinProtocolVersion = i;
        VersionMessage peerVersionMessage = getPeerVersionMessage();
        if (peerVersionMessage == null || peerVersionMessage.clientVersion >= i) {
            return false;
        }
        log.warn("{}: Disconnecting due to new min protocol version {}, got: {}", this, Integer.valueOf(i), Integer.valueOf(peerVersionMessage.clientVersion));
        close();
        return true;
    }

    public void setBloomFilter(BloomFilter bloomFilter) {
        setBloomFilter(bloomFilter, true);
    }

    public void setBloomFilter(BloomFilter bloomFilter, boolean z) {
        Preconditions.checkNotNull(bloomFilter, "Clearing filters is not currently supported");
        VersionMessage versionMessage2 = this.vPeerVersionMessage;
        if (versionMessage2 != null && versionMessage2.isBloomFilteringSupported()) {
            this.vBloomFilter = bloomFilter;
            log.debug("{}: Sending Bloom filter{}", (Object) this, (Object) z ? " and querying mempool" : "");
            sendMessage(bloomFilter);
            if (z) {
                sendMessage(new MemoryPoolMessage());
            }
            maybeRestartChainDownload();
        }
    }

    private void maybeRestartChainDownload() {
        this.lock.lock();
        try {
            if (this.awaitingFreshFilter != null) {
                if (!this.vDownloadData) {
                    log.warn("Lost download peer status whilst awaiting fresh filter.");
                } else {
                    ping().addListener(new Runnable() {
                        public void run() {
                            Peer.this.lock.lock();
                            Preconditions.checkNotNull(Peer.this.awaitingFreshFilter);
                            GetDataMessage getDataMessage = new GetDataMessage(Peer.this.params);
                            for (Sha256Hash addFilteredBlock : Peer.this.awaitingFreshFilter) {
                                getDataMessage.addFilteredBlock(addFilteredBlock);
                            }
                            Peer.this.awaitingFreshFilter = null;
                            Peer.this.lock.unlock();
                            Peer.log.info("Restarting chain download");
                            Peer.this.sendMessage(getDataMessage);
                            Peer.this.sendMessage(new Ping((long) (Math.random() * 9.223372036854776E18d)));
                        }
                    }, Threading.SAME_THREAD);
                    this.lock.unlock();
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public BloomFilter getBloomFilter() {
        return this.vBloomFilter;
    }

    public ListenableFuture<UTXOsMessage> getUTXOs(List<TransactionOutPoint> list) {
        return getUTXOs(list, true);
    }

    public ListenableFuture<UTXOsMessage> getUTXOs(List<TransactionOutPoint> list, boolean z) {
        this.lock.lock();
        try {
            VersionMessage peerVersionMessage = getPeerVersionMessage();
            if (peerVersionMessage.clientVersion < 70002) {
                throw new ProtocolException("Peer does not support getutxos protocol version");
            } else if ((peerVersionMessage.localServices & 3) == 3) {
                SettableFuture create = SettableFuture.create();
                if (this.getutxoFutures == null) {
                    this.getutxoFutures = new LinkedList<>();
                }
                this.getutxoFutures.add(create);
                sendMessage(new GetUTXOsMessage(this.params, list, z));
                return create;
            } else {
                throw new ProtocolException("Peer does not support getutxos protocol flag: find Bitcoin XT nodes.");
            }
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isDownloadTxDependencies() {
        return this.vDownloadTxDependencyDepth > 0;
    }

    public void setDownloadTxDependencies(boolean z) {
        this.vDownloadTxDependencyDepth = z ? Integer.MAX_VALUE : 0;
    }

    public void setDownloadTxDependencies(int i) {
        this.vDownloadTxDependencyDepth = i;
    }
}
