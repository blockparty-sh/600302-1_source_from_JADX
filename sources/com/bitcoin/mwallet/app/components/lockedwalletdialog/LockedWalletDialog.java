package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.airbnb.lottie.LottieAnimationView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialog;", "Landroidx/fragment/app/DialogFragment;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "hideLoading", "", "customView", "Landroid/view/View;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "showLoading", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LockedWalletDialog.kt */
public final class LockedWalletDialog extends DialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    private final WalletId walletId;

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

    public LockedWalletDialog(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this.walletId = walletId2;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Dialog dialog = new Dialog(activity, 2132017332);
            InsetDrawable insetDrawable = new InsetDrawable(new ColorDrawable(0), 20);
            Window window = dialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(insetDrawable);
            }
            return dialog;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        ViewModel viewModel = ViewModelProviders.m16of((Fragment) this).get(LockedWalletDialogBuilder.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ialogBuilder::class.java)");
        LockedWalletDialogPresenter presenter = ((LockedWalletDialogBuilder) viewModel).getPresenter();
        View inflate = layoutInflater.inflate(C1018R.layout.dialog_locked_wallet, viewGroup, false);
        ((ImageView) inflate.findViewById(C1018R.C1021id.dismissImage)).setOnClickListener(new LockedWalletDialog$onCreateView$1(this));
        TextView textView = (TextView) inflate.findViewById(C1018R.C1021id.unlockWalletButton);
        if (textView != null) {
            textView.setOnClickListener(new LockedWalletDialog$onCreateView$2(this, inflate, presenter));
        }
        TextView textView2 = (TextView) inflate.findViewById(C1018R.C1021id.removeWalletButton);
        if (textView2 != null) {
            textView2.setOnClickListener(new LockedWalletDialog$onCreateView$3(this));
        }
        presenter.getResultSuccess().observe(getViewLifecycleOwner(), new LockedWalletDialog$onCreateView$4(this, inflate));
        return inflate;
    }

    public final void showLoading(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "customView");
        View findViewById = view.findViewById(C1018R.C1021id.lockedWalletDialogActionLayout);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "customView.findViewById<…WalletDialogActionLayout)");
        ((LinearLayout) findViewById).setVisibility(8);
        View findViewById2 = view.findViewById(C1018R.C1021id.loadingAnimation);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "customView.findViewById<…w>(R.id.loadingAnimation)");
        ((LottieAnimationView) findViewById2).setVisibility(0);
    }

    public final void hideLoading(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "customView");
        View findViewById = view.findViewById(C1018R.C1021id.lockedWalletDialogActionLayout);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "customView.findViewById<…WalletDialogActionLayout)");
        ((LinearLayout) findViewById).setVisibility(0);
        View findViewById2 = view.findViewById(C1018R.C1021id.loadingAnimation);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "customView.findViewById<…w>(R.id.loadingAnimation)");
        ((LottieAnimationView) findViewById2).setVisibility(8);
    }
}
