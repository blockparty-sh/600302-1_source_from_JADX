package com.bitcoin.mwallet;

import com.bitcoin.mwallet.StreamEvent.EventCase;
import com.google.protobuf.MessageLiteOrBuilder;

public interface StreamEventOrBuilder extends MessageLiteOrBuilder {
    EventCase getEventCase();

    TxHistory getTxHistory();

    WalletUtxos getUtxos();
}
