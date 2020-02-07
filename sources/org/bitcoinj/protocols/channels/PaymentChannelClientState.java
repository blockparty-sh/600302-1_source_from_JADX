package org.bitcoinj.protocols.channels;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import javax.annotation.Nullable;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.params.KeyParameter;

public abstract class PaymentChannelClientState {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(PaymentChannelClientState.class);
    final ECKey myKey;
    final ECKey serverKey;
    protected final StateMachine<State> stateMachine = new StateMachine<>(State.UNINITIALISED, getStateTransitions());
    protected StoredClientChannel storedChannel;
    protected Coin valueToMe;
    final C3530Wallet wallet;

    public static class IncrementedPayment {
        public Coin amount;
        public TransactionSignature signature;
    }

    public enum State {
        UNINITIALISED,
        NEW,
        INITIATED,
        WAITING_FOR_SIGNED_REFUND,
        SAVE_STATE_IN_WALLET,
        PROVIDE_MULTISIG_CONTRACT_TO_SERVER,
        READY,
        EXPIRED,
        CLOSED
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public abstract void doStoreChannelInWallet(Sha256Hash sha256Hash);

    /* access modifiers changed from: protected */
    public void editContractSendRequest(SendRequest sendRequest) {
    }

    public abstract Transaction getContract();

    /* access modifiers changed from: protected */
    public abstract Transaction getContractInternal();

    /* access modifiers changed from: protected */
    public abstract Script getContractScript();

    /* access modifiers changed from: protected */
    public abstract long getExpiryTime();

    public abstract int getMajorVersion();

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public abstract Transaction getRefundTransaction();

    public abstract Coin getRefundTxFees();

    /* access modifiers changed from: protected */
    public abstract Script getSignedScript();

    /* access modifiers changed from: protected */
    public abstract Multimap<State, State> getStateTransitions();

    public abstract Coin getTotalValue();

    /* access modifiers changed from: protected */
    public abstract Coin getValueToMe();

    public abstract void initiate(@Nullable KeyParameter keyParameter) throws ValueOutOfRangeException, InsufficientMoneyException;

    PaymentChannelClientState(StoredClientChannel storedClientChannel, C3530Wallet wallet2) throws VerificationException {
        this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
        this.myKey = (ECKey) Preconditions.checkNotNull(storedClientChannel.myKey);
        this.serverKey = (ECKey) Preconditions.checkNotNull(storedClientChannel.serverKey);
        this.storedChannel = storedClientChannel;
        this.valueToMe = (Coin) Preconditions.checkNotNull(storedClientChannel.valueToMe);
    }

    public synchronized boolean isSettlementTransaction(Transaction transaction) {
        try {
            transaction.verify();
            transaction.getInput(0).verify(getContractInternal().getOutput(0));
        } catch (VerificationException unused) {
            return false;
        }
        return true;
    }

    public PaymentChannelClientState(C3530Wallet wallet2, ECKey eCKey, ECKey eCKey2, Coin coin, long j) throws VerificationException {
        this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
        this.serverKey = (ECKey) Preconditions.checkNotNull(eCKey2);
        this.myKey = (ECKey) Preconditions.checkNotNull(eCKey);
        this.valueToMe = (Coin) Preconditions.checkNotNull(coin);
    }

    /* access modifiers changed from: protected */
    public synchronized void initWalletListeners() {
        if (!(this.storedChannel == null || this.storedChannel.close == null)) {
            watchCloseConfirmations();
        }
        this.wallet.addCoinsReceivedEventListener(Threading.SAME_THREAD, new WalletCoinsReceivedEventListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onCoinsReceived(org.bitcoinj.wallet.C3530Wallet r3, org.bitcoinj.core.Transaction r4, org.bitcoinj.core.Coin r5, org.bitcoinj.core.Coin r6) {
                /*
                    r2 = this;
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r3 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this
                    monitor-enter(r3)
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r5 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    org.bitcoinj.core.Transaction r5 = r5.getContractInternal()     // Catch:{ all -> 0x004f }
                    if (r5 != 0) goto L_0x000d
                    monitor-exit(r3)     // Catch:{ all -> 0x004f }
                    return
                L_0x000d:
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r5 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    boolean r5 = r5.isSettlementTransaction(r4)     // Catch:{ all -> 0x004f }
                    if (r5 == 0) goto L_0x004d
                    org.slf4j.Logger r5 = org.bitcoinj.protocols.channels.PaymentChannelClientState.log     // Catch:{ all -> 0x004f }
                    java.lang.String r6 = "Close: transaction {} closed contract {}"
                    org.bitcoinj.core.Sha256Hash r0 = r4.getHash()     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r1 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    org.bitcoinj.core.Transaction r1 = r1.getContractInternal()     // Catch:{ all -> 0x004f }
                    org.bitcoinj.core.Sha256Hash r1 = r1.getHash()     // Catch:{ all -> 0x004f }
                    r5.info(r6, r0, r1)     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r5 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.StateMachine<org.bitcoinj.protocols.channels.PaymentChannelClientState$State> r5 = r5.stateMachine     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.PaymentChannelClientState$State r6 = org.bitcoinj.protocols.channels.PaymentChannelClientState.State.CLOSED     // Catch:{ all -> 0x004f }
                    r5.transition(r6)     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r5 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.StoredClientChannel r5 = r5.storedChannel     // Catch:{ all -> 0x004f }
                    if (r5 != 0) goto L_0x003d
                    monitor-exit(r3)     // Catch:{ all -> 0x004f }
                    return
                L_0x003d:
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r5 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.StoredClientChannel r5 = r5.storedChannel     // Catch:{ all -> 0x004f }
                    r5.close = r4     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r4 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    r4.updateChannelInWallet()     // Catch:{ all -> 0x004f }
                    org.bitcoinj.protocols.channels.PaymentChannelClientState r4 = org.bitcoinj.protocols.channels.PaymentChannelClientState.this     // Catch:{ all -> 0x004f }
                    r4.watchCloseConfirmations()     // Catch:{ all -> 0x004f }
                L_0x004d:
                    monitor-exit(r3)     // Catch:{ all -> 0x004f }
                    return
                L_0x004f:
                    r4 = move-exception
                    monitor-exit(r3)     // Catch:{ all -> 0x004f }
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.PaymentChannelClientState.C34671.onCoinsReceived(org.bitcoinj.wallet.Wallet, org.bitcoinj.core.Transaction, org.bitcoinj.core.Coin, org.bitcoinj.core.Coin):void");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void watchCloseConfirmations() {
        Futures.addCallback(this.storedChannel.close.getConfidence().getDepthFuture(Context.get().getEventHorizon(), Threading.SAME_THREAD), new FutureCallback<TransactionConfidence>() {
            public void onSuccess(TransactionConfidence transactionConfidence) {
                PaymentChannelClientState.this.deleteChannelFromWallet();
            }

            public void onFailure(Throwable th) {
                Throwables.propagate(th);
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void deleteChannelFromWallet() {
        log.info("Close tx has confirmed, deleting channel from wallet: {}", (Object) this.storedChannel);
        ((StoredPaymentChannelClientStates) this.wallet.getExtensions().get(StoredPaymentChannelClientStates.EXTENSION_ID)).removeChannel(this.storedChannel);
        this.storedChannel = null;
    }

    public synchronized State getState() {
        return (State) this.stateMachine.getState();
    }

    public void initiate() throws ValueOutOfRangeException, InsufficientMoneyException {
        initiate(null);
    }

    private synchronized Transaction makeUnsignedChannelContract(Coin coin) throws ValueOutOfRangeException {
        Transaction transaction;
        transaction = new Transaction(this.wallet.getParams());
        transaction.addInput(getContractInternal().getOutput(0));
        transaction.addOutput(coin, this.myKey.toAddress(this.wallet.getParams()));
        return transaction;
    }

    public synchronized void checkNotExpired() {
        if (C3447Utils.currentTimeSeconds() > getExpiryTime()) {
            this.stateMachine.transition(State.EXPIRED);
            disconnectFromChannel();
            throw new IllegalStateException("Channel expired");
        }
    }

    public synchronized IncrementedPayment incrementPaymentBy(Coin coin, @Nullable KeyParameter keyParameter) throws ValueOutOfRangeException {
        SigHash sigHash;
        TransactionSignature transactionSignature;
        IncrementedPayment incrementedPayment;
        this.stateMachine.checkState(State.READY);
        checkNotExpired();
        Preconditions.checkNotNull(coin);
        if (coin.signum() >= 0) {
            Coin subtract = getValueToMe().subtract(coin);
            if (subtract.compareTo(Transaction.MIN_NONDUST_OUTPUT) < 0 && subtract.signum() > 0) {
                log.info("New value being sent back as change was smaller than minimum nondust output, sending all");
                coin = getValueToMe();
                subtract = Coin.ZERO;
            }
            if (subtract.signum() >= 0) {
                Transaction makeUnsignedChannelContract = makeUnsignedChannelContract(subtract);
                log.info("Signing new payment tx {}", (Object) makeUnsignedChannelContract);
                if (subtract.equals(Coin.ZERO)) {
                    sigHash = SigHash.NONE;
                } else {
                    sigHash = SigHash.SINGLE;
                }
                if (makeUnsignedChannelContract.getVersion() >= 2) {
                    transactionSignature = makeUnsignedChannelContract.calculateWitnessSignature(0, this.myKey.maybeDecrypt(keyParameter), getSignedScript(), makeUnsignedChannelContract.getInput(0).getConnectedOutput().getValue(), sigHash, true);
                } else {
                    transactionSignature = makeUnsignedChannelContract.calculateSignature(0, this.myKey.maybeDecrypt(keyParameter), getSignedScript(), sigHash, true);
                }
                this.valueToMe = subtract;
                updateChannelInWallet();
                incrementedPayment = new IncrementedPayment();
                incrementedPayment.signature = transactionSignature;
                incrementedPayment.amount = coin;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Channel has too little money to pay ");
                sb.append(coin);
                sb.append(" satoshis");
                throw new ValueOutOfRangeException(sb.toString());
            }
        } else {
            throw new ValueOutOfRangeException("Tried to decrement payment");
        }
        return incrementedPayment;
    }

    /* access modifiers changed from: protected */
    public synchronized void updateChannelInWallet() {
        if (this.storedChannel != null) {
            this.storedChannel.valueToMe = getValueToMe();
            ((StoredPaymentChannelClientStates) this.wallet.getExtensions().get(StoredPaymentChannelClientStates.EXTENSION_ID)).updatedChannel(this.storedChannel);
        }
    }

    public synchronized void disconnectFromChannel() {
        if (this.storedChannel != null) {
            synchronized (this.storedChannel) {
                this.storedChannel.active = false;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void fakeSave() {
        try {
            this.wallet.commitTx(getContractInternal());
            this.stateMachine.transition(State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER);
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void storeChannelInWallet(Sha256Hash sha256Hash) {
        this.stateMachine.checkState(State.SAVE_STATE_IN_WALLET);
        Preconditions.checkState(sha256Hash != null);
        if (this.storedChannel != null) {
            Preconditions.checkState(this.storedChannel.f817id.equals(sha256Hash));
            return;
        }
        doStoreChannelInWallet(sha256Hash);
        try {
            this.wallet.commitTx(getContractInternal());
            this.stateMachine.transition(State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER);
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Coin getValueRefunded() {
        this.stateMachine.checkState(State.READY);
        return this.valueToMe;
    }

    public synchronized Coin getValueSpent() {
        return getTotalValue().subtract(getValueRefunded());
    }
}
