package org.bitcoinj.core;

public class GetAddrMessage extends EmptyMessage {
    public GetAddrMessage(NetworkParameters networkParameters) {
        super(networkParameters);
    }
}
