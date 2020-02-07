package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUSigningParameters implements Cloneable {

    /* renamed from: B */
    public int f1519B;

    /* renamed from: N */
    public int f1520N;
    double beta;
    public double betaSq;
    int bitsF = 6;

    /* renamed from: d */
    public int f1521d;

    /* renamed from: d1 */
    public int f1522d1;

    /* renamed from: d2 */
    public int f1523d2;

    /* renamed from: d3 */
    public int f1524d3;
    public Digest hashAlg;
    double normBound;
    public double normBoundSq;

    /* renamed from: q */
    public int f1525q;
    public int signFailTolerance = 100;

    public NTRUSigningParameters(int i, int i2, int i3, int i4, double d, double d2, Digest digest) {
        this.f1520N = i;
        this.f1525q = i2;
        this.f1521d = i3;
        this.f1519B = i4;
        this.beta = d;
        this.normBound = d2;
        this.hashAlg = digest;
        init();
    }

    public NTRUSigningParameters(int i, int i2, int i3, int i4, int i5, int i6, double d, double d2, double d3, Digest digest) {
        this.f1520N = i;
        this.f1525q = i2;
        this.f1522d1 = i3;
        this.f1523d2 = i4;
        this.f1524d3 = i5;
        this.f1519B = i6;
        this.beta = d;
        this.normBound = d2;
        this.hashAlg = digest;
        init();
    }

    private void init() {
        double d = this.beta;
        this.betaSq = d * d;
        double d2 = this.normBound;
        this.normBoundSq = d2 * d2;
    }

    public NTRUSigningParameters(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f1520N = dataInputStream.readInt();
        this.f1525q = dataInputStream.readInt();
        this.f1521d = dataInputStream.readInt();
        this.f1522d1 = dataInputStream.readInt();
        this.f1523d2 = dataInputStream.readInt();
        this.f1524d3 = dataInputStream.readInt();
        this.f1519B = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.bitsF = dataInputStream.readInt();
        String readUTF = dataInputStream.readUTF();
        if ("SHA-512".equals(readUTF)) {
            this.hashAlg = new SHA512Digest();
        } else if ("SHA-256".equals(readUTF)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.f1520N);
        dataOutputStream.writeInt(this.f1525q);
        dataOutputStream.writeInt(this.f1521d);
        dataOutputStream.writeInt(this.f1522d1);
        dataOutputStream.writeInt(this.f1523d2);
        dataOutputStream.writeInt(this.f1524d3);
        dataOutputStream.writeInt(this.f1519B);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public NTRUSigningParameters clone() {
        NTRUSigningParameters nTRUSigningParameters = new NTRUSigningParameters(this.f1520N, this.f1525q, this.f1521d, this.f1519B, this.beta, this.normBound, this.hashAlg);
        return nTRUSigningParameters;
    }

    public int hashCode() {
        int i = ((this.f1519B + 31) * 31) + this.f1520N;
        long doubleToLongBits = Double.doubleToLongBits(this.beta);
        int i2 = (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        long doubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i3 = ((((((((((((i2 * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.f1521d) * 31) + this.f1522d1) * 31) + this.f1523d2) * 31) + this.f1524d3) * 31;
        Digest digest = this.hashAlg;
        int hashCode = i3 + (digest == null ? 0 : digest.getAlgorithmName().hashCode());
        long doubleToLongBits3 = Double.doubleToLongBits(this.normBound);
        int i4 = (hashCode * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        long doubleToLongBits4 = Double.doubleToLongBits(this.normBoundSq);
        return (((((i4 * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + this.f1525q) * 31) + this.signFailTolerance;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningParameters)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = (NTRUSigningParameters) obj;
        if (this.f1519B != nTRUSigningParameters.f1519B || this.f1520N != nTRUSigningParameters.f1520N || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningParameters.betaSq) || this.bitsF != nTRUSigningParameters.bitsF || this.f1521d != nTRUSigningParameters.f1521d || this.f1522d1 != nTRUSigningParameters.f1522d1 || this.f1523d2 != nTRUSigningParameters.f1523d2 || this.f1524d3 != nTRUSigningParameters.f1524d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningParameters.normBoundSq) && this.f1525q == nTRUSigningParameters.f1525q && this.signFailTolerance == nTRUSigningParameters.signFailTolerance;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder();
        sb.append("SignatureParameters(N=");
        sb.append(this.f1520N);
        sb.append(" q=");
        sb.append(this.f1525q);
        StringBuilder sb2 = new StringBuilder(sb.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(" B=");
        sb3.append(this.f1519B);
        sb3.append(" beta=");
        sb3.append(decimalFormat.format(this.beta));
        sb3.append(" normBound=");
        sb3.append(decimalFormat.format(this.normBound));
        sb3.append(" hashAlg=");
        sb3.append(this.hashAlg);
        sb3.append(")");
        sb2.append(sb3.toString());
        return sb2.toString();
    }
}
