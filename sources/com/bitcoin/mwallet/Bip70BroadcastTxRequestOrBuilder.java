package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface Bip70BroadcastTxRequestOrBuilder extends MessageLiteOrBuilder {
    String getBip70Url();

    ByteString getBip70UrlBytes();

    String getDepositAddress();

    ByteString getDepositAddressBytes();

    ByteString getTxRaw();

    String getWalletId();

    ByteString getWalletIdBytes();
}
