package org.bitcoinj.net;

import java.nio.ByteBuffer;

public interface StreamConnection {
    void connectionClosed();

    void connectionOpened();

    int getMaxMessageSize();

    int receiveBytes(ByteBuffer byteBuffer) throws Exception;

    void setWriteTarget(MessageWriteTarget messageWriteTarget);
}
