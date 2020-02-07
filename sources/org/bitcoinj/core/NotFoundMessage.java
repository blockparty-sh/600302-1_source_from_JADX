package org.bitcoinj.core;

import java.util.ArrayList;
import java.util.List;

public class NotFoundMessage extends InventoryMessage {
    public static int MIN_PROTOCOL_VERSION = 70001;

    public NotFoundMessage(NetworkParameters networkParameters) {
        super(networkParameters);
    }

    public NotFoundMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr);
    }

    public NotFoundMessage(NetworkParameters networkParameters, List<InventoryItem> list) {
        super(networkParameters);
        this.items = new ArrayList(list);
    }
}
