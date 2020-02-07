package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter$createWallet$1", mo38000f = "CreateUserPresenter.kt", mo38001i = {}, mo38002l = {99}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: CreateUserPresenter.kt */
final class CreateUserPresenter$createWallet$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f250p$;
    final /* synthetic */ CreateUserPresenter this$0;

    CreateUserPresenter$createWallet$1(CreateUserPresenter createUserPresenter, Continuation continuation) {
        this.this$0 = createUserPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        CreateUserPresenter$createWallet$1 createUserPresenter$createWallet$1 = new CreateUserPresenter$createWallet$1(this.this$0, continuation);
        createUserPresenter$createWallet$1.f250p$ = (CoroutineScope) obj;
        return createUserPresenter$createWallet$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CreateUserPresenter$createWallet$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f250p$;
            CreateUserInteractor access$getInteractor$p = this.this$0.interactor;
            this.label = 1;
            if (access$getInteractor$p.createDefaultWallets(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.creatingWallet.setValue(Boxing.boxBoolean(false));
        return Unit.INSTANCE;
    }
}
