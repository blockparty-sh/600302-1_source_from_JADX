package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.bitcoinj.core.BitcoinSerializer.BitcoinPacketHeader;

class DummySerializer extends MessageSerializer {
    public static final DummySerializer DEFAULT = new DummySerializer();
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Dummy serializer cannot serialize/deserialize objects as it does not know which network they belong to.";

    public boolean isParseRetainMode() {
        return false;
    }

    public Message deserialize(ByteBuffer byteBuffer) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public BitcoinPacketHeader deserializeHeader(ByteBuffer byteBuffer) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public Message deserializePayload(BitcoinPacketHeader bitcoinPacketHeader, ByteBuffer byteBuffer) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public AddressMessage makeAddressMessage(byte[] bArr, int i) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public Message makeAlertMessage(byte[] bArr) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public Block makeBlock(byte[] bArr, int i, int i2) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public Message makeBloomFilter(byte[] bArr) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public FilteredBlock makeFilteredBlock(byte[] bArr) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public InventoryMessage makeInventoryMessage(byte[] bArr, int i) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public Transaction makeTransaction(byte[] bArr, int i, int i2, byte[] bArr2) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public void seekPastMagicBytes(ByteBuffer byteBuffer) throws BufferUnderflowException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public void serialize(String str, byte[] bArr, OutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }

    public void serialize(Message message, OutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException(DEFAULT_EXCEPTION_MESSAGE);
    }
}
