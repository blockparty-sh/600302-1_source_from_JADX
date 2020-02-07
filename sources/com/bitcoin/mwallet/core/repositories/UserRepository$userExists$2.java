package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.UserDao;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.UserRepository$userExists$2", mo38000f = "UserRepository.kt", mo38001i = {}, mo38002l = {30}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: UserRepository.kt */
final class UserRepository$userExists$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f395p$;
    final /* synthetic */ UserRepository this$0;

    UserRepository$userExists$2(UserRepository userRepository, Continuation continuation) {
        this.this$0 = userRepository;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        UserRepository$userExists$2 userRepository$userExists$2 = new UserRepository$userExists$2(this.this$0, continuation);
        userRepository$userExists$2.f395p$ = (CoroutineScope) obj;
        return userRepository$userExists$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((UserRepository$userExists$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f395p$;
            UserDao access$getDao$p = this.this$0.dao;
            this.label = 1;
            obj = access$getDao$p.getUser(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (obj == null) {
            z = false;
        }
        return Boxing.boxBoolean(z);
    }
}
