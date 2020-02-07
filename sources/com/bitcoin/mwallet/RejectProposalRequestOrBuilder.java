package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RejectProposalRequestOrBuilder extends MessageLiteOrBuilder {
    String getCopayerId();

    ByteString getCopayerIdBytes();

    String getProposalId();

    ByteString getProposalIdBytes();

    String getReason();

    ByteString getReasonBytes();

    String getWalletId();

    ByteString getWalletIdBytes();
}
