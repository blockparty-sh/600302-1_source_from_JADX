package org.bitcoinj.protocols.channels;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Arrays;
import javax.annotation.Nullable;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PaymentChannelServerState {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(PaymentChannelServerState.class);
    protected byte[] bestValueSignature;
    protected Coin bestValueToMe;
    protected final TransactionBroadcaster broadcaster;
    protected Transaction contract;
    protected long minExpireTime;
    protected ECKey serverKey;
    protected StateMachine<State> stateMachine;
    protected StoredServerChannel storedServerChannel;
    final C3530Wallet wallet;

    public enum State {
        UNINITIALISED,
        WAITING_FOR_REFUND_TRANSACTION,
        WAITING_FOR_MULTISIG_CONTRACT,
        WAITING_FOR_MULTISIG_ACCEPTANCE,
        READY,
        CLOSING,
        CLOSED,
        ERROR
    }

    public abstract ListenableFuture<Transaction> close() throws InsufficientMoneyException;

    /* access modifiers changed from: protected */
    public abstract Script createOutputScript();

    /* access modifiers changed from: protected */
    public abstract ECKey getClientKey();

    public abstract TransactionOutput getClientOutput();

    public abstract Coin getFeePaid();

    public abstract int getMajorVersion();

    /* access modifiers changed from: protected */
    public abstract Script getSignedScript();

    /* access modifiers changed from: protected */
    public abstract Multimap<State, State> getStateTransitions();

    /* access modifiers changed from: protected */
    public void verifyContract(Transaction transaction) {
    }

    PaymentChannelServerState(StoredServerChannel storedServerChannel2, C3530Wallet wallet2, TransactionBroadcaster transactionBroadcaster) throws VerificationException {
        boolean z;
        this.bestValueToMe = Coin.ZERO;
        this.storedServerChannel = null;
        this.contract = null;
        synchronized (storedServerChannel2) {
            this.stateMachine = new StateMachine<>(State.UNINITIALISED, getStateTransitions());
            this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
            this.broadcaster = (TransactionBroadcaster) Preconditions.checkNotNull(transactionBroadcaster);
            this.contract = (Transaction) Preconditions.checkNotNull(storedServerChannel2.contract);
            this.serverKey = (ECKey) Preconditions.checkNotNull(storedServerChannel2.myKey);
            this.storedServerChannel = storedServerChannel2;
            this.bestValueToMe = (Coin) Preconditions.checkNotNull(storedServerChannel2.bestValueToMe);
            this.minExpireTime = storedServerChannel2.refundTransactionUnlockTimeSecs;
            this.bestValueSignature = storedServerChannel2.bestValueSignature;
            if (!this.bestValueToMe.equals(Coin.ZERO)) {
                if (this.bestValueSignature == null) {
                    z = false;
                    Preconditions.checkArgument(z);
                    storedServerChannel2.state = this;
                }
            }
            z = true;
            Preconditions.checkArgument(z);
            storedServerChannel2.state = this;
        }
    }

    public PaymentChannelServerState(TransactionBroadcaster transactionBroadcaster, C3530Wallet wallet2, ECKey eCKey, long j) {
        this.bestValueToMe = Coin.ZERO;
        this.storedServerChannel = null;
        this.contract = null;
        this.stateMachine = new StateMachine<>(State.UNINITIALISED, getStateTransitions());
        this.serverKey = (ECKey) Preconditions.checkNotNull(eCKey);
        this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
        this.broadcaster = (TransactionBroadcaster) Preconditions.checkNotNull(transactionBroadcaster);
        this.minExpireTime = j;
    }

    public synchronized State getState() {
        return (State) this.stateMachine.getState();
    }

    public synchronized ListenableFuture<PaymentChannelServerState> provideContract(final Transaction transaction) throws VerificationException {
        final SettableFuture create;
        Preconditions.checkNotNull(transaction);
        this.stateMachine.checkState(State.WAITING_FOR_MULTISIG_CONTRACT);
        try {
            transaction.verify();
            this.contract = transaction;
            verifyContract(transaction);
            if (!Arrays.equals(getContractScript().getProgram(), createOutputScript().getProgram())) {
                throw new VerificationException(getMajorVersion() == 1 ? "Contract's first output was not a standard 2-of-2 multisig to client and server in that order." : "Contract was not a P2SH script of a CLTV redeem script to client and server");
            } else if (getTotalValue().signum() > 0) {
                log.info("Broadcasting multisig contract: {}", (Object) transaction);
                this.wallet.addWatchedScripts(ImmutableList.m127of(transaction.getOutput(0).getScriptPubKey()));
                this.stateMachine.transition(State.WAITING_FOR_MULTISIG_ACCEPTANCE);
                create = SettableFuture.create();
                Futures.addCallback(this.broadcaster.broadcastTransaction(transaction).future(), new FutureCallback<Transaction>() {
                    public void onSuccess(Transaction transaction) {
                        PaymentChannelServerState.log.info("Successfully broadcast multisig contract {}. Channel now open.", (Object) transaction.getHashAsString());
                        try {
                            PaymentChannelServerState.this.wallet.receivePending(transaction, null, true);
                            PaymentChannelServerState.this.stateMachine.transition(State.READY);
                            create.set(PaymentChannelServerState.this);
                        } catch (VerificationException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    public void onFailure(Throwable th) {
                        PaymentChannelServerState.log.error("Failed to broadcast contract", th);
                        PaymentChannelServerState.this.stateMachine.transition(State.ERROR);
                        create.setException(th);
                    }
                });
            } else {
                throw new VerificationException("Not accepting an attempt to open a contract with zero value.");
            }
        } catch (VerificationException e) {
            log.error("Provided multisig contract did not verify: {}", (Object) transaction.toString());
            throw e;
        }
        return create;
    }

    /* access modifiers changed from: protected */
    public synchronized SendRequest makeUnsignedChannelContract(Coin coin) {
        Transaction transaction;
        transaction = new Transaction(this.wallet.getParams());
        if (!getTotalValue().subtract(coin).equals(Coin.ZERO)) {
            transaction.addOutput(getTotalValue().subtract(coin), getClientKey().toAddress(this.wallet.getParams()));
        }
        transaction.addInput(this.contract.getOutput(0));
        return SendRequest.forTx(transaction);
    }

    public synchronized boolean incrementPayment(Coin coin, byte[] bArr) throws VerificationException, ValueOutOfRangeException, InsufficientMoneyException {
        SigHash sigHash;
        Sha256Hash sha256Hash;
        boolean z;
        Coin coin2 = coin;
        byte[] bArr2 = bArr;
        synchronized (this) {
            this.stateMachine.checkState(State.READY);
            Preconditions.checkNotNull(coin);
            Preconditions.checkNotNull(bArr);
            TransactionSignature decodeFromBitcoin = TransactionSignature.decodeFromBitcoin(bArr2, true);
            boolean equals = coin2.equals(Coin.ZERO);
            Coin subtract = getTotalValue().subtract(coin2);
            if (subtract.signum() < 0) {
                throw new ValueOutOfRangeException("Attempt to refund more than the contract allows.");
            } else if (subtract.compareTo(this.bestValueToMe) >= 0) {
                SendRequest makeUnsignedChannelContract = makeUnsignedChannelContract(subtract);
                if (!equals) {
                    if (coin2.isLessThan(makeUnsignedChannelContract.f829tx.getOutput(0).getMinNonDustValue())) {
                        throw new ValueOutOfRangeException("Attempt to refund negative value or value too small to be accepted by the network");
                    }
                }
                Transaction transaction = this.wallet.getTransaction(this.contract.getHash());
                Preconditions.checkNotNull(transaction, "Wallet did not contain multisig contract {} after state was marked READY", this.contract.getHash());
                if (transaction.getConfidence().getConfidenceType() != ConfidenceType.DEAD) {
                    if (equals) {
                        sigHash = SigHash.NONE;
                    } else {
                        sigHash = SigHash.SINGLE;
                    }
                    if (decodeFromBitcoin.sigHashMode() != sigHash || !decodeFromBitcoin.anyoneCanPay()) {
                        throw new VerificationException("New payment signature was not signed with the right SIGHASH flags.");
                    }
                    if (makeUnsignedChannelContract.f829tx.getVersion() >= 2) {
                        sha256Hash = makeUnsignedChannelContract.f829tx.hashForSignatureWitness(0, getSignedScript(), makeUnsignedChannelContract.f829tx.getInput(0).getConnectedOutput().getValue(), sigHash, true);
                    } else {
                        sha256Hash = makeUnsignedChannelContract.f829tx.hashForSignature(0, getSignedScript(), sigHash, true);
                    }
                    if (getClientKey().verify(sha256Hash, (ECDSASignature) decodeFromBitcoin)) {
                        this.bestValueToMe = subtract;
                        this.bestValueSignature = bArr2;
                        updateChannelInWallet();
                        z = !equals;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Signature does not verify on tx\n");
                        sb.append(makeUnsignedChannelContract.f829tx);
                        throw new VerificationException(sb.toString());
                    }
                } else {
                    close();
                    throw new VerificationException("Multisig contract was double-spent");
                }
            } else {
                throw new ValueOutOfRangeException("Attempt to roll back payment on the channel.");
            }
        }
        return z;
    }

    public synchronized Coin getBestValueToMe() {
        return this.bestValueToMe;
    }

    public synchronized Transaction getContract() {
        Preconditions.checkState(this.contract != null);
        return this.contract;
    }

    public long getExpiryTime() {
        return this.minExpireTime;
    }

    /* access modifiers changed from: protected */
    public synchronized void updateChannelInWallet() {
        if (this.storedServerChannel != null) {
            this.storedServerChannel.updateValueToMe(this.bestValueToMe, this.bestValueSignature);
            ((StoredPaymentChannelServerStates) this.wallet.getExtensions().get(StoredPaymentChannelServerStates.EXTENSION_ID)).updatedChannel(this.storedServerChannel);
        }
    }

    public synchronized void storeChannelInWallet(@Nullable PaymentChannelServer paymentChannelServer) {
        this.stateMachine.checkState(State.READY);
        if (this.storedServerChannel == null) {
            log.info("Storing state with contract hash {}.", (Object) getContract().getHash());
            StoredPaymentChannelServerStates storedPaymentChannelServerStates = (StoredPaymentChannelServerStates) this.wallet.addOrGetExistingExtension(new StoredPaymentChannelServerStates(this.wallet, this.broadcaster));
            StoredServerChannel storedServerChannel2 = new StoredServerChannel(this, getMajorVersion(), getContract(), getClientOutput(), getExpiryTime(), this.serverKey, getClientKey(), this.bestValueToMe, this.bestValueSignature);
            this.storedServerChannel = storedServerChannel2;
            if (paymentChannelServer != null) {
                boolean z = false;
                if (this.storedServerChannel.setConnectedHandler(paymentChannelServer, false) == paymentChannelServer) {
                    z = true;
                }
                Preconditions.checkState(z);
            }
            storedPaymentChannelServerStates.putChannel(this.storedServerChannel);
        }
    }

    public Script getContractScript() {
        Transaction transaction = this.contract;
        if (transaction == null) {
            return null;
        }
        return transaction.getOutput(0).getScriptPubKey();
    }

    /* access modifiers changed from: protected */
    public Coin getTotalValue() {
        return this.contract.getOutput(0).getValue();
    }
}
