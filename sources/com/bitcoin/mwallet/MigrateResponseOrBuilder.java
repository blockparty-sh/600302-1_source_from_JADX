package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface MigrateResponseOrBuilder extends MessageLiteOrBuilder {
    String getCurrentWalletId();

    ByteString getCurrentWalletIdBytes();
}
