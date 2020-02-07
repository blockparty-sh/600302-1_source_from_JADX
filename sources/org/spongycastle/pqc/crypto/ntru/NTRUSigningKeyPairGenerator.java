package org.spongycastle.pqc.crypto.ntru;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.ntru.NTRUSigningPrivateKeyParameters.Basis;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;

public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters params;

    private class BasisGenerationTask implements Callable<Basis> {
        private BasisGenerationTask() {
        }

        public Basis call() throws Exception {
            return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
        }
    }

    public class FGBasis extends Basis {

        /* renamed from: F */
        public IntegerPolynomial f1517F;

        /* renamed from: G */
        public IntegerPolynomial f1518G;

        FGBasis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            super(polynomial, polynomial2, integerPolynomial, nTRUSigningKeyGenerationParameters);
            this.f1517F = integerPolynomial2;
            this.f1518G = integerPolynomial3;
        }

        /* access modifiers changed from: 0000 */
        public boolean isNormOk() {
            double d = this.params.keyNormBoundSq;
            int i = this.params.f1516q;
            return ((double) this.f1517F.centeredNormSq(i)) < d && ((double) this.f1518G.centeredNormSq(i)) < d;
        }
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUSigningKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        int i = this.params.f1510B;
        while (true) {
            nTRUSigningPublicKeyParameters = null;
            if (i < 0) {
                break;
            }
            arrayList.add(newCachedThreadPool.submit(new BasisGenerationTask()));
            i--;
        }
        newCachedThreadPool.shutdown();
        ArrayList arrayList2 = new ArrayList();
        int i2 = this.params.f1510B;
        while (i2 >= 0) {
            Future future = (Future) arrayList.get(i2);
            try {
                arrayList2.add(future.get());
                if (i2 == this.params.f1510B) {
                    nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(((Basis) future.get()).f1527h, this.params.getSigningParameters());
                }
                i2--;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters((List<Basis>) arrayList2, nTRUSigningPublicKeyParameters));
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
        ArrayList arrayList = new ArrayList();
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = null;
        for (int i = this.params.f1510B; i >= 0; i--) {
            Basis generateBoundedBasis = generateBoundedBasis();
            arrayList.add(generateBoundedBasis);
            if (i == 0) {
                nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(generateBoundedBasis.f1527h, this.params.getSigningParameters());
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters((List<Basis>) arrayList, nTRUSigningPublicKeyParameters));
    }

    private void minimizeFG(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, IntegerPolynomial integerPolynomial4, int i) {
        IntegerPolynomial integerPolynomial5 = integerPolynomial;
        IntegerPolynomial integerPolynomial6 = integerPolynomial2;
        IntegerPolynomial integerPolynomial7 = integerPolynomial3;
        IntegerPolynomial integerPolynomial8 = integerPolynomial4;
        int i2 = i;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += i2 * 2 * ((integerPolynomial5.coeffs[i4] * integerPolynomial5.coeffs[i4]) + (integerPolynomial6.coeffs[i4] * integerPolynomial6.coeffs[i4]));
        }
        int i5 = i3 - 4;
        IntegerPolynomial integerPolynomial9 = (IntegerPolynomial) integerPolynomial.clone();
        IntegerPolynomial integerPolynomial10 = (IntegerPolynomial) integerPolynomial2.clone();
        int i6 = 0;
        int i7 = 0;
        while (i6 < i2 && i7 < i2) {
            int i8 = 0;
            for (int i9 = 0; i9 < i2; i9++) {
                i8 += i2 * 4 * ((integerPolynomial7.coeffs[i9] * integerPolynomial5.coeffs[i9]) + (integerPolynomial8.coeffs[i9] * integerPolynomial6.coeffs[i9]));
            }
            int sumCoeffs = i8 - ((integerPolynomial3.sumCoeffs() + integerPolynomial4.sumCoeffs()) * 4);
            if (sumCoeffs > i5) {
                integerPolynomial7.sub(integerPolynomial9);
                integerPolynomial8.sub(integerPolynomial10);
            } else if (sumCoeffs < (-i5)) {
                integerPolynomial7.add(integerPolynomial9);
                integerPolynomial8.add(integerPolynomial10);
            } else {
                i7++;
                integerPolynomial9.rotate1();
                integerPolynomial10.rotate1();
            }
            i6++;
            i7 = 0;
            i7++;
            integerPolynomial9.rotate1();
            integerPolynomial10.rotate1();
        }
    }

    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r6v5, types: [org.spongycastle.pqc.math.ntru.polynomial.Polynomial] */
    /* JADX WARNING: type inference failed for: r3v12, types: [org.spongycastle.pqc.math.ntru.polynomial.Polynomial] */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r1v34, types: [org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial] */
    /* JADX WARNING: type inference failed for: r1v36, types: [org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial] */
    /* JADX WARNING: type inference failed for: r1v44 */
    /* JADX WARNING: type inference failed for: r1v45 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyPairGenerator.FGBasis generateBasis() {
        /*
            r22 = this;
            r8 = r22
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r0 = r8.params
            int r7 = r0.f1511N
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r0 = r8.params
            int r9 = r0.f1516q
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r0 = r8.params
            int r0 = r0.f1512d
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r1 = r8.params
            int r10 = r1.f1513d1
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r1 = r8.params
            int r11 = r1.f1514d2
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r1 = r8.params
            int r12 = r1.f1515d3
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r1 = r8.params
            int r13 = r1.basisType
            int r1 = r7 * 2
            r14 = 1
            int r15 = r1 + 1
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r1 = r8.params
            boolean r6 = r1.primeCheck
        L_0x0027:
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r1 = r8.params
            int r1 = r1.polyType
            if (r1 != 0) goto L_0x003b
            int r1 = r0 + 1
            java.security.SecureRandom r2 = new java.security.SecureRandom
            r2.<init>()
            org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial r1 = org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial.generateRandom(r7, r1, r0, r2)
            r17 = r6
            goto L_0x004e
        L_0x003b:
            int r4 = r12 + 1
            java.security.SecureRandom r16 = new java.security.SecureRandom
            r16.<init>()
            r1 = r7
            r2 = r10
            r3 = r11
            r5 = r12
            r17 = r6
            r6 = r16
            org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial r1 = org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial.generateRandom(r1, r2, r3, r4, r5, r6)
        L_0x004e:
            r6 = r1
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r5 = r6.toIntegerPolynomial()
            if (r17 == 0) goto L_0x0063
            org.spongycastle.pqc.math.ntru.polynomial.ModularResultant r1 = r5.resultant(r15)
            java.math.BigInteger r1 = r1.res
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0202
        L_0x0063:
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r4 = r5.invertFq(r9)
            if (r4 == 0) goto L_0x0202
            org.spongycastle.pqc.math.ntru.polynomial.Resultant r3 = r5.resultant()
        L_0x006d:
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r1 = r8.params
            int r1 = r1.polyType
            if (r1 != 0) goto L_0x0089
            int r1 = r0 + 1
            java.security.SecureRandom r2 = new java.security.SecureRandom
            r2.<init>()
            org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial r1 = org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial.generateRandom(r7, r1, r0, r2)
            r14 = r3
            r20 = r10
            r16 = r11
            r21 = r12
            r10 = r4
            r11 = r5
            r12 = r6
            goto L_0x00a6
        L_0x0089:
            int r16 = r12 + 1
            java.security.SecureRandom r18 = new java.security.SecureRandom
            r18.<init>()
            r1 = r7
            r2 = r10
            r14 = r3
            r3 = r11
            r20 = r10
            r10 = r4
            r4 = r16
            r16 = r11
            r11 = r5
            r5 = r12
            r21 = r12
            r12 = r6
            r6 = r18
            org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial r1 = org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial.generateRandom(r1, r2, r3, r4, r5, r6)
        L_0x00a6:
            r6 = r1
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r2 = r6.toIntegerPolynomial()
            if (r17 == 0) goto L_0x00bb
            org.spongycastle.pqc.math.ntru.polynomial.ModularResultant r1 = r2.resultant(r15)
            java.math.BigInteger r1 = r1.res
            java.math.BigInteger r3 = java.math.BigInteger.ZERO
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x01f5
        L_0x00bb:
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r1 = r2.invertFq(r9)
            if (r1 == 0) goto L_0x01f3
            org.spongycastle.pqc.math.ntru.polynomial.Resultant r1 = r2.resultant()
            java.math.BigInteger r3 = r14.res
            java.math.BigInteger r4 = r1.res
            org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean r3 = org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean.calculate(r3, r4)
            java.math.BigInteger r4 = r3.gcd
            java.math.BigInteger r5 = java.math.BigInteger.ONE
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x01f3
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r0 = r14.rho
            java.lang.Object r0 = r0.clone()
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r0 = (org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial) r0
            java.math.BigInteger r4 = r3.f1554x
            r18 = r13
            r5 = r14
            long r13 = (long) r9
            java.math.BigInteger r13 = java.math.BigInteger.valueOf(r13)
            java.math.BigInteger r4 = r4.multiply(r13)
            r0.mult(r4)
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r4 = r1.rho
            java.lang.Object r4 = r4.clone()
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r4 = (org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial) r4
            java.math.BigInteger r3 = r3.f1555y
            int r13 = -r9
            long r13 = (long) r13
            java.math.BigInteger r13 = java.math.BigInteger.valueOf(r13)
            java.math.BigInteger r3 = r3.multiply(r13)
            r4.mult(r3)
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r3 = r8.params
            int r3 = r3.keyGenAlg
            r13 = 0
            if (r3 != 0) goto L_0x0162
            int[] r1 = new int[r7]
            int[] r3 = new int[r7]
            int[] r5 = r11.coeffs
            r5 = r5[r13]
            r1[r13] = r5
            int[] r5 = r2.coeffs
            r5 = r5[r13]
            r3[r13] = r5
            r5 = 1
        L_0x011f:
            if (r5 >= r7) goto L_0x0132
            int[] r13 = r11.coeffs
            int r14 = r7 - r5
            r13 = r13[r14]
            r1[r5] = r13
            int[] r13 = r2.coeffs
            r13 = r13[r14]
            r3[r5] = r13
            int r5 = r5 + 1
            goto L_0x011f
        L_0x0132:
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r5 = new org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial
            r5.<init>(r1)
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r1 = new org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial
            r1.<init>(r3)
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r3 = r12.mult(r5)
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r13 = r6.mult(r1)
            r3.add(r13)
            org.spongycastle.pqc.math.ntru.polynomial.Resultant r3 = r3.resultant()
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r5 = r5.mult(r4)
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r1 = r1.mult(r0)
            r5.add(r1)
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r1 = r3.rho
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r1 = r5.mult(r1)
            java.math.BigInteger r3 = r3.res
            r1.div(r3)
            goto L_0x01a6
        L_0x0162:
            r3 = 1
        L_0x0163:
            if (r3 >= r7) goto L_0x016a
            int r13 = r13 + 1
            int r3 = r3 * 10
            goto L_0x0163
        L_0x016a:
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r3 = r5.rho
            java.math.BigDecimal r14 = new java.math.BigDecimal
            java.math.BigInteger r5 = r5.res
            r14.<init>(r5)
            int r5 = r4.getMaxCoeffLength()
            r19 = 1
            int r5 = r5 + 1
            int r5 = r5 + r13
            org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial r3 = r3.div(r14, r5)
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r5 = r1.rho
            java.math.BigDecimal r14 = new java.math.BigDecimal
            java.math.BigInteger r1 = r1.res
            r14.<init>(r1)
            int r1 = r0.getMaxCoeffLength()
            int r1 = r1 + 1
            int r1 = r1 + r13
            org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial r1 = r5.div(r14, r1)
            org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial r3 = r3.mult(r4)
            org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial r1 = r1.mult(r0)
            r3.add(r1)
            r3.halve()
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r1 = r3.round()
        L_0x01a6:
            java.lang.Object r3 = r4.clone()
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r3 = (org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial) r3
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r4 = r12.mult(r1)
            r3.sub(r4)
            java.lang.Object r0 = r0.clone()
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r0 = (org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial) r0
            org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial r1 = r6.mult(r1)
            r0.sub(r1)
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r13 = new org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial
            r13.<init>(r3)
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r14 = new org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial
            r14.<init>(r0)
            r0 = r22
            r1 = r11
            r3 = r13
            r4 = r14
            r5 = r7
            r0.minimizeFG(r1, r2, r3, r4, r5)
            if (r18 != 0) goto L_0x01dc
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r0 = r6.mult(r10, r9)
            r4 = r0
            r3 = r13
            goto L_0x01e2
        L_0x01dc:
            org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial r0 = r13.mult(r10, r9)
            r4 = r0
            r3 = r6
        L_0x01e2:
            r4.modPositive(r9)
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyPairGenerator$FGBasis r9 = new org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyPairGenerator$FGBasis
            org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters r7 = r8.params
            r0 = r9
            r1 = r22
            r2 = r12
            r5 = r13
            r6 = r14
            r0.<init>(r2, r3, r4, r5, r6, r7)
            return r9
        L_0x01f3:
            r19 = 1
        L_0x01f5:
            r4 = r10
            r5 = r11
            r6 = r12
            r3 = r14
            r11 = r16
            r10 = r20
            r12 = r21
            r14 = 1
            goto L_0x006d
        L_0x0202:
            r6 = r17
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyPairGenerator.generateBasis():org.spongycastle.pqc.crypto.ntru.NTRUSigningKeyPairGenerator$FGBasis");
    }

    public Basis generateBoundedBasis() {
        FGBasis generateBasis;
        do {
            generateBasis = generateBasis();
        } while (!generateBasis.isNormOk());
        return generateBasis;
    }
}
