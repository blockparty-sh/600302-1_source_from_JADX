package com.bitcoin.mwallet.core.models.user;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.bitcoin.bitcoink.Mnemonic;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/user/User;", "", "id", "Lcom/bitcoin/mwallet/core/models/user/UserId;", "credentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "(Lcom/bitcoin/mwallet/core/models/user/UserId;Lcom/bitcoin/mwallet/core/models/credential/CredentialId;)V", "getCredentialId", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "getId", "()Lcom/bitcoin/mwallet/core/models/user/UserId;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "user")
/* compiled from: User.kt */
public final class User {
    public static final Companion Companion = new Companion(null);
    @NotNull
    @Embedded(prefix = "credential_")
    private final CredentialId credentialId;
    @ColumnInfo(name = "id")
    @NotNull
    @PrimaryKey

    /* renamed from: id */
    private final UserId f373id;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/user/User$Companion;", "", "()V", "createNew", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/user/User;", "Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "mnemonic", "Lcom/bitcoin/bitcoink/Mnemonic;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: User.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Pair<User, CredentialMnemonic> createNew(@NotNull Mnemonic mnemonic) {
            Intrinsics.checkParameterIsNotNull(mnemonic, "mnemonic");
            UserId userId = new UserId(null);
            return new Pair<>(new User(userId, CredentialId.Companion.from(userId)), Credential.Companion.from(mnemonic, userId));
        }
    }

    @NotNull
    public static /* synthetic */ User copy$default(User user, UserId userId, CredentialId credentialId2, int i, Object obj) {
        if ((i & 1) != 0) {
            userId = user.f373id;
        }
        if ((i & 2) != 0) {
            credentialId2 = user.credentialId;
        }
        return user.copy(userId, credentialId2);
    }

    @NotNull
    public final UserId component1() {
        return this.f373id;
    }

    @NotNull
    public final CredentialId component2() {
        return this.credentialId;
    }

    @NotNull
    public final User copy(@NotNull UserId userId, @NotNull CredentialId credentialId2) {
        Intrinsics.checkParameterIsNotNull(userId, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(credentialId2, "credentialId");
        return new User(userId, credentialId2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.credentialId, (java.lang.Object) r3.credentialId) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.user.User
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.models.user.User r3 = (com.bitcoin.mwallet.core.models.user.User) r3
            com.bitcoin.mwallet.core.models.user.UserId r0 = r2.f373id
            com.bitcoin.mwallet.core.models.user.UserId r1 = r3.f373id
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.models.credential.CredentialId r0 = r2.credentialId
            com.bitcoin.mwallet.core.models.credential.CredentialId r3 = r3.credentialId
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.user.User.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        UserId userId = this.f373id;
        int i = 0;
        int hashCode = (userId != null ? userId.hashCode() : 0) * 31;
        CredentialId credentialId2 = this.credentialId;
        if (credentialId2 != null) {
            i = credentialId2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User(id=");
        sb.append(this.f373id);
        sb.append(", credentialId=");
        sb.append(this.credentialId);
        sb.append(")");
        return sb.toString();
    }

    public User(@NotNull UserId userId, @NotNull CredentialId credentialId2) {
        Intrinsics.checkParameterIsNotNull(userId, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(credentialId2, "credentialId");
        this.f373id = userId;
        this.credentialId = credentialId2;
    }

    @NotNull
    public final UserId getId() {
        return this.f373id;
    }

    @NotNull
    public final CredentialId getCredentialId() {
        return this.credentialId;
    }
}
