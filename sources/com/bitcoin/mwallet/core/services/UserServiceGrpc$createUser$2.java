package com.bitcoin.mwallet.core.services;

import com.bitcoin.mwallet.core.models.user.UserId;
import com.bitcoin.mwallet.core.repositories.UserRepository;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$2", mo38000f = "UserServiceGrpc.kt", mo38001i = {}, mo38002l = {36}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: UserServiceGrpc.kt */
final class UserServiceGrpc$createUser$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ UserId $userId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f427p$;
    final /* synthetic */ UserServiceGrpc this$0;

    UserServiceGrpc$createUser$2(UserServiceGrpc userServiceGrpc, UserId userId, Continuation continuation) {
        this.this$0 = userServiceGrpc;
        this.$userId = userId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        UserServiceGrpc$createUser$2 userServiceGrpc$createUser$2 = new UserServiceGrpc$createUser$2(this.this$0, this.$userId, continuation);
        userServiceGrpc$createUser$2.f427p$ = (CoroutineScope) obj;
        return userServiceGrpc$createUser$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((UserServiceGrpc$createUser$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f427p$;
            UserRepository access$getService$p = this.this$0.service;
            UserId userId = this.$userId;
            this.label = 1;
            if (access$getService$p.setUserId(userId, this) == coroutine_suspended) {
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
