package org.bitcoinj.protocols.channels;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import net.jcip.annotations.GuardedBy;
import org.bitcoin.paymentchannel.Protos.ClientVersion;
import org.bitcoin.paymentchannel.Protos.Error.Builder;
import org.bitcoin.paymentchannel.Protos.Error.ErrorCode;
import org.bitcoin.paymentchannel.Protos.Initiate;
import org.bitcoin.paymentchannel.Protos.PaymentAck;
import org.bitcoin.paymentchannel.Protos.ProvideContract;
import org.bitcoin.paymentchannel.Protos.ProvideRefund;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType;
import org.bitcoin.paymentchannel.Protos.UpdatePayment;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.KeyIsEncryptedException;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.protocols.channels.IPaymentChannelClient.ClientConnection;
import org.bitcoinj.protocols.channels.PaymentChannelClientState.IncrementedPayment;
import org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.C3530Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.params.KeyParameter;

public class PaymentChannelClient implements IPaymentChannelClient {
    public static final long DEFAULT_TIME_WINDOW = 86340;
    private static final Logger log = LoggerFactory.getLogger(PaymentChannelClient.class);
    @GuardedBy("lock")
    private final ClientConnection conn;
    @GuardedBy("lock")
    @VisibleForTesting
    boolean connectionOpen;
    @GuardedBy("lock")
    SettableFuture<PaymentIncrementAck> increasePaymentFuture;
    @GuardedBy("lock")
    Coin lastPaymentActualAmount;
    protected final ReentrantLock lock;
    @GuardedBy("lock")
    private int majorVersion;
    private final Coin maxValue;
    @GuardedBy("lock")
    private long minPayment;
    private Coin missing;
    private final ECKey myKey;
    private final Sha256Hash serverId;
    @GuardedBy("lock")
    private PaymentChannelClientState state;
    @GuardedBy("lock")
    private InitStep step;
    private StoredClientChannel storedChannel;
    private final long timeWindow;
    private KeyParameter userKeySetup;
    private final VersionSelector versionSelector;
    private final C3530Wallet wallet;

    /* renamed from: org.bitcoinj.protocols.channels.PaymentChannelClient$2 */
    static /* synthetic */ class C34642 {

        /* renamed from: $SwitchMap$org$bitcoinj$protocols$channels$PaymentChannelClient$VersionSelector */
        static final /* synthetic */ int[] f811x441465ce = new int[VersionSelector.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|28) */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0069 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0073 */
        static {
            /*
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType[] r0 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f810x7faf177c = r0
                r0 = 1
                int[] r1 = f810x7faf177c     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r2 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.SERVER_VERSION     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f810x7faf177c     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r3 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.INITIATE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f810x7faf177c     // Catch:{ NoSuchFieldError -> 0x002a }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r4 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.RETURN_REFUND     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f810x7faf177c     // Catch:{ NoSuchFieldError -> 0x0035 }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r4 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.CHANNEL_OPEN     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f810x7faf177c     // Catch:{ NoSuchFieldError -> 0x0040 }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r4 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.PAYMENT_ACK     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f810x7faf177c     // Catch:{ NoSuchFieldError -> 0x004b }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r4 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.CLOSE     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r3 = f810x7faf177c     // Catch:{ NoSuchFieldError -> 0x0056 }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r4 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.ERROR     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                org.bitcoinj.protocols.channels.PaymentChannelClient$VersionSelector[] r3 = org.bitcoinj.protocols.channels.PaymentChannelClient.VersionSelector.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f811x441465ce = r3
                int[] r3 = f811x441465ce     // Catch:{ NoSuchFieldError -> 0x0069 }
                org.bitcoinj.protocols.channels.PaymentChannelClient$VersionSelector r4 = org.bitcoinj.protocols.channels.PaymentChannelClient.VersionSelector.VERSION_1     // Catch:{ NoSuchFieldError -> 0x0069 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0069 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0069 }
            L_0x0069:
                int[] r0 = f811x441465ce     // Catch:{ NoSuchFieldError -> 0x0073 }
                org.bitcoinj.protocols.channels.PaymentChannelClient$VersionSelector r3 = org.bitcoinj.protocols.channels.PaymentChannelClient.VersionSelector.VERSION_2_ALLOW_1     // Catch:{ NoSuchFieldError -> 0x0073 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0073 }
            L_0x0073:
                int[] r0 = f811x441465ce     // Catch:{ NoSuchFieldError -> 0x007d }
                org.bitcoinj.protocols.channels.PaymentChannelClient$VersionSelector r1 = org.bitcoinj.protocols.channels.PaymentChannelClient.VersionSelector.VERSION_2     // Catch:{ NoSuchFieldError -> 0x007d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.PaymentChannelClient.C34642.<clinit>():void");
        }
    }

    private enum InitStep {
        WAITING_FOR_CONNECTION_OPEN,
        WAITING_FOR_VERSION_NEGOTIATION,
        WAITING_FOR_INITIATE,
        WAITING_FOR_REFUND_RETURN,
        WAITING_FOR_CHANNEL_OPEN,
        CHANNEL_OPEN,
        WAITING_FOR_CHANNEL_CLOSE,
        CHANNEL_CLOSED
    }

    public enum VersionSelector {
        VERSION_1,
        VERSION_2_ALLOW_1,
        VERSION_2;

        public int getRequestedMinorVersion() {
            return 0;
        }

        public int getRequestedMajorVersion() {
            return C34642.f811x441465ce[ordinal()] != 1 ? 2 : 1;
        }

        public boolean isServerVersionAccepted(int i, int i2) {
            int i3 = C34642.f811x441465ce[ordinal()];
            boolean z = false;
            if (i3 == 1) {
                if (i == 1) {
                    z = true;
                }
                return z;
            } else if (i3 == 2) {
                if (i == 1 || i == 2) {
                    z = true;
                }
                return z;
            } else if (i3 != 3) {
                return false;
            } else {
                if (i == 2) {
                    z = true;
                }
                return z;
            }
        }
    }

    public PaymentChannelClient(C3530Wallet wallet2, ECKey eCKey, Coin coin, Sha256Hash sha256Hash, ClientConnection clientConnection) {
        this(wallet2, eCKey, coin, sha256Hash, clientConnection, VersionSelector.VERSION_2_ALLOW_1);
    }

    public PaymentChannelClient(C3530Wallet wallet2, ECKey eCKey, Coin coin, Sha256Hash sha256Hash, ClientConnection clientConnection, VersionSelector versionSelector2) {
        this(wallet2, eCKey, coin, sha256Hash, DEFAULT_TIME_WINDOW, null, clientConnection, versionSelector2);
    }

    public PaymentChannelClient(C3530Wallet wallet2, ECKey eCKey, Coin coin, Sha256Hash sha256Hash, long j, @Nullable KeyParameter keyParameter, ClientConnection clientConnection) {
        this(wallet2, eCKey, coin, sha256Hash, j, keyParameter, clientConnection, VersionSelector.VERSION_2_ALLOW_1);
    }

    public PaymentChannelClient(C3530Wallet wallet2, ECKey eCKey, Coin coin, Sha256Hash sha256Hash, long j, @Nullable KeyParameter keyParameter, ClientConnection clientConnection, VersionSelector versionSelector2) {
        this.lock = Threading.lock("channelclient");
        boolean z = false;
        this.connectionOpen = false;
        this.step = InitStep.WAITING_FOR_CONNECTION_OPEN;
        this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
        this.myKey = (ECKey) Preconditions.checkNotNull(eCKey);
        this.maxValue = (Coin) Preconditions.checkNotNull(coin);
        this.serverId = (Sha256Hash) Preconditions.checkNotNull(sha256Hash);
        if (j >= 0) {
            z = true;
        }
        Preconditions.checkState(z);
        this.timeWindow = j;
        this.conn = (ClientConnection) Preconditions.checkNotNull(clientConnection);
        this.userKeySetup = keyParameter;
        this.versionSelector = versionSelector2;
    }

    public Coin getMissing() {
        return this.missing;
    }

    @GuardedBy("lock")
    @Nullable
    private CloseReason receiveInitiate(Initiate initiate, Coin coin, Builder builder) throws VerificationException, InsufficientMoneyException, KeyIsEncryptedException {
        log.info("Got INITIATE message:\n{}", (Object) initiate.toString());
        if (!this.wallet.isEncrypted() || this.userKeySetup != null) {
            long expireTimeSecs = initiate.getExpireTimeSecs();
            Preconditions.checkState(expireTimeSecs >= 0 && initiate.getMinAcceptedChannelSize() >= 0);
            if (!this.conn.acceptExpireTime(expireTimeSecs)) {
                log.error("Server suggested expire time was out of our allowed bounds: {} ({} s)", (Object) C3447Utils.dateTimeFormat(1000 * expireTimeSecs), (Object) Long.valueOf(expireTimeSecs));
                builder.setCode(ErrorCode.TIME_WINDOW_UNACCEPTABLE);
                return CloseReason.TIME_WINDOW_UNACCEPTABLE;
            }
            Coin valueOf = Coin.valueOf(initiate.getMinAcceptedChannelSize());
            if (coin.compareTo(valueOf) < 0) {
                log.error("Server requested too much value");
                builder.setCode(ErrorCode.CHANNEL_VALUE_TOO_LARGE);
                this.missing = valueOf.subtract(coin);
                return CloseReason.SERVER_REQUESTED_TOO_MUCH_VALUE;
            }
            long j = Transaction.REFERENCE_DEFAULT_MIN_TX_FEE.value;
            if (initiate.getMinPayment() != j) {
                log.error("Server requested a min payment of {} but we expected {}", (Object) Long.valueOf(initiate.getMinPayment()), (Object) Long.valueOf(j));
                builder.setCode(ErrorCode.MIN_PAYMENT_TOO_LARGE);
                builder.setExpectedValue(j);
                this.missing = Coin.valueOf(initiate.getMinPayment() - j);
                return CloseReason.SERVER_REQUESTED_TOO_MUCH_VALUE;
            }
            byte[] byteArray = initiate.getMultisigKey().toByteArray();
            if (ECKey.isPubKeyCanonical(byteArray)) {
                int i = this.majorVersion;
                if (i == 1) {
                    PaymentChannelV1ClientState paymentChannelV1ClientState = new PaymentChannelV1ClientState(this.wallet, this.myKey, ECKey.fromPublicOnly(byteArray), coin, expireTimeSecs);
                    this.state = paymentChannelV1ClientState;
                } else if (i != 2) {
                    return CloseReason.NO_ACCEPTABLE_VERSION;
                } else {
                    PaymentChannelV2ClientState paymentChannelV2ClientState = new PaymentChannelV2ClientState(this.wallet, this.myKey, ECKey.fromPublicOnly(byteArray), coin, expireTimeSecs);
                    this.state = paymentChannelV2ClientState;
                }
                try {
                    this.state.initiate(this.userKeySetup);
                    this.minPayment = initiate.getMinPayment();
                    int i2 = this.majorVersion;
                    if (i2 == 1) {
                        this.step = InitStep.WAITING_FOR_REFUND_RETURN;
                        this.conn.sendToServer(TwoWayChannelMessage.newBuilder().setProvideRefund(ProvideRefund.newBuilder().setMultisigKey(ByteString.copyFrom(this.myKey.getPubKey())).setTx(ByteString.copyFrom(((PaymentChannelV1ClientState) this.state).getIncompleteRefundTransaction().unsafeBitcoinSerialize()))).setType(MessageType.PROVIDE_REFUND).build());
                    } else if (i2 != 2) {
                        return CloseReason.NO_ACCEPTABLE_VERSION;
                    } else {
                        this.step = InitStep.WAITING_FOR_CHANNEL_OPEN;
                        this.state.storeChannelInWallet(this.serverId);
                        ProvideContract.Builder clientKey = ProvideContract.newBuilder().setTx(ByteString.copyFrom(this.state.getContract().unsafeBitcoinSerialize())).setClientKey(ByteString.copyFrom(this.myKey.getPubKey()));
                        try {
                            IncrementedPayment incrementPaymentBy = state().incrementPaymentBy(Coin.valueOf(this.minPayment), this.userKeySetup);
                            UpdatePayment.Builder initialPaymentBuilder = clientKey.getInitialPaymentBuilder();
                            initialPaymentBuilder.setSignature(ByteString.copyFrom(incrementPaymentBy.signature.encodeToBitcoin()));
                            initialPaymentBuilder.setClientChangeValue(this.state.getValueRefunded().value);
                            this.userKeySetup = null;
                            TwoWayChannelMessage.Builder newBuilder = TwoWayChannelMessage.newBuilder();
                            newBuilder.setProvideContract(clientKey);
                            newBuilder.setType(MessageType.PROVIDE_CONTRACT);
                            this.conn.sendToServer(newBuilder.build());
                        } catch (ValueOutOfRangeException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                    return null;
                } catch (ValueOutOfRangeException e2) {
                    log.error("Value out of range when trying to initiate", (Throwable) e2);
                    builder.setCode(ErrorCode.CHANNEL_VALUE_TOO_LARGE);
                    return CloseReason.SERVER_REQUESTED_TOO_MUCH_VALUE;
                }
            } else {
                throw new VerificationException("Server gave us a non-canonical public key, protocol error.");
            }
        } else {
            throw new KeyIsEncryptedException();
        }
    }

    @GuardedBy("lock")
    private void receiveRefund(TwoWayChannelMessage twoWayChannelMessage, @Nullable KeyParameter keyParameter) throws VerificationException {
        boolean z = false;
        Preconditions.checkState(this.majorVersion == 1);
        if (this.step == InitStep.WAITING_FOR_REFUND_RETURN && twoWayChannelMessage.hasReturnRefund()) {
            z = true;
        }
        Preconditions.checkState(z);
        log.info("Got RETURN_REFUND message, providing signed contract");
        ((PaymentChannelV1ClientState) this.state).provideRefundSignature(twoWayChannelMessage.getReturnRefund().getSignature().toByteArray(), keyParameter);
        this.step = InitStep.WAITING_FOR_CHANNEL_OPEN;
        this.state.storeChannelInWallet(this.serverId);
        ProvideContract.Builder tx = ProvideContract.newBuilder().setTx(ByteString.copyFrom(this.state.getContract().unsafeBitcoinSerialize()));
        try {
            IncrementedPayment incrementPaymentBy = state().incrementPaymentBy(Coin.valueOf(this.minPayment), keyParameter);
            UpdatePayment.Builder initialPaymentBuilder = tx.getInitialPaymentBuilder();
            initialPaymentBuilder.setSignature(ByteString.copyFrom(incrementPaymentBy.signature.encodeToBitcoin()));
            initialPaymentBuilder.setClientChangeValue(this.state.getValueRefunded().value);
            TwoWayChannelMessage.Builder newBuilder = TwoWayChannelMessage.newBuilder();
            newBuilder.setProvideContract(tx);
            newBuilder.setType(MessageType.PROVIDE_CONTRACT);
            this.conn.sendToServer(newBuilder.build());
        } catch (ValueOutOfRangeException e) {
            throw new IllegalStateException(e);
        }
    }

    @GuardedBy("lock")
    private void receiveChannelOpen() throws VerificationException {
        boolean z = false;
        Preconditions.checkState(this.step == InitStep.WAITING_FOR_CHANNEL_OPEN || (this.step == InitStep.WAITING_FOR_INITIATE && this.storedChannel != null), this.step);
        log.info("Got CHANNEL_OPEN message, ready to pay");
        if (this.step == InitStep.WAITING_FOR_INITIATE) {
            int i = this.majorVersion;
            if (i == 1) {
                this.state = new PaymentChannelV1ClientState(this.storedChannel, this.wallet);
            } else if (i == 2) {
                this.state = new PaymentChannelV2ClientState(this.storedChannel, this.wallet);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid version number ");
                sb.append(this.majorVersion);
                throw new IllegalStateException(sb.toString());
            }
        } else {
            z = true;
        }
        this.step = InitStep.CHANNEL_OPEN;
        this.conn.channelOpen(z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0065, code lost:
        r5.lock.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void receiveMessage(org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage r6) throws org.bitcoinj.core.InsufficientMoneyException {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.lock()
            boolean r0 = r5.connectionOpen     // Catch:{ all -> 0x0168 }
            com.google.common.base.Preconditions.checkState(r0)     // Catch:{ all -> 0x0168 }
            int[] r0 = org.bitcoinj.protocols.channels.PaymentChannelClient.C34642.f810x7faf177c     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r1 = r6.getType()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            int r1 = r1.ordinal()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r0 = r0[r1]     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r1 = ""
            r2 = 1
            r3 = 0
            switch(r0) {
                case 1: goto L_0x00b7;
                case 2: goto L_0x0084;
                case 3: goto L_0x007b;
                case 4: goto L_0x0077;
                case 5: goto L_0x006f;
                case 6: goto L_0x006b;
                case 7: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            org.slf4j.Logger r6 = log     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x00fe
        L_0x0021:
            boolean r0 = r6.hasError()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            com.google.common.base.Preconditions.checkState(r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.slf4j.Logger r0 = log     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r2 = "Server sent ERROR {} with explanation {}"
            org.bitcoin.paymentchannel.Protos$Error r3 = r6.getError()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$ErrorCode r3 = r3.getCode()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r3 = r3.name()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error r4 = r6.getError()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            boolean r4 = r4.hasExplanation()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            if (r4 == 0) goto L_0x004a
            org.bitcoin.paymentchannel.Protos$Error r1 = r6.getError()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r1 = r1.getExplanation()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
        L_0x004a:
            r0.error(r2, r3, r1)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r0 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.REMOTE_SENT_ERROR     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error r6 = r6.getError()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$ErrorCode r6 = r6.getCode()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r6 = r6.name()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r5.setIncreasePaymentFutureIfNeeded(r0, r6)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.IPaymentChannelClient$ClientConnection r6 = r5.conn     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r0 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.REMOTE_SENT_ERROR     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r6.destroyConnection(r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
        L_0x0065:
            java.util.concurrent.locks.ReentrantLock r6 = r5.lock
            r6.unlock()
            return
        L_0x006b:
            r5.receiveClose(r6)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0065
        L_0x006f:
            org.bitcoin.paymentchannel.Protos$PaymentAck r6 = r6.getPaymentAck()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r5.receivePaymentAck(r6)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0065
        L_0x0077:
            r5.receiveChannelOpen()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0065
        L_0x007b:
            org.spongycastle.crypto.params.KeyParameter r0 = r5.userKeySetup     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r5.receiveRefund(r6, r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r6 = 0
            r5.userKeySetup = r6     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0065
        L_0x0084:
            org.bitcoinj.protocols.channels.PaymentChannelClient$InitStep r0 = r5.step     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelClient$InitStep r1 = org.bitcoinj.protocols.channels.PaymentChannelClient.InitStep.WAITING_FOR_INITIATE     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            if (r0 != r1) goto L_0x0091
            boolean r0 = r6.hasInitiate()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            if (r0 == 0) goto L_0x0091
            goto L_0x0092
        L_0x0091:
            r2 = 0
        L_0x0092:
            com.google.common.base.Preconditions.checkState(r2)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Initiate r6 = r6.getInitiate()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r0 = org.bitcoin.paymentchannel.Protos.Error.newBuilder()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.core.Coin r1 = r5.maxValue     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r6 = r5.receiveInitiate(r6, r1, r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            if (r6 != 0) goto L_0x00a6
            goto L_0x0065
        L_0x00a6:
            org.slf4j.Logger r1 = log     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r2 = "Initiate failed with error: {}"
            org.bitcoin.paymentchannel.Protos$Error r3 = r0.build()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r3 = r3.toString()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r1.error(r2, r3)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0146
        L_0x00b7:
            org.bitcoinj.protocols.channels.PaymentChannelClient$InitStep r0 = r5.step     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelClient$InitStep r1 = org.bitcoinj.protocols.channels.PaymentChannelClient.InitStep.WAITING_FOR_VERSION_NEGOTIATION     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            if (r0 != r1) goto L_0x00c4
            boolean r0 = r6.hasServerVersion()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            if (r0 == 0) goto L_0x00c4
            goto L_0x00c5
        L_0x00c4:
            r2 = 0
        L_0x00c5:
            com.google.common.base.Preconditions.checkState(r2)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$ServerVersion r0 = r6.getServerVersion()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            int r0 = r0.getMajor()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r5.majorVersion = r0     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelClient$VersionSelector r0 = r5.versionSelector     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            int r1 = r5.majorVersion     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$ServerVersion r6 = r6.getServerVersion()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            int r6 = r6.getMinor()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            boolean r6 = r0.isServerVersionAccepted(r1, r6)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            if (r6 != 0) goto L_0x00f1
            org.bitcoin.paymentchannel.Protos$Error$Builder r6 = org.bitcoin.paymentchannel.Protos.Error.newBuilder()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$ErrorCode r0 = org.bitcoin.paymentchannel.Protos.Error.ErrorCode.NO_ACCEPTABLE_VERSION     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r0 = r6.setCode(r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r6 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.NO_ACCEPTABLE_VERSION     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0146
        L_0x00f1:
            org.slf4j.Logger r6 = log     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            java.lang.String r0 = "Got version handshake, awaiting INITIATE or resume CHANNEL_OPEN"
            r6.info(r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelClient$InitStep r6 = org.bitcoinj.protocols.channels.PaymentChannelClient.InitStep.WAITING_FOR_INITIATE     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r5.step = r6     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0065
        L_0x00fe:
            java.lang.String r0 = "Got unknown message type or type that doesn't apply to clients."
            r6.error(r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r6 = org.bitcoin.paymentchannel.Protos.Error.newBuilder()     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$ErrorCode r0 = org.bitcoin.paymentchannel.Protos.Error.ErrorCode.SYNTAX_ERROR     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r0 = r6.setCode(r0)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r6 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.REMOTE_SENT_INVALID_MESSAGE     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            r5.setIncreasePaymentFutureIfNeeded(r6, r1)     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r6 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.REMOTE_SENT_INVALID_MESSAGE     // Catch:{ VerificationException -> 0x012a, IllegalStateException -> 0x0115 }
            goto L_0x0146
        L_0x0115:
            r6 = move-exception
            org.slf4j.Logger r0 = log     // Catch:{ all -> 0x0168 }
            java.lang.String r1 = "Caught illegal state exception handling message from server"
            r0.error(r1, r6)     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r6 = org.bitcoin.paymentchannel.Protos.Error.newBuilder()     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$Error$ErrorCode r0 = org.bitcoin.paymentchannel.Protos.Error.ErrorCode.SYNTAX_ERROR     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r0 = r6.setCode(r0)     // Catch:{ all -> 0x0168 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r6 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.REMOTE_SENT_INVALID_MESSAGE     // Catch:{ all -> 0x0168 }
            goto L_0x0146
        L_0x012a:
            r6 = move-exception
            org.slf4j.Logger r0 = log     // Catch:{ all -> 0x0168 }
            java.lang.String r1 = "Caught verification exception handling message from server"
            r0.error(r1, r6)     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r0 = org.bitcoin.paymentchannel.Protos.Error.newBuilder()     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$Error$ErrorCode r1 = org.bitcoin.paymentchannel.Protos.Error.ErrorCode.BAD_TRANSACTION     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r0 = r0.setCode(r1)     // Catch:{ all -> 0x0168 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$Error$Builder r0 = r0.setExplanation(r6)     // Catch:{ all -> 0x0168 }
            org.bitcoinj.protocols.channels.PaymentChannelCloseException$CloseReason r6 = org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason.REMOTE_SENT_INVALID_MESSAGE     // Catch:{ all -> 0x0168 }
        L_0x0146:
            org.bitcoinj.protocols.channels.IPaymentChannelClient$ClientConnection r1 = r5.conn     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$Builder r2 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.newBuilder()     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$Builder r0 = r2.setError(r0)     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r2 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.ERROR     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$Builder r0 = r0.setType(r2)     // Catch:{ all -> 0x0168 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage r0 = r0.build()     // Catch:{ all -> 0x0168 }
            r1.sendToServer(r0)     // Catch:{ all -> 0x0168 }
            org.bitcoinj.protocols.channels.IPaymentChannelClient$ClientConnection r0 = r5.conn     // Catch:{ all -> 0x0168 }
            r0.destroyConnection(r6)     // Catch:{ all -> 0x0168 }
            java.util.concurrent.locks.ReentrantLock r6 = r5.lock
            r6.unlock()
            return
        L_0x0168:
            r6 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.PaymentChannelClient.receiveMessage(org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage):void");
    }

    private void setIncreasePaymentFutureIfNeeded(CloseReason closeReason, String str) {
        SettableFuture<PaymentIncrementAck> settableFuture = this.increasePaymentFuture;
        if (settableFuture != null && !settableFuture.isDone()) {
            this.increasePaymentFuture.setException(new PaymentChannelCloseException(str, closeReason));
        }
    }

    @GuardedBy("lock")
    private void receiveClose(TwoWayChannelMessage twoWayChannelMessage) throws VerificationException {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        if (twoWayChannelMessage.hasSettlement()) {
            Transaction makeTransaction = this.wallet.getParams().getDefaultSerializer().makeTransaction(twoWayChannelMessage.getSettlement().getTx().toByteArray());
            log.info("CLOSE message received with settlement tx {}", (Object) makeTransaction.getHash());
            if (this.state != null && state().isSettlementTransaction(makeTransaction)) {
                this.wallet.receivePending(makeTransaction, null);
            }
        } else {
            log.info("CLOSE message received without settlement tx");
        }
        if (this.step == InitStep.WAITING_FOR_CHANNEL_CLOSE) {
            this.conn.destroyConnection(CloseReason.CLIENT_REQUESTED_CLOSE);
        } else {
            this.conn.destroyConnection(CloseReason.SERVER_REQUESTED_CLOSE);
        }
        this.step = InitStep.CHANNEL_CLOSED;
    }

    public void connectionClosed() {
        this.lock.lock();
        try {
            this.connectionOpen = false;
            if (this.state != null) {
                this.state.disconnectFromChannel();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void settle() throws IllegalStateException {
        this.lock.lock();
        try {
            Preconditions.checkState(this.connectionOpen);
            this.step = InitStep.WAITING_FOR_CHANNEL_CLOSE;
            log.info("Sending a CLOSE message to the server and waiting for response indicating successful settlement.");
            this.conn.sendToServer(TwoWayChannelMessage.newBuilder().setType(MessageType.CLOSE).build());
        } finally {
            this.lock.unlock();
        }
    }

    public void connectionOpen() {
        this.lock.lock();
        try {
            this.connectionOpen = true;
            StoredPaymentChannelClientStates storedPaymentChannelClientStates = (StoredPaymentChannelClientStates) this.wallet.getExtensions().get(StoredPaymentChannelClientStates.EXTENSION_ID);
            if (storedPaymentChannelClientStates != null) {
                this.storedChannel = storedPaymentChannelClientStates.getUsableChannelForServerID(this.serverId);
            }
            this.step = InitStep.WAITING_FOR_VERSION_NEGOTIATION;
            ClientVersion.Builder timeWindowSecs = ClientVersion.newBuilder().setMajor(this.versionSelector.getRequestedMajorVersion()).setMinor(this.versionSelector.getRequestedMinorVersion()).setTimeWindowSecs(this.timeWindow);
            if (this.storedChannel != null) {
                timeWindowSecs.setPreviousChannelContractHash(ByteString.copyFrom(this.storedChannel.contract.getHash().getBytes()));
                log.info("Begun version handshake, attempting to reopen channel with contract hash {}", (Object) this.storedChannel.contract.getHash());
            } else {
                log.info("Begun version handshake creating new channel");
            }
            this.conn.sendToServer(TwoWayChannelMessage.newBuilder().setType(MessageType.CLIENT_VERSION).setClientVersion(timeWindowSecs).build());
        } finally {
            this.lock.unlock();
        }
    }

    public PaymentChannelClientState state() {
        this.lock.lock();
        try {
            return this.state;
        } finally {
            this.lock.unlock();
        }
    }

    public ListenableFuture<PaymentIncrementAck> incrementPayment(Coin coin) throws ValueOutOfRangeException, IllegalStateException {
        return incrementPayment(coin, null, null);
    }

    public ListenableFuture<PaymentIncrementAck> incrementPayment(Coin coin, @Nullable ByteString byteString, @Nullable KeyParameter keyParameter) throws ValueOutOfRangeException, IllegalStateException, KeyIsEncryptedException {
        this.lock.lock();
        try {
            if (state() == null || !this.connectionOpen || this.step != InitStep.CHANNEL_OPEN) {
                throw new IllegalStateException("Channel is not fully initialized/has already been closed");
            } else if (this.increasePaymentFuture == null) {
                if (this.wallet.isEncrypted()) {
                    if (keyParameter == null) {
                        throw new KeyIsEncryptedException();
                    }
                }
                IncrementedPayment incrementPaymentBy = state().incrementPaymentBy(coin, keyParameter);
                UpdatePayment.Builder clientChangeValue = UpdatePayment.newBuilder().setSignature(ByteString.copyFrom(incrementPaymentBy.signature.encodeToBitcoin())).setClientChangeValue(this.state.getValueRefunded().value);
                if (byteString != null) {
                    clientChangeValue.setInfo(byteString);
                }
                this.increasePaymentFuture = SettableFuture.create();
                this.increasePaymentFuture.addListener(new Runnable() {
                    public void run() {
                        PaymentChannelClient.this.lock.lock();
                        PaymentChannelClient paymentChannelClient = PaymentChannelClient.this;
                        paymentChannelClient.increasePaymentFuture = null;
                        paymentChannelClient.lock.unlock();
                    }
                }, MoreExecutors.sameThreadExecutor());
                this.conn.sendToServer(TwoWayChannelMessage.newBuilder().setUpdatePayment(clientChangeValue).setType(MessageType.UPDATE_PAYMENT).build());
                this.lastPaymentActualAmount = incrementPaymentBy.amount;
                return this.increasePaymentFuture;
            } else {
                throw new IllegalStateException("Already incrementing paying, wait for previous payment to complete.");
            }
        } finally {
            this.lock.unlock();
        }
    }

    private void receivePaymentAck(PaymentAck paymentAck) {
        this.lock.lock();
        try {
            if (this.increasePaymentFuture != null) {
                Preconditions.checkNotNull(this.increasePaymentFuture, "Server sent a PAYMENT_ACK with no outstanding payment");
                log.info("Received a PAYMENT_ACK from the server");
                SettableFuture<PaymentIncrementAck> settableFuture = this.increasePaymentFuture;
                Coin coin = this.lastPaymentActualAmount;
                this.lock.unlock();
                settableFuture.set(new PaymentIncrementAck(coin, paymentAck.getInfo()));
            }
        } finally {
            this.lock.unlock();
        }
    }
}
