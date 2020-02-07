package p015io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p015io.grpc.ClientStreamTracer.Factory;

@CheckReturnValue
@Immutable
/* renamed from: io.grpc.CallOptions */
public final class CallOptions {
    public static final CallOptions DEFAULT = new CallOptions();
    @Nullable
    private String authority;
    @Nullable
    private String compressorName;
    @Nullable
    private CallCredentials credentials;
    private Object[][] customOptions = ((Object[][]) Array.newInstance(Object.class, new int[]{0, 2}));
    @Nullable
    private Deadline deadline;
    @Nullable
    private Executor executor;
    @Nullable
    private Integer maxInboundMessageSize;
    @Nullable
    private Integer maxOutboundMessageSize;
    private List<Factory> streamTracerFactories = Collections.emptyList();
    @Nullable
    private Boolean waitForReady;

    /* renamed from: io.grpc.CallOptions$Key */
    public static final class C2671Key<T> {
        private final String debugString;
        /* access modifiers changed from: private */
        public final T defaultValue;

        private C2671Key(String str, T t) {
            this.debugString = str;
            this.defaultValue = t;
        }

        public T getDefault() {
            return this.defaultValue;
        }

        public String toString() {
            return this.debugString;
        }

        @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1869")
        @Deprecated
        /* renamed from: of */
        public static <T> C2671Key<T> m293of(String str, T t) {
            Preconditions.checkNotNull(str, "debugString");
            return new C2671Key<>(str, t);
        }

        public static <T> C2671Key<T> create(String str) {
            Preconditions.checkNotNull(str, "debugString");
            return new C2671Key<>(str, null);
        }

        public static <T> C2671Key<T> createWithDefault(String str, T t) {
            Preconditions.checkNotNull(str, "debugString");
            return new C2671Key<>(str, t);
        }
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1767")
    public CallOptions withAuthority(@Nullable String str) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.authority = str;
        return callOptions;
    }

    public CallOptions withCallCredentials(@Nullable CallCredentials callCredentials) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.credentials = callCredentials;
        return callOptions;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1704")
    public CallOptions withCompression(@Nullable String str) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.compressorName = str;
        return callOptions;
    }

    public CallOptions withDeadline(@Nullable Deadline deadline2) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.deadline = deadline2;
        return callOptions;
    }

    public CallOptions withDeadlineAfter(long j, TimeUnit timeUnit) {
        return withDeadline(Deadline.after(j, timeUnit));
    }

    @Nullable
    public Deadline getDeadline() {
        return this.deadline;
    }

    public CallOptions withWaitForReady() {
        CallOptions callOptions = new CallOptions(this);
        callOptions.waitForReady = Boolean.TRUE;
        return callOptions;
    }

    public CallOptions withoutWaitForReady() {
        CallOptions callOptions = new CallOptions(this);
        callOptions.waitForReady = Boolean.FALSE;
        return callOptions;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1704")
    @Nullable
    public String getCompressor() {
        return this.compressorName;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1767")
    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    @Nullable
    public CallCredentials getCredentials() {
        return this.credentials;
    }

    public CallOptions withExecutor(@Nullable Executor executor2) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.executor = executor2;
        return callOptions;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2861")
    public CallOptions withStreamTracerFactory(Factory factory) {
        CallOptions callOptions = new CallOptions(this);
        ArrayList arrayList = new ArrayList(this.streamTracerFactories.size() + 1);
        arrayList.addAll(this.streamTracerFactories);
        arrayList.add(factory);
        callOptions.streamTracerFactories = Collections.unmodifiableList(arrayList);
        return callOptions;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2861")
    public List<Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }

    public <T> CallOptions withOption(C2671Key<T> key, T t) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(t, "value");
        CallOptions callOptions = new CallOptions(this);
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                i = -1;
                break;
            } else if (key.equals(objArr[i][0])) {
                break;
            } else {
                i++;
            }
        }
        callOptions.customOptions = (Object[][]) Array.newInstance(Object.class, new int[]{this.customOptions.length + (i == -1 ? 1 : 0), 2});
        Object[][] objArr2 = this.customOptions;
        System.arraycopy(objArr2, 0, callOptions.customOptions, 0, objArr2.length);
        if (i == -1) {
            callOptions.customOptions[this.customOptions.length] = new Object[]{key, t};
        } else {
            callOptions.customOptions[i] = new Object[]{key, t};
        }
        return callOptions;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1869")
    public <T> T getOption(C2671Key<T> key) {
        Preconditions.checkNotNull(key, "key");
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                return key.defaultValue;
            }
            if (key.equals(objArr[i][0])) {
                return this.customOptions[i][1];
            }
            i++;
        }
    }

    @Nullable
    public Executor getExecutor() {
        return this.executor;
    }

    private CallOptions() {
    }

    public boolean isWaitForReady() {
        return Boolean.TRUE.equals(this.waitForReady);
    }

    /* access modifiers changed from: 0000 */
    public Boolean getWaitForReady() {
        return this.waitForReady;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2563")
    public CallOptions withMaxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        CallOptions callOptions = new CallOptions(this);
        callOptions.maxInboundMessageSize = Integer.valueOf(i);
        return callOptions;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2563")
    public CallOptions withMaxOutboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        CallOptions callOptions = new CallOptions(this);
        callOptions.maxOutboundMessageSize = Integer.valueOf(i);
        return callOptions;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2563")
    @Nullable
    public Integer getMaxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/2563")
    @Nullable
    public Integer getMaxOutboundMessageSize() {
        return this.maxOutboundMessageSize;
    }

    private CallOptions(CallOptions callOptions) {
        this.deadline = callOptions.deadline;
        this.authority = callOptions.authority;
        this.credentials = callOptions.credentials;
        this.executor = callOptions.executor;
        this.compressorName = callOptions.compressorName;
        this.customOptions = callOptions.customOptions;
        this.waitForReady = callOptions.waitForReady;
        this.maxInboundMessageSize = callOptions.maxInboundMessageSize;
        this.maxOutboundMessageSize = callOptions.maxOutboundMessageSize;
        this.streamTracerFactories = callOptions.streamTracerFactories;
    }

    public String toString() {
        String str = "callCredentials";
        ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("deadline", (Object) this.deadline).add("authority", (Object) this.authority).add(str, (Object) this.credentials);
        Executor executor2 = this.executor;
        return add.add("executor", (Object) executor2 != null ? executor2.getClass() : null).add("compressorName", (Object) this.compressorName).add("customOptions", (Object) Arrays.deepToString(this.customOptions)).add("waitForReady", isWaitForReady()).add("maxInboundMessageSize", (Object) this.maxInboundMessageSize).add("maxOutboundMessageSize", (Object) this.maxOutboundMessageSize).add("streamTracerFactories", (Object) this.streamTracerFactories).toString();
    }
}
