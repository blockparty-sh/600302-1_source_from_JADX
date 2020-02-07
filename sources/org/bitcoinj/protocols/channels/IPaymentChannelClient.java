package org.bitcoinj.protocols.channels;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import javax.annotation.Nullable;
import org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey.KeyIsEncryptedException;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.protocols.channels.PaymentChannelCloseException.CloseReason;
import org.spongycastle.crypto.params.KeyParameter;

public interface IPaymentChannelClient {

    public interface ClientConnection {
        boolean acceptExpireTime(long j);

        void channelOpen(boolean z);

        void destroyConnection(CloseReason closeReason);

        void sendToServer(TwoWayChannelMessage twoWayChannelMessage);
    }

    public interface Factory {
        IPaymentChannelClient create(String str, ClientConnection clientConnection);
    }

    void connectionClosed();

    void connectionOpen();

    ListenableFuture<PaymentIncrementAck> incrementPayment(Coin coin, @Nullable ByteString byteString, @Nullable KeyParameter keyParameter) throws ValueOutOfRangeException, IllegalStateException, KeyIsEncryptedException;

    void receiveMessage(TwoWayChannelMessage twoWayChannelMessage) throws InsufficientMoneyException;

    void settle() throws IllegalStateException;
}
