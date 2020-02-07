package com.bitcoin.mwallet.core.models.credential;

import androidx.room.Embedded;
import androidx.room.PrimaryKey;
import com.bitcoin.bitcoink.Mnemonic;
import com.bitcoin.mwallet.core.dao.EncryptedAtRestList;
import com.bitcoin.mwallet.core.models.user.UserId;
import com.bitcoin.mwallet.zion.ZionId;
import com.leanplum.internal.Constants.Params;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \b2\u00020\u0001:\u0001\bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/credential/Credential;", "", "id", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialId;)V", "getId", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "copyWithId", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Credential.kt */
public abstract class Credential {
    public static final Companion Companion = new Companion(null);
    @NotNull
    @Embedded(prefix = "id_")
    @PrimaryKey

    /* renamed from: id */
    private final CredentialId f366id;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nJ\u000e\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0003\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/credential/Credential$Companion;", "", "()V", "from", "Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "mnemonic", "Lcom/bitcoin/bitcoink/Mnemonic;", "userId", "Lcom/bitcoin/mwallet/core/models/user/UserId;", "isPasswordProtected", "", "Lcom/bitcoin/mwallet/core/models/credential/CredentialZion;", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "Lcom/bitcoin/mwallet/core/models/credential/CredentialEncrypted;", "encryptedMnemonic", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Credential.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CredentialZion from(@NotNull ZionId zionId) {
            Intrinsics.checkParameterIsNotNull(zionId, "zionId");
            return new CredentialZion(CredentialId.Companion.from(CredentialType.ZION_VAULT), zionId);
        }

        @NotNull
        public final CredentialEncrypted from(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "encryptedMnemonic");
            return new CredentialEncrypted(CredentialId.Companion.from(CredentialType.ENCRYPTED_MNEMONIC), str);
        }

        @NotNull
        public static /* synthetic */ CredentialMnemonic from$default(Companion companion, Mnemonic mnemonic, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.from(mnemonic, z);
        }

        @NotNull
        public final CredentialMnemonic from(@NotNull Mnemonic mnemonic, boolean z) {
            Intrinsics.checkParameterIsNotNull(mnemonic, "mnemonic");
            if (z) {
                return new CredentialMnemonic(CredentialId.Companion.from(CredentialType.MNEMONIC_AND_PROTECTED), new EncryptedAtRestList(mnemonic), mnemonic.getMasterKey());
            }
            return new CredentialMnemonic(CredentialId.Companion.from(CredentialType.MNEMONIC_AND_PRIVATE_KEY), new EncryptedAtRestList(mnemonic), mnemonic.getMasterKey());
        }

        @NotNull
        public final CredentialMnemonic from(@NotNull Mnemonic mnemonic, @NotNull UserId userId) {
            Intrinsics.checkParameterIsNotNull(mnemonic, "mnemonic");
            Intrinsics.checkParameterIsNotNull(userId, Params.USER_ID);
            return new CredentialMnemonic(CredentialId.Companion.from(userId), new EncryptedAtRestList(mnemonic), mnemonic.getMasterKey());
        }
    }

    public Credential(@NotNull CredentialId credentialId) {
        Intrinsics.checkParameterIsNotNull(credentialId, CommonProperties.f657ID);
        this.f366id = credentialId;
    }

    @NotNull
    public final CredentialId getId() {
        return this.f366id;
    }

    @NotNull
    public final Credential copyWithId(@NotNull CredentialId credentialId) {
        Intrinsics.checkParameterIsNotNull(credentialId, CommonProperties.f657ID);
        if (this instanceof CredentialMnemonic) {
            CredentialMnemonic credentialMnemonic = (CredentialMnemonic) this;
            return new CredentialMnemonic(credentialId, credentialMnemonic.getMnemonic(), credentialMnemonic.getMasterPrivKey());
        } else if (this instanceof CredentialZion) {
            return new CredentialZion(credentialId, ((CredentialZion) this).getZionId());
        } else {
            throw new IllegalStateException();
        }
    }
}
