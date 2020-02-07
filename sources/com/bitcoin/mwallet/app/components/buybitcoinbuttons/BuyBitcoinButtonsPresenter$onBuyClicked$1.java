package com.bitcoin.mwallet.app.components.buybitcoinbuttons;

import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.OnBuyBitcoinClickedListener;
import com.bitcoin.mwallet.core.models.Coin;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$onBuyClicked$1", mo38000f = "BuyBitcoinButtonsPresenter.kt", mo38001i = {1}, mo38002l = {41, 43}, mo38003m = "invokeSuspend", mo38004n = {"provider"}, mo38005s = {"L$0"})
/* compiled from: BuyBitcoinButtonsPresenter.kt */
final class BuyBitcoinButtonsPresenter$onBuyClicked$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Coin $coin;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f223p$;
    final /* synthetic */ BuyBitcoinButtonsPresenter this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$onBuyClicked$1$1", mo38000f = "BuyBitcoinButtonsPresenter.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$onBuyClicked$1$1 */
    /* compiled from: BuyBitcoinButtonsPresenter.kt */
    static final class C10731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f224p$;
        final /* synthetic */ BuyBitcoinButtonsPresenter$onBuyClicked$1 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10731 r0 = new C10731(this.this$0, buyBitcoinProvider2, continuation);
            r0.f224p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10731) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f224p$;
                OnBuyBitcoinClickedListener listener = this.this$0.this$0.getListener();
                if (listener == null) {
                    return null;
                }
                listener.onBuyBitcoinClicked(buyBitcoinProvider2, this.this$0.$coin);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    BuyBitcoinButtonsPresenter$onBuyClicked$1(BuyBitcoinButtonsPresenter buyBitcoinButtonsPresenter, Coin coin, Continuation continuation) {
        this.this$0 = buyBitcoinButtonsPresenter;
        this.$coin = coin;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        BuyBitcoinButtonsPresenter$onBuyClicked$1 buyBitcoinButtonsPresenter$onBuyClicked$1 = new BuyBitcoinButtonsPresenter$onBuyClicked$1(this.this$0, this.$coin, continuation);
        buyBitcoinButtonsPresenter$onBuyClicked$1.f223p$ = (CoroutineScope) obj;
        return buyBitcoinButtonsPresenter$onBuyClicked$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BuyBitcoinButtonsPresenter$onBuyClicked$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f223p$;
            BuyBitcoinButtonsPresenter buyBitcoinButtonsPresenter = this.this$0;
            this.label = 1;
            obj = buyBitcoinButtonsPresenter.getBuyBitcoinProvider(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            BuyBitcoinProvider buyBitcoinProvider = (BuyBitcoinProvider) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final BuyBitcoinProvider buyBitcoinProvider2 = (BuyBitcoinProvider) obj;
        if (buyBitcoinProvider2 == null) {
            return Unit.INSTANCE;
        }
        CoroutineContext main = Dispatchers.getMain();
        Function2 r3 = new C10731(this, null);
        this.L$0 = buyBitcoinProvider2;
        this.label = 2;
        if (BuildersKt.withContext(main, r3, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
