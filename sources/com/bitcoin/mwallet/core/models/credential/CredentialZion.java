package com.bitcoin.mwallet.core.models.credential;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.mwallet.zion.ZionId;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/credential/CredentialZion;", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "id", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lcom/bitcoin/mwallet/zion/ZionId;)V", "getZionId", "()Lcom/bitcoin/mwallet/zion/ZionId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "credential_zion")
/* compiled from: CredentialZion.kt */
public final class CredentialZion extends Credential {
    @ColumnInfo(name = "zion_id")
    @NotNull
    private final ZionId zionId;

    @NotNull
    public final ZionId getZionId() {
        return this.zionId;
    }

    public CredentialZion(@NotNull CredentialId credentialId, @NotNull ZionId zionId2) {
        Intrinsics.checkParameterIsNotNull(credentialId, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(zionId2, "zionId");
        super(credentialId);
        this.zionId = zionId2;
    }
}
