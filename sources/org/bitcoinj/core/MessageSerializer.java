package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.bitcoinj.core.BitcoinSerializer.BitcoinPacketHeader;

public abstract class MessageSerializer {
    public abstract Message deserialize(ByteBuffer byteBuffer) throws ProtocolException, IOException, UnsupportedOperationException;

    public abstract BitcoinPacketHeader deserializeHeader(ByteBuffer byteBuffer) throws ProtocolException, IOException, UnsupportedOperationException;

    public abstract Message deserializePayload(BitcoinPacketHeader bitcoinPacketHeader, ByteBuffer byteBuffer) throws ProtocolException, BufferUnderflowException, UnsupportedOperationException;

    public abstract boolean isParseRetainMode();

    public abstract AddressMessage makeAddressMessage(byte[] bArr, int i) throws ProtocolException, UnsupportedOperationException;

    public abstract Message makeAlertMessage(byte[] bArr) throws ProtocolException, UnsupportedOperationException;

    public abstract Block makeBlock(byte[] bArr, int i, int i2) throws ProtocolException, UnsupportedOperationException;

    public abstract Message makeBloomFilter(byte[] bArr) throws ProtocolException, UnsupportedOperationException;

    public abstract FilteredBlock makeFilteredBlock(byte[] bArr) throws ProtocolException, UnsupportedOperationException;

    public abstract InventoryMessage makeInventoryMessage(byte[] bArr, int i) throws ProtocolException, UnsupportedOperationException;

    public abstract Transaction makeTransaction(byte[] bArr, int i, int i2, byte[] bArr2) throws ProtocolException, UnsupportedOperationException;

    public abstract void seekPastMagicBytes(ByteBuffer byteBuffer) throws BufferUnderflowException;

    public abstract void serialize(String str, byte[] bArr, OutputStream outputStream) throws IOException, UnsupportedOperationException;

    public abstract void serialize(Message message, OutputStream outputStream) throws IOException, UnsupportedOperationException;

    public final Block makeBlock(byte[] bArr) throws ProtocolException {
        return makeBlock(bArr, 0, bArr.length);
    }

    public final Block makeBlock(byte[] bArr, int i) throws ProtocolException {
        return makeBlock(bArr, 0, i);
    }

    public final Transaction makeTransaction(byte[] bArr) throws ProtocolException, UnsupportedOperationException {
        return makeTransaction(bArr, 0);
    }

    public final Transaction makeTransaction(byte[] bArr, int i) throws ProtocolException {
        return makeTransaction(bArr, i, bArr.length, null);
    }
}
