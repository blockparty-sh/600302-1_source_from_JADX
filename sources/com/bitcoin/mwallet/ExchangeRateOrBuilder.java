package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ExchangeRateOrBuilder extends MessageLiteOrBuilder {
    String getName();

    ByteString getNameBytes();

    String getRate();

    ByteString getRateBytes();

    String getTicker();

    ByteString getTickerBytes();
}
