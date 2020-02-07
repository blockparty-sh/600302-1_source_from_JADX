package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface CreateMultiSigProposalRequestOrBuilder extends MessageLiteOrBuilder {
    String getCopayerId();

    ByteString getCopayerIdBytes();

    ProposalInput getInputs(int i);

    int getInputsCount();

    List<ProposalInput> getInputsList();

    String getMessage();

    ByteString getMessageBytes();

    ProposalOutput getOutputs(int i);

    int getOutputsCount();

    List<ProposalOutput> getOutputsList();

    String getWalletId();

    ByteString getWalletIdBytes();
}
