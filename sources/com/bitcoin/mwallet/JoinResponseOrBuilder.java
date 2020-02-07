package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface JoinResponseOrBuilder extends MessageLiteOrBuilder {
    boolean getCompleted();

    String getCopayerId();

    ByteString getCopayerIdBytes();

    String getOtherNames(int i);

    ByteString getOtherNamesBytes(int i);

    int getOtherNamesCount();

    List<String> getOtherNamesList();

    String getWalletName();

    ByteString getWalletNameBytes();
}
