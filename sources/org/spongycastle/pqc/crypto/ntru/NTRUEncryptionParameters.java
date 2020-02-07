package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUEncryptionParameters implements Cloneable {

    /* renamed from: N */
    public int f1499N;
    public int bufferLenBits;
    int bufferLenTrits;

    /* renamed from: c */
    public int f1500c;

    /* renamed from: db */
    public int f1501db;

    /* renamed from: df */
    public int f1502df;
    public int df1;
    public int df2;
    public int df3;

    /* renamed from: dg */
    public int f1503dg;
    public int dm0;

    /* renamed from: dr */
    public int f1504dr;
    public int dr1;
    public int dr2;
    public int dr3;
    public boolean fastFp;
    public Digest hashAlg;
    public boolean hashSeed;
    int llen;
    public int maxMsgLenBytes;
    public int minCallsMask;
    public int minCallsR;
    public byte[] oid;
    public int pkLen;
    public int polyType;

    /* renamed from: q */
    public int f1505q;
    public boolean sparse;

    public NTRUEncryptionParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this.f1499N = i;
        this.f1505q = i2;
        this.f1502df = i3;
        this.f1501db = i5;
        this.dm0 = i4;
        this.f1500c = i6;
        this.minCallsR = i7;
        this.minCallsMask = i8;
        this.hashSeed = z;
        this.oid = bArr;
        this.sparse = z2;
        this.fastFp = z3;
        this.polyType = 0;
        this.hashAlg = digest;
        init();
    }

    public NTRUEncryptionParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this.f1499N = i;
        this.f1505q = i2;
        this.df1 = i3;
        this.df2 = i4;
        this.df3 = i5;
        this.f1501db = i7;
        this.dm0 = i6;
        this.f1500c = i8;
        this.minCallsR = i9;
        this.minCallsMask = i10;
        this.hashSeed = z;
        this.oid = bArr;
        this.sparse = z2;
        this.fastFp = z3;
        this.polyType = 1;
        this.hashAlg = digest;
        init();
    }

    private void init() {
        this.f1504dr = this.f1502df;
        this.dr1 = this.df1;
        this.dr2 = this.df2;
        this.dr3 = this.df3;
        int i = this.f1499N;
        this.f1503dg = i / 3;
        this.llen = 1;
        int i2 = (((i * 3) / 2) / 8) - this.llen;
        int i3 = this.f1501db;
        this.maxMsgLenBytes = (i2 - (i3 / 8)) - 1;
        this.bufferLenBits = (((((i * 3) / 2) + 7) / 8) * 8) + 1;
        this.bufferLenTrits = i - 1;
        this.pkLen = i3;
    }

    public NTRUEncryptionParameters(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f1499N = dataInputStream.readInt();
        this.f1505q = dataInputStream.readInt();
        this.f1502df = dataInputStream.readInt();
        this.df1 = dataInputStream.readInt();
        this.df2 = dataInputStream.readInt();
        this.df3 = dataInputStream.readInt();
        this.f1501db = dataInputStream.readInt();
        this.dm0 = dataInputStream.readInt();
        this.f1500c = dataInputStream.readInt();
        this.minCallsR = dataInputStream.readInt();
        this.minCallsMask = dataInputStream.readInt();
        this.hashSeed = dataInputStream.readBoolean();
        this.oid = new byte[3];
        dataInputStream.read(this.oid);
        this.sparse = dataInputStream.readBoolean();
        this.fastFp = dataInputStream.readBoolean();
        this.polyType = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        if ("SHA-512".equals(readUTF)) {
            this.hashAlg = new SHA512Digest();
        } else if ("SHA-256".equals(readUTF)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public NTRUEncryptionParameters clone() {
        if (this.polyType == 0) {
            NTRUEncryptionParameters nTRUEncryptionParameters = new NTRUEncryptionParameters(this.f1499N, this.f1505q, this.f1502df, this.dm0, this.f1501db, this.f1500c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
            return nTRUEncryptionParameters;
        }
        int i = this.f1499N;
        int i2 = this.f1505q;
        int i3 = this.df1;
        int i4 = this.df2;
        int i5 = this.df3;
        int i6 = this.dm0;
        int i7 = this.f1501db;
        int i8 = this.f1500c;
        int i9 = this.minCallsR;
        int i10 = this.minCallsMask;
        boolean z = this.hashSeed;
        byte[] bArr = this.oid;
        NTRUEncryptionParameters nTRUEncryptionParameters2 = new NTRUEncryptionParameters(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, z, bArr, this.sparse, this.fastFp, this.hashAlg);
        return nTRUEncryptionParameters2;
    }

    public int getMaxMessageLength() {
        return this.maxMsgLenBytes;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f1499N);
        dataOutputStream.writeInt(this.f1505q);
        dataOutputStream.writeInt(this.f1502df);
        dataOutputStream.writeInt(this.df1);
        dataOutputStream.writeInt(this.df2);
        dataOutputStream.writeInt(this.df3);
        dataOutputStream.writeInt(this.f1501db);
        dataOutputStream.writeInt(this.dm0);
        dataOutputStream.writeInt(this.f1500c);
        dataOutputStream.writeInt(this.minCallsR);
        dataOutputStream.writeInt(this.minCallsMask);
        dataOutputStream.writeBoolean(this.hashSeed);
        dataOutputStream.write(this.oid);
        dataOutputStream.writeBoolean(this.sparse);
        dataOutputStream.writeBoolean(this.fastFp);
        dataOutputStream.write(this.polyType);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public int hashCode() {
        int i = 1231;
        int i2 = (((((((((((((((((((((((((((((((this.f1499N + 31) * 31) + this.bufferLenBits) * 31) + this.bufferLenTrits) * 31) + this.f1500c) * 31) + this.f1501db) * 31) + this.f1502df) * 31) + this.df1) * 31) + this.df2) * 31) + this.df3) * 31) + this.f1503dg) * 31) + this.dm0) * 31) + this.f1504dr) * 31) + this.dr1) * 31) + this.dr2) * 31) + this.dr3) * 31) + (this.fastFp ? 1231 : 1237)) * 31;
        Digest digest = this.hashAlg;
        int hashCode = (((((((((((((((((((i2 + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + (this.hashSeed ? 1231 : 1237)) * 31) + this.llen) * 31) + this.maxMsgLenBytes) * 31) + this.minCallsMask) * 31) + this.minCallsR) * 31) + Arrays.hashCode(this.oid)) * 31) + this.pkLen) * 31) + this.polyType) * 31) + this.f1505q) * 31;
        if (!this.sparse) {
            i = 1237;
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUEncryptionParameters nTRUEncryptionParameters = (NTRUEncryptionParameters) obj;
        if (this.f1499N != nTRUEncryptionParameters.f1499N || this.bufferLenBits != nTRUEncryptionParameters.bufferLenBits || this.bufferLenTrits != nTRUEncryptionParameters.bufferLenTrits || this.f1500c != nTRUEncryptionParameters.f1500c || this.f1501db != nTRUEncryptionParameters.f1501db || this.f1502df != nTRUEncryptionParameters.f1502df || this.df1 != nTRUEncryptionParameters.df1 || this.df2 != nTRUEncryptionParameters.df2 || this.df3 != nTRUEncryptionParameters.df3 || this.f1503dg != nTRUEncryptionParameters.f1503dg || this.dm0 != nTRUEncryptionParameters.dm0 || this.f1504dr != nTRUEncryptionParameters.f1504dr || this.dr1 != nTRUEncryptionParameters.dr1 || this.dr2 != nTRUEncryptionParameters.dr2 || this.dr3 != nTRUEncryptionParameters.dr3 || this.fastFp != nTRUEncryptionParameters.fastFp) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUEncryptionParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUEncryptionParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.hashSeed == nTRUEncryptionParameters.hashSeed && this.llen == nTRUEncryptionParameters.llen && this.maxMsgLenBytes == nTRUEncryptionParameters.maxMsgLenBytes && this.minCallsMask == nTRUEncryptionParameters.minCallsMask && this.minCallsR == nTRUEncryptionParameters.minCallsR && Arrays.equals(this.oid, nTRUEncryptionParameters.oid) && this.pkLen == nTRUEncryptionParameters.pkLen && this.polyType == nTRUEncryptionParameters.polyType && this.f1505q == nTRUEncryptionParameters.f1505q && this.sparse == nTRUEncryptionParameters.sparse;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EncryptionParameters(N=");
        sb.append(this.f1499N);
        sb.append(" q=");
        sb.append(this.f1505q);
        StringBuilder sb2 = new StringBuilder(sb.toString());
        if (this.polyType == 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" polyType=SIMPLE df=");
            sb3.append(this.f1502df);
            sb2.append(sb3.toString());
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(" polyType=PRODUCT df1=");
            sb4.append(this.df1);
            sb4.append(" df2=");
            sb4.append(this.df2);
            sb4.append(" df3=");
            sb4.append(this.df3);
            sb2.append(sb4.toString());
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append(" dm0=");
        sb5.append(this.dm0);
        sb5.append(" db=");
        sb5.append(this.f1501db);
        sb5.append(" c=");
        sb5.append(this.f1500c);
        sb5.append(" minCallsR=");
        sb5.append(this.minCallsR);
        sb5.append(" minCallsMask=");
        sb5.append(this.minCallsMask);
        sb5.append(" hashSeed=");
        sb5.append(this.hashSeed);
        sb5.append(" hashAlg=");
        sb5.append(this.hashAlg);
        sb5.append(" oid=");
        sb5.append(Arrays.toString(this.oid));
        sb5.append(" sparse=");
        sb5.append(this.sparse);
        sb5.append(")");
        sb2.append(sb5.toString());
        return sb2.toString();
    }
}
