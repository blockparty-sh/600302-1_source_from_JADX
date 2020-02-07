package com.bitcoin.mwallet.app.flows.walletdetails.deletewallet;

import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.deletewallet.DeletewalletPresenter$setWalletNameText$1", mo38000f = "DeletewalletPresenter.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: DeletewalletPresenter.kt */
final class DeletewalletPresenter$setWalletNameText$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $text;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f319p$;
    final /* synthetic */ DeletewalletPresenter this$0;

    DeletewalletPresenter$setWalletNameText$1(DeletewalletPresenter deletewalletPresenter, String str, Continuation continuation) {
        this.this$0 = deletewalletPresenter;
        this.$text = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        DeletewalletPresenter$setWalletNameText$1 deletewalletPresenter$setWalletNameText$1 = new DeletewalletPresenter$setWalletNameText$1(this.this$0, this.$text, continuation);
        deletewalletPresenter$setWalletNameText$1.f319p$ = (CoroutineScope) obj;
        return deletewalletPresenter$setWalletNameText$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((DeletewalletPresenter$setWalletNameText$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f319p$;
            MutableLiveData buttonIsEnabled = this.this$0.getButtonIsEnabled();
            String str = this.$text;
            C1261Wallet wallet = this.this$0.getWallet();
            buttonIsEnabled.setValue(Boxing.boxBoolean(Intrinsics.areEqual((Object) str, (Object) wallet != null ? wallet.getName() : null)));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
