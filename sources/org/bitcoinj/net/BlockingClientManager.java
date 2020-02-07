package org.bitcoinj.net;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.SocketFactory;

public class BlockingClientManager extends AbstractIdleService implements ClientConnectionManager {
    private final Set<BlockingClient> clients;
    private int connectTimeoutMillis;
    private final SocketFactory socketFactory;

    /* access modifiers changed from: protected */
    public void startUp() throws Exception {
    }

    public BlockingClientManager() {
        this.clients = Collections.synchronizedSet(new HashSet());
        this.connectTimeoutMillis = 1000;
        this.socketFactory = SocketFactory.getDefault();
    }

    public BlockingClientManager(SocketFactory socketFactory2) {
        this.clients = Collections.synchronizedSet(new HashSet());
        this.connectTimeoutMillis = 1000;
        this.socketFactory = (SocketFactory) Preconditions.checkNotNull(socketFactory2);
    }

    public ListenableFuture<SocketAddress> openConnection(SocketAddress socketAddress, StreamConnection streamConnection) {
        try {
            if (isRunning()) {
                BlockingClient blockingClient = new BlockingClient(socketAddress, streamConnection, this.connectTimeoutMillis, this.socketFactory, this.clients);
                return blockingClient.getConnectFuture();
            }
            throw new IllegalStateException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConnectTimeoutMillis(int i) {
        this.connectTimeoutMillis = i;
    }

    /* access modifiers changed from: protected */
    public void shutDown() throws Exception {
        synchronized (this.clients) {
            for (BlockingClient closeConnection : this.clients) {
                closeConnection.closeConnection();
            }
        }
    }

    public int getConnectedClientCount() {
        return this.clients.size();
    }

    public void closeConnections(int i) {
        if (isRunning()) {
            synchronized (this.clients) {
                Iterator it = this.clients.iterator();
                while (true) {
                    int i2 = i - 1;
                    if (i > 0 && it.hasNext()) {
                        ((BlockingClient) it.next()).closeConnection();
                        i = i2;
                    }
                }
            }
            return;
        }
        throw new IllegalStateException();
    }
}
