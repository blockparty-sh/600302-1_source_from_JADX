package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.bitcoin.mwallet.core.extensions.ParcelKt;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.Serializable;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\b\u0018\u0000 42\u00020\u0001:\u00014B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BM\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f¢\u0006\u0002\u0010\u0012J\u000b\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J\t\u0010%\u001a\u00020\u000eHÆ\u0003J\t\u0010&\u001a\u00020\u0010HÆ\u0003J\t\u0010'\u001a\u00020\fHÆ\u0003J_\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\fHÆ\u0001J\b\u0010)\u001a\u00020*H\u0016J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u00020*HÖ\u0001J\t\u00100\u001a\u00020\fHÖ\u0001J\u0018\u00101\u001a\u0002022\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00103\u001a\u00020*H\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0011\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00065"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "fiatAmount", "Ljava/math/BigDecimal;", "cryptoAmount", "tokenAmount", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "currency", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "uri", "Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "memo", "(Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;Ljava/lang/String;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getCryptoAmount", "()Ljava/math/BigDecimal;", "getCurrency", "()Ljava/lang/String;", "getFiatAmount", "getMemo", "getTokenAmount", "getUri", "()Lcom/bitcoin/mwallet/core/models/BitcoinUriContent;", "getWallet", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendWhatModel.kt */
public final class SendWhatModel implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    @NotNull
    private final Coin coin;
    @Nullable
    private final BigDecimal cryptoAmount;
    @NotNull
    private final String currency;
    @Nullable
    private final BigDecimal fiatAmount;
    @NotNull
    private final String memo;
    @Nullable
    private final BigDecimal tokenAmount;
    @NotNull
    private final BitcoinUriContent uri;
    @NotNull
    private final WalletId wallet;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SendWhatModel.kt */
    public static final class CREATOR implements Creator<SendWhatModel> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public SendWhatModel createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new SendWhatModel(parcel);
        }

        @NotNull
        public SendWhatModel[] newArray(int i) {
            return new SendWhatModel[i];
        }
    }

    @NotNull
    public static /* synthetic */ SendWhatModel copy$default(SendWhatModel sendWhatModel, BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3, Coin coin2, String str, WalletId walletId, BitcoinUriContent bitcoinUriContent, String str2, int i, Object obj) {
        SendWhatModel sendWhatModel2 = sendWhatModel;
        int i2 = i;
        return sendWhatModel.copy((i2 & 1) != 0 ? sendWhatModel2.fiatAmount : bigDecimal, (i2 & 2) != 0 ? sendWhatModel2.cryptoAmount : bigDecimal2, (i2 & 4) != 0 ? sendWhatModel2.tokenAmount : bigDecimal3, (i2 & 8) != 0 ? sendWhatModel2.coin : coin2, (i2 & 16) != 0 ? sendWhatModel2.currency : str, (i2 & 32) != 0 ? sendWhatModel2.wallet : walletId, (i2 & 64) != 0 ? sendWhatModel2.uri : bitcoinUriContent, (i2 & 128) != 0 ? sendWhatModel2.memo : str2);
    }

    @Nullable
    public final BigDecimal component1() {
        return this.fiatAmount;
    }

    @Nullable
    public final BigDecimal component2() {
        return this.cryptoAmount;
    }

    @Nullable
    public final BigDecimal component3() {
        return this.tokenAmount;
    }

    @NotNull
    public final Coin component4() {
        return this.coin;
    }

    @NotNull
    public final String component5() {
        return this.currency;
    }

    @NotNull
    public final WalletId component6() {
        return this.wallet;
    }

    @NotNull
    public final BitcoinUriContent component7() {
        return this.uri;
    }

    @NotNull
    public final String component8() {
        return this.memo;
    }

    @NotNull
    public final SendWhatModel copy(@Nullable BigDecimal bigDecimal, @Nullable BigDecimal bigDecimal2, @Nullable BigDecimal bigDecimal3, @NotNull Coin coin2, @NotNull String str, @NotNull WalletId walletId, @NotNull BitcoinUriContent bitcoinUriContent, @NotNull String str2) {
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        String str3 = str;
        Intrinsics.checkParameterIsNotNull(str, Param.CURRENCY);
        WalletId walletId2 = walletId;
        Intrinsics.checkParameterIsNotNull(walletId2, "wallet");
        BitcoinUriContent bitcoinUriContent2 = bitcoinUriContent;
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent2, "uri");
        String str4 = str2;
        Intrinsics.checkParameterIsNotNull(str4, "memo");
        SendWhatModel sendWhatModel = new SendWhatModel(bigDecimal, bigDecimal2, bigDecimal3, coin3, str3, walletId2, bitcoinUriContent2, str4);
        return sendWhatModel;
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.memo, (java.lang.Object) r3.memo) != false) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x005b
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel r3 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel) r3
            java.math.BigDecimal r0 = r2.fiatAmount
            java.math.BigDecimal r1 = r3.fiatAmount
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.math.BigDecimal r0 = r2.cryptoAmount
            java.math.BigDecimal r1 = r3.cryptoAmount
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.math.BigDecimal r0 = r2.tokenAmount
            java.math.BigDecimal r1 = r3.tokenAmount
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.core.models.Coin r0 = r2.coin
            com.bitcoin.mwallet.core.models.Coin r1 = r3.coin
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.currency
            java.lang.String r1 = r3.currency
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.wallet
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r3.wallet
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r2.uri
            com.bitcoin.mwallet.core.models.BitcoinUriContent r1 = r3.uri
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.memo
            java.lang.String r3 = r3.memo
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r3 = 0
            return r3
        L_0x005b:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        BigDecimal bigDecimal = this.fiatAmount;
        int i = 0;
        int hashCode = (bigDecimal != null ? bigDecimal.hashCode() : 0) * 31;
        BigDecimal bigDecimal2 = this.cryptoAmount;
        int hashCode2 = (hashCode + (bigDecimal2 != null ? bigDecimal2.hashCode() : 0)) * 31;
        BigDecimal bigDecimal3 = this.tokenAmount;
        int hashCode3 = (hashCode2 + (bigDecimal3 != null ? bigDecimal3.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode4 = (hashCode3 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        String str = this.currency;
        int hashCode5 = (hashCode4 + (str != null ? str.hashCode() : 0)) * 31;
        WalletId walletId = this.wallet;
        int hashCode6 = (hashCode5 + (walletId != null ? walletId.hashCode() : 0)) * 31;
        BitcoinUriContent bitcoinUriContent = this.uri;
        int hashCode7 = (hashCode6 + (bitcoinUriContent != null ? bitcoinUriContent.hashCode() : 0)) * 31;
        String str2 = this.memo;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode7 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendWhatModel(fiatAmount=");
        sb.append(this.fiatAmount);
        sb.append(", cryptoAmount=");
        sb.append(this.cryptoAmount);
        sb.append(", tokenAmount=");
        sb.append(this.tokenAmount);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", currency=");
        sb.append(this.currency);
        sb.append(", wallet=");
        sb.append(this.wallet);
        sb.append(", uri=");
        sb.append(this.uri);
        sb.append(", memo=");
        sb.append(this.memo);
        sb.append(")");
        return sb.toString();
    }

    public SendWhatModel(@Nullable BigDecimal bigDecimal, @Nullable BigDecimal bigDecimal2, @Nullable BigDecimal bigDecimal3, @NotNull Coin coin2, @NotNull String str, @NotNull WalletId walletId, @NotNull BitcoinUriContent bitcoinUriContent, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(str, Param.CURRENCY);
        Intrinsics.checkParameterIsNotNull(walletId, "wallet");
        Intrinsics.checkParameterIsNotNull(bitcoinUriContent, "uri");
        Intrinsics.checkParameterIsNotNull(str2, "memo");
        this.fiatAmount = bigDecimal;
        this.cryptoAmount = bigDecimal2;
        this.tokenAmount = bigDecimal3;
        this.coin = coin2;
        this.currency = str;
        this.wallet = walletId;
        this.uri = bitcoinUriContent;
        this.memo = str2;
    }

    @Nullable
    public final BigDecimal getCryptoAmount() {
        return this.cryptoAmount;
    }

    @Nullable
    public final BigDecimal getFiatAmount() {
        return this.fiatAmount;
    }

    @Nullable
    public final BigDecimal getTokenAmount() {
        return this.tokenAmount;
    }

    public /* synthetic */ SendWhatModel(BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3, Coin coin2, String str, WalletId walletId, BitcoinUriContent bitcoinUriContent, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bigDecimal, bigDecimal2, bigDecimal3, coin2, str, walletId, bitcoinUriContent, (i & 128) != 0 ? "" : str2);
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final String getCurrency() {
        return this.currency;
    }

    @NotNull
    public final String getMemo() {
        return this.memo;
    }

    @NotNull
    public final BitcoinUriContent getUri() {
        return this.uri;
    }

    @NotNull
    public final WalletId getWallet() {
        return this.wallet;
    }

    public SendWhatModel(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        BigDecimal readBigDecimal = ParcelKt.readBigDecimal(parcel);
        BigDecimal readBigDecimal2 = ParcelKt.readBigDecimal(parcel);
        BigDecimal readBigDecimal3 = ParcelKt.readBigDecimal(parcel);
        Parcelable readParcelable = parcel.readParcelable(Coin.class.getClassLoader());
        if (readParcelable == null) {
            Intrinsics.throwNpe();
        }
        Coin coin2 = (Coin) readParcelable;
        String readString = parcel.readString();
        if (readString == null) {
            Intrinsics.throwNpe();
        }
        Serializable readSerializable = parcel.readSerializable();
        if (readSerializable != null) {
            WalletId walletId = (WalletId) readSerializable;
            Parcelable readParcelable2 = parcel.readParcelable(BitcoinUriContent.class.getClassLoader());
            if (readParcelable2 == null) {
                Intrinsics.throwNpe();
            }
            BitcoinUriContent bitcoinUriContent = (BitcoinUriContent) readParcelable2;
            String readString2 = parcel.readString();
            if (readString2 == null) {
                Intrinsics.throwNpe();
            }
            this(readBigDecimal, readBigDecimal2, readBigDecimal3, coin2, readString, walletId, bitcoinUriContent, readString2);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.core.models.wallet.WalletId");
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        ParcelKt.writeBigDecimal(parcel, this.fiatAmount);
        ParcelKt.writeBigDecimal(parcel, this.cryptoAmount);
        ParcelKt.writeBigDecimal(parcel, this.tokenAmount);
        parcel.writeParcelable(this.coin, i);
        parcel.writeString(this.currency);
        parcel.writeSerializable(this.wallet);
        parcel.writeParcelable(this.uri, i);
        parcel.writeString(this.memo);
    }
}
