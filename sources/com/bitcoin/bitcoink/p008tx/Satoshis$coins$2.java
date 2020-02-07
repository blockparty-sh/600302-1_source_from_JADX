package com.bitcoin.bitcoink.p008tx;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "Ljava/math/BigDecimal;", "kotlin.jvm.PlatformType", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.bitcoink.tx.Satoshis$coins$2 */
/* compiled from: Satoshis.kt */
final class Satoshis$coins$2 extends Lambda implements Function0<BigDecimal> {
    final /* synthetic */ Satoshis this$0;

    Satoshis$coins$2(Satoshis satoshis) {
        this.this$0 = satoshis;
        super(0);
    }

    public final BigDecimal invoke() {
        return new BigDecimal(this.this$0.getSatoshis()).movePointLeft(8);
    }
}
