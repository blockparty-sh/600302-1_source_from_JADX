package com.bitcoin.mwallet;

import com.bitcoin.mwallet.GetUtxosResponse.ResponseCase;
import com.bitcoin.mwallet.GetUtxosResponse.WalletUtxosList;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GetUtxosResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ResponseCase getResponseCase();

    WalletUtxosList getUtxos();
}
