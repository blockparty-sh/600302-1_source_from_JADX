package com.journeyapps.barcodescanner;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.android.C2220R;
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.PreviewCallback;

public class DecoderThread {
    private static final String TAG = "DecoderThread";
    /* access modifiers changed from: private */
    public final Object LOCK = new Object();
    private final Callback callback = new Callback() {
        public boolean handleMessage(Message message) {
            if (message.what == C2220R.C2222id.zxing_decode) {
                DecoderThread.this.decode((SourceData) message.obj);
            } else if (message.what == C2220R.C2222id.zxing_preview_failed) {
                DecoderThread.this.requestNextPreview();
            }
            return true;
        }
    };
    private CameraInstance cameraInstance;
    private Rect cropRect;
    private Decoder decoder;
    /* access modifiers changed from: private */
    public Handler handler;
    private final PreviewCallback previewCallback = new PreviewCallback() {
        public void onPreview(SourceData sourceData) {
            synchronized (DecoderThread.this.LOCK) {
                if (DecoderThread.this.running) {
                    DecoderThread.this.handler.obtainMessage(C2220R.C2222id.zxing_decode, sourceData).sendToTarget();
                }
            }
        }

        public void onPreviewError(Exception exc) {
            synchronized (DecoderThread.this.LOCK) {
                if (DecoderThread.this.running) {
                    DecoderThread.this.handler.obtainMessage(C2220R.C2222id.zxing_preview_failed).sendToTarget();
                }
            }
        }
    };
    private Handler resultHandler;
    /* access modifiers changed from: private */
    public boolean running = false;
    private HandlerThread thread;

    public DecoderThread(CameraInstance cameraInstance2, Decoder decoder2, Handler handler2) {
        Util.validateMainThread();
        this.cameraInstance = cameraInstance2;
        this.decoder = decoder2;
        this.resultHandler = handler2;
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public void setDecoder(Decoder decoder2) {
        this.decoder = decoder2;
    }

    public Rect getCropRect() {
        return this.cropRect;
    }

    public void setCropRect(Rect rect) {
        this.cropRect = rect;
    }

    public void start() {
        Util.validateMainThread();
        this.thread = new HandlerThread(TAG);
        this.thread.start();
        this.handler = new Handler(this.thread.getLooper(), this.callback);
        this.running = true;
        requestNextPreview();
    }

    public void stop() {
        Util.validateMainThread();
        synchronized (this.LOCK) {
            this.running = false;
            this.handler.removeCallbacksAndMessages(null);
            this.thread.quit();
        }
    }

    /* access modifiers changed from: private */
    public void requestNextPreview() {
        this.cameraInstance.requestPreview(this.previewCallback);
    }

    /* access modifiers changed from: protected */
    public LuminanceSource createSource(SourceData sourceData) {
        if (this.cropRect == null) {
            return null;
        }
        return sourceData.createSource();
    }

    /* access modifiers changed from: private */
    public void decode(SourceData sourceData) {
        long currentTimeMillis = System.currentTimeMillis();
        sourceData.setCropRect(this.cropRect);
        LuminanceSource createSource = createSource(sourceData);
        Result decode = createSource != null ? this.decoder.decode(createSource) : null;
        if (decode != null) {
            long currentTimeMillis2 = System.currentTimeMillis();
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Found barcode in ");
            sb.append(currentTimeMillis2 - currentTimeMillis);
            sb.append(" ms");
            Log.d(str, sb.toString());
            if (this.resultHandler != null) {
                Message obtain = Message.obtain(this.resultHandler, C2220R.C2222id.zxing_decode_succeeded, new BarcodeResult(decode, sourceData));
                obtain.setData(new Bundle());
                obtain.sendToTarget();
            }
        } else {
            Handler handler2 = this.resultHandler;
            if (handler2 != null) {
                Message.obtain(handler2, C2220R.C2222id.zxing_decode_failed).sendToTarget();
            }
        }
        if (this.resultHandler != null) {
            Message.obtain(this.resultHandler, C2220R.C2222id.zxing_possible_result_points, this.decoder.getPossibleResultPoints()).sendToTarget();
        }
        requestNextPreview();
    }
}
