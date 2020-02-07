package com.bitcoin.mwallet.core.services.wallet;

import com.bitcoin.mwallet.MigrateSingleSigWalletResponse;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/MigrateSingleSigWalletResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.wallet.WalletServiceGrpc$migrateExisitingWallet$2$response$1", mo38000f = "WalletServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletServiceGrpc.kt */
final class WalletServiceGrpc$migrateExisitingWallet$2$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MigrateSingleSigWalletResponse>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f452p$;
    final /* synthetic */ WalletServiceGrpc$migrateExisitingWallet$2 this$0;

    WalletServiceGrpc$migrateExisitingWallet$2$response$1(WalletServiceGrpc$migrateExisitingWallet$2 walletServiceGrpc$migrateExisitingWallet$2, Continuation continuation) {
        this.this$0 = walletServiceGrpc$migrateExisitingWallet$2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletServiceGrpc$migrateExisitingWallet$2$response$1 walletServiceGrpc$migrateExisitingWallet$2$response$1 = new WalletServiceGrpc$migrateExisitingWallet$2$response$1(this.this$0, continuation);
        walletServiceGrpc$migrateExisitingWallet$2$response$1.f452p$ = (CoroutineScope) obj;
        return walletServiceGrpc$migrateExisitingWallet$2$response$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletServiceGrpc$migrateExisitingWallet$2$response$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f452p$;
            return this.this$0.this$0.stub.migrateExistingWallet(this.this$0.$request);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
