package com.bitcoin.mwallet;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface SubscribeRequestOrBuilder extends MessageLiteOrBuilder {
    SubscriptionRequest getRequest(int i);

    int getRequestCount();

    List<SubscriptionRequest> getRequestList();
}
