package com.bitcoin.mwallet.app.components.globalbalance.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.globalbalance.builder.GlobalBalanceBuilder;
import com.bitcoin.mwallet.app.components.globalbalance.presenter.GlobalBalancePresenter;
import com.bitcoin.mwallet.app.viper.ComponentView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/globalbalance/view/GlobalBalanceView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/globalbalance/builder/GlobalBalanceBuilder;", "Lcom/bitcoin/mwallet/app/components/globalbalance/presenter/GlobalBalancePresenter;", "()V", "bindDataObservers", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GlobalBalanceView.kt */
public final class GlobalBalanceView extends ComponentView<GlobalBalanceBuilder, GlobalBalancePresenter> {
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

    public static final /* synthetic */ GlobalBalancePresenter access$getPresenter$p(GlobalBalanceView globalBalanceView) {
        return (GlobalBalancePresenter) globalBalanceView.getPresenter();
    }

    public GlobalBalanceView() {
        super(C1018R.layout.fragment_component_globalbalance, Reflection.getOrCreateKotlinClass(GlobalBalanceBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        bindDataObservers();
        ((Button) _$_findCachedViewById(C1018R.C1021id.buyButton)).setOnClickListener(new GlobalBalanceView$onActivityCreated$1(this));
    }

    private final void bindDataObservers() {
        ((GlobalBalancePresenter) getPresenter()).getBalanceFromWallets().observe(getViewLifecycleOwner(), new GlobalBalanceView$bindDataObservers$1(this));
    }
}