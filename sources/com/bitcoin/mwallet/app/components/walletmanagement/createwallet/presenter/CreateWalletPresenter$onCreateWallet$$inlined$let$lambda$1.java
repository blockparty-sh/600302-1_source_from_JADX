package com.bitcoin.mwallet.app.components.walletmanagement.createwallet.presenter;

import com.bitcoin.mwallet.core.models.Coin;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/bitcoin/mwallet/app/components/walletmanagement/createwallet/presenter/CreateWalletPresenter$onCreateWallet$1$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CreateWalletPresenter.kt */
final class CreateWalletPresenter$onCreateWallet$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Coin $coin;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f233p$;
    final /* synthetic */ CreateWalletPresenter this$0;

    CreateWalletPresenter$onCreateWallet$$inlined$let$lambda$1(Coin coin, Continuation continuation, CreateWalletPresenter createWalletPresenter) {
        this.$coin = coin;
        this.this$0 = createWalletPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        CreateWalletPresenter$onCreateWallet$$inlined$let$lambda$1 createWalletPresenter$onCreateWallet$$inlined$let$lambda$1 = new CreateWalletPresenter$onCreateWallet$$inlined$let$lambda$1(this.$coin, continuation, this.this$0);
        createWalletPresenter$onCreateWallet$$inlined$let$lambda$1.f233p$ = (CoroutineScope) obj;
        return createWalletPresenter$onCreateWallet$$inlined$let$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CreateWalletPresenter$onCreateWallet$$inlined$let$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f233p$;
            CreateWalletPresenter createWalletPresenter = this.this$0;
            Coin coin = this.$coin;
            this.label = 1;
            if (createWalletPresenter.createWallet(coin, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
