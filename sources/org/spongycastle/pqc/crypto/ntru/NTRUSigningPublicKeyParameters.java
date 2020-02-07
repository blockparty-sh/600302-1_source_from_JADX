package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

public class NTRUSigningPublicKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: h */
    public IntegerPolynomial f1528h;
    private NTRUSigningParameters params;

    public NTRUSigningPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.f1528h = integerPolynomial;
        this.params = nTRUSigningParameters;
    }

    public NTRUSigningPublicKeyParameters(byte[] bArr, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.f1528h = IntegerPolynomial.fromBinary(bArr, nTRUSigningParameters.f1520N, nTRUSigningParameters.f1525q);
        this.params = nTRUSigningParameters;
    }

    public NTRUSigningPublicKeyParameters(InputStream inputStream, NTRUSigningParameters nTRUSigningParameters) throws IOException {
        super(false);
        this.f1528h = IntegerPolynomial.fromBinary(inputStream, nTRUSigningParameters.f1520N, nTRUSigningParameters.f1525q);
        this.params = nTRUSigningParameters;
    }

    public byte[] getEncoded() {
        return this.f1528h.toBinary(this.params.f1525q);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.f1528h;
        int i = 0;
        int hashCode = ((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31;
        NTRUSigningParameters nTRUSigningParameters = this.params;
        if (nTRUSigningParameters != null) {
            i = nTRUSigningParameters.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = (NTRUSigningPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.f1528h;
        if (integerPolynomial == null) {
            if (nTRUSigningPublicKeyParameters.f1528h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUSigningPublicKeyParameters.f1528h)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = this.params;
        if (nTRUSigningParameters == null) {
            if (nTRUSigningPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!nTRUSigningParameters.equals(nTRUSigningPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }
}
