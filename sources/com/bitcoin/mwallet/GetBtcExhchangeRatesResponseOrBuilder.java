package com.bitcoin.mwallet;

import com.bitcoin.mwallet.GetBtcExhchangeRatesResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GetBtcExhchangeRatesResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ExchangeRates getRates();

    ResponseCase getResponseCase();
}
