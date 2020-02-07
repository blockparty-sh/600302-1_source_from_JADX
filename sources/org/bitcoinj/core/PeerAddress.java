package org.bitcoinj.core;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.net.InetAddresses;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.bitcoinj.core.NetworkParameters.ProtocolVersion;
import org.bitcoinj.params.MainNetParams;

public class PeerAddress extends ChildMessage {
    static final int MESSAGE_SIZE = 30;
    private InetAddress addr;
    private String hostname;
    private int port;
    private BigInteger services;
    private long time;

    public PeerAddress(NetworkParameters networkParameters, byte[] bArr, int i, int i2) throws ProtocolException {
        super(networkParameters, bArr, i, i2);
    }

    public PeerAddress(NetworkParameters networkParameters, byte[] bArr, int i, int i2, Message message, MessageSerializer messageSerializer) throws ProtocolException {
        super(networkParameters, bArr, i, i2, message, messageSerializer, Integer.MIN_VALUE);
    }

    public PeerAddress(InetAddress inetAddress, int i, int i2) {
        this.addr = (InetAddress) Preconditions.checkNotNull(inetAddress);
        this.port = i;
        this.protocolVersion = i2;
        this.services = BigInteger.ZERO;
        this.length = i2 > 31402 ? 30 : 26;
    }

    public PeerAddress(InetAddress inetAddress, int i) {
        this(inetAddress, i, ProtocolVersion.CURRENT.getBitcoinProtocolVersion());
    }

    public PeerAddress(NetworkParameters networkParameters, InetAddress inetAddress, int i) {
        this(inetAddress, i, networkParameters.getProtocolVersionNum(ProtocolVersion.CURRENT));
    }

    public PeerAddress(InetAddress inetAddress) {
        this(inetAddress, MainNetParams.get().getPort());
    }

    public PeerAddress(NetworkParameters networkParameters, InetAddress inetAddress) {
        this(networkParameters, inetAddress, networkParameters.getPort());
    }

    public PeerAddress(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress.getAddress(), inetSocketAddress.getPort(), ProtocolVersion.CURRENT.getBitcoinProtocolVersion());
    }

    public PeerAddress(NetworkParameters networkParameters, InetSocketAddress inetSocketAddress) {
        this(networkParameters, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
    }

    public PeerAddress(String str, int i) {
        this.hostname = str;
        this.port = i;
        this.protocolVersion = ProtocolVersion.CURRENT.getBitcoinProtocolVersion();
        this.services = BigInteger.ZERO;
    }

    public PeerAddress(NetworkParameters networkParameters, String str, int i) {
        super(networkParameters);
        this.hostname = str;
        this.port = i;
        this.protocolVersion = networkParameters.getProtocolVersionNum(ProtocolVersion.CURRENT);
        this.services = BigInteger.ZERO;
    }

    public static PeerAddress localhost(NetworkParameters networkParameters) {
        return new PeerAddress(networkParameters, InetAddresses.forString("127.0.0.1"), networkParameters.getPort());
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        if (this.protocolVersion >= 31402) {
            C3447Utils.uint32ToByteStreamLE((long) ((int) C3447Utils.currentTimeSeconds()), outputStream);
        }
        C3447Utils.uint64ToByteStreamLE(this.services, outputStream);
        byte[] address = this.addr.getAddress();
        if (address.length == 4) {
            byte[] bArr = new byte[16];
            System.arraycopy(address, 0, bArr, 12, 4);
            bArr[10] = -1;
            bArr[11] = -1;
            address = bArr;
        }
        outputStream.write(address);
        outputStream.write((byte) ((this.port >> 8) & 255));
        outputStream.write((byte) (this.port & 255));
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        if (this.protocolVersion > 31402) {
            this.time = readUint32();
        } else {
            this.time = -1;
        }
        this.services = readUint64();
        try {
            this.addr = InetAddress.getByAddress(readBytes(16));
            byte[] bArr = this.payload;
            int i = this.cursor;
            this.cursor = i + 1;
            int i2 = (bArr[i] & 255) << 8;
            byte[] bArr2 = this.payload;
            int i3 = this.cursor;
            this.cursor = i3 + 1;
            this.port = i2 | (bArr2[i3] & 255);
            this.length = this.protocolVersion > 31402 ? 30 : 26;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHostname() {
        return this.hostname;
    }

    public InetAddress getAddr() {
        return this.addr;
    }

    public InetSocketAddress getSocketAddress() {
        return new InetSocketAddress(getAddr(), getPort());
    }

    public void setAddr(InetAddress inetAddress) {
        unCache();
        this.addr = inetAddress;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        unCache();
        this.port = i;
    }

    public BigInteger getServices() {
        return this.services;
    }

    public void setServices(BigInteger bigInteger) {
        unCache();
        this.services = bigInteger;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        unCache();
        this.time = j;
    }

    public String toString() {
        String str = "]:";
        String str2 = "[";
        if (this.hostname != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(this.hostname);
            sb.append(str);
            sb.append(this.port);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(this.addr.getHostAddress());
        sb2.append(str);
        sb2.append(this.port);
        return sb2.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PeerAddress peerAddress = (PeerAddress) obj;
        if (!peerAddress.addr.equals(this.addr) || peerAddress.port != this.port || peerAddress.time != this.time || !peerAddress.services.equals(this.services)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.addr, Integer.valueOf(this.port), Long.valueOf(this.time), this.services);
    }

    public InetSocketAddress toSocketAddress() {
        String str = this.hostname;
        if (str != null) {
            return InetSocketAddress.createUnresolved(str, this.port);
        }
        return new InetSocketAddress(this.addr, this.port);
    }
}
