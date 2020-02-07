package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.models.user.User;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/user/User;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.UserRepository$getOrCreateUser$2", mo38000f = "UserRepository.kt", mo38001i = {1, 2}, mo38002l = {37, 39, 40}, mo38003m = "invokeSuspend", mo38004n = {"createdUser", "createdUser"}, mo38005s = {"L$0", "L$0"})
/* compiled from: UserRepository.kt */
final class UserRepository$getOrCreateUser$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super User>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f394p$;
    final /* synthetic */ UserRepository this$0;

    UserRepository$getOrCreateUser$2(UserRepository userRepository, Continuation continuation) {
        this.this$0 = userRepository;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        UserRepository$getOrCreateUser$2 userRepository$getOrCreateUser$2 = new UserRepository$getOrCreateUser$2(this.this$0, continuation);
        userRepository$getOrCreateUser$2.f394p$ = (CoroutineScope) obj;
        return userRepository$getOrCreateUser$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((UserRepository$getOrCreateUser$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x008a A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x0030
            if (r1 == r5) goto L_0x002c
            if (r1 == r4) goto L_0x0023
            if (r1 != r3) goto L_0x001b
            java.lang.Object r0 = r8.L$0
            kotlin.Pair r0 = (kotlin.Pair) r0
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            goto L_0x008b
        L_0x001b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0023:
            java.lang.Object r1 = r8.L$0
            kotlin.Pair r1 = (kotlin.Pair) r1
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r1
            goto L_0x0070
        L_0x002c:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0044
        L_0x0030:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.CoroutineScope r9 = r8.f394p$
            com.bitcoin.mwallet.core.repositories.UserRepository r9 = r8.this$0
            com.bitcoin.mwallet.core.dao.UserDao r9 = r9.dao
            r8.label = r5
            java.lang.Object r9 = r9.getUser(r8)
            if (r9 != r0) goto L_0x0044
            return r0
        L_0x0044:
            com.bitcoin.mwallet.core.models.user.User r9 = (com.bitcoin.mwallet.core.models.user.User) r9
            if (r9 == 0) goto L_0x0049
            return r9
        L_0x0049:
            com.bitcoin.mwallet.core.models.user.User$Companion r9 = com.bitcoin.mwallet.core.models.user.User.Companion
            com.bitcoin.bitcoink.Mnemonic$Companion r1 = com.bitcoin.bitcoink.Mnemonic.Companion
            com.bitcoin.bitcoink.Mnemonic r1 = r1.generate()
            kotlin.Pair r9 = r9.createNew(r1)
            com.bitcoin.mwallet.core.repositories.UserRepository r1 = r8.this$0
            com.bitcoin.mwallet.core.dao.CredentialDao r1 = r1.credentialDao
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic[] r6 = new com.bitcoin.mwallet.core.models.credential.CredentialMnemonic[r5]
            java.lang.Object r7 = r9.getSecond()
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r7 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r7
            r6[r2] = r7
            r8.L$0 = r9
            r8.label = r4
            java.lang.Object r1 = r1.upsert(r6, r8)
            if (r1 != r0) goto L_0x0070
            return r0
        L_0x0070:
            com.bitcoin.mwallet.core.repositories.UserRepository r1 = r8.this$0
            com.bitcoin.mwallet.core.dao.UserDao r1 = r1.dao
            com.bitcoin.mwallet.core.models.user.User[] r4 = new com.bitcoin.mwallet.core.models.user.User[r5]
            java.lang.Object r5 = r9.getFirst()
            com.bitcoin.mwallet.core.models.user.User r5 = (com.bitcoin.mwallet.core.models.user.User) r5
            r4[r2] = r5
            r8.L$0 = r9
            r8.label = r3
            java.lang.Object r1 = r1.upsert(r4, r8)
            if (r1 != r0) goto L_0x008b
            return r0
        L_0x008b:
            java.lang.Object r9 = r9.getFirst()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.UserRepository$getOrCreateUser$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
