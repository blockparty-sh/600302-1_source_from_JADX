package org.bitcoinj.net.discovery;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.utils.ContextPropagatingThreadFactory;
import org.bitcoinj.utils.DaemonThreadFactory;

public class DnsDiscovery extends MultiplexingDiscovery {

    public static class DnsSeedDiscovery implements PeerDiscovery {
        private final String hostname;
        private final NetworkParameters params;

        public void shutdown() {
        }

        public DnsSeedDiscovery(NetworkParameters networkParameters, String str) {
            this.hostname = str;
            this.params = networkParameters;
        }

        public InetSocketAddress[] getPeers(long j, long j2, TimeUnit timeUnit) throws PeerDiscoveryException {
            if (j == 0) {
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(this.hostname);
                    InetSocketAddress[] inetSocketAddressArr = new InetSocketAddress[allByName.length];
                    for (int i = 0; i < allByName.length; i++) {
                        inetSocketAddressArr[i] = new InetSocketAddress(allByName[i], this.params.getPort());
                    }
                    return inetSocketAddressArr;
                } catch (UnknownHostException e) {
                    throw new PeerDiscoveryException((Throwable) e);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("DNS seeds cannot filter by services: ");
                sb.append(j);
                throw new PeerDiscoveryException(sb.toString());
            }
        }

        public String toString() {
            return this.hostname;
        }
    }

    public DnsDiscovery(NetworkParameters networkParameters) {
        this(networkParameters.getDnsSeeds(), networkParameters);
    }

    public DnsDiscovery(String[] strArr, NetworkParameters networkParameters) {
        super(networkParameters, buildDiscoveries(networkParameters, strArr));
    }

    private static List<PeerDiscovery> buildDiscoveries(NetworkParameters networkParameters, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr != null) {
            for (String dnsSeedDiscovery : strArr) {
                arrayList.add(new DnsSeedDiscovery(networkParameters, dnsSeedDiscovery));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public ExecutorService createExecutor() {
        String str = "DNS seed lookups";
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return Executors.newSingleThreadExecutor(new ContextPropagatingThreadFactory(str));
        }
        return Executors.newFixedThreadPool(this.seeds.size(), new DaemonThreadFactory(str));
    }
}
