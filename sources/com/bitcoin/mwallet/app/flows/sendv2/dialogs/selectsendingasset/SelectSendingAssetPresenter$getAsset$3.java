package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import com.bitcoin.mwallet.core.models.slp.Slp;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SelectSendingAssetPresenter$getAsset$3", mo38000f = "SelectSendingAssetPresenter.kt", mo38001i = {}, mo38002l = {53}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SelectSendingAssetPresenter.kt */
final class SelectSendingAssetPresenter$getAsset$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Slp>>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f282p$;
    final /* synthetic */ SelectSendingAssetPresenter this$0;

    SelectSendingAssetPresenter$getAsset$3(SelectSendingAssetPresenter selectSendingAssetPresenter, Continuation continuation) {
        this.this$0 = selectSendingAssetPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SelectSendingAssetPresenter$getAsset$3 selectSendingAssetPresenter$getAsset$3 = new SelectSendingAssetPresenter$getAsset$3(this.this$0, continuation);
        selectSendingAssetPresenter$getAsset$3.f282p$ = (CoroutineScope) obj;
        return selectSendingAssetPresenter$getAsset$3;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SelectSendingAssetPresenter$getAsset$3) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f282p$;
            SlpRepository access$getSlpRepository$p = this.this$0.slpRepository;
            this.label = 1;
            obj = access$getSlpRepository$p.getAllTokens(this);
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
