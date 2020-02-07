package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SweepUtxoOrBuilder extends MessageLiteOrBuilder {
    String getAmount();

    ByteString getAmountBytes();

    String getScriptPubkey();

    ByteString getScriptPubkeyBytes();

    String getTxId();

    ByteString getTxIdBytes();

    int getUtxoIndex();
}
