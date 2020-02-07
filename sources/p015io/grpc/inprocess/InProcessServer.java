package p015io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.ThreadSafe;
import p015io.grpc.InternalChannelz.SocketStats;
import p015io.grpc.InternalInstrumented;
import p015io.grpc.ServerStreamTracer.Factory;
import p015io.grpc.internal.InternalServer;
import p015io.grpc.internal.ObjectPool;
import p015io.grpc.internal.ServerListener;
import p015io.grpc.internal.ServerTransportListener;

@ThreadSafe
/* renamed from: io.grpc.inprocess.InProcessServer */
final class InProcessServer implements InternalServer {
    private static final ConcurrentMap<String, InProcessServer> registry = new ConcurrentHashMap();
    private ServerListener listener;
    private final int maxInboundMetadataSize;
    private final String name;
    private ScheduledExecutorService scheduler;
    private final ObjectPool<ScheduledExecutorService> schedulerPool;
    private boolean shutdown;
    private final List<Factory> streamTracerFactories;

    public InternalInstrumented<SocketStats> getListenSocketStats() {
        return null;
    }

    static InProcessServer findServer(String str) {
        return (InProcessServer) registry.get(str);
    }

    InProcessServer(InProcessServerBuilder inProcessServerBuilder, List<? extends Factory> list) {
        this.name = inProcessServerBuilder.name;
        this.schedulerPool = inProcessServerBuilder.schedulerPool;
        this.maxInboundMetadataSize = inProcessServerBuilder.maxInboundMetadataSize;
        this.streamTracerFactories = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "streamTracerFactories"));
    }

    public void start(ServerListener serverListener) throws IOException {
        this.listener = serverListener;
        this.scheduler = (ScheduledExecutorService) this.schedulerPool.getObject();
        if (registry.putIfAbsent(this.name, this) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("name already registered: ");
            sb.append(this.name);
            throw new IOException(sb.toString());
        }
    }

    public SocketAddress getListenSocketAddress() {
        return new InProcessSocketAddress(this.name);
    }

    public void shutdown() {
        if (registry.remove(this.name, this)) {
            this.scheduler = (ScheduledExecutorService) this.schedulerPool.returnObject(this.scheduler);
            synchronized (this) {
                this.shutdown = true;
                this.listener.serverShutdown();
            }
            return;
        }
        throw new AssertionError();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("name", (Object) this.name).toString();
    }

    /* access modifiers changed from: 0000 */
    public synchronized ServerTransportListener register(InProcessTransport inProcessTransport) {
        if (this.shutdown) {
            return null;
        }
        return this.listener.transportCreated(inProcessTransport);
    }

    /* access modifiers changed from: 0000 */
    public ObjectPool<ScheduledExecutorService> getScheduledExecutorServicePool() {
        return this.schedulerPool;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxInboundMetadataSize() {
        return this.maxInboundMetadataSize;
    }

    /* access modifiers changed from: 0000 */
    public List<Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }
}
