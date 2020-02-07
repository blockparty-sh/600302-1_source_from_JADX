package com.bitcoin.mwallet;

import com.bitcoin.mwallet.Bip70BroadcastTxResponse.ResponseCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface Bip70BroadcastTxResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    String getMemo();

    ByteString getMemoBytes();

    ResponseCase getResponseCase();

    String getTxId();

    ByteString getTxIdBytes();
}
