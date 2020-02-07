package com.bitcoin.bitcoink.address;

import com.bitcoin.bitcoink.Network;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AddressCash.kt */
final class AddressCash$toString$2 extends Lambda implements Function0<String> {
    final /* synthetic */ byte[] $bytes;
    final /* synthetic */ Network $network;
    final /* synthetic */ AddressCash this$0;

    AddressCash$toString$2(AddressCash addressCash, Network network, byte[] bArr) {
        this.this$0 = addressCash;
        this.$network = network;
        this.$bytes = bArr;
        super(0);
    }

    @NotNull
    public final String invoke() {
        AddressType access$getType$p = this.this$0.type;
        if (access$getType$p != null) {
            String encodeCashAddress = AddressCashUtil.encodeCashAddress(AddressCash.Companion.prefix$bitcoink(this.$network), AddressCashUtil.packAddressData(this.$bytes, access$getType$p.getByte()));
            if (encodeCashAddress != null) {
                return encodeCashAddress;
            }
        }
        return "Unknown";
    }
}
