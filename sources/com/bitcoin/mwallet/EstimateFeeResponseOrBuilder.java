package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface EstimateFeeResponseOrBuilder extends MessageLiteOrBuilder {
    FeeEstimate getEstimate(int i);

    int getEstimateCount();

    List<FeeEstimate> getEstimateList();
}
