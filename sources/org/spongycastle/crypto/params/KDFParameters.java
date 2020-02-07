package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

public class KDFParameters implements DerivationParameters {

    /* renamed from: iv */
    byte[] f1316iv;
    byte[] shared;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.shared = bArr;
        this.f1316iv = bArr2;
    }

    public byte[] getSharedSecret() {
        return this.shared;
    }

    public byte[] getIV() {
        return this.f1316iv;
    }
}
