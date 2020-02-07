package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;

public interface SubscribeResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    boolean hasError();
}
