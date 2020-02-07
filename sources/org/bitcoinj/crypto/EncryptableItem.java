package org.bitcoinj.crypto;

import javax.annotation.Nullable;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;

public interface EncryptableItem {
    long getCreationTimeSeconds();

    @Nullable
    EncryptedData getEncryptedData();

    EncryptionType getEncryptionType();

    @Nullable
    byte[] getSecretBytes();

    boolean isEncrypted();
}
