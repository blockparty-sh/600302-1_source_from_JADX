package p015io.grpc;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import javax.net.ssl.SSLSession;
import p015io.grpc.Attributes.C2669Key;

/* renamed from: io.grpc.Grpc */
public final class Grpc {
    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1710")
    public static final C2669Key<SocketAddress> TRANSPORT_ATTR_LOCAL_ADDR = C2669Key.create("local-addr");
    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1710")
    public static final C2669Key<SocketAddress> TRANSPORT_ATTR_REMOTE_ADDR = C2669Key.create("remote-addr");
    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/1710")
    public static final C2669Key<SSLSession> TRANSPORT_ATTR_SSL_SESSION = C2669Key.create("ssl-session");

    @ExperimentalApi("https://github.com/grpc/grpc-java/issues/4972")
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: io.grpc.Grpc$TransportAttr */
    public @interface TransportAttr {
    }

    private Grpc() {
    }
}
