package com.bitcoin.mwallet.zion;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionWalletName.kt */
final class ZionWalletName$name$2 extends Lambda implements Function0<String> {
    final /* synthetic */ ZionWalletName this$0;

    ZionWalletName$name$2(ZionWalletName zionWalletName) {
        this.this$0 = zionWalletName;
        super(0);
    }

    @NotNull
    public final String invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.this$0.getPrefix().getPrefix());
        sb.append('-');
        sb.append(this.this$0.getIndex());
        return sb.toString();
    }
}
