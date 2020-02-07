package com.bitcoin.mwallet.zion;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.zion.ZionRepositoryZKMA$Companion$trySuspendZionCall$2", mo38000f = "ZionRepositoryZKMA.kt", mo38001i = {}, mo38002l = {350}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$Companion$trySuspendZionCall$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function1 $func;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f456p$;

    ZionRepositoryZKMA$Companion$trySuspendZionCall$2(Function1 function1, Continuation continuation) {
        this.$func = function1;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ZionRepositoryZKMA$Companion$trySuspendZionCall$2 zionRepositoryZKMA$Companion$trySuspendZionCall$2 = new ZionRepositoryZKMA$Companion$trySuspendZionCall$2(this.$func, continuation);
        zionRepositoryZKMA$Companion$trySuspendZionCall$2.f456p$ = (CoroutineScope) obj;
        return zionRepositoryZKMA$Companion$trySuspendZionCall$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ZionRepositoryZKMA$Companion$trySuspendZionCall$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f456p$;
            Function1 function1 = this.$func;
            this.label = 1;
            obj = function1.invoke(this);
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
