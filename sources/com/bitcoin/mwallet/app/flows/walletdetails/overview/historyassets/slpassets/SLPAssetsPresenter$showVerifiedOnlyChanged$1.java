package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter$showVerifiedOnlyChanged$1", mo38000f = "SLPAssetsPresenter.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SLPAssetsPresenter.kt */
final class SLPAssetsPresenter$showVerifiedOnlyChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $showOnlyVerifed;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f326p$;
    final /* synthetic */ SLPAssetsPresenter this$0;

    SLPAssetsPresenter$showVerifiedOnlyChanged$1(SLPAssetsPresenter sLPAssetsPresenter, boolean z, Continuation continuation) {
        this.this$0 = sLPAssetsPresenter;
        this.$showOnlyVerifed = z;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SLPAssetsPresenter$showVerifiedOnlyChanged$1 sLPAssetsPresenter$showVerifiedOnlyChanged$1 = new SLPAssetsPresenter$showVerifiedOnlyChanged$1(this.this$0, this.$showOnlyVerifed, continuation);
        sLPAssetsPresenter$showVerifiedOnlyChanged$1.f326p$ = (CoroutineScope) obj;
        return sLPAssetsPresenter$showVerifiedOnlyChanged$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SLPAssetsPresenter$showVerifiedOnlyChanged$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f326p$;
            this.this$0.modifyWalletInteractor.setShowUnverifiedTokens(this.this$0.getWalletId(), !this.$showOnlyVerifed);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
