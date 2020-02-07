package com.bitcoin.mwallet.core.utils.slp;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SlpTransactionType.kt */
final class SlpTransactionType$bytes$2 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ SlpTransactionType this$0;

    SlpTransactionType$bytes$2(SlpTransactionType slpTransactionType) {
        this.this$0 = slpTransactionType;
        super(0);
    }

    @NotNull
    public final byte[] invoke() {
        String access$getText$p = this.this$0.text;
        Charset charset = Charsets.US_ASCII;
        if (access$getText$p != null) {
            byte[] bytes = access$getText$p.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            return bytes;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
