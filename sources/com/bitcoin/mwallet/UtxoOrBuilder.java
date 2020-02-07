package com.bitcoin.mwallet;

import com.bitcoin.mwallet.Utxo.UtxoAddress;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UtxoOrBuilder extends MessageLiteOrBuilder {
    UtxoAddress getAddress();

    int getOutputIndex();

    long getSatoshis();

    ByteString getScript();

    Token getToken();

    String getTxId();

    ByteString getTxIdBytes();

    boolean hasAddress();

    boolean hasToken();
}
