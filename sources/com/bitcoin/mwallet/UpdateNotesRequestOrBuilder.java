package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UpdateNotesRequestOrBuilder extends MessageLiteOrBuilder {
    String getCopayerId();

    ByteString getCopayerIdBytes();

    TxNote getTxNote(int i);

    int getTxNoteCount();

    List<TxNote> getTxNoteList();

    String getWalletId();

    ByteString getWalletIdBytes();
}
