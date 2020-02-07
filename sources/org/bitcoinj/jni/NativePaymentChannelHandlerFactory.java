package org.bitcoinj.jni;

import java.net.SocketAddress;
import javax.annotation.Nullable;
import org.bitcoinj.protocols.channels.PaymentChannelServerListener.HandlerFactory;
import org.bitcoinj.protocols.channels.ServerConnectionEventHandler;

public class NativePaymentChannelHandlerFactory implements HandlerFactory {
    public long ptr;

    @Nullable
    public native ServerConnectionEventHandler onNewConnection(SocketAddress socketAddress);
}
