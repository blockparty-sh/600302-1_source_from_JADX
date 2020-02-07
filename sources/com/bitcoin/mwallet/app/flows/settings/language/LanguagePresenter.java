package com.bitcoin.mwallet.app.flows.settings.language;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.android.SharedPreferenceLiveData;
import com.bitcoin.mwallet.app.android.SharedPreferenceStringLiveData;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.preferences.Language;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0002\u0010\nJ\u001e\u0010\u0019\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b0\u001aJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fJ\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020 R\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX.¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006$"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/language/LanguagePresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "interactor", "Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageInteractor;Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageRouter;)V", "allLanguages", "", "Lcom/bitcoin/mwallet/core/preferences/Language;", "getAllLanguages", "()[Lcom/bitcoin/mwallet/core/preferences/Language;", "setAllLanguages", "([Lcom/bitcoin/mwallet/core/preferences/Language;)V", "[Lcom/bitcoin/mwallet/core/preferences/Language;", "queriedLanguages", "Landroidx/lifecycle/MutableLiveData;", "getQueriedLanguages", "()Landroidx/lifecycle/MutableLiveData;", "setQueriedLanguages", "(Landroidx/lifecycle/MutableLiveData;)V", "getLanguageWithInfo", "Landroidx/lifecycle/LiveData;", "", "Lkotlin/Pair;", "", "getSelectedPreference", "Lcom/bitcoin/mwallet/app/android/SharedPreferenceLiveData;", "", "setQuery", "", "query", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LanguagePresenter.kt */
public final class LanguagePresenter extends ScreenPresenter<LanguageRouter> {
    @NotNull
    public Language[] allLanguages;
    private final Context context;
    /* access modifiers changed from: private */
    public final LanguageInteractor interactor;
    @NotNull
    private MutableLiveData<Language[]> queriedLanguages = new MutableLiveData<>();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.settings.language.LanguagePresenter$1", mo38000f = "LanguagePresenter.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.settings.language.LanguagePresenter$1 */
    /* compiled from: LanguagePresenter.kt */
    static final class C11071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f311p$;
        final /* synthetic */ LanguagePresenter this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C11071 r0 = new C11071(this.this$0, continuation);
            r0.f311p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C11071) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f311p$;
                LanguagePresenter languagePresenter = this.this$0;
                languagePresenter.setAllLanguages(languagePresenter.interactor.getAvailableLanguages());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public LanguagePresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull LanguageInteractor languageInteractor, @NotNull LanguageRouter languageRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(languageInteractor, "interactor");
        Intrinsics.checkParameterIsNotNull(languageRouter, "router");
        super(context2, languageRouter);
        this.context = context2;
        this.interactor = languageInteractor;
        BuildersKt__BuildersKt.runBlocking$default(null, new C11071(this, null), 1, null);
    }

    @NotNull
    public final MutableLiveData<Language[]> getQueriedLanguages() {
        return this.queriedLanguages;
    }

    public final void setQueriedLanguages(@NotNull MutableLiveData<Language[]> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.queriedLanguages = mutableLiveData;
    }

    @NotNull
    public final Language[] getAllLanguages() {
        Language[] languageArr = this.allLanguages;
        if (languageArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allLanguages");
        }
        return languageArr;
    }

    public final void setAllLanguages(@NotNull Language[] languageArr) {
        Intrinsics.checkParameterIsNotNull(languageArr, "<set-?>");
        this.allLanguages = languageArr;
    }

    @NotNull
    public final LiveData<List<Pair<Language, Boolean>>> getLanguageWithInfo() {
        return LiveDataKt.combineLatest(this.queriedLanguages, getSelectedPreference(), LanguagePresenter$getLanguageWithInfo$1.INSTANCE);
    }

    @NotNull
    public final SharedPreferenceLiveData<String> getSelectedPreference() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "sharedPreferences");
        return new SharedPreferenceStringLiveData<>(defaultSharedPreferences, SharedPreference.LANGUAGE);
    }

    public final void setQuery(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, SearchIntents.EXTRA_QUERY);
        String lowerCase = str.toLowerCase();
        String str2 = "(this as java.lang.String).toLowerCase()";
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, str2);
        String str3 = "allLanguages";
        if (Intrinsics.areEqual((Object) lowerCase, (Object) "")) {
            MutableLiveData<Language[]> mutableLiveData = this.queriedLanguages;
            Language[] languageArr = this.allLanguages;
            if (languageArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str3);
            }
            mutableLiveData.setValue(languageArr);
            return;
        }
        MutableLiveData<Language[]> mutableLiveData2 = this.queriedLanguages;
        Language[] languageArr2 = this.allLanguages;
        if (languageArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str3);
        }
        Collection arrayList = new ArrayList();
        int length = languageArr2.length;
        int i = 0;
        while (i < length) {
            Language language = languageArr2[i];
            String name = language.name();
            if (name != null) {
                String lowerCase2 = name.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase2, str2);
                if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                    arrayList.add(language);
                }
                i++;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        Object[] array = ((List) arrayList).toArray(new Language[0]);
        if (array != null) {
            mutableLiveData2.setValue(array);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
