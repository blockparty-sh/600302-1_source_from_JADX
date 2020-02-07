package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter$init$2", mo38000f = "SendAmountSelectionPresenter.kt", mo38001i = {}, mo38002l = {213}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SendAmountSelectionPresenter.kt */
final class SendAmountSelectionPresenter$init$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Coin $coin;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f299p$;
    final /* synthetic */ SendAmountSelectionPresenter this$0;

    SendAmountSelectionPresenter$init$2(SendAmountSelectionPresenter sendAmountSelectionPresenter, Coin coin, Continuation continuation) {
        this.this$0 = sendAmountSelectionPresenter;
        this.$coin = coin;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SendAmountSelectionPresenter$init$2 sendAmountSelectionPresenter$init$2 = new SendAmountSelectionPresenter$init$2(this.this$0, this.$coin, continuation);
        sendAmountSelectionPresenter$init$2.f299p$ = (CoroutineScope) obj;
        return sendAmountSelectionPresenter$init$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SendAmountSelectionPresenter$init$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        MutableLiveData mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f299p$;
            MutableLiveData selectedWalletId = this.this$0.getSelectedWalletId();
            GetWalletInteractor getWalletInteractor = this.this$0.getGetWalletInteractor();
            Coin coin = this.$coin;
            this.L$0 = selectedWalletId;
            this.label = 1;
            Object walletIdsByCoin = getWalletInteractor.getWalletIdsByCoin(coin, this);
            if (walletIdsByCoin == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutableLiveData = selectedWalletId;
            obj = walletIdsByCoin;
        } else if (i == 1) {
            mutableLiveData = (MutableLiveData) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableLiveData.setValue(CollectionsKt.firstOrNull((List) obj));
        return Unit.INSTANCE;
    }
}
