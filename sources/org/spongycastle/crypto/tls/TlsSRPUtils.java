package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;
import org.spongycastle.util.Integers;

public class TlsSRPUtils {
    public static final Integer EXT_SRP = Integers.valueOf(12);

    public static void addSRPExtension(Hashtable hashtable, byte[] bArr) throws IOException {
        hashtable.put(EXT_SRP, createSRPExtension(bArr));
    }

    public static byte[] getSRPExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_SRP);
        if (extensionData == null) {
            return null;
        }
        return readSRPExtension(extensionData);
    }

    public static byte[] createSRPExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            return TlsUtils.encodeOpaque8(bArr);
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] readSRPExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return readOpaque8;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }
}
