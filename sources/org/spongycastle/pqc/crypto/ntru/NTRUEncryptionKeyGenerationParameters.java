package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bitcoinj.script.ScriptOpCodes;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUEncryptionKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public static final NTRUEncryptionKeyGenerationParameters APR2011_439;
    public static final NTRUEncryptionKeyGenerationParameters APR2011_439_FAST;
    public static final NTRUEncryptionKeyGenerationParameters APR2011_743;
    public static final NTRUEncryptionKeyGenerationParameters APR2011_743_FAST;
    public static final NTRUEncryptionKeyGenerationParameters EES1087EP2;
    public static final NTRUEncryptionKeyGenerationParameters EES1171EP1;
    public static final NTRUEncryptionKeyGenerationParameters EES1499EP1;

    /* renamed from: N */
    public int f1492N;
    public int bufferLenBits;
    int bufferLenTrits;

    /* renamed from: c */
    public int f1493c;

    /* renamed from: db */
    public int f1494db;

    /* renamed from: df */
    public int f1495df;
    public int df1;
    public int df2;
    public int df3;

    /* renamed from: dg */
    public int f1496dg;
    public int dm0;

    /* renamed from: dr */
    public int f1497dr;
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
    public int f1498q;
    public boolean sparse;

    static {
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters = new NTRUEncryptionKeyGenerationParameters(1087, 2048, 120, 120, 256, 13, 25, 14, true, new byte[]{0, 6, 3}, true, false, new SHA512Digest());
        EES1087EP2 = nTRUEncryptionKeyGenerationParameters;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters2 = new NTRUEncryptionKeyGenerationParameters(1171, 2048, 106, 106, 256, 13, 20, 15, true, new byte[]{0, 6, 4}, true, false, new SHA512Digest());
        EES1171EP1 = nTRUEncryptionKeyGenerationParameters2;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters3 = new NTRUEncryptionKeyGenerationParameters(1499, 2048, 79, 79, 256, 13, 17, 19, true, new byte[]{0, 6, 5}, true, false, new SHA512Digest());
        EES1499EP1 = nTRUEncryptionKeyGenerationParameters3;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters4 = new NTRUEncryptionKeyGenerationParameters(439, 2048, 146, ScriptOpCodes.OP_SIZE, 128, 9, 32, 9, true, new byte[]{0, 7, 101}, true, false, new SHA256Digest());
        APR2011_439 = nTRUEncryptionKeyGenerationParameters4;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters5 = new NTRUEncryptionKeyGenerationParameters(439, 2048, 9, 8, 5, ScriptOpCodes.OP_SIZE, 128, 9, 32, 9, true, new byte[]{0, 7, 101}, true, true, new SHA256Digest());
        APR2011_439_FAST = nTRUEncryptionKeyGenerationParameters5;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters6 = new NTRUEncryptionKeyGenerationParameters(743, 2048, 248, 220, 256, 10, 27, 14, true, new byte[]{0, 7, 105}, false, false, new SHA512Digest());
        APR2011_743 = nTRUEncryptionKeyGenerationParameters6;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters7 = new NTRUEncryptionKeyGenerationParameters(743, 2048, 11, 11, 15, 220, 256, 10, 27, 14, true, new byte[]{0, 7, 105}, false, true, new SHA512Digest());
        APR2011_743_FAST = nTRUEncryptionKeyGenerationParameters7;
    }

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        super(new SecureRandom(), i5);
        this.f1492N = i;
        this.f1498q = i2;
        this.f1495df = i3;
        this.f1494db = i5;
        this.dm0 = i4;
        this.f1493c = i6;
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

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        int i11 = i7;
        super(new SecureRandom(), i7);
        this.f1492N = i;
        this.f1498q = i2;
        this.df1 = i3;
        this.df2 = i4;
        this.df3 = i5;
        this.f1494db = i11;
        this.dm0 = i6;
        this.f1493c = i8;
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
        this.f1497dr = this.f1495df;
        this.dr1 = this.df1;
        this.dr2 = this.df2;
        this.dr3 = this.df3;
        int i = this.f1492N;
        this.f1496dg = i / 3;
        this.llen = 1;
        int i2 = (((i * 3) / 2) / 8) - this.llen;
        int i3 = this.f1494db;
        this.maxMsgLenBytes = (i2 - (i3 / 8)) - 1;
        this.bufferLenBits = (((((i * 3) / 2) + 7) / 8) * 8) + 1;
        this.bufferLenTrits = i - 1;
        this.pkLen = i3;
    }

    public NTRUEncryptionKeyGenerationParameters(InputStream inputStream) throws IOException {
        super(new SecureRandom(), -1);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f1492N = dataInputStream.readInt();
        this.f1498q = dataInputStream.readInt();
        this.f1495df = dataInputStream.readInt();
        this.df1 = dataInputStream.readInt();
        this.df2 = dataInputStream.readInt();
        this.df3 = dataInputStream.readInt();
        this.f1494db = dataInputStream.readInt();
        this.dm0 = dataInputStream.readInt();
        this.f1493c = dataInputStream.readInt();
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

    public NTRUEncryptionParameters getEncryptionParameters() {
        if (this.polyType == 0) {
            NTRUEncryptionParameters nTRUEncryptionParameters = new NTRUEncryptionParameters(this.f1492N, this.f1498q, this.f1495df, this.dm0, this.f1494db, this.f1493c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
            return nTRUEncryptionParameters;
        }
        int i = this.f1492N;
        int i2 = this.f1498q;
        int i3 = this.df1;
        int i4 = this.df2;
        int i5 = this.df3;
        int i6 = this.dm0;
        int i7 = this.f1494db;
        int i8 = this.f1493c;
        int i9 = this.minCallsR;
        int i10 = this.minCallsMask;
        boolean z = this.hashSeed;
        byte[] bArr = this.oid;
        NTRUEncryptionParameters nTRUEncryptionParameters2 = new NTRUEncryptionParameters(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, z, bArr, this.sparse, this.fastFp, this.hashAlg);
        return nTRUEncryptionParameters2;
    }

    public NTRUEncryptionKeyGenerationParameters clone() {
        if (this.polyType == 0) {
            NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters = new NTRUEncryptionKeyGenerationParameters(this.f1492N, this.f1498q, this.f1495df, this.dm0, this.f1494db, this.f1493c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, this.hashAlg);
            return nTRUEncryptionKeyGenerationParameters;
        }
        int i = this.f1492N;
        int i2 = this.f1498q;
        int i3 = this.df1;
        int i4 = this.df2;
        int i5 = this.df3;
        int i6 = this.dm0;
        int i7 = this.f1494db;
        int i8 = this.f1493c;
        int i9 = this.minCallsR;
        int i10 = this.minCallsMask;
        boolean z = this.hashSeed;
        byte[] bArr = this.oid;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters2 = new NTRUEncryptionKeyGenerationParameters(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, z, bArr, this.sparse, this.fastFp, this.hashAlg);
        return nTRUEncryptionKeyGenerationParameters2;
    }

    public int getMaxMessageLength() {
        return this.maxMsgLenBytes;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f1492N);
        dataOutputStream.writeInt(this.f1498q);
        dataOutputStream.writeInt(this.f1495df);
        dataOutputStream.writeInt(this.df1);
        dataOutputStream.writeInt(this.df2);
        dataOutputStream.writeInt(this.df3);
        dataOutputStream.writeInt(this.f1494db);
        dataOutputStream.writeInt(this.dm0);
        dataOutputStream.writeInt(this.f1493c);
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
        int i2 = (((((((((((((((((((((((((((((((this.f1492N + 31) * 31) + this.bufferLenBits) * 31) + this.bufferLenTrits) * 31) + this.f1493c) * 31) + this.f1494db) * 31) + this.f1495df) * 31) + this.df1) * 31) + this.df2) * 31) + this.df3) * 31) + this.f1496dg) * 31) + this.dm0) * 31) + this.f1497dr) * 31) + this.dr1) * 31) + this.dr2) * 31) + this.dr3) * 31) + (this.fastFp ? 1231 : 1237)) * 31;
        Digest digest = this.hashAlg;
        int hashCode = (((((((((((((((((((i2 + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + (this.hashSeed ? 1231 : 1237)) * 31) + this.llen) * 31) + this.maxMsgLenBytes) * 31) + this.minCallsMask) * 31) + this.minCallsR) * 31) + Arrays.hashCode(this.oid)) * 31) + this.pkLen) * 31) + this.polyType) * 31) + this.f1498q) * 31;
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
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters = (NTRUEncryptionKeyGenerationParameters) obj;
        if (this.f1492N != nTRUEncryptionKeyGenerationParameters.f1492N || this.bufferLenBits != nTRUEncryptionKeyGenerationParameters.bufferLenBits || this.bufferLenTrits != nTRUEncryptionKeyGenerationParameters.bufferLenTrits || this.f1493c != nTRUEncryptionKeyGenerationParameters.f1493c || this.f1494db != nTRUEncryptionKeyGenerationParameters.f1494db || this.f1495df != nTRUEncryptionKeyGenerationParameters.f1495df || this.df1 != nTRUEncryptionKeyGenerationParameters.df1 || this.df2 != nTRUEncryptionKeyGenerationParameters.df2 || this.df3 != nTRUEncryptionKeyGenerationParameters.df3 || this.f1496dg != nTRUEncryptionKeyGenerationParameters.f1496dg || this.dm0 != nTRUEncryptionKeyGenerationParameters.dm0 || this.f1497dr != nTRUEncryptionKeyGenerationParameters.f1497dr || this.dr1 != nTRUEncryptionKeyGenerationParameters.dr1 || this.dr2 != nTRUEncryptionKeyGenerationParameters.dr2 || this.dr3 != nTRUEncryptionKeyGenerationParameters.dr3 || this.fastFp != nTRUEncryptionKeyGenerationParameters.fastFp) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUEncryptionKeyGenerationParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUEncryptionKeyGenerationParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.hashSeed == nTRUEncryptionKeyGenerationParameters.hashSeed && this.llen == nTRUEncryptionKeyGenerationParameters.llen && this.maxMsgLenBytes == nTRUEncryptionKeyGenerationParameters.maxMsgLenBytes && this.minCallsMask == nTRUEncryptionKeyGenerationParameters.minCallsMask && this.minCallsR == nTRUEncryptionKeyGenerationParameters.minCallsR && Arrays.equals(this.oid, nTRUEncryptionKeyGenerationParameters.oid) && this.pkLen == nTRUEncryptionKeyGenerationParameters.pkLen && this.polyType == nTRUEncryptionKeyGenerationParameters.polyType && this.f1498q == nTRUEncryptionKeyGenerationParameters.f1498q && this.sparse == nTRUEncryptionKeyGenerationParameters.sparse;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EncryptionParameters(N=");
        sb.append(this.f1492N);
        sb.append(" q=");
        sb.append(this.f1498q);
        StringBuilder sb2 = new StringBuilder(sb.toString());
        if (this.polyType == 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" polyType=SIMPLE df=");
            sb3.append(this.f1495df);
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
        sb5.append(this.f1494db);
        sb5.append(" c=");
        sb5.append(this.f1493c);
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
