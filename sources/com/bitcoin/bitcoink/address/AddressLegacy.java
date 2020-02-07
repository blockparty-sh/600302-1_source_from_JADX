package com.bitcoin.bitcoink.address;

import com.bitcoin.bitcoink.Network;
import com.microsoft.appcenter.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bitcoinj.core.Address;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0000H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/AddressLegacy;", "Lcom/bitcoin/bitcoink/address/Address;", "network", "Lcom/bitcoin/bitcoink/Network;", "version", "", "bytes", "", "(Lcom/bitcoin/bitcoink/Network;I[B)V", "toLegacy", "toString", "", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressLegacy.kt */
public final class AddressLegacy extends Address {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/AddressLegacy$Companion;", "", "()V", "parse", "Lcom/bitcoin/bitcoink/address/AddressLegacy;", "network", "Lcom/bitcoin/bitcoink/Network;", "base58", "", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AddressLegacy.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final AddressLegacy parse(@NotNull Network network, @NotNull String str) {
            String str2 = Constants.COMMON_SCHEMA_PREFIX_SEPARATOR;
            Intrinsics.checkParameterIsNotNull(network, "network");
            Intrinsics.checkParameterIsNotNull(str, "base58");
            try {
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) str2, false, 2, (Object) null)) {
                    str = StringsKt.substringAfter$default(str, str2, (String) null, 2, (Object) null);
                }
                Address fromBase58 = Address.fromBase58(network.getParams$bitcoink(), str);
                Intrinsics.checkExpressionValueIsNotNull(fromBase58, "org.bitcoinj.core.Addres…params, addressSubstring)");
                int version = fromBase58.getVersion();
                byte[] hash160 = fromBase58.getHash160();
                Intrinsics.checkExpressionValueIsNotNull(hash160, "address.hash160");
                return new AddressLegacy(network, version, hash160);
            } catch (RuntimeException unused) {
                return null;
            }
        }
    }

    @NotNull
    public AddressLegacy toLegacy() {
        return this;
    }

    public AddressLegacy(@NotNull Network network, int i, @NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(network, "network");
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        super(network, i, bArr);
    }

    @NotNull
    public String toString() {
        return toBase58();
    }
}
