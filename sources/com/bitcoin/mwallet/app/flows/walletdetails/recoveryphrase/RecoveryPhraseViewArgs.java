package com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseViewArgs;", "Landroidx/navigation/NavArgs;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "walletTicker", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletTicker", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: RecoveryPhraseViewArgs.kt */
public final class RecoveryPhraseViewArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final WalletId walletId;
    @NotNull
    private final String walletTicker;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseViewArgs$Companion;", "", "()V", "fromBundle", "Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseViewArgs;", "bundle", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: RecoveryPhraseViewArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final RecoveryPhraseViewArgs fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            bundle.setClassLoader(RecoveryPhraseViewArgs.class.getClassLoader());
            String str = "walletId";
            if (!bundle.containsKey(str)) {
                throw new IllegalArgumentException("Required argument \"walletId\" is missing and does not have an android:defaultValue");
            } else if (Parcelable.class.isAssignableFrom(WalletId.class) || Serializable.class.isAssignableFrom(WalletId.class)) {
                WalletId walletId = (WalletId) bundle.get(str);
                if (walletId != null) {
                    String str2 = "walletTicker";
                    if (bundle.containsKey(str2)) {
                        String string = bundle.getString(str2);
                        if (string != null) {
                            return new RecoveryPhraseViewArgs(walletId, string);
                        }
                        throw new IllegalArgumentException("Argument \"walletTicker\" is marked as non-null but was passed a null value.");
                    }
                    throw new IllegalArgumentException("Required argument \"walletTicker\" is missing and does not have an android:defaultValue");
                }
                throw new IllegalArgumentException("Argument \"walletId\" is marked as non-null but was passed a null value.");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(WalletId.class.getName());
                sb.append(" must implement Parcelable or Serializable or must be an Enum.");
                throw new UnsupportedOperationException(sb.toString());
            }
        }
    }

    @NotNull
    public static /* synthetic */ RecoveryPhraseViewArgs copy$default(RecoveryPhraseViewArgs recoveryPhraseViewArgs, WalletId walletId2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            walletId2 = recoveryPhraseViewArgs.walletId;
        }
        if ((i & 2) != 0) {
            str = recoveryPhraseViewArgs.walletTicker;
        }
        return recoveryPhraseViewArgs.copy(walletId2, str);
    }

    @JvmStatic
    @NotNull
    public static final RecoveryPhraseViewArgs fromBundle(@NotNull Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @NotNull
    public final WalletId component1() {
        return this.walletId;
    }

    @NotNull
    public final String component2() {
        return this.walletTicker;
    }

    @NotNull
    public final RecoveryPhraseViewArgs copy(@NotNull WalletId walletId2, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "walletTicker");
        return new RecoveryPhraseViewArgs(walletId2, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.walletTicker, (java.lang.Object) r3.walletTicker) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhraseViewArgs
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhraseViewArgs r3 = (com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhraseViewArgs) r3
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.walletId
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r3.walletId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = r2.walletTicker
            java.lang.String r3 = r3.walletTicker
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhraseViewArgs.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        WalletId walletId2 = this.walletId;
        int i = 0;
        int hashCode = (walletId2 != null ? walletId2.hashCode() : 0) * 31;
        String str = this.walletTicker;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RecoveryPhraseViewArgs(walletId=");
        sb.append(this.walletId);
        sb.append(", walletTicker=");
        sb.append(this.walletTicker);
        sb.append(")");
        return sb.toString();
    }

    public RecoveryPhraseViewArgs(@NotNull WalletId walletId2, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "walletTicker");
        this.walletId = walletId2;
        this.walletTicker = str;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final String getWalletTicker() {
        return this.walletTicker;
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = "walletId";
        if (Parcelable.class.isAssignableFrom(WalletId.class)) {
            WalletId walletId2 = this.walletId;
            if (walletId2 != null) {
                bundle.putParcelable(str, (Parcelable) walletId2);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.os.Parcelable");
            }
        } else if (Serializable.class.isAssignableFrom(WalletId.class)) {
            WalletId walletId3 = this.walletId;
            if (walletId3 != null) {
                bundle.putSerializable(str, walletId3);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.io.Serializable");
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(WalletId.class.getName());
            sb.append(" must implement Parcelable or Serializable or must be an Enum.");
            throw new UnsupportedOperationException(sb.toString());
        }
        bundle.putString("walletTicker", this.walletTicker);
        return bundle;
    }
}
