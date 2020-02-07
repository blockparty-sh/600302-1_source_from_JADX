package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface JoinMultiSigWalletRequestOrBuilder extends MessageLiteOrBuilder {
    String getWalletId();

    ByteString getWalletIdBytes();

    String getXPubKey();

    ByteString getXPubKeyBytes();

    String getYourName();

    ByteString getYourNameBytes();
}
