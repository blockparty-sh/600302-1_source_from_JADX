package com.bitcoin.mwallet.app.components.buybitcoinbuttons.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsBuilder;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.BuyBitcoinButtonsPresenter;
import com.bitcoin.mwallet.app.viper.ComponentView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0002J\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/BuyBitcoinButtonsView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/BuyBitcoinButtonsBuilder;", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/BuyBitcoinButtonsPresenter;", "()V", "listener", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/OnBuyBitcoinClickedListener;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "setButtonActions", "setOnBuyBitcoinButtonClickedListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: BuyBitcoinButtonsView.kt */
public final class BuyBitcoinButtonsView extends ComponentView<BuyBitcoinButtonsBuilder, BuyBitcoinButtonsPresenter> {
    private HashMap _$_findViewCache;
    private OnBuyBitcoinClickedListener listener;

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

    public static final /* synthetic */ BuyBitcoinButtonsPresenter access$getPresenter$p(BuyBitcoinButtonsView buyBitcoinButtonsView) {
        return (BuyBitcoinButtonsPresenter) buyBitcoinButtonsView.getPresenter();
    }

    public BuyBitcoinButtonsView() {
        super(C1018R.layout.fragment_component_buybitcoinbuttons, Reflection.getOrCreateKotlinClass(BuyBitcoinButtonsBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ((BuyBitcoinButtonsPresenter) getPresenter()).setListener(this.listener);
        setButtonActions();
    }

    public final void setOnBuyBitcoinButtonClickedListener(@NotNull OnBuyBitcoinClickedListener onBuyBitcoinClickedListener) {
        Intrinsics.checkParameterIsNotNull(onBuyBitcoinClickedListener, CastExtraArgs.LISTENER);
        this.listener = onBuyBitcoinClickedListener;
    }

    private final void setButtonActions() {
        View view = getView();
        if (view != null) {
            Button button = (Button) view.findViewById(C1018R.C1021id.buyBchButton);
            if (button != null) {
                button.setOnClickListener(new BuyBitcoinButtonsView$setButtonActions$1(this));
            }
        }
    }
}
