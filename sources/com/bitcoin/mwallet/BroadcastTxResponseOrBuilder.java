package com.bitcoin.mwallet;

import com.bitcoin.mwallet.BroadcastTxResponse.ResponseCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BroadcastTxResponseOrBuilder extends MessageLiteOrBuilder {
    BroadcastTxError getBroadcastError();

    int getBroadcastErrorValue();

    ResponseError getError();

    ResponseCase getResponseCase();

    String getTxId();

    ByteString getTxIdBytes();
}
