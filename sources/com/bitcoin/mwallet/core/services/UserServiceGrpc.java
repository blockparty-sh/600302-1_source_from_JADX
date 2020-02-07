package com.bitcoin.mwallet.core.services;

import androidx.core.app.NotificationCompat;
import com.bitcoin.mwallet.UserServiceGrpc.UserServiceBlockingStub;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import p015io.grpc.ManagedChannel;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/UserServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "service", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;Lcom/bitcoin/mwallet/core/repositories/UserRepository;)V", "stub", "Lcom/bitcoin/mwallet/UserServiceGrpc$UserServiceBlockingStub;", "createUser", "", "xPubKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UserServiceGrpc.kt */
public final class UserServiceGrpc extends GrpcServiceBase {
    /* access modifiers changed from: private */
    public final UserRepository service;
    /* access modifiers changed from: private */
    public final UserServiceBlockingStub stub;

    public UserServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel, @NotNull UserRepository userRepository) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        Intrinsics.checkParameterIsNotNull(userRepository, NotificationCompat.CATEGORY_SERVICE);
        super(coroutineDispatcher);
        this.service = userRepository;
        UserServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.UserServiceGrpc.newBlockingStub(managedChannel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "UserServiceGrpc.newBlockingStub(channel)");
        this.stub = newBlockingStub;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009e A[PHI: r10 
      PHI: (r10v2 java.lang.Object) = (r10v5 java.lang.Object), (r10v1 java.lang.Object) binds: [B:19:0x009b, B:10:0x002a] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createUser(@org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$1 r0 = (com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$1 r0 = new com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0052
            if (r2 == r5) goto L_0x0046
            if (r2 != r4) goto L_0x003e
            java.lang.Object r9 = r0.L$3
            com.bitcoin.mwallet.core.models.user.UserId r9 = (com.bitcoin.mwallet.core.models.user.UserId) r9
            java.lang.Object r9 = r0.L$2
            com.bitcoin.mwallet.CreateUserResponse r9 = (com.bitcoin.mwallet.CreateUserResponse) r9
            java.lang.Object r9 = r0.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r9 = r0.L$0
            com.bitcoin.mwallet.core.services.UserServiceGrpc r9 = (com.bitcoin.mwallet.core.services.UserServiceGrpc) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x009e
        L_0x003e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0046:
            java.lang.Object r9 = r0.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.core.services.UserServiceGrpc r2 = (com.bitcoin.mwallet.core.services.UserServiceGrpc) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0070
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.CoroutineDispatcher r10 = r8.getGrpcDispatcher()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$response$1 r2 = new com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$response$1
            r2.<init>(r8, r9, r3)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r2, r0)
            if (r10 != r1) goto L_0x006f
            return r1
        L_0x006f:
            r2 = r8
        L_0x0070:
            java.lang.String r5 = "withContext(grpcDispatch…teUser(request)\n        }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r5)
            com.bitcoin.mwallet.CreateUserResponse r10 = (com.bitcoin.mwallet.CreateUserResponse) r10
            com.bitcoin.mwallet.core.models.user.UserId r5 = new com.bitcoin.mwallet.core.models.user.UserId
            java.lang.String r6 = r10.getUserId()
            r5.<init>(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$2 r7 = new com.bitcoin.mwallet.core.services.UserServiceGrpc$createUser$2
            r7.<init>(r2, r5, r3)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r5
            r0.label = r4
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r0)
            if (r10 != r1) goto L_0x009e
            return r1
        L_0x009e:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.UserServiceGrpc.createUser(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
