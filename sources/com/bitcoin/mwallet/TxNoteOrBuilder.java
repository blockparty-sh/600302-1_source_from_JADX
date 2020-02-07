package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface TxNoteOrBuilder extends MessageLiteOrBuilder {
    String getNote();

    ByteString getNoteBytes();

    String getTxId();

    ByteString getTxIdBytes();
}
