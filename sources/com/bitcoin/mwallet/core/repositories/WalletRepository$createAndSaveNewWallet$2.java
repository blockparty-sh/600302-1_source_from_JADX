package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import kotlin.Metadata;
import kotlin.Pair;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$createAndSaveNewWallet$2", mo38000f = "WalletRepository.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: WalletRepository.kt */
final class WalletRepository$createAndSaveNewWallet$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends C1261Wallet, ? extends CredentialMnemonic>>, Object> {
    final /* synthetic */ Coin $coin;
    final /* synthetic */ String $walletName;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f401p$;

    WalletRepository$createAndSaveNewWallet$2(Coin coin, String str, Continuation continuation) {
        this.$coin = coin;
        this.$walletName = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        WalletRepository$createAndSaveNewWallet$2 walletRepository$createAndSaveNewWallet$2 = new WalletRepository$createAndSaveNewWallet$2(this.$coin, this.$walletName, continuation);
        walletRepository$createAndSaveNewWallet$2.f401p$ = (CoroutineScope) obj;
        return walletRepository$createAndSaveNewWallet$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((WalletRepository$createAndSaveNewWallet$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f401p$;
            return C1261Wallet.Companion.createNew(this.$coin, this.$walletName);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
