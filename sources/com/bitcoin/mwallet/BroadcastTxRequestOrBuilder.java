package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BroadcastTxRequestOrBuilder extends MessageLiteOrBuilder {
    Coin getCoin();

    int getCoinValue();

    Network getNetwork();

    int getNetworkValue();

    ByteString getTxRaw();
}
