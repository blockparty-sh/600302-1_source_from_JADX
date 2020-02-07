package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface Bip70OutputOrBuilder extends MessageLiteOrBuilder {
    String getAddress();

    ByteString getAddressBytes();

    long getAmount();

    String getScriptHex();

    ByteString getScriptHexBytes();
}
