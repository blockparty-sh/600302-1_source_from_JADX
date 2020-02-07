package org.bitcoinj.jni;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason;
import org.bitcoinj.protocols.channels.ServerConnectionEventHandler;

public class NativePaymentChannelServerConnectionEventHandler extends ServerConnectionEventHandler {
    public long ptr;

    public native void channelClosed(CloseReason closeReason);

    public native void channelOpen(Sha256Hash sha256Hash);

    public native ListenableFuture<ByteString> paymentIncrease(Coin coin, Coin coin2, ByteString byteString);
}
