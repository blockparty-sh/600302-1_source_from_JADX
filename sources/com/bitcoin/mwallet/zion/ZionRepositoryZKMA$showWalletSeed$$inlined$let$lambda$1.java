package com.bitcoin.mwallet.zion;

import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo37405d2 = {"<anonymous>", "", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$showWalletSeed$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$showWalletSeed$$inlined$let$lambda$1 extends Lambda implements Function0 {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ ZionId $zionId$inlined;

    ZionRepositoryZKMA$showWalletSeed$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionId zionId) {
        this.$it = htcWalletSdkManager;
        this.$continuation$inlined = continuation;
        this.$zionId$inlined = zionId;
        super(0);
    }

    @Nullable
    public final Void invoke() {
        int showSeed = this.$it.showSeed(this.$zionId$inlined.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("showSeed zionId=");
        sb.append(this.$zionId$inlined);
        sb.append(" showSeed=");
        sb.append(showSeed);
        Timber.m426d(sb.toString(), new Object[0]);
        return null;
    }
}
