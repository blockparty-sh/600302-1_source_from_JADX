package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.cottacush.android.currencyedittext.CurrencyEditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010\u0019\u001a\u00020\u0017J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u0018\u0010\u001b\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001d\u001a\u00020\u0007J\u0012\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsPresenter;", "()V", "colors", "", "", "getColors", "()Ljava/util/List;", "setColors", "(Ljava/util/List;)V", "dots", "Landroid/widget/ImageView;", "getDots", "setDots", "navArgs", "Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "bindActions", "", "bindColorDots", "bindObservers", "dismissKeyboard", "dotSelected", "imageView", "color", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsView.kt */
public final class WalletSettingsView extends ScreenView<WalletSettingsBuilder, WalletSettingsPresenter> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(WalletSettingsView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/walletdetails/walletsettings/WalletSettingsViewArgs;"))};
    private HashMap _$_findViewCache;
    @NotNull
    private List<Integer> colors = new ArrayList();
    @NotNull
    private List<ImageView> dots = new ArrayList();
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(WalletSettingsViewArgs.class), new WalletSettingsView$$special$$inlined$navArgs$1(this));

    private final WalletSettingsViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (WalletSettingsViewArgs) lazy.getValue();
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

    public WalletSettingsView() {
        super(C1018R.layout.fragment_screen_walletdetails_walletsettings, Reflection.getOrCreateKotlinClass(WalletSettingsBuilder.class));
    }

    @NotNull
    public final List<ImageView> getDots() {
        return this.dots;
    }

    public final void setDots(@NotNull List<ImageView> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.dots = list;
    }

    @NotNull
    public final List<Integer> getColors() {
        return this.colors;
    }

    public final void setColors(@NotNull List<Integer> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.colors = list;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        ((WalletSettingsPresenter) getPresenter()).setWalletId(getNavArgs().getWalletId());
        C1261Wallet wallet = ((WalletSettingsPresenter) getPresenter()).getWallet(getNavArgs().getWalletId());
        if (wallet != null && wallet.getCoin() == Coin.BTC) {
            View view = getView();
            if (view != null) {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.instantPayLayout);
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
            }
        }
        bindActions();
        bindObservers();
    }

    public final void bindObservers() {
        BooleanRef booleanRef = new BooleanRef();
        booleanRef.element = true;
        ((WalletSettingsPresenter) getPresenter()).getWallet().observe(getViewLifecycleOwner(), new WalletSettingsView$bindObservers$1(this, booleanRef));
        ((WalletSettingsPresenter) getPresenter()).getColorData().observe(getViewLifecycleOwner(), new WalletSettingsView$bindObservers$2(this));
        ((WalletSettingsPresenter) getPresenter()).getWalletCount().observe(getViewLifecycleOwner(), new WalletSettingsView$bindObservers$3(this));
    }

    public final void bindActions() {
        View view = getView();
        if (view != null) {
            EditText editText = (EditText) view.findViewById(C1018R.C1021id.walletNameEditText);
            if (editText != null) {
                editText.addTextChangedListener(new WalletSettingsView$bindActions$$inlined$addTextChangedListener$1(this));
            }
        }
        View view2 = getView();
        if (view2 != null) {
            EditText editText2 = (EditText) view2.findViewById(C1018R.C1021id.walletNameEditText);
            if (editText2 != null) {
                editText2.setOnEditorActionListener(new WalletSettingsView$bindActions$2(this));
            }
        }
        View view3 = getView();
        CurrencyEditText currencyEditText = view3 != null ? (CurrencyEditText) view3.findViewById(C1018R.C1021id.autoSpendThreshold) : null;
        if (currencyEditText != null) {
            currencyEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789.,"));
        }
        if (currencyEditText != null) {
            currencyEditText.addTextChangedListener(new WalletSettingsView$bindActions$$inlined$addTextChangedListener$2(this));
        }
        View view4 = getView();
        if (view4 != null) {
            Switch switchR = (Switch) view4.findViewById(C1018R.C1021id.hideBalancesSwitch);
            if (switchR != null) {
                switchR.setOnCheckedChangeListener(new WalletSettingsView$bindActions$4(this));
            }
        }
        View view5 = getView();
        if (view5 != null) {
            Switch switchR2 = (Switch) view5.findViewById(C1018R.C1021id.requireSpendingAuthSwitch);
            if (switchR2 != null) {
                switchR2.setOnCheckedChangeListener(new WalletSettingsView$bindActions$5(this));
            }
        }
        View view6 = getView();
        if (view6 != null) {
            LinearLayout linearLayout = (LinearLayout) view6.findViewById(C1018R.C1021id.deleteWalletContainer);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(new WalletSettingsView$bindActions$6(this));
            }
        }
        View view7 = getView();
        if (view7 != null) {
            LinearLayout linearLayout2 = (LinearLayout) view7.findViewById(C1018R.C1021id.walletRecoveryPhraseContainer);
            if (linearLayout2 != null) {
                linearLayout2.setOnClickListener(new WalletSettingsView$bindActions$7(this));
            }
        }
        View view8 = getView();
        if (view8 != null) {
            LinearLayout linearLayout3 = (LinearLayout) view8.findViewById(C1018R.C1021id.walletInformationContainer);
            if (linearLayout3 != null) {
                linearLayout3.setOnClickListener(new WalletSettingsView$bindActions$8(this));
            }
        }
        bindColorDots();
    }

    /* access modifiers changed from: private */
    public final void dismissKeyboard() {
        FragmentActivity activity = getActivity();
        IBinder iBinder = null;
        Object systemService = activity != null ? activity.getSystemService("input_method") : null;
        if (systemService != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            View view = getView();
            if (view != null) {
                iBinder = view.getWindowToken();
            }
            inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
    }

    public final void dotSelected(@Nullable ImageView imageView, int i) {
        ((WalletSettingsPresenter) getPresenter()).setColor(i);
        int size = this.dots.size() - 1;
        if (size >= 0) {
            int i2 = 0;
            while (true) {
                ImageView imageView2 = (ImageView) this.dots.get(i2);
                if (imageView2 != null) {
                    imageView2.setImageResource(C1018R.C1020drawable.background_wallet_color_dot);
                }
                ImageView imageView3 = (ImageView) this.dots.get(i2);
                if (imageView3 != null) {
                    imageView3.setColorFilter(((Number) this.colors.get(i2)).intValue(), Mode.MULTIPLY);
                }
                if (i2 == size) {
                    break;
                }
                i2++;
            }
        }
        if (imageView != null) {
            imageView.setImageResource(C1018R.C1020drawable.ic_colorselected);
        }
        if (imageView != null) {
            imageView.setColorFilter(i, Mode.MULTIPLY);
        }
    }

    public final void bindColorDots() {
        View view = getView();
        ImageView imageView = null;
        ImageView imageView2 = view != null ? (ImageView) view.findViewById(C1018R.C1021id.walletGreenColor) : null;
        View view2 = getView();
        ImageView imageView3 = view2 != null ? (ImageView) view2.findViewById(C1018R.C1021id.walletBlueColor) : null;
        View view3 = getView();
        ImageView imageView4 = view3 != null ? (ImageView) view3.findViewById(C1018R.C1021id.walletPurpleColor) : null;
        View view4 = getView();
        ImageView imageView5 = view4 != null ? (ImageView) view4.findViewById(C1018R.C1021id.walletNavyColor) : null;
        View view5 = getView();
        ImageView imageView6 = view5 != null ? (ImageView) view5.findViewById(C1018R.C1021id.walletOrangeColor) : null;
        View view6 = getView();
        ImageView imageView7 = view6 != null ? (ImageView) view6.findViewById(C1018R.C1021id.walletYellowColor) : null;
        View view7 = getView();
        ImageView imageView8 = view7 != null ? (ImageView) view7.findViewById(C1018R.C1021id.walletPinkColor) : null;
        View view8 = getView();
        if (view8 != null) {
            imageView = (ImageView) view8.findViewById(C1018R.C1021id.walletTransparentColor);
        }
        this.dots = CollectionsKt.mutableListOf(imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView);
        this.colors = CollectionsKt.mutableListOf(Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.cashGreen)), Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.walletColorBlue)), Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.walletColorPurple)), Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.walletColorNavy)), Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.walletColorOrange)), Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.walletColorYellow)), Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.walletColorPink)), Integer.valueOf(ContextCompat.getColor(requireContext(), C1018R.C1019color.walletColorTransparent)));
        if (imageView2 != null) {
            imageView2.setOnClickListener(new WalletSettingsView$bindColorDots$1(this, imageView2));
        }
        if (imageView3 != null) {
            imageView3.setOnClickListener(new WalletSettingsView$bindColorDots$2(this, imageView3));
        }
        if (imageView4 != null) {
            imageView4.setOnClickListener(new WalletSettingsView$bindColorDots$3(this, imageView4));
        }
        if (imageView5 != null) {
            imageView5.setOnClickListener(new WalletSettingsView$bindColorDots$4(this, imageView5));
        }
        if (imageView6 != null) {
            imageView6.setOnClickListener(new WalletSettingsView$bindColorDots$5(this, imageView6));
        }
        if (imageView7 != null) {
            imageView7.setOnClickListener(new WalletSettingsView$bindColorDots$6(this, imageView7));
        }
        if (imageView8 != null) {
            imageView8.setOnClickListener(new WalletSettingsView$bindColorDots$7(this, imageView8));
        }
        if (imageView != null) {
            imageView.setOnClickListener(new WalletSettingsView$bindColorDots$8(this, imageView));
        }
    }
}
