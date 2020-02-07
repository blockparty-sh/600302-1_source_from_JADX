package org.bitcoinj.core;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Objects;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import javax.annotation.Nullable;
import org.bitcoinj.core.NetworkParameters.ProtocolVersion;

public class VersionMessage extends Message {
    public static final String BITCOINJ_VERSION = "0.14.5.2";
    public static final String LIBRARY_SUBVER = "/bitcoinj.cash:0.14.5.2/";
    public static final int NODE_BITCOIN_CASH = 32;
    public static final int NODE_GETUTXOS = 2;
    public static final int NODE_NETWORK = 1;
    public long bestHeight;
    public int clientVersion;
    public long localServices;
    public PeerAddress myAddr;
    public boolean relayTxesBeforeFilter;
    public String subVer;
    public PeerAddress theirAddr;
    public long time;

    public VersionMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public VersionMessage(NetworkParameters networkParameters, int i) {
        super(networkParameters);
        this.clientVersion = networkParameters.getProtocolVersionNum(ProtocolVersion.CURRENT);
        this.localServices = 0;
        this.time = System.currentTimeMillis() / 1000;
        try {
            byte[] bArr = {Byte.MAX_VALUE, 0, 0, 1};
            this.myAddr = new PeerAddress(InetAddress.getByAddress(bArr), networkParameters.getPort(), 0);
            this.theirAddr = new PeerAddress(InetAddress.getByAddress(bArr), networkParameters.getPort(), 0);
            this.subVer = LIBRARY_SUBVER;
            this.bestHeight = (long) i;
            this.relayTxesBeforeFilter = true;
            this.length = 85;
            if (this.protocolVersion > 31402) {
                this.length += 8;
            }
            this.length += VarInt.sizeOf((long) this.subVer.length()) + this.subVer.length();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.clientVersion = (int) readUint32();
        this.localServices = readUint64().longValue();
        this.time = readUint64().longValue();
        this.myAddr = new PeerAddress(this.params, this.payload, this.cursor, 0);
        this.cursor += this.myAddr.getMessageSize();
        this.theirAddr = new PeerAddress(this.params, this.payload, this.cursor, 0);
        this.cursor += this.theirAddr.getMessageSize();
        readUint64();
        try {
            this.subVer = "";
            this.bestHeight = 0;
            boolean z = true;
            this.relayTxesBeforeFilter = true;
            if (hasMoreBytes()) {
                this.subVer = readStr();
                if (hasMoreBytes()) {
                    this.bestHeight = readUint32();
                    if (hasMoreBytes()) {
                        if (readBytes(1)[0] == 0) {
                            z = false;
                        }
                        this.relayTxesBeforeFilter = z;
                        this.length = this.cursor - this.offset;
                    }
                }
            }
        } finally {
            this.length = this.cursor - this.offset;
        }
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        C3447Utils.uint32ToByteStreamLE((long) this.clientVersion, outputStream);
        C3447Utils.uint32ToByteStreamLE(this.localServices, outputStream);
        C3447Utils.uint32ToByteStreamLE(this.localServices >> 32, outputStream);
        C3447Utils.uint32ToByteStreamLE(this.time, outputStream);
        C3447Utils.uint32ToByteStreamLE(this.time >> 32, outputStream);
        try {
            this.myAddr.bitcoinSerialize(outputStream);
            this.theirAddr.bitcoinSerialize(outputStream);
            C3447Utils.uint32ToByteStreamLE(0, outputStream);
            C3447Utils.uint32ToByteStreamLE(0, outputStream);
            byte[] bytes = this.subVer.getBytes(Utf8Charset.NAME);
            outputStream.write(new VarInt((long) bytes.length).encode());
            outputStream.write(bytes);
            C3447Utils.uint32ToByteStreamLE(this.bestHeight, outputStream);
            outputStream.write(this.relayTxesBeforeFilter ? 1 : 0);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public boolean hasBlockChain() {
        return (this.localServices & 1) == 1;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VersionMessage versionMessage = (VersionMessage) obj;
        if (!(versionMessage.bestHeight == this.bestHeight && versionMessage.clientVersion == this.clientVersion && versionMessage.localServices == this.localServices && versionMessage.time == this.time && versionMessage.subVer.equals(this.subVer) && versionMessage.myAddr.equals(this.myAddr) && versionMessage.theirAddr.equals(this.theirAddr) && versionMessage.relayTxesBeforeFilter == this.relayTxesBeforeFilter)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.bestHeight), Integer.valueOf(this.clientVersion), Long.valueOf(this.localServices), Long.valueOf(this.time), this.subVer, this.myAddr, this.theirAddr, Boolean.valueOf(this.relayTxesBeforeFilter));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "\n";
        sb.append(str);
        sb.append("client version: ");
        sb.append(this.clientVersion);
        sb.append(str);
        sb.append("local services: ");
        sb.append(this.localServices);
        sb.append(str);
        sb.append("time:           ");
        sb.append(this.time);
        sb.append(str);
        sb.append("my addr:        ");
        sb.append(this.myAddr);
        sb.append(str);
        sb.append("their addr:     ");
        sb.append(this.theirAddr);
        sb.append(str);
        sb.append("sub version:    ");
        sb.append(this.subVer);
        sb.append(str);
        sb.append("best height:    ");
        sb.append(this.bestHeight);
        sb.append(str);
        sb.append("delay tx relay: ");
        sb.append(!this.relayTxesBeforeFilter);
        sb.append(str);
        return sb.toString();
    }

    public VersionMessage duplicate() {
        VersionMessage versionMessage = new VersionMessage(this.params, (int) this.bestHeight);
        versionMessage.clientVersion = this.clientVersion;
        versionMessage.localServices = this.localServices;
        versionMessage.time = this.time;
        versionMessage.myAddr = this.myAddr;
        versionMessage.theirAddr = this.theirAddr;
        versionMessage.subVer = this.subVer;
        versionMessage.relayTxesBeforeFilter = this.relayTxesBeforeFilter;
        return versionMessage;
    }

    public void appendToSubVer(String str, String str2, @Nullable String str3) {
        checkSubVerComponent(str);
        checkSubVerComponent(str2);
        if (str3 != null) {
            checkSubVerComponent(str3);
            this.subVer = this.subVer.concat(String.format(Locale.US, "%s:%s(%s)/", new Object[]{str, str2, str3}));
            return;
        }
        this.subVer = this.subVer.concat(String.format(Locale.US, "%s:%s/", new Object[]{str, str2}));
    }

    private static void checkSubVerComponent(String str) {
        if (str.contains("/") || str.contains("(") || str.contains(")")) {
            throw new IllegalArgumentException("name contains invalid characters");
        }
    }

    public boolean isPingPongSupported() {
        return this.clientVersion >= this.params.getProtocolVersionNum(ProtocolVersion.PONG);
    }

    public boolean isBloomFilteringSupported() {
        return this.clientVersion >= this.params.getProtocolVersionNum(ProtocolVersion.BLOOM_FILTER);
    }

    public boolean isGetUTXOsSupported() {
        return this.clientVersion >= 70002 && (this.localServices & 2) == 2;
    }
}
