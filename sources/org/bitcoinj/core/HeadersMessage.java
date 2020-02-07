package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeadersMessage extends Message {
    public static final int MAX_HEADERS = 2000;
    private static final Logger log = LoggerFactory.getLogger(HeadersMessage.class);
    private List<Block> blockHeaders;

    public HeadersMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public HeadersMessage(NetworkParameters networkParameters, Block... blockArr) throws ProtocolException {
        super(networkParameters);
        this.blockHeaders = Arrays.asList(blockArr);
    }

    public HeadersMessage(NetworkParameters networkParameters, List<Block> list) throws ProtocolException {
        super(networkParameters);
        this.blockHeaders = list;
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        outputStream.write(new VarInt((long) this.blockHeaders.size()).encode());
        for (Block cloneAsHeader : this.blockHeaders) {
            cloneAsHeader.cloneAsHeader().bitcoinSerializeToStream(outputStream);
            outputStream.write(0);
        }
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        long readVarInt = readVarInt();
        if (readVarInt <= PeerGroup.DEFAULT_PING_INTERVAL_MSEC) {
            this.blockHeaders = new ArrayList();
            BitcoinSerializer serializer = this.params.getSerializer(true);
            int i = 0;
            while (((long) i) < readVarInt) {
                Block makeBlock = serializer.makeBlock(this.payload, this.cursor, Integer.MIN_VALUE);
                if (!makeBlock.hasTransactions()) {
                    this.cursor += makeBlock.optimalEncodingMessageSize;
                    this.blockHeaders.add(makeBlock);
                    i++;
                } else {
                    throw new ProtocolException("Block header does not end with a null byte");
                }
            }
            if (this.length == Integer.MIN_VALUE) {
                this.length = this.cursor - this.offset;
            }
            if (log.isDebugEnabled()) {
                for (int i2 = 0; ((long) i2) < readVarInt; i2++) {
                    log.debug(((Block) this.blockHeaders.get(i2)).toString());
                }
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Too many headers: got ");
        sb.append(readVarInt);
        sb.append(" which is larger than ");
        sb.append(MAX_HEADERS);
        throw new ProtocolException(sb.toString());
    }

    public List<Block> getBlockHeaders() {
        return this.blockHeaders;
    }
}
