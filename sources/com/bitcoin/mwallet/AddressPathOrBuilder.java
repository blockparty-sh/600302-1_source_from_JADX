package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AddressPathOrBuilder extends MessageLiteOrBuilder {
    int getX();

    int getY();
}
