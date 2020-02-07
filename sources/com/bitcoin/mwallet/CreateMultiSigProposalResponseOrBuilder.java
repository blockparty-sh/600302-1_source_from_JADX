package com.bitcoin.mwallet;

import com.bitcoin.mwallet.CreateMultiSigProposalResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CreateMultiSigProposalResponseOrBuilder extends MessageLiteOrBuilder {
    CreateProposalResponse getCreation();

    ProposalCreationError getCreationError();

    int getCreationErrorValue();

    ResponseError getError();

    ResponseCase getResponseCase();
}
