package org.spongycastle.pqc.math.ntru.euclid;

public class IntEuclidean {
    public int gcd;

    /* renamed from: x */
    public int f1556x;

    /* renamed from: y */
    public int f1557y;

    private IntEuclidean() {
    }

    public static IntEuclidean calculate(int i, int i2) {
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        int i7 = i2;
        int i8 = i;
        int i9 = i7;
        while (i9 != 0) {
            int i10 = i8 / i9;
            int i11 = i8 % i9;
            i8 = i9;
            i9 = i11;
            int i12 = i5;
            i5 = i3 - (i10 * i5);
            i3 = i12;
            int i13 = i6;
            i6 = i4 - (i10 * i6);
            i4 = i13;
        }
        IntEuclidean intEuclidean = new IntEuclidean();
        intEuclidean.f1556x = i3;
        intEuclidean.f1557y = i4;
        intEuclidean.gcd = i8;
        return intEuclidean;
    }
}
