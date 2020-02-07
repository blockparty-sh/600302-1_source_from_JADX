package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CreateProposalResponseOrBuilder extends MessageLiteOrBuilder {
    String getProposalId();

    ByteString getProposalIdBytes();

    String getWalletId();

    ByteString getWalletIdBytes();
}
