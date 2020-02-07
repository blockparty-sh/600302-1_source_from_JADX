package com.bitcoin.mwallet.app.flows.settings.currencies;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesBuilder;", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "()V", "bindActions", "", "bindObservers", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesView.kt */
public final class CurrenciesView extends ScreenView<CurrenciesBuilder, CurrenciesPresenter> {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public CurrenciesView() {
        super(C1018R.layout.fragment_screen_settings_currencies, Reflection.getOrCreateKotlinClass(CurrenciesBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        bindObservers();
        bindActions();
    }

    public final void bindActions() {
        View view = getView();
        if (view != null) {
            SearchView searchView = (SearchView) view.findViewById(C1018R.C1021id.currencySearchView);
            if (searchView != null) {
                searchView.setOnQueryTextListener(new CurrenciesView$bindActions$1(this));
            }
        }
    }

    public final void bindObservers() {
        CurrencyPreferenceAdapter currencyPreferenceAdapter = new CurrencyPreferenceAdapter(getFragmentManager(), (CurrenciesPresenter) getPresenter());
        View view = getView();
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(C1018R.C1021id.currencyListView);
            if (recyclerView != null) {
                recyclerView.setAdapter(currencyPreferenceAdapter);
            }
        }
        ((CurrenciesPresenter) getPresenter()).setQuery("");
        ((CurrenciesPresenter) getPresenter()).getCurrenciesWithInfo().observe(getViewLifecycleOwner(), new CurrenciesView$bindObservers$1(currencyPreferenceAdapter));
    }
}
