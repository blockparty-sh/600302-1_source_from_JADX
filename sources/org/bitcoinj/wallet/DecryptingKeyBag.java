package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.KeyIsEncryptedException;
import org.spongycastle.crypto.params.KeyParameter;

public class DecryptingKeyBag implements KeyBag {
    protected final KeyParameter aesKey;
    protected final KeyBag target;

    public DecryptingKeyBag(KeyBag keyBag, @Nullable KeyParameter keyParameter) {
        this.target = (KeyBag) Preconditions.checkNotNull(keyBag);
        this.aesKey = keyParameter;
    }

    @Nullable
    private ECKey maybeDecrypt(ECKey eCKey) {
        if (eCKey == null) {
            return null;
        }
        if (!eCKey.isEncrypted()) {
            return eCKey;
        }
        KeyParameter keyParameter = this.aesKey;
        if (keyParameter != null) {
            return eCKey.decrypt(keyParameter);
        }
        throw new KeyIsEncryptedException();
    }

    private RedeemData maybeDecrypt(RedeemData redeemData) {
        ArrayList arrayList = new ArrayList();
        for (ECKey maybeDecrypt : redeemData.keys) {
            arrayList.add(maybeDecrypt(maybeDecrypt));
        }
        return RedeemData.m343of((List<ECKey>) arrayList, redeemData.redeemScript);
    }

    @Nullable
    public ECKey findKeyFromPubHash(byte[] bArr) {
        return maybeDecrypt(this.target.findKeyFromPubHash(bArr));
    }

    @Nullable
    public ECKey findKeyFromPubKey(byte[] bArr) {
        return maybeDecrypt(this.target.findKeyFromPubKey(bArr));
    }

    @Nullable
    public RedeemData findRedeemDataFromScriptHash(byte[] bArr) {
        return maybeDecrypt(this.target.findRedeemDataFromScriptHash(bArr));
    }
}
