package org.bitcoinj.net.discovery;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public interface PeerDiscovery {
    InetSocketAddress[] getPeers(long j, long j2, TimeUnit timeUnit) throws PeerDiscoveryException;

    void shutdown();
}
