package com.bitcoin.mwallet.app.flows.walletdetails.scanfortransactions;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails.AdvancedDetailsViewArgs;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0002J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/scanfortransactions/ScanForTransactionsPresenter;", "()V", "navArgs", "Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "disbleAction", "", "interval", "", "enableAction", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScanForTransactionsView.kt */
public final class ScanForTransactionsView extends ScreenView<ScanForTransactionsBuilder, ScanForTransactionsPresenter> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ScanForTransactionsView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/walletdetails/advanceddetails/AdvancedDetailsViewArgs;"))};
    private HashMap _$_findViewCache;
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(AdvancedDetailsViewArgs.class), new ScanForTransactionsView$$special$$inlined$navArgs$1(this));

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

    public ScanForTransactionsView() {
        super(C1018R.layout.fragment_screen_walletdetails_scanfortransactions, Reflection.getOrCreateKotlinClass(ScanForTransactionsBuilder.class));
    }

    /* access modifiers changed from: private */
    public final void disbleAction(long j) {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.buttonTextView);
            if (textView != null) {
                textView.setEnabled(false);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.buttonTextView);
            if (textView2 != null) {
                textView2.setText(getResources().getString(C1018R.string.settings_scan_for_transactions_scanning));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            TextView textView3 = (TextView) view3.findViewById(C1018R.C1021id.buttonTextView);
            if (textView3 != null) {
                textView3.setTextColor(getResources().getColor(C1018R.C1019color.gray));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            ImageView imageView = (ImageView) view4.findViewById(C1018R.C1021id.progressIcon);
            if (imageView != null) {
                imageView.setColorFilter(getResources().getColor(C1018R.C1019color.gray));
            }
        }
        ScanForTransactionsView$disbleAction$timer$1 scanForTransactionsView$disbleAction$timer$1 = new ScanForTransactionsView$disbleAction$timer$1(this, j, j, 1000);
        scanForTransactionsView$disbleAction$timer$1.start();
    }

    /* access modifiers changed from: private */
    public final void enableAction() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.buttonTextView);
            if (textView != null) {
                textView.setEnabled(true);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.buttonTextView);
            if (textView2 != null) {
                textView2.setText(getResources().getString(C1018R.string.settings_scan_for_transactions));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            TextView textView3 = (TextView) view3.findViewById(C1018R.C1021id.buttonTextView);
            if (textView3 != null) {
                textView3.setTextColor(getResources().getColor(C1018R.C1019color.bchGreen));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            ImageView imageView = (ImageView) view4.findViewById(C1018R.C1021id.progressIcon);
            if (imageView != null) {
                imageView.setColorFilter(getResources().getColor(C1018R.C1019color.bchGreen));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            LinearLayout linearLayout = (LinearLayout) view5.findViewById(C1018R.C1021id.scanForFundsLayout);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new ScanForTransactionsView$enableAction$1(this));
            }
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ((ScanForTransactionsPresenter) getPresenter()).setWalletId(getNavArgs().getWalletId());
        ((ScanForTransactionsPresenter) getPresenter()).checkProgress().observe(this, new ScanForTransactionsView$onActivityCreated$1(this));
    }
}
