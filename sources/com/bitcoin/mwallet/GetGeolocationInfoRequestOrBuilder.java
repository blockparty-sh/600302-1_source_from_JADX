package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GetGeolocationInfoRequestOrBuilder extends MessageLiteOrBuilder {
    String getIp();

    ByteString getIpBytes();
}
