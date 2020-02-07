package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UnsubscribeResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    boolean hasError();
}
