package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionViewArgs;", "Landroidx/navigation/NavArgs;", "sendToUri", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "preferredWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getPreferredWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getSendToUri", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toString", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionViewArgs.kt */
public final class SendAmountSelectionViewArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final WalletId preferredWalletId;
    @NotNull
    private final BitcoinUriContent sendToUri;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionViewArgs$Companion;", "", "()V", "fromBundle", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionViewArgs;", "bundle", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SendAmountSelectionViewArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SendAmountSelectionViewArgs fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            bundle.setClassLoader(SendAmountSelectionViewArgs.class.getClassLoader());
            String str = "sendToUri";
            if (bundle.containsKey(str)) {
                String str2 = " must implement Parcelable or Serializable or must be an Enum.";
                if (Parcelable.class.isAssignableFrom(BitcoinUriContent.class) || Serializable.class.isAssignableFrom(BitcoinUriContent.class)) {
                    BitcoinUriContent bitcoinUriContent = (BitcoinUriContent) bundle.get(str);
                    if (bitcoinUriContent != null) {
                        String str3 = "preferredWalletId";
                        if (!bundle.containsKey(str3)) {
                            throw new IllegalArgumentException("Required argument \"preferredWalletId\" is missing and does not have an android:defaultValue");
                        } else if (Parcelable.class.isAssignableFrom(WalletId.class) || Serializable.class.isAssignableFrom(WalletId.class)) {
                            return new SendAmountSelectionViewArgs(bitcoinUriContent, (WalletId) bundle.get(str3));
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(WalletId.class.getName());
                            sb.append(str2);
                            throw new UnsupportedOperationException(sb.toString());
                        }
                    } else {
                        throw new IllegalArgumentException("Argument \"sendToUri\" is marked as non-null but was passed a null value.");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(BitcoinUriContent.class.getName());
                    sb2.append(str2);
                    throw new UnsupportedOperationException(sb2.toString());
                }
            } else {
                throw new IllegalArgumentException("Required argument \"sendToUri\" is missing and does not have an android:defaultValue");
            }
        }
    }

    @NotNull
    public static /* synthetic */ SendAmountSelectionViewArgs copy$default(SendAmountSelectionViewArgs sendAmountSelectionViewArgs, BitcoinUriContent bitcoinUriContent, WalletId walletId, int i, Object obj) {
        if ((i & 1) != 0) {
            bitcoinUriContent = sendAmountSelectionViewArgs.sendToUri;
        }
        if ((i & 2) != 0) {
            walletId = sendAmountSelectionViewArgs.preferredWalletId;
        }
        return sendAmountSelectionViewArgs.copy(bitcoinUriContent, walletId);
    }

    @JvmStatic
    @NotNull
    public static final SendAmountSelectionViewArgs fromBundle(@NotNull Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @NotNull
    public final BitcoinUriContent component1() {
        return this.sendToUri;
    }

    @Nullable
    public final WalletId component2() {
        return this.preferredWalletId;
    }

    @NotNull
    public final SendAmountSelectionViewArgs copy(@NotNull BitcoinUriContent bitcoinUriContent, @Nullable WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "sendToUri");
        return new SendAmountSelectionViewArgs(bitcoinUriContent, walletId);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.preferredWalletId, (java.lang.Object) r3.preferredWalletId) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionViewArgs
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionViewArgs r3 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionViewArgs) r3
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r2.sendToUri
            com.bitcoin.mwallet.core.models.BitcoinUriContent r1 = r3.sendToUri
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.preferredWalletId
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = r3.preferredWalletId
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionViewArgs.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        BitcoinUriContent bitcoinUriContent = this.sendToUri;
        int i = 0;
        int hashCode = (bitcoinUriContent != null ? bitcoinUriContent.hashCode() : 0) * 31;
        WalletId walletId = this.preferredWalletId;
        if (walletId != null) {
            i = walletId.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendAmountSelectionViewArgs(sendToUri=");
        sb.append(this.sendToUri);
        sb.append(", preferredWalletId=");
        sb.append(this.preferredWalletId);
        sb.append(")");
        return sb.toString();
    }

    public SendAmountSelectionViewArgs(@NotNull BitcoinUriContent bitcoinUriContent, @Nullable WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "sendToUri");
        this.sendToUri = bitcoinUriContent;
        this.preferredWalletId = walletId;
    }

    @Nullable
    public final WalletId getPreferredWalletId() {
        return this.preferredWalletId;
    }

    @NotNull
    public final BitcoinUriContent getSendToUri() {
        return this.sendToUri;
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = " must implement Parcelable or Serializable or must be an Enum.";
        String str2 = "sendToUri";
        if (Parcelable.class.isAssignableFrom(BitcoinUriContent.class)) {
            BitcoinUriContent bitcoinUriContent = this.sendToUri;
            if (bitcoinUriContent != null) {
                bundle.putParcelable(str2, bitcoinUriContent);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.os.Parcelable");
            }
        } else if (Serializable.class.isAssignableFrom(BitcoinUriContent.class)) {
            BitcoinUriContent bitcoinUriContent2 = this.sendToUri;
            if (bitcoinUriContent2 != null) {
                bundle.putSerializable(str2, (Serializable) bitcoinUriContent2);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.io.Serializable");
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(BitcoinUriContent.class.getName());
            sb.append(str);
            throw new UnsupportedOperationException(sb.toString());
        }
        String str3 = "preferredWalletId";
        if (Parcelable.class.isAssignableFrom(WalletId.class)) {
            bundle.putParcelable(str3, (Parcelable) this.preferredWalletId);
        } else if (Serializable.class.isAssignableFrom(WalletId.class)) {
            bundle.putSerializable(str3, this.preferredWalletId);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(WalletId.class.getName());
            sb2.append(str);
            throw new UnsupportedOperationException(sb2.toString());
        }
        return bundle;
    }
}
