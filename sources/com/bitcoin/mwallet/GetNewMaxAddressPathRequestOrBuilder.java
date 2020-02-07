package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GetNewMaxAddressPathRequestOrBuilder extends MessageLiteOrBuilder {
    AddressPath getCurrentMaxPath();

    String getWalletId();

    ByteString getWalletIdBytes();

    boolean hasCurrentMaxPath();
}
