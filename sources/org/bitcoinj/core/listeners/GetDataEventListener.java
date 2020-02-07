package org.bitcoinj.core.listeners;

import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.GetDataMessage;
import org.bitcoinj.core.Message;
import org.bitcoinj.core.Peer;

public interface GetDataEventListener {
    @Nullable
    List<Message> getData(Peer peer, GetDataMessage getDataMessage);
}
