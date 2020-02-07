package com.bitcoin.mwallet;

import com.bitcoin.mwallet.RecoverSingleSigWalletResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RecoverSingleSigWalletResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    RecoverResponse getImport();

    ResponseCase getResponseCase();
}
