package org.bitcoinj.wallet;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import net.jcip.annotations.GuardedBy;
import org.bitcoinj.core.AbstractBlockChain;
import org.bitcoinj.core.AbstractBlockChain.NewBlockType;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.BloomFilter;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.MissingPrivateKeyException;
import org.bitcoinj.core.FilteredBlock;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerFilterProvider;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.Purpose;
import org.bitcoinj.core.TransactionBag;
import org.bitcoinj.core.TransactionBroadcast;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.TransactionConfidence.Listener;
import org.bitcoinj.core.TransactionConfidence.Listener.ChangeReason;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionInput.ConnectMode;
import org.bitcoinj.core.TransactionInput.ConnectionResult;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.core.UTXOProvider;
import org.bitcoinj.core.UTXOProviderException;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.core.listeners.NewBestBlockListener;
import org.bitcoinj.core.listeners.ReorganizeListener;
import org.bitcoinj.core.listeners.TransactionConfidenceEventListener;
import org.bitcoinj.core.listeners.TransactionReceivedInBlockListener;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.crypto.KeyCrypterScrypt;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptChunk;
import org.bitcoinj.signers.LocalTransactionSigner;
import org.bitcoinj.signers.MissingSigResolutionSigner;
import org.bitcoinj.signers.TransactionSigner;
import org.bitcoinj.signers.TransactionSigner.ProposedTransaction;
import org.bitcoinj.utils.BaseTaggableObject;
import org.bitcoinj.utils.ListenerRegistration;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.bitcoinj.wallet.Protos.C3509Key;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.bitcoinj.wallet.RiskAnalysis.Analyzer;
import org.bitcoinj.wallet.RiskAnalysis.Result;
import org.bitcoinj.wallet.WalletTransaction.Pool;
import org.bitcoinj.wallet.listeners.KeyChainEventListener;
import org.bitcoinj.wallet.listeners.ScriptsChangeEventListener;
import org.bitcoinj.wallet.listeners.WalletChangeEventListener;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;
import org.bitcoinj.wallet.listeners.WalletCoinsSentEventListener;
import org.bitcoinj.wallet.listeners.WalletEventListener;
import org.bitcoinj.wallet.listeners.WalletReorganizeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.params.KeyParameter;

/* renamed from: org.bitcoinj.wallet.Wallet */
public class C3530Wallet extends BaseTaggableObject implements NewBestBlockListener, TransactionReceivedInBlockListener, PeerFilterProvider, KeyBag, TransactionBag, ReorganizeListener {
    private static final int MINIMUM_BLOOM_DATA_LENGTH = 8;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(C3530Wallet.class);
    private boolean acceptRiskyTransactions;
    @GuardedBy("lock")
    private List<BalanceFutureRequest> balanceFutureRequests;
    private final AtomicInteger bloomFilterGuard;
    private final ArrayList<TransactionOutPoint> bloomOutPoints;
    private final CopyOnWriteArrayList<ListenerRegistration<WalletChangeEventListener>> changeListeners;
    protected CoinSelector coinSelector;
    private final CopyOnWriteArrayList<ListenerRegistration<WalletCoinsReceivedEventListener>> coinsReceivedListeners;
    private final CopyOnWriteArrayList<ListenerRegistration<WalletCoinsSentEventListener>> coinsSentListeners;
    private Map<Transaction, ChangeReason> confidenceChanged;
    protected final Context context;
    private final Map<Sha256Hash, Transaction> dead;
    private String description;
    private final HashMap<String, WalletExtension> extensions;
    private boolean hardSaveOnNextBlock;
    private HashSet<Sha256Hash> ignoreNextNewBlock;
    private boolean insideReorg;
    @GuardedBy("keyChainGroupLock")
    private KeyChainGroup keyChainGroup;
    protected final ReentrantLock keyChainGroupLock;
    @Nullable
    private Sha256Hash lastBlockSeenHash;
    private int lastBlockSeenHeight;
    private long lastBlockSeenTimeSecs;
    protected final ReentrantLock lock;
    protected final HashSet<TransactionOutput> myUnspents;
    private int onWalletChangedSuppressions;
    protected final NetworkParameters params;
    private final Map<Sha256Hash, Transaction> pending;
    private final CopyOnWriteArrayList<ListenerRegistration<WalletReorganizeEventListener>> reorganizeListeners;
    private Analyzer riskAnalyzer;
    private final LinkedHashMap<Sha256Hash, Transaction> riskDropped;
    private final CopyOnWriteArrayList<ListenerRegistration<ScriptsChangeEventListener>> scriptChangeListeners;
    @GuardedBy("lock")
    private List<TransactionSigner> signers;
    private final Map<Sha256Hash, Transaction> spent;
    private final CopyOnWriteArrayList<ListenerRegistration<TransactionConfidenceEventListener>> transactionConfidenceListeners;
    protected final Map<Sha256Hash, Transaction> transactions;
    private Listener txConfidenceListener;
    private final Map<Sha256Hash, Transaction> unspent;
    protected volatile WalletFiles vFileManager;
    private volatile long vKeyRotationTimestamp;
    protected volatile TransactionBroadcaster vTransactionBroadcaster;
    @Nullable
    private volatile UTXOProvider vUTXOProvider;
    private int version;
    @GuardedBy("keyChainGroupLock")
    private Set<Script> watchedScripts;

    /* renamed from: org.bitcoinj.wallet.Wallet$11 */
    static /* synthetic */ class C353311 {
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool = new int[Pool.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                org.bitcoinj.wallet.WalletTransaction$Pool[] r0 = org.bitcoinj.wallet.WalletTransaction.Pool.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool = r0
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoinj.wallet.WalletTransaction$Pool r1 = org.bitcoinj.wallet.WalletTransaction.Pool.UNSPENT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoinj.wallet.WalletTransaction$Pool r1 = org.bitcoinj.wallet.WalletTransaction.Pool.SPENT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x002a }
                org.bitcoinj.wallet.WalletTransaction$Pool r1 = org.bitcoinj.wallet.WalletTransaction.Pool.PENDING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool     // Catch:{ NoSuchFieldError -> 0x0035 }
                org.bitcoinj.wallet.WalletTransaction$Pool r1 = org.bitcoinj.wallet.WalletTransaction.Pool.DEAD     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.C3530Wallet.C353311.<clinit>():void");
        }
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$BalanceFutureRequest */
    private static class BalanceFutureRequest {
        public SettableFuture<Coin> future;
        public BalanceType type;
        public Coin value;

        private BalanceFutureRequest() {
        }
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$BalanceType */
    public enum BalanceType {
        ESTIMATED,
        AVAILABLE,
        ESTIMATED_SPENDABLE,
        AVAILABLE_SPENDABLE
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$CompletionException */
    public static class CompletionException extends RuntimeException {
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$CouldNotAdjustDownwards */
    public static class CouldNotAdjustDownwards extends CompletionException {
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$DustySendRequested */
    public static class DustySendRequested extends CompletionException {
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$ExceededMaxTransactionSize */
    public static class ExceededMaxTransactionSize extends CompletionException {
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$FeeCalculation */
    private static class FeeCalculation {
        public TransactionOutput bestChangeOutput;
        public CoinSelection bestCoinSelection;

        private FeeCalculation() {
        }
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$FreeStandingTransactionOutput */
    private class FreeStandingTransactionOutput extends TransactionOutput {
        private int chainHeight;
        private UTXO output;

        public FreeStandingTransactionOutput(NetworkParameters networkParameters, UTXO utxo, int i) {
            super(networkParameters, (Transaction) null, utxo.getValue(), utxo.getScript().getProgram());
            this.output = utxo;
            this.chainHeight = i;
        }

        public UTXO getUTXO() {
            return this.output;
        }

        public int getParentTransactionDepthInBlocks() {
            return (this.chainHeight - this.output.getHeight()) + 1;
        }

        public int getIndex() {
            return (int) this.output.getIndex();
        }

        public Sha256Hash getParentTransactionHash() {
            return this.output.getHash();
        }
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$MissingSigsMode */
    public enum MissingSigsMode {
        USE_OP_ZERO,
        USE_DUMMY_SIG,
        THROW
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$MultipleOpReturnRequested */
    public static class MultipleOpReturnRequested extends CompletionException {
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$SendResult */
    public static class SendResult {
        public TransactionBroadcast broadcast;
        public ListenableFuture<Transaction> broadcastComplete;

        /* renamed from: tx */
        public Transaction f830tx;
    }

    /* renamed from: org.bitcoinj.wallet.Wallet$TxOffsetPair */
    private static class TxOffsetPair implements Comparable<TxOffsetPair> {
        public final int offset;

        /* renamed from: tx */
        public final Transaction f831tx;

        public TxOffsetPair(Transaction transaction, int i) {
            this.f831tx = transaction;
            this.offset = i;
        }

        public int compareTo(TxOffsetPair txOffsetPair) {
            return Ints.compare(this.offset, txOffsetPair.offset);
        }
    }

    public C3530Wallet(NetworkParameters networkParameters) {
        this(Context.getOrCreate(networkParameters));
    }

    public C3530Wallet(Context context2) {
        this(context2, new KeyChainGroup(context2.getParams()));
    }

    public static C3530Wallet fromSeed(NetworkParameters networkParameters, DeterministicSeed deterministicSeed) {
        return new C3530Wallet(networkParameters, new KeyChainGroup(networkParameters, deterministicSeed));
    }

    public static C3530Wallet fromWatchingKey(NetworkParameters networkParameters, DeterministicKey deterministicKey) {
        return new C3530Wallet(networkParameters, new KeyChainGroup(networkParameters, deterministicKey));
    }

    public static C3530Wallet fromWatchingKeyB58(NetworkParameters networkParameters, String str, long j) {
        DeterministicKey deserializeB58 = DeterministicKey.deserializeB58(null, str, networkParameters);
        deserializeB58.setCreationTimeSeconds(j);
        return fromWatchingKey(networkParameters, deserializeB58);
    }

    public static C3530Wallet fromKeys(NetworkParameters networkParameters, List<ECKey> list) {
        for (ECKey eCKey : list) {
            Preconditions.checkArgument(!(eCKey instanceof DeterministicKey));
        }
        KeyChainGroup keyChainGroup2 = new KeyChainGroup(networkParameters);
        keyChainGroup2.importKeys(list);
        return new C3530Wallet(networkParameters, keyChainGroup2);
    }

    public C3530Wallet(NetworkParameters networkParameters, KeyChainGroup keyChainGroup2) {
        this(Context.getOrCreate(networkParameters), keyChainGroup2);
    }

    private C3530Wallet(Context context2, KeyChainGroup keyChainGroup2) {
        this.lock = Threading.lock("wallet");
        this.keyChainGroupLock = Threading.lock("wallet-keychaingroup");
        this.myUnspents = Sets.newHashSet();
        this.riskDropped = new LinkedHashMap<Sha256Hash, Transaction>() {
            /* access modifiers changed from: protected */
            public boolean removeEldestEntry(Entry<Sha256Hash, Transaction> entry) {
                return size() > 1000;
            }
        };
        this.changeListeners = new CopyOnWriteArrayList<>();
        this.coinsReceivedListeners = new CopyOnWriteArrayList<>();
        this.coinsSentListeners = new CopyOnWriteArrayList<>();
        this.reorganizeListeners = new CopyOnWriteArrayList<>();
        this.scriptChangeListeners = new CopyOnWriteArrayList<>();
        this.transactionConfidenceListeners = new CopyOnWriteArrayList<>();
        this.riskAnalyzer = DefaultRiskAnalysis.FACTORY;
        this.coinSelector = new DefaultCoinSelector();
        this.hardSaveOnNextBlock = false;
        this.balanceFutureRequests = Lists.newLinkedList();
        this.bloomOutPoints = Lists.newArrayList();
        this.bloomFilterGuard = new AtomicInteger(0);
        this.context = context2;
        this.params = context2.getParams();
        this.keyChainGroup = (KeyChainGroup) Preconditions.checkNotNull(keyChainGroup2);
        if (this.params.getId().equals(NetworkParameters.ID_UNITTESTNET)) {
            this.keyChainGroup.setLookaheadSize(5);
        }
        if (this.keyChainGroup.numKeys() == 0) {
            this.keyChainGroup.createAndActivateNewHDChain();
        }
        this.watchedScripts = Sets.newHashSet();
        this.unspent = new HashMap();
        this.spent = new HashMap();
        this.pending = new HashMap();
        this.dead = new HashMap();
        this.transactions = new HashMap();
        this.extensions = new HashMap<>();
        this.confidenceChanged = new LinkedHashMap();
        this.signers = new ArrayList();
        addTransactionSigner(new LocalTransactionSigner());
        createTransientState();
    }

    private void createTransientState() {
        this.ignoreNextNewBlock = new HashSet<>();
        this.txConfidenceListener = new Listener() {
            public void onConfidenceChanged(TransactionConfidence transactionConfidence, ChangeReason changeReason) {
                if (changeReason == ChangeReason.SEEN_PEERS) {
                    C3530Wallet.this.lock.lock();
                    try {
                        C3530Wallet.this.checkBalanceFuturesLocked(null);
                        C3530Wallet.this.queueOnTransactionConfidenceChanged(C3530Wallet.this.getTransaction(transactionConfidence.getTransactionHash()));
                        C3530Wallet.this.maybeQueueOnWalletChanged();
                    } finally {
                        C3530Wallet.this.lock.unlock();
                    }
                }
            }
        };
        this.acceptRiskyTransactions = false;
    }

    public NetworkParameters getNetworkParameters() {
        return this.params;
    }

    public DeterministicKeyChain getActiveKeyChain() {
        return this.keyChainGroup.getActiveKeyChain();
    }

    public final void addTransactionSigner(TransactionSigner transactionSigner) {
        this.lock.lock();
        try {
            if (transactionSigner.isReady()) {
                this.signers.add(transactionSigner);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Signer instance is not ready to be added into Wallet: ");
            sb.append(transactionSigner.getClass());
            throw new IllegalStateException(sb.toString());
        } finally {
            this.lock.unlock();
        }
    }

    public List<TransactionSigner> getTransactionSigners() {
        this.lock.lock();
        try {
            return ImmutableList.copyOf((Collection<? extends E>) this.signers);
        } finally {
            this.lock.unlock();
        }
    }

    public DeterministicKey currentKey(KeyPurpose keyPurpose) {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            return this.keyChainGroup.currentKey(keyPurpose);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public DeterministicKey currentReceiveKey() {
        return currentKey(KeyPurpose.RECEIVE_FUNDS);
    }

    public Address currentAddress(KeyPurpose keyPurpose) {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            return this.keyChainGroup.currentAddress(keyPurpose);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public Address currentReceiveAddress() {
        return currentAddress(KeyPurpose.RECEIVE_FUNDS);
    }

    public DeterministicKey freshKey(KeyPurpose keyPurpose) {
        return (DeterministicKey) freshKeys(keyPurpose, 1).get(0);
    }

    /* JADX INFO: finally extract failed */
    public List<DeterministicKey> freshKeys(KeyPurpose keyPurpose, int i) {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            List<DeterministicKey> freshKeys = this.keyChainGroup.freshKeys(keyPurpose, i);
            this.keyChainGroupLock.unlock();
            saveNow();
            return freshKeys;
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    public DeterministicKey freshReceiveKey() {
        return freshKey(KeyPurpose.RECEIVE_FUNDS);
    }

    /* JADX INFO: finally extract failed */
    public Address freshAddress(KeyPurpose keyPurpose) {
        this.keyChainGroupLock.lock();
        try {
            Address freshAddress = this.keyChainGroup.freshAddress(keyPurpose);
            this.keyChainGroupLock.unlock();
            saveNow();
            return freshAddress;
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    public Address freshReceiveAddress() {
        return freshAddress(KeyPurpose.RECEIVE_FUNDS);
    }

    public List<ECKey> getIssuedReceiveKeys() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.getActiveKeyChain().getIssuedReceiveKeys();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public List<Address> getIssuedReceiveAddresses() {
        List<ECKey> issuedReceiveKeys = getIssuedReceiveKeys();
        ArrayList arrayList = new ArrayList(issuedReceiveKeys.size());
        for (ECKey address : issuedReceiveKeys) {
            arrayList.add(address.toAddress(getParams()));
        }
        return arrayList;
    }

    public void upgradeToDeterministic(@Nullable KeyParameter keyParameter) throws DeterministicUpgradeRequiresPassword {
        this.keyChainGroupLock.lock();
        try {
            this.keyChainGroup.upgradeToDeterministic(this.vKeyRotationTimestamp, keyParameter);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean isDeterministicUpgradeRequired() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.isDeterministicUpgradeRequired();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    private void maybeUpgradeToHD() throws DeterministicUpgradeRequiresPassword {
        maybeUpgradeToHD(null);
    }

    @GuardedBy("keyChainGroupLock")
    private void maybeUpgradeToHD(@Nullable KeyParameter keyParameter) throws DeterministicUpgradeRequiresPassword {
        Preconditions.checkState(this.keyChainGroupLock.isHeldByCurrentThread());
        if (this.keyChainGroup.isDeterministicUpgradeRequired()) {
            log.info("Upgrade to HD wallets is required, attempting to do so.");
            try {
                upgradeToDeterministic(keyParameter);
            } catch (DeterministicUpgradeRequiresPassword e) {
                log.error("Failed to auto upgrade due to encryption. You should call wallet.upgradeToDeterministic with the users AES key to avoid this error.");
                throw e;
            }
        }
    }

    public List<Script> getWatchedScripts() {
        this.keyChainGroupLock.lock();
        try {
            return new ArrayList(this.watchedScripts);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean removeKey(ECKey eCKey) {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.removeImportedKey(eCKey);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public int getKeyChainGroupSize() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.numKeys();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    @VisibleForTesting
    public int getKeyChainGroupCombinedKeyLookaheadEpochs() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.getCombinedKeyLookaheadEpochs();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public List<ECKey> getImportedKeys() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.getImportedKeys();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public Address currentChangeAddress() {
        return currentAddress(KeyPurpose.CHANGE);
    }

    public Address getChangeAddress() {
        return currentChangeAddress();
    }

    @Deprecated
    public boolean addKey(ECKey eCKey) {
        return importKey(eCKey);
    }

    public boolean importKey(ECKey eCKey) {
        return importKeys(Lists.newArrayList((E[]) new ECKey[]{eCKey})) == 1;
    }

    @Deprecated
    public int addKeys(List<ECKey> list) {
        return importKeys(list);
    }

    /* JADX INFO: finally extract failed */
    public int importKeys(List<ECKey> list) {
        checkNoDeterministicKeys(list);
        this.keyChainGroupLock.lock();
        try {
            int importKeys = this.keyChainGroup.importKeys(list);
            this.keyChainGroupLock.unlock();
            saveNow();
            return importKeys;
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    private void checkNoDeterministicKeys(List<ECKey> list) {
        for (ECKey eCKey : list) {
            if (eCKey instanceof DeterministicKey) {
                throw new IllegalArgumentException("Cannot import HD keys back into the wallet");
            }
        }
    }

    public int importKeysAndEncrypt(List<ECKey> list, CharSequence charSequence) {
        this.keyChainGroupLock.lock();
        try {
            Preconditions.checkNotNull(getKeyCrypter(), "Wallet is not encrypted");
            int importKeysAndEncrypt = importKeysAndEncrypt(list, getKeyCrypter().deriveKey(charSequence));
            return importKeysAndEncrypt;
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public int importKeysAndEncrypt(List<ECKey> list, KeyParameter keyParameter) {
        this.keyChainGroupLock.lock();
        try {
            checkNoDeterministicKeys(list);
            int importKeysAndEncrypt = this.keyChainGroup.importKeysAndEncrypt(list, keyParameter);
            return importKeysAndEncrypt;
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public void addAndActivateHDChain(DeterministicKeyChain deterministicKeyChain) {
        this.keyChainGroupLock.lock();
        try {
            this.keyChainGroup.addAndActivateHDChain(deterministicKeyChain);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public void setKeyChainGroupLookaheadSize(int i) {
        this.keyChainGroupLock.lock();
        try {
            this.keyChainGroup.setLookaheadSize(i);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public int getKeyChainGroupLookaheadSize() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.getLookaheadSize();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public void setKeyChainGroupLookaheadThreshold(int i) {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            this.keyChainGroup.setLookaheadThreshold(i);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public int getKeyChainGroupLookaheadThreshold() {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            return this.keyChainGroup.getLookaheadThreshold();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public DeterministicKey getWatchingKey() {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            return this.keyChainGroup.getActiveKeyChain().getWatchingKey();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean isWatching() {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            return this.keyChainGroup.isWatching();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean isAddressWatched(Address address) {
        return isWatchedScript(ScriptBuilder.createOutputScript(address));
    }

    public boolean addWatchedAddress(Address address) {
        if (addWatchedAddresses(Lists.newArrayList((E[]) new Address[]{address}), C3447Utils.currentTimeMillis() / 1000) == 1) {
            return true;
        }
        return false;
    }

    public boolean addWatchedAddress(Address address, long j) {
        return addWatchedAddresses(Lists.newArrayList((E[]) new Address[]{address}), j) == 1;
    }

    public int addWatchedAddresses(List<Address> list, long j) {
        ArrayList newArrayList = Lists.newArrayList();
        for (Address createOutputScript : list) {
            Script createOutputScript2 = ScriptBuilder.createOutputScript(createOutputScript);
            createOutputScript2.setCreationTimeSeconds(j);
            newArrayList.add(createOutputScript2);
        }
        return addWatchedScripts(newArrayList);
    }

    public int addWatchedScripts(List<Script> list) {
        this.keyChainGroupLock.lock();
        try {
            int i = 0;
            for (Script script : list) {
                if (this.watchedScripts.contains(script)) {
                    this.watchedScripts.remove(script);
                }
                if (script.getCreationTimeSeconds() == 0) {
                    log.warn("Adding a script to the wallet with a creation time of zero, this will disable the checkpointing optimization!    {}", (Object) script);
                }
                this.watchedScripts.add(script);
                i++;
            }
            if (i > 0) {
                queueOnScriptsChanged(list, true);
                saveNow();
            }
            return i;
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean removeWatchedAddress(Address address) {
        return removeWatchedAddresses(ImmutableList.m127of(address));
    }

    public boolean removeWatchedAddresses(List<Address> list) {
        ArrayList newArrayList = Lists.newArrayList();
        for (Address createOutputScript : list) {
            newArrayList.add(ScriptBuilder.createOutputScript(createOutputScript));
        }
        return removeWatchedScripts(newArrayList);
    }

    public boolean removeWatchedScripts(List<Script> list) {
        this.lock.lock();
        try {
            for (Script script : list) {
                if (this.watchedScripts.contains(script)) {
                    this.watchedScripts.remove(script);
                }
            }
            queueOnScriptsChanged(list, false);
            saveNow();
            return true;
        } finally {
            this.lock.unlock();
        }
    }

    public List<Address> getWatchedAddresses() {
        this.keyChainGroupLock.lock();
        try {
            LinkedList linkedList = new LinkedList();
            for (Script script : this.watchedScripts) {
                if (script.isSentToAddress()) {
                    linkedList.add(script.getToAddress(this.params));
                }
            }
            return linkedList;
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    @Nullable
    public ECKey findKeyFromPubHash(byte[] bArr) {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.findKeyFromPubHash(bArr);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean hasKey(ECKey eCKey) {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.hasKey(eCKey);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean isPubKeyHashMine(byte[] bArr) {
        return findKeyFromPubHash(bArr) != null;
    }

    public boolean isWatchedScript(Script script) {
        this.keyChainGroupLock.lock();
        try {
            return this.watchedScripts.contains(script);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    @Nullable
    public ECKey findKeyFromPubKey(byte[] bArr) {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.findKeyFromPubKey(bArr);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean isPubKeyMine(byte[] bArr) {
        return findKeyFromPubKey(bArr) != null;
    }

    @Nullable
    public RedeemData findRedeemDataFromScriptHash(byte[] bArr) {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.findRedeemDataFromScriptHash(bArr);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean isPayToScriptHashMine(byte[] bArr) {
        return findRedeemDataFromScriptHash(bArr) != null;
    }

    private void markKeysAsUsed(Transaction transaction) {
        this.keyChainGroupLock.lock();
        try {
            for (TransactionOutput scriptPubKey : transaction.getOutputs()) {
                Script scriptPubKey2 = scriptPubKey.getScriptPubKey();
                if (scriptPubKey2.isSentToRawPubKey()) {
                    this.keyChainGroup.markPubKeyAsUsed(scriptPubKey2.getPubKey());
                } else if (scriptPubKey2.isSentToAddress()) {
                    this.keyChainGroup.markPubKeyHashAsUsed(scriptPubKey2.getPubKeyHash());
                } else if (scriptPubKey2.isPayToScriptHash()) {
                    this.keyChainGroup.markP2SHAddressAsUsed(Address.fromP2SHScript(transaction.getParams(), scriptPubKey2));
                }
            }
            this.keyChainGroupLock.unlock();
        } catch (ScriptException e) {
            log.warn("Could not parse tx output script: {}", (Object) e.toString());
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    public DeterministicSeed getKeyChainSeed() {
        this.keyChainGroupLock.lock();
        try {
            DeterministicSeed seed = this.keyChainGroup.getActiveKeyChain().getSeed();
            if (seed != null) {
                return seed;
            }
            throw new MissingPrivateKeyException();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public DeterministicKey getKeyByPath(List<ChildNumber> list) {
        this.keyChainGroupLock.lock();
        try {
            maybeUpgradeToHD();
            return this.keyChainGroup.getActiveKeyChain().getKeyByPath(list, false);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public void encrypt(CharSequence charSequence) {
        this.keyChainGroupLock.lock();
        try {
            KeyCrypterScrypt keyCrypterScrypt = new KeyCrypterScrypt();
            this.keyChainGroup.encrypt(keyCrypterScrypt, keyCrypterScrypt.deriveKey(charSequence));
            this.keyChainGroupLock.unlock();
            saveNow();
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void encrypt(KeyCrypter keyCrypter, KeyParameter keyParameter) {
        this.keyChainGroupLock.lock();
        try {
            this.keyChainGroup.encrypt(keyCrypter, keyParameter);
            this.keyChainGroupLock.unlock();
            saveNow();
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void decrypt(CharSequence charSequence) {
        this.keyChainGroupLock.lock();
        try {
            KeyCrypter keyCrypter = this.keyChainGroup.getKeyCrypter();
            Preconditions.checkState(keyCrypter != null, "Not encrypted");
            this.keyChainGroup.decrypt(keyCrypter.deriveKey(charSequence));
            this.keyChainGroupLock.unlock();
            saveNow();
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void decrypt(KeyParameter keyParameter) {
        this.keyChainGroupLock.lock();
        try {
            this.keyChainGroup.decrypt(keyParameter);
            this.keyChainGroupLock.unlock();
            saveNow();
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            throw th;
        }
    }

    public boolean checkPassword(CharSequence charSequence) {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.checkPassword(charSequence);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean checkAESKey(KeyParameter keyParameter) {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.checkAESKey(keyParameter);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    @Nullable
    public KeyCrypter getKeyCrypter() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.getKeyCrypter();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public EncryptionType getEncryptionType() {
        EncryptionType encryptionType;
        this.keyChainGroupLock.lock();
        try {
            KeyCrypter keyCrypter = this.keyChainGroup.getKeyCrypter();
            if (keyCrypter != null) {
                encryptionType = keyCrypter.getUnderstoodEncryptionType();
            } else {
                encryptionType = EncryptionType.UNENCRYPTED;
            }
            return encryptionType;
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public boolean isEncrypted() {
        return getEncryptionType() != EncryptionType.UNENCRYPTED;
    }

    public void changeEncryptionPassword(CharSequence charSequence, CharSequence charSequence2) {
        this.keyChainGroupLock.lock();
        try {
            decrypt(charSequence);
            encrypt(charSequence2);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public void changeEncryptionKey(KeyCrypter keyCrypter, KeyParameter keyParameter, KeyParameter keyParameter2) {
        this.keyChainGroupLock.lock();
        try {
            decrypt(keyParameter);
            encrypt(keyCrypter, keyParameter2);
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public List<C3509Key> serializeKeyChainGroupToProtobuf() {
        this.keyChainGroupLock.lock();
        try {
            return this.keyChainGroup.serializeToProtobuf();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveToFile(java.io.File r7, java.io.File r8) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "Temp file still exists after failed save."
            java.util.concurrent.locks.ReentrantLock r1 = r6.lock
            r1.lock()
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ RuntimeException -> 0x00ac }
            r2.<init>(r7)     // Catch:{ RuntimeException -> 0x00ac }
            r6.saveToFileStream(r2)     // Catch:{ RuntimeException -> 0x00a7, all -> 0x00a4 }
            r2.flush()     // Catch:{ RuntimeException -> 0x00a7, all -> 0x00a4 }
            java.io.FileDescriptor r3 = r2.getFD()     // Catch:{ RuntimeException -> 0x00a7, all -> 0x00a4 }
            r3.sync()     // Catch:{ RuntimeException -> 0x00a7, all -> 0x00a4 }
            r2.close()     // Catch:{ RuntimeException -> 0x00a7, all -> 0x00a4 }
            boolean r2 = org.bitcoinj.core.C3447Utils.isWindows()     // Catch:{ RuntimeException -> 0x00ac }
            java.lang.String r3 = " to "
            java.lang.String r4 = "Failed to rename "
            if (r2 == 0) goto L_0x0072
            java.io.File r8 = r8.getCanonicalFile()     // Catch:{ RuntimeException -> 0x00ac }
            boolean r2 = r8.exists()     // Catch:{ RuntimeException -> 0x00ac }
            if (r2 == 0) goto L_0x0040
            boolean r2 = r8.delete()     // Catch:{ RuntimeException -> 0x00ac }
            if (r2 == 0) goto L_0x0038
            goto L_0x0040
        L_0x0038:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ RuntimeException -> 0x00ac }
            java.lang.String r2 = "Failed to delete canonical wallet file for replacement with autosave"
            r8.<init>(r2)     // Catch:{ RuntimeException -> 0x00ac }
            throw r8     // Catch:{ RuntimeException -> 0x00ac }
        L_0x0040:
            boolean r2 = r7.renameTo(r8)     // Catch:{ RuntimeException -> 0x00ac }
            if (r2 == 0) goto L_0x0057
            java.util.concurrent.locks.ReentrantLock r8 = r6.lock
            r8.unlock()
            boolean r7 = r7.exists()
            if (r7 == 0) goto L_0x0056
            org.slf4j.Logger r7 = log
            r7.warn(r0)
        L_0x0056:
            return
        L_0x0057:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ RuntimeException -> 0x00ac }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x00ac }
            r5.<init>()     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r4)     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r7)     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r3)     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r8)     // Catch:{ RuntimeException -> 0x00ac }
            java.lang.String r8 = r5.toString()     // Catch:{ RuntimeException -> 0x00ac }
            r2.<init>(r8)     // Catch:{ RuntimeException -> 0x00ac }
            throw r2     // Catch:{ RuntimeException -> 0x00ac }
        L_0x0072:
            boolean r2 = r7.renameTo(r8)     // Catch:{ RuntimeException -> 0x00ac }
            if (r2 == 0) goto L_0x0089
            java.util.concurrent.locks.ReentrantLock r8 = r6.lock
            r8.unlock()
            boolean r7 = r7.exists()
            if (r7 == 0) goto L_0x0088
            org.slf4j.Logger r7 = log
            r7.warn(r0)
        L_0x0088:
            return
        L_0x0089:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ RuntimeException -> 0x00ac }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x00ac }
            r5.<init>()     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r4)     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r7)     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r3)     // Catch:{ RuntimeException -> 0x00ac }
            r5.append(r8)     // Catch:{ RuntimeException -> 0x00ac }
            java.lang.String r8 = r5.toString()     // Catch:{ RuntimeException -> 0x00ac }
            r2.<init>(r8)     // Catch:{ RuntimeException -> 0x00ac }
            throw r2     // Catch:{ RuntimeException -> 0x00ac }
        L_0x00a4:
            r8 = move-exception
            r1 = r2
            goto L_0x00b5
        L_0x00a7:
            r8 = move-exception
            r1 = r2
            goto L_0x00ad
        L_0x00aa:
            r8 = move-exception
            goto L_0x00b5
        L_0x00ac:
            r8 = move-exception
        L_0x00ad:
            org.slf4j.Logger r2 = log     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = "Failed whilst saving wallet"
            r2.error(r3, r8)     // Catch:{ all -> 0x00aa }
            throw r8     // Catch:{ all -> 0x00aa }
        L_0x00b5:
            java.util.concurrent.locks.ReentrantLock r2 = r6.lock
            r2.unlock()
            if (r1 == 0) goto L_0x00bf
            r1.close()
        L_0x00bf:
            boolean r7 = r7.exists()
            if (r7 == 0) goto L_0x00ca
            org.slf4j.Logger r7 = log
            r7.warn(r0)
        L_0x00ca:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.C3530Wallet.saveToFile(java.io.File, java.io.File):void");
    }

    public void saveToFile(File file) throws IOException {
        saveToFile(File.createTempFile("wallet", null, file.getAbsoluteFile().getParentFile()), file);
    }

    public void setAcceptRiskyTransactions(boolean z) {
        this.lock.lock();
        try {
            this.acceptRiskyTransactions = z;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isAcceptRiskyTransactions() {
        this.lock.lock();
        try {
            return this.acceptRiskyTransactions;
        } finally {
            this.lock.unlock();
        }
    }

    public void setRiskAnalyzer(Analyzer analyzer) {
        this.lock.lock();
        try {
            this.riskAnalyzer = (Analyzer) Preconditions.checkNotNull(analyzer);
        } finally {
            this.lock.unlock();
        }
    }

    public Analyzer getRiskAnalyzer() {
        this.lock.lock();
        try {
            return this.riskAnalyzer;
        } finally {
            this.lock.unlock();
        }
    }

    public WalletFiles autosaveToFile(File file, long j, TimeUnit timeUnit, @Nullable WalletFiles.Listener listener) {
        this.lock.lock();
        try {
            Preconditions.checkState(this.vFileManager == null, "Already auto saving this wallet.");
            WalletFiles walletFiles = new WalletFiles(this, file, j, timeUnit);
            if (listener != null) {
                walletFiles.setListener(listener);
            }
            this.vFileManager = walletFiles;
            return walletFiles;
        } finally {
            this.lock.unlock();
        }
    }

    public void shutdownAutosaveAndWait() {
        this.lock.lock();
        try {
            WalletFiles walletFiles = this.vFileManager;
            this.vFileManager = null;
            Preconditions.checkState(walletFiles != null, "Auto saving not enabled.");
            walletFiles.shutdownAndWait();
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void saveLater() {
        WalletFiles walletFiles = this.vFileManager;
        if (walletFiles != null) {
            walletFiles.saveLater();
        }
    }

    /* access modifiers changed from: protected */
    public void saveNow() {
        WalletFiles walletFiles = this.vFileManager;
        if (walletFiles != null) {
            try {
                walletFiles.saveNow();
            } catch (IOException e) {
                log.error("Failed to save wallet to disk!", (Throwable) e);
                UncaughtExceptionHandler uncaughtExceptionHandler = Threading.uncaughtExceptionHandler;
                if (uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e);
                }
            }
        }
    }

    public void saveToFileStream(OutputStream outputStream) throws IOException {
        this.lock.lock();
        try {
            new WalletProtobufSerializer().writeWallet(this, outputStream);
        } finally {
            this.lock.unlock();
        }
    }

    public NetworkParameters getParams() {
        return this.params;
    }

    public Context getContext() {
        return this.context;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0014 A[Catch:{ IOException -> 0x0018 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.bitcoinj.wallet.C3530Wallet loadFromFile(java.io.File r2, @javax.annotation.Nullable org.bitcoinj.wallet.WalletExtension... r3) throws org.bitcoinj.wallet.UnreadableWalletException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0011 }
            r1.<init>(r2)     // Catch:{ all -> 0x0011 }
            org.bitcoinj.wallet.Wallet r2 = loadFromFileStream(r1, r3)     // Catch:{ all -> 0x000e }
            r1.close()     // Catch:{ IOException -> 0x0018 }
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0017:
            throw r2     // Catch:{ IOException -> 0x0018 }
        L_0x0018:
            r2 = move-exception
            org.bitcoinj.wallet.UnreadableWalletException r3 = new org.bitcoinj.wallet.UnreadableWalletException
            java.lang.String r0 = "Could not open file"
            r3.<init>(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.C3530Wallet.loadFromFile(java.io.File, org.bitcoinj.wallet.WalletExtension[]):org.bitcoinj.wallet.Wallet");
    }

    public boolean isConsistent() {
        try {
            isConsistentOrThrow();
            return true;
        } catch (IllegalStateException e) {
            log.error(e.getMessage());
            try {
                log.error(toString());
            } catch (RuntimeException e2) {
                log.error("Printing inconsistent wallet failed", (Throwable) e2);
            }
            return false;
        }
    }

    public void isConsistentOrThrow() throws IllegalStateException {
        this.lock.lock();
        try {
            Set<Transaction> transactions2 = getTransactions(true);
            HashSet hashSet = new HashSet();
            for (Transaction hash : transactions2) {
                hashSet.add(hash.getHash());
            }
            int size = transactions2.size();
            if (size == hashSet.size()) {
                int size2 = this.unspent.size() + this.spent.size() + this.pending.size() + this.dead.size();
                if (size == size2) {
                    for (Transaction transaction : this.unspent.values()) {
                        if (!isTxConsistent(transaction, false)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Inconsistent unspent tx: ");
                            sb.append(transaction.getHashAsString());
                            throw new IllegalStateException(sb.toString());
                        }
                    }
                    for (Transaction transaction2 : this.spent.values()) {
                        if (!isTxConsistent(transaction2, true)) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Inconsistent spent tx: ");
                            sb2.append(transaction2.getHashAsString());
                            throw new IllegalStateException(sb2.toString());
                        }
                    }
                    return;
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Inconsistent wallet sizes: ");
                sb3.append(size);
                sb3.append(", ");
                sb3.append(size2);
                throw new IllegalStateException(sb3.toString());
            }
            throw new IllegalStateException("Two transactions with same hash");
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean isTxConsistent(Transaction transaction, boolean z) {
        boolean z2 = true;
        boolean z3 = true;
        for (TransactionOutput transactionOutput : transaction.getOutputs()) {
            String str = "isAvailableForSpending != spentBy";
            if (transactionOutput.isAvailableForSpending()) {
                if (transactionOutput.isMineOrWatched(this)) {
                    z3 = false;
                }
                if (transactionOutput.getSpentBy() != null) {
                    log.error(str);
                    return false;
                }
            } else if (transactionOutput.getSpentBy() == null) {
                log.error(str);
                return false;
            }
        }
        if (z3 != z) {
            z2 = false;
        }
        return z2;
    }

    public static C3530Wallet loadFromFileStream(InputStream inputStream, @Nullable WalletExtension... walletExtensionArr) throws UnreadableWalletException {
        C3530Wallet readWallet = new WalletProtobufSerializer().readWallet(inputStream, walletExtensionArr);
        if (!readWallet.isConsistent()) {
            log.error("Loaded an inconsistent wallet");
        }
        return readWallet;
    }

    public boolean notifyTransactionIsInBlock(Sha256Hash sha256Hash, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException {
        boolean z;
        this.lock.lock();
        try {
            Transaction transaction = (Transaction) this.transactions.get(sha256Hash);
            if (transaction == null) {
                transaction = (Transaction) this.riskDropped.get(sha256Hash);
                if (transaction != null) {
                    log.info("Risk analysis dropped tx {} but was included in block anyway", (Object) transaction.getHash());
                } else {
                    z = false;
                    return z;
                }
            }
            receive(transaction, storedBlock, newBlockType, i);
            z = true;
            return z;
        } finally {
            this.lock.unlock();
        }
    }

    public void receivePending(Transaction transaction, @Nullable List<Transaction> list, boolean z) throws VerificationException {
        this.lock.lock();
        try {
            transaction.verify();
            if (!getContainingPools(transaction).equals(EnumSet.noneOf(Pool.class))) {
                Logger logger = log;
                StringBuilder sb = new StringBuilder();
                sb.append("Received tx we already saw in a block or created ourselves: ");
                sb.append(transaction.getHashAsString());
                logger.debug(sb.toString());
            } else {
                if (!z) {
                    if (!isPendingTransactionRelevant(transaction)) {
                    }
                }
                if (!isTransactionRisky(transaction, list) || this.acceptRiskyTransactions) {
                    Coin valueSentToMe = transaction.getValueSentToMe(this);
                    Coin valueSentFromMe = transaction.getValueSentFromMe(this);
                    if (log.isInfoEnabled()) {
                        log.info(String.format(Locale.US, "Received a pending transaction %s that spends %s from our own wallet, and sends us %s", new Object[]{transaction.getHashAsString(), valueSentFromMe.toFriendlyString(), valueSentToMe.toFriendlyString()}));
                    }
                    if (transaction.getConfidence().getSource().equals(Source.UNKNOWN)) {
                        log.warn("Wallet received transaction with an unknown source. Consider tagging it!");
                    }
                    commitTx(transaction);
                    this.lock.unlock();
                    return;
                }
                this.riskDropped.put(transaction.getHash(), transaction);
                log.warn("There are now {} risk dropped transactions being kept in memory", (Object) Integer.valueOf(this.riskDropped.size()));
            }
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isTransactionRisky(Transaction transaction, @Nullable List<Transaction> list) {
        boolean z;
        this.lock.lock();
        if (list == null) {
            try {
                list = ImmutableList.m126of();
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
        RiskAnalysis create = this.riskAnalyzer.create(this, transaction, list);
        if (create.analyze() != Result.OK) {
            log.warn("Pending transaction was considered risky: {}\n{}", (Object) create, (Object) transaction);
            z = true;
        } else {
            z = false;
        }
        this.lock.unlock();
        return z;
    }

    public void receivePending(Transaction transaction, @Nullable List<Transaction> list) throws VerificationException {
        receivePending(transaction, list, false);
    }

    public boolean isPendingTransactionRelevant(Transaction transaction) throws ScriptException {
        this.lock.lock();
        try {
            if (!getContainingPools(transaction).equals(EnumSet.noneOf(Pool.class))) {
                Logger logger = log;
                StringBuilder sb = new StringBuilder();
                sb.append("Received tx we already saw in a block or created ourselves: ");
                sb.append(transaction.getHashAsString());
                logger.debug(sb.toString());
            } else if (!isTransactionRelevant(transaction)) {
                log.debug("Received tx that isn't relevant to this wallet, discarding.");
            } else {
                this.lock.unlock();
                return true;
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isTransactionRelevant(Transaction transaction) throws ScriptException {
        this.lock.lock();
        try {
            return transaction.getValueSentFromMe(this).signum() > 0 || transaction.getValueSentToMe(this).signum() > 0 || !findDoubleSpendsAgainst(transaction, this.transactions).isEmpty();
        } finally {
            this.lock.unlock();
        }
    }

    private Set<Transaction> findDoubleSpendsAgainst(Transaction transaction, Map<Sha256Hash, Transaction> map) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        if (transaction.isCoinBase()) {
            return Sets.newHashSet();
        }
        HashSet hashSet = new HashSet();
        for (TransactionInput outpoint : transaction.getInputs()) {
            hashSet.add(outpoint.getOutpoint());
        }
        HashSet newHashSet = Sets.newHashSet();
        for (Transaction transaction2 : map.values()) {
            for (TransactionInput outpoint2 : transaction2.getInputs()) {
                if (hashSet.contains(outpoint2.getOutpoint())) {
                    newHashSet.add(transaction2);
                }
            }
        }
        return newHashSet;
    }

    /* access modifiers changed from: 0000 */
    public void addTransactionsDependingOn(Set<Transaction> set, Set<Transaction> set2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Transaction transaction : set) {
            linkedHashMap.put(transaction.getHash(), transaction);
        }
        while (!linkedHashMap.isEmpty()) {
            Transaction transaction2 = (Transaction) linkedHashMap.remove(linkedHashMap.keySet().iterator().next());
            for (Transaction transaction3 : set2) {
                if (!transaction3.equals(transaction2)) {
                    for (TransactionInput outpoint : transaction3.getInputs()) {
                        if (outpoint.getOutpoint().getHash().equals(transaction2.getHash()) && linkedHashMap.get(transaction3.getHash()) == null) {
                            linkedHashMap.put(transaction3.getHash(), transaction3);
                            set.add(transaction3);
                        }
                    }
                }
            }
        }
    }

    public void receiveFromBlock(Transaction transaction, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException {
        this.lock.lock();
        try {
            if (isTransactionRelevant(transaction)) {
                receive(transaction, storedBlock, newBlockType, i);
                this.lock.unlock();
            }
        } finally {
            this.lock.unlock();
        }
    }

    private void receive(Transaction transaction, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Coin balance = getBalance();
        Sha256Hash hash = transaction.getHash();
        boolean z = newBlockType == NewBlockType.BEST_CHAIN;
        boolean z2 = newBlockType == NewBlockType.SIDE_CHAIN;
        Coin subtract = transaction.getValueSentToMe(this).subtract(transaction.getValueSentFromMe(this));
        Logger logger = log;
        Object[] objArr = new Object[5];
        objArr[0] = z2 ? " on a side chain" : "";
        objArr[1] = subtract.toFriendlyString();
        objArr[2] = transaction.getHashAsString();
        objArr[3] = Integer.valueOf(i);
        objArr[4] = storedBlock != null ? storedBlock.getHeader().getHash() : "(unit test)";
        logger.info("Received tx{} for {}: {} [{}] in block {}", objArr);
        markKeysAsUsed(transaction);
        this.onWalletChangedSuppressions++;
        Transaction transaction2 = (Transaction) this.transactions.get(transaction.getHash());
        if (transaction2 != null) {
            transaction = transaction2;
        }
        boolean z3 = this.pending.remove(hash) != null;
        if (z3) {
            log.info("  <-pending");
        }
        if (z) {
            boolean z4 = this.dead.remove(hash) != null;
            if (z4) {
                log.info("  <-dead");
            }
            if (z3) {
                for (TransactionOutput transactionOutput : transaction.getOutputs()) {
                    TransactionInput spentBy = transactionOutput.getSpentBy();
                    if (spentBy != null) {
                        Preconditions.checkState(this.myUnspents.add(transactionOutput));
                        spentBy.disconnect();
                    }
                }
            }
            processTxFromBestChain(transaction, z3 || z4);
        } else {
            Preconditions.checkState(z2);
            if (z3) {
                addWalletTransaction(Pool.PENDING, transaction);
                log.info("  ->pending");
            } else {
                Sha256Hash hash2 = transaction.getHash();
                if (!this.unspent.containsKey(hash2) && !this.spent.containsKey(hash2) && !this.dead.containsKey(hash2)) {
                    commitTx(transaction);
                }
            }
        }
        if (storedBlock != null) {
            transaction.setBlockAppearance(storedBlock, z, i);
            if (z) {
                this.ignoreNextNewBlock.add(hash);
                HashSet newHashSet = Sets.newHashSet((E[]) new Transaction[]{transaction});
                addTransactionsDependingOn(newHashSet, getTransactions(true));
                newHashSet.remove(transaction);
                for (Transaction transaction3 : sortTxnsByDependency(newHashSet)) {
                    if (transaction3.getConfidence().getConfidenceType().equals(ConfidenceType.IN_CONFLICT) && isNotSpendingTxnsInConfidenceType(transaction3, ConfidenceType.IN_CONFLICT)) {
                        transaction3.getConfidence().setConfidenceType(ConfidenceType.PENDING);
                        this.confidenceChanged.put(transaction3, ChangeReason.TYPE);
                    }
                }
            }
        }
        this.onWalletChangedSuppressions--;
        if (z) {
            this.confidenceChanged.put(transaction, ChangeReason.TYPE);
        } else {
            maybeQueueOnWalletChanged();
        }
        if (!this.insideReorg && z) {
            Coin balance2 = getBalance();
            Logger logger2 = log;
            StringBuilder sb = new StringBuilder();
            sb.append("Balance is now: ");
            sb.append(balance2.toFriendlyString());
            logger2.info(sb.toString());
            if (!z3) {
                int signum = subtract.signum();
                if (signum > 0) {
                    queueOnCoinsReceived(transaction, balance, balance2);
                } else if (signum < 0) {
                    queueOnCoinsSent(transaction, balance, balance2);
                }
            }
            checkBalanceFuturesLocked(balance2);
        }
        informConfidenceListenersIfNotReorganizing();
        isConsistentOrThrow();
        saveLater();
        this.hardSaveOnNextBlock = true;
    }

    private boolean isNotSpendingTxnsInConfidenceType(Transaction transaction, ConfidenceType confidenceType) {
        for (TransactionInput outpoint : transaction.getInputs()) {
            Transaction transaction2 = getTransaction(outpoint.getOutpoint().getHash());
            if (transaction2 != null && transaction2.getConfidence().getConfidenceType().equals(confidenceType)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public List<Transaction> sortTxnsByDependency(Set<Transaction> set) {
        int i;
        boolean z;
        ArrayList arrayList = new ArrayList(set);
        int i2 = 0;
        while (i2 < arrayList.size() - 1) {
            do {
                i = i2 + 1;
                int i3 = i;
                while (true) {
                    if (i3 >= arrayList.size()) {
                        z = false;
                        continue;
                        break;
                    } else if (spends((Transaction) arrayList.get(i2), (Transaction) arrayList.get(i3))) {
                        arrayList.add(i3, (Transaction) arrayList.remove(i2));
                        z = true;
                        continue;
                        break;
                    } else {
                        i3++;
                    }
                }
            } while (z);
            i2 = i;
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public boolean spends(Transaction transaction, Transaction transaction2) {
        for (TransactionInput outpoint : transaction.getInputs()) {
            if (outpoint.getOutpoint().getHash().equals(transaction2.getHash())) {
                return true;
            }
        }
        return false;
    }

    private void informConfidenceListenersIfNotReorganizing() {
        if (!this.insideReorg) {
            for (Entry entry : this.confidenceChanged.entrySet()) {
                Transaction transaction = (Transaction) entry.getKey();
                transaction.getConfidence().queueListeners((ChangeReason) entry.getValue());
                queueOnTransactionConfidenceChanged(transaction);
            }
            this.confidenceChanged.clear();
        }
    }

    public void notifyNewBestBlock(StoredBlock storedBlock) throws VerificationException {
        Sha256Hash hash = storedBlock.getHeader().getHash();
        if (!hash.equals(getLastBlockSeenHash())) {
            this.lock.lock();
            try {
                setLastBlockSeenHash(hash);
                setLastBlockSeenHeight(storedBlock.getHeight());
                setLastBlockSeenTimeSecs(storedBlock.getHeader().getTimeSeconds());
                for (Transaction transaction : getTransactions(true)) {
                    if (this.ignoreNextNewBlock.contains(transaction.getHash())) {
                        this.ignoreNextNewBlock.remove(transaction.getHash());
                    } else {
                        TransactionConfidence confidence = transaction.getConfidence();
                        if (confidence.getConfidenceType() == ConfidenceType.BUILDING) {
                            if (confidence.incrementDepthInBlocks() > this.context.getEventHorizon()) {
                                confidence.clearBroadcastBy();
                            }
                            this.confidenceChanged.put(transaction, ChangeReason.DEPTH);
                        }
                    }
                }
                informConfidenceListenersIfNotReorganizing();
                maybeQueueOnWalletChanged();
                if (this.hardSaveOnNextBlock) {
                    saveNow();
                    this.hardSaveOnNextBlock = false;
                } else {
                    saveLater();
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    private void processTxFromBestChain(Transaction transaction, boolean z) throws VerificationException {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        boolean z2 = true;
        Preconditions.checkState(!this.pending.containsKey(transaction.getHash()));
        if (transaction.isCoinBase() && this.dead.containsKey(transaction.getHash())) {
            log.info("  coinbase tx <-dead: confidence {}", (Object) transaction.getHashAsString(), (Object) transaction.getConfidence().getConfidenceType().name());
            this.dead.remove(transaction.getHash());
        }
        updateForSpends(transaction, true);
        if (transaction.getValueSentToMe(this).signum() <= 0) {
            z2 = false;
        }
        if (z2) {
            if (transaction.isEveryOwnedOutputSpent(this)) {
                log.info("  tx {} ->spent (by pending)", (Object) transaction.getHashAsString());
                addWalletTransaction(Pool.SPENT, transaction);
            } else {
                log.info("  tx {} ->unspent", (Object) transaction.getHashAsString());
                addWalletTransaction(Pool.UNSPENT, transaction);
            }
        } else if (transaction.getValueSentFromMe(this).signum() > 0) {
            log.info("  tx {} ->spent", (Object) transaction.getHashAsString());
            addWalletTransaction(Pool.SPENT, transaction);
        } else if (z) {
            log.info("  tx {} ->spent (manually added)", (Object) transaction.getHashAsString());
            addWalletTransaction(Pool.SPENT, transaction);
        }
        Set findDoubleSpendsAgainst = findDoubleSpendsAgainst(transaction, this.pending);
        if (!findDoubleSpendsAgainst.isEmpty()) {
            killTxns(findDoubleSpendsAgainst, transaction);
        }
    }

    private void updateForSpends(Transaction transaction, boolean z) throws VerificationException {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        if (z) {
            Preconditions.checkState(!this.pending.containsKey(transaction.getHash()));
        }
        for (TransactionInput transactionInput : transaction.getInputs()) {
            ConnectionResult connect = transactionInput.connect(this.unspent, ConnectMode.ABORT_ON_CONFLICT);
            if (connect == ConnectionResult.NO_SUCH_TX) {
                connect = transactionInput.connect(this.spent, ConnectMode.ABORT_ON_CONFLICT);
                if (connect == ConnectionResult.NO_SUCH_TX) {
                    connect = transactionInput.connect(this.pending, ConnectMode.ABORT_ON_CONFLICT);
                    if (connect == ConnectionResult.NO_SUCH_TX) {
                    }
                }
            }
            TransactionOutput transactionOutput = (TransactionOutput) Preconditions.checkNotNull(transactionInput.getConnectedOutput());
            if (connect == ConnectionResult.ALREADY_SPENT) {
                if (!z) {
                    log.warn("Saw two pending transactions double spend each other");
                    log.warn("  offending input is input {}", (Object) Integer.valueOf(transaction.getInputs().indexOf(transactionInput)));
                    String str = "{}: {}";
                    log.warn(str, (Object) transaction.getHash(), (Object) C3447Utils.HEX.encode(transaction.unsafeBitcoinSerialize()));
                    Transaction parentTransaction = transactionOutput.getSpentBy().getParentTransaction();
                    log.warn(str, (Object) parentTransaction.getHash(), (Object) C3447Utils.HEX.encode(parentTransaction.unsafeBitcoinSerialize()));
                }
            } else if (connect == ConnectionResult.SUCCESS) {
                Transaction transaction2 = (Transaction) Preconditions.checkNotNull(transactionInput.getConnectedTransaction());
                log.info("  marked {} as spent by {}", (Object) transactionInput.getOutpoint(), (Object) transaction.getHashAsString());
                maybeMovePool(transaction2, "prevtx");
                if (transactionOutput.isMineOrWatched(this)) {
                    Preconditions.checkState(this.myUnspents.remove(transactionOutput));
                }
            }
        }
        for (Transaction transaction3 : this.pending.values()) {
            for (TransactionInput transactionInput2 : transaction3.getInputs()) {
                ConnectionResult connect2 = transactionInput2.connect(transaction, ConnectMode.ABORT_ON_CONFLICT);
                if (z) {
                    Preconditions.checkState(connect2 != ConnectionResult.ALREADY_SPENT);
                }
                if (connect2 == ConnectionResult.SUCCESS) {
                    log.info("Connected pending tx input {}:{}", (Object) transaction3.getHashAsString(), (Object) Integer.valueOf(transaction3.getInputs().indexOf(transactionInput2)));
                    if (this.myUnspents.remove(transactionInput2.getConnectedOutput())) {
                        log.info("Removed from UNSPENTS: {}", (Object) transactionInput2.getConnectedOutput());
                    }
                }
            }
        }
        if (!z) {
            maybeMovePool(transaction, "pendingtx");
        }
    }

    private void killTxns(Set<Transaction> set, @Nullable Transaction transaction) {
        String str;
        String str2;
        LinkedList linkedList = new LinkedList(set);
        while (true) {
            str = "kill";
            if (linkedList.isEmpty()) {
                break;
            }
            Transaction transaction2 = (Transaction) linkedList.poll();
            Logger logger = log;
            String hashAsString = transaction2.getHashAsString();
            if (transaction != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(" by ");
                sb.append(transaction.getHashAsString());
                str2 = sb.toString();
            } else {
                str2 = "";
            }
            logger.warn("TX {} killed{}", (Object) hashAsString, (Object) str2);
            log.warn("Disconnecting each input and moving connected transactions.");
            this.pending.remove(transaction2.getHash());
            this.unspent.remove(transaction2.getHash());
            this.spent.remove(transaction2.getHash());
            addWalletTransaction(Pool.DEAD, transaction2);
            for (TransactionInput transactionInput : transaction2.getInputs()) {
                Transaction connectedTransaction = transactionInput.getConnectedTransaction();
                if (connectedTransaction != null) {
                    if (!(connectedTransaction.getConfidence().getConfidenceType() == ConfidenceType.DEAD || transactionInput.getConnectedOutput().getSpentBy() == null || !transactionInput.getConnectedOutput().getSpentBy().equals(transactionInput))) {
                        Preconditions.checkState(this.myUnspents.add(transactionInput.getConnectedOutput()));
                        log.info("Added to UNSPENTS: {} in {}", (Object) transactionInput.getConnectedOutput(), (Object) transactionInput.getConnectedOutput().getParentTransaction().getHash());
                    }
                    transactionInput.disconnect();
                    maybeMovePool(connectedTransaction, str);
                }
            }
            transaction2.getConfidence().setOverridingTransaction(transaction);
            this.confidenceChanged.put(transaction2, ChangeReason.TYPE);
            for (TransactionOutput transactionOutput : transaction2.getOutputs()) {
                if (this.myUnspents.remove(transactionOutput)) {
                    log.info("XX Removed from UNSPENTS: {}", (Object) transactionOutput);
                }
                TransactionInput spentBy = transactionOutput.getSpentBy();
                if (spentBy != null) {
                    Transaction parentTransaction = spentBy.getParentTransaction();
                    log.info("This death invalidated dependent tx {}", (Object) parentTransaction.getHash());
                    linkedList.push(parentTransaction);
                }
            }
        }
        if (transaction != null) {
            log.warn("Now attempting to connect the inputs of the overriding transaction.");
            for (TransactionInput transactionInput2 : transaction.getInputs()) {
                String str3 = "Removing from UNSPENTS: {}";
                if (transactionInput2.connect(this.unspent, ConnectMode.DISCONNECT_ON_CONFLICT) == ConnectionResult.SUCCESS) {
                    maybeMovePool(transactionInput2.getConnectedTransaction(), str);
                    this.myUnspents.remove(transactionInput2.getConnectedOutput());
                    log.info(str3, (Object) transactionInput2.getConnectedOutput());
                } else if (transactionInput2.connect(this.spent, ConnectMode.DISCONNECT_ON_CONFLICT) == ConnectionResult.SUCCESS) {
                    maybeMovePool(transactionInput2.getConnectedTransaction(), str);
                    this.myUnspents.remove(transactionInput2.getConnectedOutput());
                    log.info(str3, (Object) transactionInput2.getConnectedOutput());
                }
            }
        }
    }

    private void maybeMovePool(Transaction transaction, String str) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        if (transaction.isEveryOwnedOutputSpent(this)) {
            if (this.unspent.remove(transaction.getHash()) != null) {
                if (log.isInfoEnabled()) {
                    log.info("  {} {} <-unspent ->spent", (Object) transaction.getHashAsString(), (Object) str);
                }
                this.spent.put(transaction.getHash(), transaction);
            }
        } else if (this.spent.remove(transaction.getHash()) != null) {
            if (log.isInfoEnabled()) {
                log.info("  {} {} <-spent ->unspent", (Object) transaction.getHashAsString(), (Object) str);
            }
            this.unspent.put(transaction.getHash(), transaction);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0115 A[Catch:{ ScriptException -> 0x015d, all -> 0x0164 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x013b A[Catch:{ ScriptException -> 0x015d, all -> 0x0164 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0148 A[Catch:{ ScriptException -> 0x015d, all -> 0x0164 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean maybeCommitTx(org.bitcoinj.core.Transaction r8) throws org.bitcoinj.core.VerificationException {
        /*
            r7 = this;
            r8.verify()
            java.util.concurrent.locks.ReentrantLock r0 = r7.lock
            r0.lock()
            java.util.Map<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.Transaction> r0 = r7.pending     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.Sha256Hash r1 = r8.getHash()     // Catch:{ all -> 0x0164 }
            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x0164 }
            r1 = 0
            if (r0 == 0) goto L_0x001b
            java.util.concurrent.locks.ReentrantLock r8 = r7.lock
            r8.unlock()
            return r1
        L_0x001b:
            org.slf4j.Logger r0 = log     // Catch:{ all -> 0x0164 }
            java.lang.String r2 = "commitTx of {}"
            java.lang.String r3 = r8.getHashAsString()     // Catch:{ all -> 0x0164 }
            r0.info(r2, r3)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.Coin r0 = r7.getBalance()     // Catch:{ all -> 0x0164 }
            java.util.Date r2 = org.bitcoinj.core.C3447Utils.now()     // Catch:{ all -> 0x0164 }
            r8.setUpdateTime(r2)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.Coin r2 = org.bitcoinj.core.Coin.ZERO     // Catch:{ all -> 0x0164 }
            java.util.List r3 = r8.getOutputs()     // Catch:{ all -> 0x0164 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0164 }
        L_0x003b:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0164 }
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionOutput r4 = (org.bitcoinj.core.TransactionOutput) r4     // Catch:{ all -> 0x0164 }
            boolean r5 = r4.isMineOrWatched(r7)     // Catch:{ all -> 0x0164 }
            if (r5 != 0) goto L_0x004e
            goto L_0x003b
        L_0x004e:
            org.bitcoinj.core.Coin r4 = r4.getValue()     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.Coin r2 = r2.add(r4)     // Catch:{ all -> 0x0164 }
            goto L_0x003b
        L_0x0057:
            r7.updateForSpends(r8, r1)     // Catch:{ all -> 0x0164 }
            java.util.Map<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.Transaction> r1 = r7.pending     // Catch:{ all -> 0x0164 }
            java.util.Set r1 = r7.findDoubleSpendsAgainst(r8, r1)     // Catch:{ all -> 0x0164 }
            java.util.Map<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.Transaction> r3 = r7.unspent     // Catch:{ all -> 0x0164 }
            java.util.Set r3 = r7.findDoubleSpendsAgainst(r8, r3)     // Catch:{ all -> 0x0164 }
            java.util.Map<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.Transaction> r4 = r7.spent     // Catch:{ all -> 0x0164 }
            java.util.Set r4 = r7.findDoubleSpendsAgainst(r8, r4)     // Catch:{ all -> 0x0164 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0164 }
            r5 = 1
            if (r3 == 0) goto L_0x00ed
            boolean r3 = r4.isEmpty()     // Catch:{ all -> 0x0164 }
            if (r3 == 0) goto L_0x00ed
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r3 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.DEAD     // Catch:{ all -> 0x0164 }
            boolean r3 = r7.isNotSpendingTxnsInConfidenceType(r8, r3)     // Catch:{ all -> 0x0164 }
            if (r3 != 0) goto L_0x0082
            goto L_0x00ed
        L_0x0082:
            boolean r3 = r1.isEmpty()     // Catch:{ all -> 0x0164 }
            if (r3 == 0) goto L_0x00b2
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r3 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.IN_CONFLICT     // Catch:{ all -> 0x0164 }
            boolean r3 = r7.isNotSpendingTxnsInConfidenceType(r8, r3)     // Catch:{ all -> 0x0164 }
            if (r3 != 0) goto L_0x0091
            goto L_0x00b2
        L_0x0091:
            org.slf4j.Logger r1 = log     // Catch:{ all -> 0x0164 }
            java.lang.String r3 = "->pending: {}"
            java.lang.String r4 = r8.getHashAsString()     // Catch:{ all -> 0x0164 }
            r1.info(r3, r4)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence r1 = r8.getConfidence()     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r3 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.PENDING     // Catch:{ all -> 0x0164 }
            r1.setConfidenceType(r3)     // Catch:{ all -> 0x0164 }
            java.util.Map<org.bitcoinj.core.Transaction, org.bitcoinj.core.TransactionConfidence$Listener$ChangeReason> r1 = r7.confidenceChanged     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence$Listener$ChangeReason r3 = org.bitcoinj.core.TransactionConfidence.Listener.ChangeReason.TYPE     // Catch:{ all -> 0x0164 }
            r1.put(r8, r3)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.wallet.WalletTransaction$Pool r1 = org.bitcoinj.wallet.WalletTransaction.Pool.PENDING     // Catch:{ all -> 0x0164 }
            r7.addWalletTransaction(r1, r8)     // Catch:{ all -> 0x0164 }
            goto L_0x010d
        L_0x00b2:
            org.slf4j.Logger r3 = log     // Catch:{ all -> 0x0164 }
            java.lang.String r4 = "->pending (IN_CONFLICT): {}"
            java.lang.String r6 = r8.getHashAsString()     // Catch:{ all -> 0x0164 }
            r3.info(r4, r6)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.wallet.WalletTransaction$Pool r3 = org.bitcoinj.wallet.WalletTransaction.Pool.PENDING     // Catch:{ all -> 0x0164 }
            r7.addWalletTransaction(r3, r8)     // Catch:{ all -> 0x0164 }
            r1.add(r8)     // Catch:{ all -> 0x0164 }
            java.util.Set r3 = r7.getTransactions(r5)     // Catch:{ all -> 0x0164 }
            r7.addTransactionsDependingOn(r1, r3)     // Catch:{ all -> 0x0164 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0164 }
        L_0x00d0:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0164 }
            if (r3 == 0) goto L_0x010d
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.Transaction r3 = (org.bitcoinj.core.Transaction) r3     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence r4 = r3.getConfidence()     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r6 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.IN_CONFLICT     // Catch:{ all -> 0x0164 }
            r4.setConfidenceType(r6)     // Catch:{ all -> 0x0164 }
            java.util.Map<org.bitcoinj.core.Transaction, org.bitcoinj.core.TransactionConfidence$Listener$ChangeReason> r4 = r7.confidenceChanged     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence$Listener$ChangeReason r6 = org.bitcoinj.core.TransactionConfidence.Listener.ChangeReason.TYPE     // Catch:{ all -> 0x0164 }
            r4.put(r3, r6)     // Catch:{ all -> 0x0164 }
            goto L_0x00d0
        L_0x00ed:
            org.slf4j.Logger r1 = log     // Catch:{ all -> 0x0164 }
            java.lang.String r3 = "->dead: {}"
            java.lang.String r4 = r8.getHashAsString()     // Catch:{ all -> 0x0164 }
            r1.info(r3, r4)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence r1 = r8.getConfidence()     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence$ConfidenceType r3 = org.bitcoinj.core.TransactionConfidence.ConfidenceType.DEAD     // Catch:{ all -> 0x0164 }
            r1.setConfidenceType(r3)     // Catch:{ all -> 0x0164 }
            java.util.Map<org.bitcoinj.core.Transaction, org.bitcoinj.core.TransactionConfidence$Listener$ChangeReason> r1 = r7.confidenceChanged     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.TransactionConfidence$Listener$ChangeReason r3 = org.bitcoinj.core.TransactionConfidence.Listener.ChangeReason.TYPE     // Catch:{ all -> 0x0164 }
            r1.put(r8, r3)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.wallet.WalletTransaction$Pool r1 = org.bitcoinj.wallet.WalletTransaction.Pool.DEAD     // Catch:{ all -> 0x0164 }
            r7.addWalletTransaction(r1, r8)     // Catch:{ all -> 0x0164 }
        L_0x010d:
            org.slf4j.Logger r1 = log     // Catch:{ all -> 0x0164 }
            boolean r1 = r1.isInfoEnabled()     // Catch:{ all -> 0x0164 }
            if (r1 == 0) goto L_0x0126
            org.slf4j.Logger r1 = log     // Catch:{ all -> 0x0164 }
            java.lang.String r3 = "Estimated balance is now: {}"
            org.bitcoinj.wallet.Wallet$BalanceType r4 = org.bitcoinj.wallet.C3530Wallet.BalanceType.ESTIMATED     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.Coin r4 = r7.getBalance(r4)     // Catch:{ all -> 0x0164 }
            java.lang.String r4 = r4.toFriendlyString()     // Catch:{ all -> 0x0164 }
            r1.info(r3, r4)     // Catch:{ all -> 0x0164 }
        L_0x0126:
            r7.markKeysAsUsed(r8)     // Catch:{ all -> 0x0164 }
            org.bitcoinj.core.Coin r1 = r8.getValueSentFromMe(r7)     // Catch:{ ScriptException -> 0x015d }
            org.bitcoinj.core.Coin r3 = r0.add(r2)     // Catch:{ ScriptException -> 0x015d }
            org.bitcoinj.core.Coin r3 = r3.subtract(r1)     // Catch:{ ScriptException -> 0x015d }
            int r2 = r2.signum()     // Catch:{ ScriptException -> 0x015d }
            if (r2 <= 0) goto L_0x0142
            r2 = 0
            r7.checkBalanceFuturesLocked(r2)     // Catch:{ ScriptException -> 0x015d }
            r7.queueOnCoinsReceived(r8, r0, r3)     // Catch:{ ScriptException -> 0x015d }
        L_0x0142:
            int r1 = r1.signum()     // Catch:{ ScriptException -> 0x015d }
            if (r1 <= 0) goto L_0x014b
            r7.queueOnCoinsSent(r8, r0, r3)     // Catch:{ ScriptException -> 0x015d }
        L_0x014b:
            r7.maybeQueueOnWalletChanged()     // Catch:{ ScriptException -> 0x015d }
            r7.isConsistentOrThrow()     // Catch:{ all -> 0x0164 }
            r7.informConfidenceListenersIfNotReorganizing()     // Catch:{ all -> 0x0164 }
            r7.saveNow()     // Catch:{ all -> 0x0164 }
            java.util.concurrent.locks.ReentrantLock r8 = r7.lock
            r8.unlock()
            return r5
        L_0x015d:
            r8 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0164 }
            r0.<init>(r8)     // Catch:{ all -> 0x0164 }
            throw r0     // Catch:{ all -> 0x0164 }
        L_0x0164:
            r8 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = r7.lock
            r0.unlock()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.C3530Wallet.maybeCommitTx(org.bitcoinj.core.Transaction):boolean");
    }

    public void commitTx(Transaction transaction) throws VerificationException {
        Preconditions.checkArgument(maybeCommitTx(transaction), "commitTx called on the same transaction twice");
    }

    public void addEventListener(WalletEventListener walletEventListener) {
        addChangeEventListener(Threading.USER_THREAD, walletEventListener);
        addCoinsReceivedEventListener(Threading.USER_THREAD, walletEventListener);
        addCoinsSentEventListener(Threading.USER_THREAD, walletEventListener);
        addKeyChainEventListener(Threading.USER_THREAD, walletEventListener);
        addReorganizeEventListener(Threading.USER_THREAD, walletEventListener);
        addScriptChangeEventListener(Threading.USER_THREAD, walletEventListener);
        addTransactionConfidenceEventListener(Threading.USER_THREAD, walletEventListener);
    }

    @Deprecated
    public void addEventListener(WalletEventListener walletEventListener, Executor executor) {
        addCoinsReceivedEventListener(executor, walletEventListener);
        addCoinsSentEventListener(executor, walletEventListener);
        addChangeEventListener(executor, walletEventListener);
        addKeyChainEventListener(executor, walletEventListener);
        addReorganizeEventListener(executor, walletEventListener);
        addScriptChangeEventListener(executor, walletEventListener);
        addTransactionConfidenceEventListener(executor, walletEventListener);
    }

    public void addChangeEventListener(WalletChangeEventListener walletChangeEventListener) {
        addChangeEventListener(Threading.USER_THREAD, walletChangeEventListener);
    }

    public void addChangeEventListener(Executor executor, WalletChangeEventListener walletChangeEventListener) {
        this.changeListeners.add(new ListenerRegistration(walletChangeEventListener, executor));
    }

    public void addCoinsReceivedEventListener(WalletCoinsReceivedEventListener walletCoinsReceivedEventListener) {
        addCoinsReceivedEventListener(Threading.USER_THREAD, walletCoinsReceivedEventListener);
    }

    public void addCoinsReceivedEventListener(Executor executor, WalletCoinsReceivedEventListener walletCoinsReceivedEventListener) {
        this.coinsReceivedListeners.add(new ListenerRegistration(walletCoinsReceivedEventListener, executor));
    }

    public void addCoinsSentEventListener(WalletCoinsSentEventListener walletCoinsSentEventListener) {
        addCoinsSentEventListener(Threading.USER_THREAD, walletCoinsSentEventListener);
    }

    public void addCoinsSentEventListener(Executor executor, WalletCoinsSentEventListener walletCoinsSentEventListener) {
        this.coinsSentListeners.add(new ListenerRegistration(walletCoinsSentEventListener, executor));
    }

    public void addKeyChainEventListener(KeyChainEventListener keyChainEventListener) {
        this.keyChainGroup.addEventListener(keyChainEventListener, Threading.USER_THREAD);
    }

    public void addKeyChainEventListener(Executor executor, KeyChainEventListener keyChainEventListener) {
        this.keyChainGroup.addEventListener(keyChainEventListener, executor);
    }

    public void addReorganizeEventListener(WalletReorganizeEventListener walletReorganizeEventListener) {
        addReorganizeEventListener(Threading.USER_THREAD, walletReorganizeEventListener);
    }

    public void addReorganizeEventListener(Executor executor, WalletReorganizeEventListener walletReorganizeEventListener) {
        this.reorganizeListeners.add(new ListenerRegistration(walletReorganizeEventListener, executor));
    }

    public void addScriptsChangeEventListener(ScriptsChangeEventListener scriptsChangeEventListener) {
        addScriptChangeEventListener(Threading.USER_THREAD, scriptsChangeEventListener);
    }

    public void addScriptChangeEventListener(Executor executor, ScriptsChangeEventListener scriptsChangeEventListener) {
        this.scriptChangeListeners.add(new ListenerRegistration(scriptsChangeEventListener, executor));
    }

    public void addTransactionConfidenceEventListener(TransactionConfidenceEventListener transactionConfidenceEventListener) {
        addTransactionConfidenceEventListener(Threading.USER_THREAD, transactionConfidenceEventListener);
    }

    public void addTransactionConfidenceEventListener(Executor executor, TransactionConfidenceEventListener transactionConfidenceEventListener) {
        this.transactionConfidenceListeners.add(new ListenerRegistration(transactionConfidenceEventListener, executor));
    }

    @Deprecated
    public boolean removeEventListener(WalletEventListener walletEventListener) {
        return removeChangeEventListener(walletEventListener) || removeCoinsReceivedEventListener(walletEventListener) || removeCoinsSentEventListener(walletEventListener) || removeKeyChainEventListener(walletEventListener) || removeReorganizeEventListener(walletEventListener) || removeTransactionConfidenceEventListener(walletEventListener);
    }

    public boolean removeChangeEventListener(WalletChangeEventListener walletChangeEventListener) {
        return ListenerRegistration.removeFromList(walletChangeEventListener, this.changeListeners);
    }

    public boolean removeCoinsReceivedEventListener(WalletCoinsReceivedEventListener walletCoinsReceivedEventListener) {
        return ListenerRegistration.removeFromList(walletCoinsReceivedEventListener, this.coinsReceivedListeners);
    }

    public boolean removeCoinsSentEventListener(WalletCoinsSentEventListener walletCoinsSentEventListener) {
        return ListenerRegistration.removeFromList(walletCoinsSentEventListener, this.coinsSentListeners);
    }

    public boolean removeKeyChainEventListener(KeyChainEventListener keyChainEventListener) {
        return this.keyChainGroup.removeEventListener(keyChainEventListener);
    }

    public boolean removeReorganizeEventListener(WalletReorganizeEventListener walletReorganizeEventListener) {
        return ListenerRegistration.removeFromList(walletReorganizeEventListener, this.reorganizeListeners);
    }

    public boolean removeScriptChangeEventListener(ScriptsChangeEventListener scriptsChangeEventListener) {
        return ListenerRegistration.removeFromList(scriptsChangeEventListener, this.scriptChangeListeners);
    }

    public boolean removeTransactionConfidenceEventListener(TransactionConfidenceEventListener transactionConfidenceEventListener) {
        return ListenerRegistration.removeFromList(transactionConfidenceEventListener, this.transactionConfidenceListeners);
    }

    /* access modifiers changed from: private */
    public void queueOnTransactionConfidenceChanged(final Transaction transaction) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Iterator it = this.transactionConfidenceListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            if (listenerRegistration.executor == Threading.SAME_THREAD) {
                ((TransactionConfidenceEventListener) listenerRegistration.listener).onTransactionConfidenceChanged(this, transaction);
            } else {
                listenerRegistration.executor.execute(new Runnable() {
                    public void run() {
                        ((TransactionConfidenceEventListener) listenerRegistration.listener).onTransactionConfidenceChanged(C3530Wallet.this, transaction);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void maybeQueueOnWalletChanged() {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Preconditions.checkState(this.onWalletChangedSuppressions >= 0);
        if (this.onWalletChangedSuppressions <= 0) {
            Iterator it = this.changeListeners.iterator();
            while (it.hasNext()) {
                final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
                listenerRegistration.executor.execute(new Runnable() {
                    public void run() {
                        ((WalletChangeEventListener) listenerRegistration.listener).onWalletChanged(C3530Wallet.this);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void queueOnCoinsReceived(Transaction transaction, Coin coin, Coin coin2) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Iterator it = this.coinsReceivedListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            Executor executor = listenerRegistration.executor;
            final Transaction transaction2 = transaction;
            final Coin coin3 = coin;
            final Coin coin4 = coin2;
            C35375 r2 = new Runnable() {
                public void run() {
                    ((WalletCoinsReceivedEventListener) listenerRegistration.listener).onCoinsReceived(C3530Wallet.this, transaction2, coin3, coin4);
                }
            };
            executor.execute(r2);
        }
    }

    /* access modifiers changed from: protected */
    public void queueOnCoinsSent(Transaction transaction, Coin coin, Coin coin2) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Iterator it = this.coinsSentListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            Executor executor = listenerRegistration.executor;
            final Transaction transaction2 = transaction;
            final Coin coin3 = coin;
            final Coin coin4 = coin2;
            C35386 r2 = new Runnable() {
                public void run() {
                    ((WalletCoinsSentEventListener) listenerRegistration.listener).onCoinsSent(C3530Wallet.this, transaction2, coin3, coin4);
                }
            };
            executor.execute(r2);
        }
    }

    /* access modifiers changed from: protected */
    public void queueOnReorganize() {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Preconditions.checkState(this.insideReorg);
        Iterator it = this.reorganizeListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            listenerRegistration.executor.execute(new Runnable() {
                public void run() {
                    ((WalletReorganizeEventListener) listenerRegistration.listener).onReorganize(C3530Wallet.this);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void queueOnScriptsChanged(final List<Script> list, final boolean z) {
        Iterator it = this.scriptChangeListeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            listenerRegistration.executor.execute(new Runnable() {
                public void run() {
                    ((ScriptsChangeEventListener) listenerRegistration.listener).onScriptsChanged(C3530Wallet.this, list, z);
                }
            });
        }
    }

    public Set<Transaction> getTransactions(boolean z) {
        this.lock.lock();
        try {
            HashSet hashSet = new HashSet();
            hashSet.addAll(this.unspent.values());
            hashSet.addAll(this.spent.values());
            hashSet.addAll(this.pending.values());
            if (z) {
                hashSet.addAll(this.dead.values());
            }
            return hashSet;
        } finally {
            this.lock.unlock();
        }
    }

    public Iterable<WalletTransaction> getWalletTransactions() {
        this.lock.lock();
        try {
            HashSet hashSet = new HashSet();
            addWalletTransactionsToSet(hashSet, Pool.UNSPENT, this.unspent.values());
            addWalletTransactionsToSet(hashSet, Pool.SPENT, this.spent.values());
            addWalletTransactionsToSet(hashSet, Pool.DEAD, this.dead.values());
            addWalletTransactionsToSet(hashSet, Pool.PENDING, this.pending.values());
            return hashSet;
        } finally {
            this.lock.unlock();
        }
    }

    private static void addWalletTransactionsToSet(Set<WalletTransaction> set, Pool pool, Collection<Transaction> collection) {
        for (Transaction walletTransaction : collection) {
            set.add(new WalletTransaction(pool, walletTransaction));
        }
    }

    public void addWalletTransaction(WalletTransaction walletTransaction) {
        this.lock.lock();
        try {
            addWalletTransaction(walletTransaction.getPool(), walletTransaction.getTransaction());
        } finally {
            this.lock.unlock();
        }
    }

    private void addWalletTransaction(Pool pool, Transaction transaction) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        this.transactions.put(transaction.getHash(), transaction);
        int i = C353311.$SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool[pool.ordinal()];
        boolean z = false;
        if (i == 1) {
            if (this.unspent.put(transaction.getHash(), transaction) == null) {
                z = true;
            }
            Preconditions.checkState(z);
        } else if (i == 2) {
            if (this.spent.put(transaction.getHash(), transaction) == null) {
                z = true;
            }
            Preconditions.checkState(z);
        } else if (i == 3) {
            if (this.pending.put(transaction.getHash(), transaction) == null) {
                z = true;
            }
            Preconditions.checkState(z);
        } else if (i == 4) {
            if (this.dead.put(transaction.getHash(), transaction) == null) {
                z = true;
            }
            Preconditions.checkState(z);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown wallet transaction type ");
            sb.append(pool);
            throw new RuntimeException(sb.toString());
        }
        if (pool == Pool.UNSPENT || pool == Pool.PENDING) {
            for (TransactionOutput transactionOutput : transaction.getOutputs()) {
                if (transactionOutput.isAvailableForSpending() && transactionOutput.isMineOrWatched(this)) {
                    this.myUnspents.add(transactionOutput);
                }
            }
        }
        transaction.getConfidence().addEventListener(Threading.SAME_THREAD, this.txConfidenceListener);
    }

    public List<Transaction> getTransactionsByTime() {
        return getRecentTransactions(0, false);
    }

    public List<Transaction> getRecentTransactions(int i, boolean z) {
        this.lock.lock();
        try {
            Preconditions.checkArgument(i >= 0);
            int size = this.unspent.size() + this.spent.size() + this.pending.size();
            if (i > size || i == 0) {
                i = size;
            }
            ArrayList arrayList = new ArrayList(getTransactions(z));
            Collections.sort(arrayList, Transaction.SORT_TX_BY_UPDATE_TIME);
            if (i != arrayList.size()) {
                arrayList.subList(i, arrayList.size()).clear();
            }
            return arrayList;
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    public Transaction getTransaction(Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            return (Transaction) this.transactions.get(sha256Hash);
        } finally {
            this.lock.unlock();
        }
    }

    public Map<Sha256Hash, Transaction> getTransactionPool(Pool pool) {
        Map<Sha256Hash, Transaction> map;
        this.lock.lock();
        try {
            int i = C353311.$SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool[pool.ordinal()];
            if (i == 1) {
                map = this.unspent;
            } else if (i == 2) {
                map = this.spent;
            } else if (i == 3) {
                map = this.pending;
            } else if (i == 4) {
                map = this.dead;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown wallet transaction type ");
                sb.append(pool);
                throw new RuntimeException(sb.toString());
            }
            return map;
        } finally {
            this.lock.unlock();
        }
    }

    public void reset() {
        this.lock.lock();
        try {
            clearTransactions();
            this.lastBlockSeenHash = null;
            this.lastBlockSeenHeight = -1;
            this.lastBlockSeenTimeSecs = 0;
            saveLater();
            maybeQueueOnWalletChanged();
        } finally {
            this.lock.unlock();
        }
    }

    public void clearTransactions(int i) {
        this.lock.lock();
        if (i == 0) {
            try {
                clearTransactions();
                saveLater();
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private void clearTransactions() {
        this.unspent.clear();
        this.spent.clear();
        this.pending.clear();
        this.dead.clear();
        this.transactions.clear();
        this.myUnspents.clear();
    }

    public List<TransactionOutput> getWatchedOutputs(boolean z) {
        this.lock.lock();
        this.keyChainGroupLock.lock();
        try {
            LinkedList newLinkedList = Lists.newLinkedList();
            for (Transaction transaction : Iterables.concat(this.unspent.values(), this.pending.values())) {
                if (!z || transaction.isMature()) {
                    for (TransactionOutput transactionOutput : transaction.getOutputs()) {
                        if (transactionOutput.isAvailableForSpending()) {
                            try {
                                if (this.watchedScripts.contains(transactionOutput.getScriptPubKey())) {
                                    newLinkedList.add(transactionOutput);
                                }
                            } catch (ScriptException unused) {
                            }
                        }
                    }
                }
            }
            return newLinkedList;
        } finally {
            this.keyChainGroupLock.unlock();
            this.lock.unlock();
        }
    }

    public void cleanup() {
        this.lock.lock();
        boolean z = false;
        try {
            Iterator it = this.pending.values().iterator();
            while (it.hasNext()) {
                Transaction transaction = (Transaction) it.next();
                if (isTransactionRisky(transaction, null) && !this.acceptRiskyTransactions) {
                    log.debug("Found risky transaction {} in wallet during cleanup.", (Object) transaction.getHashAsString());
                    if (!transaction.isAnyOutputSpent()) {
                        for (TransactionInput transactionInput : transaction.getInputs()) {
                            TransactionOutput connectedOutput = transactionInput.getConnectedOutput();
                            if (connectedOutput != null) {
                                if (connectedOutput.isMineOrWatched(this)) {
                                    Preconditions.checkState(this.myUnspents.add(connectedOutput));
                                }
                                transactionInput.disconnect();
                            }
                        }
                        for (TransactionOutput remove : transaction.getOutputs()) {
                            this.myUnspents.remove(remove);
                        }
                        it.remove();
                        this.transactions.remove(transaction.getHash());
                        z = true;
                        log.info("Removed transaction {} from pending pool during cleanup.", (Object) transaction.getHashAsString());
                    } else {
                        log.info("Cannot remove transaction {} from pending pool during cleanup, as it's already spent partially.", (Object) transaction.getHashAsString());
                    }
                }
            }
            if (z) {
                isConsistentOrThrow();
                saveLater();
                if (log.isInfoEnabled()) {
                    log.info("Estimated balance is now: {}", (Object) getBalance(BalanceType.ESTIMATED).toFriendlyString());
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public EnumSet<Pool> getContainingPools(Transaction transaction) {
        this.lock.lock();
        try {
            EnumSet<Pool> noneOf = EnumSet.noneOf(Pool.class);
            Sha256Hash hash = transaction.getHash();
            if (this.unspent.containsKey(hash)) {
                noneOf.add(Pool.UNSPENT);
            }
            if (this.spent.containsKey(hash)) {
                noneOf.add(Pool.SPENT);
            }
            if (this.pending.containsKey(hash)) {
                noneOf.add(Pool.PENDING);
            }
            if (this.dead.containsKey(hash)) {
                noneOf.add(Pool.DEAD);
            }
            return noneOf;
        } finally {
            this.lock.unlock();
        }
    }

    @VisibleForTesting
    public int getPoolSize(Pool pool) {
        int size;
        this.lock.lock();
        try {
            int i = C353311.$SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool[pool.ordinal()];
            if (i == 1) {
                size = this.unspent.size();
            } else if (i == 2) {
                size = this.spent.size();
            } else if (i == 3) {
                size = this.pending.size();
            } else if (i == 4) {
                size = this.dead.size();
            } else {
                throw new RuntimeException("Unreachable");
            }
            return size;
        } finally {
            this.lock.unlock();
        }
    }

    @VisibleForTesting
    public boolean poolContainsTxHash(Pool pool, Sha256Hash sha256Hash) {
        boolean containsKey;
        this.lock.lock();
        try {
            int i = C353311.$SwitchMap$org$bitcoinj$wallet$WalletTransaction$Pool[pool.ordinal()];
            if (i == 1) {
                containsKey = this.unspent.containsKey(sha256Hash);
            } else if (i == 2) {
                containsKey = this.spent.containsKey(sha256Hash);
            } else if (i == 3) {
                containsKey = this.pending.containsKey(sha256Hash);
            } else if (i == 4) {
                containsKey = this.dead.containsKey(sha256Hash);
            } else {
                throw new RuntimeException("Unreachable");
            }
            return containsKey;
        } finally {
            this.lock.unlock();
        }
    }

    public List<TransactionOutput> getUnspents() {
        this.lock.lock();
        try {
            return new ArrayList(this.myUnspents);
        } finally {
            this.lock.unlock();
        }
    }

    public String toString() {
        return toString(false, true, true, null);
    }

    public String toString(boolean z, boolean z2, boolean z3, @Nullable AbstractBlockChain abstractBlockChain) {
        String str;
        String str2 = "  ";
        this.lock.lock();
        this.keyChainGroupLock.lock();
        try {
            StringBuilder sb = new StringBuilder();
            Coin balance = getBalance(BalanceType.ESTIMATED);
            Coin balance2 = getBalance(BalanceType.AVAILABLE_SPENDABLE);
            sb.append("Wallet containing ");
            sb.append(balance.toFriendlyString());
            sb.append(" (spendable: ");
            sb.append(balance2.toFriendlyString());
            sb.append(") in:\n");
            sb.append(str2);
            sb.append(this.pending.size());
            sb.append(" pending transactions\n");
            sb.append(str2);
            sb.append(this.unspent.size());
            sb.append(" unspent transactions\n");
            sb.append(str2);
            sb.append(this.spent.size());
            sb.append(" spent transactions\n");
            sb.append(str2);
            sb.append(this.dead.size());
            sb.append(" dead transactions\n");
            Date lastBlockSeenTime = getLastBlockSeenTime();
            sb.append("Last seen best block: ");
            sb.append(getLastBlockSeenHeight());
            sb.append(" (");
            if (lastBlockSeenTime == null) {
                str = "time unknown";
            } else {
                str = C3447Utils.dateTimeFormat(lastBlockSeenTime);
            }
            sb.append(str);
            sb.append("): ");
            sb.append(getLastBlockSeenHash());
            sb.append(10);
            KeyCrypter keyCrypter = this.keyChainGroup.getKeyCrypter();
            if (keyCrypter != null) {
                sb.append("Encryption: ");
                sb.append(keyCrypter);
                sb.append(10);
            }
            if (isWatching()) {
                sb.append("Wallet is watching.\n");
            }
            sb.append("\nKeys:\n");
            sb.append("Earliest creation time: ");
            sb.append(C3447Utils.dateTimeFormat(getEarliestKeyCreationTime() * 1000));
            sb.append(10);
            Date keyRotationTime = getKeyRotationTime();
            if (keyRotationTime != null) {
                sb.append("Key rotation time:      ");
                sb.append(C3447Utils.dateTimeFormat(keyRotationTime));
                sb.append(10);
            }
            sb.append(this.keyChainGroup.toString(z));
            if (!this.watchedScripts.isEmpty()) {
                sb.append("\nWatched scripts:\n");
                for (Script script : this.watchedScripts) {
                    sb.append(str2);
                    sb.append(script);
                    sb.append("\n");
                }
            }
            if (z2) {
                if (this.pending.size() > 0) {
                    sb.append("\n>>> PENDING:\n");
                    toStringHelper(sb, this.pending, abstractBlockChain, Transaction.SORT_TX_BY_UPDATE_TIME);
                }
                if (this.unspent.size() > 0) {
                    sb.append("\n>>> UNSPENT:\n");
                    toStringHelper(sb, this.unspent, abstractBlockChain, Transaction.SORT_TX_BY_HEIGHT);
                }
                if (this.spent.size() > 0) {
                    sb.append("\n>>> SPENT:\n");
                    toStringHelper(sb, this.spent, abstractBlockChain, Transaction.SORT_TX_BY_HEIGHT);
                }
                if (this.dead.size() > 0) {
                    sb.append("\n>>> DEAD:\n");
                    toStringHelper(sb, this.dead, abstractBlockChain, Transaction.SORT_TX_BY_UPDATE_TIME);
                }
            }
            if (z3 && this.extensions.size() > 0) {
                sb.append("\n>>> EXTENSIONS:\n");
                for (WalletExtension append : this.extensions.values()) {
                    sb.append(append);
                    sb.append("\n\n");
                }
            }
            return sb.toString();
        } finally {
            this.keyChainGroupLock.unlock();
            this.lock.unlock();
        }
    }

    private void toStringHelper(StringBuilder sb, Map<Sha256Hash, Transaction> map, @Nullable AbstractBlockChain abstractBlockChain, @Nullable Comparator<Transaction> comparator) {
        Collection<Transaction> collection;
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        if (comparator != null) {
            collection = new TreeSet<>(comparator);
            collection.addAll(map.values());
        } else {
            collection = map.values();
        }
        for (Transaction transaction : collection) {
            try {
                sb.append(transaction.getValue(this).toFriendlyString());
                sb.append(" total value (sends ");
                sb.append(transaction.getValueSentFromMe(this).toFriendlyString());
                sb.append(" and receives ");
                sb.append(transaction.getValueSentToMe(this).toFriendlyString());
                sb.append(")\n");
            } catch (ScriptException unused) {
            }
            if (transaction.hasConfidence()) {
                sb.append("  confidence: ");
                sb.append(transaction.getConfidence());
                sb.append(10);
            }
            sb.append(transaction.toString(abstractBlockChain));
        }
    }

    public Collection<Transaction> getPendingTransactions() {
        this.lock.lock();
        try {
            return Collections.unmodifiableCollection(this.pending.values());
        } finally {
            this.lock.unlock();
        }
    }

    public long getEarliestKeyCreationTime() {
        this.keyChainGroupLock.lock();
        try {
            long earliestKeyCreationTime = this.keyChainGroup.getEarliestKeyCreationTime();
            for (Script creationTimeSeconds : this.watchedScripts) {
                earliestKeyCreationTime = Math.min(creationTimeSeconds.getCreationTimeSeconds(), earliestKeyCreationTime);
            }
            if (earliestKeyCreationTime == Long.MAX_VALUE) {
                earliestKeyCreationTime = C3447Utils.currentTimeSeconds();
            }
            return earliestKeyCreationTime;
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    @Nullable
    public Sha256Hash getLastBlockSeenHash() {
        this.lock.lock();
        try {
            return this.lastBlockSeenHash;
        } finally {
            this.lock.unlock();
        }
    }

    public void setLastBlockSeenHash(@Nullable Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            this.lastBlockSeenHash = sha256Hash;
        } finally {
            this.lock.unlock();
        }
    }

    public void setLastBlockSeenHeight(int i) {
        this.lock.lock();
        try {
            this.lastBlockSeenHeight = i;
        } finally {
            this.lock.unlock();
        }
    }

    public void setLastBlockSeenTimeSecs(long j) {
        this.lock.lock();
        try {
            this.lastBlockSeenTimeSecs = j;
        } finally {
            this.lock.unlock();
        }
    }

    public long getLastBlockSeenTimeSecs() {
        this.lock.lock();
        try {
            return this.lastBlockSeenTimeSecs;
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    public Date getLastBlockSeenTime() {
        long lastBlockSeenTimeSecs2 = getLastBlockSeenTimeSecs();
        if (lastBlockSeenTimeSecs2 == 0) {
            return null;
        }
        return new Date(lastBlockSeenTimeSecs2 * 1000);
    }

    public int getLastBlockSeenHeight() {
        this.lock.lock();
        try {
            return this.lastBlockSeenHeight;
        } finally {
            this.lock.unlock();
        }
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }

    @Deprecated
    public Coin getWatchedBalance() {
        return getBalance();
    }

    @Deprecated
    public Coin getWatchedBalance(CoinSelector coinSelector2) {
        return getBalance(coinSelector2);
    }

    public Coin getBalance() {
        return getBalance(BalanceType.AVAILABLE);
    }

    public Coin getBalance(BalanceType balanceType) {
        this.lock.lock();
        try {
            boolean z = false;
            boolean z2 = true;
            if (balanceType != BalanceType.AVAILABLE) {
                if (balanceType != BalanceType.AVAILABLE_SPENDABLE) {
                    if (balanceType != BalanceType.ESTIMATED) {
                        if (balanceType != BalanceType.ESTIMATED_SPENDABLE) {
                            throw new AssertionError("Unknown balance type");
                        }
                    }
                    if (balanceType != BalanceType.ESTIMATED_SPENDABLE) {
                        z2 = false;
                    }
                    List<TransactionOutput> calculateAllSpendCandidates = calculateAllSpendCandidates(false, z2);
                    Coin coin = Coin.ZERO;
                    for (TransactionOutput value : calculateAllSpendCandidates) {
                        coin = coin.add(value.getValue());
                    }
                    this.lock.unlock();
                    return coin;
                }
            }
            if (balanceType == BalanceType.AVAILABLE_SPENDABLE) {
                z = true;
            }
            return this.coinSelector.select(NetworkParameters.MAX_MONEY, calculateAllSpendCandidates(true, z)).valueGathered;
        } finally {
            this.lock.unlock();
        }
    }

    public Coin getBalance(CoinSelector coinSelector2) {
        this.lock.lock();
        try {
            Preconditions.checkNotNull(coinSelector2);
            return coinSelector2.select(this.params.getMaxMoney(), calculateAllSpendCandidates(true, false)).valueGathered;
        } finally {
            this.lock.unlock();
        }
    }

    public ListenableFuture<Coin> getBalanceFuture(Coin coin, BalanceType balanceType) {
        this.lock.lock();
        try {
            SettableFuture<Coin> create = SettableFuture.create();
            Coin balance = getBalance(balanceType);
            if (balance.compareTo(coin) >= 0) {
                create.set(balance);
            } else {
                BalanceFutureRequest balanceFutureRequest = new BalanceFutureRequest();
                balanceFutureRequest.future = create;
                balanceFutureRequest.value = coin;
                balanceFutureRequest.type = balanceType;
                this.balanceFutureRequests.add(balanceFutureRequest);
            }
            return create;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: private */
    public void checkBalanceFuturesLocked(@Nullable Coin coin) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        ListIterator listIterator = this.balanceFutureRequests.listIterator();
        while (listIterator.hasNext()) {
            final BalanceFutureRequest balanceFutureRequest = (BalanceFutureRequest) listIterator.next();
            final Coin balance = getBalance(balanceFutureRequest.type);
            if (balance.compareTo(balanceFutureRequest.value) >= 0) {
                listIterator.remove();
                Threading.USER_THREAD.execute(new Runnable() {
                    public void run() {
                        balanceFutureRequest.future.set(balance);
                    }
                });
            }
        }
    }

    public Coin getTotalReceived() {
        Coin coin = Coin.ZERO;
        for (Transaction transaction : this.transactions.values()) {
            Coin coin2 = Coin.ZERO;
            for (TransactionOutput transactionOutput : transaction.getOutputs()) {
                if (transactionOutput.isMine(this)) {
                    coin2 = coin2.add(transactionOutput.getValue());
                }
            }
            for (TransactionInput connectedOutput : transaction.getInputs()) {
                TransactionOutput connectedOutput2 = connectedOutput.getConnectedOutput();
                if (connectedOutput2 != null && connectedOutput2.isMine(this)) {
                    coin2 = coin2.subtract(connectedOutput2.getValue());
                }
            }
            if (coin2.isPositive()) {
                coin = coin.add(coin2);
            }
        }
        return coin;
    }

    public Coin getTotalSent() {
        Coin coin = Coin.ZERO;
        for (Transaction transaction : this.transactions.values()) {
            Coin coin2 = Coin.ZERO;
            for (TransactionOutput transactionOutput : transaction.getOutputs()) {
                if (!transactionOutput.isMine(this)) {
                    coin2 = coin2.add(transactionOutput.getValue());
                }
            }
            Coin coin3 = Coin.ZERO;
            for (TransactionInput connectedOutput : transaction.getInputs()) {
                TransactionOutput connectedOutput2 = connectedOutput.getConnectedOutput();
                if (connectedOutput2 != null && connectedOutput2.isMine(this)) {
                    coin3 = coin3.add(connectedOutput2.getValue());
                }
            }
            Coin inputSum = transaction.getInputSum();
            if (coin3 != inputSum) {
                coin2 = Coin.valueOf(new BigInteger(coin2.toString()).multiply(new BigInteger(coin3.toString())).divide(new BigInteger(inputSum.toString())).longValue());
            }
            coin = coin.add(coin2);
        }
        return coin;
    }

    public Transaction createSend(Address address, Coin coin) throws InsufficientMoneyException {
        SendRequest sendRequest = SendRequest.m345to(address, coin);
        if (this.params.getId().equals(NetworkParameters.ID_UNITTESTNET)) {
            sendRequest.shuffleOutputs = false;
        }
        completeTx(sendRequest);
        return sendRequest.f829tx;
    }

    public Transaction sendCoinsOffline(SendRequest sendRequest) throws InsufficientMoneyException {
        this.lock.lock();
        try {
            completeTx(sendRequest);
            commitTx(sendRequest.f829tx);
            return sendRequest.f829tx;
        } finally {
            this.lock.unlock();
        }
    }

    public SendResult sendCoins(TransactionBroadcaster transactionBroadcaster, Address address, Coin coin) throws InsufficientMoneyException {
        return sendCoins(transactionBroadcaster, SendRequest.m345to(address, coin));
    }

    public SendResult sendCoins(TransactionBroadcaster transactionBroadcaster, Address address, Coin coin, boolean z) throws InsufficientMoneyException {
        SendRequest sendRequest = SendRequest.m345to(address, coin);
        sendRequest.setUseForkId(z);
        return sendCoins(transactionBroadcaster, sendRequest);
    }

    public SendResult sendCoins(TransactionBroadcaster transactionBroadcaster, SendRequest sendRequest) throws InsufficientMoneyException {
        Preconditions.checkState(!this.lock.isHeldByCurrentThread());
        Transaction sendCoinsOffline = sendCoinsOffline(sendRequest);
        SendResult sendResult = new SendResult();
        sendResult.f830tx = sendCoinsOffline;
        sendResult.broadcast = transactionBroadcaster.broadcastTransaction(sendCoinsOffline);
        sendResult.broadcastComplete = sendResult.broadcast.future();
        return sendResult;
    }

    public SendResult sendCoins(SendRequest sendRequest) throws InsufficientMoneyException {
        TransactionBroadcaster transactionBroadcaster = this.vTransactionBroadcaster;
        Preconditions.checkState(transactionBroadcaster != null, "No transaction broadcaster is configured");
        return sendCoins(transactionBroadcaster, sendRequest);
    }

    public Transaction sendCoins(Peer peer, SendRequest sendRequest) throws InsufficientMoneyException {
        Transaction sendCoinsOffline = sendCoinsOffline(sendRequest);
        peer.sendMessage(sendCoinsOffline);
        return sendCoinsOffline;
    }

    public void completeTx(SendRequest sendRequest) throws InsufficientMoneyException {
        CoinSelection coinSelection;
        this.lock.lock();
        try {
            boolean z = false;
            Preconditions.checkArgument(!sendRequest.completed, "Given SendRequest has already been completed.");
            if (sendRequest.getUseForkId()) {
                sendRequest.f829tx.setVersion(2);
            }
            Coin coin = Coin.ZERO;
            for (TransactionOutput value : sendRequest.f829tx.getOutputs()) {
                coin = coin.add(value.getValue());
            }
            log.info("Completing send tx with {} outputs totalling {} and a fee of {}/kB", Integer.valueOf(sendRequest.f829tx.getOutputs().size()), coin.toFriendlyString(), sendRequest.feePerKb.toFriendlyString());
            Coin coin2 = Coin.ZERO;
            for (TransactionInput transactionInput : sendRequest.f829tx.getInputs()) {
                if (transactionInput.getConnectedOutput() != null) {
                    coin2 = coin2.add(transactionInput.getConnectedOutput().getValue());
                } else {
                    log.warn("SendRequest transaction already has inputs but we don't know how much they are worth - they will be added to fee.");
                }
            }
            Coin subtract = coin.subtract(coin2);
            ArrayList arrayList = new ArrayList(sendRequest.f829tx.getInputs());
            if (sendRequest.ensureMinRequiredFee && !sendRequest.emptyWallet) {
                int i = 0;
                for (TransactionOutput transactionOutput : sendRequest.f829tx.getOutputs()) {
                    if (transactionOutput.isDust()) {
                        throw new DustySendRequested();
                    } else if (transactionOutput.getScriptPubKey().isOpReturn()) {
                        i++;
                    }
                }
                if (i > 1) {
                    throw new MultipleOpReturnRequested();
                }
            }
            List calculateAllSpendCandidates = calculateAllSpendCandidates(true, sendRequest.missingSigsMode == MissingSigsMode.THROW);
            TransactionOutput transactionOutput2 = null;
            if (!sendRequest.emptyWallet) {
                FeeCalculation calculateFee = calculateFee(sendRequest, subtract, arrayList, sendRequest.ensureMinRequiredFee, calculateAllSpendCandidates);
                coinSelection = calculateFee.bestCoinSelection;
                transactionOutput2 = calculateFee.bestChangeOutput;
            } else {
                if (sendRequest.f829tx.getOutputs().size() == 1) {
                    z = true;
                }
                Preconditions.checkState(z, "Empty wallet TX must have a single output only.");
                coinSelection = (sendRequest.coinSelector == null ? this.coinSelector : sendRequest.coinSelector).select(this.params.getMaxMoney(), calculateAllSpendCandidates);
                sendRequest.f829tx.getOutput(0).setValue(coinSelection.valueGathered);
                log.info("  emptying {}", (Object) coinSelection.valueGathered.toFriendlyString());
            }
            for (TransactionOutput addInput : coinSelection.gathered) {
                sendRequest.f829tx.addInput(addInput);
            }
            if (sendRequest.emptyWallet) {
                if (!adjustOutputDownwardsForFee(sendRequest.f829tx, coinSelection, sendRequest.feePerKb == null ? Coin.ZERO : sendRequest.feePerKb, sendRequest.ensureMinRequiredFee)) {
                    throw new CouldNotAdjustDownwards();
                }
            }
            if (transactionOutput2 != null) {
                sendRequest.f829tx.addOutput(transactionOutput2);
                log.info("  with {} change", (Object) transactionOutput2.getValue().toFriendlyString());
            }
            if (sendRequest.shuffleOutputs) {
                sendRequest.f829tx.shuffleOutputs();
            }
            if (sendRequest.signInputs) {
                signTransaction(sendRequest);
            }
            if (sendRequest.f829tx.unsafeBitcoinSerialize().length <= 100000) {
                sendRequest.f829tx.getConfidence().setSource(Source.SELF);
                sendRequest.f829tx.setPurpose(Purpose.USER_PAYMENT);
                sendRequest.f829tx.setExchangeRate(sendRequest.exchangeRate);
                sendRequest.f829tx.setMemo(sendRequest.memo);
                sendRequest.completed = true;
                log.info("  completed: {}", (Object) sendRequest.f829tx);
                return;
            }
            throw new ExceededMaxTransactionSize();
        } finally {
            this.lock.unlock();
        }
    }

    public void signTransaction(SendRequest sendRequest) {
        DecryptingKeyBag decryptingKeyBag;
        TransactionInput input;
        this.lock.lock();
        try {
            Transaction transaction = sendRequest.f829tx;
            List inputs = transaction.getInputs();
            List outputs = transaction.getOutputs();
            Preconditions.checkState(inputs.size() > 0);
            Preconditions.checkState(outputs.size() > 0);
            decryptingKeyBag = new DecryptingKeyBag(this, sendRequest.aesKey);
            int size = transaction.getInputs().size();
            for (int i = 0; i < size; i++) {
                long j = (long) i;
                input = transaction.getInput(j);
                if (input.getConnectedOutput() != null) {
                    input.getScriptSig().correctlySpends(transaction, j, input.getConnectedOutput().getScriptPubKey());
                    log.warn("Input {} already correctly spends output, assuming SIGHASH type used will be safe and skipping signing.", (Object) Integer.valueOf(i));
                }
            }
            ProposedTransaction proposedTransaction = new ProposedTransaction(transaction, sendRequest.getUseForkId());
            for (TransactionSigner transactionSigner : this.signers) {
                if (!transactionSigner.signInputs(proposedTransaction, decryptingKeyBag)) {
                    log.info("{} returned false for the tx", (Object) transactionSigner.getClass().getName());
                }
            }
            new MissingSigResolutionSigner(sendRequest.missingSigsMode).signInputs(proposedTransaction, decryptingKeyBag);
            this.lock.unlock();
        } catch (ScriptException e) {
            log.debug("Input contained an incorrect signature", (Throwable) e);
            Script scriptPubKey = input.getConnectedOutput().getScriptPubKey();
            RedeemData connectedRedeemData = input.getConnectedRedeemData(decryptingKeyBag);
            Preconditions.checkNotNull(connectedRedeemData, "Transaction exists in wallet that we cannot redeem: %s", input.getOutpoint().getHash());
            input.setScriptSig(scriptPubKey.createEmptyInputScript((ECKey) connectedRedeemData.keys.get(0), connectedRedeemData.redeemScript));
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    private boolean adjustOutputDownwardsForFee(Transaction transaction, CoinSelection coinSelection, Coin coin, boolean z) {
        Coin divide = coin.multiply((long) (transaction.unsafeBitcoinSerialize().length + estimateBytesForSigning(coinSelection))).divide(1000);
        if (z && divide.compareTo(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE) < 0) {
            divide = Transaction.REFERENCE_DEFAULT_MIN_TX_FEE;
        }
        TransactionOutput output = transaction.getOutput(0);
        output.setValue(output.getValue().subtract(divide));
        return !output.isDust();
    }

    public List<TransactionOutput> calculateAllSpendCandidates() {
        return calculateAllSpendCandidates(true, true);
    }

    @Deprecated
    public List<TransactionOutput> calculateAllSpendCandidates(boolean z) {
        return calculateAllSpendCandidates(z, true);
    }

    public List<TransactionOutput> calculateAllSpendCandidates(boolean z, boolean z2) {
        List<TransactionOutput> list;
        this.lock.lock();
        try {
            if (this.vUTXOProvider == null) {
                list = new ArrayList<>(this.myUnspents.size());
                Iterator it = this.myUnspents.iterator();
                while (it.hasNext()) {
                    TransactionOutput transactionOutput = (TransactionOutput) it.next();
                    if (!z2 || canSignFor(transactionOutput.getScriptPubKey())) {
                        Transaction transaction = (Transaction) Preconditions.checkNotNull(transactionOutput.getParentTransaction());
                        if (!z || transaction.isMature()) {
                            list.add(transactionOutput);
                        }
                    }
                }
            } else {
                list = calculateAllSpendCandidatesFromUTXOProvider(z);
            }
            return list;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean canSignFor(Script script) {
        boolean z = false;
        if (script.isSentToRawPubKey()) {
            ECKey findKeyFromPubKey = findKeyFromPubKey(script.getPubKey());
            if (findKeyFromPubKey != null && (findKeyFromPubKey.isEncrypted() || findKeyFromPubKey.hasPrivKey())) {
                z = true;
            }
            return z;
        } else if (script.isPayToScriptHash()) {
            RedeemData findRedeemDataFromScriptHash = findRedeemDataFromScriptHash(script.getPubKeyHash());
            if (findRedeemDataFromScriptHash != null && canSignFor(findRedeemDataFromScriptHash.redeemScript)) {
                z = true;
            }
            return z;
        } else if (script.isSentToAddress()) {
            ECKey findKeyFromPubHash = findKeyFromPubHash(script.getPubKeyHash());
            if (findKeyFromPubHash != null && (findKeyFromPubHash.isEncrypted() || findKeyFromPubHash.hasPrivKey())) {
                z = true;
            }
            return z;
        } else {
            if (script.isSentToMultiSig()) {
                for (ECKey pubKey : script.getPubKeys()) {
                    ECKey findKeyFromPubKey2 = findKeyFromPubKey(pubKey.getPubKey());
                    if (findKeyFromPubKey2 != null && (findKeyFromPubKey2.isEncrypted() || findKeyFromPubKey2.hasPrivKey())) {
                        return true;
                    }
                }
            } else if (script.isSentToCLTVPaymentChannel()) {
                byte[] cLTVPaymentChannelSenderPubKey = script.getCLTVPaymentChannelSenderPubKey();
                ECKey findKeyFromPubKey3 = findKeyFromPubKey(cLTVPaymentChannelSenderPubKey);
                if (findKeyFromPubKey3 != null && (findKeyFromPubKey3.isEncrypted() || findKeyFromPubKey3.hasPrivKey())) {
                    return true;
                }
                script.getCLTVPaymentChannelRecipientPubKey();
                ECKey findKeyFromPubKey4 = findKeyFromPubKey(cLTVPaymentChannelSenderPubKey);
                return findKeyFromPubKey4 != null && (findKeyFromPubKey4.isEncrypted() || findKeyFromPubKey4.hasPrivKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    public LinkedList<TransactionOutput> calculateAllSpendCandidatesFromUTXOProvider(boolean z) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        UTXOProvider uTXOProvider = (UTXOProvider) Preconditions.checkNotNull(this.vUTXOProvider, "No UTXO provider has been set");
        LinkedList<TransactionOutput> newLinkedList = Lists.newLinkedList();
        try {
            int chainHeadHeight = uTXOProvider.getChainHeadHeight();
            for (UTXO utxo : getStoredOutputsFromUTXOProvider()) {
                boolean isCoinbase = utxo.isCoinbase();
                int height = (chainHeadHeight - utxo.getHeight()) + 1;
                if (!z || !isCoinbase || height >= this.params.getSpendableCoinbaseDepth()) {
                    newLinkedList.add(new FreeStandingTransactionOutput(this.params, utxo, chainHeadHeight));
                }
            }
            for (Transaction transaction : this.pending.values()) {
                for (TransactionInput transactionInput : transaction.getInputs()) {
                    if (transactionInput.getConnectedOutput().isMine(this)) {
                        newLinkedList.remove(transactionInput.getConnectedOutput());
                    }
                }
                if (!z || transaction.isMature()) {
                    for (TransactionOutput transactionOutput : transaction.getOutputs()) {
                        if (transactionOutput.isAvailableForSpending() && transactionOutput.isMine(this)) {
                            newLinkedList.add(transactionOutput);
                        }
                    }
                }
            }
            return newLinkedList;
        } catch (UTXOProviderException e) {
            throw new RuntimeException("UTXO provider error", e);
        }
    }

    /* access modifiers changed from: protected */
    public List<UTXO> getStoredOutputsFromUTXOProvider() throws UTXOProviderException {
        UTXOProvider uTXOProvider = (UTXOProvider) Preconditions.checkNotNull(this.vUTXOProvider, "No UTXO provider has been set");
        ArrayList arrayList = new ArrayList();
        List<ECKey> importedKeys = getImportedKeys();
        importedKeys.addAll(getActiveKeyChain().getLeafKeys());
        ArrayList arrayList2 = new ArrayList();
        for (ECKey pubKeyHash : importedKeys) {
            arrayList2.add(new Address(this.params, pubKeyHash.getPubKeyHash()));
        }
        arrayList.addAll(uTXOProvider.getOpenTransactionOutputs(arrayList2));
        return arrayList;
    }

    public CoinSelector getCoinSelector() {
        this.lock.lock();
        try {
            return this.coinSelector;
        } finally {
            this.lock.unlock();
        }
    }

    public void setCoinSelector(CoinSelector coinSelector2) {
        this.lock.lock();
        try {
            this.coinSelector = (CoinSelector) Preconditions.checkNotNull(coinSelector2);
        } finally {
            this.lock.unlock();
        }
    }

    public void allowSpendingUnconfirmedTransactions() {
        setCoinSelector(AllowUnconfirmedCoinSelector.get());
    }

    @Nullable
    public UTXOProvider getUTXOProvider() {
        this.lock.lock();
        try {
            return this.vUTXOProvider;
        } finally {
            this.lock.unlock();
        }
    }

    public void setUTXOProvider(@Nullable UTXOProvider uTXOProvider) {
        boolean z;
        this.lock.lock();
        if (uTXOProvider != null) {
            try {
                if (!uTXOProvider.getParams().equals(this.params)) {
                    z = false;
                    Preconditions.checkArgument(z);
                    this.vUTXOProvider = uTXOProvider;
                    this.lock.unlock();
                }
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
        z = true;
        Preconditions.checkArgument(z);
        this.vUTXOProvider = uTXOProvider;
        this.lock.unlock();
    }

    public void reorganize(StoredBlock storedBlock, List<StoredBlock> list, List<StoredBlock> list2) throws VerificationException {
        String str;
        this.lock.lock();
        try {
            Preconditions.checkState(this.confidenceChanged.size() == 0);
            Preconditions.checkState(!this.insideReorg);
            this.insideReorg = true;
            Preconditions.checkState(this.onWalletChangedSuppressions == 0);
            this.onWalletChangedSuppressions++;
            ArrayListMultimap create = ArrayListMultimap.create();
            for (Transaction transaction : getTransactions(true)) {
                Map appearsInHashes = transaction.getAppearsInHashes();
                if (appearsInHashes != null) {
                    for (Entry entry : appearsInHashes.entrySet()) {
                        create.put(entry.getKey(), new TxOffsetPair(transaction, ((Integer) entry.getValue()).intValue()));
                    }
                }
            }
            for (Sha256Hash sha256Hash : create.keySet()) {
                Collections.sort(create.get(sha256Hash));
            }
            ArrayList<Sha256Hash> arrayList = new ArrayList<>(list.size());
            log.info("Old part of chain (top to bottom):");
            Iterator it = list.iterator();
            while (true) {
                str = "  {}";
                if (!it.hasNext()) {
                    break;
                }
                StoredBlock storedBlock2 = (StoredBlock) it.next();
                log.info(str, (Object) storedBlock2.getHeader().getHashAsString());
                arrayList.add(storedBlock2.getHeader().getHash());
            }
            log.info("New part of chain (top to bottom):");
            for (StoredBlock header : list2) {
                log.info(str, (Object) header.getHeader().getHashAsString());
            }
            Collections.reverse(list2);
            LinkedList newLinkedList = Lists.newLinkedList();
            for (Sha256Hash sha256Hash2 : arrayList) {
                for (TxOffsetPair txOffsetPair : create.get(sha256Hash2)) {
                    Transaction transaction2 = txOffsetPair.f831tx;
                    Sha256Hash hash = transaction2.getHash();
                    if (transaction2.isCoinBase()) {
                        log.warn("Coinbase killed by re-org: {}", (Object) transaction2.getHashAsString());
                        killTxns(ImmutableSet.m169of(transaction2), null);
                    } else {
                        for (TransactionOutput transactionOutput : transaction2.getOutputs()) {
                            TransactionInput spentBy = transactionOutput.getSpentBy();
                            if (spentBy != null) {
                                if (transactionOutput.isMineOrWatched(this)) {
                                    Preconditions.checkState(this.myUnspents.add(transactionOutput));
                                }
                                spentBy.disconnect();
                            }
                        }
                        newLinkedList.add(transaction2);
                        this.unspent.remove(hash);
                        this.spent.remove(hash);
                        Preconditions.checkState(!this.pending.containsKey(hash));
                        Preconditions.checkState(!this.dead.containsKey(hash));
                    }
                }
            }
            Iterator it2 = newLinkedList.iterator();
            while (it2.hasNext()) {
                Transaction transaction3 = (Transaction) it2.next();
                if (!transaction3.isCoinBase()) {
                    log.info("  ->pending {}", (Object) transaction3.getHash());
                    transaction3.getConfidence().setConfidenceType(ConfidenceType.PENDING);
                    this.confidenceChanged.put(transaction3, ChangeReason.TYPE);
                    addWalletTransaction(Pool.PENDING, transaction3);
                    updateForSpends(transaction3, false);
                }
            }
            int size = list.size();
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("depthToSubtract = ");
            sb.append(size);
            logger.info(sb.toString());
            subtractDepth(size, this.spent.values());
            subtractDepth(size, this.unspent.values());
            subtractDepth(size, this.dead.values());
            setLastBlockSeenHash(storedBlock.getHeader().getHash());
            for (StoredBlock storedBlock3 : list2) {
                log.info("Replaying block {}", (Object) storedBlock3.getHeader().getHashAsString());
                for (TxOffsetPair txOffsetPair2 : create.get(storedBlock3.getHeader().getHash())) {
                    log.info("  tx {}", (Object) txOffsetPair2.f831tx.getHash());
                    receive(txOffsetPair2.f831tx, storedBlock3, NewBlockType.BEST_CHAIN, txOffsetPair2.offset);
                }
                notifyNewBestBlock(storedBlock3);
            }
            isConsistentOrThrow();
            Coin balance = getBalance();
            log.info("post-reorg balance is {}", (Object) balance.toFriendlyString());
            queueOnReorganize();
            this.insideReorg = false;
            this.onWalletChangedSuppressions--;
            maybeQueueOnWalletChanged();
            checkBalanceFuturesLocked(balance);
            informConfidenceListenersIfNotReorganizing();
            saveLater();
            this.lock.unlock();
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    private void subtractDepth(int i, Collection<Transaction> collection) {
        for (Transaction transaction : collection) {
            if (transaction.getConfidence().getConfidenceType() == ConfidenceType.BUILDING) {
                transaction.getConfidence().setDepthInBlocks(transaction.getConfidence().getDepthInBlocks() - i);
                this.confidenceChanged.put(transaction, ChangeReason.DEPTH);
            }
        }
    }

    public void beginBloomFilterCalculation() {
        if (this.bloomFilterGuard.incrementAndGet() <= 1) {
            this.lock.lock();
            this.keyChainGroupLock.lock();
            calcBloomOutPointsLocked();
        }
    }

    private void calcBloomOutPointsLocked() {
        this.bloomOutPoints.clear();
        HashSet<Transaction> hashSet = new HashSet<>();
        hashSet.addAll(this.unspent.values());
        hashSet.addAll(this.spent.values());
        hashSet.addAll(this.pending.values());
        for (Transaction outputs : hashSet) {
            for (TransactionOutput transactionOutput : outputs.getOutputs()) {
                try {
                    if (isTxOutputBloomFilterable(transactionOutput)) {
                        this.bloomOutPoints.add(transactionOutput.getOutPointFor());
                    }
                } catch (ScriptException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @GuardedBy("keyChainGroupLock")
    public void endBloomFilterCalculation() {
        if (this.bloomFilterGuard.decrementAndGet() <= 0) {
            this.bloomOutPoints.clear();
            this.keyChainGroupLock.unlock();
            this.lock.unlock();
        }
    }

    public int getBloomFilterElementCount() {
        beginBloomFilterCalculation();
        try {
            return this.bloomOutPoints.size() + this.keyChainGroup.getBloomFilterElementCount() + this.watchedScripts.size();
        } finally {
            endBloomFilterCalculation();
        }
    }

    public boolean isRequiringUpdateAllBloomFilter() {
        this.keyChainGroupLock.lock();
        try {
            return !this.watchedScripts.isEmpty();
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public BloomFilter getBloomFilter(double d) {
        beginBloomFilterCalculation();
        try {
            return getBloomFilter(getBloomFilterElementCount(), d, (long) (Math.random() * 9.223372036854776E18d));
        } finally {
            endBloomFilterCalculation();
        }
    }

    @GuardedBy("keyChainGroupLock")
    public BloomFilter getBloomFilter(int i, double d, long j) {
        beginBloomFilterCalculation();
        try {
            BloomFilter bloomFilter = this.keyChainGroup.getBloomFilter(i, d, j);
            for (Script chunks : this.watchedScripts) {
                for (ScriptChunk scriptChunk : chunks.getChunks()) {
                    if (!scriptChunk.isOpCode() && scriptChunk.data.length >= 8) {
                        bloomFilter.insert(scriptChunk.data);
                    }
                }
            }
            Iterator it = this.bloomOutPoints.iterator();
            while (it.hasNext()) {
                bloomFilter.insert(((TransactionOutPoint) it.next()).unsafeBitcoinSerialize());
            }
            return bloomFilter;
        } finally {
            endBloomFilterCalculation();
        }
    }

    private boolean isTxOutputBloomFilterable(TransactionOutput transactionOutput) {
        Script scriptPubKey = transactionOutput.getScriptPubKey();
        if ((!(scriptPubKey.isSentToRawPubKey() || scriptPubKey.isPayToScriptHash()) || !this.myUnspents.contains(transactionOutput)) && !this.watchedScripts.contains(scriptPubKey)) {
            return false;
        }
        return true;
    }

    public boolean checkForFilterExhaustion(FilteredBlock filteredBlock) {
        this.keyChainGroupLock.lock();
        try {
            int combinedKeyLookaheadEpochs = this.keyChainGroup.getCombinedKeyLookaheadEpochs();
            for (Transaction markKeysAsUsed : filteredBlock.getAssociatedTransactions().values()) {
                markKeysAsUsed(markKeysAsUsed);
            }
            int combinedKeyLookaheadEpochs2 = this.keyChainGroup.getCombinedKeyLookaheadEpochs();
            boolean z = true;
            Preconditions.checkState(combinedKeyLookaheadEpochs2 >= combinedKeyLookaheadEpochs);
            if (combinedKeyLookaheadEpochs2 <= combinedKeyLookaheadEpochs) {
                z = false;
            }
            return z;
        } finally {
            this.keyChainGroupLock.unlock();
        }
    }

    public void addExtension(WalletExtension walletExtension) {
        String walletExtensionID = ((WalletExtension) Preconditions.checkNotNull(walletExtension)).getWalletExtensionID();
        this.lock.lock();
        try {
            if (!this.extensions.containsKey(walletExtensionID)) {
                this.extensions.put(walletExtensionID, walletExtension);
                saveNow();
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot add two extensions with the same ID: ");
            sb.append(walletExtensionID);
            throw new IllegalStateException(sb.toString());
        } finally {
            this.lock.unlock();
        }
    }

    public WalletExtension addOrGetExistingExtension(WalletExtension walletExtension) {
        String walletExtensionID = ((WalletExtension) Preconditions.checkNotNull(walletExtension)).getWalletExtensionID();
        this.lock.lock();
        try {
            WalletExtension walletExtension2 = (WalletExtension) this.extensions.get(walletExtensionID);
            if (walletExtension2 != null) {
                return walletExtension2;
            }
            this.extensions.put(walletExtensionID, walletExtension);
            saveNow();
            this.lock.unlock();
            return walletExtension;
        } finally {
            this.lock.unlock();
        }
    }

    public void addOrUpdateExtension(WalletExtension walletExtension) {
        String walletExtensionID = ((WalletExtension) Preconditions.checkNotNull(walletExtension)).getWalletExtensionID();
        this.lock.lock();
        try {
            this.extensions.put(walletExtensionID, walletExtension);
            saveNow();
        } finally {
            this.lock.unlock();
        }
    }

    public Map<String, WalletExtension> getExtensions() {
        this.lock.lock();
        try {
            return ImmutableMap.copyOf((Map<? extends K, ? extends V>) this.extensions);
        } finally {
            this.lock.unlock();
        }
    }

    public void deserializeExtension(WalletExtension walletExtension, byte[] bArr) throws Exception {
        this.lock.lock();
        this.keyChainGroupLock.lock();
        try {
            walletExtension.deserializeWalletExtension(this, bArr);
            this.extensions.put(walletExtension.getWalletExtensionID(), walletExtension);
        } catch (Throwable th) {
            this.keyChainGroupLock.unlock();
            this.lock.unlock();
            throw th;
        }
        this.keyChainGroupLock.unlock();
        this.lock.unlock();
    }

    public void setTag(String str, ByteString byteString) {
        super.setTag(str, byteString);
        saveNow();
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x01c8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bitcoinj.wallet.C3530Wallet.FeeCalculation calculateFee(org.bitcoinj.wallet.SendRequest r21, org.bitcoinj.core.Coin r22, java.util.List<org.bitcoinj.core.TransactionInput> r23, boolean r24, java.util.List<org.bitcoinj.core.TransactionOutput> r25) throws org.bitcoinj.core.InsufficientMoneyException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r23
            java.util.concurrent.locks.ReentrantLock r3 = r0.lock
            boolean r3 = r3.isHeldByCurrentThread()
            com.google.common.base.Preconditions.checkState(r3)
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0016:
            r0.resetTxInputs(r1, r2)
            org.bitcoinj.core.Coin r12 = r1.feePerKb
            long r13 = (long) r5
            org.bitcoinj.core.Coin r12 = r12.multiply(r13)
            r13 = 1000(0x3e8, double:4.94E-321)
            org.bitcoinj.core.Coin r12 = r12.divide(r13)
            if (r24 == 0) goto L_0x0032
            org.bitcoinj.core.Coin r13 = org.bitcoinj.core.Transaction.REFERENCE_DEFAULT_MIN_TX_FEE
            int r13 = r12.compareTo(r13)
            if (r13 >= 0) goto L_0x0032
            org.bitcoinj.core.Coin r12 = org.bitcoinj.core.Transaction.REFERENCE_DEFAULT_MIN_TX_FEE
        L_0x0032:
            r13 = r12
            r12 = r22
            org.bitcoinj.core.Coin r14 = r12.add(r13)
            if (r6 == 0) goto L_0x003f
            org.bitcoinj.core.Coin r14 = r14.add(r6)
        L_0x003f:
            org.bitcoinj.wallet.CoinSelector r15 = r1.coinSelector
            if (r15 != 0) goto L_0x0046
            org.bitcoinj.wallet.CoinSelector r15 = r0.coinSelector
            goto L_0x0048
        L_0x0046:
            org.bitcoinj.wallet.CoinSelector r15 = r1.coinSelector
        L_0x0048:
            java.util.LinkedList r4 = new java.util.LinkedList
            r3 = r25
            r4.<init>(r3)
            org.bitcoinj.wallet.CoinSelection r4 = r15.select(r14, r4)
            org.bitcoinj.core.Coin r15 = r4.valueGathered
            int r15 = r15.compareTo(r14)
            if (r15 >= 0) goto L_0x0063
            org.bitcoinj.core.Coin r3 = r4.valueGathered
            org.bitcoinj.core.Coin r4 = r14.subtract(r3)
            goto L_0x01c9
        L_0x0063:
            java.util.Collection<org.bitcoinj.core.TransactionOutput> r15 = r4.gathered
            int r15 = r15.size()
            r17 = 1
            if (r15 > 0) goto L_0x0076
            int r15 = r23.size()
            if (r15 <= 0) goto L_0x0074
            goto L_0x0076
        L_0x0074:
            r15 = 0
            goto L_0x0077
        L_0x0076:
            r15 = 1
        L_0x0077:
            com.google.common.base.Preconditions.checkState(r15)
            org.bitcoinj.core.Coin r15 = r4.valueGathered
            org.bitcoinj.core.Coin r14 = r15.subtract(r14)
            if (r6 == 0) goto L_0x0086
            org.bitcoinj.core.Coin r14 = r14.add(r6)
        L_0x0086:
            boolean r15 = r1.ensureMinRequiredFee
            if (r15 == 0) goto L_0x00b0
            org.bitcoinj.core.Coin r15 = org.bitcoinj.core.Coin.ZERO
            boolean r15 = r14.equals(r15)
            if (r15 != 0) goto L_0x00b0
            org.bitcoinj.core.Coin r15 = org.bitcoinj.core.Coin.CENT
            int r15 = r14.compareTo(r15)
            if (r15 >= 0) goto L_0x00b0
            org.bitcoinj.core.Coin r15 = org.bitcoinj.core.Transaction.REFERENCE_DEFAULT_MIN_TX_FEE
            int r15 = r13.compareTo(r15)
            if (r15 >= 0) goto L_0x00b0
            org.bitcoinj.core.Coin r15 = org.bitcoinj.core.Coin.CENT
            org.bitcoinj.core.Coin r3 = org.bitcoinj.core.Transaction.REFERENCE_DEFAULT_MIN_TX_FEE
            org.bitcoinj.core.Coin r3 = r3.subtract(r13)
            org.bitcoinj.core.Coin r14 = r14.subtract(r3)
            r3 = 1
            goto L_0x00b2
        L_0x00b0:
            r15 = r6
            r3 = 0
        L_0x00b2:
            int r13 = r14.signum()
            if (r13 <= 0) goto L_0x0119
            org.bitcoinj.core.Address r13 = r1.changeAddress
            if (r13 != 0) goto L_0x00c0
            org.bitcoinj.core.Address r13 = r20.currentChangeAddress()
        L_0x00c0:
            r18 = r10
            org.bitcoinj.core.TransactionOutput r10 = new org.bitcoinj.core.TransactionOutput
            r19 = r11
            org.bitcoinj.core.NetworkParameters r11 = r0.params
            org.bitcoinj.core.Transaction r12 = r1.f829tx
            r10.<init>(r11, r12, r14, r13)
            boolean r11 = r1.ensureMinRequiredFee
            if (r11 == 0) goto L_0x00eb
            boolean r11 = r10.isDust()
            if (r11 == 0) goto L_0x00eb
            org.bitcoinj.core.Coin r11 = org.bitcoinj.core.Transaction.REFERENCE_DEFAULT_MIN_TX_FEE
            org.bitcoinj.core.Coin r12 = r10.getMinNonDustValue()
            org.bitcoinj.core.Coin r13 = org.bitcoinj.core.Coin.SATOSHI
            org.bitcoinj.core.Coin r12 = r12.add(r13)
            org.bitcoinj.core.Coin r11 = r11.add(r12)
            r15 = r11
            r11 = 0
            r12 = 0
            goto L_0x012b
        L_0x00eb:
            byte[] r11 = r10.unsafeBitcoinSerialize()
            int r11 = r11.length
            org.bitcoinj.core.Transaction r12 = r1.f829tx
            java.util.List r12 = r12.getOutputs()
            int r12 = r12.size()
            long r12 = (long) r12
            int r12 = org.bitcoinj.core.VarInt.sizeOf(r12)
            int r11 = r11 + r12
            org.bitcoinj.core.Transaction r12 = r1.f829tx
            java.util.List r12 = r12.getOutputs()
            int r12 = r12.size()
            int r12 = r12 + -1
            long r12 = (long) r12
            int r12 = org.bitcoinj.core.VarInt.sizeOf(r12)
            int r11 = r11 - r12
            r12 = 0
            int r11 = r11 + r12
            if (r3 != 0) goto L_0x012f
            r13 = 0
            r15 = 0
            goto L_0x0130
        L_0x0119:
            r18 = r10
            r19 = r11
            r12 = 0
            if (r3 == 0) goto L_0x012d
            org.bitcoinj.core.Coin r10 = org.bitcoinj.core.Transaction.REFERENCE_DEFAULT_MIN_TX_FEE
            org.bitcoinj.core.Coin r11 = org.bitcoinj.core.Coin.SATOSHI
            org.bitcoinj.core.Coin r10 = r10.add(r11)
            r15 = r10
            r10 = 0
            r11 = 0
        L_0x012b:
            r13 = 1
            goto L_0x0130
        L_0x012d:
            r10 = 0
            r11 = 0
        L_0x012f:
            r13 = 0
        L_0x0130:
            java.util.Collection<org.bitcoinj.core.TransactionOutput> r14 = r4.gathered
            java.util.Iterator r14 = r14.iterator()
        L_0x0136:
            boolean r16 = r14.hasNext()
            if (r16 == 0) goto L_0x015d
            java.lang.Object r16 = r14.next()
            r12 = r16
            org.bitcoinj.core.TransactionOutput r12 = (org.bitcoinj.core.TransactionOutput) r12
            r16 = r14
            org.bitcoinj.core.Transaction r14 = r1.f829tx
            org.bitcoinj.core.TransactionInput r12 = r14.addInput(r12)
            byte[] r12 = r12.getScriptBytes()
            int r12 = r12.length
            if (r12 != 0) goto L_0x0155
            r12 = 1
            goto L_0x0156
        L_0x0155:
            r12 = 0
        L_0x0156:
            com.google.common.base.Preconditions.checkState(r12)
            r14 = r16
            r12 = 0
            goto L_0x0136
        L_0x015d:
            org.bitcoinj.core.Transaction r12 = r1.f829tx
            byte[] r12 = r12.unsafeBitcoinSerialize()
            int r12 = r12.length
            int r11 = r11 + r12
            int r12 = r0.estimateBytesForSigning(r4)
            int r11 = r11 + r12
            if (r11 <= r5) goto L_0x017b
            org.bitcoinj.core.Coin r12 = r1.feePerKb
            int r12 = r12.signum()
            if (r12 <= 0) goto L_0x017b
            r5 = r11
            r10 = r18
            r11 = r19
            goto L_0x0016
        L_0x017b:
            if (r13 == 0) goto L_0x0185
            if (r7 != 0) goto L_0x0180
            r7 = r4
        L_0x0180:
            r10 = r18
        L_0x0182:
            r11 = r19
            goto L_0x01b5
        L_0x0185:
            if (r3 == 0) goto L_0x01a1
            if (r9 != 0) goto L_0x018b
            r3 = 1
            goto L_0x018c
        L_0x018b:
            r3 = 0
        L_0x018c:
            com.google.common.base.Preconditions.checkState(r3)
            org.bitcoinj.core.Coin r3 = org.bitcoinj.core.Coin.CENT
            boolean r3 = r15.equals(r3)
            com.google.common.base.Preconditions.checkState(r3)
            java.lang.Object r3 = com.google.common.base.Preconditions.checkNotNull(r10)
            org.bitcoinj.core.TransactionOutput r3 = (org.bitcoinj.core.TransactionOutput) r3
            r10 = r3
            r9 = r4
            goto L_0x0182
        L_0x01a1:
            if (r8 != 0) goto L_0x01a5
            r3 = 1
            goto L_0x01a6
        L_0x01a5:
            r3 = 0
        L_0x01a6:
            com.google.common.base.Preconditions.checkState(r3)
            if (r15 != 0) goto L_0x01ad
            r3 = 1
            goto L_0x01ae
        L_0x01ad:
            r3 = 0
        L_0x01ae:
            com.google.common.base.Preconditions.checkState(r3)
            r8 = r4
            r11 = r10
            r10 = r18
        L_0x01b5:
            if (r15 == 0) goto L_0x01c8
            if (r6 == 0) goto L_0x01c5
            int r3 = r15.compareTo(r6)
            if (r3 <= 0) goto L_0x01c0
            goto L_0x01c2
        L_0x01c0:
            r17 = 0
        L_0x01c2:
            com.google.common.base.Preconditions.checkState(r17)
        L_0x01c5:
            r6 = r15
            goto L_0x0016
        L_0x01c8:
            r4 = 0
        L_0x01c9:
            r0.resetTxInputs(r1, r2)
            if (r7 != 0) goto L_0x01e7
            if (r9 != 0) goto L_0x01e7
            if (r8 == 0) goto L_0x01d3
            goto L_0x01e7
        L_0x01d3:
            com.google.common.base.Preconditions.checkNotNull(r4)
            org.slf4j.Logger r1 = log
            java.lang.String r2 = r4.toFriendlyString()
            java.lang.String r3 = "Insufficient value in wallet for send: needed {} more"
            r1.warn(r3, r2)
            org.bitcoinj.core.InsufficientMoneyException r1 = new org.bitcoinj.core.InsufficientMoneyException
            r1.<init>(r4)
            throw r1
        L_0x01e7:
            org.bitcoinj.wallet.Wallet$FeeCalculation r1 = new org.bitcoinj.wallet.Wallet$FeeCalculation
            r2 = 0
            r1.<init>()
            if (r8 == 0) goto L_0x0204
            if (r11 == 0) goto L_0x01fc
            org.bitcoinj.core.Coin r2 = r8.valueGathered
            org.bitcoinj.core.Coin r3 = r11.getValue()
            org.bitcoinj.core.Coin r2 = r2.subtract(r3)
            goto L_0x01fe
        L_0x01fc:
            org.bitcoinj.core.Coin r2 = r8.valueGathered
        L_0x01fe:
            r4 = r2
            r1.bestCoinSelection = r8
            r1.bestChangeOutput = r11
            goto L_0x0205
        L_0x0204:
            r4 = 0
        L_0x0205:
            if (r9 == 0) goto L_0x0224
            org.bitcoinj.core.Coin r2 = r9.valueGathered
            java.lang.Object r3 = com.google.common.base.Preconditions.checkNotNull(r10)
            org.bitcoinj.core.TransactionOutput r3 = (org.bitcoinj.core.TransactionOutput) r3
            org.bitcoinj.core.Coin r3 = r3.getValue()
            org.bitcoinj.core.Coin r2 = r2.subtract(r3)
            if (r4 == 0) goto L_0x021f
            int r3 = r2.compareTo(r4)
            if (r3 >= 0) goto L_0x0224
        L_0x021f:
            r1.bestCoinSelection = r9
            r1.bestChangeOutput = r10
            goto L_0x0225
        L_0x0224:
            r2 = r4
        L_0x0225:
            if (r7 == 0) goto L_0x0236
            if (r2 == 0) goto L_0x0231
            org.bitcoinj.core.Coin r3 = r7.valueGathered
            int r2 = r3.compareTo(r2)
            if (r2 >= 0) goto L_0x0236
        L_0x0231:
            r1.bestCoinSelection = r7
            r2 = 0
            r1.bestChangeOutput = r2
        L_0x0236:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.C3530Wallet.calculateFee(org.bitcoinj.wallet.SendRequest, org.bitcoinj.core.Coin, java.util.List, boolean, java.util.List):org.bitcoinj.wallet.Wallet$FeeCalculation");
    }

    private void resetTxInputs(SendRequest sendRequest, List<TransactionInput> list) {
        sendRequest.f829tx.clearInputs();
        for (TransactionInput addInput : list) {
            sendRequest.f829tx.addInput(addInput);
        }
    }

    private int estimateBytesForSigning(CoinSelection coinSelection) {
        Script script;
        int i = 0;
        for (TransactionOutput scriptPubKey : coinSelection.gathered) {
            try {
                Script scriptPubKey2 = scriptPubKey.getScriptPubKey();
                String str = "Coin selection includes unspendable outputs";
                ECKey eCKey = null;
                if (scriptPubKey2.isSentToAddress()) {
                    ECKey findKeyFromPubHash = findKeyFromPubHash(scriptPubKey2.getPubKeyHash());
                    Preconditions.checkNotNull(findKeyFromPubHash, str);
                    eCKey = findKeyFromPubHash;
                    script = null;
                } else if (scriptPubKey2.isPayToScriptHash()) {
                    script = findRedeemDataFromScriptHash(scriptPubKey2.getPubKeyHash()).redeemScript;
                    Preconditions.checkNotNull(script, str);
                } else {
                    script = null;
                }
                i += scriptPubKey2.getNumberOfBytesRequiredToSpend(eCKey, script);
            } catch (ScriptException e) {
                throw new IllegalStateException(e);
            }
        }
        return i;
    }

    public void setTransactionBroadcaster(@Nullable TransactionBroadcaster transactionBroadcaster) {
        Transaction[] transactionArr = new Transaction[0];
        this.lock.lock();
        try {
            if (this.vTransactionBroadcaster != transactionBroadcaster) {
                this.vTransactionBroadcaster = transactionBroadcaster;
                if (transactionBroadcaster != null) {
                    Transaction[] transactionArr2 = (Transaction[]) this.pending.values().toArray(transactionArr);
                    this.lock.unlock();
                    for (Transaction transaction : transactionArr2) {
                        ConfidenceType confidenceType = transaction.getConfidence().getConfidenceType();
                        Preconditions.checkState(confidenceType == ConfidenceType.PENDING || confidenceType == ConfidenceType.IN_CONFLICT, "Expected PENDING or IN_CONFLICT, was %s.", confidenceType);
                        log.info("New broadcaster so uploading waiting tx {}", (Object) transaction.getHash());
                        transactionBroadcaster.broadcastTransaction(transaction);
                    }
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void setKeyRotationTime(Date date) {
        setKeyRotationTime(date.getTime() / 1000);
    }

    @Nullable
    public Date getKeyRotationTime() {
        long j = this.vKeyRotationTimestamp;
        if (j != 0) {
            return new Date(j * 1000);
        }
        return null;
    }

    public void setKeyRotationTime(long j) {
        Preconditions.checkArgument(j <= C3447Utils.currentTimeSeconds(), "Given time (%s) cannot be in the future.", C3447Utils.dateTimeFormat(1000 * j));
        this.vKeyRotationTimestamp = j;
        saveNow();
    }

    public boolean isKeyRotating(ECKey eCKey) {
        long j = this.vKeyRotationTimestamp;
        return j != 0 && eCKey.getCreationTimeSeconds() < j;
    }

    @Deprecated
    public ListenableFuture<List<Transaction>> maybeDoMaintenance(@Nullable KeyParameter keyParameter, boolean z) throws DeterministicUpgradeRequiresPassword {
        return doMaintenance(keyParameter, z);
    }

    public ListenableFuture<List<Transaction>> doMaintenance(@Nullable KeyParameter keyParameter, boolean z) throws DeterministicUpgradeRequiresPassword {
        this.lock.lock();
        this.keyChainGroupLock.lock();
        try {
            List<Transaction> maybeRotateKeys = maybeRotateKeys(keyParameter, z);
            if (!z) {
                return Futures.immediateFuture(maybeRotateKeys);
            }
            this.keyChainGroupLock.unlock();
            this.lock.unlock();
            Preconditions.checkState(!this.lock.isHeldByCurrentThread());
            ArrayList arrayList = new ArrayList(maybeRotateKeys.size());
            TransactionBroadcaster transactionBroadcaster = this.vTransactionBroadcaster;
            for (Transaction broadcastTransaction : maybeRotateKeys) {
                try {
                    ListenableFuture future = transactionBroadcaster.broadcastTransaction(broadcastTransaction).future();
                    arrayList.add(future);
                    Futures.addCallback(future, new FutureCallback<Transaction>() {
                        public void onSuccess(Transaction transaction) {
                            C3530Wallet.log.info("Successfully broadcast key rotation tx: {}", (Object) transaction);
                        }

                        public void onFailure(Throwable th) {
                            C3530Wallet.log.error("Failed to broadcast key rotation tx", th);
                        }
                    });
                } catch (Exception e) {
                    log.error("Failed to broadcast rekey tx", (Throwable) e);
                }
            }
            return Futures.allAsList((Iterable<? extends ListenableFuture<? extends V>>) arrayList);
        } finally {
            this.keyChainGroupLock.unlock();
            this.lock.unlock();
        }
    }

    @GuardedBy("keyChainGroupLock")
    private List<Transaction> maybeRotateKeys(@Nullable KeyParameter keyParameter, boolean z) throws DeterministicUpgradeRequiresPassword {
        Transaction rekeyOneBatch;
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Preconditions.checkState(this.keyChainGroupLock.isHeldByCurrentThread());
        LinkedList newLinkedList = Lists.newLinkedList();
        long j = this.vKeyRotationTimestamp;
        if (j == 0) {
            return newLinkedList;
        }
        boolean z2 = true;
        Iterator it = this.keyChainGroup.getDeterministicKeyChains().iterator();
        while (true) {
            if (it.hasNext()) {
                if (((DeterministicKeyChain) it.next()).getEarliestKeyCreationTime() >= j) {
                    z2 = false;
                    break;
                }
            } else {
                break;
            }
        }
        if (z2) {
            try {
                if (this.keyChainGroup.getImportedKeys().isEmpty()) {
                    log.info("All HD chains are currently rotating and we have no random keys, creating fresh HD chain ...");
                    this.keyChainGroup.createAndActivateNewHDChain();
                } else {
                    log.info("All HD chains are currently rotating, attempting to create a new one from the next oldest non-rotating key material ...");
                    this.keyChainGroup.upgradeToDeterministic(j, keyParameter);
                    log.info(" ... upgraded to HD again, based on next best oldest key.");
                }
            } catch (AllRandomKeysRotating unused) {
                log.info(" ... no non-rotating random keys available, generating entirely new HD tree: backup required after this.");
                this.keyChainGroup.createAndActivateNewHDChain();
            }
            saveNow();
        }
        do {
            rekeyOneBatch = rekeyOneBatch(j, keyParameter, newLinkedList, z);
            if (rekeyOneBatch != null) {
                newLinkedList.add(rekeyOneBatch);
            }
            if (rekeyOneBatch == null) {
                break;
            }
        } while (rekeyOneBatch.getInputs().size() == 600);
        return newLinkedList;
    }

    @Nullable
    private Transaction rekeyOneBatch(long j, @Nullable KeyParameter keyParameter, List<Transaction> list, boolean z) {
        this.lock.lock();
        try {
            boolean z2 = true;
            FilteringCoinSelector filteringCoinSelector = new FilteringCoinSelector(new KeyTimeCoinSelector(this, j, true));
            for (Transaction excludeOutputsSpentBy : list) {
                filteringCoinSelector.excludeOutputsSpentBy(excludeOutputsSpentBy);
            }
            CoinSelection select = filteringCoinSelector.select(Coin.ZERO, calculateAllSpendCandidates());
            if (!select.valueGathered.equals(Coin.ZERO)) {
                maybeUpgradeToHD(keyParameter);
                Transaction transaction = new Transaction(this.params);
                for (TransactionOutput addInput : select.gathered) {
                    transaction.addInput(addInput);
                }
                transaction.addOutput(select.valueGathered, z ? freshReceiveAddress() : currentReceiveAddress());
                if (!adjustOutputDownwardsForFee(transaction, select, Transaction.DEFAULT_TX_FEE, true)) {
                    log.error("Failed to adjust rekey tx for fees.");
                } else {
                    transaction.getConfidence().setSource(Source.SELF);
                    transaction.setPurpose(Purpose.KEY_ROTATION);
                    SendRequest forTx = SendRequest.forTx(transaction);
                    forTx.aesKey = keyParameter;
                    if (z) {
                        signTransaction(forTx);
                    }
                    if (transaction.unsafeBitcoinSerialize().length >= 100000) {
                        z2 = false;
                    }
                    Preconditions.checkState(z2);
                    this.lock.unlock();
                    return transaction;
                }
            }
            this.lock.unlock();
            return null;
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }
}
