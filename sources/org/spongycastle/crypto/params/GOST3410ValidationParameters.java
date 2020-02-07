package org.spongycastle.crypto.params;

public class GOST3410ValidationParameters {

    /* renamed from: c */
    private int f1306c;

    /* renamed from: cL */
    private long f1307cL;

    /* renamed from: x0 */
    private int f1308x0;
    private long x0L;

    public GOST3410ValidationParameters(int i, int i2) {
        this.f1308x0 = i;
        this.f1306c = i2;
    }

    public GOST3410ValidationParameters(long j, long j2) {
        this.x0L = j;
        this.f1307cL = j2;
    }

    public int getC() {
        return this.f1306c;
    }

    public int getX0() {
        return this.f1308x0;
    }

    public long getCL() {
        return this.f1307cL;
    }

    public long getX0L() {
        return this.x0L;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
        if (gOST3410ValidationParameters.f1306c == this.f1306c && gOST3410ValidationParameters.f1308x0 == this.f1308x0 && gOST3410ValidationParameters.f1307cL == this.f1307cL && gOST3410ValidationParameters.x0L == this.x0L) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.f1308x0 ^ this.f1306c;
        long j = this.x0L;
        int i2 = (i ^ ((int) j)) ^ ((int) (j >> 32));
        long j2 = this.f1307cL;
        return (i2 ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
