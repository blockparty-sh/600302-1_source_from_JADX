package com.bitcoin.mwallet;

import com.bitcoin.mwallet.RejectProposalResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RejectProposalResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    boolean getRejected();

    ResponseCase getResponseCase();
}
