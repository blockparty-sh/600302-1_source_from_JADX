package org.bitcoinj.wallet;

import javax.annotation.Nullable;
import org.bitcoinj.core.ECKey;

public interface KeyBag {
    @Nullable
    ECKey findKeyFromPubHash(byte[] bArr);

    @Nullable
    ECKey findKeyFromPubKey(byte[] bArr);

    @Nullable
    RedeemData findRedeemDataFromScriptHash(byte[] bArr);
}
