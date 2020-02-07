package org.bitcoinj.core;

import androidx.work.WorkRequest;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Runnables;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.Uninterruptibles;
import com.squareup.okhttp.OkHttpClient;
import com.subgraph.orchid.TorClient;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import net.jcip.annotations.GuardedBy;
import org.bitcoinj.core.NetworkParameters.ProtocolVersion;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.listeners.AbstractPeerEventListener;
import org.bitcoinj.core.listeners.BlocksDownloadedEventListener;
import org.bitcoinj.core.listeners.ChainDownloadStartedEventListener;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.core.listeners.GetDataEventListener;
import org.bitcoinj.core.listeners.OnTransactionBroadcastListener;
import org.bitcoinj.core.listeners.PeerConnectedEventListener;
import org.bitcoinj.core.listeners.PeerDataEventListener;
import org.bitcoinj.core.listeners.PeerDisconnectedEventListener;
import org.bitcoinj.core.listeners.PeerDiscoveredEventListener;
import org.bitcoinj.core.listeners.PreMessageReceivedEventListener;
import org.bitcoinj.crypto.DRMWorkaround;
import org.bitcoinj.net.BlockingClientManager;
import org.bitcoinj.net.ClientConnectionManager;
import org.bitcoinj.net.FilterMerger;
import org.bitcoinj.net.NioClientManager;
import org.bitcoinj.net.discovery.HttpDiscovery;
import org.bitcoinj.net.discovery.HttpDiscovery.Details;
import org.bitcoinj.net.discovery.MultiplexingDiscovery;
import org.bitcoinj.net.discovery.PeerDiscovery;
import org.bitcoinj.net.discovery.PeerDiscoveryException;
import org.bitcoinj.net.discovery.TorDiscovery;
import org.bitcoinj.script.Script;
import org.bitcoinj.utils.ContextPropagatingThreadFactory;
import org.bitcoinj.utils.ExponentialBackoff;
import org.bitcoinj.utils.ExponentialBackoff.Params;
import org.bitcoinj.utils.ListenerRegistration;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.listeners.KeyChainEventListener;
import org.bitcoinj.wallet.listeners.ScriptsChangeEventListener;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeerGroup implements TransactionBroadcaster {
    public static final double DEFAULT_BLOOM_FILTER_FP_RATE = 1.0E-5d;
    public static final int DEFAULT_CONNECTIONS = 12;
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 5000;
    private static final long DEFAULT_PEER_DISCOVERY_TIMEOUT_MILLIS = 5000;
    public static final long DEFAULT_PING_INTERVAL_MSEC = 2000;
    public static final double MAX_FP_RATE_INCREASE = 10.0d;
    private static final int TOR_TIMEOUT_SECONDS = 60;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(PeerGroup.class);
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public final Map<PeerAddress, ExponentialBackoff> backoffMap;
    /* access modifiers changed from: private */
    public final FilterMerger bloomFilterMerger;
    @Nullable
    protected final AbstractBlockChain chain;
    @Nullable
    private ChainDownloadSpeedCalculator chainDownloadSpeedCalculator;
    /* access modifiers changed from: private */
    public final ClientConnectionManager channels;
    @GuardedBy("lock")
    @Nullable
    private PeerDataEventListener downloadListener;
    @GuardedBy("lock")
    private Peer downloadPeer;
    @GuardedBy("lock")
    private int downloadTxDependencyDepth;
    protected final ListeningScheduledExecutorService executor;
    /* access modifiers changed from: private */
    public CountDownLatch executorStartupLatch;
    @GuardedBy("lock")
    private long fastCatchupTimeSecs;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public ExponentialBackoff groupBackoff;
    /* access modifiers changed from: private */
    public final Map<FilterRecalculateMode, SettableFuture<BloomFilter>> inFlightRecalculations;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public final PriorityQueue<PeerAddress> inactives;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public boolean ipv6Unreachable;
    private LocalhostCheckState localhostCheckState;
    protected final ReentrantLock lock;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public int maxConnections;
    private int minBroadcastConnections;
    protected final NetworkParameters params;
    private final Params peerBackoffParams;
    protected final CopyOnWriteArrayList<ListenerRegistration<PeerConnectedEventListener>> peerConnectedEventListeners;
    protected final CopyOnWriteArrayList<ListenerRegistration<PeerDisconnectedEventListener>> peerDisconnectedEventListeners;
    protected final CopyOnWriteArrayList<ListenerRegistration<PeerDiscoveredEventListener>> peerDiscoveredEventListeners;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet<PeerDiscovery> peerDiscoverers;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<PeerFilterProvider> peerFilterProviders;
    private final CopyOnWriteArrayList<ListenerRegistration<GetDataEventListener>> peerGetDataEventListeners;
    private final PeerListener peerListener;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<Peer> peers;
    private final CopyOnWriteArrayList<ListenerRegistration<BlocksDownloadedEventListener>> peersBlocksDownloadedEventListeners;
    private final CopyOnWriteArrayList<ListenerRegistration<ChainDownloadStartedEventListener>> peersChainDownloadStartedEventListeners;
    private final CopyOnWriteArrayList<ListenerRegistration<PreMessageReceivedEventListener>> peersPreMessageReceivedEventListeners;
    protected final CopyOnWriteArrayList<ListenerRegistration<OnTransactionBroadcastListener>> peersTransactionBroadastEventListeners;
    private final CopyOnWriteArrayList<Peer> pendingPeers;
    @GuardedBy("lock")
    private long pingIntervalMsec;
    private long requiredServices;
    /* access modifiers changed from: private */
    public final Set<TransactionBroadcast> runningBroadcasts;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public int stallMinSpeedBytesSec;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public int stallPeriodSeconds;
    private final PeerStartupListener startupListener;
    /* access modifiers changed from: private */
    @Nullable
    public final TorClient torClient;
    private Runnable triggerConnectionsJob;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public boolean useLocalhostPeerWhenPossible;
    /* access modifiers changed from: private */
    public volatile boolean vBloomFilteringEnabled;
    /* access modifiers changed from: private */
    public volatile int vConnectTimeoutMillis;
    private volatile int vMaxPeersToDiscoverCount;
    private volatile int vMinRequiredProtocolVersion;
    private volatile long vPeerDiscoveryTimeoutMillis;
    /* access modifiers changed from: private */
    @Nullable
    public volatile ListenableScheduledFuture<?> vPingTask;
    /* access modifiers changed from: private */
    public volatile boolean vRunning;
    private volatile boolean vUsedUp;
    @GuardedBy("lock")
    private VersionMessage versionMessage;
    private final WalletCoinsReceivedEventListener walletCoinsReceivedEventListener;
    private final KeyChainEventListener walletKeyEventListener;
    private final ScriptsChangeEventListener walletScriptEventListener;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<C3530Wallet> wallets;

    /* renamed from: org.bitcoinj.core.PeerGroup$17 */
    static /* synthetic */ class C342917 {
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$core$PeerGroup$FilterRecalculateMode = new int[FilterRecalculateMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                org.bitcoinj.core.PeerGroup$FilterRecalculateMode[] r0 = org.bitcoinj.core.PeerGroup.FilterRecalculateMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bitcoinj$core$PeerGroup$FilterRecalculateMode = r0
                int[] r0 = $SwitchMap$org$bitcoinj$core$PeerGroup$FilterRecalculateMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoinj.core.PeerGroup$FilterRecalculateMode r1 = org.bitcoinj.core.PeerGroup.FilterRecalculateMode.SEND_IF_CHANGED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$bitcoinj$core$PeerGroup$FilterRecalculateMode     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoinj.core.PeerGroup$FilterRecalculateMode r1 = org.bitcoinj.core.PeerGroup.FilterRecalculateMode.DONT_SEND     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$bitcoinj$core$PeerGroup$FilterRecalculateMode     // Catch:{ NoSuchFieldError -> 0x002a }
                org.bitcoinj.core.PeerGroup$FilterRecalculateMode r1 = org.bitcoinj.core.PeerGroup.FilterRecalculateMode.FORCE_SEND_FOR_REFRESH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.PeerGroup.C342917.<clinit>():void");
        }
    }

    private class ChainDownloadSpeedCalculator implements BlocksDownloadedEventListener, Runnable {
        private int blocksInLastSecond;
        private long bytesInLastSecond;
        private int cursor;
        private int maxStalls;
        private int origTxnsInLastSecond;
        private long[] samples;
        private boolean syncDone;
        private int txnsInLastSecond;
        private int warmupSeconds;

        private ChainDownloadSpeedCalculator() {
            this.maxStalls = 3;
            this.warmupSeconds = -1;
        }

        public synchronized void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int i) {
            this.blocksInLastSecond++;
            this.bytesInLastSecond += 80;
            List transactions = block.getTransactions();
            int i2 = 0;
            int countAndMeasureSize = transactions != null ? countAndMeasureSize(transactions) : 0;
            if (filteredBlock != null) {
                i2 = countAndMeasureSize(filteredBlock.getAssociatedTransactions().values());
            }
            this.txnsInLastSecond += countAndMeasureSize + i2;
            if (filteredBlock != null) {
                this.origTxnsInLastSecond += filteredBlock.getTransactionCount();
            }
        }

        private int countAndMeasureSize(Collection<Transaction> collection) {
            for (Transaction messageSize : collection) {
                this.bytesInLastSecond += (long) messageSize.getMessageSize();
            }
            return collection.size();
        }

        public void run() {
            try {
                calculate();
            } catch (Throwable th) {
                PeerGroup.log.error("Error in speed calculator", th);
            }
        }

        private void calculate() {
            PeerGroup.this.lock.lock();
            try {
                int access$2500 = PeerGroup.this.stallMinSpeedBytesSec;
                int access$2600 = PeerGroup.this.stallPeriodSeconds;
                synchronized (this) {
                    if (this.samples == null || this.samples.length != access$2600) {
                        this.samples = new long[access$2600];
                        Arrays.fill(this.samples, (long) (access$2500 * 2));
                        this.warmupSeconds = 15;
                    }
                    if (!(PeerGroup.this.chain != null && PeerGroup.this.chain.getBestChainHeight() < PeerGroup.this.getMostCommonChainHeight())) {
                        this.syncDone = true;
                    }
                    if (!this.syncDone) {
                        if (this.warmupSeconds < 0) {
                            long[] jArr = this.samples;
                            int i = this.cursor;
                            this.cursor = i + 1;
                            jArr[i] = this.bytesInLastSecond;
                            if (this.cursor == this.samples.length) {
                                this.cursor = 0;
                            }
                            long j = 0;
                            for (long j2 : this.samples) {
                                j += j2;
                            }
                            long length = j / ((long) this.samples.length);
                            double d = ((double) length) / 1024.0d;
                            long j3 = length;
                            double d2 = ((double) access$2500) / 1024.0d;
                            PeerGroup.log.info(String.format(Locale.US, "%d blocks/sec, %d tx/sec, %d pre-filtered tx/sec, avg/last %.2f/%.2f kilobytes per sec (stall threshold <%.2f KB/sec for %d seconds)", new Object[]{Integer.valueOf(this.blocksInLastSecond), Integer.valueOf(this.txnsInLastSecond), Integer.valueOf(this.origTxnsInLastSecond), Double.valueOf(d), Double.valueOf(((double) this.bytesInLastSecond) / 1024.0d), Double.valueOf(d2), Integer.valueOf(this.samples.length)}));
                            if (j3 < ((long) access$2500) && this.maxStalls > 0) {
                                this.maxStalls--;
                                if (this.maxStalls == 0) {
                                    PeerGroup.log.warn("This network seems to be slower than the requested stall threshold - won't do stall disconnects any more.");
                                } else {
                                    Peer downloadPeer = PeerGroup.this.getDownloadPeer();
                                    PeerGroup.log.warn(String.format(Locale.US, "Chain download stalled: received %.2f KB/sec for %d seconds, require average of %.2f KB/sec, disconnecting %s", new Object[]{Double.valueOf(d), Integer.valueOf(this.samples.length), Double.valueOf(d2), downloadPeer}));
                                    downloadPeer.close();
                                    this.samples = null;
                                    this.warmupSeconds = access$2600;
                                }
                            }
                        } else {
                            this.warmupSeconds--;
                            if (this.bytesInLastSecond > 0) {
                                PeerGroup.log.info(String.format(Locale.US, "%d blocks/sec, %d tx/sec, %d pre-filtered tx/sec, last %.2f kilobytes per sec", new Object[]{Integer.valueOf(this.blocksInLastSecond), Integer.valueOf(this.txnsInLastSecond), Integer.valueOf(this.origTxnsInLastSecond), Double.valueOf(((double) this.bytesInLastSecond) / 1024.0d)}));
                            }
                        }
                    }
                    this.blocksInLastSecond = 0;
                    this.txnsInLastSecond = 0;
                    this.origTxnsInLastSecond = 0;
                    this.bytesInLastSecond = 0;
                }
            } finally {
                PeerGroup.this.lock.unlock();
            }
        }
    }

    public enum FilterRecalculateMode {
        SEND_IF_CHANGED,
        FORCE_SEND_FOR_REFRESH,
        DONT_SEND
    }

    private enum LocalhostCheckState {
        NOT_TRIED,
        FOUND,
        FOUND_AND_CONNECTED,
        NOT_THERE
    }

    private class PeerListener implements GetDataEventListener, BlocksDownloadedEventListener {
        public PeerListener() {
        }

        public List<Message> getData(Peer peer, GetDataMessage getDataMessage) {
            return PeerGroup.this.handleGetData(getDataMessage);
        }

        public void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int i) {
            if (PeerGroup.this.chain != null) {
                double falsePositiveRate = PeerGroup.this.chain.getFalsePositiveRate();
                double bloomFilterFPRate = PeerGroup.this.bloomFilterMerger.getBloomFilterFPRate() * 10.0d;
                if (falsePositiveRate > bloomFilterFPRate) {
                    if (PeerGroup.log.isDebugEnabled()) {
                        PeerGroup.log.debug("Force update Bloom filter due to high false positive rate ({} vs {})", (Object) Double.valueOf(falsePositiveRate), (Object) Double.valueOf(bloomFilterFPRate));
                    }
                    PeerGroup.this.recalculateFastCatchupAndFilter(FilterRecalculateMode.FORCE_SEND_FOR_REFRESH);
                }
            }
        }
    }

    private class PeerStartupListener implements PeerConnectedEventListener, PeerDisconnectedEventListener {
        private PeerStartupListener() {
        }

        public void onPeerConnected(Peer peer, int i) {
            PeerGroup.this.handleNewPeer(peer);
        }

        public void onPeerDisconnected(Peer peer, int i) {
            PeerGroup.this.handlePeerDeath(peer, null);
        }
    }

    public PeerGroup(NetworkParameters networkParameters) {
        this(networkParameters, (AbstractBlockChain) null);
    }

    public PeerGroup(Context context) {
        this(context, (AbstractBlockChain) null);
    }

    public PeerGroup(NetworkParameters networkParameters, @Nullable AbstractBlockChain abstractBlockChain) {
        this(Context.getOrCreate(networkParameters), abstractBlockChain, (ClientConnectionManager) new NioClientManager());
    }

    public PeerGroup(Context context, @Nullable AbstractBlockChain abstractBlockChain) {
        this(context, abstractBlockChain, (ClientConnectionManager) new NioClientManager());
    }

    public static PeerGroup newWithTor(NetworkParameters networkParameters, @Nullable AbstractBlockChain abstractBlockChain, TorClient torClient2) throws TimeoutException {
        return newWithTor(Context.getOrCreate(networkParameters), abstractBlockChain, torClient2);
    }

    public static PeerGroup newWithTor(Context context, @Nullable AbstractBlockChain abstractBlockChain, TorClient torClient2) throws TimeoutException {
        return newWithTor(context, abstractBlockChain, torClient2, true);
    }

    public static PeerGroup newWithTor(Context context, @Nullable AbstractBlockChain abstractBlockChain, TorClient torClient2, boolean z) throws TimeoutException {
        Preconditions.checkNotNull(torClient2);
        DRMWorkaround.maybeDisableExportControls();
        BlockingClientManager blockingClientManager = new BlockingClientManager(torClient2.getSocketFactory());
        blockingClientManager.setConnectTimeoutMillis(60000);
        PeerGroup peerGroup = new PeerGroup(context, abstractBlockChain, blockingClientManager, torClient2);
        peerGroup.setConnectTimeoutMillis(60000);
        if (z) {
            NetworkParameters params2 = context.getParams();
            Details[] httpSeeds = params2.getHttpSeeds();
            if (httpSeeds.length > 0) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setSocketFactory(torClient2.getSocketFactory());
                ArrayList newArrayList = Lists.newArrayList();
                for (Details httpDiscovery : httpSeeds) {
                    newArrayList.add(new HttpDiscovery(params2, httpDiscovery, okHttpClient));
                }
                peerGroup.addPeerDiscovery(new MultiplexingDiscovery(params2, newArrayList));
            } else {
                peerGroup.addPeerDiscovery(new TorDiscovery(params2, torClient2));
            }
        }
        return peerGroup;
    }

    public PeerGroup(NetworkParameters networkParameters, @Nullable AbstractBlockChain abstractBlockChain, ClientConnectionManager clientConnectionManager) {
        this(Context.getOrCreate(networkParameters), abstractBlockChain, clientConnectionManager, null);
    }

    public PeerGroup(Context context, @Nullable AbstractBlockChain abstractBlockChain, ClientConnectionManager clientConnectionManager) {
        this(context, abstractBlockChain, clientConnectionManager, null);
    }

    private PeerGroup(Context context, @Nullable AbstractBlockChain abstractBlockChain, ClientConnectionManager clientConnectionManager, @Nullable TorClient torClient2) {
        this.requiredServices = 0;
        this.vMaxPeersToDiscoverCount = 100;
        this.vPeerDiscoveryTimeoutMillis = DEFAULT_PEER_DISCOVERY_TIMEOUT_MILLIS;
        this.lock = Threading.lock("peergroup");
        this.peersBlocksDownloadedEventListeners = new CopyOnWriteArrayList<>();
        this.peersChainDownloadStartedEventListeners = new CopyOnWriteArrayList<>();
        this.peerConnectedEventListeners = new CopyOnWriteArrayList<>();
        this.peerDiscoveredEventListeners = new CopyOnWriteArrayList<>();
        this.peerDisconnectedEventListeners = new CopyOnWriteArrayList<>();
        this.peerGetDataEventListeners = new CopyOnWriteArrayList<>();
        this.peersPreMessageReceivedEventListeners = new CopyOnWriteArrayList<>();
        this.peersTransactionBroadastEventListeners = new CopyOnWriteArrayList<>();
        this.pingIntervalMsec = DEFAULT_PING_INTERVAL_MSEC;
        this.useLocalhostPeerWhenPossible = true;
        int i = 0;
        this.ipv6Unreachable = false;
        this.peerListener = new PeerListener();
        this.minBroadcastConnections = 0;
        this.walletScriptEventListener = new ScriptsChangeEventListener() {
            public void onScriptsChanged(C3530Wallet wallet, List<Script> list, boolean z) {
                PeerGroup.this.recalculateFastCatchupAndFilter(FilterRecalculateMode.SEND_IF_CHANGED);
            }
        };
        this.walletKeyEventListener = new KeyChainEventListener() {
            public void onKeysAdded(List<ECKey> list) {
                PeerGroup.this.recalculateFastCatchupAndFilter(FilterRecalculateMode.SEND_IF_CHANGED);
            }
        };
        this.walletCoinsReceivedEventListener = new WalletCoinsReceivedEventListener() {
            public void onCoinsReceived(C3530Wallet wallet, Transaction transaction, Coin coin, Coin coin2) {
                for (TransactionOutput transactionOutput : transaction.getOutputs()) {
                    if (transactionOutput.getScriptPubKey().isSentToRawPubKey() && transactionOutput.isMine(wallet)) {
                        if (transaction.getConfidence().getConfidenceType() == ConfidenceType.BUILDING) {
                            PeerGroup.this.recalculateFastCatchupAndFilter(FilterRecalculateMode.SEND_IF_CHANGED);
                            return;
                        } else {
                            PeerGroup.this.recalculateFastCatchupAndFilter(FilterRecalculateMode.DONT_SEND);
                            return;
                        }
                    }
                }
            }
        };
        Params params2 = new Params(1000, 1.5f, 600000);
        this.peerBackoffParams = params2;
        Params params3 = new Params(1000, 1.5f, WorkRequest.MIN_BACKOFF_MILLIS);
        this.groupBackoff = new ExponentialBackoff(params3);
        this.startupListener = new PeerStartupListener();
        this.vConnectTimeoutMillis = 5000;
        this.vBloomFilteringEnabled = true;
        this.executorStartupLatch = new CountDownLatch(1);
        this.triggerConnectionsJob = new Runnable() {
            private static final long MIN_PEER_DISCOVERY_INTERVAL = 1000;
            private boolean firstRun = true;

            public void run() {
                try {
                    mo45704go();
                } catch (Throwable th) {
                    PeerGroup.log.error("Exception when trying to build connections", th);
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:28:0x008c, code lost:
                if (r8.this$0.discoverPeers() > 0) goto L_0x009a;
             */
            /* renamed from: go */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void mo45704go() {
                /*
                    r8 = this;
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this
                    boolean r0 = r0.vRunning
                    if (r0 != 0) goto L_0x0009
                    return
                L_0x0009:
                    long r0 = org.bitcoinj.core.C3447Utils.currentTimeMillis()
                    org.bitcoinj.core.PeerGroup r2 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r2 = r2.lock
                    r2.lock()
                    r2 = 0
                    boolean r3 = org.bitcoinj.core.C3447Utils.isAndroidRuntime()     // Catch:{ all -> 0x01cc }
                    if (r3 != 0) goto L_0x004c
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01cc }
                    boolean r3 = r3.useLocalhostPeerWhenPossible     // Catch:{ all -> 0x01cc }
                    if (r3 == 0) goto L_0x004c
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01cc }
                    boolean r3 = r3.maybeCheckForLocalhostPeer()     // Catch:{ all -> 0x01cc }
                    if (r3 == 0) goto L_0x004c
                    boolean r3 = r8.firstRun     // Catch:{ all -> 0x01cc }
                    if (r3 == 0) goto L_0x004c
                    org.slf4j.Logger r0 = org.bitcoinj.core.PeerGroup.log     // Catch:{ all -> 0x01cc }
                    java.lang.String r1 = "Localhost peer detected, trying to use it instead of P2P discovery"
                    r0.info(r1)     // Catch:{ all -> 0x01cc }
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01cc }
                    r0.maxConnections = r2     // Catch:{ all -> 0x01cc }
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01cc }
                    r0.connectToLocalHost()     // Catch:{ all -> 0x01cc }
                    r8.firstRun = r2
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r0 = r0.lock
                    r0.unlock()
                    return
                L_0x004c:
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01cc }
                    java.util.PriorityQueue r3 = r3.inactives     // Catch:{ all -> 0x01cc }
                    boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x01cc }
                    r4 = 1
                    if (r3 != 0) goto L_0x0079
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01cc }
                    java.util.Map r3 = r3.backoffMap     // Catch:{ all -> 0x01cc }
                    org.bitcoinj.core.PeerGroup r5 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01cc }
                    java.util.PriorityQueue r5 = r5.inactives     // Catch:{ all -> 0x01cc }
                    java.lang.Object r5 = r5.peek()     // Catch:{ all -> 0x01cc }
                    java.lang.Object r3 = r3.get(r5)     // Catch:{ all -> 0x01cc }
                    org.bitcoinj.utils.ExponentialBackoff r3 = (org.bitcoinj.utils.ExponentialBackoff) r3     // Catch:{ all -> 0x01cc }
                    long r5 = r3.getRetryTime()     // Catch:{ all -> 0x01cc }
                    int r3 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                    if (r3 > 0) goto L_0x0079
                    r3 = 1
                    goto L_0x007a
                L_0x0079:
                    r3 = 0
                L_0x007a:
                    r3 = r3 ^ r4
                    r8.firstRun = r2
                    org.bitcoinj.core.PeerGroup r5 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r5 = r5.lock
                    r5.unlock()
                    if (r3 == 0) goto L_0x0099
                    org.bitcoinj.core.PeerGroup r5 = org.bitcoinj.core.PeerGroup.this     // Catch:{ PeerDiscoveryException -> 0x008f }
                    int r5 = r5.discoverPeers()     // Catch:{ PeerDiscoveryException -> 0x008f }
                    if (r5 <= 0) goto L_0x0099
                    goto L_0x009a
                L_0x008f:
                    r4 = move-exception
                    org.slf4j.Logger r5 = org.bitcoinj.core.PeerGroup.log
                    java.lang.String r6 = "Peer discovery failure"
                    r5.error(r6, r4)
                L_0x0099:
                    r4 = 0
                L_0x009a:
                    org.bitcoinj.core.PeerGroup r5 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r5 = r5.lock
                    r5.lock()
                    if (r3 == 0) goto L_0x00c6
                    if (r4 == 0) goto L_0x00bd
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    int r3 = r3.countConnectedAndPendingPeers()     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r4 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    int r4 = r4.getMaxConnections()     // Catch:{ all -> 0x01c3 }
                    if (r3 < r4) goto L_0x00bd
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.utils.ExponentialBackoff r3 = r3.groupBackoff     // Catch:{ all -> 0x01c3 }
                    r3.trackSuccess()     // Catch:{ all -> 0x01c3 }
                    goto L_0x00c6
                L_0x00bd:
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.utils.ExponentialBackoff r3 = r3.groupBackoff     // Catch:{ all -> 0x01c3 }
                    r3.trackFailure()     // Catch:{ all -> 0x01c3 }
                L_0x00c6:
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    java.util.PriorityQueue r3 = r3.inactives     // Catch:{ all -> 0x01c3 }
                    boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x01c3 }
                    if (r3 == 0) goto L_0x011f
                    org.bitcoinj.core.PeerGroup r2 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    int r2 = r2.countConnectedAndPendingPeers()     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    int r3 = r3.getMaxConnections()     // Catch:{ all -> 0x01c3 }
                    if (r2 >= r3) goto L_0x0117
                    org.bitcoinj.core.PeerGroup r2 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.utils.ExponentialBackoff r2 = r2.groupBackoff     // Catch:{ all -> 0x01c3 }
                    long r2 = r2.getRetryTime()     // Catch:{ all -> 0x01c3 }
                    long r2 = r2 - r0
                    r0 = 1000(0x3e8, double:4.94E-321)
                    long r0 = java.lang.Math.max(r2, r0)     // Catch:{ all -> 0x01c3 }
                    org.slf4j.Logger r2 = org.bitcoinj.core.PeerGroup.log     // Catch:{ all -> 0x01c3 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c3 }
                    r3.<init>()     // Catch:{ all -> 0x01c3 }
                    java.lang.String r4 = "Peer discovery didn't provide us any more peers, will try again in "
                    r3.append(r4)     // Catch:{ all -> 0x01c3 }
                    r3.append(r0)     // Catch:{ all -> 0x01c3 }
                    java.lang.String r4 = "ms."
                    r3.append(r4)     // Catch:{ all -> 0x01c3 }
                    java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01c3 }
                    r2.info(r3)     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r2 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    com.google.common.util.concurrent.ListeningScheduledExecutorService r2 = r2.executor     // Catch:{ all -> 0x01c3 }
                    java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x01c3 }
                    r2.schedule(r8, r0, r3)     // Catch:{ all -> 0x01c3 }
                L_0x0117:
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r0 = r0.lock
                    r0.unlock()
                    return
                L_0x011f:
                    org.bitcoinj.core.PeerGroup r3 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    java.util.PriorityQueue r3 = r3.inactives     // Catch:{ all -> 0x01c3 }
                    java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerAddress r3 = (org.bitcoinj.core.PeerAddress) r3     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r4 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    boolean r4 = r4.ipv6Unreachable     // Catch:{ all -> 0x01c3 }
                    if (r4 == 0) goto L_0x013b
                    java.net.InetAddress r4 = r3.getAddr()     // Catch:{ all -> 0x01c3 }
                    boolean r4 = r4 instanceof java.net.Inet6Address     // Catch:{ all -> 0x01c3 }
                    if (r4 != 0) goto L_0x011f
                L_0x013b:
                    org.bitcoinj.core.PeerGroup r4 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    java.util.Map r4 = r4.backoffMap     // Catch:{ all -> 0x01c3 }
                    java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.utils.ExponentialBackoff r4 = (org.bitcoinj.utils.ExponentialBackoff) r4     // Catch:{ all -> 0x01c3 }
                    long r4 = r4.getRetryTime()     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r6 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.utils.ExponentialBackoff r6 = r6.groupBackoff     // Catch:{ all -> 0x01c3 }
                    long r6 = r6.getRetryTime()     // Catch:{ all -> 0x01c3 }
                    long r4 = java.lang.Math.max(r4, r6)     // Catch:{ all -> 0x01c3 }
                    int r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                    if (r6 <= 0) goto L_0x019b
                    long r4 = r4 - r0
                    org.slf4j.Logger r0 = org.bitcoinj.core.PeerGroup.log     // Catch:{ all -> 0x01c3 }
                    java.lang.String r1 = "Waiting {} msec before next connect attempt {}"
                    java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01c3 }
                    if (r3 != 0) goto L_0x016d
                    java.lang.String r6 = ""
                    goto L_0x017e
                L_0x016d:
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c3 }
                    r6.<init>()     // Catch:{ all -> 0x01c3 }
                    java.lang.String r7 = "to "
                    r6.append(r7)     // Catch:{ all -> 0x01c3 }
                    r6.append(r3)     // Catch:{ all -> 0x01c3 }
                    java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01c3 }
                L_0x017e:
                    r0.info(r1, r2, r6)     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    java.util.PriorityQueue r0 = r0.inactives     // Catch:{ all -> 0x01c3 }
                    r0.add(r3)     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    com.google.common.util.concurrent.ListeningScheduledExecutorService r0 = r0.executor     // Catch:{ all -> 0x01c3 }
                    java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x01c3 }
                    r0.schedule(r8, r4, r1)     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r0 = r0.lock
                    r0.unlock()
                    return
                L_0x019b:
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r1 = org.bitcoinj.core.PeerGroup.this     // Catch:{ all -> 0x01c3 }
                    int r1 = r1.vConnectTimeoutMillis     // Catch:{ all -> 0x01c3 }
                    r0.connectTo(r3, r2, r1)     // Catch:{ all -> 0x01c3 }
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r0 = r0.lock
                    r0.unlock()
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this
                    int r0 = r0.countConnectedAndPendingPeers()
                    org.bitcoinj.core.PeerGroup r1 = org.bitcoinj.core.PeerGroup.this
                    int r1 = r1.getMaxConnections()
                    if (r0 >= r1) goto L_0x01c2
                    org.bitcoinj.core.PeerGroup r0 = org.bitcoinj.core.PeerGroup.this
                    com.google.common.util.concurrent.ListeningScheduledExecutorService r0 = r0.executor
                    r0.execute(r8)
                L_0x01c2:
                    return
                L_0x01c3:
                    r0 = move-exception
                    org.bitcoinj.core.PeerGroup r1 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r1 = r1.lock
                    r1.unlock()
                    throw r0
                L_0x01cc:
                    r0 = move-exception
                    r8.firstRun = r2
                    org.bitcoinj.core.PeerGroup r1 = org.bitcoinj.core.PeerGroup.this
                    java.util.concurrent.locks.ReentrantLock r1 = r1.lock
                    r1.unlock()
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.PeerGroup.C34346.mo45704go():void");
            }
        };
        this.localhostCheckState = LocalhostCheckState.NOT_TRIED;
        this.inFlightRecalculations = Maps.newHashMap();
        this.stallPeriodSeconds = 10;
        this.stallMinSpeedBytesSec = 1600;
        Preconditions.checkNotNull(context);
        this.params = context.getParams();
        this.chain = abstractBlockChain;
        this.fastCatchupTimeSecs = this.params.getGenesisBlock().getTimeSeconds();
        this.wallets = new CopyOnWriteArrayList<>();
        this.peerFilterProviders = new CopyOnWriteArrayList<>();
        this.torClient = torClient2;
        this.executor = createPrivateExecutor();
        this.maxConnections = 0;
        if (abstractBlockChain != null) {
            i = abstractBlockChain.getBestChainHeight();
        }
        this.versionMessage = new VersionMessage(this.params, i);
        this.versionMessage.relayTxesBeforeFilter = true;
        this.downloadTxDependencyDepth = Integer.MAX_VALUE;
        this.inactives = new PriorityQueue<>(1, new Comparator<PeerAddress>() {
            public int compare(PeerAddress peerAddress, PeerAddress peerAddress2) {
                Preconditions.checkState(PeerGroup.this.lock.isHeldByCurrentThread());
                int compareTo = ((ExponentialBackoff) PeerGroup.this.backoffMap.get(peerAddress)).compareTo((ExponentialBackoff) PeerGroup.this.backoffMap.get(peerAddress2));
                return compareTo == 0 ? Ints.compare(peerAddress.getPort(), peerAddress2.getPort()) : compareTo;
            }
        });
        this.backoffMap = new HashMap();
        this.peers = new CopyOnWriteArrayList<>();
        this.pendingPeers = new CopyOnWriteArrayList<>();
        this.channels = clientConnectionManager;
        this.peerDiscoverers = new CopyOnWriteArraySet<>();
        this.runningBroadcasts = Collections.synchronizedSet(new HashSet());
        this.bloomFilterMerger = new FilterMerger(1.0E-5d);
        this.vMinRequiredProtocolVersion = this.params.getProtocolVersionNum(ProtocolVersion.BLOOM_FILTER);
    }

    /* access modifiers changed from: protected */
    public ListeningScheduledExecutorService createPrivateExecutor() {
        ListeningScheduledExecutorService listeningDecorator = MoreExecutors.listeningDecorator((ScheduledExecutorService) new ScheduledThreadPoolExecutor(1, new ContextPropagatingThreadFactory("PeerGroup Thread")));
        listeningDecorator.execute(new Runnable() {
            public void run() {
                Uninterruptibles.awaitUninterruptibly(PeerGroup.this.executorStartupLatch);
            }
        });
        return listeningDecorator;
    }

    public void setPeerDiscoveryTimeoutMillis(long j) {
        this.vPeerDiscoveryTimeoutMillis = j;
    }

    public void setMaxConnections(int i) {
        this.lock.lock();
        try {
            this.maxConnections = i;
            if (isRunning()) {
                this.lock.unlock();
                int connectedClientCount = i - this.channels.getConnectedClientCount();
                if (connectedClientCount > 0) {
                    triggerConnections();
                }
                if (connectedClientCount < 0) {
                    this.channels.closeConnections(-connectedClientCount);
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void setDownloadTxDependencies(int i) {
        this.lock.lock();
        try {
            this.downloadTxDependencyDepth = i;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: private */
    public void triggerConnections() {
        if (!this.executor.isShutdown()) {
            this.executor.execute(this.triggerConnectionsJob);
        }
    }

    public int getMaxConnections() {
        this.lock.lock();
        try {
            return this.maxConnections;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: private */
    public List<Message> handleGetData(GetDataMessage getDataMessage) {
        this.lock.lock();
        try {
            LinkedList linkedList = new LinkedList();
            Iterator it = new LinkedList(getDataMessage.getItems()).iterator();
            while (it.hasNext()) {
                InventoryItem inventoryItem = (InventoryItem) it.next();
                Iterator it2 = this.wallets.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Transaction transaction = ((C3530Wallet) it2.next()).getTransaction(inventoryItem.hash);
                    if (transaction != null) {
                        linkedList.add(transaction);
                        it.remove();
                        break;
                    }
                }
            }
            return linkedList;
        } finally {
            this.lock.unlock();
        }
    }

    public void setVersionMessage(VersionMessage versionMessage2) {
        this.lock.lock();
        try {
            this.versionMessage = versionMessage2;
        } finally {
            this.lock.unlock();
        }
    }

    public VersionMessage getVersionMessage() {
        this.lock.lock();
        try {
            return this.versionMessage;
        } finally {
            this.lock.unlock();
        }
    }

    public void setUserAgent(String str, String str2, @Nullable String str3) {
        AbstractBlockChain abstractBlockChain = this.chain;
        VersionMessage versionMessage2 = new VersionMessage(this.params, abstractBlockChain == null ? 0 : abstractBlockChain.getBestChainHeight());
        versionMessage2.relayTxesBeforeFilter = false;
        updateVersionMessageRelayTxesBeforeFilter(versionMessage2);
        versionMessage2.appendToSubVer(str, str2, str3);
        setVersionMessage(versionMessage2);
    }

    private void updateVersionMessageRelayTxesBeforeFilter(VersionMessage versionMessage2) {
        this.lock.lock();
        try {
            boolean z = true;
            if ((this.chain != null && !this.chain.shouldVerifyTransactions()) && this.peerFilterProviders.size() > 0 && this.vBloomFilteringEnabled) {
                z = false;
            }
            versionMessage2.relayTxesBeforeFilter = z;
        } finally {
            this.lock.unlock();
        }
    }

    public void setUserAgent(String str, String str2) {
        setUserAgent(str, str2, null);
    }

    @Deprecated
    public void addEventListener(AbstractPeerEventListener abstractPeerEventListener, Executor executor2) {
        addBlocksDownloadedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addChainDownloadStartedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addConnectedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addDisconnectedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addDiscoveredEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addGetDataEventListener(Threading.USER_THREAD, abstractPeerEventListener);
        addOnTransactionBroadcastListener(Threading.USER_THREAD, abstractPeerEventListener);
        addPreMessageReceivedEventListener(Threading.USER_THREAD, abstractPeerEventListener);
    }

    @Deprecated
    public void addEventListener(AbstractPeerEventListener abstractPeerEventListener) {
        addBlocksDownloadedEventListener(this.executor, abstractPeerEventListener);
        addChainDownloadStartedEventListener(this.executor, abstractPeerEventListener);
        addConnectedEventListener(this.executor, abstractPeerEventListener);
        addDisconnectedEventListener(this.executor, abstractPeerEventListener);
        addDiscoveredEventListener(this.executor, abstractPeerEventListener);
        addGetDataEventListener(this.executor, abstractPeerEventListener);
        addOnTransactionBroadcastListener(this.executor, abstractPeerEventListener);
        addPreMessageReceivedEventListener(this.executor, abstractPeerEventListener);
    }

    public void addBlocksDownloadedEventListener(BlocksDownloadedEventListener blocksDownloadedEventListener) {
        addBlocksDownloadedEventListener(Threading.USER_THREAD, blocksDownloadedEventListener);
    }

    public void addBlocksDownloadedEventListener(Executor executor2, BlocksDownloadedEventListener blocksDownloadedEventListener) {
        this.peersBlocksDownloadedEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(blocksDownloadedEventListener), executor2));
        for (Peer addBlocksDownloadedEventListener : getConnectedPeers()) {
            addBlocksDownloadedEventListener.addBlocksDownloadedEventListener(executor2, blocksDownloadedEventListener);
        }
        for (Peer addBlocksDownloadedEventListener2 : getPendingPeers()) {
            addBlocksDownloadedEventListener2.addBlocksDownloadedEventListener(executor2, blocksDownloadedEventListener);
        }
    }

    public void addChainDownloadStartedEventListener(ChainDownloadStartedEventListener chainDownloadStartedEventListener) {
        addChainDownloadStartedEventListener(Threading.USER_THREAD, chainDownloadStartedEventListener);
    }

    public void addChainDownloadStartedEventListener(Executor executor2, ChainDownloadStartedEventListener chainDownloadStartedEventListener) {
        this.peersChainDownloadStartedEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(chainDownloadStartedEventListener), executor2));
        for (Peer addChainDownloadStartedEventListener : getConnectedPeers()) {
            addChainDownloadStartedEventListener.addChainDownloadStartedEventListener(executor2, chainDownloadStartedEventListener);
        }
        for (Peer addChainDownloadStartedEventListener2 : getPendingPeers()) {
            addChainDownloadStartedEventListener2.addChainDownloadStartedEventListener(executor2, chainDownloadStartedEventListener);
        }
    }

    public void addConnectedEventListener(PeerConnectedEventListener peerConnectedEventListener) {
        addConnectedEventListener(Threading.USER_THREAD, peerConnectedEventListener);
    }

    public void addConnectedEventListener(Executor executor2, PeerConnectedEventListener peerConnectedEventListener) {
        this.peerConnectedEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(peerConnectedEventListener), executor2));
        for (Peer addConnectedEventListener : getConnectedPeers()) {
            addConnectedEventListener.addConnectedEventListener(executor2, peerConnectedEventListener);
        }
        for (Peer addConnectedEventListener2 : getPendingPeers()) {
            addConnectedEventListener2.addConnectedEventListener(executor2, peerConnectedEventListener);
        }
    }

    public void addDisconnectedEventListener(PeerDisconnectedEventListener peerDisconnectedEventListener) {
        addDisconnectedEventListener(Threading.USER_THREAD, peerDisconnectedEventListener);
    }

    public void addDisconnectedEventListener(Executor executor2, PeerDisconnectedEventListener peerDisconnectedEventListener) {
        this.peerDisconnectedEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(peerDisconnectedEventListener), executor2));
        for (Peer addDisconnectedEventListener : getConnectedPeers()) {
            addDisconnectedEventListener.addDisconnectedEventListener(executor2, peerDisconnectedEventListener);
        }
        for (Peer addDisconnectedEventListener2 : getPendingPeers()) {
            addDisconnectedEventListener2.addDisconnectedEventListener(executor2, peerDisconnectedEventListener);
        }
    }

    public void addDiscoveredEventListener(PeerDiscoveredEventListener peerDiscoveredEventListener) {
        addDiscoveredEventListener(Threading.USER_THREAD, peerDiscoveredEventListener);
    }

    public void addDiscoveredEventListener(Executor executor2, PeerDiscoveredEventListener peerDiscoveredEventListener) {
        this.peerDiscoveredEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(peerDiscoveredEventListener), executor2));
    }

    public void addGetDataEventListener(GetDataEventListener getDataEventListener) {
        addGetDataEventListener(Threading.USER_THREAD, getDataEventListener);
    }

    public void addGetDataEventListener(Executor executor2, GetDataEventListener getDataEventListener) {
        this.peerGetDataEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(getDataEventListener), executor2));
        for (Peer addGetDataEventListener : getConnectedPeers()) {
            addGetDataEventListener.addGetDataEventListener(executor2, getDataEventListener);
        }
        for (Peer addGetDataEventListener2 : getPendingPeers()) {
            addGetDataEventListener2.addGetDataEventListener(executor2, getDataEventListener);
        }
    }

    public void addOnTransactionBroadcastListener(OnTransactionBroadcastListener onTransactionBroadcastListener) {
        addOnTransactionBroadcastListener(Threading.USER_THREAD, onTransactionBroadcastListener);
    }

    public void addOnTransactionBroadcastListener(Executor executor2, OnTransactionBroadcastListener onTransactionBroadcastListener) {
        this.peersTransactionBroadastEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(onTransactionBroadcastListener), executor2));
        for (Peer addOnTransactionBroadcastListener : getConnectedPeers()) {
            addOnTransactionBroadcastListener.addOnTransactionBroadcastListener(executor2, onTransactionBroadcastListener);
        }
        for (Peer addOnTransactionBroadcastListener2 : getPendingPeers()) {
            addOnTransactionBroadcastListener2.addOnTransactionBroadcastListener(executor2, onTransactionBroadcastListener);
        }
    }

    public void addPreMessageReceivedEventListener(PreMessageReceivedEventListener preMessageReceivedEventListener) {
        addPreMessageReceivedEventListener(Threading.USER_THREAD, preMessageReceivedEventListener);
    }

    public void addPreMessageReceivedEventListener(Executor executor2, PreMessageReceivedEventListener preMessageReceivedEventListener) {
        this.peersPreMessageReceivedEventListeners.add(new ListenerRegistration(Preconditions.checkNotNull(preMessageReceivedEventListener), executor2));
        for (Peer addPreMessageReceivedEventListener : getConnectedPeers()) {
            addPreMessageReceivedEventListener.addPreMessageReceivedEventListener(executor2, preMessageReceivedEventListener);
        }
        for (Peer addPreMessageReceivedEventListener2 : getPendingPeers()) {
            addPreMessageReceivedEventListener2.addPreMessageReceivedEventListener(executor2, preMessageReceivedEventListener);
        }
    }

    @Deprecated
    public void removeEventListener(AbstractPeerEventListener abstractPeerEventListener) {
        removeBlocksDownloadedEventListener(abstractPeerEventListener);
        removeChainDownloadStartedEventListener(abstractPeerEventListener);
        removeConnectedEventListener(abstractPeerEventListener);
        removeDisconnectedEventListener(abstractPeerEventListener);
        removeDiscoveredEventListener(abstractPeerEventListener);
        removeGetDataEventListener(abstractPeerEventListener);
        removeOnTransactionBroadcastListener(abstractPeerEventListener);
        removePreMessageReceivedEventListener(abstractPeerEventListener);
    }

    public boolean removeBlocksDownloadedEventListener(BlocksDownloadedEventListener blocksDownloadedEventListener) {
        boolean removeFromList = ListenerRegistration.removeFromList(blocksDownloadedEventListener, this.peersBlocksDownloadedEventListeners);
        for (Peer removeBlocksDownloadedEventListener : getConnectedPeers()) {
            removeBlocksDownloadedEventListener.removeBlocksDownloadedEventListener(blocksDownloadedEventListener);
        }
        for (Peer removeBlocksDownloadedEventListener2 : getPendingPeers()) {
            removeBlocksDownloadedEventListener2.removeBlocksDownloadedEventListener(blocksDownloadedEventListener);
        }
        return removeFromList;
    }

    public boolean removeChainDownloadStartedEventListener(ChainDownloadStartedEventListener chainDownloadStartedEventListener) {
        boolean removeFromList = ListenerRegistration.removeFromList(chainDownloadStartedEventListener, this.peersChainDownloadStartedEventListeners);
        for (Peer removeChainDownloadStartedEventListener : getConnectedPeers()) {
            removeChainDownloadStartedEventListener.removeChainDownloadStartedEventListener(chainDownloadStartedEventListener);
        }
        for (Peer removeChainDownloadStartedEventListener2 : getPendingPeers()) {
            removeChainDownloadStartedEventListener2.removeChainDownloadStartedEventListener(chainDownloadStartedEventListener);
        }
        return removeFromList;
    }

    public boolean removeConnectedEventListener(PeerConnectedEventListener peerConnectedEventListener) {
        boolean removeFromList = ListenerRegistration.removeFromList(peerConnectedEventListener, this.peerConnectedEventListeners);
        for (Peer removeConnectedEventListener : getConnectedPeers()) {
            removeConnectedEventListener.removeConnectedEventListener(peerConnectedEventListener);
        }
        for (Peer removeConnectedEventListener2 : getPendingPeers()) {
            removeConnectedEventListener2.removeConnectedEventListener(peerConnectedEventListener);
        }
        return removeFromList;
    }

    public boolean removeDisconnectedEventListener(PeerDisconnectedEventListener peerDisconnectedEventListener) {
        boolean removeFromList = ListenerRegistration.removeFromList(peerDisconnectedEventListener, this.peerDisconnectedEventListeners);
        for (Peer removeDisconnectedEventListener : getConnectedPeers()) {
            removeDisconnectedEventListener.removeDisconnectedEventListener(peerDisconnectedEventListener);
        }
        for (Peer removeDisconnectedEventListener2 : getPendingPeers()) {
            removeDisconnectedEventListener2.removeDisconnectedEventListener(peerDisconnectedEventListener);
        }
        return removeFromList;
    }

    public boolean removeDiscoveredEventListener(PeerDiscoveredEventListener peerDiscoveredEventListener) {
        return ListenerRegistration.removeFromList(peerDiscoveredEventListener, this.peerDiscoveredEventListeners);
    }

    public boolean removeGetDataEventListener(GetDataEventListener getDataEventListener) {
        boolean removeFromList = ListenerRegistration.removeFromList(getDataEventListener, this.peerGetDataEventListeners);
        for (Peer removeGetDataEventListener : getConnectedPeers()) {
            removeGetDataEventListener.removeGetDataEventListener(getDataEventListener);
        }
        for (Peer removeGetDataEventListener2 : getPendingPeers()) {
            removeGetDataEventListener2.removeGetDataEventListener(getDataEventListener);
        }
        return removeFromList;
    }

    public boolean removeOnTransactionBroadcastListener(OnTransactionBroadcastListener onTransactionBroadcastListener) {
        boolean removeFromList = ListenerRegistration.removeFromList(onTransactionBroadcastListener, this.peersTransactionBroadastEventListeners);
        for (Peer removeOnTransactionBroadcastListener : getConnectedPeers()) {
            removeOnTransactionBroadcastListener.removeOnTransactionBroadcastListener(onTransactionBroadcastListener);
        }
        for (Peer removeOnTransactionBroadcastListener2 : getPendingPeers()) {
            removeOnTransactionBroadcastListener2.removeOnTransactionBroadcastListener(onTransactionBroadcastListener);
        }
        return removeFromList;
    }

    public boolean removePreMessageReceivedEventListener(PreMessageReceivedEventListener preMessageReceivedEventListener) {
        boolean removeFromList = ListenerRegistration.removeFromList(preMessageReceivedEventListener, this.peersPreMessageReceivedEventListeners);
        for (Peer removePreMessageReceivedEventListener : getConnectedPeers()) {
            removePreMessageReceivedEventListener.removePreMessageReceivedEventListener(preMessageReceivedEventListener);
        }
        for (Peer removePreMessageReceivedEventListener2 : getPendingPeers()) {
            removePreMessageReceivedEventListener2.removePreMessageReceivedEventListener(preMessageReceivedEventListener);
        }
        return removeFromList;
    }

    public List<Peer> getConnectedPeers() {
        this.lock.lock();
        try {
            return new ArrayList(this.peers);
        } finally {
            this.lock.unlock();
        }
    }

    public List<Peer> getPendingPeers() {
        this.lock.lock();
        try {
            return new ArrayList(this.pendingPeers);
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public void addAddress(PeerAddress peerAddress) {
        this.lock.lock();
        try {
            addInactive(peerAddress);
            int maxConnections2 = getMaxConnections() + 1;
            this.lock.unlock();
            setMaxConnections(maxConnections2);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    private void addInactive(PeerAddress peerAddress) {
        this.lock.lock();
        try {
            if (!this.backoffMap.containsKey(peerAddress)) {
                this.backoffMap.put(peerAddress, new ExponentialBackoff(this.peerBackoffParams));
                this.inactives.offer(peerAddress);
                this.lock.unlock();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void setRequiredServices(long j) {
        this.lock.lock();
        try {
            this.requiredServices = j;
            this.peerDiscoverers.clear();
            addPeerDiscovery(MultiplexingDiscovery.forServices(this.params, j));
        } finally {
            this.lock.unlock();
        }
    }

    public void addAddress(InetAddress inetAddress) {
        NetworkParameters networkParameters = this.params;
        addAddress(new PeerAddress(networkParameters, inetAddress, networkParameters.getPort()));
    }

    public void addPeerDiscovery(PeerDiscovery peerDiscovery) {
        this.lock.lock();
        try {
            if (getMaxConnections() == 0) {
                setMaxConnections(12);
            }
            this.peerDiscoverers.add(peerDiscovery);
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public int discoverPeers() throws PeerDiscoveryException {
        Preconditions.checkState(!this.lock.isHeldByCurrentThread());
        int i = this.vMaxPeersToDiscoverCount;
        long j = this.vPeerDiscoveryTimeoutMillis;
        Stopwatch createStarted = Stopwatch.createStarted();
        LinkedList<PeerAddress> newLinkedList = Lists.newLinkedList();
        Iterator it = this.peerDiscoverers.iterator();
        while (it.hasNext()) {
            for (InetSocketAddress peerAddress : ((PeerDiscovery) it.next()).getPeers(this.requiredServices, j, TimeUnit.MILLISECONDS)) {
                newLinkedList.add(new PeerAddress(this.params, peerAddress));
            }
            if (newLinkedList.size() >= i) {
                break;
            }
        }
        if (!newLinkedList.isEmpty()) {
            for (PeerAddress addInactive : newLinkedList) {
                addInactive(addInactive);
            }
            final ImmutableSet copyOf = ImmutableSet.copyOf((Collection<? extends E>) newLinkedList);
            Iterator it2 = this.peerDiscoveredEventListeners.iterator();
            while (it2.hasNext()) {
                final ListenerRegistration listenerRegistration = (ListenerRegistration) it2.next();
                listenerRegistration.executor.execute(new Runnable() {
                    public void run() {
                        ((PeerDiscoveredEventListener) listenerRegistration.listener).onPeersDiscovered(copyOf);
                    }
                });
            }
        }
        createStarted.stop();
        log.info("Peer discovery took {} and returned {} items", (Object) createStarted, (Object) Integer.valueOf(newLinkedList.size()));
        return newLinkedList.size();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void waitForJobQueue() {
        Futures.getUnchecked(this.executor.submit(Runnables.doNothing()));
    }

    /* access modifiers changed from: private */
    public int countConnectedAndPendingPeers() {
        this.lock.lock();
        try {
            return this.peers.size() + this.pendingPeers.size();
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:14|13|17|18|(2:20|21)|27|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        r4 = r1;
        r1 = r0;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003d */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[SYNTHETIC, Splitter:B:20:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0050 A[SYNTHETIC, Splitter:B:23:0x0050] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean maybeCheckForLocalhostPeer() {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            boolean r0 = r0.isHeldByCurrentThread()
            com.google.common.base.Preconditions.checkState(r0)
            org.bitcoinj.core.PeerGroup$LocalhostCheckState r0 = r5.localhostCheckState
            org.bitcoinj.core.PeerGroup$LocalhostCheckState r1 = org.bitcoinj.core.PeerGroup.LocalhostCheckState.NOT_TRIED
            if (r0 != r1) goto L_0x0054
            r0 = 0
            java.net.Socket r1 = new java.net.Socket     // Catch:{ IOException -> 0x003d }
            r1.<init>()     // Catch:{ IOException -> 0x003d }
            java.net.InetSocketAddress r0 = new java.net.InetSocketAddress     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            java.lang.String r2 = "127.0.0.1"
            java.net.InetAddress r2 = com.google.common.net.InetAddresses.forString(r2)     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            org.bitcoinj.core.NetworkParameters r3 = r5.params     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            int r3 = r3.getPort()     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            r0.<init>(r2, r3)     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            int r2 = r5.vConnectTimeoutMillis     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            r1.connect(r0, r2)     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            org.bitcoinj.core.PeerGroup$LocalhostCheckState r0 = org.bitcoinj.core.PeerGroup.LocalhostCheckState.FOUND     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            r5.localhostCheckState = r0     // Catch:{ IOException -> 0x0036, all -> 0x0034 }
            r0 = 1
            r1.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            return r0
        L_0x0034:
            r0 = move-exception
            goto L_0x004e
        L_0x0036:
            r0 = r1
            goto L_0x003d
        L_0x0038:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x004e
        L_0x003d:
            org.slf4j.Logger r1 = log     // Catch:{ all -> 0x0038 }
            java.lang.String r2 = "Localhost peer not detected."
            r1.info(r2)     // Catch:{ all -> 0x0038 }
            org.bitcoinj.core.PeerGroup$LocalhostCheckState r1 = org.bitcoinj.core.PeerGroup.LocalhostCheckState.NOT_THERE     // Catch:{ all -> 0x0038 }
            r5.localhostCheckState = r1     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0054
            r0.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0054
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            throw r0
        L_0x0054:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.PeerGroup.maybeCheckForLocalhostPeer():boolean");
    }

    public ListenableFuture startAsync() {
        if (this.chain == null) {
            log.warn("Starting up with no attached block chain. Did you forget to pass one to the constructor?");
        }
        Preconditions.checkState(!this.vUsedUp, "Cannot start a peer group twice");
        this.vRunning = true;
        this.vUsedUp = true;
        this.executorStartupLatch.countDown();
        return this.executor.submit((Runnable) new Runnable() {
            public void run() {
                try {
                    PeerGroup.log.info("Starting ...");
                    if (PeerGroup.this.torClient != null) {
                        PeerGroup.log.info("Starting Tor/Orchid ...");
                        PeerGroup.this.torClient.start();
                        PeerGroup.this.torClient.waitUntilReady(60000);
                        PeerGroup.log.info("Tor ready");
                    }
                    PeerGroup.this.channels.startAsync();
                    PeerGroup.this.channels.awaitRunning();
                    PeerGroup.this.triggerConnections();
                    PeerGroup.this.setupPinging();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    PeerGroup.log.error("Exception when starting up", th);
                }
            }
        });
    }

    public void start() {
        Futures.getUnchecked(startAsync());
    }

    @Deprecated
    public void awaitRunning() {
        waitForJobQueue();
    }

    public ListenableFuture stopAsync() {
        Preconditions.checkState(this.vRunning);
        this.vRunning = false;
        ListenableFuture submit = this.executor.submit((Runnable) new Runnable() {
            public void run() {
                try {
                    PeerGroup.log.info("Stopping ...");
                    PeerGroup.this.channels.stopAsync();
                    PeerGroup.this.channels.awaitTerminated();
                    Iterator it = PeerGroup.this.peerDiscoverers.iterator();
                    while (it.hasNext()) {
                        ((PeerDiscovery) it.next()).shutdown();
                    }
                    if (PeerGroup.this.torClient != null) {
                        PeerGroup.this.torClient.stop();
                    }
                    PeerGroup.this.vRunning = false;
                    PeerGroup.log.info("Stopped.");
                } catch (Throwable th) {
                    PeerGroup.log.error("Exception when shutting down", th);
                }
            }
        });
        this.executor.shutdown();
        return submit;
    }

    public void stop() {
        try {
            stopAsync();
            log.info("Awaiting PeerGroup shutdown ...");
            this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public void awaitTerminated() {
        try {
            this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWallet(C3530Wallet wallet) {
        this.lock.lock();
        try {
            Preconditions.checkNotNull(wallet);
            Preconditions.checkState(!this.wallets.contains(wallet));
            this.wallets.add(wallet);
            wallet.setTransactionBroadcaster(this);
            wallet.addCoinsReceivedEventListener(Threading.SAME_THREAD, this.walletCoinsReceivedEventListener);
            wallet.addKeyChainEventListener(Threading.SAME_THREAD, this.walletKeyEventListener);
            wallet.addScriptChangeEventListener(Threading.SAME_THREAD, this.walletScriptEventListener);
            addPeerFilterProvider(wallet);
            Iterator it = this.peers.iterator();
            while (it.hasNext()) {
                ((Peer) it.next()).addWallet(wallet);
            }
        } finally {
            this.lock.unlock();
        }
    }

    public ListenableFuture<BloomFilter> addPeerFilterProvider(PeerFilterProvider peerFilterProvider) {
        this.lock.lock();
        try {
            Preconditions.checkNotNull(peerFilterProvider);
            Preconditions.checkState(!this.peerFilterProviders.contains(peerFilterProvider));
            this.peerFilterProviders.add(0, peerFilterProvider);
            ListenableFuture<BloomFilter> recalculateFastCatchupAndFilter = recalculateFastCatchupAndFilter(FilterRecalculateMode.SEND_IF_CHANGED);
            updateVersionMessageRelayTxesBeforeFilter(getVersionMessage());
            return recalculateFastCatchupAndFilter;
        } finally {
            this.lock.unlock();
        }
    }

    public void removePeerFilterProvider(PeerFilterProvider peerFilterProvider) {
        this.lock.lock();
        try {
            Preconditions.checkNotNull(peerFilterProvider);
            Preconditions.checkArgument(this.peerFilterProviders.remove(peerFilterProvider));
        } finally {
            this.lock.unlock();
        }
    }

    public void removeWallet(C3530Wallet wallet) {
        this.wallets.remove(Preconditions.checkNotNull(wallet));
        this.peerFilterProviders.remove(wallet);
        wallet.removeCoinsReceivedEventListener(this.walletCoinsReceivedEventListener);
        wallet.removeKeyChainEventListener(this.walletKeyEventListener);
        wallet.removeScriptChangeEventListener(this.walletScriptEventListener);
        wallet.setTransactionBroadcaster(null);
        Iterator it = this.peers.iterator();
        while (it.hasNext()) {
            ((Peer) it.next()).removeWallet(wallet);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r3.executor.execute(new org.bitcoinj.core.PeerGroup.C342210(r3));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.util.concurrent.ListenableFuture<org.bitcoinj.core.BloomFilter> recalculateFastCatchupAndFilter(final org.bitcoinj.core.PeerGroup.FilterRecalculateMode r4) {
        /*
            r3 = this;
            com.google.common.util.concurrent.SettableFuture r0 = com.google.common.util.concurrent.SettableFuture.create()
            java.util.Map<org.bitcoinj.core.PeerGroup$FilterRecalculateMode, com.google.common.util.concurrent.SettableFuture<org.bitcoinj.core.BloomFilter>> r1 = r3.inFlightRecalculations
            monitor-enter(r1)
            java.util.Map<org.bitcoinj.core.PeerGroup$FilterRecalculateMode, com.google.common.util.concurrent.SettableFuture<org.bitcoinj.core.BloomFilter>> r2 = r3.inFlightRecalculations     // Catch:{ all -> 0x002a }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0019
            java.util.Map<org.bitcoinj.core.PeerGroup$FilterRecalculateMode, com.google.common.util.concurrent.SettableFuture<org.bitcoinj.core.BloomFilter>> r0 = r3.inFlightRecalculations     // Catch:{ all -> 0x002a }
            java.lang.Object r4 = r0.get(r4)     // Catch:{ all -> 0x002a }
            com.google.common.util.concurrent.ListenableFuture r4 = (com.google.common.util.concurrent.ListenableFuture) r4     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return r4
        L_0x0019:
            java.util.Map<org.bitcoinj.core.PeerGroup$FilterRecalculateMode, com.google.common.util.concurrent.SettableFuture<org.bitcoinj.core.BloomFilter>> r2 = r3.inFlightRecalculations     // Catch:{ all -> 0x002a }
            r2.put(r4, r0)     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            org.bitcoinj.core.PeerGroup$10 r1 = new org.bitcoinj.core.PeerGroup$10
            r1.<init>(r4, r0)
            com.google.common.util.concurrent.ListeningScheduledExecutorService r4 = r3.executor     // Catch:{ RejectedExecutionException -> 0x0029 }
            r4.execute(r1)     // Catch:{ RejectedExecutionException -> 0x0029 }
        L_0x0029:
            return r0
        L_0x002a:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.PeerGroup.recalculateFastCatchupAndFilter(org.bitcoinj.core.PeerGroup$FilterRecalculateMode):com.google.common.util.concurrent.ListenableFuture");
    }

    public void setBloomFilterFalsePositiveRate(double d) {
        this.lock.lock();
        try {
            this.bloomFilterMerger.setBloomFilterFPRate(d);
            recalculateFastCatchupAndFilter(FilterRecalculateMode.SEND_IF_CHANGED);
        } finally {
            this.lock.unlock();
        }
    }

    public int numConnectedPeers() {
        return this.peers.size();
    }

    @Nullable
    public Peer connectTo(InetSocketAddress inetSocketAddress) {
        this.lock.lock();
        try {
            PeerAddress peerAddress = new PeerAddress(this.params, inetSocketAddress);
            this.backoffMap.put(peerAddress, new ExponentialBackoff(this.peerBackoffParams));
            Peer connectTo = connectTo(peerAddress, true, this.vConnectTimeoutMillis);
            return connectTo;
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    public Peer connectToLocalHost() {
        this.lock.lock();
        try {
            PeerAddress localhost = PeerAddress.localhost(this.params);
            this.backoffMap.put(localhost, new ExponentialBackoff(this.peerBackoffParams));
            return connectTo(localhost, true, this.vConnectTimeoutMillis);
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    @GuardedBy("lock")
    @Nullable
    public Peer connectTo(PeerAddress peerAddress, boolean z, int i) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        VersionMessage duplicate = getVersionMessage().duplicate();
        AbstractBlockChain abstractBlockChain = this.chain;
        duplicate.bestHeight = abstractBlockChain == null ? 0 : (long) abstractBlockChain.getBestChainHeight();
        duplicate.time = C3447Utils.currentTimeSeconds();
        Peer createPeer = createPeer(peerAddress, duplicate);
        createPeer.addConnectedEventListener(Threading.SAME_THREAD, this.startupListener);
        createPeer.addDisconnectedEventListener(Threading.SAME_THREAD, this.startupListener);
        createPeer.setMinProtocolVersion(this.vMinRequiredProtocolVersion);
        this.pendingPeers.add(createPeer);
        try {
            log.info("Attempting connection to {}     ({} connected, {} pending, {} max)", peerAddress, Integer.valueOf(this.peers.size()), Integer.valueOf(this.pendingPeers.size()), Integer.valueOf(this.maxConnections));
            ListenableFuture openConnection = this.channels.openConnection(peerAddress.toSocketAddress(), createPeer);
            if (openConnection.isDone()) {
                Uninterruptibles.getUninterruptibly(openConnection);
            }
            createPeer.setSocketTimeout(i);
            if (z) {
                this.maxConnections++;
            }
            return createPeer;
        } catch (ExecutionException e) {
            Throwable rootCause = Throwables.getRootCause(e);
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to connect to ");
            sb.append(peerAddress);
            sb.append(": ");
            sb.append(rootCause.getMessage());
            logger.warn(sb.toString());
            handlePeerDeath(createPeer, rootCause);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @GuardedBy("lock")
    public Peer createPeer(PeerAddress peerAddress, VersionMessage versionMessage2) {
        Peer peer = new Peer(this.params, versionMessage2, peerAddress, this.chain, this.downloadTxDependencyDepth);
        return peer;
    }

    public void setConnectTimeoutMillis(int i) {
        this.vConnectTimeoutMillis = i;
    }

    public void startBlockChainDownload(PeerDataEventListener peerDataEventListener) {
        this.lock.lock();
        try {
            if (this.downloadPeer != null) {
                if (this.downloadListener != null) {
                    removeDataEventListenerFromPeer(this.downloadPeer, this.downloadListener);
                }
                if (peerDataEventListener != null) {
                    addDataEventListenerToPeer(Threading.USER_THREAD, this.downloadPeer, peerDataEventListener);
                }
            }
            this.downloadListener = peerDataEventListener;
            if (!this.peers.isEmpty()) {
                startBlockChainDownloadFromPeer((Peer) this.peers.iterator().next());
            }
        } finally {
            this.lock.unlock();
        }
    }

    private static void addDataEventListenerToPeer(Executor executor2, Peer peer, PeerDataEventListener peerDataEventListener) {
        peer.addBlocksDownloadedEventListener(executor2, peerDataEventListener);
        peer.addChainDownloadStartedEventListener(executor2, peerDataEventListener);
        peer.addGetDataEventListener(executor2, peerDataEventListener);
        peer.addPreMessageReceivedEventListener(executor2, peerDataEventListener);
    }

    private static void removeDataEventListenerFromPeer(Peer peer, PeerDataEventListener peerDataEventListener) {
        peer.removeBlocksDownloadedEventListener(peerDataEventListener);
        peer.removeChainDownloadStartedEventListener(peerDataEventListener);
        peer.removeGetDataEventListener(peerDataEventListener);
        peer.removePreMessageReceivedEventListener(peerDataEventListener);
    }

    public void downloadBlockChain() {
        DownloadProgressTracker downloadProgressTracker = new DownloadProgressTracker();
        startBlockChainDownload(downloadProgressTracker);
        try {
            downloadProgressTracker.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void handleNewPeer(final Peer peer) {
        this.lock.lock();
        try {
            this.groupBackoff.trackSuccess();
            ((ExponentialBackoff) this.backoffMap.get(peer.getAddress())).trackSuccess();
            this.pendingPeers.remove(peer);
            this.peers.add(peer);
            final int size = this.peers.size();
            boolean z = false;
            log.info("{}: New peer      ({} connected, {} pending, {} max)", peer, Integer.valueOf(size), Integer.valueOf(this.pendingPeers.size()), Integer.valueOf(this.maxConnections));
            if (this.bloomFilterMerger.getLastFilter() != null) {
                peer.setBloomFilter(this.bloomFilterMerger.getLastFilter());
            }
            peer.setDownloadData(false);
            Iterator it = this.wallets.iterator();
            while (it.hasNext()) {
                peer.addWallet((C3530Wallet) it.next());
            }
            if (this.downloadPeer == null) {
                setDownloadPeer(selectDownloadPeer(this.peers));
                if (!(this.downloadListener == null || this.chain == null)) {
                    z = true;
                }
                if (z) {
                    startBlockChainDownloadFromPeer(this.downloadPeer);
                }
            }
            peer.addBlocksDownloadedEventListener(Threading.SAME_THREAD, this.peerListener);
            peer.addGetDataEventListener(Threading.SAME_THREAD, this.peerListener);
            Iterator it2 = this.peersBlocksDownloadedEventListeners.iterator();
            while (it2.hasNext()) {
                ListenerRegistration listenerRegistration = (ListenerRegistration) it2.next();
                peer.addBlocksDownloadedEventListener(listenerRegistration.executor, (BlocksDownloadedEventListener) listenerRegistration.listener);
            }
            Iterator it3 = this.peersChainDownloadStartedEventListeners.iterator();
            while (it3.hasNext()) {
                ListenerRegistration listenerRegistration2 = (ListenerRegistration) it3.next();
                peer.addChainDownloadStartedEventListener(listenerRegistration2.executor, (ChainDownloadStartedEventListener) listenerRegistration2.listener);
            }
            Iterator it4 = this.peerConnectedEventListeners.iterator();
            while (it4.hasNext()) {
                ListenerRegistration listenerRegistration3 = (ListenerRegistration) it4.next();
                peer.addConnectedEventListener(listenerRegistration3.executor, (PeerConnectedEventListener) listenerRegistration3.listener);
            }
            Iterator it5 = this.peerGetDataEventListeners.iterator();
            while (it5.hasNext()) {
                ListenerRegistration listenerRegistration4 = (ListenerRegistration) it5.next();
                peer.addGetDataEventListener(listenerRegistration4.executor, (GetDataEventListener) listenerRegistration4.listener);
            }
            Iterator it6 = this.peersTransactionBroadastEventListeners.iterator();
            while (it6.hasNext()) {
                ListenerRegistration listenerRegistration5 = (ListenerRegistration) it6.next();
                peer.addOnTransactionBroadcastListener(listenerRegistration5.executor, (OnTransactionBroadcastListener) listenerRegistration5.listener);
            }
            Iterator it7 = this.peersPreMessageReceivedEventListeners.iterator();
            while (it7.hasNext()) {
                ListenerRegistration listenerRegistration6 = (ListenerRegistration) it7.next();
                peer.addPreMessageReceivedEventListener(listenerRegistration6.executor, (PreMessageReceivedEventListener) listenerRegistration6.listener);
            }
            this.lock.unlock();
            Iterator it8 = this.peerConnectedEventListeners.iterator();
            while (it8.hasNext()) {
                final ListenerRegistration listenerRegistration7 = (ListenerRegistration) it8.next();
                listenerRegistration7.executor.execute(new Runnable() {
                    public void run() {
                        ((PeerConnectedEventListener) listenerRegistration7.listener).onPeerConnected(peer, size);
                    }
                });
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void setupPinging() {
        if (getPingIntervalMsec() > 0) {
            this.vPingTask = this.executor.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        if (PeerGroup.this.getPingIntervalMsec() <= 0) {
                            ListenableScheduledFuture access$2400 = PeerGroup.this.vPingTask;
                            if (access$2400 != null) {
                                access$2400.cancel(false);
                                PeerGroup.this.vPingTask = null;
                            }
                            return;
                        }
                        for (Peer peer : PeerGroup.this.getConnectedPeers()) {
                            if (peer.getPeerVersionMessage().clientVersion >= PeerGroup.this.params.getProtocolVersionNum(ProtocolVersion.PONG)) {
                                peer.ping();
                            }
                        }
                    } catch (Throwable th) {
                        PeerGroup.log.error("Exception in ping loop", th);
                    }
                }
            }, getPingIntervalMsec(), getPingIntervalMsec(), TimeUnit.MILLISECONDS);
        }
    }

    private void setDownloadPeer(@Nullable Peer peer) {
        this.lock.lock();
        try {
            if (this.downloadPeer != peer) {
                if (this.downloadPeer != null) {
                    log.info("Unsetting download peer: {}", (Object) this.downloadPeer);
                    if (this.downloadListener != null) {
                        removeDataEventListenerFromPeer(this.downloadPeer, this.downloadListener);
                    }
                    this.downloadPeer.setDownloadData(false);
                }
                this.downloadPeer = peer;
                if (this.downloadPeer != null) {
                    log.info("Setting download peer: {}", (Object) this.downloadPeer);
                    if (this.downloadListener != null) {
                        addDataEventListenerToPeer(Threading.SAME_THREAD, peer, this.downloadListener);
                    }
                    boolean z = true;
                    this.downloadPeer.setDownloadData(true);
                    if (this.chain != null) {
                        Peer peer2 = this.downloadPeer;
                        long j = this.fastCatchupTimeSecs;
                        if (this.bloomFilterMerger.getLastFilter() == null) {
                            z = false;
                        }
                        peer2.setDownloadParameters(j, z);
                    }
                }
                this.lock.unlock();
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Deprecated
    @Nullable
    public TxConfidenceTable getMemoryPool() {
        return Context.get().getConfidenceTable();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022 A[Catch:{ all -> 0x0036 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFastCatchupTimeSecs(long r5) {
        /*
            r4 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r4.lock
            r0.lock()
            org.bitcoinj.core.AbstractBlockChain r0 = r4.chain     // Catch:{ all -> 0x0036 }
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0016
            org.bitcoinj.core.AbstractBlockChain r0 = r4.chain     // Catch:{ all -> 0x0036 }
            boolean r0 = r0.shouldVerifyTransactions()     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            r0 = 0
            goto L_0x0017
        L_0x0016:
            r0 = 1
        L_0x0017:
            java.lang.String r3 = "Fast catchup is incompatible with fully verifying"
            com.google.common.base.Preconditions.checkState(r0, r3)     // Catch:{ all -> 0x0036 }
            r4.fastCatchupTimeSecs = r5     // Catch:{ all -> 0x0036 }
            org.bitcoinj.core.Peer r0 = r4.downloadPeer     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0030
            org.bitcoinj.core.Peer r0 = r4.downloadPeer     // Catch:{ all -> 0x0036 }
            org.bitcoinj.net.FilterMerger r3 = r4.bloomFilterMerger     // Catch:{ all -> 0x0036 }
            org.bitcoinj.core.BloomFilter r3 = r3.getLastFilter()     // Catch:{ all -> 0x0036 }
            if (r3 == 0) goto L_0x002d
            r1 = 1
        L_0x002d:
            r0.setDownloadParameters(r5, r1)     // Catch:{ all -> 0x0036 }
        L_0x0030:
            java.util.concurrent.locks.ReentrantLock r5 = r4.lock
            r5.unlock()
            return
        L_0x0036:
            r5 = move-exception
            java.util.concurrent.locks.ReentrantLock r6 = r4.lock
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.PeerGroup.setFastCatchupTimeSecs(long):void");
    }

    public long getFastCatchupTimeSecs() {
        this.lock.lock();
        try {
            return this.fastCatchupTimeSecs;
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void handlePeerDeath(final Peer peer, @Nullable Throwable th) {
        if (isRunning()) {
            this.lock.lock();
            try {
                this.pendingPeers.remove(peer);
                this.peers.remove(peer);
                PeerAddress address = peer.getAddress();
                log.info("{}: Peer died      ({} connected, {} pending, {} max)", address, Integer.valueOf(this.peers.size()), Integer.valueOf(this.pendingPeers.size()), Integer.valueOf(this.maxConnections));
                if (peer == this.downloadPeer) {
                    log.info("Download peer died. Picking a new one.");
                    setDownloadPeer(null);
                    Peer selectDownloadPeer = selectDownloadPeer(this.peers);
                    if (selectDownloadPeer != null) {
                        setDownloadPeer(selectDownloadPeer);
                        if (this.downloadListener != null) {
                            startBlockChainDownloadFromPeer(selectDownloadPeer);
                        }
                    }
                }
                int size = this.peers.size() + this.pendingPeers.size();
                final int size2 = this.peers.size();
                this.groupBackoff.trackFailure();
                if (!(th instanceof NoRouteToHostException)) {
                    ((ExponentialBackoff) this.backoffMap.get(address)).trackFailure();
                    this.inactives.offer(address);
                } else if ((address.getAddr() instanceof Inet6Address) && !this.ipv6Unreachable) {
                    this.ipv6Unreachable = true;
                    log.warn("IPv6 peer connect failed due to routing failure, ignoring IPv6 addresses from now on");
                }
                if (size < getMaxConnections()) {
                    triggerConnections();
                }
                this.lock.unlock();
                peer.removeBlocksDownloadedEventListener(this.peerListener);
                peer.removeGetDataEventListener(this.peerListener);
                Iterator it = this.wallets.iterator();
                while (it.hasNext()) {
                    peer.removeWallet((C3530Wallet) it.next());
                }
                Iterator it2 = this.peersBlocksDownloadedEventListeners.iterator();
                while (it2.hasNext()) {
                    peer.removeBlocksDownloadedEventListener((BlocksDownloadedEventListener) ((ListenerRegistration) it2.next()).listener);
                }
                Iterator it3 = this.peersChainDownloadStartedEventListeners.iterator();
                while (it3.hasNext()) {
                    peer.removeChainDownloadStartedEventListener((ChainDownloadStartedEventListener) ((ListenerRegistration) it3.next()).listener);
                }
                Iterator it4 = this.peerGetDataEventListeners.iterator();
                while (it4.hasNext()) {
                    peer.removeGetDataEventListener((GetDataEventListener) ((ListenerRegistration) it4.next()).listener);
                }
                Iterator it5 = this.peersPreMessageReceivedEventListeners.iterator();
                while (it5.hasNext()) {
                    peer.removePreMessageReceivedEventListener((PreMessageReceivedEventListener) ((ListenerRegistration) it5.next()).listener);
                }
                Iterator it6 = this.peersTransactionBroadastEventListeners.iterator();
                while (it6.hasNext()) {
                    peer.removeOnTransactionBroadcastListener((OnTransactionBroadcastListener) ((ListenerRegistration) it6.next()).listener);
                }
                Iterator it7 = this.peerDisconnectedEventListeners.iterator();
                while (it7.hasNext()) {
                    final ListenerRegistration listenerRegistration = (ListenerRegistration) it7.next();
                    listenerRegistration.executor.execute(new Runnable() {
                        public void run() {
                            ((PeerDisconnectedEventListener) listenerRegistration.listener).onPeerDisconnected(peer, size2);
                        }
                    });
                    peer.removeDisconnectedEventListener((PeerDisconnectedEventListener) listenerRegistration.listener);
                }
            } catch (Throwable th2) {
                this.lock.unlock();
                throw th2;
            }
        }
    }

    public void setStallThreshold(int i, int i2) {
        this.lock.lock();
        try {
            this.stallPeriodSeconds = i;
            this.stallMinSpeedBytesSec = i2;
        } finally {
            this.lock.unlock();
        }
    }

    private void startBlockChainDownloadFromPeer(Peer peer) {
        this.lock.lock();
        try {
            setDownloadPeer(peer);
            if (this.chainDownloadSpeedCalculator == null) {
                this.chainDownloadSpeedCalculator = new ChainDownloadSpeedCalculator();
                this.executor.scheduleAtFixedRate(this.chainDownloadSpeedCalculator, 1, 1, TimeUnit.SECONDS);
            }
            peer.addBlocksDownloadedEventListener(Threading.SAME_THREAD, this.chainDownloadSpeedCalculator);
            peer.startBlockChainDownload();
        } finally {
            this.lock.unlock();
        }
    }

    public ListenableFuture<List<Peer>> waitForPeers(int i) {
        return waitForPeersOfVersion(i, 0);
    }

    public ListenableFuture<List<Peer>> waitForPeersOfVersion(int i, long j) {
        List findPeersOfAtLeastVersion = findPeersOfAtLeastVersion(j);
        if (findPeersOfAtLeastVersion.size() >= i) {
            return Futures.immediateFuture(findPeersOfAtLeastVersion);
        }
        SettableFuture create = SettableFuture.create();
        final long j2 = j;
        final int i2 = i;
        final SettableFuture settableFuture = create;
        C342614 r0 = new PeerConnectedEventListener() {
            public void onPeerConnected(Peer peer, int i) {
                List findPeersOfAtLeastVersion = PeerGroup.this.findPeersOfAtLeastVersion(j2);
                if (findPeersOfAtLeastVersion.size() >= i2) {
                    settableFuture.set(findPeersOfAtLeastVersion);
                    PeerGroup.this.removeConnectedEventListener(this);
                }
            }
        };
        addConnectedEventListener(r0);
        return create;
    }

    public List<Peer> findPeersOfAtLeastVersion(long j) {
        this.lock.lock();
        try {
            ArrayList arrayList = new ArrayList(this.peers.size());
            Iterator it = this.peers.iterator();
            while (it.hasNext()) {
                Peer peer = (Peer) it.next();
                if (((long) peer.getPeerVersionMessage().clientVersion) >= j) {
                    arrayList.add(peer);
                }
            }
            return arrayList;
        } finally {
            this.lock.unlock();
        }
    }

    public ListenableFuture<List<Peer>> waitForPeersWithServiceMask(final int i, final int i2) {
        this.lock.lock();
        try {
            List findPeersWithServiceMask = findPeersWithServiceMask(i2);
            if (findPeersWithServiceMask.size() >= i) {
                return Futures.immediateFuture(findPeersWithServiceMask);
            }
            final SettableFuture create = SettableFuture.create();
            addConnectedEventListener(new PeerConnectedEventListener() {
                public void onPeerConnected(Peer peer, int i) {
                    List findPeersWithServiceMask = PeerGroup.this.findPeersWithServiceMask(i2);
                    if (findPeersWithServiceMask.size() >= i) {
                        create.set(findPeersWithServiceMask);
                        PeerGroup.this.removeConnectedEventListener(this);
                    }
                }
            });
            this.lock.unlock();
            return create;
        } finally {
            this.lock.unlock();
        }
    }

    public List<Peer> findPeersWithServiceMask(int i) {
        this.lock.lock();
        try {
            ArrayList arrayList = new ArrayList(this.peers.size());
            Iterator it = this.peers.iterator();
            while (it.hasNext()) {
                Peer peer = (Peer) it.next();
                long j = (long) i;
                if ((peer.getPeerVersionMessage().localServices & j) == j) {
                    arrayList.add(peer);
                }
            }
            return arrayList;
        } finally {
            this.lock.unlock();
        }
    }

    public int getMinBroadcastConnections() {
        int i;
        this.lock.lock();
        try {
            if (this.minBroadcastConnections == 0) {
                i = getMaxConnections();
                if (i > 1) {
                    int round = (int) Math.round(((double) getMaxConnections()) * 0.8d);
                    this.lock.unlock();
                    return round;
                }
            } else {
                i = this.minBroadcastConnections;
            }
            return i;
        } finally {
            this.lock.unlock();
        }
    }

    public void setMinBroadcastConnections(int i) {
        this.lock.lock();
        try {
            this.minBroadcastConnections = i;
        } finally {
            this.lock.unlock();
        }
    }

    public TransactionBroadcast broadcastTransaction(Transaction transaction) {
        return broadcastTransaction(transaction, Math.max(1, getMinBroadcastConnections()));
    }

    public TransactionBroadcast broadcastTransaction(Transaction transaction, int i) {
        if (transaction.getConfidence().getSource().equals(Source.UNKNOWN)) {
            log.info("Transaction source unknown, setting to SELF: {}", (Object) transaction.getHashAsString());
            transaction.getConfidence().setSource(Source.SELF);
        }
        final TransactionBroadcast transactionBroadcast = new TransactionBroadcast(this, transaction);
        transactionBroadcast.setMinConnections(i);
        Futures.addCallback(transactionBroadcast.future(), new FutureCallback<Transaction>() {
            public void onSuccess(Transaction transaction) {
                PeerGroup.this.runningBroadcasts.remove(transactionBroadcast);
                Iterator it = PeerGroup.this.wallets.iterator();
                while (it.hasNext()) {
                    try {
                        ((C3530Wallet) it.next()).receivePending(transaction, null);
                    } catch (VerificationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            public void onFailure(Throwable th) {
                PeerGroup.this.runningBroadcasts.remove(transactionBroadcast);
            }
        });
        this.runningBroadcasts.add(transactionBroadcast);
        transactionBroadcast.broadcast();
        return transactionBroadcast;
    }

    public long getPingIntervalMsec() {
        this.lock.lock();
        try {
            return this.pingIntervalMsec;
        } finally {
            this.lock.unlock();
        }
    }

    public void setPingIntervalMsec(long j) {
        this.lock.lock();
        try {
            this.pingIntervalMsec = j;
            ListenableScheduledFuture<?> listenableScheduledFuture = this.vPingTask;
            if (listenableScheduledFuture != null) {
                listenableScheduledFuture.cancel(false);
            }
            setupPinging();
        } finally {
            this.lock.unlock();
        }
    }

    public void setMinRequiredProtocolVersion(int i) {
        this.vMinRequiredProtocolVersion = i;
    }

    public int getMinRequiredProtocolVersion() {
        return this.vMinRequiredProtocolVersion;
    }

    public int getMostCommonChainHeight() {
        this.lock.lock();
        try {
            return getMostCommonChainHeight(this.peers);
        } finally {
            this.lock.unlock();
        }
    }

    public static int getMostCommonChainHeight(List<Peer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Peer bestHeight : list) {
            arrayList.add(Integer.valueOf((int) bestHeight.getBestHeight()));
        }
        return C3447Utils.maxOfMostFreq((List<Integer>) arrayList);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Peer selectDownloadPeer(List<Peer> list) {
        if (list.isEmpty()) {
            return null;
        }
        int mostCommonChainHeight = getMostCommonChainHeight(list);
        ArrayList<Peer> arrayList = new ArrayList<>();
        for (Peer peer : list) {
            if (peer.getBestHeight() == ((long) mostCommonChainHeight)) {
                arrayList.add(peer);
            }
        }
        int protocolVersionNum = this.params.getProtocolVersionNum(ProtocolVersion.BLOOM_FILTER);
        int i = 0;
        int i2 = 0;
        for (Peer peerVersionMessage : arrayList) {
            i = Math.max(peerVersionMessage.getPeerVersionMessage().clientVersion, i);
            i2 = Math.min(i, protocolVersionNum);
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (Peer peer2 : arrayList) {
            if (peer2.getPeerVersionMessage().clientVersion >= i2) {
                arrayList2.add(peer2);
            }
        }
        return (Peer) arrayList2.get((int) (Math.random() * ((double) arrayList2.size())));
    }

    public Peer getDownloadPeer() {
        this.lock.lock();
        try {
            return this.downloadPeer;
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    public TorClient getTorClient() {
        return this.torClient;
    }

    public int getMaxPeersToDiscoverCount() {
        return this.vMaxPeersToDiscoverCount;
    }

    public void setMaxPeersToDiscoverCount(int i) {
        this.vMaxPeersToDiscoverCount = i;
    }

    public boolean getUseLocalhostPeerWhenPossible() {
        this.lock.lock();
        try {
            return this.useLocalhostPeerWhenPossible;
        } finally {
            this.lock.unlock();
        }
    }

    public void setUseLocalhostPeerWhenPossible(boolean z) {
        this.lock.lock();
        try {
            this.useLocalhostPeerWhenPossible = z;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isRunning() {
        return this.vRunning;
    }

    public void setBloomFilteringEnabled(boolean z) {
        this.vBloomFilteringEnabled = z;
    }

    public boolean isBloomFilteringEnabled() {
        return this.vBloomFilteringEnabled;
    }
}
