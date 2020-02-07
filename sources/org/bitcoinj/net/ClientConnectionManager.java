package org.bitcoinj.net;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Service;
import java.net.SocketAddress;

public interface ClientConnectionManager extends Service {
    void closeConnections(int i);

    int getConnectedClientCount();

    ListenableFuture<SocketAddress> openConnection(SocketAddress socketAddress, StreamConnection streamConnection);
}
