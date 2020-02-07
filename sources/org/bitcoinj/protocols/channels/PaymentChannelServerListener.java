package org.bitcoinj.protocols.channels;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import javax.annotation.Nullable;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.net.NioServer;
import org.bitcoinj.net.ProtobufConnection;
import org.bitcoinj.net.ProtobufConnection.Listener;
import org.bitcoinj.net.StreamConnectionFactory;
import org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason;
import org.bitcoinj.wallet.C3530Wallet;

public class PaymentChannelServerListener {
    /* access modifiers changed from: private */
    public final TransactionBroadcaster broadcaster;
    /* access modifiers changed from: private */
    public final HandlerFactory eventHandlerFactory;
    /* access modifiers changed from: private */
    public final Coin minAcceptedChannelSize;
    private NioServer server;
    /* access modifiers changed from: private */
    public final int timeoutSeconds;
    /* access modifiers changed from: private */
    public final C3530Wallet wallet;

    public interface HandlerFactory {
        @Nullable
        ServerConnectionEventHandler onNewConnection(SocketAddress socketAddress);
    }

    private class ServerHandler {
        /* access modifiers changed from: private */
        public CloseReason closeReason;
        /* access modifiers changed from: private */
        public ServerConnectionEventHandler eventHandler;
        /* access modifiers changed from: private */
        public final PaymentChannelServer paymentChannelManager;
        private final Listener<TwoWayChannelMessage> protobufHandlerListener;
        /* access modifiers changed from: private */
        public final ProtobufConnection<TwoWayChannelMessage> socketProtobufHandler;

        /* JADX WARNING: type inference failed for: r0v2, types: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage, com.google.protobuf.MessageLite] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v2, types: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage, com.google.protobuf.MessageLite]
          assigns: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 15
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ServerHandler(final java.net.SocketAddress r7, int r8) {
            /*
                r5 = this;
                org.bitcoinj.protocols.channels.PaymentChannelServerListener.this = r6
                r5.<init>()
                org.bitcoinj.protocols.channels.PaymentChannelServer r0 = new org.bitcoinj.protocols.channels.PaymentChannelServer
                org.bitcoinj.core.TransactionBroadcaster r1 = r6.broadcaster
                org.bitcoinj.wallet.Wallet r2 = r6.wallet
                org.bitcoinj.core.Coin r3 = r6.minAcceptedChannelSize
                org.bitcoinj.protocols.channels.PaymentChannelServerListener$ServerHandler$1 r4 = new org.bitcoinj.protocols.channels.PaymentChannelServerListener$ServerHandler$1
                r4.<init>(r6)
                r0.<init>(r1, r2, r3, r4)
                r5.paymentChannelManager = r0
                org.bitcoinj.protocols.channels.PaymentChannelServerListener$ServerHandler$2 r0 = new org.bitcoinj.protocols.channels.PaymentChannelServerListener$ServerHandler$2
                r0.<init>(r6, r7)
                r5.protobufHandlerListener = r0
                org.bitcoinj.net.ProtobufConnection r6 = new org.bitcoinj.net.ProtobufConnection
                org.bitcoinj.net.ProtobufConnection$Listener<org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage> r7 = r5.protobufHandlerListener
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage r0 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.getDefaultInstance()
                int r8 = r8 * 1000
                r1 = 32767(0x7fff, float:4.5916E-41)
                r6.<init>(r7, r0, r1, r8)
                r5.socketProtobufHandler = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.PaymentChannelServerListener.ServerHandler.<init>(org.bitcoinj.protocols.channels.PaymentChannelServerListener, java.net.SocketAddress, int):void");
        }
    }

    public void bindAndStart(int i) throws Exception {
        this.server = new NioServer(new StreamConnectionFactory() {
            public ProtobufConnection<TwoWayChannelMessage> getNewConnection(InetAddress inetAddress, int i) {
                return new ServerHandler(new InetSocketAddress(inetAddress, i), PaymentChannelServerListener.this.timeoutSeconds).socketProtobufHandler;
            }
        }, new InetSocketAddress(i));
        this.server.startAsync();
        this.server.awaitRunning();
    }

    public PaymentChannelServerListener(TransactionBroadcaster transactionBroadcaster, C3530Wallet wallet2, int i, Coin coin, HandlerFactory handlerFactory) throws IOException {
        this.wallet = (C3530Wallet) Preconditions.checkNotNull(wallet2);
        this.broadcaster = (TransactionBroadcaster) Preconditions.checkNotNull(transactionBroadcaster);
        this.eventHandlerFactory = (HandlerFactory) Preconditions.checkNotNull(handlerFactory);
        this.minAcceptedChannelSize = (Coin) Preconditions.checkNotNull(coin);
        this.timeoutSeconds = i;
    }

    public void close() {
        this.server.stopAsync();
        this.server.awaitTerminated();
    }
}
