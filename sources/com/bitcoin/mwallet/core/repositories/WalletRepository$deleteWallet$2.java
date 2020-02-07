package com.bitcoin.mwallet.core.repositories;

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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletRepository.kt */
final class WalletRepository$deleteWallet$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WalletId $walletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f402p$;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$deleteWallet$2(WalletRepository walletRepository, WalletId walletId, Continuation continuation) {
        this.this$0 = walletRepository;
        this.$walletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$deleteWallet$2 walletRepository$deleteWallet$2 = new WalletRepository$deleteWallet$2(this.this$0, this.$walletId, continuation);
        walletRepository$deleteWallet$2.f402p$ = (CoroutineScope) obj;
        return walletRepository$deleteWallet$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$deleteWallet$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f402p$;
            this.this$0.database.runInTransaction((Runnable) new Runnable(this) {
                final /* synthetic */ WalletRepository$deleteWallet$2 this$0;

                @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
                @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1$1", mo38000f = "WalletRepository.kt", mo38001i = {1, 2, 3}, mo38002l = {365, 367, 369, 370}, mo38003m = "invokeSuspend", mo38004n = {"credentialId", "credentialId", "credentialId"}, mo38005s = {"L$0", "L$0", "L$0"})
                /* renamed from: com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1$1 */
                /* compiled from: WalletRepository.kt */
                static final class C12691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f403p$;
                    final /* synthetic */ C12681 this$0;

                    {
                        this.this$0 = r1;
                    }

                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        Intrinsics.checkParameterIsNotNull(continuation, "completion");
                        C12691 r0 = new C12691(this.this$0, continuation);
                        r0.f403p$ = (CoroutineScope) obj;
                        return r0;
                    }

                    public final Object invoke(Object obj, Object obj2) {
                        return ((C12691) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:22:0x008b A[RETURN] */
                    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a6 A[RETURN] */
                    @org.jetbrains.annotations.Nullable
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
                        /*
                            r6 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r6.label
                            r2 = 4
                            r3 = 3
                            r4 = 2
                            r5 = 1
                            if (r1 == 0) goto L_0x0039
                            if (r1 == r5) goto L_0x0035
                            if (r1 == r4) goto L_0x002d
                            if (r1 == r3) goto L_0x0025
                            if (r1 != r2) goto L_0x001d
                            java.lang.Object r0 = r6.L$0
                            com.bitcoin.mwallet.core.models.credential.CredentialId r0 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r0
                            kotlin.ResultKt.throwOnFailure(r7)
                            goto L_0x00a7
                        L_0x001d:
                            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r7.<init>(r0)
                            throw r7
                        L_0x0025:
                            java.lang.Object r1 = r6.L$0
                            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r1
                            kotlin.ResultKt.throwOnFailure(r7)
                            goto L_0x008c
                        L_0x002d:
                            java.lang.Object r1 = r6.L$0
                            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r1
                            kotlin.ResultKt.throwOnFailure(r7)
                            goto L_0x0071
                        L_0x0035:
                            kotlin.ResultKt.throwOnFailure(r7)
                            goto L_0x0057
                        L_0x0039:
                            kotlin.ResultKt.throwOnFailure(r7)
                            kotlinx.coroutines.CoroutineScope r7 = r6.f403p$
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1 r7 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2 r7 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r7 = r7.this$0
                            com.bitcoin.mwallet.core.dao.WalletDao r7 = r7.walletDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1 r1 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2 r1 = r1.this$0
                            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r1.$walletId
                            r6.label = r5
                            java.lang.Object r7 = r7.selectCredentialId(r1, r6)
                            if (r7 != r0) goto L_0x0057
                            return r0
                        L_0x0057:
                            r1 = r7
                            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r1
                            if (r1 == 0) goto L_0x0071
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1 r7 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2 r7 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r7 = r7.this$0
                            com.bitcoin.mwallet.core.dao.CredentialDao r7 = r7.credentialDao
                            r6.L$0 = r1
                            r6.label = r4
                            java.lang.Object r7 = r7.delete(r1, r6)
                            if (r7 != r0) goto L_0x0071
                            return r0
                        L_0x0071:
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1 r7 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2 r7 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r7 = r7.this$0
                            com.bitcoin.mwallet.core.dao.WalletDao r7 = r7.walletDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1 r4 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2 r4 = r4.this$0
                            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r4.$walletId
                            r6.L$0 = r1
                            r6.label = r3
                            java.lang.Object r7 = r7.deleteById(r4, r6)
                            if (r7 != r0) goto L_0x008c
                            return r0
                        L_0x008c:
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1 r7 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2 r7 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r7 = r7.this$0
                            com.bitcoin.mwallet.core.dao.WalletAddressInfoDao r7 = r7.walletAddressInfoDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2$1 r3 = r6.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2 r3 = r3.this$0
                            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = r3.$walletId
                            r6.L$0 = r1
                            r6.label = r2
                            java.lang.Object r7 = r7.delete(r3, r6)
                            if (r7 != r0) goto L_0x00a7
                            return r0
                        L_0x00a7:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository$deleteWallet$2.C12681.C12691.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    BuildersKt__BuildersKt.runBlocking$default(null, new C12691(this, null), 1, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
