package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogPresenter.WhenMappings;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogPresenter$unlockWallet$1", mo38000f = "LockedWalletDialogPresenter.kt", mo38001i = {0}, mo38002l = {39}, mo38003m = "invokeSuspend", mo38004n = {"mnemonicString"}, mo38005s = {"L$0"})
/* compiled from: LockedWalletDialogPresenter.kt */
final class LockedWalletDialogPresenter$unlockWallet$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $password;
    final /* synthetic */ WalletId $walletId;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f227p$;
    final /* synthetic */ LockedWalletDialogPresenter this$0;

    LockedWalletDialogPresenter$unlockWallet$1(LockedWalletDialogPresenter lockedWalletDialogPresenter, WalletId walletId, String str, Continuation continuation) {
        this.this$0 = lockedWalletDialogPresenter;
        this.$walletId = walletId;
        this.$password = str;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        LockedWalletDialogPresenter$unlockWallet$1 lockedWalletDialogPresenter$unlockWallet$1 = new LockedWalletDialogPresenter$unlockWallet$1(this.this$0, this.$walletId, this.$password, continuation);
        lockedWalletDialogPresenter$unlockWallet$1.f227p$ = (CoroutineScope) obj;
        return lockedWalletDialogPresenter$unlockWallet$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((LockedWalletDialogPresenter$unlockWallet$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f227p$;
            String str = (String) BuildersKt__BuildersKt.runBlocking$default(null, new LockedWalletDialogPresenter$unlockWallet$1$mnemonicString$1(this, null), 1, null);
            if (str == null) {
                this.this$0.getResultSuccess().postValue(UnlockWalletResult.INVALID_PASSWORD);
                return Unit.INSTANCE;
            }
            LockedWalletDialogInteractor access$getInteractor$p = this.this$0.interactor;
            WalletId walletId = this.$walletId;
            this.L$0 = str;
            this.label = 1;
            obj = access$getInteractor$p.unlockWallet(walletId, str, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            String str2 = (String) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[((CreateWalletResult) obj).ordinal()];
        if (i2 == 1) {
            this.this$0.getResultSuccess().postValue(UnlockWalletResult.SUCCESS);
        } else if (i2 == 2) {
            this.this$0.getResultSuccess().postValue(UnlockWalletResult.INVALID_MNEMONIC);
        } else if (i2 != 3) {
            this.this$0.getResultSuccess().postValue(UnlockWalletResult.RECOVER_FAILED);
        } else {
            this.this$0.getResultSuccess().postValue(UnlockWalletResult.DUPLICATE_MNEMONIC);
        }
        return Unit.INSTANCE;
    }
}
