package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {

    /* renamed from: h */
    public IntegerPolynomial f1509h;

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.f1509h = integerPolynomial;
    }

    public NTRUEncryptionPublicKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.f1509h = IntegerPolynomial.fromBinary(bArr, nTRUEncryptionParameters.f1499N, nTRUEncryptionParameters.f1505q);
    }

    public NTRUEncryptionPublicKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) throws IOException {
        super(false, nTRUEncryptionParameters);
        this.f1509h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.f1499N, nTRUEncryptionParameters.f1505q);
    }

    public byte[] getEncoded() {
        return this.f1509h.toBinary(this.params.f1505q);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.f1509h;
        int i = 0;
        int hashCode = ((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31;
        if (this.params != null) {
            i = this.params.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPublicKeyParameters)) {
            return false;
        }
        NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.f1509h;
        if (integerPolynomial == null) {
            if (nTRUEncryptionPublicKeyParameters.f1509h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUEncryptionPublicKeyParameters.f1509h)) {
            return false;
        }
        if (this.params == null) {
            if (nTRUEncryptionPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!this.params.equals(nTRUEncryptionPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }
}
