package com.bitcoin.mwallet;

import com.bitcoin.mwallet.FetchCreatedProposalRequest.RequestCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface FetchCreatedProposalRequestOrBuilder extends MessageLiteOrBuilder {
    String getProposalId();

    ByteString getProposalIdBytes();

    RequestCase getRequestCase();

    String getWalletId();

    ByteString getWalletIdBytes();
}
