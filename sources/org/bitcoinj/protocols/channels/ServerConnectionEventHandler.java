package org.bitcoinj.protocols.channels;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import javax.annotation.Nullable;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.net.ProtobufConnection;
import org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason;

public abstract class ServerConnectionEventHandler {
    private ProtobufConnection<TwoWayChannelMessage> connectionChannel;

    public abstract void channelClosed(CloseReason closeReason);

    public abstract void channelOpen(Sha256Hash sha256Hash);

    @Nullable
    public abstract ListenableFuture<ByteString> paymentIncrease(Coin coin, Coin coin2, ByteString byteString);

    /* access modifiers changed from: 0000 */
    public synchronized void setConnectionChannel(@Nullable ProtobufConnection<TwoWayChannelMessage> protobufConnection) {
        this.connectionChannel = protobufConnection;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage, com.google.protobuf.MessageLite] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v3, types: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage, com.google.protobuf.MessageLite]
      assigns: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage]
      uses: [com.google.protobuf.MessageLite]
      mth insns count: 18
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
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void closeChannel() {
        /*
            r3 = this;
            monitor-enter(r3)
            org.bitcoinj.net.ProtobufConnection<org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage> r0 = r3.connectionChannel     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x001f
            org.bitcoinj.net.ProtobufConnection<org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage> r0 = r3.connectionChannel     // Catch:{ all -> 0x0027 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$Builder r1 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.newBuilder()     // Catch:{ all -> 0x0027 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r2 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.CLOSE     // Catch:{ all -> 0x0027 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$Builder r1 = r1.setType(r2)     // Catch:{ all -> 0x0027 }
            org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage r1 = r1.build()     // Catch:{ all -> 0x0027 }
            r0.write(r1)     // Catch:{ all -> 0x0027 }
            org.bitcoinj.net.ProtobufConnection<org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage> r0 = r3.connectionChannel     // Catch:{ all -> 0x0027 }
            r0.closeConnection()     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)
            return
        L_0x001f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0027 }
            java.lang.String r1 = "Channel is not fully initialized/has already been closed"
            r0.<init>(r1)     // Catch:{ all -> 0x0027 }
            throw r0     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.ServerConnectionEventHandler.closeChannel():void");
    }
}
