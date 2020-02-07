package com.bitcoin.mwallet;

import com.bitcoin.mwallet.SubscriptionRequest.IdCase;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SubscriptionRequestOrBuilder extends MessageLiteOrBuilder {
    String getCopayerId();

    ByteString getCopayerIdBytes();

    IdCase getIdCase();

    String getSingleSigWalletId();

    ByteString getSingleSigWalletIdBytes();

    String getToken();

    ByteString getTokenBytes();
}
