package com.bitcoin.mwallet.app.components.appinfodialog;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.bitcoin.mwallet.C1018R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/appinfodialog/AppInfoDialogView;", "Landroidx/fragment/app/DialogFragment;", "listener", "Lcom/bitcoin/mwallet/app/components/appinfodialog/AboutDialogListener;", "(Lcom/bitcoin/mwallet/app/components/appinfodialog/AboutDialogListener;)V", "getListener", "()Lcom/bitcoin/mwallet/app/components/appinfodialog/AboutDialogListener;", "tap", "", "getTap", "()I", "setTap", "(I)V", "getVersionName", "", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AppInfoDialogView.kt */
public final class AppInfoDialogView extends DialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    private final AboutDialogListener listener;
    private int tap;

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

    public AppInfoDialogView(@NotNull AboutDialogListener aboutDialogListener) {
        Intrinsics.checkParameterIsNotNull(aboutDialogListener, CastExtraArgs.LISTENER);
        this.listener = aboutDialogListener;
    }

    @NotNull
    public final AboutDialogListener getListener() {
        return this.listener;
    }

    public final int getTap() {
        return this.tap;
    }

    public final void setTap(int i) {
        this.tap = i;
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
        String str;
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(C1018R.layout.dialog_about, viewGroup, false);
        View findViewById = inflate.findViewById(C1018R.C1021id.walletVersion);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "customView.findViewById<…View>(R.id.walletVersion)");
        TextView textView = (TextView) findViewById;
        Context context = getContext();
        if (context != null) {
            str = context.getString(C1018R.string.wallet_version, new Object[]{getVersionName()});
        } else {
            str = null;
        }
        textView.setText(str);
        ((ImageView) inflate.findViewById(C1018R.C1021id.bitcoinComLogo)).setOnClickListener(new AppInfoDialogView$onCreateView$1(this));
        return inflate;
    }

    private final String getVersionName() {
        Context context = getContext();
        String str = null;
        PackageManager packageManager = context != null ? context.getPackageManager() : null;
        if (packageManager == null) {
            Intrinsics.throwNpe();
        }
        Context context2 = getContext();
        if (context2 != null) {
            str = context2.getPackageName();
        }
        PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "context?.packageManager!…(context?.packageName, 0)");
        String str2 = packageInfo.versionName;
        Intrinsics.checkExpressionValueIsNotNull(str2, "pInfo.versionName");
        return str2;
    }
}
