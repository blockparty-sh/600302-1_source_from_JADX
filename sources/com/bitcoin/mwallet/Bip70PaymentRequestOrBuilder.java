package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface Bip70PaymentRequestOrBuilder extends MessageLiteOrBuilder {
    String getBip70Url();

    ByteString getBip70UrlBytes();
}
