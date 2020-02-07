package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GetOldTxNotesRequestOrBuilder extends MessageLiteOrBuilder {
    String getCopayerId();

    ByteString getCopayerIdBytes();

    String getWalletId();

    ByteString getWalletIdBytes();
}
