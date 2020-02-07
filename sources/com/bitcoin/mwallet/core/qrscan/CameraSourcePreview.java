package com.bitcoin.mwallet.core.qrscan;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;
import androidx.annotation.RequiresPermission;
import androidx.core.view.ViewCompat;
import java.io.IOException;

public class CameraSourcePreview extends ViewGroup {
    private static final String TAG = "CameraSourcePreview";
    private CameraSource mCameraSource;
    private Context mContext;
    private boolean mStartRequested = false;
    /* access modifiers changed from: private */
    public boolean mSurfaceAvailable = false;
    private SurfaceView mSurfaceView;

    private class SurfaceCallback implements Callback {
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        private SurfaceCallback() {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            String str = CameraSourcePreview.TAG;
            CameraSourcePreview.this.mSurfaceAvailable = true;
            try {
                CameraSourcePreview.this.startIfReady();
            } catch (SecurityException e) {
                Log.e(str, "Do not have permission to start the camera", e);
            } catch (IOException e2) {
                Log.e(str, "Could not start camera source.", e2);
            } catch (RuntimeException e3) {
                Log.e(str, "Runtime exception when starting camera source.", e3);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            CameraSourcePreview.this.mSurfaceAvailable = false;
        }
    }

    public CameraSourcePreview(Context context) {
        super(context);
        this.mContext = context;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.mSurfaceView = new SurfaceView(context);
        this.mSurfaceView.getHolder().addCallback(new SurfaceCallback());
        addView(this.mSurfaceView);
    }

    public CameraSourcePreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mSurfaceView = new SurfaceView(context);
        this.mSurfaceView.getHolder().addCallback(new SurfaceCallback());
        addView(this.mSurfaceView);
    }

    @RequiresPermission("android.permission.CAMERA")
    public void start(CameraSource cameraSource) throws IOException, SecurityException {
        if (cameraSource == null) {
            stop();
        }
        this.mCameraSource = cameraSource;
        if (this.mCameraSource != null) {
            this.mStartRequested = true;
            startIfReady();
        }
    }

    public void stop() {
        CameraSource cameraSource = this.mCameraSource;
        if (cameraSource != null) {
            cameraSource.stop();
        }
    }

    public void release() {
        CameraSource cameraSource = this.mCameraSource;
        if (cameraSource != null) {
            cameraSource.release();
            this.mCameraSource = null;
        }
    }

    /* access modifiers changed from: private */
    @RequiresPermission("android.permission.CAMERA")
    public void startIfReady() throws IOException, SecurityException {
        if (this.mStartRequested && this.mSurfaceAvailable) {
            this.mCameraSource.start(this.mSurfaceView.getHolder());
            this.mStartRequested = false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046 A[LOOP:0: B:14:0x0040->B:16:0x0046, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            java.lang.String r5 = "CameraSourcePreview"
            com.bitcoin.mwallet.core.qrscan.CameraSource r0 = r4.mCameraSource
            if (r0 == 0) goto L_0x0015
            com.google.android.gms.common.images.Size r0 = r0.getPreviewSize()
            if (r0 == 0) goto L_0x0015
            int r1 = r0.getWidth()
            int r0 = r0.getHeight()
            goto L_0x0019
        L_0x0015:
            r1 = 320(0x140, float:4.48E-43)
            r0 = 240(0xf0, float:3.36E-43)
        L_0x0019:
            boolean r2 = r4.isPortraitMode()
            if (r2 == 0) goto L_0x0020
            goto L_0x0023
        L_0x0020:
            r3 = r1
            r1 = r0
            r0 = r3
        L_0x0023:
            int r8 = r8 - r6
            int r9 = r9 - r7
            float r6 = (float) r8
            float r7 = (float) r0
            float r6 = r6 / r7
            float r0 = (float) r1
            float r6 = r6 * r0
            int r6 = (int) r6
            if (r6 <= r9) goto L_0x0036
            float r6 = (float) r9
            float r6 = r6 / r0
            float r6 = r6 * r7
            int r6 = (int) r6
            r7 = r6
            r6 = r9
            goto L_0x0037
        L_0x0036:
            r7 = r8
        L_0x0037:
            int r9 = r9 - r6
            int r9 = r9 / 2
            int r6 = r6 + r9
            int r8 = r8 - r7
            int r8 = r8 / 2
            int r7 = r7 + r8
            r0 = 0
        L_0x0040:
            int r1 = r4.getChildCount()
            if (r0 >= r1) goto L_0x0050
            android.view.View r1 = r4.getChildAt(r0)
            r1.layout(r8, r9, r7, r6)
            int r0 = r0 + 1
            goto L_0x0040
        L_0x0050:
            r4.startIfReady()     // Catch:{ SecurityException -> 0x005b, IOException -> 0x0054 }
            goto L_0x0061
        L_0x0054:
            r6 = move-exception
            java.lang.String r7 = "Could not start camera source."
            android.util.Log.e(r5, r7, r6)
            goto L_0x0061
        L_0x005b:
            r6 = move-exception
            java.lang.String r7 = "Do not have permission to start the camera"
            android.util.Log.e(r5, r7, r6)
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.qrscan.CameraSourcePreview.onLayout(boolean, int, int, int, int):void");
    }

    private boolean isPortraitMode() {
        int i = this.mContext.getResources().getConfiguration().orientation;
        if (i == 2) {
            return false;
        }
        if (i == 1) {
            return true;
        }
        Log.d(TAG, "isPortraitMode returning false by default");
        return false;
    }
}
