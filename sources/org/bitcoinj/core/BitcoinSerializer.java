package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.bitcoinj.core.listeners.FeeFilterMessage;
import org.bitcoinj.core.listeners.SendHeadersMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitcoinSerializer extends MessageSerializer {
    private static final int COMMAND_LEN = 12;
    private static final Logger log = LoggerFactory.getLogger(BitcoinSerializer.class);
    private static final Map<Class<? extends Message>, String> names = new HashMap();
    private final NetworkParameters params;
    private final boolean parseRetain;

    public static class BitcoinPacketHeader {
        public static final int HEADER_LENGTH = 20;
        public final byte[] checksum;
        public final String command;
        public final byte[] header = new byte[20];
        public final int size;

        public BitcoinPacketHeader(ByteBuffer byteBuffer) throws ProtocolException, BufferUnderflowException {
            byte[] bArr = this.header;
            byteBuffer.get(bArr, 0, bArr.length);
            int i = 0;
            while (this.header[i] != 0 && i < 12) {
                i++;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(this.header, 0, bArr2, 0, i);
            this.command = C3447Utils.toString(bArr2, "US-ASCII");
            this.size = (int) C3447Utils.readUint32(this.header, 12);
            int i2 = this.size;
            if (i2 > 33554432 || i2 < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Message size too large: ");
                sb.append(this.size);
                throw new ProtocolException(sb.toString());
            }
            this.checksum = new byte[4];
            System.arraycopy(this.header, 16, this.checksum, 0, 4);
        }
    }

    static {
        names.put(VersionMessage.class, "version");
        names.put(InventoryMessage.class, "inv");
        names.put(Block.class, "block");
        names.put(GetDataMessage.class, "getdata");
        names.put(Transaction.class, "tx");
        names.put(AddressMessage.class, "addr");
        names.put(Ping.class, "ping");
        names.put(Pong.class, "pong");
        names.put(VersionAck.class, "verack");
        names.put(GetBlocksMessage.class, "getblocks");
        names.put(GetHeadersMessage.class, "getheaders");
        names.put(GetAddrMessage.class, "getaddr");
        names.put(HeadersMessage.class, "headers");
        names.put(BloomFilter.class, "filterload");
        names.put(FilteredBlock.class, "merkleblock");
        names.put(NotFoundMessage.class, "notfound");
        names.put(MemoryPoolMessage.class, "mempool");
        names.put(RejectMessage.class, "reject");
        names.put(GetUTXOsMessage.class, "getutxos");
        names.put(UTXOsMessage.class, "utxos");
    }

    public BitcoinSerializer(NetworkParameters networkParameters, boolean z) {
        this.params = networkParameters;
        this.parseRetain = z;
    }

    public void serialize(String str, byte[] bArr, OutputStream outputStream) throws IOException {
        byte[] bArr2 = new byte[24];
        C3447Utils.uint32ToByteArrayBE(this.params.getPacketMagic(), bArr2, 0);
        int i = 0;
        while (i < str.length() && i < 12) {
            bArr2[i + 4] = (byte) (str.codePointAt(i) & 255);
            i++;
        }
        C3447Utils.uint32ToByteArrayLE((long) bArr.length, bArr2, 16);
        System.arraycopy(Sha256Hash.hashTwice(bArr), 0, bArr2, 20, 4);
        outputStream.write(bArr2);
        outputStream.write(bArr);
        if (log.isDebugEnabled()) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append(C3447Utils.HEX.encode(bArr2));
            sb.append(C3447Utils.HEX.encode(bArr));
            logger.debug("Sending {} message: {}", (Object) str, (Object) sb.toString());
        }
    }

    public void serialize(Message message, OutputStream outputStream) throws IOException {
        String str = (String) names.get(message.getClass());
        if (str != null) {
            serialize(str, message.bitcoinSerialize(), outputStream);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("BitcoinSerializer doesn't currently know how to serialize ");
        sb.append(message.getClass());
        throw new Error(sb.toString());
    }

    public Message deserialize(ByteBuffer byteBuffer) throws ProtocolException, IOException {
        seekPastMagicBytes(byteBuffer);
        return deserializePayload(new BitcoinPacketHeader(byteBuffer), byteBuffer);
    }

    public BitcoinPacketHeader deserializeHeader(ByteBuffer byteBuffer) throws ProtocolException, IOException {
        return new BitcoinPacketHeader(byteBuffer);
    }

    public Message deserializePayload(BitcoinPacketHeader bitcoinPacketHeader, ByteBuffer byteBuffer) throws ProtocolException, BufferUnderflowException {
        byte[] bArr = new byte[bitcoinPacketHeader.size];
        byteBuffer.get(bArr, 0, bitcoinPacketHeader.size);
        byte[] hashTwice = Sha256Hash.hashTwice(bArr);
        if (bitcoinPacketHeader.checksum[0] == hashTwice[0] && bitcoinPacketHeader.checksum[1] == hashTwice[1] && bitcoinPacketHeader.checksum[2] == hashTwice[2] && bitcoinPacketHeader.checksum[3] == hashTwice[3]) {
            if (log.isDebugEnabled()) {
                log.debug("Received {} byte '{}' message: {}", Integer.valueOf(bitcoinPacketHeader.size), bitcoinPacketHeader.command, C3447Utils.HEX.encode(bArr));
            }
            try {
                return makeMessage(bitcoinPacketHeader.command, bitcoinPacketHeader.size, bArr, hashTwice, bitcoinPacketHeader.checksum);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error deserializing message ");
                sb.append(C3447Utils.HEX.encode(bArr));
                sb.append("\n");
                throw new ProtocolException(sb.toString(), e);
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Checksum failed to verify, actual ");
            sb2.append(C3447Utils.HEX.encode(hashTwice));
            sb2.append(" vs ");
            sb2.append(C3447Utils.HEX.encode(bitcoinPacketHeader.checksum));
            throw new ProtocolException(sb2.toString());
        }
    }

    private Message makeMessage(String str, int i, byte[] bArr, byte[] bArr2, byte[] bArr3) throws ProtocolException {
        Message message;
        if (str.equals("version")) {
            return new VersionMessage(this.params, bArr);
        }
        if (str.equals("inv")) {
            message = makeInventoryMessage(bArr, i);
        } else if (str.equals("block")) {
            message = makeBlock(bArr, i);
        } else if (str.equals("merkleblock")) {
            message = makeFilteredBlock(bArr);
        } else if (str.equals("getdata")) {
            message = new GetDataMessage(this.params, bArr, this, i);
        } else if (str.equals("getblocks")) {
            message = new GetBlocksMessage(this.params, bArr);
        } else if (str.equals("getheaders")) {
            message = new GetHeadersMessage(this.params, bArr);
        } else if (str.equals("tx")) {
            message = makeTransaction(bArr, 0, i, bArr2);
        } else if (str.equals("addr")) {
            message = makeAddressMessage(bArr, i);
        } else if (str.equals("ping")) {
            message = new Ping(this.params, bArr);
        } else if (str.equals("pong")) {
            message = new Pong(this.params, bArr);
        } else if (str.equals("verack")) {
            return new VersionAck(this.params, bArr);
        } else {
            if (str.equals("headers")) {
                return new HeadersMessage(this.params, bArr);
            }
            if (str.equals("alert")) {
                return makeAlertMessage(bArr);
            }
            if (str.equals("filterload")) {
                return makeBloomFilter(bArr);
            }
            if (str.equals("notfound")) {
                return new NotFoundMessage(this.params, bArr);
            }
            if (str.equals("mempool")) {
                return new MemoryPoolMessage();
            }
            if (str.equals("reject")) {
                return new RejectMessage(this.params, bArr);
            }
            if (str.equals("utxos")) {
                return new UTXOsMessage(this.params, bArr);
            }
            if (str.equals("getutxos")) {
                return new GetUTXOsMessage(this.params, bArr);
            }
            if (str.equals("sendheaders")) {
                return new SendHeadersMessage(this.params);
            }
            if (str.equals("feefilter")) {
                return new FeeFilterMessage(this.params);
            }
            log.warn("No support for deserializing message with name {}", (Object) str);
            return new UnknownMessage(this.params, str, bArr);
        }
        return message;
    }

    public NetworkParameters getParameters() {
        return this.params;
    }

    public AddressMessage makeAddressMessage(byte[] bArr, int i) throws ProtocolException {
        return new AddressMessage(this.params, bArr, this, i);
    }

    public Message makeAlertMessage(byte[] bArr) throws ProtocolException {
        return new AlertMessage(this.params, bArr);
    }

    public Block makeBlock(byte[] bArr, int i, int i2) throws ProtocolException {
        Block block = new Block(this.params, bArr, i, this, i2);
        return block;
    }

    public Message makeBloomFilter(byte[] bArr) throws ProtocolException {
        return new BloomFilter(this.params, bArr);
    }

    public FilteredBlock makeFilteredBlock(byte[] bArr) throws ProtocolException {
        return new FilteredBlock(this.params, bArr);
    }

    public InventoryMessage makeInventoryMessage(byte[] bArr, int i) throws ProtocolException {
        return new InventoryMessage(this.params, bArr, this, i);
    }

    public Transaction makeTransaction(byte[] bArr, int i, int i2, byte[] bArr2) throws ProtocolException {
        Transaction transaction = new Transaction(this.params, bArr, i, null, this, i2);
        if (bArr2 != null) {
            transaction.setHash(Sha256Hash.wrapReversed(bArr2));
        }
        return transaction;
    }

    public void seekPastMagicBytes(ByteBuffer byteBuffer) throws BufferUnderflowException {
        while (true) {
            int i = 3;
            while (true) {
                if (byteBuffer.get() == ((byte) ((int) (255 & (this.params.getPacketMagic() >>> (i * 8)))))) {
                    i--;
                    if (i < 0) {
                        return;
                    }
                }
            }
        }
    }

    public boolean isParseRetainMode() {
        return this.parseRetain;
    }
}
