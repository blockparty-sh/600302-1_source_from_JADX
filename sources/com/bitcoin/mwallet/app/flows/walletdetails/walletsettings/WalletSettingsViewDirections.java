package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u0000 \u00062\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewDirections;", "", "()V", "ActionWalletSettingsToWalletAdvancedDetails", "ActionWalletSettingsToWalletDeleteWallet", "ActionWalletSettingsToWalletRecoveryPhrase", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsViewDirections.kt */
public final class WalletSettingsViewDirections {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewDirections$ActionWalletSettingsToWalletAdvancedDetails;", "Landroidx/navigation/NavDirections;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSettingsViewDirections.kt */
    private static final class ActionWalletSettingsToWalletAdvancedDetails implements NavDirections {
        @NotNull
        private final WalletId walletId;

        @NotNull
        public static /* synthetic */ ActionWalletSettingsToWalletAdvancedDetails copy$default(ActionWalletSettingsToWalletAdvancedDetails actionWalletSettingsToWalletAdvancedDetails, WalletId walletId2, int i, Object obj) {
            if ((i & 1) != 0) {
                walletId2 = actionWalletSettingsToWalletAdvancedDetails.walletId;
            }
            return actionWalletSettingsToWalletAdvancedDetails.copy(walletId2);
        }

        @NotNull
        public final WalletId component1() {
            return this.walletId;
        }

        @NotNull
        public final ActionWalletSettingsToWalletAdvancedDetails copy(@NotNull WalletId walletId2) {
            Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
            return new ActionWalletSettingsToWalletAdvancedDetails(walletId2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.walletId, (java.lang.Object) ((com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletAdvancedDetails) r2).walletId) != false) goto L_0x0015;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
            /*
                r1 = this;
                if (r1 == r2) goto L_0x0015
                boolean r0 = r2 instanceof com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletAdvancedDetails
                if (r0 == 0) goto L_0x0013
                com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections$ActionWalletSettingsToWalletAdvancedDetails r2 = (com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletAdvancedDetails) r2
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletAdvancedDetails.equals(java.lang.Object):boolean");
        }

        public int getActionId() {
            return C1018R.C1021id.action_walletSettings_to_walletAdvancedDetails;
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
            sb.append("ActionWalletSettingsToWalletAdvancedDetails(walletId=");
            sb.append(this.walletId);
            sb.append(")");
            return sb.toString();
        }

        public ActionWalletSettingsToWalletAdvancedDetails(@NotNull WalletId walletId2) {
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

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewDirections$ActionWalletSettingsToWalletDeleteWallet;", "Landroidx/navigation/NavDirections;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSettingsViewDirections.kt */
    private static final class ActionWalletSettingsToWalletDeleteWallet implements NavDirections {
        @NotNull
        private final WalletId walletId;

        @NotNull
        public static /* synthetic */ ActionWalletSettingsToWalletDeleteWallet copy$default(ActionWalletSettingsToWalletDeleteWallet actionWalletSettingsToWalletDeleteWallet, WalletId walletId2, int i, Object obj) {
            if ((i & 1) != 0) {
                walletId2 = actionWalletSettingsToWalletDeleteWallet.walletId;
            }
            return actionWalletSettingsToWalletDeleteWallet.copy(walletId2);
        }

        @NotNull
        public final WalletId component1() {
            return this.walletId;
        }

        @NotNull
        public final ActionWalletSettingsToWalletDeleteWallet copy(@NotNull WalletId walletId2) {
            Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
            return new ActionWalletSettingsToWalletDeleteWallet(walletId2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.walletId, (java.lang.Object) ((com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletDeleteWallet) r2).walletId) != false) goto L_0x0015;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
            /*
                r1 = this;
                if (r1 == r2) goto L_0x0015
                boolean r0 = r2 instanceof com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletDeleteWallet
                if (r0 == 0) goto L_0x0013
                com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections$ActionWalletSettingsToWalletDeleteWallet r2 = (com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletDeleteWallet) r2
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletDeleteWallet.equals(java.lang.Object):boolean");
        }

        public int getActionId() {
            return C1018R.C1021id.action_walletSettings_to_walletDeleteWallet;
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
            sb.append("ActionWalletSettingsToWalletDeleteWallet(walletId=");
            sb.append(this.walletId);
            sb.append(")");
            return sb.toString();
        }

        public ActionWalletSettingsToWalletDeleteWallet(@NotNull WalletId walletId2) {
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

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewDirections$ActionWalletSettingsToWalletRecoveryPhrase;", "Landroidx/navigation/NavDirections;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "walletTicker", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletTicker", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSettingsViewDirections.kt */
    private static final class ActionWalletSettingsToWalletRecoveryPhrase implements NavDirections {
        @NotNull
        private final WalletId walletId;
        @NotNull
        private final String walletTicker;

        @NotNull
        public static /* synthetic */ ActionWalletSettingsToWalletRecoveryPhrase copy$default(ActionWalletSettingsToWalletRecoveryPhrase actionWalletSettingsToWalletRecoveryPhrase, WalletId walletId2, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                walletId2 = actionWalletSettingsToWalletRecoveryPhrase.walletId;
            }
            if ((i & 2) != 0) {
                str = actionWalletSettingsToWalletRecoveryPhrase.walletTicker;
            }
            return actionWalletSettingsToWalletRecoveryPhrase.copy(walletId2, str);
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
        public final ActionWalletSettingsToWalletRecoveryPhrase copy(@NotNull WalletId walletId2, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
            Intrinsics.checkParameterIsNotNull(str, "walletTicker");
            return new ActionWalletSettingsToWalletRecoveryPhrase(walletId2, str);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.walletTicker, (java.lang.Object) r3.walletTicker) != false) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x001f
                boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletRecoveryPhrase
                if (r0 == 0) goto L_0x001d
                com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections$ActionWalletSettingsToWalletRecoveryPhrase r3 = (com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletRecoveryPhrase) r3
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsViewDirections.ActionWalletSettingsToWalletRecoveryPhrase.equals(java.lang.Object):boolean");
        }

        public int getActionId() {
            return C1018R.C1021id.action_walletSettings_to_walletRecoveryPhrase;
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
            sb.append("ActionWalletSettingsToWalletRecoveryPhrase(walletId=");
            sb.append(this.walletId);
            sb.append(", walletTicker=");
            sb.append(this.walletTicker);
            sb.append(")");
            return sb.toString();
        }

        public ActionWalletSettingsToWalletRecoveryPhrase(@NotNull WalletId walletId2, @NotNull String str) {
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
            bundle.putString("walletTicker", this.walletTicker);
            return bundle;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewDirections$Companion;", "", "()V", "actionWalletSettingsToWalletAdvancedDetails", "Landroidx/navigation/NavDirections;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "actionWalletSettingsToWalletDeleteWallet", "actionWalletSettingsToWalletRecoveryPhrase", "walletTicker", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSettingsViewDirections.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final NavDirections actionWalletSettingsToWalletAdvancedDetails(@NotNull WalletId walletId) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            return new ActionWalletSettingsToWalletAdvancedDetails(walletId);
        }

        @NotNull
        public final NavDirections actionWalletSettingsToWalletDeleteWallet(@NotNull WalletId walletId) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            return new ActionWalletSettingsToWalletDeleteWallet(walletId);
        }

        @NotNull
        public final NavDirections actionWalletSettingsToWalletRecoveryPhrase(@NotNull WalletId walletId, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            Intrinsics.checkParameterIsNotNull(str, "walletTicker");
            return new ActionWalletSettingsToWalletRecoveryPhrase(walletId, str);
        }
    }

    private WalletSettingsViewDirections() {
    }
}
