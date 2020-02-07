package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.bitcoin.mwallet.core.models.Coin;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionViewArgs;", "Landroidx/navigation/NavArgs;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toString", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionViewArgs.kt */
public final class ReceiveAmountSelectionViewArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Coin coin;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionViewArgs$Companion;", "", "()V", "fromBundle", "Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionViewArgs;", "bundle", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ReceiveAmountSelectionViewArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ReceiveAmountSelectionViewArgs fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            bundle.setClassLoader(ReceiveAmountSelectionViewArgs.class.getClassLoader());
            String str = "coin";
            if (!bundle.containsKey(str)) {
                throw new IllegalArgumentException("Required argument \"coin\" is missing and does not have an android:defaultValue");
            } else if (Parcelable.class.isAssignableFrom(Coin.class) || Serializable.class.isAssignableFrom(Coin.class)) {
                Coin coin = (Coin) bundle.get(str);
                if (coin != null) {
                    return new ReceiveAmountSelectionViewArgs(coin);
                }
                throw new IllegalArgumentException("Argument \"coin\" is marked as non-null but was passed a null value.");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(Coin.class.getName());
                sb.append(" must implement Parcelable or Serializable or must be an Enum.");
                throw new UnsupportedOperationException(sb.toString());
            }
        }
    }

    @NotNull
    public static /* synthetic */ ReceiveAmountSelectionViewArgs copy$default(ReceiveAmountSelectionViewArgs receiveAmountSelectionViewArgs, Coin coin2, int i, Object obj) {
        if ((i & 1) != 0) {
            coin2 = receiveAmountSelectionViewArgs.coin;
        }
        return receiveAmountSelectionViewArgs.copy(coin2);
    }

    @JvmStatic
    @NotNull
    public static final ReceiveAmountSelectionViewArgs fromBundle(@NotNull Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @NotNull
    public final Coin component1() {
        return this.coin;
    }

    @NotNull
    public final ReceiveAmountSelectionViewArgs copy(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        return new ReceiveAmountSelectionViewArgs(coin2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.coin, (java.lang.Object) ((com.bitcoin.mwallet.app.flows.receive.receiveamountselection.ReceiveAmountSelectionViewArgs) r2).coin) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.app.flows.receive.receiveamountselection.ReceiveAmountSelectionViewArgs
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.app.flows.receive.receiveamountselection.ReceiveAmountSelectionViewArgs r2 = (com.bitcoin.mwallet.app.flows.receive.receiveamountselection.ReceiveAmountSelectionViewArgs) r2
            com.bitcoin.mwallet.core.models.Coin r0 = r1.coin
            com.bitcoin.mwallet.core.models.Coin r2 = r2.coin
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receiveamountselection.ReceiveAmountSelectionViewArgs.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Coin coin2 = this.coin;
        if (coin2 != null) {
            return coin2.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReceiveAmountSelectionViewArgs(coin=");
        sb.append(this.coin);
        sb.append(")");
        return sb.toString();
    }

    public ReceiveAmountSelectionViewArgs(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        this.coin = coin2;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = "coin";
        if (Parcelable.class.isAssignableFrom(Coin.class)) {
            Coin coin2 = this.coin;
            if (coin2 != null) {
                bundle.putParcelable(str, coin2);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.os.Parcelable");
            }
        } else if (Serializable.class.isAssignableFrom(Coin.class)) {
            Coin coin3 = this.coin;
            if (coin3 != null) {
                bundle.putSerializable(str, coin3);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.io.Serializable");
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(Coin.class.getName());
            sb.append(" must implement Parcelable or Serializable or must be an Enum.");
            throw new UnsupportedOperationException(sb.toString());
        }
        return bundle;
    }
}
