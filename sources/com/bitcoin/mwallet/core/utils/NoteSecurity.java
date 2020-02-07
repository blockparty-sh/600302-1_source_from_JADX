package com.bitcoin.mwallet.core.utils;

import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import walletutils.Walletutils;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/NoteSecurity;", "", "()V", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NoteSecurity.kt */
public final class NoteSecurity {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/NoteSecurity$Companion;", "", "()V", "decryptNewFormat", "", "note", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "decryptOldFormat", "oldNoteJson", "oldEncryptionKey", "encryptNewFormat", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: NoteSecurity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String decryptOldFormat(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "oldNoteJson");
            Intrinsics.checkParameterIsNotNull(str2, "oldEncryptionKey");
            String decryptSJCLTxNote = Walletutils.decryptSJCLTxNote(str, str2);
            Intrinsics.checkExpressionValueIsNotNull(decryptSJCLTxNote, "Walletutils.decryptSJCLT…teJson, oldEncryptionKey)");
            return decryptSJCLTxNote;
        }

        @NotNull
        public final String encryptNewFormat(@NotNull String str, @NotNull SigningKey signingKey) {
            Intrinsics.checkParameterIsNotNull(str, "note");
            Intrinsics.checkParameterIsNotNull(signingKey, "signingKey");
            String encrypt = Walletutils.encrypt(signingKey.getPrivateKey(), str);
            Intrinsics.checkExpressionValueIsNotNull(encrypt, "Walletutils.encrypt(signingKey.privateKey, note)");
            return encrypt;
        }

        @NotNull
        public final String decryptNewFormat(@NotNull String str, @NotNull SigningKey signingKey) {
            Intrinsics.checkParameterIsNotNull(str, "note");
            Intrinsics.checkParameterIsNotNull(signingKey, "signingKey");
            String decrypt = Walletutils.decrypt(signingKey.getPrivateKey(), str);
            Intrinsics.checkExpressionValueIsNotNull(decrypt, "Walletutils.decrypt(signingKey.privateKey, note)");
            return decrypt;
        }
    }
}
