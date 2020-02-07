package com.bitcoin.mwallet.core;

import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/Constants;", "", "()V", "defaultCurrency", "Ljava/util/Currency;", "getDefaultCurrency", "()Ljava/util/Currency;", "defaultNetworkFeeLevel", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "getDefaultNetworkFeeLevel", "()Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Constants.kt */
public final class Constants {
    public static final Constants INSTANCE = new Constants();
    @NotNull
    private static final Currency defaultCurrency;
    @NotNull
    private static final NetworkFeeLevel defaultNetworkFeeLevel = NetworkFeeLevel.NORMAL;

    static {
        Currency instance = Currency.getInstance("USD");
        Intrinsics.checkExpressionValueIsNotNull(instance, "Currency.getInstance(\"USD\")");
        defaultCurrency = instance;
    }

    private Constants() {
    }

    @NotNull
    public final Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    @NotNull
    public final NetworkFeeLevel getDefaultNetworkFeeLevel() {
        return defaultNetworkFeeLevel;
    }
}
