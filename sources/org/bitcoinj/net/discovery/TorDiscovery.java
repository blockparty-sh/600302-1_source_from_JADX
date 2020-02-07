package org.bitcoinj.net.discovery;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorClient;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.utils.ContextPropagatingThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TorDiscovery implements PeerDiscovery {
    public static final int MINIMUM_ROUTER_COUNT = 4;
    public static final int MINIMUM_ROUTER_LOOKUP_COUNT = 6;
    public static final int RECEIVE_RETRIES = 3;
    public static final int RESOLVE_CNAME = 0;
    public static final int RESOLVE_ERROR = 240;
    public static final int RESOLVE_IPV4 = 4;
    public static final int RESOLVE_IPV6 = 6;
    public static final int RESOLVE_STREAM_ID = 4096;
    public static final int ROUTER_LOOKUP_COUNT = 10;
    private static final Logger log = LoggerFactory.getLogger(TorDiscovery.class);
    private final String[] hostNames;
    private final NetworkParameters netParams;
    private final CircuitPathChooser pathChooser;
    private ListeningExecutorService threadPool;
    /* access modifiers changed from: private */
    public final TorClient torClient;

    private static class Lookup {
        final InetAddress address;
        final Router router;

        Lookup(Router router2, InetAddress inetAddress) {
            this.router = router2;
            this.address = inetAddress;
        }
    }

    public TorDiscovery(NetworkParameters networkParameters, TorClient torClient2) {
        this(networkParameters.getDnsSeeds(), networkParameters, torClient2);
    }

    public TorDiscovery(String[] strArr, NetworkParameters networkParameters, TorClient torClient2) {
        this.hostNames = strArr;
        this.netParams = networkParameters;
        this.torClient = torClient2;
        this.pathChooser = CircuitPathChooser.create(torClient2.getConfig(), torClient2.getDirectory());
    }

    public InetSocketAddress[] getPeers(long j, long j2, TimeUnit timeUnit) throws PeerDiscoveryException {
        if (this.hostNames == null) {
            throw new PeerDiscoveryException("Unable to find any peers via DNS");
        } else if (j == 0) {
            HashSet newHashSet = Sets.newHashSet();
            ArrayList newArrayList = Lists.newArrayList();
            while (newHashSet.size() < 10) {
                newHashSet.add(this.pathChooser.chooseExitNodeForTargets(newArrayList));
            }
            try {
                List circuits = getCircuits(this.torClient.getConfig().getCircuitBuildTimeout(), TimeUnit.MILLISECONDS, newHashSet);
                if (!circuits.isEmpty()) {
                    Collection lookupAddresses = lookupAddresses(j2, timeUnit, circuits);
                    if (lookupAddresses.size() >= 4) {
                        ArrayList newArrayList2 = Lists.newArrayList();
                        newArrayList2.addAll(lookupAddresses);
                        Collections.shuffle(newArrayList2);
                        return (InetSocketAddress[]) newArrayList2.toArray(new InetSocketAddress[newArrayList2.size()]);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to find enough peers via Tor - got ");
                    sb.append(lookupAddresses.size());
                    throw new PeerDiscoveryException(sb.toString());
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Failed to open any circuit within ");
                sb2.append(String.valueOf(j2));
                sb2.append(" ");
                sb2.append(timeUnit);
                throw new PeerDiscoveryException(sb2.toString());
            } catch (InterruptedException e) {
                throw new PeerDiscoveryException((Throwable) e);
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("DNS seeds cannot filter by services: ");
            sb3.append(j);
            throw new PeerDiscoveryException(sb3.toString());
        }
    }

    private List<Circuit> getCircuits(long j, TimeUnit timeUnit, Set<Router> set) throws InterruptedException {
        Preconditions.checkArgument(set.size() >= 6, "Set of {} routers is smaller than required minimum {}", Integer.valueOf(set.size()), Integer.valueOf(6));
        createThreadPool(set.size());
        try {
            ArrayList newArrayList = Lists.newArrayList();
            final CountDownLatch countDownLatch = new CountDownLatch(6);
            for (final Router router : set) {
                ListenableFuture submit = this.threadPool.submit((Callable<T>) new Callable<Circuit>() {
                    public Circuit call() throws Exception {
                        return TorDiscovery.this.torClient.getCircuitManager().openInternalCircuitTo(Lists.newArrayList((E[]) new Router[]{router}));
                    }
                });
                Futures.addCallback(submit, new FutureCallback<Circuit>() {
                    public void onSuccess(Circuit circuit) {
                        countDownLatch.countDown();
                    }

                    public void onFailure(Throwable th) {
                        countDownLatch.countDown();
                    }
                });
                newArrayList.add(submit);
            }
            boolean await = countDownLatch.await(j, timeUnit);
            ArrayList arrayList = new ArrayList((Collection) Futures.successfulAsList((Iterable<? extends ListenableFuture<? extends V>>) newArrayList).get());
            arrayList.removeAll(Collections.singleton(null));
            int size = set.size() - arrayList.size();
            if (size > 0) {
                Logger logger = log;
                StringBuilder sb = new StringBuilder();
                sb.append("{} failures ");
                sb.append(await ? "" : "(including timeout) ");
                sb.append("opening DNS lookup circuits");
                logger.warn(sb.toString(), (Object) Integer.valueOf(size));
            }
            shutdownThreadPool();
            return arrayList;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            shutdownThreadPool();
            throw th;
        }
    }

    private Collection<InetSocketAddress> lookupAddresses(long j, TimeUnit timeUnit, List<Circuit> list) throws InterruptedException {
        int i;
        createThreadPool(list.size() * this.hostNames.length);
        try {
            ArrayList<ListenableFuture> newArrayList = Lists.newArrayList();
            Iterator it = list.iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                final Circuit circuit = (Circuit) it.next();
                String[] strArr = this.hostNames;
                int length = strArr.length;
                while (i < length) {
                    final String str = strArr[i];
                    newArrayList.add(this.threadPool.submit((Callable<T>) new Callable<Lookup>() {
                        public Lookup call() throws Exception {
                            return new Lookup(circuit.getFinalCircuitNode().getRouter(), TorDiscovery.this.lookup(circuit, str));
                        }
                    }));
                    i++;
                }
            }
            this.threadPool.awaitTermination(j, timeUnit);
            for (ListenableFuture listenableFuture : newArrayList) {
                if (!listenableFuture.isDone()) {
                    i++;
                    listenableFuture.cancel(true);
                }
            }
            if (i > 0) {
                log.warn("{} DNS lookups timed out", (Object) Integer.valueOf(i));
            }
            ArrayList<Lookup> arrayList = new ArrayList<>((Collection) Futures.successfulAsList((Iterable<? extends ListenableFuture<? extends V>>) newArrayList).get());
            arrayList.removeAll(Collections.singleton(null));
            HashMap newHashMap = Maps.newHashMap();
            for (Lookup lookup : arrayList) {
                newHashMap.put(lookup.router.getIdentityHash(), new InetSocketAddress(lookup.address, this.netParams.getPort()));
            }
            Collection<InetSocketAddress> values = newHashMap.values();
            shutdownThreadPool();
            return values;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            shutdownThreadPool();
            throw th;
        }
    }

    private synchronized void shutdownThreadPool() {
        this.threadPool.shutdownNow();
        this.threadPool = null;
    }

    private synchronized void createThreadPool(int i) {
        this.threadPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(i, new ContextPropagatingThreadFactory("Tor DNS discovery")));
    }

    /* access modifiers changed from: private */
    public InetAddress lookup(Circuit circuit, String str) throws UnknownHostException {
        RelayCell createRelayCell = circuit.createRelayCell(11, 4096, circuit.getFinalCircuitNode());
        createRelayCell.putString(str);
        circuit.sendRelayCell(createRelayCell);
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            RelayCell receiveRelayCell = circuit.receiveRelayCell();
            if (receiveRelayCell != null) {
                while (receiveRelayCell.cellBytesRemaining() > 0) {
                    int i2 = receiveRelayCell.getByte();
                    byte[] bArr = new byte[receiveRelayCell.getByte()];
                    receiveRelayCell.getByteArray(bArr);
                    receiveRelayCell.getInt();
                    if (i2 == 0 || i2 >= 240) {
                        throw new RuntimeException(new String(bArr));
                    }
                    if (i2 != 4) {
                        if (i2 == 6) {
                        }
                    }
                    return InetAddress.getByAddress(bArr);
                }
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not look up ");
        sb.append(str);
        throw new RuntimeException(sb.toString());
    }

    public synchronized void shutdown() {
        if (this.threadPool != null) {
            shutdownThreadPool();
        }
    }
}
