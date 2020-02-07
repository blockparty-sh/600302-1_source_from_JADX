package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.listener.MnemonicScannerListener;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¨\u0006\n"}, mo37405d2 = {"com/bitcoin/mwallet/app/components/walletmanagement/importwallet/view/MnemonicScannerView$onCreateView$1", "Lcom/journeyapps/barcodescanner/BarcodeCallback;", "barcodeResult", "", "result", "Lcom/journeyapps/barcodescanner/BarcodeResult;", "possibleResultPoints", "resultPoints", "", "Lcom/google/zxing/ResultPoint;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicScannerView.kt */
public final class MnemonicScannerView$onCreateView$1 implements BarcodeCallback {
    final /* synthetic */ MnemonicScannerView this$0;

    public void possibleResultPoints(@NotNull List<? extends ResultPoint> list) {
        Intrinsics.checkParameterIsNotNull(list, "resultPoints");
    }

    MnemonicScannerView$onCreateView$1(MnemonicScannerView mnemonicScannerView) {
        this.this$0 = mnemonicScannerView;
    }

    public void barcodeResult(@Nullable BarcodeResult barcodeResult) {
        String str = null;
        if ((barcodeResult != null ? barcodeResult.getText() : null) != null) {
            MnemonicScannerListener listener = this.this$0.getListener();
            if (barcodeResult != null) {
                str = barcodeResult.getText();
            }
            Intrinsics.checkExpressionValueIsNotNull(str, "result?.text");
            listener.onScanned(str);
            this.this$0.dismiss();
        }
    }
}
