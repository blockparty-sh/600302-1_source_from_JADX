package org.bitcoinj.core.listeners;

import org.bitcoinj.core.EmptyMessage;
import org.bitcoinj.core.NetworkParameters;

public class FeeFilterMessage extends EmptyMessage {
    public FeeFilterMessage(NetworkParameters networkParameters) {
        super(networkParameters);
    }
}
