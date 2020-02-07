package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ProposalInputOrBuilder extends MessageLiteOrBuilder {
    String getSignature();

    ByteString getSignatureBytes();

    Utxo getUtxo();

    boolean hasUtxo();
}
