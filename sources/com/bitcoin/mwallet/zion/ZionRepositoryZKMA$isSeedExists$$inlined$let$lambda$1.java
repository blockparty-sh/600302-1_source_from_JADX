package com.bitcoin.mwallet.zion;

import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$isSeedExists$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$isSeedExists$$inlined$let$lambda$1 extends Lambda implements Function0<ZionResponse<Boolean>> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ ZionId $zionId$inlined;

    ZionRepositoryZKMA$isSeedExists$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionId zionId) {
        this.$it = htcWalletSdkManager;
        this.$continuation$inlined = continuation;
        this.$zionId$inlined = zionId;
        super(0);
    }

    @NotNull
    public final ZionResponse<Boolean> invoke() {
        int isSeedExists = this.$it.isSeedExists(this.$zionId$inlined.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("isSeedExists zionId=");
        sb.append(this.$zionId$inlined);
        sb.append(" seedExists=");
        sb.append(isSeedExists);
        boolean z = false;
        Timber.m426d(sb.toString(), new Object[0]);
        if (isSeedExists == 0) {
            z = true;
        }
        return new ZionResponse<>(Boolean.valueOf(z), null);
    }
}
