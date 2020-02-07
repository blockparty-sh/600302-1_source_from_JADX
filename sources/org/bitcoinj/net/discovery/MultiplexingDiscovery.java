package org.bitcoinj.net.discovery;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.squareup.okhttp.OkHttpClient;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.net.discovery.DnsDiscovery.DnsSeedDiscovery;
import org.bitcoinj.net.discovery.HttpDiscovery.Details;
import org.bitcoinj.utils.ContextPropagatingThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiplexingDiscovery implements PeerDiscovery {
    private static final Logger log = LoggerFactory.getLogger(MultiplexingDiscovery.class);
    protected final NetworkParameters netParams;
    protected final List<PeerDiscovery> seeds;
    private volatile ExecutorService vThreadPool;

    public static MultiplexingDiscovery forServices(NetworkParameters networkParameters, long j) {
        ArrayList newArrayList = Lists.newArrayList();
        Details[] httpSeeds = networkParameters.getHttpSeeds();
        if (httpSeeds != null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            for (Details httpDiscovery : httpSeeds) {
                newArrayList.add(new HttpDiscovery(networkParameters, httpDiscovery, okHttpClient));
            }
        }
        if (j == 0) {
            String[] dnsSeeds = networkParameters.getDnsSeeds();
            if (dnsSeeds != null) {
                for (String dnsSeedDiscovery : dnsSeeds) {
                    newArrayList.add(new DnsSeedDiscovery(networkParameters, dnsSeedDiscovery));
                }
            }
        }
        return new MultiplexingDiscovery(networkParameters, newArrayList);
    }

    public MultiplexingDiscovery(NetworkParameters networkParameters, List<PeerDiscovery> list) {
        Preconditions.checkArgument(!list.isEmpty());
        this.netParams = networkParameters;
        this.seeds = list;
    }

    public InetSocketAddress[] getPeers(long j, long j2, TimeUnit timeUnit) throws PeerDiscoveryException {
        long j3 = j2;
        TimeUnit timeUnit2 = timeUnit;
        this.vThreadPool = createExecutor();
        try {
            ArrayList newArrayList = Lists.newArrayList();
            for (final PeerDiscovery peerDiscovery : this.seeds) {
                final long j4 = j;
                final long j5 = j2;
                final TimeUnit timeUnit3 = timeUnit;
                C34561 r1 = new Callable<InetSocketAddress[]>() {
                    public InetSocketAddress[] call() throws Exception {
                        return peerDiscovery.getPeers(j4, j5, timeUnit3);
                    }
                };
                newArrayList.add(r1);
            }
            List invokeAll = this.vThreadPool.invokeAll(newArrayList, j3, timeUnit2);
            ArrayList newArrayList2 = Lists.newArrayList();
            for (int i = 0; i < invokeAll.size(); i++) {
                Future future = (Future) invokeAll.get(i);
                if (future.isCancelled()) {
                    log.warn("Seed {}: timed out", this.seeds.get(i));
                } else {
                    try {
                        Collections.addAll(newArrayList2, (InetSocketAddress[]) future.get());
                    } catch (ExecutionException e) {
                        log.warn("Seed {}: failed to look up: {}", this.seeds.get(i), (Object) e.getMessage());
                    }
                }
            }
            if (newArrayList2.size() != 0) {
                Collections.shuffle(newArrayList2);
                this.vThreadPool.shutdownNow();
                InetSocketAddress[] inetSocketAddressArr = (InetSocketAddress[]) newArrayList2.toArray(new InetSocketAddress[newArrayList2.size()]);
                this.vThreadPool.shutdown();
                return inetSocketAddressArr;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No peer discovery returned any results in ");
            sb.append(timeUnit2.toMillis(j3));
            sb.append("ms. Check internet connection?");
            throw new PeerDiscoveryException(sb.toString());
        } catch (InterruptedException e2) {
            throw new PeerDiscoveryException((Throwable) e2);
        } catch (Throwable th) {
            this.vThreadPool.shutdown();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public ExecutorService createExecutor() {
        return Executors.newFixedThreadPool(this.seeds.size(), new ContextPropagatingThreadFactory("Multiplexing discovery"));
    }

    public void shutdown() {
        ExecutorService executorService = this.vThreadPool;
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
