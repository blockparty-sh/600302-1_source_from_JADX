package org.spongycastle.math.p025ec;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Random;
import org.spongycastle.math.field.FiniteField;
import org.spongycastle.math.field.FiniteFields;
import org.spongycastle.math.p025ec.ECFieldElement.C3640Fp;
import org.spongycastle.math.p025ec.ECPoint.C3641Fp;
import org.spongycastle.math.p025ec.endo.ECEndomorphism;
import org.spongycastle.math.p025ec.endo.GLVEndomorphism;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Integers;

/* renamed from: org.spongycastle.math.ec.ECCurve */
public abstract class ECCurve {
    public static final int COORD_AFFINE = 0;
    public static final int COORD_HOMOGENEOUS = 1;
    public static final int COORD_JACOBIAN = 2;
    public static final int COORD_JACOBIAN_CHUDNOVSKY = 3;
    public static final int COORD_JACOBIAN_MODIFIED = 4;
    public static final int COORD_LAMBDA_AFFINE = 5;
    public static final int COORD_LAMBDA_PROJECTIVE = 6;
    public static final int COORD_SKEWED = 7;

    /* renamed from: a */
    protected ECFieldElement f1345a;

    /* renamed from: b */
    protected ECFieldElement f1346b;
    protected BigInteger cofactor;
    protected int coord = 0;
    protected ECEndomorphism endomorphism = null;
    protected FiniteField field;
    protected ECMultiplier multiplier = null;
    protected BigInteger order;

    /* renamed from: org.spongycastle.math.ec.ECCurve$AbstractF2m */
    public static abstract class AbstractF2m extends ECCurve {
        private static FiniteField buildField(int i, int i2, int i3, int i4) {
            if (i2 == 0) {
                throw new IllegalArgumentException("k1 must be > 0");
            } else if (i3 == 0) {
                if (i4 == 0) {
                    return FiniteFields.getBinaryExtensionField(new int[]{0, i2, i});
                }
                throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
            } else if (i3 <= i2) {
                throw new IllegalArgumentException("k2 must be > k1");
            } else if (i4 > i3) {
                return FiniteFields.getBinaryExtensionField(new int[]{0, i2, i3, i4, i});
            } else {
                throw new IllegalArgumentException("k3 must be > k2");
            }
        }

        protected AbstractF2m(int i, int i2, int i3, int i4) {
            super(buildField(i, i2, i3, i4));
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECCurve$AbstractFp */
    public static abstract class AbstractFp extends ECCurve {
        protected AbstractFp(BigInteger bigInteger) {
            super(FiniteFields.getPrimeField(bigInteger));
        }

        /* access modifiers changed from: protected */
        public ECPoint decompressPoint(int i, BigInteger bigInteger) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement sqrt = fromBigInteger.square().add(this.f1345a).multiply(fromBigInteger).add(this.f1346b).sqrt();
            if (sqrt != null) {
                if (sqrt.testBitZero() != (i == 1)) {
                    sqrt = sqrt.negate();
                }
                return createRawPoint(fromBigInteger, sqrt, true);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECCurve$Config */
    public class Config {
        protected int coord;
        protected ECEndomorphism endomorphism;
        protected ECMultiplier multiplier;

        Config(int i, ECEndomorphism eCEndomorphism, ECMultiplier eCMultiplier) {
            this.coord = i;
            this.endomorphism = eCEndomorphism;
            this.multiplier = eCMultiplier;
        }

        public Config setCoordinateSystem(int i) {
            this.coord = i;
            return this;
        }

        public Config setEndomorphism(ECEndomorphism eCEndomorphism) {
            this.endomorphism = eCEndomorphism;
            return this;
        }

        public Config setMultiplier(ECMultiplier eCMultiplier) {
            this.multiplier = eCMultiplier;
            return this;
        }

        public ECCurve create() {
            if (ECCurve.this.supportsCoordinateSystem(this.coord)) {
                ECCurve cloneCurve = ECCurve.this.cloneCurve();
                if (cloneCurve != ECCurve.this) {
                    cloneCurve.coord = this.coord;
                    cloneCurve.endomorphism = this.endomorphism;
                    cloneCurve.multiplier = this.multiplier;
                    return cloneCurve;
                }
                throw new IllegalStateException("implementation returned current curve");
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECCurve$F2m */
    public static class F2m extends AbstractF2m {
        private static final int F2M_DEFAULT_COORDS = 6;
        private org.spongycastle.math.p025ec.ECPoint.F2m infinity;

        /* renamed from: k1 */
        private int f1347k1;

        /* renamed from: k2 */
        private int f1348k2;

        /* renamed from: k3 */
        private int f1349k3;

        /* renamed from: m */
        private int f1350m;

        /* renamed from: mu */
        private byte f1351mu;

        /* renamed from: si */
        private BigInteger[] f1352si;

        public boolean supportsCoordinateSystem(int i) {
            return i == 0 || i == 1 || i == 6;
        }

        public F2m(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, i3, i4, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(i, i2, i3, i4);
            this.f1351mu = 0;
            this.f1352si = null;
            this.f1350m = i;
            this.f1347k1 = i2;
            this.f1348k2 = i3;
            this.f1349k3 = i4;
            this.order = bigInteger3;
            this.cofactor = bigInteger4;
            this.infinity = new org.spongycastle.math.p025ec.ECPoint.F2m(this, null, null);
            this.f1345a = fromBigInteger(bigInteger);
            this.f1346b = fromBigInteger(bigInteger2);
            this.coord = 6;
        }

        protected F2m(int i, int i2, int i3, int i4, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger, BigInteger bigInteger2) {
            super(i, i2, i3, i4);
            this.f1351mu = 0;
            this.f1352si = null;
            this.f1350m = i;
            this.f1347k1 = i2;
            this.f1348k2 = i3;
            this.f1349k3 = i4;
            this.order = bigInteger;
            this.cofactor = bigInteger2;
            this.infinity = new org.spongycastle.math.p025ec.ECPoint.F2m(this, null, null);
            this.f1345a = eCFieldElement;
            this.f1346b = eCFieldElement2;
            this.coord = 6;
        }

        /* access modifiers changed from: protected */
        public ECCurve cloneCurve() {
            F2m f2m = new F2m(this.f1350m, this.f1347k1, this.f1348k2, this.f1349k3, this.f1345a, this.f1346b, this.order, this.cofactor);
            return f2m;
        }

        /* access modifiers changed from: protected */
        public ECMultiplier createDefaultMultiplier() {
            if (isKoblitz()) {
                return new WTauNafMultiplier();
            }
            return super.createDefaultMultiplier();
        }

        public int getFieldSize() {
            return this.f1350m;
        }

        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            org.spongycastle.math.p025ec.ECFieldElement.F2m f2m = new org.spongycastle.math.p025ec.ECFieldElement.F2m(this.f1350m, this.f1347k1, this.f1348k2, this.f1349k3, bigInteger);
            return f2m;
        }

        public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement fromBigInteger2 = fromBigInteger(bigInteger2);
            int coordinateSystem = getCoordinateSystem();
            if (coordinateSystem == 5 || coordinateSystem == 6) {
                if (!fromBigInteger.isZero()) {
                    fromBigInteger2 = fromBigInteger2.divide(fromBigInteger).add(fromBigInteger);
                } else if (!fromBigInteger2.square().equals(getB())) {
                    throw new IllegalArgumentException();
                }
            }
            return createRawPoint(fromBigInteger, fromBigInteger2, z);
        }

        /* access modifiers changed from: protected */
        public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            return new org.spongycastle.math.p025ec.ECPoint.F2m(this, eCFieldElement, eCFieldElement2, z);
        }

        /* access modifiers changed from: protected */
        public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            org.spongycastle.math.p025ec.ECPoint.F2m f2m = new org.spongycastle.math.p025ec.ECPoint.F2m(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
            return f2m;
        }

        public ECPoint getInfinity() {
            return this.infinity;
        }

        public boolean isKoblitz() {
            return this.order != null && this.cofactor != null && this.f1346b.isOne() && (this.f1345a.isZero() || this.f1345a.isOne());
        }

        /* access modifiers changed from: 0000 */
        public synchronized byte getMu() {
            if (this.f1351mu == 0) {
                this.f1351mu = Tnaf.getMu(this);
            }
            return this.f1351mu;
        }

        /* access modifiers changed from: 0000 */
        public synchronized BigInteger[] getSi() {
            if (this.f1352si == null) {
                this.f1352si = Tnaf.getSi(this);
            }
            return this.f1352si;
        }

        /* access modifiers changed from: protected */
        public ECPoint decompressPoint(int i, BigInteger bigInteger) {
            ECFieldElement eCFieldElement;
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            if (fromBigInteger.isZero()) {
                eCFieldElement = this.f1346b.sqrt();
            } else {
                ECFieldElement solveQuadraticEquation = solveQuadraticEquation(fromBigInteger.square().invert().multiply(this.f1346b).add(this.f1345a).add(fromBigInteger));
                if (solveQuadraticEquation != null) {
                    if (solveQuadraticEquation.testBitZero() != (i == 1)) {
                        solveQuadraticEquation = solveQuadraticEquation.addOne();
                    }
                    int coordinateSystem = getCoordinateSystem();
                    if (coordinateSystem == 5 || coordinateSystem == 6) {
                        eCFieldElement = solveQuadraticEquation.add(fromBigInteger);
                    } else {
                        eCFieldElement = solveQuadraticEquation.multiply(fromBigInteger);
                    }
                } else {
                    eCFieldElement = null;
                }
            }
            if (eCFieldElement != null) {
                return createRawPoint(fromBigInteger, eCFieldElement, true);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        private ECFieldElement solveQuadraticEquation(ECFieldElement eCFieldElement) {
            ECFieldElement eCFieldElement2;
            if (eCFieldElement.isZero()) {
                return eCFieldElement;
            }
            ECFieldElement fromBigInteger = fromBigInteger(ECConstants.ZERO);
            Random random = new Random();
            do {
                ECFieldElement fromBigInteger2 = fromBigInteger(new BigInteger(this.f1350m, random));
                ECFieldElement eCFieldElement3 = eCFieldElement;
                eCFieldElement2 = fromBigInteger;
                for (int i = 1; i <= this.f1350m - 1; i++) {
                    ECFieldElement square = eCFieldElement3.square();
                    eCFieldElement2 = eCFieldElement2.square().add(square.multiply(fromBigInteger2));
                    eCFieldElement3 = square.add(eCFieldElement);
                }
                if (!eCFieldElement3.isZero()) {
                    return null;
                }
            } while (eCFieldElement2.square().add(eCFieldElement2).isZero());
            return eCFieldElement2;
        }

        public int getM() {
            return this.f1350m;
        }

        public boolean isTrinomial() {
            return this.f1348k2 == 0 && this.f1349k3 == 0;
        }

        public int getK1() {
            return this.f1347k1;
        }

        public int getK2() {
            return this.f1348k2;
        }

        public int getK3() {
            return this.f1349k3;
        }

        public BigInteger getN() {
            return this.order;
        }

        public BigInteger getH() {
            return this.cofactor;
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECCurve$Fp */
    public static class C3639Fp extends AbstractFp {
        private static final int FP_DEFAULT_COORDS = 4;
        C3641Fp infinity;

        /* renamed from: q */
        BigInteger f1353q;

        /* renamed from: r */
        BigInteger f1354r;

        public boolean supportsCoordinateSystem(int i) {
            return i == 0 || i == 1 || i == 2 || i == 4;
        }

        public C3639Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this(bigInteger, bigInteger2, bigInteger3, null, null);
        }

        public C3639Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
            super(bigInteger);
            this.f1353q = bigInteger;
            this.f1354r = C3640Fp.calculateResidue(bigInteger);
            this.infinity = new C3641Fp(this, null, null);
            this.f1345a = fromBigInteger(bigInteger2);
            this.f1346b = fromBigInteger(bigInteger3);
            this.order = bigInteger4;
            this.cofactor = bigInteger5;
            this.coord = 4;
        }

        protected C3639Fp(BigInteger bigInteger, BigInteger bigInteger2, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(bigInteger, bigInteger2, eCFieldElement, eCFieldElement2, null, null);
        }

        protected C3639Fp(BigInteger bigInteger, BigInteger bigInteger2, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(bigInteger);
            this.f1353q = bigInteger;
            this.f1354r = bigInteger2;
            this.infinity = new C3641Fp(this, null, null);
            this.f1345a = eCFieldElement;
            this.f1346b = eCFieldElement2;
            this.order = bigInteger3;
            this.cofactor = bigInteger4;
            this.coord = 4;
        }

        /* access modifiers changed from: protected */
        public ECCurve cloneCurve() {
            C3639Fp fp = new C3639Fp(this.f1353q, this.f1354r, this.f1345a, this.f1346b, this.order, this.cofactor);
            return fp;
        }

        public BigInteger getQ() {
            return this.f1353q;
        }

        public int getFieldSize() {
            return this.f1353q.bitLength();
        }

        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            return new C3640Fp(this.f1353q, this.f1354r, bigInteger);
        }

        /* access modifiers changed from: protected */
        public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            return new C3641Fp(this, eCFieldElement, eCFieldElement2, z);
        }

        /* access modifiers changed from: protected */
        public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            C3641Fp fp = new C3641Fp(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
            return fp;
        }

        public ECPoint importPoint(ECPoint eCPoint) {
            if (this != eCPoint.getCurve() && getCoordinateSystem() == 2 && !eCPoint.isInfinity()) {
                int coordinateSystem = eCPoint.getCurve().getCoordinateSystem();
                if (coordinateSystem == 2 || coordinateSystem == 3 || coordinateSystem == 4) {
                    C3641Fp fp = new C3641Fp(this, fromBigInteger(eCPoint.f1361x.toBigInteger()), fromBigInteger(eCPoint.f1362y.toBigInteger()), new ECFieldElement[]{fromBigInteger(eCPoint.f1363zs[0].toBigInteger())}, eCPoint.withCompression);
                    return fp;
                }
            }
            return super.importPoint(eCPoint);
        }

        public ECPoint getInfinity() {
            return this.infinity;
        }
    }

    /* access modifiers changed from: protected */
    public abstract ECCurve cloneCurve();

    /* access modifiers changed from: protected */
    public abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z);

    /* access modifiers changed from: protected */
    public abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z);

    /* access modifiers changed from: protected */
    public abstract ECPoint decompressPoint(int i, BigInteger bigInteger);

    public abstract ECFieldElement fromBigInteger(BigInteger bigInteger);

    public abstract int getFieldSize();

    public abstract ECPoint getInfinity();

    public boolean supportsCoordinateSystem(int i) {
        return i == 0;
    }

    public static int[] getAllCoordinateSystems() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    protected ECCurve(FiniteField finiteField) {
        this.field = finiteField;
    }

    public Config configure() {
        return new Config(this.coord, this.endomorphism, this.multiplier);
    }

    public ECPoint validatePoint(BigInteger bigInteger, BigInteger bigInteger2) {
        ECPoint createPoint = createPoint(bigInteger, bigInteger2);
        if (createPoint.isValid()) {
            return createPoint;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    public ECPoint validatePoint(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
        ECPoint createPoint = createPoint(bigInteger, bigInteger2, z);
        if (createPoint.isValid()) {
            return createPoint;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2) {
        return createPoint(bigInteger, bigInteger2, false);
    }

    public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
        return createRawPoint(fromBigInteger(bigInteger), fromBigInteger(bigInteger2), z);
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createDefaultMultiplier() {
        ECEndomorphism eCEndomorphism = this.endomorphism;
        if (eCEndomorphism instanceof GLVEndomorphism) {
            return new GLVMultiplier(this, (GLVEndomorphism) eCEndomorphism);
        }
        return new WNafL2RMultiplier();
    }

    public PreCompInfo getPreCompInfo(ECPoint eCPoint, String str) {
        PreCompInfo preCompInfo;
        checkPoint(eCPoint);
        synchronized (eCPoint) {
            Hashtable hashtable = eCPoint.preCompTable;
            if (hashtable == null) {
                preCompInfo = null;
            } else {
                preCompInfo = (PreCompInfo) hashtable.get(str);
            }
        }
        return preCompInfo;
    }

    public void setPreCompInfo(ECPoint eCPoint, String str, PreCompInfo preCompInfo) {
        checkPoint(eCPoint);
        synchronized (eCPoint) {
            Hashtable hashtable = eCPoint.preCompTable;
            if (hashtable == null) {
                hashtable = new Hashtable(4);
                eCPoint.preCompTable = hashtable;
            }
            hashtable.put(str, preCompInfo);
        }
    }

    public ECPoint importPoint(ECPoint eCPoint) {
        if (this == eCPoint.getCurve()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return getInfinity();
        }
        ECPoint normalize = eCPoint.normalize();
        return validatePoint(normalize.getXCoord().toBigInteger(), normalize.getYCoord().toBigInteger(), normalize.withCompression);
    }

    public void normalizeAll(ECPoint[] eCPointArr) {
        checkPoints(eCPointArr);
        if (getCoordinateSystem() != 0) {
            ECFieldElement[] eCFieldElementArr = new ECFieldElement[eCPointArr.length];
            int[] iArr = new int[eCPointArr.length];
            int i = 0;
            for (int i2 = 0; i2 < eCPointArr.length; i2++) {
                ECPoint eCPoint = eCPointArr[i2];
                if (eCPoint != null && !eCPoint.isNormalized()) {
                    eCFieldElementArr[i] = eCPoint.getZCoord(0);
                    int i3 = i + 1;
                    iArr[i] = i2;
                    i = i3;
                }
            }
            if (i != 0) {
                ECAlgorithms.montgomeryTrick(eCFieldElementArr, 0, i);
                for (int i4 = 0; i4 < i; i4++) {
                    int i5 = iArr[i4];
                    eCPointArr[i5] = eCPointArr[i5].normalize(eCFieldElementArr[i4]);
                }
            }
        }
    }

    public FiniteField getField() {
        return this.field;
    }

    public ECFieldElement getA() {
        return this.f1345a;
    }

    public ECFieldElement getB() {
        return this.f1346b;
    }

    public BigInteger getOrder() {
        return this.order;
    }

    public BigInteger getCofactor() {
        return this.cofactor;
    }

    public int getCoordinateSystem() {
        return this.coord;
    }

    public ECEndomorphism getEndomorphism() {
        return this.endomorphism;
    }

    public synchronized ECMultiplier getMultiplier() {
        if (this.multiplier == null) {
            this.multiplier = createDefaultMultiplier();
        }
        return this.multiplier;
    }

    public ECPoint decodePoint(byte[] bArr) {
        ECPoint eCPoint;
        int fieldSize = (getFieldSize() + 7) / 8;
        boolean z = false;
        byte b = bArr[0];
        if (b != 0) {
            if (b == 2 || b == 3) {
                if (bArr.length == fieldSize + 1) {
                    eCPoint = decompressPoint(b & 1, BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize));
                    if (!eCPoint.satisfiesCofactor()) {
                        throw new IllegalArgumentException("Invalid point");
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
            } else if (b != 4) {
                if (b != 6 && b != 7) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid point encoding 0x");
                    sb.append(Integer.toString(b, 16));
                    throw new IllegalArgumentException(sb.toString());
                } else if (bArr.length == (fieldSize * 2) + 1) {
                    BigInteger fromUnsignedByteArray = BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize);
                    BigInteger fromUnsignedByteArray2 = BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize);
                    boolean testBit = fromUnsignedByteArray2.testBit(0);
                    if (b == 7) {
                        z = true;
                    }
                    if (testBit == z) {
                        eCPoint = validatePoint(fromUnsignedByteArray, fromUnsignedByteArray2);
                    } else {
                        throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                }
            } else if (bArr.length == (fieldSize * 2) + 1) {
                eCPoint = validatePoint(BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize), BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize));
            } else {
                throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
            }
        } else if (bArr.length == 1) {
            eCPoint = getInfinity();
        } else {
            throw new IllegalArgumentException("Incorrect length for infinity encoding");
        }
        if (b == 0 || !eCPoint.isInfinity()) {
            return eCPoint;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    /* access modifiers changed from: protected */
    public void checkPoint(ECPoint eCPoint) {
        if (eCPoint == null || this != eCPoint.getCurve()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    /* access modifiers changed from: protected */
    public void checkPoints(ECPoint[] eCPointArr) {
        if (eCPointArr != null) {
            int i = 0;
            while (i < eCPointArr.length) {
                ECPoint eCPoint = eCPointArr[i];
                if (eCPoint == null || this == eCPoint.getCurve()) {
                    i++;
                } else {
                    throw new IllegalArgumentException("'points' entries must be null or on this curve");
                }
            }
            return;
        }
        throw new IllegalArgumentException("'points' cannot be null");
    }

    public boolean equals(ECCurve eCCurve) {
        return this == eCCurve || (eCCurve != null && getField().equals(eCCurve.getField()) && getA().toBigInteger().equals(eCCurve.getA().toBigInteger()) && getB().toBigInteger().equals(eCCurve.getB().toBigInteger()));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ECCurve) && equals((ECCurve) obj));
    }

    public int hashCode() {
        return (getField().hashCode() ^ Integers.rotateLeft(getA().toBigInteger().hashCode(), 8)) ^ Integers.rotateLeft(getB().toBigInteger().hashCode(), 16);
    }
}
