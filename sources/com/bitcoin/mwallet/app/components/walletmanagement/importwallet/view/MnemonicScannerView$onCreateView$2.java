package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicScannerView.kt */
final class MnemonicScannerView$onCreateView$2 implements OnClickListener {
    final /* synthetic */ DecoratedBarcodeView $barcodeScannerView;
    final /* synthetic */ MnemonicScannerView this$0;

    MnemonicScannerView$onCreateView$2(MnemonicScannerView mnemonicScannerView, DecoratedBarcodeView decoratedBarcodeView) {
        this.this$0 = mnemonicScannerView;
        this.$barcodeScannerView = decoratedBarcodeView;
    }

    public final void onClick(View view) {
        this.this$0.getListener().onCancelDialog();
        DecoratedBarcodeView decoratedBarcodeView = this.$barcodeScannerView;
        if (decoratedBarcodeView != null) {
            decoratedBarcodeView.pauseAndWait();
        }
        this.this$0.dismiss();
    }
}
