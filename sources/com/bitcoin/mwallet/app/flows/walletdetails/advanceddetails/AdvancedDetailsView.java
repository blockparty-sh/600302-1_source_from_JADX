package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020*J\u0006\u0010,\u001a\u00020*J\u0012\u0010-\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u001b\u0010\u0014\u001a\u00020\u00158BX\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\u001c\u0010 \u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR\u001c\u0010#\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR\u001c\u0010&\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\b\"\u0004\b(\u0010\n¨\u00060"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsPresenter;", "()V", "addressTypeCell", "Landroid/widget/LinearLayout;", "getAddressTypeCell", "()Landroid/widget/LinearLayout;", "setAddressTypeCell", "(Landroid/widget/LinearLayout;)V", "coinTypeCell", "getCoinTypeCell", "setCoinTypeCell", "configurationCell", "getConfigurationCell", "setConfigurationCell", "derivationCell", "getDerivationCell", "setDerivationCell", "navArgs", "Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "networkCell", "getNetworkCell", "setNetworkCell", "pubkeyCell", "getPubkeyCell", "setPubkeyCell", "scanForFundsCell", "getScanForFundsCell", "setScanForFundsCell", "walletIdCell", "getWalletIdCell", "setWalletIdCell", "walletInputsCell", "getWalletInputsCell", "setWalletInputsCell", "bindCells", "", "bindInitialText", "bindObservers", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsView.kt */
public final class AdvancedDetailsView extends ScreenView<AdvancedDetailsBuilder, AdvancedDetailsPresenter> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AdvancedDetailsView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewArgs;"))};
    private HashMap _$_findViewCache;
    @Nullable
    private LinearLayout addressTypeCell;
    @Nullable
    private LinearLayout coinTypeCell;
    @Nullable
    private LinearLayout configurationCell;
    @Nullable
    private LinearLayout derivationCell;
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(AdvancedDetailsViewArgs.class), new AdvancedDetailsView$$special$$inlined$navArgs$1(this));
    @Nullable
    private LinearLayout networkCell;
    @Nullable
    private LinearLayout pubkeyCell;
    @Nullable
    private LinearLayout scanForFundsCell;
    @Nullable
    private LinearLayout walletIdCell;
    @Nullable
    private LinearLayout walletInputsCell;

    private final AdvancedDetailsViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (AdvancedDetailsViewArgs) lazy.getValue();
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

    public AdvancedDetailsView() {
        super(C1018R.layout.fragment_screen_walletdetails_advanceddetails, Reflection.getOrCreateKotlinClass(AdvancedDetailsBuilder.class));
    }

    @Nullable
    public final LinearLayout getCoinTypeCell() {
        return this.coinTypeCell;
    }

    public final void setCoinTypeCell(@Nullable LinearLayout linearLayout) {
        this.coinTypeCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getWalletIdCell() {
        return this.walletIdCell;
    }

    public final void setWalletIdCell(@Nullable LinearLayout linearLayout) {
        this.walletIdCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getConfigurationCell() {
        return this.configurationCell;
    }

    public final void setConfigurationCell(@Nullable LinearLayout linearLayout) {
        this.configurationCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getNetworkCell() {
        return this.networkCell;
    }

    public final void setNetworkCell(@Nullable LinearLayout linearLayout) {
        this.networkCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getAddressTypeCell() {
        return this.addressTypeCell;
    }

    public final void setAddressTypeCell(@Nullable LinearLayout linearLayout) {
        this.addressTypeCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getDerivationCell() {
        return this.derivationCell;
    }

    public final void setDerivationCell(@Nullable LinearLayout linearLayout) {
        this.derivationCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getPubkeyCell() {
        return this.pubkeyCell;
    }

    public final void setPubkeyCell(@Nullable LinearLayout linearLayout) {
        this.pubkeyCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getWalletInputsCell() {
        return this.walletInputsCell;
    }

    public final void setWalletInputsCell(@Nullable LinearLayout linearLayout) {
        this.walletInputsCell = linearLayout;
    }

    @Nullable
    public final LinearLayout getScanForFundsCell() {
        return this.scanForFundsCell;
    }

    public final void setScanForFundsCell(@Nullable LinearLayout linearLayout) {
        this.scanForFundsCell = linearLayout;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ((AdvancedDetailsPresenter) getPresenter()).setWalletId(getNavArgs().getWalletId());
        bindCells();
        bindInitialText();
        bindObservers();
    }

    public final void bindCells() {
        View view = getView();
        LinearLayout linearLayout = null;
        this.coinTypeCell = view != null ? (LinearLayout) view.findViewById(C1018R.C1021id.coinTypeCell) : null;
        View view2 = getView();
        this.walletIdCell = view2 != null ? (LinearLayout) view2.findViewById(C1018R.C1021id.walletIdCell) : null;
        LinearLayout linearLayout2 = this.walletIdCell;
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(new AdvancedDetailsView$bindCells$1(this));
        }
        View view3 = getView();
        this.configurationCell = view3 != null ? (LinearLayout) view3.findViewById(C1018R.C1021id.configurationCell) : null;
        View view4 = getView();
        this.networkCell = view4 != null ? (LinearLayout) view4.findViewById(C1018R.C1021id.networkCell) : null;
        View view5 = getView();
        this.derivationCell = view5 != null ? (LinearLayout) view5.findViewById(C1018R.C1021id.derivationCell) : null;
        View view6 = getView();
        this.pubkeyCell = view6 != null ? (LinearLayout) view6.findViewById(C1018R.C1021id.pubKeyCell) : null;
        LinearLayout linearLayout3 = this.pubkeyCell;
        if (linearLayout3 != null) {
            linearLayout3.setOnClickListener(new AdvancedDetailsView$bindCells$2(this));
        }
        View view7 = getView();
        this.walletInputsCell = view7 != null ? (LinearLayout) view7.findViewById(C1018R.C1021id.walletInputsCell) : null;
        View view8 = getView();
        if (view8 != null) {
            linearLayout = (LinearLayout) view8.findViewById(C1018R.C1021id.scanForFundsContainer);
        }
        this.scanForFundsCell = linearLayout;
    }

    public final void bindInitialText() {
        LinearLayout linearLayout = this.coinTypeCell;
        if (linearLayout != null) {
            TextView textView = (TextView) linearLayout.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView != null) {
                textView.setText(getResources().getString(C1018R.string.settings_coin_type));
            }
        }
        LinearLayout linearLayout2 = this.walletIdCell;
        if (linearLayout2 != null) {
            TextView textView2 = (TextView) linearLayout2.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView2 != null) {
                textView2.setText(getResources().getString(C1018R.string.settings_wallet_id));
            }
        }
        LinearLayout linearLayout3 = this.configurationCell;
        if (linearLayout3 != null) {
            TextView textView3 = (TextView) linearLayout3.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView3 != null) {
                textView3.setText(getResources().getString(C1018R.string.settings_configuration));
            }
        }
        LinearLayout linearLayout4 = this.networkCell;
        if (linearLayout4 != null) {
            TextView textView4 = (TextView) linearLayout4.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView4 != null) {
                textView4.setText(getResources().getString(C1018R.string.settings_network));
            }
        }
        LinearLayout linearLayout5 = this.addressTypeCell;
        if (linearLayout5 != null) {
            TextView textView5 = (TextView) linearLayout5.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView5 != null) {
                textView5.setText(getResources().getString(C1018R.string.settings_address_type));
            }
        }
        LinearLayout linearLayout6 = this.derivationCell;
        if (linearLayout6 != null) {
            TextView textView6 = (TextView) linearLayout6.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView6 != null) {
                textView6.setText(getResources().getString(C1018R.string.settings_derivation_path));
            }
        }
        LinearLayout linearLayout7 = this.pubkeyCell;
        if (linearLayout7 != null) {
            TextView textView7 = (TextView) linearLayout7.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView7 != null) {
                textView7.setText(getResources().getString(C1018R.string.settings_extended_public_keys));
            }
        }
        LinearLayout linearLayout8 = this.walletInputsCell;
        if (linearLayout8 != null) {
            TextView textView8 = (TextView) linearLayout8.findViewById(C1018R.C1021id.advancedWalletTitle);
            if (textView8 != null) {
                textView8.setText(getResources().getString(C1018R.string.settings_total_wallet_inputs));
            }
        }
    }

    public final void bindObservers() {
        ((AdvancedDetailsPresenter) getPresenter()).getWallet().observe(getViewLifecycleOwner(), new AdvancedDetailsView$bindObservers$1(this));
        ((AdvancedDetailsPresenter) getPresenter()).getUTXOs().observe(getViewLifecycleOwner(), new AdvancedDetailsView$bindObservers$2(this));
        View view = getView();
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.scanForFundsLayout);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new AdvancedDetailsView$bindObservers$3(this));
            }
        }
    }
}
