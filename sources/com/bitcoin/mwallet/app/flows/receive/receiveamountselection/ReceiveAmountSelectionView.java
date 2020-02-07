package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType;
import com.bitcoin.mwallet.app.components.customheader.CustomHeaderListener;
import com.bitcoin.mwallet.app.components.customheader.CustomHeaderView;
import com.bitcoin.mwallet.app.components.pinpad.OnPinPadItemClickedListener;
import com.bitcoin.mwallet.app.components.pinpad.pinpadView;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.app.viper.ScreenView.Companion;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u000eH\u0016R\u001b\u0010\u0007\u001a\u00020\b8BX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionBuilder;", "Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionPresenter;", "Lcom/bitcoin/mwallet/app/components/pinpad/OnPinPadItemClickedListener;", "Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderListener;", "()V", "navArgs", "Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "bindButtonActions", "", "bindDataObservers", "decimalTapped", "deleteTapped", "digitTapped", "digit", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onClosePressed", "onLeftButtonPressed", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionView.kt */
public final class ReceiveAmountSelectionView extends ScreenView<ReceiveAmountSelectionBuilder, ReceiveAmountSelectionPresenter> implements OnPinPadItemClickedListener, CustomHeaderListener {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReceiveAmountSelectionView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionViewArgs;"))};
    private HashMap _$_findViewCache;
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(ReceiveAmountSelectionViewArgs.class), new ReceiveAmountSelectionView$$special$$inlined$navArgs$1(this));

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EntryType.values().length];

        static {
            $EnumSwitchMapping$0[EntryType.TOKEN.ordinal()] = 1;
        }
    }

    private final ReceiveAmountSelectionViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ReceiveAmountSelectionViewArgs) lazy.getValue();
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

    public ReceiveAmountSelectionView() {
        super(C1018R.layout.fragment_component_amountselection, Reflection.getOrCreateKotlinClass(ReceiveAmountSelectionBuilder.class));
    }

    public void onLeftButtonPressed() {
        ((ReceiveAmountSelectionPresenter) getPresenter()).onBackPressed();
    }

    public void onClosePressed() {
        ReceiveAmountSelectionPresenter receiveAmountSelectionPresenter = (ReceiveAmountSelectionPresenter) getPresenter();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            receiveAmountSelectionPresenter.onClosePressed(activity);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        Object obj;
        Object obj2;
        super.onActivityCreated(bundle);
        Companion companion = ScreenView.Companion;
        Fragment fragment = this;
        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
        String str = "fragment.childFragmentManager";
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, str);
        List fragments = childFragmentManager.getFragments();
        String str2 = "fragment.childFragmentManager.fragments";
        Intrinsics.checkExpressionValueIsNotNull(fragments, str2);
        Iterator it = fragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Fragment) obj) instanceof pinpadView) {
                break;
            }
        }
        if (!(obj instanceof pinpadView)) {
            obj = null;
        }
        pinpadView pinpadview = (pinpadView) obj;
        ((ReceiveAmountSelectionPresenter) getPresenter()).init(getNavArgs().getCoin());
        if (pinpadview != null) {
            pinpadview.setPinpadListener(this);
        }
        bindButtonActions();
        bindDataObservers();
        Companion companion2 = ScreenView.Companion;
        FragmentManager childFragmentManager2 = fragment.getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager2, str);
        List fragments2 = childFragmentManager2.getFragments();
        Intrinsics.checkExpressionValueIsNotNull(fragments2, str2);
        Iterator it2 = fragments2.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it2.next();
            if (((Fragment) obj2) instanceof CustomHeaderView) {
                break;
            }
        }
        if (!(obj2 instanceof CustomHeaderView)) {
            obj2 = null;
        }
        CustomHeaderView customHeaderView = (CustomHeaderView) obj2;
        if (customHeaderView != null) {
            String string = getResources().getString(C1018R.string.select_amount_header);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.select_amount_header)");
            customHeaderView.setHeaderText(string);
            customHeaderView.setCloseButtonVisibiity(4);
        }
    }

    private final void bindButtonActions() {
        View view = getView();
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.walletSelectContainer);
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            TextView textView = (TextView) view2.findViewById(C1018R.C1021id.sendAllButton);
            if (textView != null) {
                textView.setVisibility(4);
            }
        }
        View view3 = getView();
        if (view3 != null) {
            Button button = (Button) view3.findViewById(C1018R.C1021id.continueButton);
            if (button != null) {
                button.setOnClickListener(new ReceiveAmountSelectionView$bindButtonActions$1(this));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            TextView textView2 = (TextView) view4.findViewById(C1018R.C1021id.currencySelector);
            if (textView2 != null) {
                textView2.setOnClickListener(new ReceiveAmountSelectionView$bindButtonActions$2(this));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            ImageButton imageButton = (ImageButton) view5.findViewById(C1018R.C1021id.swapFiatButton);
            if (imageButton != null) {
                imageButton.setOnClickListener(new ReceiveAmountSelectionView$bindButtonActions$3(this));
            }
        }
    }

    private final void bindDataObservers() {
        ((ReceiveAmountSelectionPresenter) getPresenter()).getPrimaryAmount().observe(getViewLifecycleOwner(), new ReceiveAmountSelectionView$bindDataObservers$1(this));
        ((ReceiveAmountSelectionPresenter) getPresenter()).getSecondaryDisplayAmount().observe(getViewLifecycleOwner(), new ReceiveAmountSelectionView$bindDataObservers$2(this));
        ((ReceiveAmountSelectionPresenter) getPresenter()).getPrimaryEntryType().observe(getViewLifecycleOwner(), new ReceiveAmountSelectionView$bindDataObservers$3(this));
        ((ReceiveAmountSelectionPresenter) getPresenter()).getDisplayCurrency().observe(getViewLifecycleOwner(), new ReceiveAmountSelectionView$bindDataObservers$4(this));
    }

    public void digitTapped(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "digit");
        ((ReceiveAmountSelectionPresenter) getPresenter()).digitTapped(str);
    }

    public void decimalTapped() {
        ((ReceiveAmountSelectionPresenter) getPresenter()).decimalTapped();
    }

    public void deleteTapped() {
        ((ReceiveAmountSelectionPresenter) getPresenter()).deleteTapped();
    }
}
