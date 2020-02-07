package com.bitcoin.mwallet.core.qrscan;

import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import java.util.Map;

public class BarcodeMapTracker extends Tracker<Barcode> {
    private BarcodeUpdateListener mBarcodeUpdateListener;
    private Map<Integer, Barcode> mBarcodes;
    private Integer mId;

    BarcodeMapTracker(Map<Integer, Barcode> map, BarcodeUpdateListener barcodeUpdateListener) {
        this.mBarcodes = map;
        this.mBarcodeUpdateListener = barcodeUpdateListener;
    }

    public void onNewItem(int i, Barcode barcode) {
        this.mId = Integer.valueOf(i);
        this.mBarcodes.put(Integer.valueOf(i), barcode);
        BarcodeUpdateListener barcodeUpdateListener = this.mBarcodeUpdateListener;
        if (barcodeUpdateListener != null) {
            barcodeUpdateListener.onBarcodeDetected(barcode);
        }
    }

    public void onUpdate(Detections<Barcode> detections, Barcode barcode) {
        Integer num = this.mId;
        if (num != null) {
            this.mBarcodes.put(num, barcode);
        }
    }

    public void onMissing(Detections<Barcode> detections) {
        Integer num = this.mId;
        if (num != null) {
            this.mBarcodes.remove(num);
            this.mId = null;
        }
    }

    public void onDone() {
        Integer num = this.mId;
        if (num != null) {
            this.mBarcodes.remove(num);
            this.mId = null;
        }
    }
}
