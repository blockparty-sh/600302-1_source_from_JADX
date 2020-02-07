package p015io.grpc.okhttp;

import p015io.grpc.Internal;
import p015io.grpc.InternalServiceProviders;
import p015io.grpc.ManagedChannelProvider;
import p015io.grpc.internal.GrpcUtil;

@Internal
/* renamed from: io.grpc.okhttp.OkHttpChannelProvider */
public final class OkHttpChannelProvider extends ManagedChannelProvider {
    public boolean isAvailable() {
        return true;
    }

    public int priority() {
        return (GrpcUtil.IS_RESTRICTED_APPENGINE || InternalServiceProviders.isAndroid(getClass().getClassLoader())) ? 8 : 3;
    }

    public OkHttpChannelBuilder builderForAddress(String str, int i) {
        return OkHttpChannelBuilder.forAddress(str, i);
    }

    public OkHttpChannelBuilder builderForTarget(String str) {
        return OkHttpChannelBuilder.forTarget(str);
    }
}
