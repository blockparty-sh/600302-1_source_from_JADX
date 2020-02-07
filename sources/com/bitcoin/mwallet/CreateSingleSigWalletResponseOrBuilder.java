package com.bitcoin.mwallet;

import com.bitcoin.mwallet.CreateSingleSigWalletResponse.ResponseCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CreateSingleSigWalletResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ResponseCase getResponseCase();

    String getWalletId();

    ByteString getWalletIdBytes();
}
