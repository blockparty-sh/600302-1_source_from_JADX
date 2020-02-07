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
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2m;

public class McElieceCCA2KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2";
    private int fieldPoly;
    private boolean initialized = false;

    /* renamed from: m */
    private int f1451m;
    private McElieceCCA2KeyGenerationParameters mcElieceCCA2Params;

    /* renamed from: n */
    private int f1452n;
    private SecureRandom random;

    /* renamed from: t */
    private int f1453t;

    private void initializeDefault() {
        init(new McElieceCCA2KeyGenerationParameters(new SecureRandom(), new McElieceCCA2Parameters()));
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.mcElieceCCA2Params = (McElieceCCA2KeyGenerationParameters) keyGenerationParameters;
        this.random = new SecureRandom();
        this.f1451m = this.mcElieceCCA2Params.getParameters().getM();
        this.f1452n = this.mcElieceCCA2Params.getParameters().getN();
        this.f1453t = this.mcElieceCCA2Params.getParameters().getT();
        this.fieldPoly = this.mcElieceCCA2Params.getParameters().getFieldPoly();
        this.initialized = true;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        GF2mField gF2mField = new GF2mField(this.f1451m, this.fieldPoly);
        PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(gF2mField, this.f1453t, 'I', this.random);
        PolynomialGF2mSmallM[] squareRootMatrix = new PolynomialRingGF2m(gF2mField, polynomialGF2mSmallM).getSquareRootMatrix();
        GF2Matrix createCanonicalCheckMatrix = GoppaCode.createCanonicalCheckMatrix(gF2mField, polynomialGF2mSmallM);
        MaMaPe computeSystematicForm = GoppaCode.computeSystematicForm(createCanonicalCheckMatrix, this.random);
        GF2Matrix secondMatrix = computeSystematicForm.getSecondMatrix();
        Permutation permutation = computeSystematicForm.getPermutation();
        GF2Matrix gF2Matrix = (GF2Matrix) secondMatrix.computeTranspose();
        int numRows = gF2Matrix.getNumRows();
        McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters = new McElieceCCA2PublicKeyParameters(OID, this.f1452n, this.f1453t, gF2Matrix, this.mcElieceCCA2Params.getParameters());
        McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters = new McElieceCCA2PrivateKeyParameters(OID, this.f1452n, numRows, gF2mField, polynomialGF2mSmallM, permutation, createCanonicalCheckMatrix, squareRootMatrix, this.mcElieceCCA2Params.getParameters());
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) mcElieceCCA2PublicKeyParameters, (AsymmetricKeyParameter) mcElieceCCA2PrivateKeyParameters);
    }
}
