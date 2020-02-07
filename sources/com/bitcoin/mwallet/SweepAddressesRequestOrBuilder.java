package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface SweepAddressesRequestOrBuilder extends MessageLiteOrBuilder {
    String getAddresses(int i);

    ByteString getAddressesBytes(int i);

    int getAddressesCount();

    List<String> getAddressesList();

    Coin getCoin();

    int getCoinValue();
}
