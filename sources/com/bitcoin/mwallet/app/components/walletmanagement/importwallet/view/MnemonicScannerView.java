package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.listener.MnemonicScannerListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u001a\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/view/MnemonicScannerView;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "listener", "Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/listener/MnemonicScannerListener;", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/listener/MnemonicScannerListener;)V", "getListener", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/listener/MnemonicScannerListener;", "setListener", "getTheme", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "", "onResume", "onViewCreated", "view", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicScannerView.kt */
public final class MnemonicScannerView extends AppCompatDialogFragment {
    private HashMap _$_findViewCache;
    @NotNull
    private MnemonicScannerListener listener;

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

    public int getTheme() {
        return C1018R.C1026style.QRCodeDialogTheme;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public MnemonicScannerView(@NotNull MnemonicScannerListener mnemonicScannerListener) {
        Intrinsics.checkParameterIsNotNull(mnemonicScannerListener, CastExtraArgs.LISTENER);
        this.listener = mnemonicScannerListener;
    }

    @NotNull
    public final MnemonicScannerListener getListener() {
        return this.listener;
    }

    public final void setListener(@NotNull MnemonicScannerListener mnemonicScannerListener) {
        Intrinsics.checkParameterIsNotNull(mnemonicScannerListener, "<set-?>");
        this.listener = mnemonicScannerListener;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(C1018R.layout.fragment_component_mnemonic_scanner, viewGroup, false);
        DecoratedBarcodeView decoratedBarcodeView = inflate != null ? (DecoratedBarcodeView) inflate.findViewById(C1018R.C1021id.zxing_barcode_scanner) : null;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        String str = "android.permission.CAMERA";
        if (ContextCompat.checkSelfPermission(context, str) != 0) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            ActivityCompat.requestPermissions(activity, new String[]{str}, 1);
        }
        if (decoratedBarcodeView != null) {
            BarcodeView barcodeView = decoratedBarcodeView.getBarcodeView();
            if (barcodeView != null) {
                barcodeView.decodeContinuous(new MnemonicScannerView$onCreateView$1(this));
            }
        }
        Button button = (Button) inflate.findViewById(C1018R.C1021id.cancelButton);
        if (button != null) {
            button.setOnClickListener(new MnemonicScannerView$onCreateView$2(this, decoratedBarcodeView));
        }
        return inflate;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        DecoratedBarcodeView decoratedBarcodeView = (DecoratedBarcodeView) view.findViewById(C1018R.C1021id.zxing_barcode_scanner);
        if (decoratedBarcodeView != null) {
            decoratedBarcodeView.resume();
        }
    }

    public void onPause() {
        super.onPause();
        View view = getView();
        if (view != null) {
            DecoratedBarcodeView decoratedBarcodeView = (DecoratedBarcodeView) view.findViewById(C1018R.C1021id.zxing_barcode_scanner);
            if (decoratedBarcodeView != null) {
                decoratedBarcodeView.pauseAndWait();
            }
        }
    }

    public void onResume() {
        super.onResume();
        View view = getView();
        if (view != null) {
            DecoratedBarcodeView decoratedBarcodeView = (DecoratedBarcodeView) view.findViewById(C1018R.C1021id.zxing_barcode_scanner);
            if (decoratedBarcodeView != null) {
                decoratedBarcodeView.resume();
            }
        }
    }
}
