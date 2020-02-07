package com.bitcoin.mwallet;

import com.bitcoin.mwallet.EstimateNetworkFeeResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface EstimateNetworkFeeResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    EstimateFeeResponse getEstimate();

    ResponseCase getResponseCase();
}
