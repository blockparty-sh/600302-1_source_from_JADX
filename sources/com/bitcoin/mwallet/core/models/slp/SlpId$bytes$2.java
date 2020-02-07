package com.bitcoin.mwallet.core.models.slp;

import com.bitcoin.bitcoink.util.ByteUtils.Hex;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SlpId.kt */
final class SlpId$bytes$2 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ SlpId this$0;

    SlpId$bytes$2(SlpId slpId) {
        this.this$0 = slpId;
        super(0);
    }

    @NotNull
    public final byte[] invoke() {
        return Hex.INSTANCE.decode(this.this$0.getId());
    }
}
