package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* renamed from: n */
    private int f1458n;
    private String oid;

    /* renamed from: t */
    private int f1459t;

    public McElieceCCA2PublicKeyParameters(String str, int i, int i2, GF2Matrix gF2Matrix, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(false, mcElieceCCA2Parameters);
        this.oid = str;
        this.f1458n = i;
        this.f1459t = i2;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public McElieceCCA2PublicKeyParameters(String str, int i, int i2, byte[] bArr, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(false, mcElieceCCA2Parameters);
        this.oid = str;
        this.f1458n = i;
        this.f1459t = i2;
        this.matrixG = new GF2Matrix(bArr);
    }

    public int getN() {
        return this.f1458n;
    }

    public int getT() {
        return this.f1459t;
    }

    public GF2Matrix getMatrixG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }

    public String getOIDString() {
        return this.oid;
    }
}
