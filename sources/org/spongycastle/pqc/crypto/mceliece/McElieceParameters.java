package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class McElieceParameters implements CipherParameters {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* renamed from: m */
    private int f1475m;

    /* renamed from: n */
    private int f1476n;

    /* renamed from: t */
    private int f1477t;

    public McElieceParameters() {
        this(11, 50);
    }

    public McElieceParameters(int i) throws IllegalArgumentException {
        if (i >= 1) {
            this.f1475m = 0;
            this.f1476n = 1;
            while (true) {
                int i2 = this.f1476n;
                if (i2 < i) {
                    this.f1476n = i2 << 1;
                    this.f1475m++;
                } else {
                    this.f1477t = i2 >>> 1;
                    int i3 = this.f1477t;
                    int i4 = this.f1475m;
                    this.f1477t = i3 / i4;
                    this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i4);
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("key size must be positive");
        }
    }

    public McElieceParameters(int i, int i2) throws IllegalArgumentException {
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        } else if (i <= 32) {
            this.f1475m = i;
            this.f1476n = 1 << i;
            if (i2 < 0) {
                throw new IllegalArgumentException("t must be positive");
            } else if (i2 <= this.f1476n) {
                this.f1477t = i2;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i);
            } else {
                throw new IllegalArgumentException("t must be less than n = 2^m");
            }
        } else {
            throw new IllegalArgumentException("m is too large");
        }
    }

    public McElieceParameters(int i, int i2, int i3) throws IllegalArgumentException {
        this.f1475m = i;
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        } else if (i <= 32) {
            this.f1476n = 1 << i;
            this.f1477t = i2;
            if (i2 < 0) {
                throw new IllegalArgumentException("t must be positive");
            } else if (i2 > this.f1476n) {
                throw new IllegalArgumentException("t must be less than n = 2^m");
            } else if (PolynomialRingGF2.degree(i3) != i || !PolynomialRingGF2.isIrreducible(i3)) {
                throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
            } else {
                this.fieldPoly = i3;
            }
        } else {
            throw new IllegalArgumentException(" m is too large");
        }
    }

    public int getM() {
        return this.f1475m;
    }

    public int getN() {
        return this.f1476n;
    }

    public int getT() {
        return this.f1477t;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }
}
