package org.bitcoinj.net;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.bitcoinj.utils.Threading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ConnectionHandler implements MessageWriteTarget {
    private static final int BUFFER_SIZE_LOWER_BOUND = 4096;
    private static final int BUFFER_SIZE_UPPER_BOUND = 65536;
    private static final int OUTBOUND_BUFFER_BYTE_COUNT = 33554456;
    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);
    @GuardedBy("lock")
    private final LinkedList<ByteBuffer> bytesToWrite;
    @GuardedBy("lock")
    private long bytesToWriteRemaining;
    @GuardedBy("lock")
    private final SocketChannel channel;
    @GuardedBy("lock")
    private boolean closeCalled;
    private Set<ConnectionHandler> connectedHandlers;
    @GuardedBy("lock")
    StreamConnection connection;
    @GuardedBy("lock")
    private final SelectionKey key;
    private final ReentrantLock lock;
    @GuardedBy("lock")
    private final ByteBuffer readBuff;

    public ConnectionHandler(StreamConnectionFactory streamConnectionFactory, SelectionKey selectionKey) throws IOException {
        this(streamConnectionFactory.getNewConnection(((SocketChannel) selectionKey.channel()).socket().getInetAddress(), ((SocketChannel) selectionKey.channel()).socket().getPort()), selectionKey);
        if (this.connection == null) {
            throw new IOException("Parser factory.getNewConnection returned null");
        }
    }

    private ConnectionHandler(@Nullable StreamConnection streamConnection, SelectionKey selectionKey) {
        this.lock = Threading.lock("nioConnectionHandler");
        this.closeCalled = false;
        this.bytesToWriteRemaining = 0;
        this.bytesToWrite = new LinkedList<>();
        this.key = selectionKey;
        this.channel = (SocketChannel) Preconditions.checkNotNull((SocketChannel) selectionKey.channel());
        if (streamConnection == null) {
            this.readBuff = null;
            return;
        }
        this.connection = streamConnection;
        this.readBuff = ByteBuffer.allocateDirect(Math.min(Math.max(streamConnection.getMaxMessageSize(), 4096), 65536));
        streamConnection.setWriteTarget(this);
        this.connectedHandlers = null;
    }

    public ConnectionHandler(StreamConnection streamConnection, SelectionKey selectionKey, Set<ConnectionHandler> set) {
        this((StreamConnection) Preconditions.checkNotNull(streamConnection), selectionKey);
        this.lock.lock();
        try {
            this.connectedHandlers = set;
            if (!this.closeCalled) {
                Preconditions.checkState(this.connectedHandlers.add(this));
            }
        } finally {
            this.lock.unlock();
        }
    }

    @GuardedBy("lock")
    private void setWriteOps() {
        SelectionKey selectionKey = this.key;
        selectionKey.interestOps(selectionKey.interestOps() | 4);
        this.key.selector().wakeup();
    }

    private void tryWriteBytes() throws IOException {
        this.lock.lock();
        try {
            Iterator it = this.bytesToWrite.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ByteBuffer byteBuffer = (ByteBuffer) it.next();
                this.bytesToWriteRemaining -= (long) this.channel.write(byteBuffer);
                if (byteBuffer.hasRemaining()) {
                    setWriteOps();
                    break;
                }
                it.remove();
            }
            if (this.bytesToWrite.isEmpty()) {
                this.key.interestOps(this.key.interestOps() & -5);
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeBytes(byte[] r9) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String r0 = "Error writing message to connection, closing connection"
            java.util.concurrent.locks.ReentrantLock r1 = r8.lock
            r1.lock()
            r1 = 0
            r2 = 1
            long r3 = r8.bytesToWriteRemaining     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            int r5 = r9.length     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            long r5 = (long) r5     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            long r3 = r3 + r5
            r5 = 33554456(0x2000018, double:1.6578104E-316)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0033
            java.util.LinkedList<java.nio.ByteBuffer> r3 = r8.bytesToWrite     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            int r4 = r9.length     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            byte[] r4 = java.util.Arrays.copyOf(r9, r4)     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r4)     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            r3.offer(r4)     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            long r3 = r8.bytesToWriteRemaining     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            int r9 = r9.length     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            long r5 = (long) r9     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            long r3 = r3 + r5
            r8.bytesToWriteRemaining = r3     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            r8.setWriteOps()     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            java.util.concurrent.locks.ReentrantLock r9 = r8.lock
            r9.unlock()
            return
        L_0x0033:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            java.lang.String r3 = "Outbound buffer overflowed"
            r9.<init>(r3)     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
            throw r9     // Catch:{ IOException -> 0x0051, CancelledKeyException -> 0x003d }
        L_0x003b:
            r9 = move-exception
            goto L_0x0062
        L_0x003d:
            r9 = move-exception
            java.util.concurrent.locks.ReentrantLock r3 = r8.lock     // Catch:{ all -> 0x003b }
            r3.unlock()     // Catch:{ all -> 0x003b }
            org.slf4j.Logger r2 = log     // Catch:{ all -> 0x0060 }
            r2.warn(r0, r9)     // Catch:{ all -> 0x0060 }
            r8.closeConnection()     // Catch:{ all -> 0x0060 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0060 }
            r0.<init>(r9)     // Catch:{ all -> 0x0060 }
            throw r0     // Catch:{ all -> 0x0060 }
        L_0x0051:
            r9 = move-exception
            java.util.concurrent.locks.ReentrantLock r3 = r8.lock     // Catch:{ all -> 0x003b }
            r3.unlock()     // Catch:{ all -> 0x003b }
            org.slf4j.Logger r2 = log     // Catch:{ all -> 0x0060 }
            r2.warn(r0, r9)     // Catch:{ all -> 0x0060 }
            r8.closeConnection()     // Catch:{ all -> 0x0060 }
            throw r9     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r9 = move-exception
            r2 = 0
        L_0x0062:
            if (r2 == 0) goto L_0x0069
            java.util.concurrent.locks.ReentrantLock r0 = r8.lock
            r0.unlock()
        L_0x0069:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.net.ConnectionHandler.writeBytes(byte[]):void");
    }

    public void closeConnection() {
        Preconditions.checkState(!this.lock.isHeldByCurrentThread());
        try {
            this.channel.close();
            connectionClosed();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void connectionClosed() {
        this.lock.lock();
        try {
            boolean z = false;
            boolean z2 = !this.closeCalled;
            this.closeCalled = true;
            if (z2) {
                Set<ConnectionHandler> set = this.connectedHandlers;
                if (set == null || set.remove(this)) {
                    z = true;
                }
                Preconditions.checkState(z);
                this.connection.connectionClosed();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public static void handleKey(SelectionKey selectionKey) {
        ConnectionHandler connectionHandler = (ConnectionHandler) selectionKey.attachment();
        if (connectionHandler != null) {
            try {
                if (!selectionKey.isValid()) {
                    connectionHandler.closeConnection();
                    return;
                }
                if (selectionKey.isReadable()) {
                    int read = connectionHandler.channel.read(connectionHandler.readBuff);
                    if (read != 0) {
                        if (read == -1) {
                            selectionKey.cancel();
                            connectionHandler.closeConnection();
                            return;
                        }
                        connectionHandler.readBuff.flip();
                        Preconditions.checkState(connectionHandler.readBuff.position() == ((StreamConnection) Preconditions.checkNotNull(connectionHandler.connection)).receiveBytes(connectionHandler.readBuff));
                        connectionHandler.readBuff.compact();
                    } else {
                        return;
                    }
                }
                if (selectionKey.isWritable()) {
                    connectionHandler.tryWriteBytes();
                }
            } catch (Exception e) {
                Throwable rootCause = Throwables.getRootCause(e);
                Logger logger = log;
                Object[] objArr = new Object[3];
                objArr[0] = rootCause.getClass().getName();
                objArr[1] = rootCause.getMessage() != null ? rootCause.getMessage() : "";
                objArr[2] = e;
                logger.warn("Error handling SelectionKey: {} {}", objArr);
                connectionHandler.closeConnection();
            }
        }
    }
}
