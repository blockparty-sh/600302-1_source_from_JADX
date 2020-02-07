package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter.SingleWalletAssetViewHolder;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$tokens$1", mo38000f = "WalletAssetsRecyclerAdapter.kt", mo38001i = {}, mo38002l = {108}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* renamed from: com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$tokens$1 */
/* compiled from: WalletAssetsRecyclerAdapter.kt */
final class C1082xb6ac1a5a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Slp>>, Object> {
    final /* synthetic */ WalletInfoBalance $walletBalance;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f231p$;
    final /* synthetic */ SingleWalletAssetViewHolder this$0;

    C1082xb6ac1a5a(SingleWalletAssetViewHolder singleWalletAssetViewHolder, WalletInfoBalance walletInfoBalance, Continuation continuation) {
        this.this$0 = singleWalletAssetViewHolder;
        this.$walletBalance = walletInfoBalance;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        C1082xb6ac1a5a walletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$tokens$1 = new C1082xb6ac1a5a(this.this$0, this.$walletBalance, continuation);
        walletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$tokens$1.f231p$ = (CoroutineScope) obj;
        return walletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$tokens$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((C1082xb6ac1a5a) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f231p$;
            SlpRepository slpRepository = this.this$0.getPresenter().getSlpRepository();
            WalletId walletId = this.$walletBalance.getWalletId();
            this.label = 1;
            obj = slpRepository.getAllTokensFromWalletId(walletId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
