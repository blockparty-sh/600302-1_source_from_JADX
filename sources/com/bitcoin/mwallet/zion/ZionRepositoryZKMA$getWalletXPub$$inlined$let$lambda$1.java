package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.core.models.Coin;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import com.htc.htcwalletsdk.Security.Key.PublicKeyHolder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "Lcom/bitcoin/mwallet/zion/ZionXPub;", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$getWalletXPub$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$getWalletXPub$$inlined$let$lambda$1 extends Lambda implements Function0<ZionResponse<ZionXPub>> {
    final /* synthetic */ int $account$inlined;
    final /* synthetic */ Coin $coin$inlined;
    final /* synthetic */ int $coinType$inlined;
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ int $purpose$inlined;
    final /* synthetic */ DerivationPath $walletPath$inlined;
    final /* synthetic */ ZionId $zionId$inlined;

    ZionRepositoryZKMA$getWalletXPub$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionId zionId, int i, int i2, int i3, Coin coin, DerivationPath derivationPath) {
        this.$it = htcWalletSdkManager;
        this.$continuation$inlined = continuation;
        this.$zionId$inlined = zionId;
        this.$purpose$inlined = i;
        this.$coinType$inlined = i2;
        this.$account$inlined = i3;
        this.$coin$inlined = coin;
        this.$walletPath$inlined = derivationPath;
        super(0);
    }

    @NotNull
    public final ZionResponse<ZionXPub> invoke() {
        PublicKeyHolder accountExtPublicKey = this.$it.getAccountExtPublicKey(this.$zionId$inlined.getId(), this.$purpose$inlined, this.$coinType$inlined, this.$account$inlined);
        Intrinsics.checkExpressionValueIsNotNull(accountExtPublicKey, "it.getAccountExtPublicKe…rpose, coinType, account)");
        String key = accountExtPublicKey.getKey();
        Intrinsics.checkExpressionValueIsNotNull(key, "it.getAccountExtPublicKe…e, coinType, account).key");
        return new ZionResponse<>(new ZionXPub(this.$zionId$inlined, this.$coin$inlined, this.$walletPath$inlined, ExtendedPublicKey.Companion.fromBase58(key, Network.MAIN)), null);
    }
}
