package org.bitcoinj.net;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import org.bitcoinj.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockingClient implements MessageWriteTarget {
    private static final int BUFFER_SIZE_LOWER_BOUND = 4096;
    private static final int BUFFER_SIZE_UPPER_BOUND = 65536;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(BlockingClient.class);
    /* access modifiers changed from: private */
    public SettableFuture<SocketAddress> connectFuture = SettableFuture.create();
    /* access modifiers changed from: private */
    public Socket socket;
    /* access modifiers changed from: private */
    public volatile boolean vCloseRequested = false;

    public BlockingClient(SocketAddress socketAddress, StreamConnection streamConnection, int i, SocketFactory socketFactory, @Nullable Set<BlockingClient> set) throws IOException {
        streamConnection.setWriteTarget(this);
        this.socket = socketFactory.createSocket();
        final Context context = Context.get();
        final Set<BlockingClient> set2 = set;
        final SocketAddress socketAddress2 = socketAddress;
        final int i2 = i;
        final StreamConnection streamConnection2 = streamConnection;
        C34531 r0 = new Thread() {
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x0076, code lost:
                if (r0 == null) goto L_0x007d;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:21:0x0078, code lost:
                r0.remove(r5.this$0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x007d, code lost:
                r6.connectionClosed();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:23:0x0082, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x0045, code lost:
                if (r0 != null) goto L_0x0078;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    org.bitcoinj.core.Context r0 = r2
                    org.bitcoinj.core.Context.propagate(r0)
                    java.util.Set r0 = r3
                    if (r0 == 0) goto L_0x000e
                    org.bitcoinj.net.BlockingClient r1 = org.bitcoinj.net.BlockingClient.this
                    r0.add(r1)
                L_0x000e:
                    org.bitcoinj.net.BlockingClient r0 = org.bitcoinj.net.BlockingClient.this     // Catch:{ Exception -> 0x004a }
                    java.net.Socket r0 = r0.socket     // Catch:{ Exception -> 0x004a }
                    java.net.SocketAddress r1 = r4     // Catch:{ Exception -> 0x004a }
                    int r2 = r5     // Catch:{ Exception -> 0x004a }
                    r0.connect(r1, r2)     // Catch:{ Exception -> 0x004a }
                    org.bitcoinj.net.StreamConnection r0 = r6     // Catch:{ Exception -> 0x004a }
                    r0.connectionOpened()     // Catch:{ Exception -> 0x004a }
                    org.bitcoinj.net.BlockingClient r0 = org.bitcoinj.net.BlockingClient.this     // Catch:{ Exception -> 0x004a }
                    com.google.common.util.concurrent.SettableFuture r0 = r0.connectFuture     // Catch:{ Exception -> 0x004a }
                    java.net.SocketAddress r1 = r4     // Catch:{ Exception -> 0x004a }
                    r0.set(r1)     // Catch:{ Exception -> 0x004a }
                    org.bitcoinj.net.BlockingClient r0 = org.bitcoinj.net.BlockingClient.this     // Catch:{ Exception -> 0x004a }
                    java.net.Socket r0 = r0.socket     // Catch:{ Exception -> 0x004a }
                    java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Exception -> 0x004a }
                    org.bitcoinj.net.StreamConnection r1 = r6     // Catch:{ Exception -> 0x004a }
                    org.bitcoinj.net.BlockingClient.runReadLoop(r0, r1)     // Catch:{ Exception -> 0x004a }
                    org.bitcoinj.net.BlockingClient r0 = org.bitcoinj.net.BlockingClient.this     // Catch:{ IOException -> 0x0043 }
                    java.net.Socket r0 = r0.socket     // Catch:{ IOException -> 0x0043 }
                    r0.close()     // Catch:{ IOException -> 0x0043 }
                L_0x0043:
                    java.util.Set r0 = r3
                    if (r0 == 0) goto L_0x007d
                    goto L_0x0078
                L_0x0048:
                    r0 = move-exception
                    goto L_0x0083
                L_0x004a:
                    r0 = move-exception
                    org.bitcoinj.net.BlockingClient r1 = org.bitcoinj.net.BlockingClient.this     // Catch:{ all -> 0x0048 }
                    boolean r1 = r1.vCloseRequested     // Catch:{ all -> 0x0048 }
                    if (r1 != 0) goto L_0x006b
                    org.slf4j.Logger r1 = org.bitcoinj.net.BlockingClient.log     // Catch:{ all -> 0x0048 }
                    java.lang.String r2 = "Error trying to open/read from connection: {}: {}"
                    java.net.SocketAddress r3 = r4     // Catch:{ all -> 0x0048 }
                    java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x0048 }
                    r1.error(r2, r3, r4)     // Catch:{ all -> 0x0048 }
                    org.bitcoinj.net.BlockingClient r1 = org.bitcoinj.net.BlockingClient.this     // Catch:{ all -> 0x0048 }
                    com.google.common.util.concurrent.SettableFuture r1 = r1.connectFuture     // Catch:{ all -> 0x0048 }
                    r1.setException(r0)     // Catch:{ all -> 0x0048 }
                L_0x006b:
                    org.bitcoinj.net.BlockingClient r0 = org.bitcoinj.net.BlockingClient.this     // Catch:{ IOException -> 0x0074 }
                    java.net.Socket r0 = r0.socket     // Catch:{ IOException -> 0x0074 }
                    r0.close()     // Catch:{ IOException -> 0x0074 }
                L_0x0074:
                    java.util.Set r0 = r3
                    if (r0 == 0) goto L_0x007d
                L_0x0078:
                    org.bitcoinj.net.BlockingClient r1 = org.bitcoinj.net.BlockingClient.this
                    r0.remove(r1)
                L_0x007d:
                    org.bitcoinj.net.StreamConnection r0 = r6
                    r0.connectionClosed()
                    return
                L_0x0083:
                    org.bitcoinj.net.BlockingClient r1 = org.bitcoinj.net.BlockingClient.this     // Catch:{ IOException -> 0x008c }
                    java.net.Socket r1 = r1.socket     // Catch:{ IOException -> 0x008c }
                    r1.close()     // Catch:{ IOException -> 0x008c }
                L_0x008c:
                    java.util.Set r1 = r3
                    if (r1 == 0) goto L_0x0095
                    org.bitcoinj.net.BlockingClient r2 = org.bitcoinj.net.BlockingClient.this
                    r1.remove(r2)
                L_0x0095:
                    org.bitcoinj.net.StreamConnection r1 = r6
                    r1.connectionClosed()
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.net.BlockingClient.C34531.run():void");
            }
        };
        StringBuilder sb = new StringBuilder();
        sb.append("BlockingClient network thread for ");
        sb.append(socketAddress);
        r0.setName(sb.toString());
        r0.setDaemon(true);
        r0.start();
    }

    public static void runReadLoop(InputStream inputStream, StreamConnection streamConnection) throws Exception {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(Math.min(Math.max(streamConnection.getMaxMessageSize(), 4096), 65536));
        byte[] bArr = new byte[allocateDirect.capacity()];
        while (true) {
            boolean z = true;
            Preconditions.checkState(allocateDirect.remaining() > 0 && allocateDirect.remaining() <= bArr.length);
            int read = inputStream.read(bArr, 0, Math.max(1, Math.min(allocateDirect.remaining(), inputStream.available())));
            if (read != -1) {
                allocateDirect.put(bArr, 0, read);
                allocateDirect.flip();
                if (allocateDirect.position() != streamConnection.receiveBytes(allocateDirect)) {
                    z = false;
                }
                Preconditions.checkState(z);
                allocateDirect.compact();
            } else {
                return;
            }
        }
    }

    public void closeConnection() {
        try {
            this.vCloseRequested = true;
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void writeBytes(byte[] bArr) throws IOException {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            outputStream.write(bArr);
            outputStream.flush();
        } catch (IOException e) {
            log.error("Error writing message to connection, closing connection", (Throwable) e);
            closeConnection();
            throw e;
        }
    }

    public ListenableFuture<SocketAddress> getConnectFuture() {
        return this.connectFuture;
    }
}
