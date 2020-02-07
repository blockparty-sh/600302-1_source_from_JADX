package com.bitcoin.mwallet;

import com.bitcoin.mwallet.CreateUserResponse.ResponseCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CreateUserResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ResponseCase getResponseCase();

    String getUserId();

    ByteString getUserIdBytes();
}
