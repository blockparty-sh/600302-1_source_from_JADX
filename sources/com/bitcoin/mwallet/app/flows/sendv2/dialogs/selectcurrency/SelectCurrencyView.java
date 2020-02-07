package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.views.FullScreenRoundedBottomSheetDialogFragment;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Currency;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J&\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0002X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyView;", "Lcom/bitcoin/mwallet/core/views/FullScreenRoundedBottomSheetDialogFragment;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "onCurrencySelectedListener", "(Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;)V", "listener", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onCurrencySelected", "", "currency", "Ljava/util/Currency;", "onViewCreated", "view", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectCurrencyView.kt */
public final class SelectCurrencyView extends FullScreenRoundedBottomSheetDialogFragment implements OnCurrencySelectedListener {
    private HashMap _$_findViewCache;
    private OnCurrencySelectedListener listener;

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

    public SelectCurrencyView(@NotNull OnCurrencySelectedListener onCurrencySelectedListener) {
        Intrinsics.checkParameterIsNotNull(onCurrencySelectedListener, "onCurrencySelectedListener");
        this.listener = onCurrencySelectedListener;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return getLayoutInflater().inflate(C1018R.layout.fragment_screen_sendv2_selectcurrency, null);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(SelectCurrencyBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…rencyBuilder::class.java)");
        SelectCurrencyPresenter presenter = ((SelectCurrencyBuilder) viewModel).getPresenter();
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = new SelectCurrencyAdapter(this);
        ((SelectCurrencyAdapter) objectRef.element).submitList(presenter.getCurrencies());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(C1018R.C1021id.currencyListView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "view?.findViewById<Recyc…w>(R.id.currencyListView)");
        recyclerView.setAdapter((SelectCurrencyAdapter) objectRef.element);
        SearchView searchView = (SearchView) view.findViewById(C1018R.C1021id.keywordSearchView);
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SelectCurrencyView$onViewCreated$1(objectRef));
        }
    }

    public void onCurrencySelected(@NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
        this.listener.onCurrencySelected(currency);
        dismiss();
    }
}
