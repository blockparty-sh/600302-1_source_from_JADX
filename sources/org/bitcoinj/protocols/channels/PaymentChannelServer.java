package org.bitcoinj.protocols.channels;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import net.jcip.annotations.GuardedBy;
import org.bitcoin.paymentchannel.Protos.ClientVersion;
import org.bitcoin.paymentchannel.Protos.Error;
import org.bitcoin.paymentchannel.Protos.Error.ErrorCode;
import org.bitcoin.paymentchannel.Protos.Initiate;
import org.bitcoin.paymentchannel.Protos.ProvideContract;
import org.bitcoin.paymentchannel.Protos.ProvideRefund;
import org.bitcoin.paymentchannel.Protos.ReturnRefund;
import org.bitcoin.paymentchannel.Protos.ServerVersion;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.Builder;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.C3530Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentChannelServer {
    public static final long DEFAULT_MAX_TIME_WINDOW = 604800;
    public static final long DEFAULT_MIN_TIME_WINDOW = 14400;
    public static final long HARD_MIN_TIME_WINDOW = 7200;
    public static final Map<Integer, Integer> SERVER_VERSIONS;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(PaymentChannelServer.class);
    private final TransactionBroadcaster broadcaster;
    @GuardedBy("lock")
    private boolean channelSettling;
    /* access modifiers changed from: private */
    public final ServerConnection conn;
    @GuardedBy("lock")
    private boolean connectionOpen;
    @GuardedBy("lock")
    private long expireTime;
    protected final ReentrantLock lock;
    @GuardedBy("lock")
    private int majorVersion;
    protected final long maxTimeWindow;
    private final Coin minAcceptedChannelSize;
    protected final long minTimeWindow;
    @GuardedBy("lock")
    private ECKey myKey;
    @GuardedBy("lock")
    private PaymentChannelServerState state;
    @GuardedBy("lock")
    private InitStep step;
    private final C3530Wallet wallet;

    private enum InitStep {
        WAITING_ON_CLIENT_VERSION,
        WAITING_ON_UNSIGNED_REFUND,
        WAITING_ON_CONTRACT,
        WAITING_ON_MULTISIG_ACCEPTANCE,
        CHANNEL_OPEN
    }

    public interface ServerConnection {
        void channelOpen(Sha256Hash sha256Hash);

        void destroyConnection(CloseReason closeReason);

        @Nullable
        ListenableFuture<ByteString> paymentIncrease(Coin coin, Coin coin2, @Nullable ByteString byteString);

        void sendToClient(TwoWayChannelMessage twoWayChannelMessage);
    }

    static {
        Integer valueOf = Integer.valueOf(1);
        Integer valueOf2 = Integer.valueOf(0);
        SERVER_VERSIONS = ImmutableMap.m147of(valueOf, valueOf2, Integer.valueOf(2), valueOf2);
    }

    public PaymentChannelServer(TransactionBroadcaster transactionBroadcaster, C3530Wallet wallet2, Coin coin, ServerConnection serverConnection) {
        this(transactionBroadcaster, wallet2, coin, DEFAULT_MIN_TIME_WINDOW, DEFAULT_MAX_TIME_WINDOW, serverConnection);
    }

    public PaymentChannelServer(TransactionBroadcaster transactionBroadcaster, C3530Wallet wallet2, Coin coin, long j, long j2, ServerConnection serverConnection) {
        this.lock = Threading.lock("channelserver");
        this.step = InitStep.WAITING_ON_CLIENT_VERSION;
        this.connectionOpen = false;
        this.channelSettling = false;
        if (j > j2) {
            throw new IllegalArgumentException("minTimeWindow must be less or equal to maxTimeWindow");
        } else if (j >= HARD_MIN_TIME_WINDOW) {
            this.broadcaster = (TransactionBroadcaster) Preconditions.checkNotNull(transactionBroadcaster);
            this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
            this.minAcceptedChannelSize = (Coin) Preconditions.checkNotNull(coin);
            this.conn = (ServerConnection) Preconditions.checkNotNull(serverConnection);
            this.minTimeWindow = j;
            this.maxTimeWindow = j2;
        } else {
            throw new IllegalArgumentException("minTimeWindow must be larger than7200 seconds");
        }
    }

    @Nullable
    public PaymentChannelServerState state() {
        return this.state;
    }

    @GuardedBy("lock")
    private void receiveVersionMessage(TwoWayChannelMessage twoWayChannelMessage) throws VerificationException {
        Preconditions.checkState(this.step == InitStep.WAITING_ON_CLIENT_VERSION && twoWayChannelMessage.hasClientVersion());
        ClientVersion clientVersion = twoWayChannelMessage.getClientVersion();
        this.majorVersion = clientVersion.getMajor();
        if (!SERVER_VERSIONS.containsKey(Integer.valueOf(this.majorVersion))) {
            StringBuilder sb = new StringBuilder();
            sb.append("This server needs one of protocol versions ");
            sb.append(SERVER_VERSIONS.keySet());
            sb.append(" , client offered ");
            sb.append(this.majorVersion);
            error(sb.toString(), ErrorCode.NO_ACCEPTABLE_VERSION, CloseReason.NO_ACCEPTABLE_VERSION);
            return;
        }
        this.conn.sendToClient(TwoWayChannelMessage.newBuilder().setType(MessageType.SERVER_VERSION).setServerVersion(ServerVersion.newBuilder().setMajor(this.majorVersion).setMinor(((Integer) SERVER_VERSIONS.get(Integer.valueOf(this.majorVersion))).intValue())).build());
        ByteString previousChannelContractHash = clientVersion.getPreviousChannelContractHash();
        if (previousChannelContractHash != null && previousChannelContractHash.size() == 32) {
            Sha256Hash wrap = Sha256Hash.wrap(previousChannelContractHash.toByteArray());
            log.info("New client that wants to resume {}", (Object) wrap);
            StoredPaymentChannelServerStates storedPaymentChannelServerStates = (StoredPaymentChannelServerStates) this.wallet.getExtensions().get(StoredPaymentChannelServerStates.EXTENSION_ID);
            if (storedPaymentChannelServerStates != null) {
                StoredServerChannel channel = storedPaymentChannelServerStates.getChannel(wrap);
                if (channel != null) {
                    PaymentChannelServer connectedHandler = channel.setConnectedHandler(this, false);
                    if (connectedHandler != this) {
                        log.warn("  ... and that channel is already in use, disconnecting other user.");
                        connectedHandler.close();
                        channel.setConnectedHandler(this, true);
                    }
                    log.info("Got resume version message, responding with VERSIONS and CHANNEL_OPEN");
                    this.state = channel.getOrCreateState(this.wallet, this.broadcaster);
                    this.step = InitStep.CHANNEL_OPEN;
                    this.conn.sendToClient(TwoWayChannelMessage.newBuilder().setType(MessageType.CHANNEL_OPEN).build());
                    this.conn.channelOpen(wrap);
                    return;
                }
                log.error(" ... but we do not have any record of that contract! Resume failed.");
            } else {
                log.error(" ... but we do not have any stored channels! Resume failed.");
            }
        }
        log.info("Got initial version message, responding with VERSIONS and INITIATE: min value={}", (Object) Long.valueOf(this.minAcceptedChannelSize.value));
        this.myKey = new ECKey();
        this.wallet.freshReceiveKey();
        this.expireTime = C3447Utils.currentTimeSeconds() + truncateTimeWindow(clientVersion.getTimeWindowSecs());
        int i = this.majorVersion;
        if (i == 1) {
            this.step = InitStep.WAITING_ON_UNSIGNED_REFUND;
        } else if (i != 2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Protocol version ");
            sb2.append(this.majorVersion);
            sb2.append(" not supported");
            error(sb2.toString(), ErrorCode.NO_ACCEPTABLE_VERSION, CloseReason.NO_ACCEPTABLE_VERSION);
        } else {
            this.step = InitStep.WAITING_ON_CONTRACT;
        }
        this.conn.sendToClient(TwoWayChannelMessage.newBuilder().setInitiate(Initiate.newBuilder().setMultisigKey(ByteString.copyFrom(this.myKey.getPubKey())).setExpireTimeSecs(this.expireTime).setMinAcceptedChannelSize(this.minAcceptedChannelSize.value).setMinPayment(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE.value)).setType(MessageType.INITIATE).build());
    }

    private long truncateTimeWindow(long j) {
        if (j < this.minTimeWindow) {
            log.info("client requested time window {} s to short, offering {} s", (Object) Long.valueOf(j), (Object) Long.valueOf(this.minTimeWindow));
            return this.minTimeWindow;
        }
        if (j > this.maxTimeWindow) {
            log.info("client requested time window {} s to long, offering {} s", (Object) Long.valueOf(j), (Object) Long.valueOf(this.minTimeWindow));
            j = this.maxTimeWindow;
        }
        return j;
    }

    @GuardedBy("lock")
    private void receiveRefundMessage(TwoWayChannelMessage twoWayChannelMessage) throws VerificationException {
        boolean z = false;
        Preconditions.checkState(this.majorVersion == 1);
        if (this.step == InitStep.WAITING_ON_UNSIGNED_REFUND && twoWayChannelMessage.hasProvideRefund()) {
            z = true;
        }
        Preconditions.checkState(z);
        log.info("Got refund transaction, returning signature");
        ProvideRefund provideRefund = twoWayChannelMessage.getProvideRefund();
        PaymentChannelV1ServerState paymentChannelV1ServerState = new PaymentChannelV1ServerState(this.broadcaster, this.wallet, this.myKey, this.expireTime);
        this.state = paymentChannelV1ServerState;
        byte[] provideRefundTransaction = ((PaymentChannelV1ServerState) this.state).provideRefundTransaction(this.wallet.getParams().getDefaultSerializer().makeTransaction(provideRefund.getTx().toByteArray()), provideRefund.getMultisigKey().toByteArray());
        this.step = InitStep.WAITING_ON_CONTRACT;
        this.conn.sendToClient(TwoWayChannelMessage.newBuilder().setReturnRefund(ReturnRefund.newBuilder().setSignature(ByteString.copyFrom(provideRefundTransaction))).setType(MessageType.RETURN_REFUND).build());
    }

    /* access modifiers changed from: private */
    public void multisigContractPropogated(ProvideContract provideContract, Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            if (this.connectionOpen) {
                if (!this.channelSettling) {
                    this.state.storeChannelInWallet(this);
                    receiveUpdatePaymentMessage(provideContract.getInitialPayment(), false);
                    this.conn.sendToClient(TwoWayChannelMessage.newBuilder().setType(MessageType.CHANNEL_OPEN).build());
                    this.step = InitStep.CHANNEL_OPEN;
                    this.conn.channelOpen(sha256Hash);
                    this.lock.unlock();
                    return;
                }
            }
        } catch (VerificationException e) {
            log.error("Initial payment failed to verify", (Throwable) e);
            error(e.getMessage(), ErrorCode.BAD_TRANSACTION, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
        } catch (ValueOutOfRangeException e2) {
            log.error("Initial payment value was out of range", (Throwable) e2);
            error(e2.getMessage(), ErrorCode.BAD_TRANSACTION, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
        } catch (InsufficientMoneyException e3) {
            log.error("Tried to settle channel and could not afford the fees whilst updating payment", (Throwable) e3);
            error(e3.getMessage(), ErrorCode.BAD_TRANSACTION, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
        this.lock.unlock();
    }

    @GuardedBy("lock")
    private void receiveContractMessage(TwoWayChannelMessage twoWayChannelMessage) throws VerificationException {
        int i = this.majorVersion;
        boolean z = false;
        Preconditions.checkState(i == 1 || i == 2);
        if (this.step == InitStep.WAITING_ON_CONTRACT && twoWayChannelMessage.hasProvideContract()) {
            z = true;
        }
        Preconditions.checkState(z);
        log.info("Got contract, broadcasting and responding with CHANNEL_OPEN");
        final ProvideContract provideContract = twoWayChannelMessage.getProvideContract();
        if (this.majorVersion == 2) {
            PaymentChannelV2ServerState paymentChannelV2ServerState = new PaymentChannelV2ServerState(this.broadcaster, this.wallet, this.myKey, this.expireTime);
            this.state = paymentChannelV2ServerState;
            Preconditions.checkState(provideContract.hasClientKey(), "ProvideContract didn't have a client key in protocol v2");
            ((PaymentChannelV2ServerState) this.state).provideClientKey(provideContract.getClientKey().toByteArray());
        }
        final Transaction makeTransaction = this.wallet.getParams().getDefaultSerializer().makeTransaction(provideContract.getTx().toByteArray());
        this.step = InitStep.WAITING_ON_MULTISIG_ACCEPTANCE;
        this.state.provideContract(makeTransaction).addListener(new Runnable() {
            public void run() {
                PaymentChannelServer.this.multisigContractPropogated(provideContract, makeTransaction.getHash());
            }
        }, Threading.SAME_THREAD);
    }

    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3, types: [com.google.common.util.concurrent.ListenableFuture] */
    /* JADX WARNING: type inference failed for: r3v4, types: [com.google.protobuf.ByteString] */
    /* JADX WARNING: type inference failed for: r3v5, types: [com.google.common.util.concurrent.ListenableFuture] */
    /* JADX WARNING: type inference failed for: r3v6, types: [com.google.protobuf.ByteString] */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v2
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.protobuf.ByteString, com.google.common.util.concurrent.ListenableFuture]
      uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.google.common.util.concurrent.ListenableFuture, com.google.protobuf.ByteString]
      mth insns count: 44
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @net.jcip.annotations.GuardedBy("lock")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void receiveUpdatePaymentMessage(org.bitcoin.paymentchannel.Protos.UpdatePayment r5, boolean r6) throws org.bitcoinj.core.VerificationException, org.bitcoinj.protocols.channels.ValueOutOfRangeException, org.bitcoinj.core.InsufficientMoneyException {
        /*
            r4 = this;
            org.slf4j.Logger r0 = log
            java.lang.String r1 = "Got a payment update"
            r0.info(r1)
            org.bitcoinj.protocols.channels.PaymentChannelServerState r0 = r4.state
            org.bitcoinj.core.Coin r0 = r0.getBestValueToMe()
            long r1 = r5.getClientChangeValue()
            org.bitcoinj.core.Coin r1 = org.bitcoinj.core.Coin.valueOf(r1)
            org.bitcoinj.protocols.channels.PaymentChannelServerState r2 = r4.state
            com.google.protobuf.ByteString r3 = r5.getSignature()
            byte[] r3 = r3.toByteArray()
            boolean r1 = r2.incrementPayment(r1, r3)
            org.bitcoinj.protocols.channels.PaymentChannelServerState r2 = r4.state
            org.bitcoinj.core.Coin r2 = r2.getBestValueToMe()
            org.bitcoinj.core.Coin r0 = r2.subtract(r0)
            int r2 = r0.signum()
            r3 = 0
            if (r2 <= 0) goto L_0x004a
            boolean r2 = r5.hasInfo()
            if (r2 == 0) goto L_0x003e
            com.google.protobuf.ByteString r3 = r5.getInfo()
        L_0x003e:
            org.bitcoinj.protocols.channels.PaymentChannelServer$ServerConnection r5 = r4.conn
            org.bitcoinj.protocols.channels.PaymentChannelServerState r2 = r4.state
            org.bitcoinj.core.Coin r2 = r2.getBestValueToMe()
            com.google.common.util.concurrent.ListenableFuture r3 = r5.paymentIncrease(r0, r2, r3)
        L_0x004a:
            if (r6 == 0) goto L_0x0069
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$Builder r5 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.newBuilder()
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r6 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.PAYMENT_ACK
            r5.setType(r6)
            if (r3 != 0) goto L_0x0061
            org.bitcoinj.protocols.channels.PaymentChannelServer$ServerConnection r6 = r4.conn
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage r5 = r5.build()
            r6.sendToClient(r5)
            goto L_0x0069
        L_0x0061:
            org.bitcoinj.protocols.channels.PaymentChannelServer$2 r6 = new org.bitcoinj.protocols.channels.PaymentChannelServer$2
            r6.<init>(r5)
            com.google.common.util.concurrent.Futures.addCallback(r3, r6)
        L_0x0069:
            if (r1 != 0) goto L_0x0077
            org.slf4j.Logger r5 = log
            java.lang.String r6 = "Channel is now fully exhausted, closing/initiating settlement"
            r5.info(r6)
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r5 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.CHANNEL_EXHAUSTED
            r4.settlePayment(r5)
        L_0x0077:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.PaymentChannelServer.receiveUpdatePaymentMessage(org.bitcoin.paymentchannel.Protos$UpdatePayment, boolean):void");
    }

    public void receiveMessage(TwoWayChannelMessage twoWayChannelMessage) {
        this.lock.lock();
        try {
            Preconditions.checkState(this.connectionOpen);
            if (!this.channelSettling) {
                switch (twoWayChannelMessage.getType()) {
                    case CLIENT_VERSION:
                        receiveVersionMessage(twoWayChannelMessage);
                        break;
                    case PROVIDE_REFUND:
                        receiveRefundMessage(twoWayChannelMessage);
                        break;
                    case PROVIDE_CONTRACT:
                        receiveContractMessage(twoWayChannelMessage);
                        break;
                    case UPDATE_PAYMENT:
                        Preconditions.checkState(this.step == InitStep.CHANNEL_OPEN && twoWayChannelMessage.hasUpdatePayment());
                        receiveUpdatePaymentMessage(twoWayChannelMessage.getUpdatePayment(), true);
                        break;
                    case CLOSE:
                        receiveCloseMessage();
                        break;
                    case ERROR:
                        Preconditions.checkState(twoWayChannelMessage.hasError());
                        log.error("Client sent ERROR {} with explanation {}", (Object) twoWayChannelMessage.getError().getCode().name(), (Object) twoWayChannelMessage.getError().hasExplanation() ? twoWayChannelMessage.getError().getExplanation() : "");
                        this.conn.destroyConnection(CloseReason.REMOTE_SENT_ERROR);
                        break;
                    default:
                        error("Got unknown message type or type that doesn't apply to servers.", ErrorCode.SYNTAX_ERROR, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
                        break;
                }
            }
            this.lock.unlock();
            return;
        } catch (VerificationException e) {
            log.error("Caught verification exception handling message from client", (Throwable) e);
            error(e.getMessage(), ErrorCode.BAD_TRANSACTION, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
        } catch (ValueOutOfRangeException e2) {
            log.error("Caught value out of range exception handling message from client", (Throwable) e2);
            error(e2.getMessage(), ErrorCode.BAD_TRANSACTION, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
        } catch (InsufficientMoneyException e3) {
            log.error("Caught insufficient money exception handling message from client", (Throwable) e3);
            error(e3.getMessage(), ErrorCode.BAD_TRANSACTION, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
        } catch (IllegalStateException e4) {
            log.error("Caught illegal state exception handling message from client", (Throwable) e4);
            error(e4.getMessage(), ErrorCode.SYNTAX_ERROR, CloseReason.REMOTE_SENT_INVALID_MESSAGE);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
        this.lock.unlock();
    }

    /* access modifiers changed from: private */
    public void error(String str, ErrorCode errorCode, CloseReason closeReason) {
        log.error(str);
        this.conn.sendToClient(TwoWayChannelMessage.newBuilder().setError(Error.newBuilder().setCode(errorCode).setExplanation(str)).setType(MessageType.ERROR).build());
        this.conn.destroyConnection(closeReason);
    }

    @GuardedBy("lock")
    private void receiveCloseMessage() throws InsufficientMoneyException {
        log.info("Got CLOSE message, closing channel");
        if (this.state != null) {
            settlePayment(CloseReason.CLIENT_REQUESTED_CLOSE);
        } else {
            this.conn.destroyConnection(CloseReason.CLIENT_REQUESTED_CLOSE);
        }
    }

    @GuardedBy("lock")
    private void settlePayment(final CloseReason closeReason) throws InsufficientMoneyException {
        this.channelSettling = true;
        Futures.addCallback(this.state.close(), new FutureCallback<Transaction>() {
            public void onSuccess(Transaction transaction) {
                Builder newBuilder = TwoWayChannelMessage.newBuilder();
                newBuilder.setType(MessageType.CLOSE);
                if (transaction != null) {
                    newBuilder.getSettlementBuilder().setTx(ByteString.copyFrom(transaction.unsafeBitcoinSerialize()));
                    PaymentChannelServer.log.info("Sending CLOSE back with broadcast settlement tx.");
                } else {
                    PaymentChannelServer.log.info("Sending CLOSE back without broadcast settlement tx.");
                }
                PaymentChannelServer.this.conn.sendToClient(newBuilder.build());
                PaymentChannelServer.this.conn.destroyConnection(closeReason);
            }

            public void onFailure(Throwable th) {
                PaymentChannelServer.log.error("Failed to broadcast settlement tx", th);
                PaymentChannelServer.this.conn.destroyConnection(closeReason);
            }
        });
    }

    public void connectionClosed() {
        this.lock.lock();
        try {
            log.info("Server channel closed.");
            this.connectionOpen = false;
            try {
                if (!(this.state == null || this.state.getContract() == null)) {
                    StoredPaymentChannelServerStates storedPaymentChannelServerStates = (StoredPaymentChannelServerStates) this.wallet.getExtensions().get(StoredPaymentChannelServerStates.EXTENSION_ID);
                    if (storedPaymentChannelServerStates != null) {
                        StoredServerChannel channel = storedPaymentChannelServerStates.getChannel(this.state.getContract().getHash());
                        if (channel != null) {
                            channel.clearConnectedHandler();
                        }
                    }
                }
            } catch (IllegalStateException unused) {
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void connectionOpen() {
        this.lock.lock();
        try {
            log.info("New server channel active.");
            this.connectionOpen = true;
        } finally {
            this.lock.unlock();
        }
    }

    public void close() {
        this.lock.lock();
        try {
            if (this.connectionOpen && !this.channelSettling) {
                Builder newBuilder = TwoWayChannelMessage.newBuilder();
                newBuilder.setType(MessageType.CLOSE);
                this.conn.sendToClient(newBuilder.build());
                this.conn.destroyConnection(CloseReason.SERVER_REQUESTED_CLOSE);
            }
        } finally {
            this.lock.unlock();
        }
    }
}
