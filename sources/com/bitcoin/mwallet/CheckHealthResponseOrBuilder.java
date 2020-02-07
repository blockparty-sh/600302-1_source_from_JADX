package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CheckHealthResponseOrBuilder extends MessageLiteOrBuilder {
    String getRegion();

    ByteString getRegionBytes();
}
