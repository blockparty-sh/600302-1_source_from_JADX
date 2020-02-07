package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Arrays;

public abstract class DTLSProtocol {
    protected final SecureRandom secureRandom;

    protected DTLSProtocol(SecureRandom secureRandom2) {
        if (secureRandom2 != null) {
            this.secureRandom = secureRandom2;
            return;
        }
        throw new IllegalArgumentException("'secureRandom' cannot be null");
    }

    /* access modifiers changed from: protected */
    public void processFinished(byte[] bArr, byte[] bArr2) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        byte[] readFully = TlsUtils.readFully(bArr2.length, (InputStream) byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!Arrays.constantTimeAreEqual(bArr2, readFully)) {
            throw new TlsFatalAlert(40);
        }
    }

    protected static short evaluateMaxFragmentLengthExtension(Hashtable hashtable, Hashtable hashtable2, short s) throws IOException {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s);
    }

    protected static byte[] generateCertificate(Certificate certificate) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificate.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected static byte[] generateSupplementalData(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsProtocol.writeSupplementalData(byteArrayOutputStream, vector);
        return byteArrayOutputStream.toByteArray();
    }

    protected static void validateSelectedCipherSuite(int i, short s) throws IOException {
        int encryptionAlgorithm = TlsUtils.getEncryptionAlgorithm(i);
        if (encryptionAlgorithm == 1 || encryptionAlgorithm == 2) {
            throw new TlsFatalAlert(s);
        }
    }
}
