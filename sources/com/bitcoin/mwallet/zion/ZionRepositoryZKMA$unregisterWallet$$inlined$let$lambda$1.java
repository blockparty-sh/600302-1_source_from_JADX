package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.util.Sha256Hash;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo37405d2 = {"<anonymous>", "", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$unregisterWallet$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$unregisterWallet$$inlined$let$lambda$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ ZionId $zionId$inlined;
    final /* synthetic */ ZionWalletName $zionWalletName$inlined;

    ZionRepositoryZKMA$unregisterWallet$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionWalletName zionWalletName, ZionId zionId) {
        this.$it = htcWalletSdkManager;
        this.$continuation$inlined = continuation;
        this.$zionWalletName$inlined = zionWalletName;
        this.$zionId$inlined = zionId;
        super(0);
    }

    public final void invoke() {
        Sha256Hash sha256Hash = Sha256Hash.INSTANCE;
        String name = this.$zionWalletName$inlined.getName();
        Charset charset = Charsets.UTF_8;
        if (name != null) {
            byte[] bytes = name.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            this.$it.unregister(this.$zionWalletName$inlined.getName(), new String(sha256Hash.hashOnce(bytes), Charsets.UTF_8), this.$zionId$inlined.getId());
            StringBuilder sb = new StringBuilder();
            sb.append("Unregistered wallet with zionId=");
            sb.append(this.$zionId$inlined);
            Timber.m426d(sb.toString(), new Object[0]);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
