package org.bitcoinj.protocols.channels;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.net.InetSocketAddress;
import javax.annotation.Nullable;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.net.ProtobufConnection;
import org.bitcoinj.protocols.channels.PaymentChannelClient.VersionSelector;
import org.bitcoinj.wallet.C3530Wallet;
import org.spongycastle.crypto.params.KeyParameter;

public class PaymentChannelClientConnection {
    /* access modifiers changed from: private */
    public final PaymentChannelClient channelClient;
    /* access modifiers changed from: private */
    public final SettableFuture<PaymentChannelClientConnection> channelOpenFuture;
    /* access modifiers changed from: private */
    public final ProtobufConnection<TwoWayChannelMessage> wireParser;

    public PaymentChannelClientConnection(InetSocketAddress inetSocketAddress, int i, C3530Wallet wallet, ECKey eCKey, Coin coin, String str) throws IOException, ValueOutOfRangeException {
        this(inetSocketAddress, i, wallet, eCKey, coin, str, VersionSelector.VERSION_2_ALLOW_1);
    }

    public PaymentChannelClientConnection(InetSocketAddress inetSocketAddress, int i, C3530Wallet wallet, ECKey eCKey, Coin coin, String str, VersionSelector versionSelector) throws IOException, ValueOutOfRangeException {
        this(inetSocketAddress, i, wallet, eCKey, coin, str, PaymentChannelClient.DEFAULT_TIME_WINDOW, null, versionSelector);
    }

    public PaymentChannelClientConnection(InetSocketAddress inetSocketAddress, int i, C3530Wallet wallet, ECKey eCKey, Coin coin, String str, long j, @Nullable KeyParameter keyParameter) throws IOException, ValueOutOfRangeException {
        this(inetSocketAddress, i, wallet, eCKey, coin, str, j, keyParameter, VersionSelector.VERSION_2_ALLOW_1);
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage, com.google.protobuf.MessageLite] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v1, types: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage, com.google.protobuf.MessageLite]
      assigns: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage]
      uses: [com.google.protobuf.MessageLite]
      mth insns count: 24
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PaymentChannelClientConnection(java.net.InetSocketAddress r13, int r14, org.bitcoinj.wallet.C3530Wallet r15, org.bitcoinj.core.ECKey r16, org.bitcoinj.core.Coin r17, java.lang.String r18, long r19, @javax.annotation.Nullable org.spongycastle.crypto.params.KeyParameter r21, org.bitcoinj.protocols.channels.PaymentChannelClient.VersionSelector r22) throws java.io.IOException, org.bitcoinj.protocols.channels.ValueOutOfRangeException {
        /*
            r12 = this;
            r0 = r12
            r12.<init>()
            com.google.common.util.concurrent.SettableFuture r1 = com.google.common.util.concurrent.SettableFuture.create()
            r0.channelOpenFuture = r1
            org.bitcoinj.protocols.channels.PaymentChannelClient r1 = new org.bitcoinj.protocols.channels.PaymentChannelClient
            byte[] r2 = r18.getBytes()
            org.bitcoinj.core.Sha256Hash r6 = org.bitcoinj.core.Sha256Hash.m338of(r2)
            org.bitcoinj.protocols.channels.PaymentChannelClientConnection$1 r10 = new org.bitcoinj.protocols.channels.PaymentChannelClientConnection$1
            r7 = r19
            r10.<init>(r7)
            r2 = r1
            r3 = r15
            r4 = r16
            r5 = r17
            r9 = r21
            r11 = r22
            r2.<init>(r3, r4, r5, r6, r7, r9, r10, r11)
            r0.channelClient = r1
            org.bitcoinj.net.ProtobufConnection r1 = new org.bitcoinj.net.ProtobufConnection
            org.bitcoinj.protocols.channels.PaymentChannelClientConnection$2 r2 = new org.bitcoinj.protocols.channels.PaymentChannelClientConnection$2
            r2.<init>()
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage r3 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.getDefaultInstance()
            r4 = r14
            int r4 = r4 * 1000
            r5 = 32767(0x7fff, float:4.5916E-41)
            r1.<init>(r2, r3, r5, r4)
            r0.wireParser = r1
            org.bitcoinj.net.NioClient r1 = new org.bitcoinj.net.NioClient
            org.bitcoinj.net.ProtobufConnection<org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage> r2 = r0.wireParser
            r3 = r13
            r1.<init>(r13, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.PaymentChannelClientConnection.<init>(java.net.InetSocketAddress, int, org.bitcoinj.wallet.Wallet, org.bitcoinj.core.ECKey, org.bitcoinj.core.Coin, java.lang.String, long, org.spongycastle.crypto.params.KeyParameter, org.bitcoinj.protocols.channels.PaymentChannelClient$VersionSelector):void");
    }

    public ListenableFuture<PaymentChannelClientConnection> getChannelOpenFuture() {
        return this.channelOpenFuture;
    }

    public ListenableFuture<PaymentIncrementAck> incrementPayment(Coin coin) throws ValueOutOfRangeException, IllegalStateException {
        return this.channelClient.incrementPayment(coin, null, null);
    }

    public ListenableFuture<PaymentIncrementAck> incrementPayment(Coin coin, @Nullable ByteString byteString, @Nullable KeyParameter keyParameter) throws ValueOutOfRangeException, IllegalStateException {
        return this.channelClient.incrementPayment(coin, byteString, keyParameter);
    }

    public PaymentChannelClientState state() {
        return this.channelClient.state();
    }

    public void settle() {
        try {
            this.channelClient.settle();
        } catch (IllegalStateException unused) {
        }
    }

    public void disconnectWithoutSettlement() {
        this.wireParser.closeConnection();
    }
}
