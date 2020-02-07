package org.bitcoinj.protocols.channels;

import com.google.protobuf.ByteString;
import javax.annotation.Nullable;
import org.bitcoinj.core.Coin;

public class PaymentIncrementAck {
    @Nullable
    private final ByteString info;
    private final Coin value;

    public PaymentIncrementAck(Coin coin, @Nullable ByteString byteString) {
        this.value = coin;
        this.info = byteString;
    }

    public Coin getValue() {
        return this.value;
    }

    @Nullable
    public ByteString getInfo() {
        return this.info;
    }
}
