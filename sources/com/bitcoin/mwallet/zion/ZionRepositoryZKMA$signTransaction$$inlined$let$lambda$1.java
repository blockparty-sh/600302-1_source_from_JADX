package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.Hex;
import com.bitcoin.bitcoink.Hex.Companion;
import com.bitcoin.mwallet.core.models.Coin;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$signTransaction$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$signTransaction$$inlined$let$lambda$1 extends Lambda implements Function0<ZionResponse<byte[]>> {
    final /* synthetic */ Coin $coin$inlined;
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ ZionId $zionId$inlined;
    final /* synthetic */ ZionTransaction $zionTransasction$inlined;
    final /* synthetic */ ZionRepositoryZKMA this$0;

    ZionRepositoryZKMA$signTransaction$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, ZionRepositoryZKMA zionRepositoryZKMA, Continuation continuation, ZionId zionId, ZionTransaction zionTransaction, Coin coin) {
        this.$it = htcWalletSdkManager;
        this.this$0 = zionRepositoryZKMA;
        this.$continuation$inlined = continuation;
        this.$zionId$inlined = zionId;
        this.$zionTransasction$inlined = zionTransaction;
        this.$coin$inlined = coin;
        super(0);
    }

    @NotNull
    public final ZionResponse<byte[]> invoke() {
        ByteArrayHolder byteArrayHolder = new ByteArrayHolder();
        String json = this.this$0.getZionTxJsonAdapter().toJson(this.$zionTransasction$inlined);
        Intrinsics.checkExpressionValueIsNotNull(json, "zionTxJsonAdapter.toJson(zionTransasction)");
        StringBuilder sb = new StringBuilder();
        sb.append("zionTransasction ");
        sb.append(json);
        Timber.m426d(sb.toString(), new Object[0]);
        int signTransaction = this.$it.signTransaction(this.$zionId$inlined.getId(), ZionRepositoryZKMA.Companion.toZionCoinType(this.$coin$inlined), 0.0f, json, byteArrayHolder);
        ZionError fromResult = ZionError.Companion.fromResult(signTransaction);
        if (fromResult != null) {
            return new ZionResponse<>(null, fromResult);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("signTransaction result=");
        sb2.append(signTransaction);
        sb2.append(" receivedLength=");
        sb2.append(byteArrayHolder.receivedLength);
        sb2.append(" raw=");
        Companion companion = Hex.Companion;
        byte[] bArr = byteArrayHolder.byteArray;
        Intrinsics.checkExpressionValueIsNotNull(bArr, "txHolder.byteArray");
        sb2.append(companion.encode(bArr));
        Timber.m426d(sb2.toString(), new Object[0]);
        return new ZionResponse<>(byteArrayHolder.byteArray, null);
    }
}
