package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ExchangeRatesOrBuilder extends MessageLiteOrBuilder {
    ExchangeRate getRates(int i);

    int getRatesCount();

    List<ExchangeRate> getRatesList();

    long getUpdatedEpochMillis();
}
