package org.bitcoinj.net;

import java.net.InetAddress;
import javax.annotation.Nullable;

public interface StreamConnectionFactory {
    @Nullable
    StreamConnection getNewConnection(InetAddress inetAddress, int i);
}
