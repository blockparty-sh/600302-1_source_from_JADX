package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.C2220R;
import com.google.zxing.client.android.DecodeFormatManager;
import com.google.zxing.client.android.DecodeHintManager;
import com.google.zxing.client.android.Intents.Scan;
import com.journeyapps.barcodescanner.camera.CameraParametersCallback;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DecoratedBarcodeView extends FrameLayout {
    private BarcodeView barcodeView;
    private TextView statusView;
    private TorchListener torchListener;
    /* access modifiers changed from: private */
    public ViewfinderView viewFinder;

    public interface TorchListener {
        void onTorchOff();

        void onTorchOn();
    }

    private class WrappedCallback implements BarcodeCallback {
        private BarcodeCallback delegate;

        public WrappedCallback(BarcodeCallback barcodeCallback) {
            this.delegate = barcodeCallback;
        }

        public void barcodeResult(BarcodeResult barcodeResult) {
            this.delegate.barcodeResult(barcodeResult);
        }

        public void possibleResultPoints(List<ResultPoint> list) {
            for (ResultPoint addPossibleResultPoint : list) {
                DecoratedBarcodeView.this.viewFinder.addPossibleResultPoint(addPossibleResultPoint);
            }
            this.delegate.possibleResultPoints(list);
        }
    }

    public DecoratedBarcodeView(Context context) {
        super(context);
        initialize();
    }

    public DecoratedBarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet);
    }

    public DecoratedBarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(attributeSet);
    }

    private void initialize(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C2220R.styleable.zxing_view);
        int resourceId = obtainStyledAttributes.getResourceId(C2220R.styleable.zxing_view_zxing_scanner_layout, C2220R.layout.zxing_barcode_scanner);
        obtainStyledAttributes.recycle();
        inflate(getContext(), resourceId, this);
        this.barcodeView = (BarcodeView) findViewById(C2220R.C2222id.zxing_barcode_surface);
        BarcodeView barcodeView2 = this.barcodeView;
        if (barcodeView2 != null) {
            barcodeView2.initializeAttributes(attributeSet);
            this.viewFinder = (ViewfinderView) findViewById(C2220R.C2222id.zxing_viewfinder_view);
            ViewfinderView viewfinderView = this.viewFinder;
            if (viewfinderView != null) {
                viewfinderView.setCameraPreview(this.barcodeView);
                this.statusView = (TextView) findViewById(C2220R.C2222id.zxing_status_view);
                return;
            }
            throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.ViewfinderView on provided layout with the id \"zxing_viewfinder_view\".");
        }
        throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.BarcodeView on provided layout with the id \"zxing_barcode_surface\".");
    }

    private void initialize() {
        initialize(null);
    }

    public void initializeFromIntent(Intent intent) {
        Set parseDecodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
        Map parseDecodeHints = DecodeHintManager.parseDecodeHints(intent);
        CameraSettings cameraSettings = new CameraSettings();
        String str = Scan.CAMERA_ID;
        if (intent.hasExtra(str)) {
            int intExtra = intent.getIntExtra(str, -1);
            if (intExtra >= 0) {
                cameraSettings.setRequestedCameraId(intExtra);
            }
        }
        String stringExtra = intent.getStringExtra(Scan.PROMPT_MESSAGE);
        if (stringExtra != null) {
            setStatusText(stringExtra);
        }
        int intExtra2 = intent.getIntExtra(Scan.SCAN_TYPE, 0);
        String stringExtra2 = intent.getStringExtra(Scan.CHARACTER_SET);
        new MultiFormatReader().setHints(parseDecodeHints);
        this.barcodeView.setCameraSettings(cameraSettings);
        this.barcodeView.setDecoderFactory(new DefaultDecoderFactory(parseDecodeFormats, parseDecodeHints, stringExtra2, intExtra2));
    }

    public void setStatusText(String str) {
        TextView textView = this.statusView;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void pause() {
        this.barcodeView.pause();
    }

    public void pauseAndWait() {
        this.barcodeView.pauseAndWait();
    }

    public void resume() {
        this.barcodeView.resume();
    }

    public BarcodeView getBarcodeView() {
        return (BarcodeView) findViewById(C2220R.C2222id.zxing_barcode_surface);
    }

    public ViewfinderView getViewFinder() {
        return this.viewFinder;
    }

    public TextView getStatusView() {
        return this.statusView;
    }

    public void decodeSingle(BarcodeCallback barcodeCallback) {
        this.barcodeView.decodeSingle(new WrappedCallback(barcodeCallback));
    }

    public void decodeContinuous(BarcodeCallback barcodeCallback) {
        this.barcodeView.decodeContinuous(new WrappedCallback(barcodeCallback));
    }

    public void setTorchOn() {
        this.barcodeView.setTorch(true);
        TorchListener torchListener2 = this.torchListener;
        if (torchListener2 != null) {
            torchListener2.onTorchOn();
        }
    }

    public void setTorchOff() {
        this.barcodeView.setTorch(false);
        TorchListener torchListener2 = this.torchListener;
        if (torchListener2 != null) {
            torchListener2.onTorchOff();
        }
    }

    public void changeCameraParameters(CameraParametersCallback cameraParametersCallback) {
        this.barcodeView.changeCameraParameters(cameraParametersCallback);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 24) {
            setTorchOn();
            return true;
        } else if (i == 25) {
            setTorchOff();
            return true;
        } else if (i == 27 || i == 80) {
            return true;
        } else {
            return super.onKeyDown(i, keyEvent);
        }
    }

    public void setTorchListener(TorchListener torchListener2) {
        this.torchListener = torchListener2;
    }
}
