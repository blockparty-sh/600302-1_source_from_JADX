package com.bitcoin.mwallet;

import com.bitcoin.mwallet.RegisterRegionRequest.RegionRegistration;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RegisterRegionRequestOrBuilder extends MessageLiteOrBuilder {
    RegionRegistration getRegistration(int i);

    int getRegistrationCount();

    List<RegionRegistration> getRegistrationList();

    String getUserLanguage();

    ByteString getUserLanguageBytes();
}
