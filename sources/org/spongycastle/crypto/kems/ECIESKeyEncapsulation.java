package org.spongycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.KeyEncapsulation;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECMultiplier;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.FixedPointCombMultiplier;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public class ECIESKeyEncapsulation implements KeyEncapsulation {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;
    private DerivationFunction kdf;
    private ECKeyParameters key;
    private SecureRandom rnd;

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = false;
        this.OldCofactorMode = false;
        this.SingleHashMode = false;
    }

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom, boolean z, boolean z2, boolean z3) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = z;
        this.OldCofactorMode = z2;
        this.SingleHashMode = z3;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ECKeyParameters) {
            this.key = (ECKeyParameters) cipherParameters;
            return;
        }
        throw new IllegalArgumentException("EC key required");
    }

    public CipherParameters encrypt(byte[] bArr, int i, int i2) throws IllegalArgumentException {
        ECKeyParameters eCKeyParameters = this.key;
        if (eCKeyParameters instanceof ECPublicKeyParameters) {
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) eCKeyParameters;
            ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
            ECCurve curve = parameters.getCurve();
            BigInteger n = parameters.getN();
            BigInteger h = parameters.getH();
            BigInteger createRandomInRange = BigIntegers.createRandomInRange(ONE, n, this.rnd);
            ECPoint[] eCPointArr = {createBasePointMultiplier().multiply(parameters.getG(), createRandomInRange), eCPublicKeyParameters.getQ().multiply(this.CofactorMode ? createRandomInRange.multiply(h).mod(n) : createRandomInRange)};
            curve.normalizeAll(eCPointArr);
            ECPoint eCPoint = eCPointArr[0];
            ECPoint eCPoint2 = eCPointArr[1];
            byte[] encoded = eCPoint.getEncoded();
            System.arraycopy(encoded, 0, bArr, i, encoded.length);
            byte[] encoded2 = eCPoint2.getAffineXCoord().getEncoded();
            if (this.SingleHashMode) {
                encoded2 = Arrays.concatenate(encoded, encoded2);
            }
            this.kdf.init(new KDFParameters(encoded2, null));
            byte[] bArr2 = new byte[i2];
            this.kdf.generateBytes(bArr2, 0, bArr2.length);
            return new KeyParameter(bArr2);
        }
        throw new IllegalArgumentException("Public key required for encryption");
    }

    public CipherParameters encrypt(byte[] bArr, int i) {
        return encrypt(bArr, 0, i);
    }

    public CipherParameters decrypt(byte[] bArr, int i, int i2, int i3) throws IllegalArgumentException {
        ECKeyParameters eCKeyParameters = this.key;
        if (eCKeyParameters instanceof ECPrivateKeyParameters) {
            ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) eCKeyParameters;
            ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
            ECCurve curve = parameters.getCurve();
            BigInteger n = parameters.getN();
            BigInteger h = parameters.getH();
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            ECPoint decodePoint = curve.decodePoint(bArr2);
            if (this.CofactorMode || this.OldCofactorMode) {
                decodePoint = decodePoint.multiply(h);
            }
            BigInteger d = eCPrivateKeyParameters.getD();
            if (this.CofactorMode) {
                d = d.multiply(h.modInverse(n)).mod(n);
            }
            byte[] encoded = decodePoint.multiply(d).normalize().getAffineXCoord().getEncoded();
            if (this.SingleHashMode) {
                encoded = Arrays.concatenate(bArr2, encoded);
            }
            this.kdf.init(new KDFParameters(encoded, null));
            byte[] bArr3 = new byte[i3];
            this.kdf.generateBytes(bArr3, 0, bArr3.length);
            return new KeyParameter(bArr3);
        }
        throw new IllegalArgumentException("Private key required for encryption");
    }

    public CipherParameters decrypt(byte[] bArr, int i) {
        return decrypt(bArr, 0, bArr.length, i);
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
