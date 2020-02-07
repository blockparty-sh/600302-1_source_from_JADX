package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface MigrateSingleSigWalletRequestOrBuilder extends MessageLiteOrBuilder {
    Coin getCoin();

    int getCoinValue();

    boolean getMultiSig();

    Network getNetwork();

    int getNetworkValue();

    String getOldWalletId();

    ByteString getOldWalletIdBytes();

    String getSigningPubKey();

    ByteString getSigningPubKeyBytes();

    String getXPubKey();

    ByteString getXPubKeyBytes();
}
