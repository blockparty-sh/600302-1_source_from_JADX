package org.bitcoinj.protocols.channels;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import java.math.BigInteger;
import javax.annotation.Nullable;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.protocols.channels.PaymentChannelClientState.State;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.wallet.AllowUnconfirmedCoinSelector;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.params.KeyParameter;

public class PaymentChannelV2ClientState extends PaymentChannelClientState {
    private static final Logger log = LoggerFactory.getLogger(PaymentChannelV1ClientState.class);
    private Transaction contract;
    private final long expiryTime;
    private Coin refundFees;
    @VisibleForTesting
    Transaction refundTx;
    private final Coin totalValue;

    public int getMajorVersion() {
        return 2;
    }

    PaymentChannelV2ClientState(StoredClientChannel storedClientChannel, C3530Wallet wallet) throws VerificationException {
        super(storedClientChannel, wallet);
        this.contract = (Transaction) Preconditions.checkNotNull(storedClientChannel.contract);
        this.expiryTime = storedClientChannel.expiryTime;
        this.totalValue = this.contract.getOutput(0).getValue();
        this.valueToMe = (Coin) Preconditions.checkNotNull(storedClientChannel.valueToMe);
        this.refundTx = (Transaction) Preconditions.checkNotNull(storedClientChannel.refund);
        this.refundFees = (Coin) Preconditions.checkNotNull(storedClientChannel.refundFees);
        this.stateMachine.transition(State.READY);
        initWalletListeners();
    }

    public PaymentChannelV2ClientState(C3530Wallet wallet, ECKey eCKey, ECKey eCKey2, Coin coin, long j) throws VerificationException {
        super(wallet, eCKey, eCKey2, coin, j);
        Preconditions.checkArgument(coin.signum() > 0);
        initWalletListeners();
        Coin coin2 = (Coin) Preconditions.checkNotNull(coin);
        this.totalValue = coin2;
        this.valueToMe = coin2;
        this.expiryTime = j;
        this.stateMachine.transition(State.NEW);
    }

    /* access modifiers changed from: protected */
    public Multimap<State, State> getStateTransitions() {
        ListMultimap build = MultimapBuilder.enumKeys(State.class).arrayListValues().build();
        build.put(State.UNINITIALISED, State.NEW);
        build.put(State.UNINITIALISED, State.READY);
        build.put(State.NEW, State.SAVE_STATE_IN_WALLET);
        build.put(State.SAVE_STATE_IN_WALLET, State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER);
        build.put(State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER, State.READY);
        build.put(State.READY, State.EXPIRED);
        build.put(State.READY, State.CLOSED);
        return build;
    }

    public synchronized void initiate(@Nullable KeyParameter keyParameter) throws ValueOutOfRangeException, InsufficientMoneyException {
        TransactionSignature transactionSignature;
        KeyParameter keyParameter2 = keyParameter;
        synchronized (this) {
            NetworkParameters params = this.wallet.getParams();
            Transaction transaction = new Transaction(params);
            Script createCLTVPaymentChannelOutput = ScriptBuilder.createCLTVPaymentChannelOutput(BigInteger.valueOf(this.expiryTime), this.myKey, this.serverKey);
            if (!transaction.addOutput(this.totalValue, ScriptBuilder.createP2SHOutputScript(createCLTVPaymentChannelOutput)).isDust()) {
                SendRequest forTx = SendRequest.forTx(transaction);
                forTx.coinSelector = AllowUnconfirmedCoinSelector.get();
                editContractSendRequest(forTx);
                forTx.shuffleOutputs = false;
                forTx.aesKey = keyParameter2;
                this.wallet.completeTx(forTx);
                Coin fee = forTx.f829tx.getFee();
                this.contract = forTx.f829tx;
                this.refundTx = new Transaction(params);
                this.refundTx.addInput(this.contract.getOutput(0)).setSequenceNumber(4294967294L);
                this.refundTx.setLockTime(this.expiryTime);
                if (Context.get().isEnsureMinRequiredFee()) {
                    Coin subtract = this.totalValue.subtract(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE);
                    if (Transaction.MIN_NONDUST_OUTPUT.compareTo(subtract) <= 0) {
                        this.refundTx.addOutput(subtract, this.myKey.toAddress(params));
                        this.refundFees = fee.add(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE);
                    } else {
                        throw new ValueOutOfRangeException("totalValue too small to use");
                    }
                } else {
                    this.refundTx.addOutput(this.totalValue, this.myKey.toAddress(params));
                    this.refundFees = fee;
                }
                if (this.refundTx.getVersion() >= 2) {
                    transactionSignature = this.refundTx.calculateWitnessSignature(0, this.myKey.maybeDecrypt(keyParameter2), getSignedScript(), this.refundTx.getInput(0).getConnectedOutput().getValue(), SigHash.ALL, false);
                } else {
                    transactionSignature = this.refundTx.calculateSignature(0, this.myKey.maybeDecrypt(keyParameter2), getSignedScript(), SigHash.ALL, false);
                }
                this.refundTx.getInput(0).setScriptSig(ScriptBuilder.createCLTVPaymentChannelP2SHRefund(transactionSignature, createCLTVPaymentChannelOutput));
                this.refundTx.getConfidence().setSource(Source.SELF);
                log.info("initiated channel with contract {}", (Object) this.contract.getHashAsString());
                this.stateMachine.transition(State.SAVE_STATE_IN_WALLET);
            } else {
                throw new ValueOutOfRangeException("totalValue too small to use");
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized Coin getValueToMe() {
        return this.valueToMe;
    }

    /* access modifiers changed from: protected */
    public long getExpiryTime() {
        return this.expiryTime;
    }

    public synchronized Transaction getContract() {
        Preconditions.checkState(this.contract != null);
        if (this.stateMachine.getState() == State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER) {
            this.stateMachine.transition(State.READY);
        }
        return this.contract;
    }

    /* access modifiers changed from: protected */
    public synchronized Transaction getContractInternal() {
        return this.contract;
    }

    /* access modifiers changed from: protected */
    public synchronized Script getContractScript() {
        return this.contract.getOutput(0).getScriptPubKey();
    }

    /* access modifiers changed from: protected */
    public Script getSignedScript() {
        return ScriptBuilder.createCLTVPaymentChannelOutput(BigInteger.valueOf(this.expiryTime), this.myKey, this.serverKey);
    }

    public synchronized Coin getRefundTxFees() {
        Preconditions.checkState(getState().compareTo(State.NEW) > 0);
        return this.refundFees;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public Transaction getRefundTransaction() {
        return this.refundTx;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void doStoreChannelInWallet(Sha256Hash sha256Hash) {
        StoredPaymentChannelClientStates storedPaymentChannelClientStates = (StoredPaymentChannelClientStates) this.wallet.getExtensions().get(StoredPaymentChannelClientStates.EXTENSION_ID);
        Preconditions.checkNotNull(storedPaymentChannelClientStates, "You have not added the StoredPaymentChannelClientStates extension to the wallet.");
        Preconditions.checkState(storedPaymentChannelClientStates.getChannel(sha256Hash, this.contract.getHash()) == null);
        StoredClientChannel storedClientChannel = new StoredClientChannel(getMajorVersion(), sha256Hash, this.contract, this.refundTx, this.myKey, this.serverKey, this.valueToMe, this.refundFees, this.expiryTime, true);
        this.storedChannel = storedClientChannel;
        storedPaymentChannelClientStates.putChannel(this.storedChannel);
    }

    public Coin getTotalValue() {
        return this.totalValue;
    }
}
