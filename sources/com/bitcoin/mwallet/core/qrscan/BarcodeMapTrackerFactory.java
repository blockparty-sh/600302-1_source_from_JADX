package com.bitcoin.mwallet.core.qrscan;

import com.google.android.gms.vision.MultiProcessor.Factory;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import java.util.Map;

public class BarcodeMapTrackerFactory implements Factory<Barcode> {
    private Map<Integer, Barcode> mBarcodes;
    private BarcodeUpdateListener mListener;

    public BarcodeMapTrackerFactory(Map<Integer, Barcode> map, BarcodeUpdateListener barcodeUpdateListener) {
        this.mBarcodes = map;
        this.mListener = barcodeUpdateListener;
    }

    public Tracker<Barcode> create(Barcode barcode) {
        return new BarcodeMapTracker(this.mBarcodes, this.mListener);
    }
}
