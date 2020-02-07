package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewDirections;", "", "()V", "ActionWalletAdvancedDetailsToWalltScanForTransactions", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsViewDirections.kt */
public final class AdvancedDetailsViewDirections {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewDirections$ActionWalletAdvancedDetailsToWalltScanForTransactions;", "Landroidx/navigation/NavDirections;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AdvancedDetailsViewDirections.kt */
    private static final class ActionWalletAdvancedDetailsToWalltScanForTransactions implements NavDirections {
        @NotNull
        private final WalletId walletId;

        @NotNull
        public static /* synthetic */ ActionWalletAdvancedDetailsToWalltScanForTransactions copy$default(ActionWalletAdvancedDetailsToWalltScanForTransactions actionWalletAdvancedDetailsToWalltScanForTransactions, WalletId walletId2, int i, Object obj) {
            if ((i & 1) != 0) {
                walletId2 = actionWalletAdvancedDetailsToWalltScanForTransactions.walletId;
            }
            return actionWalletAdvancedDetailsToWalltScanForTransactions.copy(walletId2);
        }

        @NotNull
        public final WalletId component1() {
            return this.walletId;
        }

        @NotNull
        public final ActionWalletAdvancedDetailsToWalltScanForTransactions copy(@NotNull WalletId walletId2) {
            Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
            return new ActionWalletAdvancedDetailsToWalltScanForTransactions(walletId2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.walletId, (java.lang.Object) ((com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails.AdvancedDetailsViewDirections.ActionWalletAdvancedDetailsToWalltScanForTransactions) r2).walletId) != false) goto L_0x0015;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
            /*
                r1 = this;
                if (r1 == r2) goto L_0x0015
                boolean r0 = r2 instanceof com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails.AdvancedDetailsViewDirections.ActionWalletAdvancedDetailsToWalltScanForTransactions
                if (r0 == 0) goto L_0x0013
                com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails.AdvancedDetailsViewDirections$ActionWalletAdvancedDetailsToWalltScanForTransactions r2 = (com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails.AdvancedDetailsViewDirections.ActionWalletAdvancedDetailsToWalltScanForTransactions) r2
                com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r1.walletId
                com.bitcoin.mwallet.core.models.wallet.WalletId r2 = r2.walletId
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails.AdvancedDetailsViewDirections.ActionWalletAdvancedDetailsToWalltScanForTransactions.equals(java.lang.Object):boolean");
        }

        public int getActionId() {
            return C1018R.C1021id.action_walletAdvancedDetails_to_walltScanForTransactions;
        }

        public int hashCode() {
            WalletId walletId2 = this.walletId;
            if (walletId2 != null) {
                return walletId2.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ActionWalletAdvancedDetailsToWalltScanForTransactions(walletId=");
            sb.append(this.walletId);
            sb.append(")");
            return sb.toString();
        }

        public ActionWalletAdvancedDetailsToWalltScanForTransactions(@NotNull WalletId walletId2) {
            Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
            this.walletId = walletId2;
        }

        @NotNull
        public final WalletId getWalletId() {
            return this.walletId;
        }

        @NotNull
        public Bundle getArguments() {
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
            return bundle;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewDirections$Companion;", "", "()V", "actionWalletAdvancedDetailsToWalltScanForTransactions", "Landroidx/navigation/NavDirections;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AdvancedDetailsViewDirections.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final NavDirections actionWalletAdvancedDetailsToWalltScanForTransactions(@NotNull WalletId walletId) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            return new ActionWalletAdvancedDetailsToWalltScanForTransactions(walletId);
        }
    }

    private AdvancedDetailsViewDirections() {
    }
}
