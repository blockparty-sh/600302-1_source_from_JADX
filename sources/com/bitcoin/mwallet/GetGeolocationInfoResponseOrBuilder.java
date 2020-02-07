package com.bitcoin.mwallet;

import com.bitcoin.mwallet.GetGeolocationInfoResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface GetGeolocationInfoResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    LocationInfo getInfo();

    LocationError getLocationError();

    int getLocationErrorValue();

    ResponseCase getResponseCase();
}
