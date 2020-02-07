package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import com.bitcoin.mwallet.zion.ZionRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.walletsettings.WalletSettingsPresenter$hasZion$1", mo38000f = "WalletSettingsPresenter.kt", mo38001i = {}, mo38002l = {38}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletSettingsPresenter.kt */
final class WalletSettingsPresenter$hasZion$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f340p$;
    final /* synthetic */ WalletSettingsPresenter this$0;

    WalletSettingsPresenter$hasZion$1(WalletSettingsPresenter walletSettingsPresenter, Continuation continuation) {
        this.this$0 = walletSettingsPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletSettingsPresenter$hasZion$1 walletSettingsPresenter$hasZion$1 = new WalletSettingsPresenter$hasZion$1(this.this$0, continuation);
        walletSettingsPresenter$hasZion$1.f340p$ = (CoroutineScope) obj;
        return walletSettingsPresenter$hasZion$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletSettingsPresenter$hasZion$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f340p$;
            ZionRepository access$getZionRepository$p = this.this$0.zionRepository;
            this.label = 1;
            obj = access$getZionRepository$p.hasZion(this);
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
