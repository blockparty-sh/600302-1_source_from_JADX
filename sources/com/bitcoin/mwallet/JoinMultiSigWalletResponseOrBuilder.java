package com.bitcoin.mwallet;

import com.bitcoin.mwallet.JoinMultiSigWalletResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface JoinMultiSigWalletResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    JoinResponse getJoined();

    ResponseCase getResponseCase();
}
