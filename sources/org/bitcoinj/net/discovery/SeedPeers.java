package org.bitcoinj.net.discovery;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.bitcoinj.core.NetworkParameters;

public class SeedPeers implements PeerDiscovery {
    private NetworkParameters params;
    private int pnseedIndex;
    private int[] seedAddrs;

    public void shutdown() {
    }

    public SeedPeers(NetworkParameters networkParameters) {
        this(networkParameters.getAddrSeeds(), networkParameters);
    }

    public SeedPeers(int[] iArr, NetworkParameters networkParameters) {
        this.seedAddrs = iArr;
        this.params = networkParameters;
    }

    @Nullable
    public InetSocketAddress getPeer() throws PeerDiscoveryException {
        try {
            return nextPeer();
        } catch (UnknownHostException e) {
            throw new PeerDiscoveryException((Throwable) e);
        }
    }

    @Nullable
    private InetSocketAddress nextPeer() throws UnknownHostException, PeerDiscoveryException {
        int[] iArr = this.seedAddrs;
        if (iArr == null || iArr.length == 0) {
            throw new PeerDiscoveryException("No IP address seeds configured; unable to find any peers");
        }
        int i = this.pnseedIndex;
        if (i >= iArr.length) {
            return null;
        }
        this.pnseedIndex = i + 1;
        return new InetSocketAddress(convertAddress(iArr[i]), this.params.getPort());
    }

    public InetSocketAddress[] getPeers(long j, long j2, TimeUnit timeUnit) throws PeerDiscoveryException {
        if (j == 0) {
            try {
                return allPeers();
            } catch (UnknownHostException e) {
                throw new PeerDiscoveryException((Throwable) e);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Pre-determined peers cannot be filtered by services: ");
            sb.append(j);
            throw new PeerDiscoveryException(sb.toString());
        }
    }

    private InetSocketAddress[] allPeers() throws UnknownHostException {
        InetSocketAddress[] inetSocketAddressArr = new InetSocketAddress[this.seedAddrs.length];
        int i = 0;
        while (true) {
            int[] iArr = this.seedAddrs;
            if (i >= iArr.length) {
                return inetSocketAddressArr;
            }
            inetSocketAddressArr[i] = new InetSocketAddress(convertAddress(iArr[i]), this.params.getPort());
            i++;
        }
    }

    private InetAddress convertAddress(int i) throws UnknownHostException {
        return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
    }
}
