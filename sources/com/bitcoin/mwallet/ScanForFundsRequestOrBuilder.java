package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ScanForFundsRequestOrBuilder extends MessageLiteOrBuilder {
    String getWalletId();

    ByteString getWalletIdBytes();
}
