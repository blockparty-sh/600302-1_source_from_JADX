package org.bitcoinj.crypto;

import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Arrays;
import javax.annotation.Nullable;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;

public class LazyECPoint {
    private final byte[] bits;
    private final ECCurve curve;
    @Nullable
    private ECPoint point;

    public LazyECPoint(ECCurve eCCurve, byte[] bArr) {
        this.curve = eCCurve;
        this.bits = bArr;
    }

    public LazyECPoint(ECPoint eCPoint) {
        this.point = (ECPoint) Preconditions.checkNotNull(eCPoint);
        this.curve = null;
        this.bits = null;
    }

    public ECPoint get() {
        if (this.point == null) {
            this.point = this.curve.decodePoint(this.bits);
        }
        return this.point;
    }

    public ECPoint getDetachedPoint() {
        return get().getDetachedPoint();
    }

    public byte[] getEncoded() {
        byte[] bArr = this.bits;
        if (bArr != null) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        return get().getEncoded();
    }

    public boolean isInfinity() {
        return get().isInfinity();
    }

    public ECPoint timesPow2(int i) {
        return get().timesPow2(i);
    }

    public ECFieldElement getYCoord() {
        return get().getYCoord();
    }

    public ECFieldElement[] getZCoords() {
        return get().getZCoords();
    }

    public boolean isNormalized() {
        return get().isNormalized();
    }

    public boolean isCompressed() {
        byte[] bArr = this.bits;
        if (bArr == null) {
            return get().isCompressed();
        }
        boolean z = false;
        if (bArr[0] == 2 || bArr[0] == 3) {
            z = true;
        }
        return z;
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return get().multiply(bigInteger);
    }

    public ECPoint subtract(ECPoint eCPoint) {
        return get().subtract(eCPoint);
    }

    public boolean isValid() {
        return get().isValid();
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return get().scaleY(eCFieldElement);
    }

    public ECFieldElement getXCoord() {
        return get().getXCoord();
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return get().scaleX(eCFieldElement);
    }

    public boolean equals(ECPoint eCPoint) {
        return get().equals(eCPoint);
    }

    public ECPoint negate() {
        return get().negate();
    }

    public ECPoint threeTimes() {
        return get().threeTimes();
    }

    public ECFieldElement getZCoord(int i) {
        return get().getZCoord(i);
    }

    public byte[] getEncoded(boolean z) {
        if (z == isCompressed()) {
            byte[] bArr = this.bits;
            if (bArr != null) {
                return Arrays.copyOf(bArr, bArr.length);
            }
        }
        return get().getEncoded(z);
    }

    public ECPoint add(ECPoint eCPoint) {
        return get().add(eCPoint);
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        return get().twicePlus(eCPoint);
    }

    public ECCurve getCurve() {
        return get().getCurve();
    }

    public ECPoint normalize() {
        return get().normalize();
    }

    public ECFieldElement getY() {
        return normalize().getYCoord();
    }

    public ECPoint twice() {
        return get().twice();
    }

    public ECFieldElement getAffineYCoord() {
        return get().getAffineYCoord();
    }

    public ECFieldElement getAffineXCoord() {
        return get().getAffineXCoord();
    }

    public ECFieldElement getX() {
        return normalize().getXCoord();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(getCanonicalEncoding(), ((LazyECPoint) obj).getCanonicalEncoding());
    }

    public int hashCode() {
        return Arrays.hashCode(getCanonicalEncoding());
    }

    private byte[] getCanonicalEncoding() {
        return getEncoded(true);
    }
}
