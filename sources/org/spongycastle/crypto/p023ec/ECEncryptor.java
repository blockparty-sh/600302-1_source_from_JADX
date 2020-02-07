package org.spongycastle.crypto.p023ec;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.math.p025ec.ECPoint;

/* renamed from: org.spongycastle.crypto.ec.ECEncryptor */
public interface ECEncryptor {
    ECPair encrypt(ECPoint eCPoint);

    void init(CipherParameters cipherParameters);
}
