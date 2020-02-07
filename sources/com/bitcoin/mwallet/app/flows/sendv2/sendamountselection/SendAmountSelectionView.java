package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
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
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
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
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0006\u0010\u0016\u001a\u00020\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\b\u0010 \u001a\u00020\u0014H\u0016J\b\u0010!\u001a\u00020\u0014H\u0016J\u0010\u0010\"\u001a\u00020\u00142\b\u0010#\u001a\u0004\u0018\u00010$R\u001b\u0010\u0007\u001a\u00020\b8BX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006%"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionBuilder;", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionPresenter;", "Lcom/bitcoin/mwallet/app/components/pinpad/OnPinPadItemClickedListener;", "Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderListener;", "()V", "navArgs", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "getWalletType", "()Lcom/bitcoin/mwallet/core/entity/WalletType;", "setWalletType", "(Lcom/bitcoin/mwallet/core/entity/WalletType;)V", "bindButtonActions", "", "bindDataObservers", "bindInitialArgs", "decimalTapped", "deleteTapped", "digitTapped", "digit", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onClosePressed", "onLeftButtonPressed", "onStart", "setPreferredWallet", "preferredWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
public final class SendAmountSelectionView extends ScreenView<SendAmountSelectionBuilder, SendAmountSelectionPresenter> implements OnPinPadItemClickedListener, CustomHeaderListener {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SendAmountSelectionView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendAmountSelectionViewArgs;"))};
    private HashMap _$_findViewCache;
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SendAmountSelectionViewArgs.class), new SendAmountSelectionView$$special$$inlined$navArgs$1(this));
    @NotNull
    public WalletType walletType;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[EntryType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[EntryType.values().length];

        static {
            $EnumSwitchMapping$0[EntryType.TOKEN.ordinal()] = 1;
            $EnumSwitchMapping$1[EntryType.TOKEN.ordinal()] = 1;
        }
    }

    private final SendAmountSelectionViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (SendAmountSelectionViewArgs) lazy.getValue();
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

    public SendAmountSelectionView() {
        super(C1018R.layout.fragment_component_amountselection, Reflection.getOrCreateKotlinClass(SendAmountSelectionBuilder.class));
    }

    public void onLeftButtonPressed() {
        ((SendAmountSelectionPresenter) getPresenter()).onBackPressed();
    }

    public void onClosePressed() {
        SendAmountSelectionPresenter sendAmountSelectionPresenter = (SendAmountSelectionPresenter) getPresenter();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            sendAmountSelectionPresenter.onClosePressed(activity);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    @NotNull
    public final WalletType getWalletType() {
        WalletType walletType2 = this.walletType;
        if (walletType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletType");
        }
        return walletType2;
    }

    public final void setWalletType(@NotNull WalletType walletType2) {
        Intrinsics.checkParameterIsNotNull(walletType2, "<set-?>");
        this.walletType = walletType2;
    }

    public void onStart() {
        super.onStart();
        setPreferredWallet(getNavArgs().getPreferredWalletId());
        ((SendAmountSelectionPresenter) getPresenter()).setInitialSatoshiAmount(getNavArgs().getSendToUri().getAmount());
    }

    public final void setPreferredWallet(@Nullable WalletId walletId) {
        if (walletId != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SendAmountSelectionView$setPreferredWallet$1(this, walletId, null), 3, null);
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        Object obj;
        Object obj2;
        WalletType walletType2;
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
        if (pinpadview != null) {
            pinpadview.setPinpadListener(this);
        }
        bindInitialArgs();
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
        }
        if (getNavArgs().getSendToUri().isSlp()) {
            walletType2 = WalletType.SLP;
        } else if (getNavArgs().getSendToUri().getCoin() == Coin.BCH) {
            walletType2 = WalletType.BCH;
        } else {
            walletType2 = WalletType.BTC;
        }
        this.walletType = walletType2;
    }

    public final void bindInitialArgs() {
        ((SendAmountSelectionPresenter) getPresenter()).init(getNavArgs().getSendToUri().getCoin(), getNavArgs().getSendToUri().getTokenId());
        ((SendAmountSelectionPresenter) getPresenter()).setSendToUri(getNavArgs().getSendToUri());
    }

    private final void bindButtonActions() {
        View view = getView();
        if (view != null) {
            Button button = (Button) view.findViewById(C1018R.C1021id.continueButton);
            if (button != null) {
                button.setOnClickListener(new SendAmountSelectionView$bindButtonActions$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            ImageButton imageButton = (ImageButton) view2.findViewById(C1018R.C1021id.swapFiatButton);
            if (imageButton != null) {
                imageButton.setOnClickListener(new SendAmountSelectionView$bindButtonActions$2(this));
            }
        }
        View view3 = getView();
        if (view3 != null) {
            TextView textView = (TextView) view3.findViewById(C1018R.C1021id.sendAllButton);
            if (textView != null) {
                textView.setOnClickListener(new SendAmountSelectionView$bindButtonActions$3(this));
            }
        }
        View view4 = getView();
        if (view4 != null) {
            TextView textView2 = (TextView) view4.findViewById(C1018R.C1021id.currencySelector);
            if (textView2 != null) {
                textView2.setOnClickListener(new SendAmountSelectionView$bindButtonActions$4(this));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            LinearLayout linearLayout = (LinearLayout) view5.findViewById(C1018R.C1021id.walletSelectContainer);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new SendAmountSelectionView$bindButtonActions$5(this));
            }
        }
    }

    private final void bindDataObservers() {
        if (getNavArgs().getSendToUri().isSlp()) {
            View view = getView();
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.secondaryAmountTextView);
                if (textView != null) {
                    textView.setVisibility(8);
                }
            }
            View view2 = getView();
            if (view2 != null) {
                TextView textView2 = (TextView) view2.findViewById(C1018R.C1021id.currencySelector);
                if (textView2 != null) {
                    textView2.setVisibility(8);
                }
            }
            View view3 = getView();
            if (view3 != null) {
                ImageButton imageButton = (ImageButton) view3.findViewById(C1018R.C1021id.swapFiatButton);
                if (imageButton != null) {
                    imageButton.setVisibility(8);
                }
            }
        }
        ((SendAmountSelectionPresenter) getPresenter()).getPrimaryAmount().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$1(this));
        ((SendAmountSelectionPresenter) getPresenter()).getSecondaryDisplayAmount().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$2(this));
        ((SendAmountSelectionPresenter) getPresenter()).getPrimaryEntryType().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$3(this));
        ((SendAmountSelectionPresenter) getPresenter()).getDisplayCurrency().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$4(this));
        ((SendAmountSelectionPresenter) getPresenter()).getSelectedWalletId().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$5(this));
        ((SendAmountSelectionPresenter) getPresenter()).getBalanceDependencies().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$6(this));
        ((SendAmountSelectionPresenter) getPresenter()).getWalletBalance().observe(this, new SendAmountSelectionView$bindDataObservers$7(this));
        ((SendAmountSelectionPresenter) getPresenter()).getHasEnoughBalance().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$8(this));
        ((SendAmountSelectionPresenter) getPresenter()).getSendEnabled().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$9(this));
        ((SendAmountSelectionPresenter) getPresenter()).getAmountError().observe(getViewLifecycleOwner(), new SendAmountSelectionView$bindDataObservers$10(this));
    }

    public void digitTapped(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "digit");
        if (((SendAmountSelectionPresenter) getPresenter()).getSelectedWalletId().getValue() == null) {
            View view = getView();
            if (view != null) {
                Button button = (Button) view.findViewById(C1018R.C1021id.continueButton);
                if (button != null) {
                    button.setEnabled(false);
                }
            }
            ((SendAmountSelectionPresenter) getPresenter()).getSelectedWalletId().postValue(null);
        } else if (Intrinsics.areEqual((Object) (Boolean) ((SendAmountSelectionPresenter) getPresenter()).getHasEnoughBalance().getValue(), (Object) Boolean.valueOf(false))) {
            View view2 = getView();
            if (view2 != null) {
                TextView textView = (TextView) view2.findViewById(C1018R.C1021id.primaryAmountTextView);
                if (textView != null) {
                    textView.startAnimation(AnimationUtils.loadAnimation(getContext(), C1018R.anim.shake));
                }
            }
        } else {
            ((SendAmountSelectionPresenter) getPresenter()).digitTapped(str);
        }
    }

    public void decimalTapped() {
        if (Intrinsics.areEqual((Object) (Boolean) ((SendAmountSelectionPresenter) getPresenter()).getHasEnoughBalance().getValue(), (Object) Boolean.valueOf(false))) {
            View view = getView();
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.primaryAmountTextView);
                if (textView != null) {
                    textView.startAnimation(AnimationUtils.loadAnimation(getContext(), C1018R.anim.shake));
                }
            }
            return;
        }
        ((SendAmountSelectionPresenter) getPresenter()).decimalTapped();
    }

    public void deleteTapped() {
        ((SendAmountSelectionPresenter) getPresenter()).deleteTapped();
    }
}
