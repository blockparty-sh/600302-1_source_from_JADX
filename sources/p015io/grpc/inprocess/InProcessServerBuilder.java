package p015io.grpc.inprocess;

import com.google.common.base.Preconditions;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p015io.grpc.ExperimentalApi;
import p015io.grpc.ServerStreamTracer.Factory;
import p015io.grpc.internal.AbstractServerImplBuilder;
import p015io.grpc.internal.FixedObjectPool;
import p015io.grpc.internal.GrpcUtil;
import p015io.grpc.internal.ObjectPool;
import p015io.grpc.internal.SharedResourcePool;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1783")
/* renamed from: io.grpc.inprocess.InProcessServerBuilder */
public final class InProcessServerBuilder extends AbstractServerImplBuilder<InProcessServerBuilder> {
    int maxInboundMetadataSize = Integer.MAX_VALUE;
    final String name;
    ObjectPool<ScheduledExecutorService> schedulerPool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);

    public static InProcessServerBuilder forName(String str) {
        return new InProcessServerBuilder(str);
    }

    public static InProcessServerBuilder forPort(int i) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static String generateName() {
        return UUID.randomUUID().toString();
    }

    private InProcessServerBuilder(String str) {
        this.name = (String) Preconditions.checkNotNull(str, "name");
        setStatsRecordStartedRpcs(false);
        setStatsRecordFinishedRpcs(false);
        handshakeTimeout(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    public InProcessServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.schedulerPool = new FixedObjectPool(Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    public InProcessServerBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    /* access modifiers changed from: protected */
    public List<InProcessServer> buildTransportServers(List<? extends Factory> list) {
        return Collections.singletonList(new InProcessServer(this, list));
    }

    public InProcessServerBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("TLS not supported in InProcessServer");
    }
}
