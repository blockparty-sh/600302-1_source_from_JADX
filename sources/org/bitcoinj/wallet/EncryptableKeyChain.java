package org.bitcoinj.wallet;

import javax.annotation.Nullable;
import org.bitcoinj.crypto.KeyCrypter;
import org.spongycastle.crypto.params.KeyParameter;

public interface EncryptableKeyChain extends KeyChain {
    boolean checkAESKey(KeyParameter keyParameter);

    boolean checkPassword(CharSequence charSequence);

    @Nullable
    KeyCrypter getKeyCrypter();

    EncryptableKeyChain toDecrypted(CharSequence charSequence);

    EncryptableKeyChain toDecrypted(KeyParameter keyParameter);

    EncryptableKeyChain toEncrypted(CharSequence charSequence);

    EncryptableKeyChain toEncrypted(KeyCrypter keyCrypter, KeyParameter keyParameter);
}
