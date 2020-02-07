package com.bitcoin.mwallet;

import com.bitcoin.mwallet.CreateMultiSigWalletResponse.ResponseCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CreateMultiSigWalletResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ResponseCase getResponseCase();

    String getWalletId();

    ByteString getWalletIdBytes();
}
