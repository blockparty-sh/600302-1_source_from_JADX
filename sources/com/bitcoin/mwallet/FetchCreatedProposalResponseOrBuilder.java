package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface FetchCreatedProposalResponseOrBuilder extends MessageLiteOrBuilder {
    String getCreatorCopayerId();

    ByteString getCreatorCopayerIdBytes();

    Utxo getInputs(int i);

    int getInputsCount();

    List<Utxo> getInputsList();

    String getMessage();

    ByteString getMessageBytes();

    ProposalOutput getOutputs(int i);

    int getOutputsCount();

    List<ProposalOutput> getOutputsList();

    String getProposalId();

    ByteString getProposalIdBytes();

    long getUnixTimestamp();

    String getWalletId();

    ByteString getWalletIdBytes();
}
