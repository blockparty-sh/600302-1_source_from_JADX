package com.bitcoin.mwallet.app.flows.receive.receive;

import android.app.Activity;
import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.ReceiveActivity;
import com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter.WhenMappings;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$setWalletType$1", mo38000f = "ReceivePresenter.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: ReceivePresenter.kt */
final class ReceivePresenter$setWalletType$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WalletType $type;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f267p$;
    final /* synthetic */ ReceivePresenter this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$setWalletType$1$1", mo38000f = "ReceivePresenter.kt", mo38001i = {}, mo38002l = {136}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.receive.receive.ReceivePresenter$setWalletType$1$1 */
    /* compiled from: ReceivePresenter.kt */
    static final class C10991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super WalletId>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f268p$;
        final /* synthetic */ ReceivePresenter$setWalletType$1 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10991 r0 = new C10991(this.this$0, continuation);
            r0.f268p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10991) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f268p$;
                GetWalletInteractor access$getGetWalletInteractor$p = this.this$0.this$0.getWalletInteractor;
                Coin coin = this.this$0.$type.getCoin();
                this.label = 1;
                obj = access$getGetWalletInteractor$p.getWalletIdsByCoin(coin, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return CollectionsKt.firstOrNull((List) obj);
        }
    }

    ReceivePresenter$setWalletType$1(ReceivePresenter receivePresenter, WalletType walletType, Continuation continuation) {
        this.this$0 = receivePresenter;
        this.$type = walletType;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ReceivePresenter$setWalletType$1 receivePresenter$setWalletType$1 = new ReceivePresenter$setWalletType$1(this.this$0, this.$type, continuation);
        receivePresenter$setWalletType$1.f267p$ = (CoroutineScope) obj;
        return receivePresenter$setWalletType$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ReceivePresenter$setWalletType$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        WalletId walletId;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f267p$;
            this.this$0.getCurrentWalletType().postValue(this.$type);
            Context access$getContext$p = this.this$0.context;
            if (access$getContext$p != null) {
                Activity activityContext = ((ApplicationClass) access$getContext$p).getActivityContext();
                if (activityContext != null) {
                    ((ReceiveActivity) activityContext).getGlobalReceiveModel().setAmountValue(null);
                    int i = WhenMappings.$EnumSwitchMapping$1[this.$type.ordinal()];
                    if (i == 1) {
                        walletId = this.this$0.getPrevCashId();
                    } else if (i == 2) {
                        walletId = this.this$0.getPrevCoreId();
                    } else if (i == 3) {
                        walletId = this.this$0.getPrevCashId();
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                    MutableLiveData selectedWalletId = this.this$0.getSelectedWalletId();
                    if (walletId == null) {
                        walletId = (WalletId) BuildersKt__BuildersKt.runBlocking$default(null, new C10991(this, null), 1, null);
                    }
                    selectedWalletId.postValue(walletId);
                    return Unit.INSTANCE;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ReceiveActivity");
            }
            throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ApplicationClass");
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
