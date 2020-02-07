package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletRepository.kt */
final class WalletRepository$saveNewWalletRemote$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Credential $credentialToSave;
    final /* synthetic */ CredentialId $oldCredentialId;
    final /* synthetic */ C1261Wallet $wallet;
    final /* synthetic */ C1261Wallet $walletToSave;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f421p$;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$saveNewWalletRemote$3(WalletRepository walletRepository, C1261Wallet wallet, C1261Wallet wallet2, CredentialId credentialId, Credential credential, Continuation continuation) {
        this.this$0 = walletRepository;
        this.$wallet = wallet;
        this.$walletToSave = wallet2;
        this.$oldCredentialId = credentialId;
        this.$credentialToSave = credential;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$saveNewWalletRemote$3 walletRepository$saveNewWalletRemote$3 = new WalletRepository$saveNewWalletRemote$3(this.this$0, this.$wallet, this.$walletToSave, this.$oldCredentialId, this.$credentialToSave, continuation);
        walletRepository$saveNewWalletRemote$3.f421p$ = (CoroutineScope) obj;
        return walletRepository$saveNewWalletRemote$3;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$saveNewWalletRemote$3) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f421p$;
            this.this$0.database.runInTransaction((Runnable) new Runnable(this) {
                final /* synthetic */ WalletRepository$saveNewWalletRemote$3 this$0;

                @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
                @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1$1", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {267, 268, 269, 270}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
                /* renamed from: com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1$1 */
                /* compiled from: WalletRepository.kt */
                static final class C12731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f422p$;
                    final /* synthetic */ C12721 this$0;

                    {
                        this.this$0 = r1;
                    }

                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        Intrinsics.checkParameterIsNotNull(continuation, "completion");
                        C12731 r0 = new C12731(this.this$0, continuation);
                        r0.f422p$ = (CoroutineScope) obj;
                        return r0;
                    }

                    public final Object invoke(Object obj, Object obj2) {
                        return ((C12731) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0085 A[RETURN] */
                    /* JADX WARNING: Removed duplicated region for block: B:23:0x009e A[RETURN] */
                    @org.jetbrains.annotations.Nullable
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
                        /*
                            r7 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r7.label
                            r2 = 4
                            r3 = 3
                            r4 = 2
                            r5 = 1
                            if (r1 == 0) goto L_0x002d
                            if (r1 == r5) goto L_0x0029
                            if (r1 == r4) goto L_0x0025
                            if (r1 == r3) goto L_0x0021
                            if (r1 != r2) goto L_0x0019
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L_0x009f
                        L_0x0019:
                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r8.<init>(r0)
                            throw r8
                        L_0x0021:
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L_0x0086
                        L_0x0025:
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L_0x006d
                        L_0x0029:
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L_0x004f
                        L_0x002d:
                            kotlin.ResultKt.throwOnFailure(r8)
                            kotlinx.coroutines.CoroutineScope r8 = r7.f422p$
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r8 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r8 = r8.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r8 = r8.this$0
                            com.bitcoin.mwallet.core.dao.WalletDao r8 = r8.walletDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r1 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r1 = r1.this$0
                            com.bitcoin.mwallet.core.models.wallet.Wallet r1 = r1.$wallet
                            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r1.getId()
                            r7.label = r5
                            java.lang.Object r8 = r8.deleteById(r1, r7)
                            if (r8 != r0) goto L_0x004f
                            return r0
                        L_0x004f:
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r8 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r8 = r8.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r8 = r8.this$0
                            com.bitcoin.mwallet.core.dao.WalletDao r8 = r8.walletDao
                            com.bitcoin.mwallet.core.models.wallet.Wallet[] r1 = new com.bitcoin.mwallet.core.models.wallet.C1261Wallet[r5]
                            r5 = 0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r6 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r6 = r6.this$0
                            com.bitcoin.mwallet.core.models.wallet.Wallet r6 = r6.$walletToSave
                            r1[r5] = r6
                            r7.label = r4
                            java.lang.Object r8 = r8.upsert(r1, r7)
                            if (r8 != r0) goto L_0x006d
                            return r0
                        L_0x006d:
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r8 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r8 = r8.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r8 = r8.this$0
                            com.bitcoin.mwallet.core.dao.CredentialDao r8 = r8.credentialDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r1 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r1 = r1.this$0
                            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = r1.$oldCredentialId
                            r7.label = r3
                            java.lang.Object r8 = r8.delete(r1, r7)
                            if (r8 != r0) goto L_0x0086
                            return r0
                        L_0x0086:
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r8 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r8 = r8.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository r8 = r8.this$0
                            com.bitcoin.mwallet.core.dao.CredentialDao r8 = r8.credentialDao
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3$1 r1 = r7.this$0
                            com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3 r1 = r1.this$0
                            com.bitcoin.mwallet.core.models.credential.Credential r1 = r1.$credentialToSave
                            r7.label = r2
                            java.lang.Object r8 = r8.upsert(r1, r7)
                            if (r8 != r0) goto L_0x009f
                            return r0
                        L_0x009f:
                            kotlin.Unit r8 = kotlin.Unit.INSTANCE
                            return r8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.WalletRepository$saveNewWalletRemote$3.C12721.C12731.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    BuildersKt__BuildersKt.runBlocking$default(null, new C12731(this, null), 1, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
