package com.bitcoin.mwallet.core.services.networkfee;

import com.bitcoin.mwallet.core.models.Coin;
import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeService;", "", "getNetworkFee", "Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeResponse;", "coin", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NetworkFeeService.kt */
public interface NetworkFeeService {
    @Nullable
    Object getNetworkFee(@NotNull Set<? extends Coin> set, @NotNull Continuation<? super NetworkFeeResponse> continuation);
}
