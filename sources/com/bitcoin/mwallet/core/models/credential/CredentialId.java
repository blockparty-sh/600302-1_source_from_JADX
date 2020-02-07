package com.bitcoin.mwallet.core.models.credential;

import androidx.room.ColumnInfo;
import com.bitcoin.mwallet.core.models.user.UserId;
import com.leanplum.internal.Constants.Params;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "", "id", "", "type", "Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "(Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/credential/CredentialType;)V", "getId", "()Ljava/lang/String;", "getType", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CredentialId.kt */
public final class CredentialId {
    public static final Companion Companion = new Companion(null);
    @ColumnInfo(name = "id")
    @NotNull

    /* renamed from: id */
    private final String f367id;
    @ColumnInfo(name = "type")
    @NotNull
    private final CredentialType type;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/credential/CredentialId$Companion;", "", "()V", "from", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "type", "Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "userId", "Lcom/bitcoin/mwallet/core/models/user/UserId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CredentialId.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CredentialId from(@NotNull CredentialType credentialType) {
            Intrinsics.checkParameterIsNotNull(credentialType, "type");
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            return new CredentialId(uuid, credentialType);
        }

        @NotNull
        public final CredentialId from(@NotNull UserId userId) {
            Intrinsics.checkParameterIsNotNull(userId, Params.USER_ID);
            String id = userId.getId();
            if (id == null) {
                id = "nullUserId";
            }
            return new CredentialId(id, CredentialType.MNEMONIC_AND_PRIVATE_KEY);
        }
    }

    @NotNull
    public static /* synthetic */ CredentialId copy$default(CredentialId credentialId, String str, CredentialType credentialType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = credentialId.f367id;
        }
        if ((i & 2) != 0) {
            credentialType = credentialId.type;
        }
        return credentialId.copy(str, credentialType);
    }

    @NotNull
    public final String component1() {
        return this.f367id;
    }

    @NotNull
    public final CredentialType component2() {
        return this.type;
    }

    @NotNull
    public final CredentialId copy(@NotNull String str, @NotNull CredentialType credentialType) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(credentialType, "type");
        return new CredentialId(str, credentialType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.type, (java.lang.Object) r3.type) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.credential.CredentialId
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.models.credential.CredentialId r3 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r3
            java.lang.String r0 = r2.f367id
            java.lang.String r1 = r3.f367id
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.models.credential.CredentialType r0 = r2.type
            com.bitcoin.mwallet.core.models.credential.CredentialType r3 = r3.type
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.credential.CredentialId.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.f367id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        CredentialType credentialType = this.type;
        if (credentialType != null) {
            i = credentialType.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CredentialId(id=");
        sb.append(this.f367id);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(")");
        return sb.toString();
    }

    public CredentialId(@NotNull String str, @NotNull CredentialType credentialType) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(credentialType, "type");
        this.f367id = str;
        this.type = credentialType;
    }

    @NotNull
    public final String getId() {
        return this.f367id;
    }

    @NotNull
    public final CredentialType getType() {
        return this.type;
    }
}
