package p015io.grpc.internal;

import java.util.Map;
import p015io.grpc.Attributes;
import p015io.grpc.Attributes.C2669Key;
import p015io.grpc.SecurityLevel;

/* renamed from: io.grpc.internal.GrpcAttributes */
public final class GrpcAttributes {
    public static final C2669Key<Attributes> ATTR_CLIENT_EAG_ATTRS = C2669Key.create("io.grpc.internal.GrpcAttributes.clientEagAttrs");
    public static final C2669Key<String> ATTR_LB_ADDR_AUTHORITY = C2669Key.create("io.grpc.grpclb.lbAddrAuthority");
    public static final C2669Key<Boolean> ATTR_LB_PROVIDED_BACKEND = C2669Key.create("io.grpc.grpclb.lbProvidedBackend");
    public static final C2669Key<SecurityLevel> ATTR_SECURITY_LEVEL = C2669Key.create("io.grpc.internal.GrpcAttributes.securityLevel");
    public static final C2669Key<Map<String, ?>> NAME_RESOLVER_SERVICE_CONFIG = C2669Key.create("service-config");

    private GrpcAttributes() {
    }
}
