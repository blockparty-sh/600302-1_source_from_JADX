package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RecoverSingleSigWalletRequestOrBuilder extends MessageLiteOrBuilder {
    Coin getCoin();

    int getCoinValue();

    String getName();

    ByteString getNameBytes();

    Network getNetwork();

    int getNetworkValue();

    String getSigningPubKey();

    ByteString getSigningPubKeyBytes();

    String getXPubKey();

    ByteString getXPubKeyBytes();
}
