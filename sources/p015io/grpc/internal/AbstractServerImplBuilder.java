package p015io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p015io.grpc.BinaryLog;
import p015io.grpc.BindableService;
import p015io.grpc.CompressorRegistry;
import p015io.grpc.Context;
import p015io.grpc.DecompressorRegistry;
import p015io.grpc.HandlerRegistry;
import p015io.grpc.InternalChannelz;
import p015io.grpc.InternalNotifyOnServerBuild;
import p015io.grpc.Server;
import p015io.grpc.ServerBuilder;
import p015io.grpc.ServerInterceptor;
import p015io.grpc.ServerMethodDefinition;
import p015io.grpc.ServerServiceDefinition;
import p015io.grpc.ServerStreamTracer;
import p015io.grpc.ServerTransportFilter;
import p015io.grpc.internal.AbstractServerImplBuilder;
import p015io.grpc.internal.CallTracer.Factory;
import p015io.opencensus.trace.Tracing;

/* renamed from: io.grpc.internal.AbstractServerImplBuilder */
public abstract class AbstractServerImplBuilder<T extends AbstractServerImplBuilder<T>> extends ServerBuilder<T> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final HandlerRegistry DEFAULT_FALLBACK_REGISTRY = new DefaultFallbackRegistry();
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120);
    @Nullable
    BinaryLog binlog;
    Factory callTracerFactory = CallTracer.getDefaultFactory();
    @Nullable
    private CensusStatsModule censusStatsOverride;
    InternalChannelz channelz = InternalChannelz.instance();
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    HandlerRegistry fallbackRegistry = DEFAULT_FALLBACK_REGISTRY;
    long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;
    final List<ServerInterceptor> interceptors = new ArrayList();
    private final List<InternalNotifyOnServerBuild> notifyOnBuildList = new ArrayList();
    private boolean recordFinishedRpcs = true;
    private boolean recordRealTimeMetrics = false;
    private boolean recordStartedRpcs = true;
    final Builder registryBuilder = new Builder();
    private boolean statsEnabled = true;
    private final List<ServerStreamTracer.Factory> streamTracerFactories = new ArrayList();
    private boolean tracingEnabled = true;
    final List<ServerTransportFilter> transportFilters = new ArrayList();
    TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();

    /* renamed from: io.grpc.internal.AbstractServerImplBuilder$DefaultFallbackRegistry */
    private static final class DefaultFallbackRegistry extends HandlerRegistry {
        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
            return null;
        }

        private DefaultFallbackRegistry() {
        }

        public List<ServerServiceDefinition> getServices() {
            return Collections.emptyList();
        }
    }

    private T thisT() {
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract List<? extends InternalServer> buildTransportServers(List<? extends ServerStreamTracer.Factory> list);

    public static ServerBuilder<?> forPort(int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public final T directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    public final T executor(@Nullable Executor executor) {
        this.executorPool = executor != null ? new FixedObjectPool<>(executor) : DEFAULT_EXECUTOR_POOL;
        return thisT();
    }

    public final T addService(ServerServiceDefinition serverServiceDefinition) {
        this.registryBuilder.addService((ServerServiceDefinition) Preconditions.checkNotNull(serverServiceDefinition, NotificationCompat.CATEGORY_SERVICE));
        return thisT();
    }

    public final T addService(BindableService bindableService) {
        if (bindableService instanceof InternalNotifyOnServerBuild) {
            this.notifyOnBuildList.add((InternalNotifyOnServerBuild) bindableService);
        }
        return addService(((BindableService) Preconditions.checkNotNull(bindableService, "bindableService")).bindService());
    }

    public final T addTransportFilter(ServerTransportFilter serverTransportFilter) {
        this.transportFilters.add(Preconditions.checkNotNull(serverTransportFilter, "filter"));
        return thisT();
    }

    public final T intercept(ServerInterceptor serverInterceptor) {
        this.interceptors.add(Preconditions.checkNotNull(serverInterceptor, "interceptor"));
        return thisT();
    }

    public final T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.streamTracerFactories.add(Preconditions.checkNotNull(factory, "factory"));
        return thisT();
    }

    public final T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        if (handlerRegistry == null) {
            handlerRegistry = DEFAULT_FALLBACK_REGISTRY;
        }
        this.fallbackRegistry = handlerRegistry;
        return thisT();
    }

    public final T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry2) {
        if (decompressorRegistry2 == null) {
            decompressorRegistry2 = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        this.decompressorRegistry = decompressorRegistry2;
        return thisT();
    }

    public final T compressorRegistry(@Nullable CompressorRegistry compressorRegistry2) {
        if (compressorRegistry2 == null) {
            compressorRegistry2 = DEFAULT_COMPRESSOR_REGISTRY;
        }
        this.compressorRegistry = compressorRegistry2;
        return thisT();
    }

    public final T handshakeTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "handshake timeout is %s, but must be positive", j);
        this.handshakeTimeoutMillis = ((TimeUnit) Preconditions.checkNotNull(timeUnit, "unit")).toMillis(j);
        return thisT();
    }

    public final T setBinaryLog(@Nullable BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return thisT();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final T overrideCensusStatsModule(@Nullable CensusStatsModule censusStatsModule) {
        this.censusStatsOverride = censusStatsModule;
        return thisT();
    }

    @VisibleForTesting
    public final T setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return thisT();
    }

    /* access modifiers changed from: protected */
    public void setStatsEnabled(boolean z) {
        this.statsEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordStartedRpcs(boolean z) {
        this.recordStartedRpcs = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordFinishedRpcs(boolean z) {
        this.recordFinishedRpcs = z;
    }

    /* access modifiers changed from: protected */
    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.recordRealTimeMetrics = z;
    }

    /* access modifiers changed from: protected */
    public void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    public final Server build() {
        ServerImpl serverImpl = new ServerImpl(this, buildTransportServers(getTracerFactories()), Context.ROOT);
        for (InternalNotifyOnServerBuild notifyOnBuild : this.notifyOnBuildList) {
            notifyOnBuild.notifyOnBuild(serverImpl);
        }
        return serverImpl;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final List<? extends ServerStreamTracer.Factory> getTracerFactories() {
        ArrayList arrayList = new ArrayList();
        if (this.statsEnabled) {
            CensusStatsModule censusStatsModule = this.censusStatsOverride;
            if (censusStatsModule == null) {
                censusStatsModule = new CensusStatsModule(GrpcUtil.STOPWATCH_SUPPLIER, true, this.recordStartedRpcs, this.recordFinishedRpcs, this.recordRealTimeMetrics);
            }
            arrayList.add(censusStatsModule.getServerTracerFactory());
        }
        if (this.tracingEnabled) {
            arrayList.add(new CensusTracingModule(Tracing.getTracer(), Tracing.getPropagationComponent().getBinaryFormat()).getServerTracerFactory());
        }
        arrayList.addAll(this.streamTracerFactories);
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: protected */
    public final InternalChannelz getChannelz() {
        return this.channelz;
    }

    /* access modifiers changed from: protected */
    public final TransportTracer.Factory getTransportTracerFactory() {
        return this.transportTracerFactory;
    }
}
