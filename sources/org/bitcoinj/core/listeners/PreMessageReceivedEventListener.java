package org.bitcoinj.core.listeners;

import org.bitcoinj.core.Message;
import org.bitcoinj.core.Peer;

public interface PreMessageReceivedEventListener {
    Message onPreMessageReceived(Peer peer, Message message);
}
