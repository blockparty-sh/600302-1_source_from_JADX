package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* renamed from: g */
    private GF2Matrix f1487g;

    /* renamed from: n */
    private int f1488n;
    private String oid;

    /* renamed from: t */
    private int f1489t;

    public McEliecePublicKeyParameters(String str, int i, int i2, GF2Matrix gF2Matrix, McElieceParameters mcElieceParameters) {
        super(false, mcElieceParameters);
        this.oid = str;
        this.f1488n = i;
        this.f1489t = i2;
        this.f1487g = new GF2Matrix(gF2Matrix);
    }

    public McEliecePublicKeyParameters(String str, int i, int i2, byte[] bArr, McElieceParameters mcElieceParameters) {
        super(false, mcElieceParameters);
        this.oid = str;
        this.f1488n = i2;
        this.f1489t = i;
        this.f1487g = new GF2Matrix(bArr);
    }

    public int getN() {
        return this.f1488n;
    }

    public int getT() {
        return this.f1489t;
    }

    public GF2Matrix getG() {
        return this.f1487g;
    }

    public String getOIDString() {
        return this.oid;
    }

    public int getK() {
        return this.f1487g.getNumRows();
    }
}
