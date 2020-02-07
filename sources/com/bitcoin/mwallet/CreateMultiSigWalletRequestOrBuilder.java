package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface CreateMultiSigWalletRequestOrBuilder extends MessageLiteOrBuilder {
    Coin getCoin();

    int getCoinValue();

    String getName();

    ByteString getNameBytes();

    Network getNetwork();

    int getNetworkValue();

    int getNumCopayers();

    int getRequiredSignatures();

    String getXPubKey();

    ByteString getXPubKeyBytes();

    String getYourName();

    ByteString getYourNameBytes();
}
