package com.bitcoin.mwallet.core.models.credential;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/credential/CredentialEncrypted;", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "id", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "encryptedMnemonic", "", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Ljava/lang/String;)V", "getEncryptedMnemonic", "()Ljava/lang/String;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "credential_encrypted")
/* compiled from: CredentialEncrypted.kt */
public final class CredentialEncrypted extends Credential {
    @ColumnInfo(name = "encrypted_mnmeonic")
    @NotNull
    private final String encryptedMnemonic;

    @NotNull
    public final String getEncryptedMnemonic() {
        return this.encryptedMnemonic;
    }

    public CredentialEncrypted(@NotNull CredentialId credentialId, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(credentialId, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str, "encryptedMnemonic");
        super(credentialId);
        this.encryptedMnemonic = str;
    }
}
