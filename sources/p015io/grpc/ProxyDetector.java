package p015io.grpc;

import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/5279")
/* renamed from: io.grpc.ProxyDetector */
public interface ProxyDetector {
    @Nullable
    ProxiedSocketAddress proxyFor(SocketAddress socketAddress) throws IOException;
}
