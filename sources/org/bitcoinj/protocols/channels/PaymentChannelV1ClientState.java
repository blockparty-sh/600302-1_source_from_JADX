package org.bitcoinj.protocols.channels;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import javax.annotation.Nullable;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutput;
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

public class PaymentChannelV1ClientState extends PaymentChannelClientState {
    private static final Logger log = LoggerFactory.getLogger(PaymentChannelV1ClientState.class);
    private final long expiryTime;
    private Transaction multisigContract;
    private Script multisigScript;
    private Coin refundFees;
    private Transaction refundTx;
    private final Coin totalValue;

    public int getMajorVersion() {
        return 1;
    }

    PaymentChannelV1ClientState(StoredClientChannel storedClientChannel, C3530Wallet wallet) throws VerificationException {
        super(storedClientChannel, wallet);
        this.multisigContract = (Transaction) Preconditions.checkNotNull(storedClientChannel.contract);
        this.multisigScript = this.multisigContract.getOutput(0).getScriptPubKey();
        this.refundTx = (Transaction) Preconditions.checkNotNull(storedClientChannel.refund);
        this.refundFees = (Coin) Preconditions.checkNotNull(storedClientChannel.refundFees);
        this.expiryTime = this.refundTx.getLockTime();
        this.totalValue = this.multisigContract.getOutput(0).getValue();
        this.stateMachine.transition(State.READY);
        initWalletListeners();
    }

    public PaymentChannelV1ClientState(C3530Wallet wallet, ECKey eCKey, ECKey eCKey2, Coin coin, long j) throws VerificationException {
        super(wallet, eCKey, eCKey2, coin, j);
        Preconditions.checkArgument(coin.signum() > 0);
        initWalletListeners();
        this.totalValue = (Coin) Preconditions.checkNotNull(coin);
        this.expiryTime = j;
        this.stateMachine.transition(State.NEW);
    }

    /* access modifiers changed from: protected */
    public Multimap<State, State> getStateTransitions() {
        ListMultimap build = MultimapBuilder.enumKeys(State.class).arrayListValues().build();
        build.put(State.UNINITIALISED, State.NEW);
        build.put(State.UNINITIALISED, State.READY);
        build.put(State.NEW, State.INITIATED);
        build.put(State.INITIATED, State.WAITING_FOR_SIGNED_REFUND);
        build.put(State.WAITING_FOR_SIGNED_REFUND, State.SAVE_STATE_IN_WALLET);
        build.put(State.SAVE_STATE_IN_WALLET, State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER);
        build.put(State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER, State.READY);
        build.put(State.READY, State.EXPIRED);
        build.put(State.READY, State.CLOSED);
        return build;
    }

    public synchronized void initiate(@Nullable KeyParameter keyParameter) throws ValueOutOfRangeException, InsufficientMoneyException {
        NetworkParameters params = this.wallet.getParams();
        Transaction transaction = new Transaction(params);
        TransactionOutput addOutput = transaction.addOutput(this.totalValue, ScriptBuilder.createMultiSigOutputScript(2, Lists.newArrayList((E[]) new ECKey[]{this.myKey, this.serverKey})));
        if (!addOutput.isDust()) {
            SendRequest forTx = SendRequest.forTx(transaction);
            forTx.coinSelector = AllowUnconfirmedCoinSelector.get();
            editContractSendRequest(forTx);
            forTx.shuffleOutputs = false;
            forTx.aesKey = keyParameter;
            this.wallet.completeTx(forTx);
            Coin fee = forTx.f829tx.getFee();
            this.multisigContract = forTx.f829tx;
            this.refundTx = new Transaction(params);
            this.refundTx.addInput(addOutput).setSequenceNumber(4294967294L);
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
            this.refundTx.getConfidence().setSource(Source.SELF);
            log.info("initiated channel with multi-sig contract {}, refund {}", (Object) this.multisigContract.getHashAsString(), (Object) this.refundTx.getHashAsString());
            this.stateMachine.transition(State.INITIATED);
        } else {
            throw new ValueOutOfRangeException("totalValue too small to use");
        }
    }

    public synchronized Transaction getContract() {
        Preconditions.checkState(this.multisigContract != null);
        if (this.stateMachine.getState() == State.PROVIDE_MULTISIG_CONTRACT_TO_SERVER) {
            this.stateMachine.transition(State.READY);
        }
        return this.multisigContract;
    }

    /* access modifiers changed from: protected */
    public synchronized Transaction getContractInternal() {
        return this.multisigContract;
    }

    /* access modifiers changed from: protected */
    public synchronized Script getContractScript() {
        return this.multisigScript;
    }

    /* access modifiers changed from: protected */
    public Script getSignedScript() {
        return getContractScript();
    }

    public synchronized Transaction getIncompleteRefundTransaction() {
        Preconditions.checkState(this.refundTx != null);
        if (this.stateMachine.getState() == State.INITIATED) {
            this.stateMachine.transition(State.WAITING_FOR_SIGNED_REFUND);
        }
        return this.refundTx;
    }

    public synchronized void provideRefundSignature(byte[] bArr, @Nullable KeyParameter keyParameter) throws VerificationException {
        TransactionSignature transactionSignature;
        KeyParameter keyParameter2 = keyParameter;
        synchronized (this) {
            Preconditions.checkNotNull(bArr);
            this.stateMachine.checkState(State.WAITING_FOR_SIGNED_REFUND);
            TransactionSignature decodeFromBitcoin = TransactionSignature.decodeFromBitcoin(bArr, true);
            if (decodeFromBitcoin.sigHashMode() != SigHash.NONE || !decodeFromBitcoin.anyoneCanPay()) {
                throw new VerificationException("Refund signature was not SIGHASH_NONE|SIGHASH_ANYONECANPAY");
            }
            TransactionOutput output = this.multisigContract.getOutput(0);
            try {
                this.multisigScript = output.getScriptPubKey();
                if (this.refundTx.getVersion() >= 2) {
                    transactionSignature = this.refundTx.calculateWitnessSignature(0, this.myKey.maybeDecrypt(keyParameter2), this.multisigScript, this.refundTx.getInput(0).getConnectedOutput().getValue(), SigHash.ALL, false);
                } else {
                    transactionSignature = this.refundTx.calculateSignature(0, this.myKey.maybeDecrypt(keyParameter2), this.multisigScript, SigHash.ALL, false);
                }
                Script createMultiSigInputScript = ScriptBuilder.createMultiSigInputScript(transactionSignature, decodeFromBitcoin);
                log.info("Refund scriptSig: {}", (Object) createMultiSigInputScript);
                log.info("Multi-sig contract scriptPubKey: {}", (Object) this.multisigScript);
                TransactionInput input = this.refundTx.getInput(0);
                input.setScriptSig(createMultiSigInputScript);
                input.verify(output);
                this.stateMachine.transition(State.SAVE_STATE_IN_WALLET);
            } catch (ScriptException e) {
                throw new RuntimeException(e);
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

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void doStoreChannelInWallet(Sha256Hash sha256Hash) {
        StoredPaymentChannelClientStates storedPaymentChannelClientStates = (StoredPaymentChannelClientStates) this.wallet.getExtensions().get(StoredPaymentChannelClientStates.EXTENSION_ID);
        Preconditions.checkNotNull(storedPaymentChannelClientStates, "You have not added the StoredPaymentChannelClientStates extension to the wallet.");
        Preconditions.checkState(storedPaymentChannelClientStates.getChannel(sha256Hash, this.multisigContract.getHash()) == null);
        StoredClientChannel storedClientChannel = new StoredClientChannel(getMajorVersion(), sha256Hash, this.multisigContract, this.refundTx, this.myKey, this.serverKey, this.valueToMe, this.refundFees, 0, true);
        this.storedChannel = storedClientChannel;
        storedPaymentChannelClientStates.putChannel(this.storedChannel);
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

    public synchronized Transaction getCompletedRefundTransaction() {
        Preconditions.checkState(getState().compareTo(State.WAITING_FOR_SIGNED_REFUND) > 0);
        return this.refundTx;
    }

    public Coin getTotalValue() {
        return this.totalValue;
    }
}
