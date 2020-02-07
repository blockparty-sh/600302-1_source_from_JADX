package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ResponseErrorOrBuilder extends MessageLiteOrBuilder {
    String getErrorMessage();

    ByteString getErrorMessageBytes();

    ResponseErrorType getType();

    int getTypeValue();
}
