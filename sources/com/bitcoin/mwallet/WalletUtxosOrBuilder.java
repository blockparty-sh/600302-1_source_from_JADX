package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface WalletUtxosOrBuilder extends MessageLiteOrBuilder {
    Utxo getUtxo(int i);

    int getUtxoCount();

    List<Utxo> getUtxoList();

    String getWalletId();

    ByteString getWalletIdBytes();
}
