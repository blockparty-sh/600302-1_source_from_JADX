package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface EstimateNetworkFeeRequestOrBuilder extends MessageLiteOrBuilder {
    Coin getCoin(int i);

    int getCoinCount();

    List<Coin> getCoinList();

    int getCoinValue(int i);

    List<Integer> getCoinValueList();
}
