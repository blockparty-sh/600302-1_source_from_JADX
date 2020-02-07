package com.bitcoin.mwallet.app.flows.home.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinButtonsView;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.OnBuyBitcoinClickedListener;
import com.bitcoin.mwallet.app.components.receivesendbuttons.view.ReceiveSendButtonsView.OnReceiveSendButtonsListener;
import com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerListener;
import com.bitcoin.mwallet.app.components.walletselector.WalletSelectorListener;
import com.bitcoin.mwallet.app.components.warningdialog.WarningView;
import com.bitcoin.mwallet.app.viper.PresenterWithFinishOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.app.viper.ScreenView.Companion;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.transaction.TransactionClickedListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\bB\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0016J\b\u0010\u0017\u001a\u00020\u000bH\u0016J\b\u0010\u0018\u001a\u00020\u000bH\u0016J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016¨\u0006!"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/home/home/HomeView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/home/home/HomeBuilder;", "Lcom/bitcoin/mwallet/app/flows/home/home/HomePresenter;", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "Lcom/bitcoin/mwallet/app/components/receivesendbuttons/view/ReceiveSendButtonsView$OnReceiveSendButtonsListener;", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionClickedListener;", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/OnBuyBitcoinClickedListener;", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;", "()V", "bindActions", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAddWallet", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "onBuyBitcoinClicked", "provider", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/BuyBitcoinProvider;", "onReceive", "onResume", "onSend", "onStart", "onTransactionClicked", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "onWalletSelected", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: HomeView.kt */
public final class HomeView extends ScreenView<HomeBuilder, HomePresenter> implements WalletSelectorListener, OnReceiveSendButtonsListener, TransactionClickedListener, OnBuyBitcoinClickedListener, WalletAssetsRecyclerListener {
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

    public HomeView() {
        super(C1018R.layout.fragment_screen_home_home, Reflection.getOrCreateKotlinClass(HomeBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        Object obj;
        super.onActivityCreated(bundle);
        setUpFinishOnBackHandler((PresenterWithFinishOnBackHandler) getPresenter());
        Companion companion = ScreenView.Companion;
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "fragment.childFragmentManager");
        List fragments = childFragmentManager.getFragments();
        Intrinsics.checkExpressionValueIsNotNull(fragments, "fragment.childFragmentManager.fragments");
        Iterator it = fragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Fragment) obj) instanceof BuyBitcoinButtonsView) {
                break;
            }
        }
        if (!(obj instanceof BuyBitcoinButtonsView)) {
            obj = null;
        }
        BuyBitcoinButtonsView buyBitcoinButtonsView = (BuyBitcoinButtonsView) obj;
        if (buyBitcoinButtonsView != null) {
            buyBitcoinButtonsView.setOnBuyBitcoinButtonClickedListener(this);
        }
        bindActions();
    }

    public void onResume() {
        super.onResume();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String str = "slp_warning_already_run";
        if (!defaultSharedPreferences.getBoolean(str, false)) {
            Editor edit = defaultSharedPreferences.edit();
            Boolean bool = Boolean.TRUE;
            Intrinsics.checkExpressionValueIsNotNull(bool, "java.lang.Boolean.TRUE");
            edit.putBoolean(str, bool.booleanValue());
            edit.apply();
            new WarningView(C1018R.string.slp_assets_warning_title, C1018R.string.slp_assets_warning_description, "https://support.bitcoin.com/en/articles/3565510-how-can-i-avoid-accidentally-losing-or-burning-slp-tokens").show(getChildFragmentManager(), "slp_warning");
        }
    }

    public void onStart() {
        super.onStart();
        try {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.reportFullyDrawn();
            }
        } catch (SecurityException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to report fully drawn. ");
            sb.append(e);
            Timber.m429e(sb.toString(), new Object[0]);
        }
    }

    public void onTransactionClicked(@NotNull TxId txId) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
        ((HomePresenter) getPresenter()).onTransactionClicked(txId);
    }

    public void onReceive() {
        ((HomePresenter) getPresenter()).onReceive();
    }

    public void onSend() {
        ((HomePresenter) getPresenter()).onSend();
    }

    public void onBuyBitcoinClicked(@NotNull BuyBitcoinProvider buyBitcoinProvider, @NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(buyBitcoinProvider, "provider");
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        ((HomePresenter) getPresenter()).addBuyAnalytics();
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://buy.bitcoin.com/wallet/")));
    }

    public void onWalletSelected(@NotNull WalletId walletId, @NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        HomePresenter homePresenter = (HomePresenter) getPresenter();
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "this.requireContext()");
        homePresenter.onWalletSelected(requireContext, walletId);
    }

    public void onAddWallet(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        HomePresenter homePresenter = (HomePresenter) getPresenter();
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        homePresenter.toAddWallet(coin, childFragmentManager);
    }

    public final void bindActions() {
        View view = getView();
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.addWalletContainer);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new HomeView$bindActions$1(this));
            }
        }
    }
}
