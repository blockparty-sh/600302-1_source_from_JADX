package com.bitcoin.mwallet.app.components.walletselector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.FullScreenRoundedBottomSheetDialogFragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ&\u0010\u001c\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0018\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0005H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\n\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006'"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorDialogView;", "Lcom/bitcoin/mwallet/core/views/FullScreenRoundedBottomSheetDialogFragment;", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "listener", "defaultCoin", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "restrictive", "", "receivingText", "(Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;Lcom/bitcoin/mwallet/core/entity/WalletType;Lcom/bitcoin/mwallet/core/models/slp/SlpId;ZZ)V", "getDefaultCoin", "()Lcom/bitcoin/mwallet/core/entity/WalletType;", "getListener", "()Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "getReceivingText", "()Z", "setReceivingText", "(Z)V", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "bindObservers", "", "layoutView", "Landroid/view/View;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorPresenter;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onWalletSelected", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "walletType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorDialogView.kt */
public final class WalletSelectorDialogView extends FullScreenRoundedBottomSheetDialogFragment implements WalletSelectorListener {
    private HashMap _$_findViewCache;
    @Nullable
    private final WalletType defaultCoin;
    @NotNull
    private final WalletSelectorListener listener;
    private boolean receivingText;
    private boolean restrictive;
    @Nullable
    private final SlpId tokenId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WalletType.values().length];

        static {
            $EnumSwitchMapping$0[WalletType.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[WalletType.BTC.ordinal()] = 2;
            $EnumSwitchMapping$0[WalletType.SLP.ordinal()] = 3;
        }
    }

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

    @NotNull
    public final WalletSelectorListener getListener() {
        return this.listener;
    }

    public /* synthetic */ WalletSelectorDialogView(WalletSelectorListener walletSelectorListener, WalletType walletType, SlpId slpId, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            walletType = null;
        }
        WalletType walletType2 = walletType;
        if ((i & 4) != 0) {
            slpId = null;
        }
        this(walletSelectorListener, walletType2, slpId, (i & 8) != 0 ? true : z, (i & 16) != 0 ? false : z2);
    }

    @Nullable
    public final WalletType getDefaultCoin() {
        return this.defaultCoin;
    }

    @Nullable
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    public final boolean getReceivingText() {
        return this.receivingText;
    }

    public final void setReceivingText(boolean z) {
        this.receivingText = z;
    }

    public WalletSelectorDialogView(@NotNull WalletSelectorListener walletSelectorListener, @Nullable WalletType walletType, @Nullable SlpId slpId, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(walletSelectorListener, CastExtraArgs.LISTENER);
        this.listener = walletSelectorListener;
        this.defaultCoin = walletType;
        this.tokenId = slpId;
        this.restrictive = z;
        this.receivingText = z2;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = getLayoutInflater().inflate(C1018R.layout.fragment_component_walletselector, null);
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(WalletSelectorBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ectorBuilder::class.java)");
        WalletSelectorBuilder walletSelectorBuilder = (WalletSelectorBuilder) viewModel;
        Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutView");
        bindObservers(inflate, walletSelectorBuilder.getPresenter());
        return inflate;
    }

    public final void bindObservers(@NotNull View view, @NotNull WalletSelectorPresenter walletSelectorPresenter) {
        Intrinsics.checkParameterIsNotNull(view, "layoutView");
        Intrinsics.checkParameterIsNotNull(walletSelectorPresenter, "presenter");
        MutableLiveData selectedCoin = walletSelectorPresenter.getSelectedCoin();
        WalletType walletType = this.defaultCoin;
        if (walletType == null) {
            walletType = WalletType.BCH;
        }
        selectedCoin.setValue(walletType);
        WalletSelectorAdapter walletSelectorAdapter = new WalletSelectorAdapter(this);
        View findViewById = view.findViewById(C1018R.C1021id.walletListView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "layoutView.findViewById<…iew>(R.id.walletListView)");
        ((RecyclerView) findViewById).setAdapter(walletSelectorAdapter);
        walletSelectorPresenter.getSelectedCoin().observe(getViewLifecycleOwner(), new WalletSelectorDialogView$bindObservers$1(this, walletSelectorPresenter));
        walletSelectorPresenter.getWalletSummary().observe(getViewLifecycleOwner(), new WalletSelectorDialogView$bindObservers$2(walletSelectorAdapter));
        walletSelectorPresenter.getShowEmptyLayout().observe(getViewLifecycleOwner(), new WalletSelectorDialogView$bindObservers$3(this, view, walletSelectorPresenter));
        RadioButton radioButton = (RadioButton) view.findViewById(C1018R.C1021id.bitcoinCashFilterButton);
        RadioButton radioButton2 = (RadioButton) view.findViewById(C1018R.C1021id.bitcoinFilterButton);
        RadioButton radioButton3 = (RadioButton) view.findViewById(C1018R.C1021id.slpFilterButton);
        ((TextView) view.findViewById(C1018R.C1021id.addWalletButton)).setOnClickListener(new WalletSelectorDialogView$bindObservers$4(this, walletSelectorPresenter));
        radioButton.setOnCheckedChangeListener(new WalletSelectorDialogView$bindObservers$5(walletSelectorPresenter));
        radioButton2.setOnCheckedChangeListener(new WalletSelectorDialogView$bindObservers$6(walletSelectorPresenter));
        radioButton3.setOnCheckedChangeListener(new WalletSelectorDialogView$bindObservers$7(walletSelectorPresenter));
        if (this.receivingText) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.walletSelectorTitleText);
            if (textView != null) {
                textView.setText(C1018R.string.wallet_selector_select_recieving_wallet);
            }
        }
        WalletType walletType2 = this.defaultCoin;
        String str = "bitcoinCashRadioButton";
        if (walletType2 != null) {
            if (walletType2 != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[walletType2.ordinal()];
                String str2 = "layoutView.findViewById<…R.id.bitcoinFilterButton)";
                if (i == 1) {
                    Intrinsics.checkExpressionValueIsNotNull(radioButton, str);
                    radioButton.setChecked(true);
                    if (!this.restrictive) {
                        View findViewById2 = view.findViewById(C1018R.C1021id.bitcoinFilterButton);
                        Intrinsics.checkExpressionValueIsNotNull(findViewById2, str2);
                        ((RadioButton) findViewById2).setVisibility(8);
                    }
                } else if (i == 2) {
                    Intrinsics.checkExpressionValueIsNotNull(radioButton2, "bitcoinRadioButton");
                    radioButton2.setChecked(true);
                    this.restrictive = true;
                } else if (i == 3) {
                    Intrinsics.checkExpressionValueIsNotNull(radioButton3, "slpRadioButton");
                    radioButton3.setChecked(true);
                    if (!this.restrictive) {
                        View findViewById3 = view.findViewById(C1018R.C1021id.bitcoinFilterButton);
                        Intrinsics.checkExpressionValueIsNotNull(findViewById3, str2);
                        ((RadioButton) findViewById3).setVisibility(8);
                    }
                }
            }
            if (this.restrictive) {
                View findViewById4 = view.findViewById(C1018R.C1021id.coinSelectionGroup);
                Intrinsics.checkExpressionValueIsNotNull(findViewById4, "layoutView.findViewById<…(R.id.coinSelectionGroup)");
                ((RadioGroup) findViewById4).setVisibility(8);
                return;
            }
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(radioButton, str);
        radioButton.setChecked(true);
    }

    public void onWalletSelected(@NotNull WalletId walletId, @NotNull WalletType walletType) {
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intrinsics.checkParameterIsNotNull(walletType, "walletType");
        this.listener.onWalletSelected(walletId, walletType);
        dismiss();
    }
}
