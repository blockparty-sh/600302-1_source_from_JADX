package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.util.Sha256Hash;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "Lcom/bitcoin/mwallet/zion/ZionId;", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$registerWallet$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$registerWallet$$inlined$let$lambda$1 extends Lambda implements Function0<ZionResponse<ZionId>> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ ZionWalletName $zionWalletName$inlined;

    ZionRepositoryZKMA$registerWallet$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionWalletName zionWalletName) {
        this.$it = htcWalletSdkManager;
        this.$continuation$inlined = continuation;
        this.$zionWalletName$inlined = zionWalletName;
        super(0);
    }

    @NotNull
    public final ZionResponse<ZionId> invoke() {
        Sha256Hash sha256Hash = Sha256Hash.INSTANCE;
        String name = this.$zionWalletName$inlined.getName();
        Charset charset = Charsets.UTF_8;
        if (name != null) {
            byte[] bytes = name.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            long register = this.$it.register(this.$zionWalletName$inlined.getName(), new String(sha256Hash.hashOnce(bytes), Charsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            sb.append("Registered wallet with zionId=");
            sb.append(register);
            Timber.m426d(sb.toString(), new Object[0]);
            return new ZionResponse<>(new ZionId(register), null);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
