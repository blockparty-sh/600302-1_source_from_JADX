package com.bitcoin.bitcoink.address;

import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.AddressCashUtil.AddressVersionAndBytes;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\n\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0000H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/AddressCash;", "Lcom/bitcoin/bitcoink/address/Address;", "network", "Lcom/bitcoin/bitcoink/Network;", "version", "", "bytes", "", "(Lcom/bitcoin/bitcoink/Network;I[B)V", "toString", "", "getToString", "()Ljava/lang/String;", "toString$delegate", "Lkotlin/Lazy;", "type", "Lcom/bitcoin/bitcoink/address/AddressType;", "addressType", "toCash", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressCash.kt */
public final class AddressCash extends Address {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AddressCash.class), "toString", "getToString()Ljava/lang/String;"))};
    public static final Companion Companion = new Companion(null);
    private final Lazy toString$delegate;
    /* access modifiers changed from: private */
    public final AddressType type = addressType();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\u0018\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/AddressCash$Companion;", "", "()V", "prefix", "", "network", "Lcom/bitcoin/bitcoink/Network;", "prefix$bitcoink", "tryParse", "Lcom/bitcoin/bitcoink/address/AddressCash;", "address", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AddressCash.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Network.values().length];

            static {
                $EnumSwitchMapping$0[Network.MAIN.ordinal()] = 1;
                $EnumSwitchMapping$0[Network.TEST.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final AddressCash tryParse(@NotNull Network network, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            Intrinsics.checkParameterIsNotNull(str, "address");
            AddressVersionAndBytes tryParseAddress$bitcoink = Address.Companion.tryParseAddress$bitcoink(prefix$bitcoink(network), str);
            if (tryParseAddress$bitcoink == null) {
                return null;
            }
            byte version = tryParseAddress$bitcoink.getVersion();
            byte[] bytes = tryParseAddress$bitcoink.getBytes();
            Intrinsics.checkExpressionValueIsNotNull(bytes, "bytes.bytes");
            return new AddressCash(network, version, bytes);
        }

        @NotNull
        public final String prefix$bitcoink(@NotNull Network network) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            int i = WhenMappings.$EnumSwitchMapping$0[network.ordinal()];
            if (i == 1) {
                return "bitcoincash";
            }
            if (i == 2) {
                return "bchtest";
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    private final String getToString() {
        Lazy lazy = this.toString$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (String) lazy.getValue();
    }

    @NotNull
    public AddressCash toCash() {
        return this;
    }

    public AddressCash(@NotNull Network network, int i, @NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(network, "network");
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        super(network, i, bArr);
        this.toString$delegate = LazyKt.lazy(new AddressCash$toString$2(this, network, bArr));
    }

    private final AddressType addressType() {
        if (getVersion() == 5) {
            return AddressType.Companion.fromVersion((byte) 8);
        }
        return AddressType.Companion.fromVersion((byte) getVersion());
    }

    @NotNull
    public String toString() {
        return getToString();
    }
}
