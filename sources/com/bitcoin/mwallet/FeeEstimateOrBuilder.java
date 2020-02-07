package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface FeeEstimateOrBuilder extends MessageLiteOrBuilder {
    Coin getCoin();

    int getCoinValue();

    FeeLevel getLevel();

    int getLevelValue();

    String getSatoshiPerByte();

    ByteString getSatoshiPerByteBytes();
}
