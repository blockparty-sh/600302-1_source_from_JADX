package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RequestSignatureOrBuilder extends MessageLiteOrBuilder {
    ByteString getRequestXPubKey();

    ByteString getSignature();
}
