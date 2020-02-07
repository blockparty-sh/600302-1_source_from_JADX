package com.bitcoin.bitcoink.address;

import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.AddressCashUtil.AddressVersionAndBytes;
import com.bitcoin.bitcoink.util.Base58CheckEncoding;
import com.microsoft.appcenter.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/Address;", "Lcom/bitcoin/bitcoink/util/Base58CheckEncoding;", "network", "Lcom/bitcoin/bitcoink/Network;", "version", "", "bytes", "", "(Lcom/bitcoin/bitcoink/Network;I[B)V", "getNetwork", "()Lcom/bitcoin/bitcoink/Network;", "toCash", "Lcom/bitcoin/bitcoink/address/AddressCash;", "toEllipsizedString", "", "toLegacy", "Lcom/bitcoin/bitcoink/address/AddressLegacy;", "toSlp", "Lcom/bitcoin/bitcoink/address/AddressSLP;", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Address.kt */
public abstract class Address extends Base58CheckEncoding {
    public static final Companion Companion = new Companion(null);
    private static final int length = 20;
    @NotNull
    private final Network network;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0018\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001f\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0013R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/Address$Companion;", "", "()V", "length", "", "assertLength", "", "bytes", "assertVersion", "network", "Lcom/bitcoin/bitcoink/Network;", "version", "tryParse", "Lcom/bitcoin/bitcoink/address/Address;", "address", "", "tryParseAddress", "Lcom/bitcoin/bitcoink/address/AddressCashUtil$AddressVersionAndBytes;", "prefix", "tryParseAddress$bitcoink", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Address.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final int assertVersion(Network network, int i) {
            int[] acceptableAddressCodes$bitcoink = network.getAcceptableAddressCodes$bitcoink();
            int length = acceptableAddressCodes$bitcoink.length;
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (acceptableAddressCodes$bitcoink[i2] == i) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bad version ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            } else if (i == 8) {
                return 5;
            } else {
                return i;
            }
        }

        /* access modifiers changed from: private */
        public final byte[] assertLength(byte[] bArr) {
            if (bArr.length == 20) {
                return bArr;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Bad address length ");
            sb.append(bArr.length);
            throw new IllegalArgumentException(sb.toString());
        }

        @Nullable
        public final AddressVersionAndBytes tryParseAddress$bitcoink(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "prefix");
            Intrinsics.checkParameterIsNotNull(str2, "address");
            List split$default = StringsKt.split$default((CharSequence) str2, new String[]{Constants.COMMON_SCHEMA_PREFIX_SEPARATOR}, false, 0, 6, (Object) null);
            if (split$default.size() != 2 || (!Intrinsics.areEqual((Object) (String) split$default.get(0), (Object) str))) {
                return null;
            }
            try {
                return AddressCashUtil.decode(str, str2);
            } catch (RuntimeException unused) {
                return null;
            }
        }

        @Nullable
        public final Address tryParse(@NotNull Network network, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            Intrinsics.checkParameterIsNotNull(str, "address");
            if (StringsKt.startsWith$default(str, AddressCash.Companion.prefix$bitcoink(network), false, 2, null)) {
                return AddressCash.Companion.tryParse(network, str);
            }
            if (StringsKt.startsWith$default(str, AddressSLP.Companion.prefix$bitcoink(network), false, 2, null)) {
                return AddressSLP.Companion.tryParse(network, str);
            }
            return AddressLegacy.Companion.parse(network, str);
        }
    }

    @NotNull
    public final Network getNetwork() {
        return this.network;
    }

    public Address(@NotNull Network network2, int i, @NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(network2, "network");
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        super(Companion.assertVersion(network2, i), Companion.assertLength(bArr));
        this.network = network2;
    }

    @NotNull
    public AddressSLP toSlp() {
        return new AddressSLP(this.network, getVersion(), getBytes());
    }

    @NotNull
    public AddressCash toCash() {
        return new AddressCash(this.network, getVersion(), getBytes());
    }

    @NotNull
    public AddressLegacy toLegacy() {
        return new AddressLegacy(this.network, getVersion(), getBytes());
    }

    @NotNull
    public String toEllipsizedString() {
        String address = toString();
        int indexOf$default = StringsKt.indexOf$default((CharSequence) address, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, 0, false, 6, (Object) null);
        String str = "null cannot be cast to non-null type java.lang.String";
        if (indexOf$default != -1) {
            int i = indexOf$default + 1;
            if (address != null) {
                address = address.substring(i);
                Intrinsics.checkExpressionValueIsNotNull(address, "(this as java.lang.String).substring(startIndex)");
            } else {
                throw new TypeCastException(str);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (address != null) {
            String substring = address.substring(0, 5);
            String str2 = "(this as java.lang.Strin…ing(startIndex, endIndex)";
            Intrinsics.checkExpressionValueIsNotNull(substring, str2);
            sb.append(substring);
            sb.append("....");
            int length2 = address.length() - 5;
            int length3 = address.length();
            if (address != null) {
                String substring2 = address.substring(length2, length3);
                Intrinsics.checkExpressionValueIsNotNull(substring2, str2);
                sb.append(substring2);
                return sb.toString();
            }
            throw new TypeCastException(str);
        }
        throw new TypeCastException(str);
    }
}
