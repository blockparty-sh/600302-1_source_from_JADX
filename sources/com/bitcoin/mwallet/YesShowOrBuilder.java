package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface YesShowOrBuilder extends MessageLiteOrBuilder {
    String getSignature();

    ByteString getSignatureBytes();
}
