package com.bitcoin.mwallet;

import com.bitcoin.mwallet.UpdateWalletNameResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UpdateWalletNameResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ResponseCase getResponseCase();
}
