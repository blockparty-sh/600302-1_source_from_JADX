package com.bitcoin.mwallet.app.components.buybitcoinbuttons;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.android.Event;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.OnBuyBitcoinClickedListener;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.models.Coin;
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
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u001f\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/BuyBitcoinButtonsPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "getBuyBitcoinProviderInteractor", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/GetBuyBitcoinProviderInteractor;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/GetBuyBitcoinProviderInteractor;)V", "_error", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/android/Event;", "", "get_error", "()Landroidx/lifecycle/MutableLiveData;", "error", "Landroidx/lifecycle/LiveData;", "getError", "()Landroidx/lifecycle/LiveData;", "listener", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/OnBuyBitcoinClickedListener;", "getListener", "()Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/OnBuyBitcoinClickedListener;", "setListener", "(Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/OnBuyBitcoinClickedListener;)V", "getBuyBitcoinProvider", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/BuyBitcoinProvider;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onBuyBchClicked", "", "onBuyClicked", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: BuyBitcoinButtonsPresenter.kt */
public final class BuyBitcoinButtonsPresenter extends PresenterBase {
    @NotNull
    private final MutableLiveData<Event<String>> _error = new MutableLiveData<>(null);
    private final Context context;
    @NotNull
    private final LiveData<Event<String>> error = this._error;
    /* access modifiers changed from: private */
    public final GetBuyBitcoinProviderInteractor getBuyBitcoinProviderInteractor;
    @Nullable
    private OnBuyBitcoinClickedListener listener;
    private final CoroutineScope viewModelScope;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$1", mo38000f = "BuyBitcoinButtonsPresenter.kt", mo38001i = {}, mo38002l = {30}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$1 */
    /* compiled from: BuyBitcoinButtonsPresenter.kt */
    static final class C10721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f222p$;
        final /* synthetic */ BuyBitcoinButtonsPresenter this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10721 r0 = new C10721(this.this$0, continuation);
            r0.f222p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10721) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f222p$;
                GetBuyBitcoinProviderInteractor access$getGetBuyBitcoinProviderInteractor$p = this.this$0.getBuyBitcoinProviderInteractor;
                this.label = 1;
                if (access$getGetBuyBitcoinProviderInteractor$p.getBuyProvider(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public BuyBitcoinButtonsPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull GetBuyBitcoinProviderInteractor getBuyBitcoinProviderInteractor2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getBuyBitcoinProviderInteractor2, "getBuyBitcoinProviderInteractor");
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.getBuyBitcoinProviderInteractor = getBuyBitcoinProviderInteractor2;
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, Dispatchers.getIO(), null, new C10721(this, null), 2, null);
    }

    @Nullable
    public final OnBuyBitcoinClickedListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable OnBuyBitcoinClickedListener onBuyBitcoinClickedListener) {
        this.listener = onBuyBitcoinClickedListener;
    }

    @NotNull
    public final MutableLiveData<Event<String>> get_error() {
        return this._error;
    }

    @NotNull
    public final LiveData<Event<String>> getError() {
        return this.error;
    }

    public final void onBuyBchClicked() {
        onBuyClicked(Coin.BCH);
    }

    private final void onBuyClicked(Coin coin) {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, Dispatchers.getIO(), null, new BuyBitcoinButtonsPresenter$onBuyClicked$1(this, coin, null), 2, null);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object getBuyBitcoinProvider(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$getBuyBitcoinProvider$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$getBuyBitcoinProvider$1 r0 = (com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$getBuyBitcoinProvider$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$getBuyBitcoinProvider$1 r0 = new com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter$getBuyBitcoinProvider$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r4) goto L_0x0033
            java.lang.Object r1 = r0.L$1
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider r1 = (com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider) r1
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter r0 = (com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x006c
        L_0x0033:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r6)
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.GetBuyBitcoinProviderInteractor r6 = r5.getBuyBitcoinProviderInteractor
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider r6 = r6.getBuyProvider()
            if (r6 == 0) goto L_0x005d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Existing Buy Bitcoin provider: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r0, r1)
            return r6
        L_0x005d:
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.GetBuyBitcoinProviderInteractor r2 = r5.getBuyBitcoinProviderInteractor
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r6 = r2.getBuyProvider(r0)
            if (r6 != r1) goto L_0x006c
            return r1
        L_0x006c:
            com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider r6 = (com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider) r6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Buy Bitcoin provider: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter.getBuyBitcoinProvider(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
