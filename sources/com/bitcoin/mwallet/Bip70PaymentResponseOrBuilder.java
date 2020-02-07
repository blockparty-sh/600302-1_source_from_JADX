package com.bitcoin.mwallet;

import com.bitcoin.mwallet.Bip70PaymentResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface Bip70PaymentResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    Bip70PaymentErrorStatus getErrorStatus();

    int getErrorStatusValue();

    Bip70PaymentInfo getInfo();

    ResponseCase getResponseCase();
}
