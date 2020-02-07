package com.bitcoin.mwallet.core.models.credential;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.bitcoink.PrivateKey;
import com.bitcoin.mwallet.core.dao.EncryptedAtRestList;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "id", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "mnemonic", "Lcom/bitcoin/mwallet/core/dao/EncryptedAtRestList;", "masterPrivKey", "Lcom/bitcoin/bitcoink/PrivateKey;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lcom/bitcoin/mwallet/core/dao/EncryptedAtRestList;Lcom/bitcoin/bitcoink/PrivateKey;)V", "getMasterPrivKey", "()Lcom/bitcoin/bitcoink/PrivateKey;", "getMnemonic", "()Lcom/bitcoin/mwallet/core/dao/EncryptedAtRestList;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "credential_mnemonic")
/* compiled from: CredentialMnemonic.kt */
public final class CredentialMnemonic extends Credential {
    @ColumnInfo(name = "master_priv_key")
    @NotNull
    private final PrivateKey masterPrivKey;
    @ColumnInfo(name = "mnemonic")
    @NotNull
    private final EncryptedAtRestList mnemonic;

    @NotNull
    public final EncryptedAtRestList getMnemonic() {
        return this.mnemonic;
    }

    @NotNull
    public final PrivateKey getMasterPrivKey() {
        return this.masterPrivKey;
    }

    public CredentialMnemonic(@NotNull CredentialId credentialId, @NotNull EncryptedAtRestList encryptedAtRestList, @NotNull PrivateKey privateKey) {
        Intrinsics.checkParameterIsNotNull(credentialId, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(encryptedAtRestList, "mnemonic");
        Intrinsics.checkParameterIsNotNull(privateKey, "masterPrivKey");
        super(credentialId);
        this.mnemonic = encryptedAtRestList;
        this.masterPrivKey = privateKey;
    }
}
