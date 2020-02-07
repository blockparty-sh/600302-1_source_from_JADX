package com.bitcoin.mwallet.core.services;

import com.bitcoin.mwallet.CreateUserRequest;
import com.bitcoin.mwallet.CreateUserResponse;
import com.google.protobuf.GeneratedMessageLite;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/CreateUserResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$response$1", mo38000f = "UserServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: UserServiceGrpc.kt */
final class UserServiceGrpc$createUser$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CreateUserResponse>, Object> {
    final /* synthetic */ String $xPubKey;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f428p$;
    final /* synthetic */ UserServiceGrpc this$0;

    UserServiceGrpc$createUser$response$1(UserServiceGrpc userServiceGrpc, String str, Continuation continuation) {
        this.this$0 = userServiceGrpc;
        this.$xPubKey = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        UserServiceGrpc$createUser$response$1 userServiceGrpc$createUser$response$1 = new UserServiceGrpc$createUser$response$1(this.this$0, this.$xPubKey, continuation);
        userServiceGrpc$createUser$response$1.f428p$ = (CoroutineScope) obj;
        return userServiceGrpc$createUser$response$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((UserServiceGrpc$createUser$response$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f428p$;
            GeneratedMessageLite build = CreateUserRequest.newBuilder().setRequestXPubKey(this.$xPubKey).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "CreateUserRequest.newBui…tXPubKey(xPubKey).build()");
            return this.this$0.stub.createUser((CreateUserRequest) build);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
