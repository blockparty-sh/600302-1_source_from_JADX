package com.bitcoin.mwallet.core.models.address;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.address.Address.Companion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000fH\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "parsed", "Lcom/bitcoin/bitcoink/address/Address;", "originalText", "", "(Lcom/bitcoin/bitcoink/address/Address;Ljava/lang/String;)V", "getOriginalText", "()Ljava/lang/String;", "getParsed", "()Lcom/bitcoin/bitcoink/address/Address;", "describeContents", "", "writeToParcel", "", "flags", "CREATOR", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressAndOiginalText.kt */
public final class AddressAndOriginalText implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    @NotNull
    private final String originalText;
    @NotNull
    private final Address parsed;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/bitcoin/mwallet/core/models/address/AddressAndOriginalText;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AddressAndOiginalText.kt */
    public static final class CREATOR implements Creator<AddressAndOriginalText> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public AddressAndOriginalText createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new AddressAndOriginalText(parcel);
        }

        @NotNull
        public AddressAndOriginalText[] newArray(int i) {
            return new AddressAndOriginalText[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public AddressAndOriginalText(@NotNull Address address, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(address, "parsed");
        Intrinsics.checkParameterIsNotNull(str, "originalText");
        this.parsed = address;
        this.originalText = str;
    }

    @NotNull
    public final Address getParsed() {
        return this.parsed;
    }

    @NotNull
    public final String getOriginalText() {
        return this.originalText;
    }

    public AddressAndOriginalText(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        Companion companion = Address.Companion;
        Network network = Network.MAIN;
        String readString = parcel.readString();
        if (readString == null) {
            readString = "";
        }
        Address tryParse = companion.tryParse(network, readString);
        if (tryParse == null) {
            tryParse = new AddressInvalid();
        }
        String readString2 = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(readString2, "parcel.readString()");
        this(tryParse, readString2);
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.parsed.toBase58());
        parcel.writeString(this.originalText);
    }
}
