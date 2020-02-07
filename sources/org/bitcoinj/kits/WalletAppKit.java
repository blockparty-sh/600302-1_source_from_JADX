package org.bitcoinj.kits;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.Service.State;
import com.subgraph.orchid.TorClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import org.bitcoinj.core.AbstractBlockChain;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.CheckpointManager;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.net.discovery.PeerDiscovery;
import org.bitcoinj.protocols.channels.StoredPaymentChannelClientStates;
import org.bitcoinj.protocols.channels.StoredPaymentChannelServerStates;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.SPVBlockStore;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.KeyChainGroup;
import org.bitcoinj.wallet.Protos.C3527Wallet;
import org.bitcoinj.wallet.WalletExtension;
import org.bitcoinj.wallet.WalletProtobufSerializer;
import org.bitcoinj.wallet.WalletProtobufSerializer.WalletFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletAppKit extends AbstractIdleService {
    protected static final Logger log = LoggerFactory.getLogger(WalletAppKit.class);
    protected boolean autoStop;
    protected boolean blockingStartup;
    protected InputStream checkpoints;
    protected volatile Context context;
    protected final File directory;
    @Nullable
    protected PeerDiscovery discovery;
    protected DownloadProgressTracker downloadListener;
    protected final String filePrefix;
    protected final NetworkParameters params;
    protected PeerAddress[] peerAddresses;
    @Nullable
    protected DeterministicSeed restoreFromSeed;
    protected boolean useAutoSave;
    protected boolean useTor;
    protected String userAgent;
    protected volatile BlockChain vChain;
    protected volatile PeerGroup vPeerGroup;
    protected volatile BlockStore vStore;
    protected volatile C3530Wallet vWallet;
    protected volatile File vWalletFile;
    protected String version;
    protected WalletFactory walletFactory;

    /* access modifiers changed from: protected */
    public void onSetupCompleted() {
    }

    public WalletAppKit(NetworkParameters networkParameters, File file, String str) {
        this(new Context(networkParameters), file, str);
    }

    public WalletAppKit(Context context2, File file, String str) {
        this.useAutoSave = true;
        this.autoStop = true;
        this.blockingStartup = true;
        this.useTor = false;
        this.context = context2;
        this.params = (NetworkParameters) Preconditions.checkNotNull(context2.getParams());
        this.directory = (File) Preconditions.checkNotNull(file);
        this.filePrefix = (String) Preconditions.checkNotNull(str);
    }

    public WalletAppKit setPeerNodes(PeerAddress... peerAddressArr) {
        Preconditions.checkState(state() == State.NEW, "Cannot call after startup");
        this.peerAddresses = peerAddressArr;
        return this;
    }

    public WalletAppKit connectToLocalHost() {
        try {
            return setPeerNodes(new PeerAddress(this.params, InetAddress.getLocalHost(), this.params.getPort()));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public WalletAppKit setAutoSave(boolean z) {
        Preconditions.checkState(state() == State.NEW, "Cannot call after startup");
        this.useAutoSave = z;
        return this;
    }

    public WalletAppKit setDownloadListener(DownloadProgressTracker downloadProgressTracker) {
        this.downloadListener = downloadProgressTracker;
        return this;
    }

    public WalletAppKit setAutoStop(boolean z) {
        this.autoStop = z;
        return this;
    }

    public WalletAppKit setCheckpoints(InputStream inputStream) {
        InputStream inputStream2 = this.checkpoints;
        if (inputStream2 != null) {
            C3447Utils.closeUnchecked(inputStream2);
        }
        this.checkpoints = (InputStream) Preconditions.checkNotNull(inputStream);
        return this;
    }

    public WalletAppKit setBlockingStartup(boolean z) {
        this.blockingStartup = z;
        return this;
    }

    public WalletAppKit setUserAgent(String str, String str2) {
        this.userAgent = (String) Preconditions.checkNotNull(str);
        this.version = (String) Preconditions.checkNotNull(str2);
        return this;
    }

    public WalletAppKit useTor() {
        this.useTor = true;
        return this;
    }

    public WalletAppKit restoreWalletFromSeed(DeterministicSeed deterministicSeed) {
        this.restoreFromSeed = deterministicSeed;
        return this;
    }

    public WalletAppKit setDiscovery(@Nullable PeerDiscovery peerDiscovery) {
        this.discovery = peerDiscovery;
        return this;
    }

    /* access modifiers changed from: protected */
    public List<WalletExtension> provideWalletExtensions() throws Exception {
        return ImmutableList.m126of();
    }

    /* access modifiers changed from: protected */
    public BlockStore provideBlockStore(File file) throws BlockStoreException {
        return new SPVBlockStore(this.params, file);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isChainFileLocked() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x004b }
            java.io.File r2 = r5.directory     // Catch:{ all -> 0x004b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x004b }
            r3.<init>()     // Catch:{ all -> 0x004b }
            java.lang.String r4 = r5.filePrefix     // Catch:{ all -> 0x004b }
            r3.append(r4)     // Catch:{ all -> 0x004b }
            java.lang.String r4 = ".spvchain"
            r3.append(r4)     // Catch:{ all -> 0x004b }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x004b }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x004b }
            boolean r2 = r1.exists()     // Catch:{ all -> 0x004b }
            r3 = 0
            if (r2 != 0) goto L_0x0023
            return r3
        L_0x0023:
            boolean r2 = r1.isDirectory()     // Catch:{ all -> 0x004b }
            if (r2 == 0) goto L_0x002a
            return r3
        L_0x002a:
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ all -> 0x004b }
            java.lang.String r4 = "rw"
            r2.<init>(r1, r4)     // Catch:{ all -> 0x004b }
            java.nio.channels.FileChannel r0 = r2.getChannel()     // Catch:{ all -> 0x0047 }
            java.nio.channels.FileLock r0 = r0.tryLock()     // Catch:{ all -> 0x0047 }
            if (r0 != 0) goto L_0x0040
            r0 = 1
            r2.close()
            return r0
        L_0x0040:
            r0.release()     // Catch:{ all -> 0x0047 }
            r2.close()
            return r3
        L_0x0047:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x004c
        L_0x004b:
            r1 = move-exception
        L_0x004c:
            if (r0 == 0) goto L_0x0051
            r0.close()
        L_0x0051:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.kits.WalletAppKit.isChainFileLocked():boolean");
    }

    /* access modifiers changed from: protected */
    public void startUp() throws Exception {
        long j;
        Context.propagate(this.context);
        if (this.directory.exists() || this.directory.mkdirs()) {
            log.info("Starting up with directory = {}", (Object) this.directory);
            try {
                File file = this.directory;
                StringBuilder sb = new StringBuilder();
                sb.append(this.filePrefix);
                sb.append(".spvchain");
                File file2 = new File(file, sb.toString());
                boolean exists = file2.exists();
                File file3 = this.directory;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.filePrefix);
                sb2.append(".wallet");
                this.vWalletFile = new File(file3, sb2.toString());
                this.vWallet = createOrLoadWallet((this.vWalletFile.exists() && !exists) || this.restoreFromSeed != null);
                this.vStore = provideBlockStore(file2);
                if (!exists || this.restoreFromSeed != null) {
                    if (this.checkpoints == null && !C3447Utils.isAndroidRuntime()) {
                        this.checkpoints = CheckpointManager.openStream(this.params);
                    }
                    String str = "Failed to delete chain file in preparation for restore.";
                    String str2 = "Deleting the chain file in preparation from restore.";
                    if (this.checkpoints != null) {
                        if (this.restoreFromSeed != null) {
                            j = this.restoreFromSeed.getCreationTimeSeconds();
                            if (exists) {
                                log.info(str2);
                                this.vStore.close();
                                if (file2.delete()) {
                                    this.vStore = new SPVBlockStore(this.params, file2);
                                } else {
                                    throw new IOException(str);
                                }
                            }
                        } else {
                            j = this.vWallet.getEarliestKeyCreationTime();
                        }
                        if (j > 0) {
                            CheckpointManager.checkpoint(this.params, this.checkpoints, this.vStore, j);
                        } else {
                            log.warn("Creating a new uncheckpointed block store due to a wallet with a creation time of zero: this will result in a very slow chain sync");
                        }
                    } else if (exists) {
                        log.info(str2);
                        this.vStore.close();
                        if (file2.delete()) {
                            this.vStore = new SPVBlockStore(this.params, file2);
                        } else {
                            throw new IOException(str);
                        }
                    }
                }
                this.vChain = new BlockChain(this.params, this.vStore);
                this.vPeerGroup = createPeerGroup();
                if (this.userAgent != null) {
                    this.vPeerGroup.setUserAgent(this.userAgent, this.version);
                }
                if (this.peerAddresses != null) {
                    for (PeerAddress addAddress : this.peerAddresses) {
                        this.vPeerGroup.addAddress(addAddress);
                    }
                    this.vPeerGroup.setMaxConnections(this.peerAddresses.length);
                    this.peerAddresses = null;
                } else if (!this.params.getId().equals(NetworkParameters.ID_REGTEST) && !this.useTor) {
                    this.vPeerGroup.addPeerDiscovery(this.discovery != null ? this.discovery : new DnsDiscovery(this.params));
                }
                this.vChain.addWallet(this.vWallet);
                this.vPeerGroup.addWallet(this.vWallet);
                onSetupCompleted();
                if (this.blockingStartup) {
                    this.vPeerGroup.start();
                    installShutdownHook();
                    completeExtensionInitiations(this.vPeerGroup);
                    DownloadProgressTracker downloadProgressTracker = new DownloadProgressTracker();
                    this.vPeerGroup.startBlockChainDownload(downloadProgressTracker);
                    downloadProgressTracker.await();
                    return;
                }
                Futures.addCallback(this.vPeerGroup.startAsync(), new FutureCallback() {
                    public void onSuccess(@Nullable Object obj) {
                        WalletAppKit walletAppKit = WalletAppKit.this;
                        walletAppKit.completeExtensionInitiations(walletAppKit.vPeerGroup);
                        WalletAppKit.this.vPeerGroup.startBlockChainDownload(WalletAppKit.this.downloadListener == null ? new DownloadProgressTracker() : WalletAppKit.this.downloadListener);
                    }

                    public void onFailure(Throwable th) {
                        throw new RuntimeException(th);
                    }
                });
            } catch (BlockStoreException e) {
                throw new IOException(e);
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Could not create directory ");
            sb3.append(this.directory.getAbsolutePath());
            throw new IOException(sb3.toString());
        }
    }

    private C3530Wallet createOrLoadWallet(boolean z) throws Exception {
        C3530Wallet wallet;
        maybeMoveOldWalletOutOfTheWay();
        if (this.vWalletFile.exists()) {
            wallet = loadWallet(z);
        } else {
            C3530Wallet createWallet = createWallet();
            createWallet.freshReceiveKey();
            for (WalletExtension addExtension : provideWalletExtensions()) {
                createWallet.addExtension(addExtension);
            }
            createWallet.saveToFile(this.vWalletFile);
            wallet = loadWallet(false);
        }
        if (this.useAutoSave) {
            setupAutoSave(wallet);
        }
        return wallet;
    }

    /* access modifiers changed from: protected */
    public void setupAutoSave(C3530Wallet wallet) {
        wallet.autosaveToFile(this.vWalletFile, 5, TimeUnit.SECONDS, null);
    }

    private C3530Wallet loadWallet(boolean z) throws Exception {
        WalletProtobufSerializer walletProtobufSerializer;
        FileInputStream fileInputStream = new FileInputStream(this.vWalletFile);
        try {
            List provideWalletExtensions = provideWalletExtensions();
            WalletExtension[] walletExtensionArr = (WalletExtension[]) provideWalletExtensions.toArray(new WalletExtension[provideWalletExtensions.size()]);
            C3527Wallet parseToProto = WalletProtobufSerializer.parseToProto(fileInputStream);
            if (this.walletFactory != null) {
                walletProtobufSerializer = new WalletProtobufSerializer(this.walletFactory);
            } else {
                walletProtobufSerializer = new WalletProtobufSerializer();
            }
            C3530Wallet readWallet = walletProtobufSerializer.readWallet(this.params, walletExtensionArr, parseToProto);
            if (z) {
                readWallet.reset();
            }
            return readWallet;
        } finally {
            fileInputStream.close();
        }
    }

    /* access modifiers changed from: protected */
    public C3530Wallet createWallet() {
        KeyChainGroup keyChainGroup;
        DeterministicSeed deterministicSeed = this.restoreFromSeed;
        if (deterministicSeed != null) {
            keyChainGroup = new KeyChainGroup(this.params, deterministicSeed);
        } else {
            keyChainGroup = new KeyChainGroup(this.params);
        }
        WalletFactory walletFactory2 = this.walletFactory;
        if (walletFactory2 != null) {
            return walletFactory2.create(this.params, keyChainGroup);
        }
        return new C3530Wallet(this.params, keyChainGroup);
    }

    private void maybeMoveOldWalletOutOfTheWay() {
        File file;
        if (this.restoreFromSeed != null && this.vWalletFile.exists()) {
            int i = 1;
            do {
                String parent = this.vWalletFile.getParent();
                StringBuilder sb = new StringBuilder();
                sb.append("Backup ");
                sb.append(i);
                sb.append(" for ");
                sb.append(this.vWalletFile.getName());
                file = new File(parent, sb.toString());
                i++;
            } while (file.exists());
            log.info("Renaming old wallet file {} to {}", (Object) this.vWalletFile, (Object) file);
            if (!this.vWalletFile.renameTo(file)) {
                throw new RuntimeException("Failed to rename wallet for restore");
            }
        }
    }

    /* access modifiers changed from: private */
    public void completeExtensionInitiations(TransactionBroadcaster transactionBroadcaster) {
        StoredPaymentChannelClientStates storedPaymentChannelClientStates = (StoredPaymentChannelClientStates) this.vWallet.getExtensions().get(StoredPaymentChannelClientStates.class.getName());
        if (storedPaymentChannelClientStates != null) {
            storedPaymentChannelClientStates.setTransactionBroadcaster(transactionBroadcaster);
        }
        StoredPaymentChannelServerStates storedPaymentChannelServerStates = (StoredPaymentChannelServerStates) this.vWallet.getExtensions().get(StoredPaymentChannelServerStates.class.getName());
        if (storedPaymentChannelServerStates != null) {
            storedPaymentChannelServerStates.setTransactionBroadcaster(transactionBroadcaster);
        }
    }

    /* access modifiers changed from: protected */
    public PeerGroup createPeerGroup() throws TimeoutException {
        if (!this.useTor) {
            return new PeerGroup(this.params, (AbstractBlockChain) this.vChain);
        }
        TorClient torClient = new TorClient();
        torClient.getConfig().setDataDirectory(this.directory);
        return PeerGroup.newWithTor(this.params, (AbstractBlockChain) this.vChain, torClient);
    }

    private void installShutdownHook() {
        if (this.autoStop) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        WalletAppKit.this.stopAsync();
                        WalletAppKit.this.awaitTerminated();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void shutDown() throws Exception {
        try {
            Context.propagate(this.context);
            this.vPeerGroup.stop();
            this.vWallet.saveToFile(this.vWalletFile);
            this.vStore.close();
            this.vPeerGroup = null;
            this.vWallet = null;
            this.vStore = null;
            this.vChain = null;
        } catch (BlockStoreException e) {
            throw new IOException(e);
        }
    }

    public NetworkParameters params() {
        return this.params;
    }

    public BlockChain chain() {
        Preconditions.checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return this.vChain;
    }

    public BlockStore store() {
        Preconditions.checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return this.vStore;
    }

    public C3530Wallet wallet() {
        Preconditions.checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return this.vWallet;
    }

    public PeerGroup peerGroup() {
        Preconditions.checkState(state() == State.STARTING || state() == State.RUNNING, "Cannot call until startup is complete");
        return this.vPeerGroup;
    }

    public File directory() {
        return this.directory;
    }
}
