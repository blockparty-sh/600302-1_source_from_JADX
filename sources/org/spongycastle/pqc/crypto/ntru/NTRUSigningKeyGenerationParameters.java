package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUSigningKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public static final NTRUSigningKeyGenerationParameters APR2011_439;
    public static final NTRUSigningKeyGenerationParameters APR2011_439_PROD;
    public static final NTRUSigningKeyGenerationParameters APR2011_743;
    public static final NTRUSigningKeyGenerationParameters APR2011_743_PROD;
    public static final int BASIS_TYPE_STANDARD = 0;
    public static final int BASIS_TYPE_TRANSPOSE = 1;
    public static final int KEY_GEN_ALG_FLOAT = 1;
    public static final int KEY_GEN_ALG_RESULTANT = 0;
    public static final NTRUSigningKeyGenerationParameters TEST157;
    public static final NTRUSigningKeyGenerationParameters TEST157_PROD;

    /* renamed from: B */
    public int f1510B;

    /* renamed from: N */
    public int f1511N;
    public int basisType;
    double beta;
    public double betaSq;
    int bitsF = 6;

    /* renamed from: d */
    public int f1512d;

    /* renamed from: d1 */
    public int f1513d1;

    /* renamed from: d2 */
    public int f1514d2;

    /* renamed from: d3 */
    public int f1515d3;
    public Digest hashAlg;
    public int keyGenAlg;
    double keyNormBound;
    public double keyNormBoundSq;
    double normBound;
    public double normBoundSq;
    public int polyType;
    public boolean primeCheck;

    /* renamed from: q */
    public int f1516q;
    public int signFailTolerance = 100;
    public boolean sparse;

    static {
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = new NTRUSigningKeyGenerationParameters(439, 2048, 146, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, new SHA256Digest());
        APR2011_439 = nTRUSigningKeyGenerationParameters;
        SHA256Digest sHA256Digest = r1;
        SHA256Digest sHA256Digest2 = new SHA256Digest();
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters2 = new NTRUSigningKeyGenerationParameters(439, 2048, 9, 8, 5, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, sHA256Digest);
        APR2011_439_PROD = nTRUSigningKeyGenerationParameters2;
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters3 = new NTRUSigningKeyGenerationParameters(743, 2048, 248, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, new SHA512Digest());
        APR2011_743 = nTRUSigningKeyGenerationParameters3;
        SHA512Digest sHA512Digest = r1;
        SHA512Digest sHA512Digest2 = new SHA512Digest();
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters4 = new NTRUSigningKeyGenerationParameters(743, 2048, 11, 11, 15, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, sHA512Digest);
        APR2011_743_PROD = nTRUSigningKeyGenerationParameters4;
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters5 = new NTRUSigningKeyGenerationParameters(157, 256, 29, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());
        TEST157 = nTRUSigningKeyGenerationParameters5;
        SHA256Digest sHA256Digest3 = r1;
        SHA256Digest sHA256Digest4 = new SHA256Digest();
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters6 = new NTRUSigningKeyGenerationParameters(157, 256, 5, 5, 8, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, sHA256Digest3);
        TEST157_PROD = nTRUSigningKeyGenerationParameters6;
    }

    public NTRUSigningKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, double d, double d2, double d3, boolean z, boolean z2, int i6, Digest digest) {
        int i7 = i;
        super(new SecureRandom(), i);
        this.f1511N = i7;
        this.f1516q = i2;
        this.f1512d = i3;
        this.f1510B = i4;
        this.basisType = i5;
        this.beta = d;
        this.normBound = d2;
        this.keyNormBound = d3;
        this.primeCheck = z;
        this.sparse = z2;
        this.keyGenAlg = i6;
        this.hashAlg = digest;
        this.polyType = 0;
        init();
    }

    public NTRUSigningKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, double d, double d2, double d3, boolean z, boolean z2, int i8, Digest digest) {
        int i9 = i;
        super(new SecureRandom(), i);
        this.f1511N = i9;
        this.f1516q = i2;
        this.f1513d1 = i3;
        this.f1514d2 = i4;
        this.f1515d3 = i5;
        this.f1510B = i6;
        this.basisType = i7;
        this.beta = d;
        this.normBound = d2;
        this.keyNormBound = d3;
        this.primeCheck = z;
        this.sparse = z2;
        this.keyGenAlg = i8;
        this.hashAlg = digest;
        this.polyType = 1;
        init();
    }

    private void init() {
        double d = this.beta;
        this.betaSq = d * d;
        double d2 = this.normBound;
        this.normBoundSq = d2 * d2;
        double d3 = this.keyNormBound;
        this.keyNormBoundSq = d3 * d3;
    }

    public NTRUSigningKeyGenerationParameters(InputStream inputStream) throws IOException {
        super(new SecureRandom(), 0);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f1511N = dataInputStream.readInt();
        this.f1516q = dataInputStream.readInt();
        this.f1512d = dataInputStream.readInt();
        this.f1513d1 = dataInputStream.readInt();
        this.f1514d2 = dataInputStream.readInt();
        this.f1515d3 = dataInputStream.readInt();
        this.f1510B = dataInputStream.readInt();
        this.basisType = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.keyNormBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.primeCheck = dataInputStream.readBoolean();
        this.sparse = dataInputStream.readBoolean();
        this.bitsF = dataInputStream.readInt();
        this.keyGenAlg = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        if ("SHA-512".equals(readUTF)) {
            this.hashAlg = new SHA512Digest();
        } else if ("SHA-256".equals(readUTF)) {
            this.hashAlg = new SHA256Digest();
        }
        this.polyType = dataInputStream.read();
        init();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f1511N);
        dataOutputStream.writeInt(this.f1516q);
        dataOutputStream.writeInt(this.f1512d);
        dataOutputStream.writeInt(this.f1513d1);
        dataOutputStream.writeInt(this.f1514d2);
        dataOutputStream.writeInt(this.f1515d3);
        dataOutputStream.writeInt(this.f1510B);
        dataOutputStream.writeInt(this.basisType);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeDouble(this.keyNormBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeBoolean(this.primeCheck);
        dataOutputStream.writeBoolean(this.sparse);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.write(this.keyGenAlg);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
        dataOutputStream.write(this.polyType);
    }

    public NTRUSigningParameters getSigningParameters() {
        NTRUSigningParameters nTRUSigningParameters = new NTRUSigningParameters(this.f1511N, this.f1516q, this.f1512d, this.f1510B, this.beta, this.normBound, this.hashAlg);
        return nTRUSigningParameters;
    }

    public NTRUSigningKeyGenerationParameters clone() {
        if (this.polyType == 0) {
            int i = this.f1511N;
            int i2 = this.f1516q;
            int i3 = this.f1512d;
            int i4 = this.f1510B;
            int i5 = this.basisType;
            double d = this.beta;
            double d2 = this.normBound;
            double d3 = this.keyNormBound;
            boolean z = this.primeCheck;
            boolean z2 = this.sparse;
            boolean z3 = z2;
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = new NTRUSigningKeyGenerationParameters(i, i2, i3, i4, i5, d, d2, d3, z, z3, this.keyGenAlg, this.hashAlg);
            return nTRUSigningKeyGenerationParameters;
        }
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters2 = new NTRUSigningKeyGenerationParameters(this.f1511N, this.f1516q, this.f1513d1, this.f1514d2, this.f1515d3, this.f1510B, this.basisType, this.beta, this.normBound, this.keyNormBound, this.primeCheck, this.sparse, this.keyGenAlg, this.hashAlg);
        return nTRUSigningKeyGenerationParameters2;
    }

    public int hashCode() {
        int i = ((((this.f1510B + 31) * 31) + this.f1511N) * 31) + this.basisType;
        long doubleToLongBits = Double.doubleToLongBits(this.beta);
        int i2 = (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        long doubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i3 = ((((((((((((i2 * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.f1512d) * 31) + this.f1513d1) * 31) + this.f1514d2) * 31) + this.f1515d3) * 31;
        Digest digest = this.hashAlg;
        int hashCode = ((i3 + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + this.keyGenAlg;
        long doubleToLongBits3 = Double.doubleToLongBits(this.keyNormBound);
        int i4 = (hashCode * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        long doubleToLongBits4 = Double.doubleToLongBits(this.keyNormBoundSq);
        int i5 = (i4 * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)));
        long doubleToLongBits5 = Double.doubleToLongBits(this.normBound);
        int i6 = (i5 * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)));
        long doubleToLongBits6 = Double.doubleToLongBits(this.normBoundSq);
        int i7 = 1231;
        int i8 = ((((((((((i6 * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31) + this.polyType) * 31) + (this.primeCheck ? 1231 : 1237)) * 31) + this.f1516q) * 31) + this.signFailTolerance) * 31;
        if (!this.sparse) {
            i7 = 1237;
        }
        return i8 + i7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningKeyGenerationParameters)) {
            return false;
        }
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = (NTRUSigningKeyGenerationParameters) obj;
        if (this.f1510B != nTRUSigningKeyGenerationParameters.f1510B || this.f1511N != nTRUSigningKeyGenerationParameters.f1511N || this.basisType != nTRUSigningKeyGenerationParameters.basisType || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.betaSq) || this.bitsF != nTRUSigningKeyGenerationParameters.bitsF || this.f1512d != nTRUSigningKeyGenerationParameters.f1512d || this.f1513d1 != nTRUSigningKeyGenerationParameters.f1513d1 || this.f1514d2 != nTRUSigningKeyGenerationParameters.f1514d2 || this.f1515d3 != nTRUSigningKeyGenerationParameters.f1515d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningKeyGenerationParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningKeyGenerationParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.keyGenAlg == nTRUSigningKeyGenerationParameters.keyGenAlg && Double.doubleToLongBits(this.keyNormBound) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.keyNormBound) && Double.doubleToLongBits(this.keyNormBoundSq) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.keyNormBoundSq) && Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.normBoundSq) && this.polyType == nTRUSigningKeyGenerationParameters.polyType && this.primeCheck == nTRUSigningKeyGenerationParameters.primeCheck && this.f1516q == nTRUSigningKeyGenerationParameters.f1516q && this.signFailTolerance == nTRUSigningKeyGenerationParameters.signFailTolerance && this.sparse == nTRUSigningKeyGenerationParameters.sparse;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder();
        sb.append("SignatureParameters(N=");
        sb.append(this.f1511N);
        sb.append(" q=");
        sb.append(this.f1516q);
        StringBuilder sb2 = new StringBuilder(sb.toString());
        if (this.polyType == 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" polyType=SIMPLE d=");
            sb3.append(this.f1512d);
            sb2.append(sb3.toString());
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(" polyType=PRODUCT d1=");
            sb4.append(this.f1513d1);
            sb4.append(" d2=");
            sb4.append(this.f1514d2);
            sb4.append(" d3=");
            sb4.append(this.f1515d3);
            sb2.append(sb4.toString());
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append(" B=");
        sb5.append(this.f1510B);
        sb5.append(" basisType=");
        sb5.append(this.basisType);
        sb5.append(" beta=");
        sb5.append(decimalFormat.format(this.beta));
        sb5.append(" normBound=");
        sb5.append(decimalFormat.format(this.normBound));
        sb5.append(" keyNormBound=");
        sb5.append(decimalFormat.format(this.keyNormBound));
        sb5.append(" prime=");
        sb5.append(this.primeCheck);
        sb5.append(" sparse=");
        sb5.append(this.sparse);
        sb5.append(" keyGenAlg=");
        sb5.append(this.keyGenAlg);
        sb5.append(" hashAlg=");
        sb5.append(this.hashAlg);
        sb5.append(")");
        sb2.append(sb5.toString());
        return sb2.toString();
    }
}
