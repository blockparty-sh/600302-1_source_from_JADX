package com.bitcoin.mwallet.app.flows.sendv2.success;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/success/successViewArgs;", "Landroidx/navigation/NavArgs;", "txid", "Lcom/bitcoin/bitcoink/tx/TxId;", "sendWhatData", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;)V", "getSendWhatData", "()Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "getTxid", "()Lcom/bitcoin/bitcoink/tx/TxId;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toString", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: successViewArgs.kt */
public final class successViewArgs implements NavArgs {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SendWhatModel sendWhatData;
    @NotNull
    private final TxId txid;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/success/successViewArgs$Companion;", "", "()V", "fromBundle", "Lcom/bitcoin/mwallet/app/flows/sendv2/success/successViewArgs;", "bundle", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: successViewArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final successViewArgs fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            bundle.setClassLoader(successViewArgs.class.getClassLoader());
            String str = "txid";
            if (bundle.containsKey(str)) {
                String str2 = " must implement Parcelable or Serializable or must be an Enum.";
                if (Parcelable.class.isAssignableFrom(TxId.class) || Serializable.class.isAssignableFrom(TxId.class)) {
                    TxId txId = (TxId) bundle.get(str);
                    if (txId != null) {
                        String str3 = "sendWhatData";
                        if (!bundle.containsKey(str3)) {
                            throw new IllegalArgumentException("Required argument \"sendWhatData\" is missing and does not have an android:defaultValue");
                        } else if (Parcelable.class.isAssignableFrom(SendWhatModel.class) || Serializable.class.isAssignableFrom(SendWhatModel.class)) {
                            SendWhatModel sendWhatModel = (SendWhatModel) bundle.get(str3);
                            if (sendWhatModel != null) {
                                return new successViewArgs(txId, sendWhatModel);
                            }
                            throw new IllegalArgumentException("Argument \"sendWhatData\" is marked as non-null but was passed a null value.");
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(SendWhatModel.class.getName());
                            sb.append(str2);
                            throw new UnsupportedOperationException(sb.toString());
                        }
                    } else {
                        throw new IllegalArgumentException("Argument \"txid\" is marked as non-null but was passed a null value.");
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(TxId.class.getName());
                    sb2.append(str2);
                    throw new UnsupportedOperationException(sb2.toString());
                }
            } else {
                throw new IllegalArgumentException("Required argument \"txid\" is missing and does not have an android:defaultValue");
            }
        }
    }

    @NotNull
    public static /* synthetic */ successViewArgs copy$default(successViewArgs successviewargs, TxId txId, SendWhatModel sendWhatModel, int i, Object obj) {
        if ((i & 1) != 0) {
            txId = successviewargs.txid;
        }
        if ((i & 2) != 0) {
            sendWhatModel = successviewargs.sendWhatData;
        }
        return successviewargs.copy(txId, sendWhatModel);
    }

    @JvmStatic
    @NotNull
    public static final successViewArgs fromBundle(@NotNull Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    @NotNull
    public final TxId component1() {
        return this.txid;
    }

    @NotNull
    public final SendWhatModel component2() {
        return this.sendWhatData;
    }

    @NotNull
    public final successViewArgs copy(@NotNull TxId txId, @NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(txId, "txid");
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "sendWhatData");
        return new successViewArgs(txId, sendWhatModel);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.sendWhatData, (java.lang.Object) r3.sendWhatData) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.sendv2.success.successViewArgs
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.app.flows.sendv2.success.successViewArgs r3 = (com.bitcoin.mwallet.app.flows.sendv2.success.successViewArgs) r3
            com.bitcoin.bitcoink.tx.TxId r0 = r2.txid
            com.bitcoin.bitcoink.tx.TxId r1 = r3.txid
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel r0 = r2.sendWhatData
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel r3 = r3.sendWhatData
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.success.successViewArgs.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        TxId txId = this.txid;
        int i = 0;
        int hashCode = (txId != null ? txId.hashCode() : 0) * 31;
        SendWhatModel sendWhatModel = this.sendWhatData;
        if (sendWhatModel != null) {
            i = sendWhatModel.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("successViewArgs(txid=");
        sb.append(this.txid);
        sb.append(", sendWhatData=");
        sb.append(this.sendWhatData);
        sb.append(")");
        return sb.toString();
    }

    public successViewArgs(@NotNull TxId txId, @NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(txId, "txid");
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "sendWhatData");
        this.txid = txId;
        this.sendWhatData = sendWhatModel;
    }

    @NotNull
    public final SendWhatModel getSendWhatData() {
        return this.sendWhatData;
    }

    @NotNull
    public final TxId getTxid() {
        return this.txid;
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = "null cannot be cast to non-null type java.io.Serializable";
        String str2 = "null cannot be cast to non-null type android.os.Parcelable";
        String str3 = " must implement Parcelable or Serializable or must be an Enum.";
        String str4 = "txid";
        if (Parcelable.class.isAssignableFrom(TxId.class)) {
            TxId txId = this.txid;
            if (txId != null) {
                bundle.putParcelable(str4, (Parcelable) txId);
            } else {
                throw new TypeCastException(str2);
            }
        } else if (Serializable.class.isAssignableFrom(TxId.class)) {
            TxId txId2 = this.txid;
            if (txId2 != null) {
                bundle.putSerializable(str4, txId2);
            } else {
                throw new TypeCastException(str);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(TxId.class.getName());
            sb.append(str3);
            throw new UnsupportedOperationException(sb.toString());
        }
        String str5 = "sendWhatData";
        if (Parcelable.class.isAssignableFrom(SendWhatModel.class)) {
            SendWhatModel sendWhatModel = this.sendWhatData;
            if (sendWhatModel != null) {
                bundle.putParcelable(str5, sendWhatModel);
            } else {
                throw new TypeCastException(str2);
            }
        } else if (Serializable.class.isAssignableFrom(SendWhatModel.class)) {
            SendWhatModel sendWhatModel2 = this.sendWhatData;
            if (sendWhatModel2 != null) {
                bundle.putSerializable(str5, (Serializable) sendWhatModel2);
            } else {
                throw new TypeCastException(str);
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(SendWhatModel.class.getName());
            sb2.append(str3);
            throw new UnsupportedOperationException(sb2.toString());
        }
        return bundle;
    }
}
