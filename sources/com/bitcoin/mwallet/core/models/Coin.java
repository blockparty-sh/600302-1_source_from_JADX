package com.bitcoin.mwallet.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bitcoinj.utils.MonetaryFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0018B-\b\u0002\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0001\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0016R\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fj\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/Coin;", "", "Landroid/os/Parcelable;", "presentableNameResId", "", "ticker", "", "iconResId", "iconContentDescriptionResId", "(Ljava/lang/String;IILjava/lang/String;II)V", "getIconContentDescriptionResId", "()I", "getIconResId", "getPresentableNameResId", "getTicker", "()Ljava/lang/String;", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "BCH", "BTC", "CREATOR", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Coin.kt */
public enum Coin implements Parcelable {
    BCH(C1018R.string.general_bitcoin_cash, MonetaryFormat.CODE_BTC, C1018R.C1020drawable.logo_bch_green, C1018R.string.general_bitcoin_cash_logo_content_description),
    BTC(C1018R.string.general_bitcoin_core, "BTC", C1018R.C1020drawable.logo_btc, C1018R.string.general_bitcoin_core_logo_content_description);
    
    public static final CREATOR CREATOR = null;
    private final int iconContentDescriptionResId;
    private final int iconResId;
    private final int presentableNameResId;
    @NotNull
    private final String ticker;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\tJ\u001d\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/Coin$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/bitcoin/mwallet/core/models/Coin;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "fromTicker", "ticker", "", "newArray", "", "size", "", "(I)[Lcom/bitcoin/mwallet/core/models/Coin;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Coin.kt */
    public static final class CREATOR implements Creator<Coin> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public Coin createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            return Coin.valueOf(readString);
        }

        @NotNull
        public Coin[] newArray(int i) {
            return new Coin[i];
        }

        @Nullable
        public final Coin fromTicker(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "ticker");
            if (Intrinsics.areEqual((Object) str, (Object) Coin.BCH.getTicker())) {
                return Coin.BCH;
            }
            if (Intrinsics.areEqual((Object) str, (Object) Coin.BTC.getTicker())) {
                return Coin.BTC;
            }
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    private Coin(@StringRes int i, String str, @DrawableRes int i2, @StringRes int i3) {
        this.presentableNameResId = i;
        this.ticker = str;
        this.iconResId = i2;
        this.iconContentDescriptionResId = i3;
    }

    public final int getPresentableNameResId() {
        return this.presentableNameResId;
    }

    @NotNull
    public final String getTicker() {
        return this.ticker;
    }

    public final int getIconResId() {
        return this.iconResId;
    }

    public final int getIconContentDescriptionResId() {
        return this.iconContentDescriptionResId;
    }

    static {
        CREATOR = new CREATOR(null);
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(name());
    }
}
