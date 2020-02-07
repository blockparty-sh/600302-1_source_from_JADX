package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletRepository.kt */
final class WalletRepository$deleteWallet$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CredentialId $credentialId;
    final /* synthetic */ WalletId $walletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f404p$;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$deleteWallet$4(WalletRepository walletRepository, CredentialId credentialId, WalletId walletId, Continuation continuation) {
        this.this$0 = walletRepository;
        this.$credentialId = credentialId;
        this.$walletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$deleteWallet$4 walletRepository$deleteWallet$4 = new WalletRepository$deleteWallet$4(this.this$0, this.$credentialId, this.$walletId, continuation);
        walletRepository$deleteWallet$4.f404p$ = (CoroutineScope) obj;
        return walletRepository$deleteWallet$4;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$deleteWallet$4) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f404p$;
            this.this$0.database.runInTransaction((Runnable) new Runnable(this) {
                final /* synthetic */ WalletRepository$deleteWallet$4 this$0;

                @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
                @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1$1", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {380, 381, 382}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
                /* renamed from: com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1$1 */
                /* compiled from: WalletRepository.kt */
                static final class C12711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f405p$;
                    final /* synthetic */ C12701 this$0;

                    {
                        this.this$0 = r1;
                    }

                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        Intrinsics.checkParameterIsNotNull(continuation, "completion");
                        C12711 r0 = new C12711(this.this$0, continuation);
                        r0.f405p$ = (CoroutineScope) obj;
                        return r0;
                    }

                    public final Object invoke(Object obj, Object obj2) {
                        return ((C12711) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:18:0x007e A[RETURN] */
                    @org.jetbrains.annotations.Nullable
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r6) {
                        /*
                            r5 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r5.label
                            r2 = 3
                            r3 = 2
                            r4 = 1
                            if (r1 == 0) goto L_0x0025
                            if (r1 == r4) goto L_0x0021
                            if (r1 == r3) goto L_0x001d
                            if (r1 != r2) goto L_0x0015
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L_0x007f
                        L_0x0015:
                            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r6.<init>(r0)
                            throw r6
                        L_0x001d:
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L_0x0066
                        L_0x0021:
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L_0x0043
                        L_0x0025:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlinx.coroutines.CoroutineScope r6 = r5.f405p$
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1 r6 = r5.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4 r6 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r6 = r6.this$0
                            com.bitcoin.mwallet.core.dao.CredentialDao r6 = r6.credentialDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1 r1 = r5.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4 r1 = r1.this$0
                            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = r1.$credentialId
                            r5.label = r4
                            java.lang.Object r6 = r6.delete(r1, r5)
                            if (r6 != r0) goto L_0x0043
                            return r0
                        L_0x0043:
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1 r6 = r5.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4 r6 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r6 = r6.this$0
                            com.bitcoin.mwallet.core.dao.WalletDao r6 = r6.walletDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1 r1 = r5.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4 r1 = r1.this$0
                            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r1.$walletId
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1 r4 = r5.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4 r4 = r4.this$0
                            com.bitcoin.mwallet.core.models.credential.CredentialId r4 = r4.$credentialId
                            java.lang.String r4 = r4.getId()
                            r5.label = r3
                            java.lang.Object r6 = r6.deleteByIdAndCredentialId(r1, r4, r5)
                            if (r6 != r0) goto L_0x0066
                            return r0
                        L_0x0066:
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1 r6 = r5.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4 r6 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r6 = r6.this$0
                            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r6 = r6.walletAddressInfoDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4$1 r1 = r5.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4 r1 = r1.this$0
                            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r1.$walletId
                            r5.label = r2
                            java.lang.Object r6 = r6.delete(r1, r5)
                            if (r6 != r0) goto L_0x007f
                            return r0
                        L_0x007f:
                            kotlin.Unit r6 = kotlin.Unit.INSTANCE
                            return r6
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$4.C12701.C12711.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    BuildersKt__BuildersKt.runBlocking$default(null, new C12711(this, null), 1, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
