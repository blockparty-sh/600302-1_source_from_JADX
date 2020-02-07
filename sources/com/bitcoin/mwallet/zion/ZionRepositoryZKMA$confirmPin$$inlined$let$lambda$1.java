package com.bitcoin.mwallet.zion;

import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/zion/ZionError;", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$confirmPin$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$confirmPin$$inlined$let$lambda$1 extends Lambda implements Function0<ZionError> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ ZionId $zionId$inlined;

    ZionRepositoryZKMA$confirmPin$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionId zionId) {
        this.$it = htcWalletSdkManager;
        this.$continuation$inlined = continuation;
        this.$zionId$inlined = zionId;
        super(0);
    }

    @Nullable
    public final ZionError invoke() {
        int confirmPIN = this.$it.confirmPIN(this.$zionId$inlined.getId(), 4);
        StringBuilder sb = new StringBuilder();
        sb.append("confirmPin result=");
        sb.append(confirmPIN);
        Timber.m426d(sb.toString(), new Object[0]);
        return ZionError.Companion.fromResult(confirmPIN);
    }
}
