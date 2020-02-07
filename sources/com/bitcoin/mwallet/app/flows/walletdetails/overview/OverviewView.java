package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.HistoryAssetsPagerAdapter;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.transaction.TransactionClickedListener;
import com.google.android.material.tabs.TabLayout;
import java.io.Serializable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/OverviewPresenter;", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionClickedListener;", "()V", "bindActions", "", "bindDataObservers", "bindViews", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onTransactionClicked", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OverviewView.kt */
public final class OverviewView extends ScreenView<OverviewBuilder, OverviewPresenter> implements TransactionClickedListener {
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

    public OverviewView() {
        super(C1018R.layout.fragment_screen_walletdetails_overview, Reflection.getOrCreateKotlinClass(OverviewBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        Serializable serializableExtra = activity.getIntent().getSerializableExtra("wallet_id");
        if (serializableExtra != null) {
            WalletId walletId = (WalletId) serializableExtra;
            ((OverviewPresenter) getPresenter()).initWalletId(walletId);
            bindViews(walletId);
            bindActions();
            bindDataObservers();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.core.models.wallet.WalletId");
    }

    public final void bindViews(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        View view = getView();
        ViewPager viewPager = view != null ? (ViewPager) view.findViewById(C1018R.C1021id.historyAssetsViewPager) : null;
        View view2 = getView();
        TabLayout tabLayout = view2 != null ? (TabLayout) view2.findViewById(C1018R.C1021id.tabLayout) : null;
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        OverviewView$bindViews$1 overviewView$bindViews$1 = new OverviewView$bindViews$1(this, new HistoryAssetsPagerAdapter(childFragmentManager, 0), walletId, viewPager, tabLayout, null);
        BuildersKt__BuildersKt.runBlocking$default(null, overviewView$bindViews$1, 1, null);
    }

    public final void bindDataObservers() {
        ((OverviewPresenter) getPresenter()).getWallet().observe(getViewLifecycleOwner(), new OverviewView$bindDataObservers$1(this));
        ((OverviewPresenter) getPresenter()).getBalanceByWallet().observe(getViewLifecycleOwner(), new OverviewView$bindDataObservers$2(this));
        ((OverviewPresenter) getPresenter()).getBaseBalanceAndWalletTokens().observe(getViewLifecycleOwner(), new OverviewView$bindDataObservers$3(this));
    }

    public final void bindActions() {
        View view = getView();
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.sendButton);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new OverviewView$bindActions$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LinearLayout linearLayout2 = (LinearLayout) view2.findViewById(C1018R.C1021id.receiveButton);
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new OverviewView$bindActions$2(this));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            ImageView imageView = (ImageView) view3.findViewById(C1018R.C1021id.walletSettingsButton);
            if (imageView != null) {
                imageView.setOnClickListener(new OverviewView$bindActions$3(this));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            TextView textView = (TextView) view4.findViewById(C1018R.C1021id.backupButton);
            if (textView != null) {
                textView.setOnClickListener(new OverviewView$bindActions$4(this));
            }
        }
    }

    public void onTransactionClicked(@NotNull TxId txId) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
        ((OverviewPresenter) getPresenter()).toTxDetails(txId.getId());
    }
}
