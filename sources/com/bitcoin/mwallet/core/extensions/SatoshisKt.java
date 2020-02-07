package com.bitcoin.mwallet.core.extensions;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.Coin;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, mo37405d2 = {"toCoinString", "", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Satoshis.kt */
public final class SatoshisKt {
    @NotNull
    public static /* synthetic */ String toCoinString$default(Satoshis satoshis, Coin coin, int i, Object obj) {
        if ((i & 1) != 0) {
            coin = null;
        }
        return toCoinString(satoshis, coin);
    }

    @NotNull
    public static final String toCoinString(@Nullable Satoshis satoshis, @Nullable Coin coin) {
        String str = null;
        if (satoshis == null || satoshis.compareTo(0) <= 0) {
            BigDecimal bigDecimal = BigDecimal.ZERO;
            Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "BigDecimal.ZERO");
            if (coin != null) {
                str = coin.getTicker();
            }
            return BigDecimalKt.toCoinString(bigDecimal, str);
        }
        BigDecimal coins = satoshis.getCoins();
        if (coin != null) {
            str = coin.getTicker();
        }
        return BigDecimalKt.toCoinString(coins, str);
    }
}
