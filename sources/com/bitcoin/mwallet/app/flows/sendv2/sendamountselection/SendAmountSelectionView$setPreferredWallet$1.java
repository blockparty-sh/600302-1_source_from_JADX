package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView$setPreferredWallet$1", mo38000f = "SendAmountSelectionView.kt", mo38001i = {}, mo38002l = {56}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$setPreferredWallet$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WalletId $preferredWalletId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f303p$;
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$setPreferredWallet$1(SendAmountSelectionView sendAmountSelectionView, WalletId walletId, Continuation continuation) {
        this.this$0 = sendAmountSelectionView;
        this.$preferredWalletId = walletId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SendAmountSelectionView$setPreferredWallet$1 sendAmountSelectionView$setPreferredWallet$1 = new SendAmountSelectionView$setPreferredWallet$1(this.this$0, this.$preferredWalletId, continuation);
        sendAmountSelectionView$setPreferredWallet$1.f303p$ = (CoroutineScope) obj;
        return sendAmountSelectionView$setPreferredWallet$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SendAmountSelectionView$setPreferredWallet$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f303p$;
            GetWalletInteractor getWalletInteractor = ((SendAmountSelectionPresenter) this.this$0.getPresenter()).getGetWalletInteractor();
            WalletId walletId = this.$preferredWalletId;
            this.label = 1;
            obj = getWalletInteractor.getWallet(walletId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        C1261Wallet wallet = (C1261Wallet) obj;
        if (wallet != null) {
            if (this.this$0.getWalletType().getCoin() != wallet.getCoin()) {
                SendAmountSelectionPresenter sendAmountSelectionPresenter = (SendAmountSelectionPresenter) this.this$0.getPresenter();
                FragmentManager childFragmentManager = this.this$0.getChildFragmentManager();
                Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
                sendAmountSelectionPresenter.onWalletSelectTapped(childFragmentManager, this.this$0.getWalletType());
            } else {
                ((SendAmountSelectionPresenter) this.this$0.getPresenter()).getSelectedWalletId().postValue(this.$preferredWalletId);
            }
        }
        return Unit.INSTANCE;
    }
}
