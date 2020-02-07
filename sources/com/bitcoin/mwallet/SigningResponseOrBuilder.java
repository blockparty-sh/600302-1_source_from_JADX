package com.bitcoin.mwallet;

import com.bitcoin.mwallet.SigningResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SigningResponseOrBuilder extends MessageLiteOrBuilder {
    boolean getBroadcasted();

    BroadcastProposalError getError();

    int getErrorValue();

    int getNumMoreSignaturesRequired();

    ResponseCase getResponseCase();
}
