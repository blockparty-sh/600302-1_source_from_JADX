package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface LocationInfoOrBuilder extends MessageLiteOrBuilder {
    boolean getBuyBitcoinAllowed();

    String getCountryCode();

    ByteString getCountryCodeBytes();

    String getIp();

    ByteString getIpBytes();
}
