package com.bitcoin.mwallet;

import com.bitcoin.mwallet.SignMultiSigProposalResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SignMultiSigProposalResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ResponseCase getResponseCase();

    SigningResponse getSigningResponse();
}
