package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface StreamRequestOrBuilder extends MessageLiteOrBuilder {
    String getWalletId(int i);

    ByteString getWalletIdBytes(int i);

    int getWalletIdCount();

    List<String> getWalletIdList();
}
