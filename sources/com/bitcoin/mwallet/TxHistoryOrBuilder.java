package com.bitcoin.mwallet;

import com.bitcoin.mwallet.TxHistory.TxType;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface TxHistoryOrBuilder extends MessageLiteOrBuilder {
    boolean getConfirmed();

    long getFee();

    String getLegacyAddress();

    ByteString getLegacyAddressBytes();

    String getNote();

    ByteString getNoteBytes();

    long getSatoshis();

    long getTimestamp();

    Token getToken();

    String getTxId();

    ByteString getTxIdBytes();

    TxType getTxType();

    int getTxTypeValue();

    String getWalletId();

    ByteString getWalletIdBytes();

    boolean hasToken();
}
