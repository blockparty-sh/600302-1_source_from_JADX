package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ProposalOutputOrBuilder extends MessageLiteOrBuilder {
    long getAmount();

    String getToAddress();

    ByteString getToAddressBytes();
}
