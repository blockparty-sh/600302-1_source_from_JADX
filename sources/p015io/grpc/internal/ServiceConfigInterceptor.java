package p015io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Verify;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import p015io.grpc.CallOptions;
import p015io.grpc.CallOptions.C2671Key;
import p015io.grpc.Channel;
import p015io.grpc.ClientCall;
import p015io.grpc.ClientInterceptor;
import p015io.grpc.Deadline;
import p015io.grpc.MethodDescriptor;

/* renamed from: io.grpc.internal.ServiceConfigInterceptor */
final class ServiceConfigInterceptor implements ClientInterceptor {
    static final C2671Key<Provider> HEDGING_POLICY_KEY = C2671Key.create("internal-hedging-policy");
    static final C2671Key<Provider> RETRY_POLICY_KEY = C2671Key.create("internal-retry-policy");
    /* access modifiers changed from: private */
    public volatile boolean initComplete;
    @VisibleForTesting
    final AtomicReference<ManagedChannelServiceConfig> managedChannelServiceConfig = new AtomicReference<>();
    private final int maxHedgedAttemptsLimit;
    private final int maxRetryAttemptsLimit;
    private final boolean retryEnabled;

    ServiceConfigInterceptor(boolean z, int i, int i2) {
        this.retryEnabled = z;
        this.maxRetryAttemptsLimit = i;
        this.maxHedgedAttemptsLimit = i2;
    }

    /* access modifiers changed from: 0000 */
    public void handleUpdate(@Nullable Map<String, ?> map) {
        ManagedChannelServiceConfig managedChannelServiceConfig2;
        if (map == null) {
            managedChannelServiceConfig2 = new ManagedChannelServiceConfig(new HashMap(), new HashMap(), null, null);
        } else {
            managedChannelServiceConfig2 = ManagedChannelServiceConfig.fromServiceConfig(map, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, null);
        }
        this.managedChannelServiceConfig.set(managedChannelServiceConfig2);
        this.initComplete = true;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        if (this.retryEnabled) {
            if (this.initComplete) {
                final RetryPolicy retryPolicyFromConfig = getRetryPolicyFromConfig(methodDescriptor);
                final HedgingPolicy hedgingPolicyFromConfig = getHedgingPolicyFromConfig(methodDescriptor);
                Verify.verify(retryPolicyFromConfig.equals(RetryPolicy.DEFAULT) || hedgingPolicyFromConfig.equals(HedgingPolicy.DEFAULT), "Can not apply both retry and hedging policy for the method '%s'", (Object) methodDescriptor);
                callOptions = callOptions.withOption(RETRY_POLICY_KEY, new Provider() {
                    public RetryPolicy get() {
                        return retryPolicyFromConfig;
                    }
                }).withOption(HEDGING_POLICY_KEY, new Provider() {
                    public HedgingPolicy get() {
                        return hedgingPolicyFromConfig;
                    }
                });
            } else {
                callOptions = callOptions.withOption(RETRY_POLICY_KEY, new Provider() {
                    public RetryPolicy get() {
                        if (!ServiceConfigInterceptor.this.initComplete) {
                            return RetryPolicy.DEFAULT;
                        }
                        return ServiceConfigInterceptor.this.getRetryPolicyFromConfig(methodDescriptor);
                    }
                }).withOption(HEDGING_POLICY_KEY, new Provider() {
                    public HedgingPolicy get() {
                        if (!ServiceConfigInterceptor.this.initComplete) {
                            return HedgingPolicy.DEFAULT;
                        }
                        HedgingPolicy hedgingPolicyFromConfig = ServiceConfigInterceptor.this.getHedgingPolicyFromConfig(methodDescriptor);
                        Verify.verify(hedgingPolicyFromConfig.equals(HedgingPolicy.DEFAULT) || ServiceConfigInterceptor.this.getRetryPolicyFromConfig(methodDescriptor).equals(RetryPolicy.DEFAULT), "Can not apply both retry and hedging policy for the method '%s'", (Object) methodDescriptor);
                        return hedgingPolicyFromConfig;
                    }
                });
            }
        }
        MethodInfo methodInfo = getMethodInfo(methodDescriptor);
        if (methodInfo == null) {
            return channel.newCall(methodDescriptor, callOptions);
        }
        if (methodInfo.timeoutNanos != null) {
            Deadline after = Deadline.after(methodInfo.timeoutNanos.longValue(), TimeUnit.NANOSECONDS);
            Deadline deadline = callOptions.getDeadline();
            if (deadline == null || after.compareTo(deadline) < 0) {
                callOptions = callOptions.withDeadline(after);
            }
        }
        if (methodInfo.waitForReady != null) {
            callOptions = methodInfo.waitForReady.booleanValue() ? callOptions.withWaitForReady() : callOptions.withoutWaitForReady();
        }
        if (methodInfo.maxInboundMessageSize != null) {
            Integer maxInboundMessageSize = callOptions.getMaxInboundMessageSize();
            if (maxInboundMessageSize != null) {
                callOptions = callOptions.withMaxInboundMessageSize(Math.min(maxInboundMessageSize.intValue(), methodInfo.maxInboundMessageSize.intValue()));
            } else {
                callOptions = callOptions.withMaxInboundMessageSize(methodInfo.maxInboundMessageSize.intValue());
            }
        }
        if (methodInfo.maxOutboundMessageSize != null) {
            Integer maxOutboundMessageSize = callOptions.getMaxOutboundMessageSize();
            if (maxOutboundMessageSize != null) {
                callOptions = callOptions.withMaxOutboundMessageSize(Math.min(maxOutboundMessageSize.intValue(), methodInfo.maxOutboundMessageSize.intValue()));
            } else {
                callOptions = callOptions.withMaxOutboundMessageSize(methodInfo.maxOutboundMessageSize.intValue());
            }
        }
        return channel.newCall(methodDescriptor, callOptions);
    }

    @CheckForNull
    private MethodInfo getMethodInfo(MethodDescriptor<?, ?> methodDescriptor) {
        ManagedChannelServiceConfig managedChannelServiceConfig2 = (ManagedChannelServiceConfig) this.managedChannelServiceConfig.get();
        MethodInfo methodInfo = managedChannelServiceConfig2 != null ? (MethodInfo) managedChannelServiceConfig2.getServiceMethodMap().get(methodDescriptor.getFullMethodName()) : null;
        if (methodInfo != null || managedChannelServiceConfig2 == null) {
            return methodInfo;
        }
        return (MethodInfo) managedChannelServiceConfig2.getServiceMap().get(methodDescriptor.getServiceName());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public RetryPolicy getRetryPolicyFromConfig(MethodDescriptor<?, ?> methodDescriptor) {
        MethodInfo methodInfo = getMethodInfo(methodDescriptor);
        return methodInfo == null ? RetryPolicy.DEFAULT : methodInfo.retryPolicy;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public HedgingPolicy getHedgingPolicyFromConfig(MethodDescriptor<?, ?> methodDescriptor) {
        MethodInfo methodInfo = getMethodInfo(methodDescriptor);
        return methodInfo == null ? HedgingPolicy.DEFAULT : methodInfo.hedgingPolicy;
    }
}
