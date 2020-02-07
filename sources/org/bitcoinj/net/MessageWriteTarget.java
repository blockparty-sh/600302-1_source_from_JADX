package org.bitcoinj.net;

import java.io.IOException;

public interface MessageWriteTarget {
    void closeConnection();

    void writeBytes(byte[] bArr) throws IOException;
}
