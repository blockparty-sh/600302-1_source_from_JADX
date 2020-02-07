package com.bitcoin.mwallet.app.flows.receive.receive;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.Coin;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveViewDirections;", "", "()V", "ActionReceiveViewToReceiveAmountSelection", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveViewDirections.kt */
public final class ReceiveViewDirections {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveViewDirections$ActionReceiveViewToReceiveAmountSelection;", "Landroidx/navigation/NavDirections;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "component1", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ReceiveViewDirections.kt */
    private static final class ActionReceiveViewToReceiveAmountSelection implements NavDirections {
        @NotNull
        private final Coin coin;

        @NotNull
        public static /* synthetic */ ActionReceiveViewToReceiveAmountSelection copy$default(ActionReceiveViewToReceiveAmountSelection actionReceiveViewToReceiveAmountSelection, Coin coin2, int i, Object obj) {
            if ((i & 1) != 0) {
                coin2 = actionReceiveViewToReceiveAmountSelection.coin;
            }
            return actionReceiveViewToReceiveAmountSelection.copy(coin2);
        }

        @NotNull
        public final Coin component1() {
            return this.coin;
        }

        @NotNull
        public final ActionReceiveViewToReceiveAmountSelection copy(@NotNull Coin coin2) {
            Intrinsics.checkParameterIsNotNull(coin2, "coin");
            return new ActionReceiveViewToReceiveAmountSelection(coin2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.coin, (java.lang.Object) ((com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewDirections.ActionReceiveViewToReceiveAmountSelection) r2).coin) != false) goto L_0x0015;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
            /*
                r1 = this;
                if (r1 == r2) goto L_0x0015
                boolean r0 = r2 instanceof com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewDirections.ActionReceiveViewToReceiveAmountSelection
                if (r0 == 0) goto L_0x0013
                com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewDirections$ActionReceiveViewToReceiveAmountSelection r2 = (com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewDirections.ActionReceiveViewToReceiveAmountSelection) r2
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.receive.receive.ReceiveViewDirections.ActionReceiveViewToReceiveAmountSelection.equals(java.lang.Object):boolean");
        }

        public int getActionId() {
            return C1018R.C1021id.action_receiveView_to_receiveAmountSelection;
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
            sb.append("ActionReceiveViewToReceiveAmountSelection(coin=");
            sb.append(this.coin);
            sb.append(")");
            return sb.toString();
        }

        public ActionReceiveViewToReceiveAmountSelection(@NotNull Coin coin2) {
            Intrinsics.checkParameterIsNotNull(coin2, "coin");
            this.coin = coin2;
        }

        @NotNull
        public final Coin getCoin() {
            return this.coin;
        }

        @NotNull
        public Bundle getArguments() {
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

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveViewDirections$Companion;", "", "()V", "actionReceiveViewToReceiveAmountSelection", "Landroidx/navigation/NavDirections;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ReceiveViewDirections.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final NavDirections actionReceiveViewToReceiveAmountSelection(@NotNull Coin coin) {
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            return new ActionReceiveViewToReceiveAmountSelection(coin);
        }
    }

    private ReceiveViewDirections() {
    }
}
