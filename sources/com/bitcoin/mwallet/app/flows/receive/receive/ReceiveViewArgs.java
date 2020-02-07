package com.bitcoin.mwallet.app.flows.receive.receive;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveViewArgs;", "Landroidx/navigation/NavArgs;", "walletPk", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getWalletPk", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toString", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveViewArgs.kt */
public final class ReceiveViewArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final WalletId walletPk;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveViewArgs$Companion;", "", "()V", "fromBundle", "Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveViewArgs;", "bundle", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ReceiveViewArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ReceiveViewArgs fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            bundle.setClassLoader(ReceiveViewArgs.class.getClassLoader());
            String str = "walletPk";
            if (!bundle.containsKey(str)) {
                throw new IllegalArgumentException("Required argument \"walletPk\" is missing and does not have an android:defaultValue");
            } else if (Parcelable.class.isAssignableFrom(WalletId.class) || Serializable.class.isAssignableFrom(WalletId.class)) {
                return new ReceiveViewArgs((WalletId) bundle.get(str));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(WalletId.class.getName());
                sb.append(" must implement Parcelable or Serializable or must be an Enum.");
                throw new UnsupportedOperationException(sb.toString());
            }
        }
    }

    @NotNull
    public static /* synthetic */ ReceiveViewArgs copy$default(ReceiveViewArgs receiveViewArgs, WalletId walletId, int i, Object obj) {
        if ((i & 1) != 0) {
            walletId = receiveViewArgs.walletPk;
        }
        return receiveViewArgs.copy(walletId);
    }

    @JvmStatic
    @NotNull
    public static final ReceiveViewArgs fromBundle(@NotNull Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @Nullable
    public final WalletId component1() {
        return this.walletPk;
    }

    @NotNull
    public final ReceiveViewArgs copy(@Nullable WalletId walletId) {
        return new ReceiveViewArgs(walletId);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.walletPk, (java.lang.Object) ((com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewArgs) r2).walletPk) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewArgs
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewArgs r2 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewArgs) r2
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r1.walletPk
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = r2.walletPk
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewArgs.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        WalletId walletId = this.walletPk;
        if (walletId != null) {
            return walletId.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReceiveViewArgs(walletPk=");
        sb.append(this.walletPk);
        sb.append(")");
        return sb.toString();
    }

    public ReceiveViewArgs(@Nullable WalletId walletId) {
        this.walletPk = walletId;
    }

    @Nullable
    public final WalletId getWalletPk() {
        return this.walletPk;
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = "walletPk";
        if (Parcelable.class.isAssignableFrom(WalletId.class)) {
            bundle.putParcelable(str, (Parcelable) this.walletPk);
        } else if (Serializable.class.isAssignableFrom(WalletId.class)) {
            bundle.putSerializable(str, this.walletPk);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(WalletId.class.getName());
            sb.append(" must implement Parcelable or Serializable or must be an Enum.");
            throw new UnsupportedOperationException(sb.toString());
        }
        return bundle;
    }
}
