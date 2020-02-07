package com.bitcoin.mwallet.app.components.walletmanagement.addwallet.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.builder.ImportWalletBuilder;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.views.RoundedBottomSheetDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/addwallet/view/AddWalletView;", "Lcom/bitcoin/mwallet/core/views/RoundedBottomSheetDialogFragment;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddWalletView.kt */
public final class AddWalletView extends RoundedBottomSheetDialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    private final Coin coin;

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

    public AddWalletView(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        this.coin = coin2;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(ImportWalletBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…alletBuilder::class.java)");
        ((ImportWalletBuilder) viewModel).getPresenter();
        View inflate = getLayoutInflater().inflate(C1018R.layout.fragment_component_addwallet, null);
        if (inflate != null) {
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C1018R.C1021id.createNewWalletButton);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new AddWalletView$onCreateView$1(this));
            }
        }
        if (inflate != null) {
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(C1018R.C1021id.importWalletButton);
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new AddWalletView$onCreateView$2(this));
            }
        }
        return inflate;
    }
}
