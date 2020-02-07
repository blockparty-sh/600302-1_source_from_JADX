package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CreateUserRequestOrBuilder extends MessageLiteOrBuilder {
    String getRequestXPubKey();

    ByteString getRequestXPubKeyBytes();

    RequestSignature getSignature();

    boolean hasSignature();
}
