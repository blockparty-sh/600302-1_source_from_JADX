package org.bitcoinj.core;

import java.util.Date;
import java.util.HashSet;

public class AlertMessage extends Message {
    private static final long MAX_SET_SIZE = 100;
    private long cancel;
    private String comment;
    private byte[] content;
    private Date expiration;

    /* renamed from: id */
    private long f795id;
    private long maxVer;
    private long minVer;
    private long priority;
    private Date relayUntil;
    private String reserved;
    private byte[] signature;
    private String statusBar;
    private long version = 1;

    public AlertMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALERT: ");
        sb.append(getStatusBar());
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        int i = this.cursor;
        this.content = readByteArray();
        this.signature = readByteArray();
        this.cursor = i;
        readVarInt();
        this.version = readUint32();
        this.relayUntil = new Date(readUint64().longValue() * 1000);
        this.expiration = new Date(readUint64().longValue() * 1000);
        this.f795id = readUint32();
        this.cancel = readUint32();
        long readVarInt = readVarInt();
        if (readVarInt < 0 || readVarInt > MAX_SET_SIZE) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bad cancel set size: ");
            sb.append(readVarInt);
            throw new ProtocolException(sb.toString());
        }
        HashSet hashSet = new HashSet((int) readVarInt);
        for (long j = 0; j < readVarInt; j++) {
            hashSet.add(Long.valueOf(readUint32()));
        }
        this.minVer = readUint32();
        this.maxVer = readUint32();
        long readVarInt2 = readVarInt();
        if (readVarInt2 < 0 || readVarInt2 > MAX_SET_SIZE) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Bad subver set size: ");
            sb2.append(readVarInt2);
            throw new ProtocolException(sb2.toString());
        }
        HashSet hashSet2 = new HashSet((int) readVarInt2);
        for (long j2 = 0; j2 < readVarInt2; j2++) {
            hashSet2.add(readStr());
        }
        this.priority = readUint32();
        this.comment = readStr();
        this.statusBar = readStr();
        this.reserved = readStr();
        this.length = this.cursor - this.offset;
    }

    public boolean isSignatureValid() {
        return ECKey.verify(Sha256Hash.hashTwice(this.content), this.signature, this.params.getAlertSigningKey());
    }

    public Date getRelayUntil() {
        return this.relayUntil;
    }

    public void setRelayUntil(Date date) {
        this.relayUntil = date;
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Date date) {
        this.expiration = date;
    }

    public long getId() {
        return this.f795id;
    }

    public void setId(long j) {
        this.f795id = j;
    }

    public long getCancel() {
        return this.cancel;
    }

    public void setCancel(long j) {
        this.cancel = j;
    }

    public long getMinVer() {
        return this.minVer;
    }

    public void setMinVer(long j) {
        this.minVer = j;
    }

    public long getMaxVer() {
        return this.maxVer;
    }

    public void setMaxVer(long j) {
        this.maxVer = j;
    }

    public long getPriority() {
        return this.priority;
    }

    public void setPriority(long j) {
        this.priority = j;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public String getStatusBar() {
        return this.statusBar;
    }

    public void setStatusBar(String str) {
        this.statusBar = str;
    }

    public String getReserved() {
        return this.reserved;
    }

    public void setReserved(String str) {
        this.reserved = str;
    }

    public long getVersion() {
        return this.version;
    }
}
