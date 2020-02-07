package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import com.airbnb.lottie.LottieAnimationView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.listener.MnemonicScannerListener;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\tH\u0002J\u0006\u0010\u000f\u001a\u00020\tJ\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u0013\u001a\u00020\tH\u0016J\u0012\u0010\u0014\u001a\u00020\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000bH\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020!H\u0002J\u0006\u0010\"\u001a\u00020\tR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006#"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/view/MnemonicTabView;", "Landroidx/fragment/app/Fragment;", "Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/listener/MnemonicScannerListener;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;)V", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "addChipToGroup", "", "name", "", "chipGroup", "Lcom/google/android/material/chip/ChipGroup;", "dismissKeyboard", "hideLoading", "loadInputUI", "recoveryPhraseEditText", "Landroid/widget/EditText;", "onCancelDialog", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onScanned", "result", "showKeyboard", "view", "Landroid/widget/TextView;", "showLoading", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicTabView.kt */
public final class MnemonicTabView extends Fragment implements MnemonicScannerListener {
    private HashMap _$_findViewCache;
    @Nullable
    private final ImportWalletPresenter presenter;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CreateWalletResult.values().length];

        static {
            $EnumSwitchMapping$0[CreateWalletResult.SUCCESS.ordinal()] = 1;
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

    @Nullable
    public final ImportWalletPresenter getPresenter() {
        return this.presenter;
    }

    public MnemonicTabView(@Nullable ImportWalletPresenter importWalletPresenter) {
        this.presenter = importWalletPresenter;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        if (context != null) {
            context.setTheme(C1018R.C1026style.AppThemeMaterialComponents);
        }
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(C1018R.layout.fragment_mnemonic_tab_view, viewGroup, false);
        ImportWalletPresenter importWalletPresenter = this.presenter;
        RadioButton radioButton = null;
        Boolean valueOf = importWalletPresenter != null ? Boolean.valueOf(importWalletPresenter.getHasZion()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        if (valueOf.booleanValue()) {
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C1018R.C1021id.mnemonicLayout);
            if (linearLayout != null) {
                linearLayout.setVisibility(4);
            }
        }
        this.presenter.getProcessResult().observe(getViewLifecycleOwner(), new MnemonicTabView$onCreateView$1(this));
        RadioButton radioButton2 = (RadioButton) inflate.findViewById(C1018R.C1021id.bitcoinSelectionButton);
        if (inflate != null) {
            radioButton = (RadioButton) inflate.findViewById(C1018R.C1021id.bitcoinCashSelectionButton);
        }
        LifecycleOwner lifecycleOwner = this;
        this.presenter.getCoin().observe(lifecycleOwner, new MnemonicTabView$onCreateView$2(radioButton, radioButton2));
        if (radioButton != null) {
            radioButton.setOnCheckedChangeListener(new MnemonicTabView$onCreateView$3(this));
        }
        if (radioButton2 != null) {
            radioButton2.setOnCheckedChangeListener(new MnemonicTabView$onCreateView$4(this));
        }
        this.presenter.getMnemonicWords().observe(lifecycleOwner, new MnemonicTabView$onCreateView$5(this, inflate));
        ImageButton imageButton = (ImageButton) inflate.findViewById(C1018R.C1021id.qrCodeButton);
        if (imageButton != null) {
            imageButton.setOnClickListener(new MnemonicTabView$onCreateView$6(this));
        }
        TextView textView = (TextView) inflate.findViewById(C1018R.C1021id.importWalletByMnemonicButton);
        if (textView != null) {
            textView.setOnClickListener(new MnemonicTabView$onCreateView$7(this));
        }
        View findViewById = inflate.findViewById(C1018R.C1021id.recoveryPhraseEditText);
        String str = "layoutView.findViewById(…d.recoveryPhraseEditText)";
        Intrinsics.checkExpressionValueIsNotNull(findViewById, str);
        EditText editText = (EditText) findViewById;
        View findViewById2 = inflate.findViewById(C1018R.C1021id.mainTagChipGroup);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "layoutView.findViewById(R.id.mainTagChipGroup)");
        loadInputUI(editText, (ChipGroup) findViewById2);
        View findViewById3 = inflate.findViewById(C1018R.C1021id.recoveryPhraseEditText);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, str);
        showKeyboard((TextView) findViewById3);
        return inflate;
    }

    private final void loadInputUI(EditText editText, ChipGroup chipGroup) {
        MnemonicTabView$loadInputUI$1 mnemonicTabView$loadInputUI$1 = new MnemonicTabView$loadInputUI$1(this, chipGroup);
        editText.setOnEditorActionListener(new MnemonicTabView$loadInputUI$2(mnemonicTabView$loadInputUI$1));
        editText.addTextChangedListener(new MnemonicTabView$loadInputUI$$inlined$addTextChangedListener$1(mnemonicTabView$loadInputUI$1, editText));
    }

    public final void hideLoading() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.importWalletByMnemonicButton);
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) view2.findViewById(C1018R.C1021id.loadingAnimation);
            if (lottieAnimationView != null) {
                lottieAnimationView.setVisibility(8);
            }
        }
    }

    public final void showLoading() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.importWalletByMnemonicButton);
            if (textView != null) {
                textView.setVisibility(8);
            }
        }
        View view2 = getView();
        if (view2 != null) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) view2.findViewById(C1018R.C1021id.loadingAnimation);
            if (lottieAnimationView != null) {
                lottieAnimationView.setVisibility(0);
            }
        }
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

    /* access modifiers changed from: private */
    public final void showKeyboard(TextView textView) {
        if (textView.requestFocus()) {
            FragmentActivity activity = getActivity();
            Object systemService = activity != null ? activity.getSystemService("input_method") : null;
            if (systemService != null) {
                ((InputMethodManager) systemService).showSoftInput(textView, 0);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }

    /* access modifiers changed from: private */
    public final void addChipToGroup(String str, ChipGroup chipGroup) {
        Chip chip = new Chip(getContext());
        chip.setText(str);
        chip.setCloseIconResource(C1018R.C1020drawable.ic_chip_close);
        chip.setCloseIconTintResource(C1018R.C1019color.lightGray);
        chip.setChipBackgroundColorResource(C1018R.C1019color.chip_state);
        chip.setClickable(false);
        chip.setCheckable(false);
        chip.setCloseIconVisible(true);
        chipGroup.addView(chip);
        chip.setOnCloseIconClickListener(new MnemonicTabView$addChipToGroup$1(this, chipGroup, chip, str));
    }

    public void onScanned(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "result");
        ((ChipGroup) _$_findCachedViewById(C1018R.C1021id.mainTagChipGroup)).removeAllViews();
        ImportWalletPresenter importWalletPresenter = this.presenter;
        if (importWalletPresenter != null) {
            importWalletPresenter.onClearMnemonic();
        }
        CharSequence charSequence = str;
        String str2 = " ";
        List<String> mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default(charSequence, new String[]{str2}, false, 0, 6, (Object) null));
        List mutableList2 = CollectionsKt.toMutableList((Collection) StringsKt.split$default(charSequence, new String[]{"|"}, false, 0, 6, (Object) null));
        if (mutableList2.size() == 5 && Intrinsics.areEqual((Object) (String) mutableList2.get(0), (Object) "1")) {
            mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) mutableList2.get(1), new String[]{str2}, false, 0, 6, (Object) null));
        }
        for (String str3 : mutableList) {
            ImportWalletPresenter importWalletPresenter2 = this.presenter;
            if (importWalletPresenter2 != null) {
                importWalletPresenter2.onAddMnemonic(str3);
            }
            ChipGroup chipGroup = (ChipGroup) _$_findCachedViewById(C1018R.C1021id.mainTagChipGroup);
            Intrinsics.checkExpressionValueIsNotNull(chipGroup, "mainTagChipGroup");
            addChipToGroup(str3, chipGroup);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCancelDialog() {
        /*
            r3 = this;
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter r0 = r3.presenter
            r1 = 0
            if (r0 == 0) goto L_0x001e
            androidx.lifecycle.LiveData r0 = r0.getMnemonicWords()
            if (r0 == 0) goto L_0x001e
            java.lang.Object r0 = r0.getValue()
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x001e
            java.util.Collection r0 = (java.util.Collection) r0
            int r0 = r0.size()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x001f
        L_0x001e:
            r0 = r1
        L_0x001f:
            if (r0 == 0) goto L_0x0042
            int r0 = r0.intValue()
            r2 = 12
            if (r0 < r2) goto L_0x002d
            r3.dismissKeyboard()
            goto L_0x0042
        L_0x002d:
            android.view.View r0 = r3.getView()
            if (r0 == 0) goto L_0x003d
            r1 = 2131362416(0x7f0a0270, float:1.8344612E38)
            android.view.View r0 = r0.findViewById(r1)
            r1 = r0
            android.widget.TextView r1 = (android.widget.TextView) r1
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r3.showKeyboard(r1)
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view.MnemonicTabView.onCancelDialog():void");
    }
}
