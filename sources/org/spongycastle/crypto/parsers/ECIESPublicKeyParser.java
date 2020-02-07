package org.spongycastle.crypto.parsers;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;

public class ECIESPublicKeyParser implements KeyParser {
    private ECDomainParameters ecParams;

    public ECIESPublicKeyParser(ECDomainParameters eCDomainParameters) {
        this.ecParams = eCDomainParameters;
    }

    public AsymmetricKeyParameter readKey(InputStream inputStream) throws IOException {
        byte[] bArr;
        int read = inputStream.read();
        if (read != 0) {
            if (read == 2 || read == 3) {
                bArr = new byte[(((this.ecParams.getCurve().getFieldSize() + 7) / 8) + 1)];
            } else if (read == 4 || read == 6 || read == 7) {
                bArr = new byte[((((this.ecParams.getCurve().getFieldSize() + 7) / 8) * 2) + 1)];
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Sender's public key has invalid point encoding 0x");
                sb.append(Integer.toString(read, 16));
                throw new IOException(sb.toString());
            }
            bArr[0] = (byte) read;
            inputStream.read(bArr, 1, bArr.length - 1);
            return new ECPublicKeyParameters(this.ecParams.getCurve().decodePoint(bArr), this.ecParams);
        }
        throw new IOException("Sender's public key invalid.");
    }
}
