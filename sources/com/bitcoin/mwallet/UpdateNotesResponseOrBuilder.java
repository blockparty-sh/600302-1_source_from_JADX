package com.bitcoin.mwallet;

import com.bitcoin.mwallet.UpdateNotesResponse.ResponseCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UpdateNotesResponseOrBuilder extends MessageLiteOrBuilder {
    ResponseError getError();

    ResponseCase getResponseCase();
}
