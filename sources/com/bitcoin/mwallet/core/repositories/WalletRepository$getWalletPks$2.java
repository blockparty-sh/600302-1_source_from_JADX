package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.WalletDao;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$getWalletPks$2", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {306}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletRepository.kt */
final class WalletRepository$getWalletPks$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends WalletId>>, Object> {
    final /* synthetic */ Coin $coin;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f414p$;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$getWalletPks$2(WalletRepository walletRepository, Coin coin, Continuation continuation) {
        this.this$0 = walletRepository;
        this.$coin = coin;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$getWalletPks$2 walletRepository$getWalletPks$2 = new WalletRepository$getWalletPks$2(this.this$0, this.$coin, continuation);
        walletRepository$getWalletPks$2.f414p$ = (CoroutineScope) obj;
        return walletRepository$getWalletPks$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$getWalletPks$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f414p$;
            WalletDao access$getWalletDao$p = this.this$0.walletDao;
            Coin coin = this.$coin;
            this.label = 1;
            obj = access$getWalletDao$p.getWalletPks(coin, this);
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
