package com.bitcoin.mwallet;

import com.bitcoin.mwallet.GetNewMaxAddressPathResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GetNewMaxAddressPathResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    AddressPath getNewMaxPath();

    ResponseCase getResponseCase();
}
