package org.bitcoinj.net;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import org.bitcoinj.utils.ContextPropagatingThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioClientManager extends AbstractExecutionThreadService implements ClientConnectionManager {
    private static final Logger log = LoggerFactory.getLogger(NioClientManager.class);
    private final Set<ConnectionHandler> connectedHandlers = Collections.synchronizedSet(new HashSet());
    final Queue<PendingConnect> newConnectionChannels = new LinkedBlockingQueue();
    private final Selector selector;

    class PendingConnect {
        SocketAddress address;
        StreamConnection connection;
        SettableFuture<SocketAddress> future = SettableFuture.create();

        /* renamed from: sc */
        SocketChannel f804sc;

        PendingConnect(SocketChannel socketChannel, StreamConnection streamConnection, SocketAddress socketAddress) {
            this.f804sc = socketChannel;
            this.connection = streamConnection;
            this.address = socketAddress;
        }
    }

    private void handleKey(SelectionKey selectionKey) throws IOException {
        if (!selectionKey.isValid() || !selectionKey.isConnectable()) {
            ConnectionHandler.handleKey(selectionKey);
            return;
        }
        PendingConnect pendingConnect = (PendingConnect) selectionKey.attachment();
        StreamConnection streamConnection = pendingConnect.connection;
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ConnectionHandler connectionHandler = new ConnectionHandler(streamConnection, selectionKey, this.connectedHandlers);
        try {
            if (socketChannel.finishConnect()) {
                log.info("Connected to {}", (Object) socketChannel.socket().getRemoteSocketAddress());
                selectionKey.interestOps((selectionKey.interestOps() | 1) & -9).attach(connectionHandler);
                streamConnection.connectionOpened();
                pendingConnect.future.set(pendingConnect.address);
                return;
            }
            log.warn("Failed to connect to {}", (Object) socketChannel.socket().getRemoteSocketAddress());
            connectionHandler.closeConnection();
            pendingConnect.future.setException(new ConnectException("Unknown reason"));
            pendingConnect.future = null;
        } catch (Exception e) {
            Throwable rootCause = Throwables.getRootCause(e);
            log.warn("Failed to connect with exception: {}: {}", rootCause.getClass().getName(), rootCause.getMessage(), e);
            connectionHandler.closeConnection();
            pendingConnect.future.setException(rootCause);
            pendingConnect.future = null;
        }
    }

    public NioClientManager() {
        try {
            this.selector = SelectorProvider.provider().openSelector();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|67|64) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            java.lang.String r0 = "Error closing channel"
            java.lang.String r1 = "Error closing client manager selector"
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x0096 }
            r3 = 1
            r2.setPriority(r3)     // Catch:{ Exception -> 0x0096 }
        L_0x000c:
            boolean r2 = r7.isRunning()     // Catch:{ Exception -> 0x0096 }
            if (r2 == 0) goto L_0x0054
        L_0x0012:
            java.util.Queue<org.bitcoinj.net.NioClientManager$PendingConnect> r2 = r7.newConnectionChannels     // Catch:{ Exception -> 0x0096 }
            java.lang.Object r2 = r2.poll()     // Catch:{ Exception -> 0x0096 }
            org.bitcoinj.net.NioClientManager$PendingConnect r2 = (org.bitcoinj.net.NioClientManager.PendingConnect) r2     // Catch:{ Exception -> 0x0096 }
            if (r2 == 0) goto L_0x0032
            java.nio.channels.SocketChannel r3 = r2.f804sc     // Catch:{ ClosedChannelException -> 0x002a }
            java.nio.channels.Selector r4 = r7.selector     // Catch:{ ClosedChannelException -> 0x002a }
            r5 = 8
            java.nio.channels.SelectionKey r3 = r3.register(r4, r5)     // Catch:{ ClosedChannelException -> 0x002a }
            r3.attach(r2)     // Catch:{ ClosedChannelException -> 0x002a }
            goto L_0x0012
        L_0x002a:
            org.slf4j.Logger r2 = log     // Catch:{ Exception -> 0x0096 }
            java.lang.String r3 = "SocketChannel was closed before it could be registered"
            r2.warn(r3)     // Catch:{ Exception -> 0x0096 }
            goto L_0x0012
        L_0x0032:
            java.nio.channels.Selector r2 = r7.selector     // Catch:{ Exception -> 0x0096 }
            r2.select()     // Catch:{ Exception -> 0x0096 }
            java.nio.channels.Selector r2 = r7.selector     // Catch:{ Exception -> 0x0096 }
            java.util.Set r2 = r2.selectedKeys()     // Catch:{ Exception -> 0x0096 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x0096 }
        L_0x0041:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x0096 }
            if (r3 == 0) goto L_0x000c
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x0096 }
            java.nio.channels.SelectionKey r3 = (java.nio.channels.SelectionKey) r3     // Catch:{ Exception -> 0x0096 }
            r2.remove()     // Catch:{ Exception -> 0x0096 }
            r7.handleKey(r3)     // Catch:{ Exception -> 0x0096 }
            goto L_0x0041
        L_0x0054:
            java.nio.channels.Selector r2 = r7.selector
            java.util.Set r2 = r2.keys()
            java.util.Iterator r2 = r2.iterator()
        L_0x005e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0087
            java.lang.Object r3 = r2.next()
            java.nio.channels.SelectionKey r3 = (java.nio.channels.SelectionKey) r3
            java.nio.channels.SelectableChannel r4 = r3.channel()     // Catch:{ IOException -> 0x0072 }
            r4.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x0078
        L_0x0072:
            r4 = move-exception
            org.slf4j.Logger r5 = log
            r5.warn(r0, r4)
        L_0x0078:
            r3.cancel()
            java.lang.Object r4 = r3.attachment()
            boolean r4 = r4 instanceof org.bitcoinj.net.ConnectionHandler
            if (r4 == 0) goto L_0x005e
            org.bitcoinj.net.ConnectionHandler.handleKey(r3)
            goto L_0x005e
        L_0x0087:
            java.nio.channels.Selector r0 = r7.selector     // Catch:{ IOException -> 0x008d }
            r0.close()     // Catch:{ IOException -> 0x008d }
            goto L_0x00d6
        L_0x008d:
            r0 = move-exception
            org.slf4j.Logger r2 = log
            r2.warn(r1, r0)
            goto L_0x00d6
        L_0x0094:
            r2 = move-exception
            goto L_0x00d7
        L_0x0096:
            r2 = move-exception
            org.slf4j.Logger r3 = log     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = "Error trying to open/read from connection: "
            r3.warn(r4, r2)     // Catch:{ all -> 0x0094 }
            java.nio.channels.Selector r2 = r7.selector
            java.util.Set r2 = r2.keys()
            java.util.Iterator r2 = r2.iterator()
        L_0x00a8:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00d1
            java.lang.Object r3 = r2.next()
            java.nio.channels.SelectionKey r3 = (java.nio.channels.SelectionKey) r3
            java.nio.channels.SelectableChannel r4 = r3.channel()     // Catch:{ IOException -> 0x00bc }
            r4.close()     // Catch:{ IOException -> 0x00bc }
            goto L_0x00c2
        L_0x00bc:
            r4 = move-exception
            org.slf4j.Logger r5 = log
            r5.warn(r0, r4)
        L_0x00c2:
            r3.cancel()
            java.lang.Object r4 = r3.attachment()
            boolean r4 = r4 instanceof org.bitcoinj.net.ConnectionHandler
            if (r4 == 0) goto L_0x00a8
            org.bitcoinj.net.ConnectionHandler.handleKey(r3)
            goto L_0x00a8
        L_0x00d1:
            java.nio.channels.Selector r0 = r7.selector     // Catch:{ IOException -> 0x008d }
            r0.close()     // Catch:{ IOException -> 0x008d }
        L_0x00d6:
            return
        L_0x00d7:
            java.nio.channels.Selector r3 = r7.selector
            java.util.Set r3 = r3.keys()
            java.util.Iterator r3 = r3.iterator()
        L_0x00e1:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x010a
            java.lang.Object r4 = r3.next()
            java.nio.channels.SelectionKey r4 = (java.nio.channels.SelectionKey) r4
            java.nio.channels.SelectableChannel r5 = r4.channel()     // Catch:{ IOException -> 0x00f5 }
            r5.close()     // Catch:{ IOException -> 0x00f5 }
            goto L_0x00fb
        L_0x00f5:
            r5 = move-exception
            org.slf4j.Logger r6 = log
            r6.warn(r0, r5)
        L_0x00fb:
            r4.cancel()
            java.lang.Object r5 = r4.attachment()
            boolean r5 = r5 instanceof org.bitcoinj.net.ConnectionHandler
            if (r5 == 0) goto L_0x00e1
            org.bitcoinj.net.ConnectionHandler.handleKey(r4)
            goto L_0x00e1
        L_0x010a:
            java.nio.channels.Selector r0 = r7.selector     // Catch:{ IOException -> 0x0110 }
            r0.close()     // Catch:{ IOException -> 0x0110 }
            goto L_0x0116
        L_0x0110:
            r0 = move-exception
            org.slf4j.Logger r3 = log
            r3.warn(r1, r0)
        L_0x0116:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.net.NioClientManager.run():void");
    }

    public ListenableFuture<SocketAddress> openConnection(SocketAddress socketAddress, StreamConnection streamConnection) {
        if (isRunning()) {
            try {
                SocketChannel open = SocketChannel.open();
                open.configureBlocking(false);
                open.connect(socketAddress);
                PendingConnect pendingConnect = new PendingConnect(open, streamConnection, socketAddress);
                this.newConnectionChannels.offer(pendingConnect);
                this.selector.wakeup();
                return pendingConnect.future;
            } catch (Throwable th) {
                return Futures.immediateFailedFuture(th);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public void triggerShutdown() {
        this.selector.wakeup();
    }

    public int getConnectedClientCount() {
        return this.connectedHandlers.size();
    }

    public void closeConnections(int i) {
        ConnectionHandler connectionHandler;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                synchronized (this.connectedHandlers) {
                    connectionHandler = (ConnectionHandler) this.connectedHandlers.iterator().next();
                }
                if (connectionHandler != null) {
                    connectionHandler.closeConnection();
                }
                i = i2;
            } else {
                return;
            }
        }
        while (true) {
        }
    }

    /* access modifiers changed from: protected */
    public Executor executor() {
        return new Executor() {
            public void execute(Runnable runnable) {
                new ContextPropagatingThreadFactory("NioClientManager").newThread(runnable).start();
            }
        };
    }
}
