package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RecoverResponseOrBuilder extends MessageLiteOrBuilder {
    String getName();

    ByteString getNameBytes();

    boolean getScanning();

    String getWalletId();

    ByteString getWalletIdBytes();
}
