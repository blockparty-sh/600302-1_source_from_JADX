package org.bitcoinj.protocols.channels;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import org.bitcoinj.core.C3447Utils;
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
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.C3530Wallet.MissingSigsMode;
import org.bitcoinj.wallet.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentChannelV2ServerState extends PaymentChannelServerState {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(PaymentChannelV1ServerState.class);
    protected ECKey clientKey;
    final SettableFuture<Transaction> closedFuture = SettableFuture.create();
    private Coin feePaidForPayment;

    public TransactionOutput getClientOutput() {
        return null;
    }

    public int getMajorVersion() {
        return 2;
    }

    PaymentChannelV2ServerState(StoredServerChannel storedServerChannel, C3530Wallet wallet, TransactionBroadcaster transactionBroadcaster) throws VerificationException {
        super(storedServerChannel, wallet, transactionBroadcaster);
        synchronized (storedServerChannel) {
            this.clientKey = storedServerChannel.clientKey;
            this.stateMachine.transition(State.READY);
        }
    }

    public PaymentChannelV2ServerState(TransactionBroadcaster transactionBroadcaster, C3530Wallet wallet, ECKey eCKey, long j) {
        super(transactionBroadcaster, wallet, eCKey, j);
        this.stateMachine.transition(State.WAITING_FOR_MULTISIG_CONTRACT);
    }

    public Multimap<State, State> getStateTransitions() {
        ListMultimap build = MultimapBuilder.enumKeys(State.class).arrayListValues().build();
        build.put(State.UNINITIALISED, State.READY);
        build.put(State.UNINITIALISED, State.WAITING_FOR_MULTISIG_CONTRACT);
        build.put(State.WAITING_FOR_MULTISIG_CONTRACT, State.WAITING_FOR_MULTISIG_ACCEPTANCE);
        build.put(State.WAITING_FOR_MULTISIG_ACCEPTANCE, State.READY);
        build.put(State.READY, State.CLOSING);
        build.put(State.CLOSING, State.CLOSED);
        for (State put : State.values()) {
            build.put(put, State.ERROR);
        }
        return build;
    }

    public void provideClientKey(byte[] bArr) {
        this.clientKey = ECKey.fromPublicOnly(bArr);
    }

    public synchronized Coin getFeePaid() {
        this.stateMachine.checkState((State[]) new State[]{State.CLOSED, State.CLOSING});
        return this.feePaidForPayment;
    }

    /* access modifiers changed from: protected */
    public Script getSignedScript() {
        return createP2SHRedeemScript();
    }

    /* access modifiers changed from: protected */
    public void verifyContract(Transaction transaction) {
        super.verifyContract(transaction);
        if (!Arrays.equals(C3447Utils.sha256hash160(createP2SHRedeemScript().getProgram()), getContractScript().getPubKeyHash())) {
            throw new VerificationException("P2SH hash didn't match required contract - contract should be a CLTV micropayment channel to client and server in that order.");
        }
    }

    /* access modifiers changed from: protected */
    public Script createOutputScript() {
        return ScriptBuilder.createP2SHOutputScript(createP2SHRedeemScript());
    }

    private Script createP2SHRedeemScript() {
        return ScriptBuilder.createCLTVPaymentChannelOutput(BigInteger.valueOf(getExpiryTime()), this.clientKey, this.serverKey);
    }

    /* access modifiers changed from: protected */
    public ECKey getClientKey() {
        return this.clientKey;
    }

    private void signP2SHInput(Transaction transaction, SigHash sigHash, boolean z) {
        TransactionSignature transactionSignature;
        if (transaction.getVersion() >= 2) {
            transactionSignature = transaction.calculateWitnessSignature(0, this.serverKey, createP2SHRedeemScript(), transaction.getInput(0).getConnectedOutput().getValue(), sigHash, z);
        } else {
            transactionSignature = transaction.calculateSignature(0, this.serverKey, createP2SHRedeemScript(), sigHash, z);
        }
        transaction.getInput(0).setScriptSig(ScriptBuilder.createCLTVPaymentChannelP2SHInput(this.bestValueSignature, transactionSignature.encodeToBitcoin(), createP2SHRedeemScript()));
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
                signP2SHInput(transaction, SigHash.NONE, true);
                makeUnsignedChannelContract.shuffleOutputs = false;
                makeUnsignedChannelContract.missingSigsMode = MissingSigsMode.USE_DUMMY_SIG;
                this.wallet.completeTx(makeUnsignedChannelContract);
                this.feePaidForPayment = makeUnsignedChannelContract.f829tx.getFee();
                log.info("Calculated fee is {}", (Object) this.feePaidForPayment);
                if (this.feePaidForPayment.compareTo(this.bestValueToMe) <= 0) {
                    signP2SHInput(transaction, SigHash.ALL, false);
                    transaction.verify();
                    for (TransactionInput verify : transaction.getInputs()) {
                        verify.verify();
                    }
                    this.stateMachine.transition(State.CLOSING);
                    log.info("Closing channel, broadcasting tx {}", (Object) transaction);
                    Futures.addCallback(this.broadcaster.broadcastTransaction(transaction).future(), new FutureCallback<Transaction>() {
                        public void onSuccess(Transaction transaction) {
                            PaymentChannelV2ServerState.log.info("TX {} propagated, channel successfully closed.", (Object) transaction.getHash());
                            PaymentChannelV2ServerState.this.stateMachine.transition(State.CLOSED);
                            PaymentChannelV2ServerState.this.closedFuture.set(transaction);
                        }

                        public void onFailure(Throwable th) {
                            PaymentChannelV2ServerState.log.error("Failed to settle channel, could not broadcast: {}", th);
                            PaymentChannelV2ServerState.this.stateMachine.transition(State.ERROR);
                            PaymentChannelV2ServerState.this.closedFuture.setException(th);
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
}
