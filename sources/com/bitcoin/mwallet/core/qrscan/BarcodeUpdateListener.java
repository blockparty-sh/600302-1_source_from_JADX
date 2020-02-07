package com.bitcoin.mwallet.core.qrscan;

import androidx.annotation.UiThread;
import com.google.android.gms.vision.barcode.Barcode;

public interface BarcodeUpdateListener {
    @UiThread
    void onBarcodeDetected(Barcode barcode);
}
