package org.bitcoinj.crypto;

import java.io.Serializable;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.spongycastle.crypto.params.KeyParameter;

public interface KeyCrypter extends Serializable {
    byte[] decrypt(EncryptedData encryptedData, KeyParameter keyParameter) throws KeyCrypterException;

    KeyParameter deriveKey(CharSequence charSequence) throws KeyCrypterException;

    EncryptedData encrypt(byte[] bArr, KeyParameter keyParameter) throws KeyCrypterException;

    EncryptionType getUnderstoodEncryptionType();
}
