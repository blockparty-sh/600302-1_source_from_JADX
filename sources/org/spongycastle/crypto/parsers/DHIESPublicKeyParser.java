package org.spongycastle.crypto.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;

public class DHIESPublicKeyParser implements KeyParser {
    private DHParameters dhParams;

    public DHIESPublicKeyParser(DHParameters dHParameters) {
        this.dhParams = dHParameters;
    }

    public AsymmetricKeyParameter readKey(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[((this.dhParams.getP().bitLength() + 7) / 8)];
        inputStream.read(bArr, 0, bArr.length);
        return new DHPublicKeyParameters(new BigInteger(1, bArr), this.dhParams);
    }
}
