package com.bitcoin.mwallet.app.flows.settings.currencies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.android.SharedPreferenceLiveData;
import com.bitcoin.mwallet.app.android.SharedPreferenceStringLiveData;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import java.util.Currency;
import java.util.List;
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
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0002\u0010\fJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u001e\u0010 \u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020#0\"0\u000e0!J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%J\u000e\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020&R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R&\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesRouter;)V", "allCurrencies", "", "Ljava/util/Currency;", "getAllCurrencies", "()Ljava/util/List;", "setAllCurrencies", "(Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "queriedCurrencies", "Landroidx/lifecycle/MutableLiveData;", "getQueriedCurrencies", "()Landroidx/lifecycle/MutableLiveData;", "setQueriedCurrencies", "(Landroidx/lifecycle/MutableLiveData;)V", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "clearAllTransactionThresholds", "", "getCurrenciesWithInfo", "Landroidx/lifecycle/LiveData;", "Lkotlin/Pair;", "", "getSelectedPreference", "Lcom/bitcoin/mwallet/app/android/SharedPreferenceLiveData;", "", "setQuery", "query", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesPresenter.kt */
public final class CurrenciesPresenter extends ScreenPresenter<CurrenciesRouter> {
    @NotNull
    public List<Currency> allCurrencies;
    @NotNull
    private final Context context;
    /* access modifiers changed from: private */
    public final GetCurrentExchangeRateInteractor exchangeRateInteractor;
    /* access modifiers changed from: private */
    public final GetWalletInteractor getWalletInteractor;
    @NotNull
    private MutableLiveData<List<Currency>> queriedCurrencies = new MutableLiveData<>();
    @NotNull
    private final CoroutineScope viewModelScope;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.settings.currencies.CurrenciesPresenter$1", mo38000f = "CurrenciesPresenter.kt", mo38001i = {}, mo38002l = {36}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.settings.currencies.CurrenciesPresenter$1 */
    /* compiled from: CurrenciesPresenter.kt */
    static final class C11061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f307p$;
        final /* synthetic */ CurrenciesPresenter this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C11061 r0 = new C11061(this.this$0, continuation);
            r0.f307p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C11061) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            CurrenciesPresenter currenciesPresenter;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f307p$;
                CurrenciesPresenter currenciesPresenter2 = this.this$0;
                GetCurrentExchangeRateInteractor access$getExchangeRateInteractor$p = currenciesPresenter2.exchangeRateInteractor;
                this.L$0 = currenciesPresenter2;
                this.label = 1;
                Object availableCurrencies = access$getExchangeRateInteractor$p.getAvailableCurrencies(this);
                if (availableCurrencies == coroutine_suspended) {
                    return coroutine_suspended;
                }
                currenciesPresenter = currenciesPresenter2;
                obj = availableCurrencies;
            } else if (i == 1) {
                currenciesPresenter = (CurrenciesPresenter) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            currenciesPresenter.setAllCurrencies((List) obj);
            return Unit.INSTANCE;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    public CurrenciesPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor, @NotNull GetWalletInteractor getWalletInteractor2, @NotNull CurrenciesRouter currenciesRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(getCurrentExchangeRateInteractor, "exchangeRateInteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor2, "getWalletInteractor");
        Intrinsics.checkParameterIsNotNull(currenciesRouter, "router");
        super(context2, currenciesRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.exchangeRateInteractor = getCurrentExchangeRateInteractor;
        this.getWalletInteractor = getWalletInteractor2;
        BuildersKt__BuildersKt.runBlocking$default(null, new C11061(this, null), 1, null);
    }

    @NotNull
    public final MutableLiveData<List<Currency>> getQueriedCurrencies() {
        return this.queriedCurrencies;
    }

    public final void setQueriedCurrencies(@NotNull MutableLiveData<List<Currency>> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.queriedCurrencies = mutableLiveData;
    }

    @NotNull
    public final List<Currency> getAllCurrencies() {
        List<Currency> list = this.allCurrencies;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allCurrencies");
        }
        return list;
    }

    public final void setAllCurrencies(@NotNull List<Currency> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.allCurrencies = list;
    }

    @NotNull
    public final LiveData<List<Pair<Currency, Boolean>>> getCurrenciesWithInfo() {
        return LiveDataKt.combineLatest(this.queriedCurrencies, getSelectedPreference(), CurrenciesPresenter$getCurrenciesWithInfo$1.INSTANCE);
    }

    @NotNull
    public final SharedPreferenceLiveData<String> getSelectedPreference() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "sharedPreferences");
        return new SharedPreferenceStringLiveData<>(defaultSharedPreferences, SharedPreference.FIAT_CURRENCY);
    }

    public final void clearAllTransactionThresholds() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CurrenciesPresenter$clearAllTransactionThresholds$1(this, null), 3, null);
    }

    /* JADX INFO: used method not loaded: kotlin.text.StringsKt__StringsKt.contains$default(java.lang.CharSequence, java.lang.CharSequence, boolean, int, java.lang.Object):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009d, code lost:
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, r8, false, 2, (java.lang.Object) null) != false) goto L_0x00ac;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setQuery(@org.jetbrains.annotations.NotNull java.lang.String r14) {
        /*
            r13 = this;
            java.lang.String r0 = "query"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            java.lang.String r14 = r14.toLowerCase()
            java.lang.String r0 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r0)
            java.lang.String r1 = ""
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r14, r1)
            java.lang.String r2 = "allCurrencies"
            if (r1 == 0) goto L_0x0026
            androidx.lifecycle.MutableLiveData<java.util.List<java.util.Currency>> r14 = r13.queriedCurrencies
            java.util.List<java.util.Currency> r0 = r13.allCurrencies
            if (r0 != 0) goto L_0x0021
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x0021:
            r14.setValue(r0)
            goto L_0x00be
        L_0x0026:
            androidx.lifecycle.MutableLiveData<java.util.List<java.util.Currency>> r1 = r13.queriedCurrencies
            java.util.List<java.util.Currency> r3 = r13.allCurrencies
            if (r3 != 0) goto L_0x002f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x002f:
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r3 = r3.iterator()
        L_0x003c:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x00b9
            java.lang.Object r4 = r3.next()
            r5 = r4
            java.util.Currency r5 = (java.util.Currency) r5
            java.lang.String r6 = r5.getCurrencyCode()
            java.lang.String r7 = "currency.currencyCode"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            java.lang.String r7 = "null cannot be cast to non-null type java.lang.String"
            if (r6 == 0) goto L_0x00b3
            java.lang.String r6 = r6.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r8 = r14
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9 = 0
            r10 = 2
            r11 = 0
            boolean r6 = kotlin.text.StringsKt.contains$default(r6, r8, r11, r10, r9)
            if (r6 != 0) goto L_0x00ac
            java.lang.String r6 = r5.getDisplayName()
            java.lang.String r12 = "currency.displayName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r12)
            if (r6 == 0) goto L_0x00a6
            java.lang.String r6 = r6.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r6 = kotlin.text.StringsKt.contains$default(r6, r8, r11, r10, r9)
            if (r6 != 0) goto L_0x00ac
            java.lang.String r5 = r5.getSymbol()
            java.lang.String r6 = "currency.symbol"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            if (r5 == 0) goto L_0x00a0
            java.lang.String r5 = r5.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            boolean r5 = kotlin.text.StringsKt.contains$default(r5, r8, r11, r10, r9)
            if (r5 == 0) goto L_0x00ad
            goto L_0x00ac
        L_0x00a0:
            kotlin.TypeCastException r14 = new kotlin.TypeCastException
            r14.<init>(r7)
            throw r14
        L_0x00a6:
            kotlin.TypeCastException r14 = new kotlin.TypeCastException
            r14.<init>(r7)
            throw r14
        L_0x00ac:
            r11 = 1
        L_0x00ad:
            if (r11 == 0) goto L_0x003c
            r2.add(r4)
            goto L_0x003c
        L_0x00b3:
            kotlin.TypeCastException r14 = new kotlin.TypeCastException
            r14.<init>(r7)
            throw r14
        L_0x00b9:
            java.util.List r2 = (java.util.List) r2
            r1.setValue(r2)
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.settings.currencies.CurrenciesPresenter.setQuery(java.lang.String):void");
    }
}
