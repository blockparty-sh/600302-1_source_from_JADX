package org.bitcoinj.core;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.NotYetConnectedException;
import java.util.concurrent.locks.Lock;
import org.bitcoinj.core.BitcoinSerializer.BitcoinPacketHeader;
import org.bitcoinj.net.AbstractTimeoutHandler;
import org.bitcoinj.net.MessageWriteTarget;
import org.bitcoinj.net.StreamConnection;
import org.bitcoinj.utils.Threading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PeerSocketHandler extends AbstractTimeoutHandler implements StreamConnection {
    private static final Logger log = LoggerFactory.getLogger(PeerSocketHandler.class);
    private boolean closePending = false;
    private BitcoinPacketHeader header;
    private byte[] largeReadBuffer;
    private int largeReadBufferPos;
    private Lock lock = Threading.lock("PeerSocketHandler");
    protected PeerAddress peerAddress;
    private final MessageSerializer serializer;
    @VisibleForTesting
    protected MessageWriteTarget writeTarget = null;

    public int getMaxMessageSize() {
        return Message.MAX_SIZE;
    }

    /* access modifiers changed from: protected */
    public abstract void processMessage(Message message) throws Exception;

    public PeerSocketHandler(NetworkParameters networkParameters, InetSocketAddress inetSocketAddress) {
        Preconditions.checkNotNull(networkParameters);
        this.serializer = networkParameters.getDefaultSerializer();
        this.peerAddress = new PeerAddress(networkParameters, inetSocketAddress);
    }

    public PeerSocketHandler(NetworkParameters networkParameters, PeerAddress peerAddress2) {
        Preconditions.checkNotNull(networkParameters);
        this.serializer = networkParameters.getDefaultSerializer();
        this.peerAddress = (PeerAddress) Preconditions.checkNotNull(peerAddress2);
    }

    /* JADX INFO: finally extract failed */
    public void sendMessage(Message message) throws NotYetConnectedException {
        this.lock.lock();
        try {
            if (this.writeTarget != null) {
                this.lock.unlock();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    this.serializer.serialize(message, byteArrayOutputStream);
                    this.writeTarget.writeBytes(byteArrayOutputStream.toByteArray());
                } catch (IOException e) {
                    exceptionCaught(e);
                }
            } else {
                throw new NotYetConnectedException();
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public void close() {
        this.lock.lock();
        try {
            if (this.writeTarget == null) {
                this.closePending = true;
                return;
            }
            this.lock.unlock();
            this.writeTarget.closeConnection();
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void timeoutOccurred() {
        log.info("{}: Timed out", (Object) getAddress());
        close();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int receiveBytes(java.nio.ByteBuffer r5) {
        /*
            r4 = this;
            int r0 = r5.position()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0012
            int r0 = r5.capacity()
            r3 = 24
            if (r0 < r3) goto L_0x0012
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            com.google.common.base.Preconditions.checkArgument(r0)
        L_0x0016:
            byte[] r0 = r4.largeReadBuffer     // Catch:{ Exception -> 0x00c4 }
            if (r0 == 0) goto L_0x005b
            com.google.common.base.Preconditions.checkState(r1)     // Catch:{ Exception -> 0x00c4 }
            int r0 = r5.remaining()     // Catch:{ Exception -> 0x00c4 }
            byte[] r1 = r4.largeReadBuffer     // Catch:{ Exception -> 0x00c4 }
            int r1 = r1.length     // Catch:{ Exception -> 0x00c4 }
            int r3 = r4.largeReadBufferPos     // Catch:{ Exception -> 0x00c4 }
            int r1 = r1 - r3
            int r0 = java.lang.Math.min(r0, r1)     // Catch:{ Exception -> 0x00c4 }
            byte[] r1 = r4.largeReadBuffer     // Catch:{ Exception -> 0x00c4 }
            int r3 = r4.largeReadBufferPos     // Catch:{ Exception -> 0x00c4 }
            r5.get(r1, r3, r0)     // Catch:{ Exception -> 0x00c4 }
            int r1 = r4.largeReadBufferPos     // Catch:{ Exception -> 0x00c4 }
            int r1 = r1 + r0
            r4.largeReadBufferPos = r1     // Catch:{ Exception -> 0x00c4 }
            int r0 = r4.largeReadBufferPos     // Catch:{ Exception -> 0x00c4 }
            byte[] r1 = r4.largeReadBuffer     // Catch:{ Exception -> 0x00c4 }
            int r1 = r1.length     // Catch:{ Exception -> 0x00c4 }
            if (r0 != r1) goto L_0x0056
            org.bitcoinj.core.MessageSerializer r0 = r4.serializer     // Catch:{ Exception -> 0x00c4 }
            org.bitcoinj.core.BitcoinSerializer$BitcoinPacketHeader r1 = r4.header     // Catch:{ Exception -> 0x00c4 }
            byte[] r3 = r4.largeReadBuffer     // Catch:{ Exception -> 0x00c4 }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r3)     // Catch:{ Exception -> 0x00c4 }
            org.bitcoinj.core.Message r0 = r0.deserializePayload(r1, r3)     // Catch:{ Exception -> 0x00c4 }
            r4.processMessage(r0)     // Catch:{ Exception -> 0x00c4 }
            r0 = 0
            r4.largeReadBuffer = r0     // Catch:{ Exception -> 0x00c4 }
            r4.header = r0     // Catch:{ Exception -> 0x00c4 }
            r1 = 0
            goto L_0x005b
        L_0x0056:
            int r5 = r5.position()     // Catch:{ Exception -> 0x00c4 }
            return r5
        L_0x005b:
            int r0 = r5.position()     // Catch:{ Exception -> 0x00c4 }
            org.bitcoinj.core.MessageSerializer r3 = r4.serializer     // Catch:{ BufferUnderflowException -> 0x006a }
            org.bitcoinj.core.Message r0 = r3.deserialize(r5)     // Catch:{ BufferUnderflowException -> 0x006a }
            r4.processMessage(r0)     // Catch:{ Exception -> 0x00c4 }
            r1 = 0
            goto L_0x0016
        L_0x006a:
            if (r1 == 0) goto L_0x00bc
            int r1 = r5.limit()     // Catch:{ Exception -> 0x00c4 }
            int r3 = r5.capacity()     // Catch:{ Exception -> 0x00c4 }
            if (r1 != r3) goto L_0x00bc
            r5.position(r2)     // Catch:{ Exception -> 0x00c4 }
            org.bitcoinj.core.MessageSerializer r0 = r4.serializer     // Catch:{ BufferUnderflowException -> 0x009c }
            r0.seekPastMagicBytes(r5)     // Catch:{ BufferUnderflowException -> 0x009c }
            org.bitcoinj.core.MessageSerializer r0 = r4.serializer     // Catch:{ BufferUnderflowException -> 0x009c }
            org.bitcoinj.core.BitcoinSerializer$BitcoinPacketHeader r0 = r0.deserializeHeader(r5)     // Catch:{ BufferUnderflowException -> 0x009c }
            r4.header = r0     // Catch:{ BufferUnderflowException -> 0x009c }
            org.bitcoinj.core.BitcoinSerializer$BitcoinPacketHeader r0 = r4.header     // Catch:{ BufferUnderflowException -> 0x009c }
            int r0 = r0.size     // Catch:{ BufferUnderflowException -> 0x009c }
            byte[] r0 = new byte[r0]     // Catch:{ BufferUnderflowException -> 0x009c }
            r4.largeReadBuffer = r0     // Catch:{ BufferUnderflowException -> 0x009c }
            int r0 = r5.remaining()     // Catch:{ BufferUnderflowException -> 0x009c }
            r4.largeReadBufferPos = r0     // Catch:{ BufferUnderflowException -> 0x009c }
            byte[] r0 = r4.largeReadBuffer     // Catch:{ BufferUnderflowException -> 0x009c }
            int r1 = r4.largeReadBufferPos     // Catch:{ BufferUnderflowException -> 0x009c }
            r5.get(r0, r2, r1)     // Catch:{ BufferUnderflowException -> 0x009c }
            goto L_0x00bf
        L_0x009c:
            org.bitcoinj.core.ProtocolException r0 = new org.bitcoinj.core.ProtocolException     // Catch:{ Exception -> 0x00c4 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c4 }
            r1.<init>()     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r2 = "No magic bytes+header after reading "
            r1.append(r2)     // Catch:{ Exception -> 0x00c4 }
            int r5 = r5.capacity()     // Catch:{ Exception -> 0x00c4 }
            r1.append(r5)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r5 = " bytes"
            r1.append(r5)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r5 = r1.toString()     // Catch:{ Exception -> 0x00c4 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x00c4 }
            throw r0     // Catch:{ Exception -> 0x00c4 }
        L_0x00bc:
            r5.position(r0)     // Catch:{ Exception -> 0x00c4 }
        L_0x00bf:
            int r5 = r5.position()     // Catch:{ Exception -> 0x00c4 }
            return r5
        L_0x00c4:
            r5 = move-exception
            r4.exceptionCaught(r5)
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.PeerSocketHandler.receiveBytes(java.nio.ByteBuffer):int");
    }

    public void setWriteTarget(MessageWriteTarget messageWriteTarget) {
        boolean z = true;
        Preconditions.checkArgument(messageWriteTarget != null);
        this.lock.lock();
        try {
            if (this.writeTarget != null) {
                z = false;
            }
            Preconditions.checkArgument(z);
            boolean z2 = this.closePending;
            this.writeTarget = messageWriteTarget;
            if (z2) {
                messageWriteTarget.closeConnection();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public PeerAddress getAddress() {
        return this.peerAddress;
    }

    private void exceptionCaught(Exception exc) {
        String str;
        PeerAddress address = getAddress();
        if (address == null) {
            str = "?";
        } else {
            str = address.toString();
        }
        String str2 = " - ";
        if ((exc instanceof ConnectException) || (exc instanceof IOException)) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            sb.append(exc.getMessage());
            logger.info(sb.toString());
        } else {
            Logger logger2 = log;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(str2);
            logger2.warn(sb2.toString(), (Throwable) exc);
            UncaughtExceptionHandler uncaughtExceptionHandler = Threading.uncaughtExceptionHandler;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), exc);
            }
        }
        close();
    }
}
