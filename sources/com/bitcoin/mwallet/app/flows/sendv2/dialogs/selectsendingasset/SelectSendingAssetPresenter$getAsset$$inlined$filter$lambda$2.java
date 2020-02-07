package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import java.math.BigDecimal;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "Ljava/math/BigDecimal;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetPresenter$getAsset$4$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetPresenter.kt */
final class SelectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BigDecimal>, Object> {
    final /* synthetic */ Slp $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f280p$;
    final /* synthetic */ SelectSendingAssetPresenter this$0;

    SelectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2(Slp slp, Continuation continuation, SelectSendingAssetPresenter selectSendingAssetPresenter) {
        this.$it = slp;
        this.this$0 = selectSendingAssetPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SelectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2 selectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2 = new SelectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2(this.$it, continuation, this.this$0);
        selectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2.f280p$ = (CoroutineScope) obj;
        return selectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SelectSendingAssetPresenter$getAsset$$inlined$filter$lambda$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f280p$;
            SlpRepository access$getSlpRepository$p = this.this$0.slpRepository;
            SlpId tokenId = this.$it.getTokenId();
            this.label = 1;
            obj = access$getSlpRepository$p.getUserTokenAmount(tokenId, this);
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
