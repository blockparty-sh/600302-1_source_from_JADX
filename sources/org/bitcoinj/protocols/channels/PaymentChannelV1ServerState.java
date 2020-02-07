package org.bitcoinj.protocols.channels;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Locale;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.protocols.channels.PaymentChannelServerState.State;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptChunk;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.C3530Wallet.MissingSigsMode;
import org.bitcoinj.wallet.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentChannelV1ServerState extends PaymentChannelServerState {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(PaymentChannelV1ServerState.class);
    protected ECKey clientKey;
    private TransactionOutput clientOutput;
    final SettableFuture<Transaction> closedFuture = SettableFuture.create();
    private Coin feePaidForPayment;
    private long refundTransactionUnlockTimeSecs;

    public int getMajorVersion() {
        return 1;
    }

    PaymentChannelV1ServerState(StoredServerChannel storedServerChannel, C3530Wallet wallet, TransactionBroadcaster transactionBroadcaster) throws VerificationException {
        super(storedServerChannel, wallet, transactionBroadcaster);
        synchronized (storedServerChannel) {
            this.clientKey = ECKey.fromPublicOnly(((ScriptChunk) getContractScript().getChunks().get(1)).data);
            this.clientOutput = (TransactionOutput) Preconditions.checkNotNull(storedServerChannel.clientOutput);
            this.refundTransactionUnlockTimeSecs = storedServerChannel.refundTransactionUnlockTimeSecs;
            this.stateMachine.transition(State.READY);
        }
    }

    public PaymentChannelV1ServerState(TransactionBroadcaster transactionBroadcaster, C3530Wallet wallet, ECKey eCKey, long j) {
        super(transactionBroadcaster, wallet, eCKey, j);
        this.stateMachine.transition(State.WAITING_FOR_REFUND_TRANSACTION);
    }

    public Multimap<State, State> getStateTransitions() {
        ListMultimap build = MultimapBuilder.enumKeys(State.class).arrayListValues().build();
        build.put(State.UNINITIALISED, State.READY);
        build.put(State.UNINITIALISED, State.WAITING_FOR_REFUND_TRANSACTION);
        build.put(State.WAITING_FOR_REFUND_TRANSACTION, State.WAITING_FOR_MULTISIG_CONTRACT);
        build.put(State.WAITING_FOR_MULTISIG_CONTRACT, State.WAITING_FOR_MULTISIG_ACCEPTANCE);
        build.put(State.WAITING_FOR_MULTISIG_ACCEPTANCE, State.READY);
        build.put(State.READY, State.CLOSING);
        build.put(State.CLOSING, State.CLOSED);
        for (State put : State.values()) {
            build.put(put, State.ERROR);
        }
        return build;
    }

    public TransactionOutput getClientOutput() {
        return this.clientOutput;
    }

    /* access modifiers changed from: protected */
    public Script getSignedScript() {
        return getContractScript();
    }

    public synchronized byte[] provideRefundTransaction(Transaction transaction, byte[] bArr) throws VerificationException {
        TransactionSignature transactionSignature;
        Preconditions.checkNotNull(transaction);
        Preconditions.checkNotNull(bArr);
        this.stateMachine.checkState(State.WAITING_FOR_REFUND_TRANSACTION);
        log.info("Provided with refund transaction: {}", (Object) transaction);
        transaction.verify();
        if (transaction.getInputs().size() != 1) {
            throw new VerificationException("Refund transaction does not have exactly one input");
        } else if (transaction.getInput(0).getSequenceNumber() == TransactionInput.NO_SEQUENCE) {
            throw new VerificationException("Refund transaction's input's sequence number disables lock time");
        } else if (transaction.getLockTime() < this.minExpireTime) {
            throw new VerificationException("Refund transaction has a lock time too soon");
        } else if (transaction.getOutputs().size() == 1) {
            this.refundTransactionUnlockTimeSecs = transaction.getLockTime();
            this.clientKey = ECKey.fromPublicOnly(bArr);
            Script createMultiSigOutputScript = ScriptBuilder.createMultiSigOutputScript(2, ImmutableList.m128of(this.clientKey, this.serverKey));
            if (transaction.getVersion() >= 2) {
                transactionSignature = transaction.calculateWitnessSignature(0, this.serverKey, createMultiSigOutputScript, transaction.getInput(0).getConnectedOutput().getValue(), SigHash.NONE, true);
            } else {
                transactionSignature = transaction.calculateSignature(0, this.serverKey, createMultiSigOutputScript, SigHash.NONE, true);
            }
            log.info("Signed refund transaction.");
            this.clientOutput = transaction.getOutput(0);
            this.stateMachine.transition(State.WAITING_FOR_MULTISIG_CONTRACT);
        } else {
            throw new VerificationException("Refund transaction does not have exactly one output");
        }
        return transactionSignature.encodeToBitcoin();
    }

    /* access modifiers changed from: protected */
    public Script createOutputScript() {
        return ScriptBuilder.createMultiSigOutputScript(2, ImmutableList.m128of(this.clientKey, this.serverKey));
    }

    /* access modifiers changed from: protected */
    public ECKey getClientKey() {
        return this.clientKey;
    }

    private void signMultisigInput(Transaction transaction, SigHash sigHash, boolean z) {
        TransactionSignature transactionSignature;
        if (transaction.getVersion() >= 2) {
            transactionSignature = transaction.calculateWitnessSignature(0, this.serverKey, getContractScript(), transaction.getInput(0).getConnectedOutput().getValue(), SigHash.NONE, true);
        } else {
            transactionSignature = transaction.calculateSignature(0, this.serverKey, getContractScript(), sigHash, true);
        }
        transaction.getInput(0).setScriptSig(ScriptBuilder.createMultiSigInputScriptBytes(ImmutableList.m128of(this.bestValueSignature, transactionSignature.encodeToBitcoin())));
    }

    public synchronized ListenableFuture<Transaction> close() throws InsufficientMoneyException {
        Object obj;
        Object obj2 = 0;
        if (this.storedServerChannel != null) {
            StoredServerChannel storedServerChannel = this.storedServerChannel;
            this.storedServerChannel = null;
            ((StoredPaymentChannelServerStates) this.wallet.getExtensions().get(StoredPaymentChannelServerStates.EXTENSION_ID)).closeChannel(storedServerChannel);
            if (getState().compareTo(State.CLOSING) >= 0) {
                return this.closedFuture;
            }
        }
        if (getState().ordinal() < State.READY.ordinal()) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("Attempt to settle channel in state ");
            sb.append(getState());
            logger.error(sb.toString());
            this.stateMachine.transition(State.CLOSED);
            this.closedFuture.set(null);
            return this.closedFuture;
        } else if (getState() != State.READY) {
            Logger logger2 = log;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed attempt to settle a channel in state ");
            sb2.append(getState());
            logger2.warn(sb2.toString());
            return this.closedFuture;
        } else {
            try {
                obj = obj2;
                SendRequest makeUnsignedChannelContract = makeUnsignedChannelContract(this.bestValueToMe);
                Transaction transaction = makeUnsignedChannelContract.f829tx;
                signMultisigInput(transaction, SigHash.NONE, true);
                makeUnsignedChannelContract.shuffleOutputs = false;
                makeUnsignedChannelContract.missingSigsMode = MissingSigsMode.USE_DUMMY_SIG;
                this.wallet.completeTx(makeUnsignedChannelContract);
                this.feePaidForPayment = makeUnsignedChannelContract.f829tx.getFee();
                log.info("Calculated fee is {}", (Object) this.feePaidForPayment);
                if (this.feePaidForPayment.compareTo(this.bestValueToMe) <= 0) {
                    signMultisigInput(transaction, SigHash.ALL, false);
                    transaction.verify();
                    for (TransactionInput verify : transaction.getInputs()) {
                        verify.verify();
                    }
                    this.stateMachine.transition(State.CLOSING);
                    log.info("Closing channel, broadcasting tx {}", (Object) transaction);
                    Futures.addCallback(this.broadcaster.broadcastTransaction(transaction).future(), new FutureCallback<Transaction>() {
                        public void onSuccess(Transaction transaction) {
                            PaymentChannelV1ServerState.log.info("TX {} propagated, channel successfully closed.", (Object) transaction.getHash());
                            PaymentChannelV1ServerState.this.stateMachine.transition(State.CLOSED);
                            PaymentChannelV1ServerState.this.closedFuture.set(transaction);
                        }

                        public void onFailure(Throwable th) {
                            PaymentChannelV1ServerState.log.error("Failed to settle channel, could not broadcast: {}", th);
                            PaymentChannelV1ServerState.this.stateMachine.transition(State.ERROR);
                            PaymentChannelV1ServerState.this.closedFuture.setException(th);
                        }
                    });
                    return this.closedFuture;
                }
                obj = transaction;
                throw new InsufficientMoneyException(this.feePaidForPayment.subtract(this.bestValueToMe), String.format(Locale.US, "Had to pay more in fees (%s) than the channel was worth (%s)", new Object[]{this.feePaidForPayment, this.bestValueToMe}));
            } catch (InsufficientMoneyException e) {
                throw e;
            } catch (Exception e2) {
                log.error("Could not verify self-built tx\nMULTISIG {}\nCLOSE {}", (Object) this.contract, obj != 0 ? obj : "");
                throw new RuntimeException(e2);
            }
        }
    }

    public synchronized Coin getFeePaid() {
        this.stateMachine.checkState((State[]) new State[]{State.CLOSED, State.CLOSING});
        return this.feePaidForPayment;
    }

    public synchronized long getRefundTransactionUnlockTime() {
        Preconditions.checkState(getState().compareTo(State.WAITING_FOR_MULTISIG_CONTRACT) > 0 && getState() != State.ERROR);
        return this.refundTransactionUnlockTimeSecs;
    }
}
