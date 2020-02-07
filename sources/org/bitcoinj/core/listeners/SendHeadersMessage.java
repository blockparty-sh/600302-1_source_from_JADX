package org.bitcoinj.core.listeners;

import org.bitcoinj.core.EmptyMessage;
import org.bitcoinj.core.NetworkParameters;

public class SendHeadersMessage extends EmptyMessage {
    public SendHeadersMessage(NetworkParameters networkParameters) {
        super(networkParameters);
    }
}
