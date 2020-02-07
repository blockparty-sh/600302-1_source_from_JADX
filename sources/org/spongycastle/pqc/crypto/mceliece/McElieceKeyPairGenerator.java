package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode.MaMaPe;
import org.spongycastle.pqc.math.linearalgebra.Matrix;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2m;

public class McElieceKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    private int fieldPoly;
    private boolean initialized = false;

    /* renamed from: m */
    private int f1464m;
    private McElieceKeyGenerationParameters mcElieceParams;

    /* renamed from: n */
    private int f1465n;
    private SecureRandom random;

    /* renamed from: t */
    private int f1466t;

    private void initializeDefault() {
        initialize(new McElieceKeyGenerationParameters(new SecureRandom(), new McElieceParameters()));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.mcElieceParams = (McElieceKeyGenerationParameters) keyGenerationParameters;
        this.random = new SecureRandom();
        this.f1464m = this.mcElieceParams.getParameters().getM();
        this.f1465n = this.mcElieceParams.getParameters().getN();
        this.f1466t = this.mcElieceParams.getParameters().getT();
        this.fieldPoly = this.mcElieceParams.getParameters().getFieldPoly();
        this.initialized = true;
    }

    private AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        GF2mField gF2mField = new GF2mField(this.f1464m, this.fieldPoly);
        PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(gF2mField, this.f1466t, 'I', this.random);
        PolynomialGF2mSmallM[] squareRootMatrix = new PolynomialRingGF2m(gF2mField, polynomialGF2mSmallM).getSquareRootMatrix();
        GF2Matrix createCanonicalCheckMatrix = GoppaCode.createCanonicalCheckMatrix(gF2mField, polynomialGF2mSmallM);
        MaMaPe computeSystematicForm = GoppaCode.computeSystematicForm(createCanonicalCheckMatrix, this.random);
        GF2Matrix secondMatrix = computeSystematicForm.getSecondMatrix();
        Permutation permutation = computeSystematicForm.getPermutation();
        GF2Matrix gF2Matrix = (GF2Matrix) secondMatrix.computeTranspose();
        GF2Matrix extendLeftCompactForm = gF2Matrix.extendLeftCompactForm();
        int numRows = gF2Matrix.getNumRows();
        GF2Matrix[] createRandomRegularMatrixAndItsInverse = GF2Matrix.createRandomRegularMatrixAndItsInverse(numRows, this.random);
        Permutation permutation2 = new Permutation(this.f1465n, this.random);
        GF2Matrix gF2Matrix2 = (GF2Matrix) ((GF2Matrix) createRandomRegularMatrixAndItsInverse[0].rightMultiply((Matrix) extendLeftCompactForm)).rightMultiply(permutation2);
        String str = "1.3.6.1.4.1.8301.3.1.3.4.1";
        McEliecePublicKeyParameters mcEliecePublicKeyParameters = new McEliecePublicKeyParameters(str, this.f1465n, this.f1466t, gF2Matrix2, this.mcElieceParams.getParameters());
        String str2 = "1.3.6.1.4.1.8301.3.1.3.4.1";
        McEliecePublicKeyParameters mcEliecePublicKeyParameters2 = mcEliecePublicKeyParameters;
        McEliecePrivateKeyParameters mcEliecePrivateKeyParameters = new McEliecePrivateKeyParameters(str2, this.f1465n, numRows, gF2mField, polynomialGF2mSmallM, createRandomRegularMatrixAndItsInverse[1], permutation, permutation2, createCanonicalCheckMatrix, squareRootMatrix, this.mcElieceParams.getParameters());
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) mcEliecePublicKeyParameters2, (AsymmetricKeyParameter) mcEliecePrivateKeyParameters);
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }
}
