package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;

public interface RegisterRegionResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    boolean hasError();
}
