package org.bitcoinj.net;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.utils.Threading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtobufConnection<MessageType extends MessageLite> extends AbstractTimeoutHandler implements StreamConnection {
    private static final Logger log = LoggerFactory.getLogger(ProtobufConnection.class);
    private final Listener<MessageType> handler;
    private final ReentrantLock lock = Threading.lock("ProtobufConnection");
    final int maxMessageSize;
    @GuardedBy("lock")
    private byte[] messageBytes;
    @GuardedBy("lock")
    private int messageBytesOffset = 0;
    private final MessageLite prototype;
    @VisibleForTesting
    final AtomicReference<MessageWriteTarget> writeTarget = new AtomicReference<>();

    public interface Listener<MessageType extends MessageLite> {
        void connectionClosed(ProtobufConnection<MessageType> protobufConnection);

        void connectionOpen(ProtobufConnection<MessageType> protobufConnection);

        void messageReceived(ProtobufConnection<MessageType> protobufConnection, MessageType messagetype);
    }

    public ProtobufConnection(Listener<MessageType> listener, MessageType messagetype, int i, int i2) {
        this.handler = listener;
        this.prototype = messagetype;
        this.maxMessageSize = Math.min(i, 2147483643);
        setTimeoutEnabled(false);
        setSocketTimeout(i2);
    }

    public void setWriteTarget(MessageWriteTarget messageWriteTarget) {
        Preconditions.checkState(this.writeTarget.getAndSet(Preconditions.checkNotNull(messageWriteTarget)) == null);
    }

    public int getMaxMessageSize() {
        return this.maxMessageSize;
    }

    public void closeConnection() {
        ((MessageWriteTarget) this.writeTarget.get()).closeConnection();
    }

    /* access modifiers changed from: protected */
    public void timeoutOccurred() {
        Logger logger = log;
        StringBuilder sb = new StringBuilder();
        sb.append("Timeout occurred for ");
        sb.append(this.handler);
        logger.warn(sb.toString());
        closeConnection();
    }

    private void deserializeMessage(ByteBuffer byteBuffer) throws Exception {
        MessageLite build = this.prototype.newBuilderForType().mergeFrom(ByteString.copyFrom(byteBuffer)).build();
        resetTimeout();
        this.handler.messageReceived(this, build);
    }

    public int receiveBytes(ByteBuffer byteBuffer) throws Exception {
        int i;
        this.lock.lock();
        try {
            if (this.messageBytes != null) {
                i = Math.min(this.messageBytes.length - this.messageBytesOffset, byteBuffer.remaining());
                byteBuffer.get(this.messageBytes, this.messageBytesOffset, i);
                this.messageBytesOffset += i;
                if (this.messageBytesOffset == this.messageBytes.length) {
                    deserializeMessage(ByteBuffer.wrap(this.messageBytes));
                    this.messageBytes = null;
                    if (byteBuffer.hasRemaining()) {
                        i += receiveBytes(byteBuffer);
                    }
                }
            } else {
                boolean z = false;
                if (byteBuffer.remaining() >= 4) {
                    byteBuffer.order(ByteOrder.BIG_ENDIAN);
                    int i2 = byteBuffer.getInt();
                    if (i2 <= this.maxMessageSize) {
                        int i3 = i2 + 4;
                        if (i3 >= 4) {
                            if (byteBuffer.capacity() < i3) {
                                this.messageBytes = new byte[i2];
                                int remaining = byteBuffer.remaining();
                                byteBuffer.get(this.messageBytes, 0, remaining);
                                this.messageBytesOffset = remaining;
                                i = remaining + 4;
                            } else if (byteBuffer.remaining() < i2) {
                                byteBuffer.position(byteBuffer.position() - 4);
                            } else {
                                int limit = byteBuffer.limit();
                                byteBuffer.limit(byteBuffer.position() + i2);
                                deserializeMessage(byteBuffer);
                                if (byteBuffer.remaining() == 0) {
                                    z = true;
                                }
                                Preconditions.checkState(z);
                                byteBuffer.limit(limit);
                                if (byteBuffer.hasRemaining()) {
                                    i3 += receiveBytes(byteBuffer);
                                }
                                this.lock.unlock();
                                return i3;
                            }
                        }
                    }
                    throw new IllegalStateException("Message too large or length underflowed");
                }
                this.lock.unlock();
                return 0;
            }
            return i;
        } finally {
            this.lock.unlock();
        }
    }

    public void connectionClosed() {
        this.handler.connectionClosed(this);
    }

    public void connectionOpened() {
        setTimeoutEnabled(true);
        this.handler.connectionOpen(this);
    }

    public void write(MessageType messagetype) throws IllegalStateException {
        byte[] byteArray = messagetype.toByteArray();
        Preconditions.checkState(byteArray.length <= this.maxMessageSize);
        byte[] bArr = new byte[4];
        C3447Utils.uint32ToByteArrayBE((long) byteArray.length, bArr, 0);
        try {
            MessageWriteTarget messageWriteTarget = (MessageWriteTarget) this.writeTarget.get();
            messageWriteTarget.writeBytes(bArr);
            messageWriteTarget.writeBytes(byteArray);
        } catch (IOException unused) {
            closeConnection();
        }
    }
}
