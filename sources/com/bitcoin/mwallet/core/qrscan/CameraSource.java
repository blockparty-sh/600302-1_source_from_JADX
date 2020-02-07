package com.bitcoin.mwallet.core.qrscan;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.Detector;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CameraSource {
    private static final float ASPECT_RATIO_TOLERANCE = 0.01f;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    private static final int DUMMY_TEXTURE_NAME = 100;
    private static final String TAG = "OpenCameraSource";
    /* access modifiers changed from: private */
    public Map<byte[], ByteBuffer> mBytesToByteBuffer;
    /* access modifiers changed from: private */
    public Camera mCamera;
    /* access modifiers changed from: private */
    public final Object mCameraLock;
    /* access modifiers changed from: private */
    public Context mContext;
    private SurfaceTexture mDummySurfaceTexture;
    private SurfaceView mDummySurfaceView;
    /* access modifiers changed from: private */
    public int mFacing;
    /* access modifiers changed from: private */
    public String mFlashMode;
    /* access modifiers changed from: private */
    public String mFocusMode;
    /* access modifiers changed from: private */
    public FrameProcessingRunnable mFrameProcessor;
    /* access modifiers changed from: private */
    public Size mPreviewSize;
    /* access modifiers changed from: private */
    public Thread mProcessingThread;
    /* access modifiers changed from: private */
    public float mRequestedFps;
    /* access modifiers changed from: private */
    public int mRequestedPreviewHeight;
    /* access modifiers changed from: private */
    public int mRequestedPreviewWidth;
    /* access modifiers changed from: private */
    public int mRotation;

    public interface AutoFocusCallback {
        void onAutoFocus(boolean z);
    }

    public interface AutoFocusMoveCallback {
        void onAutoFocusMoving(boolean z);
    }

    public static class Builder {
        private CameraSource mCameraSource = new CameraSource();
        private final Detector<?> mDetector;

        public Builder(Context context, Detector<?> detector) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector != null) {
                this.mDetector = detector;
                this.mCameraSource.mContext = context;
            } else {
                throw new IllegalArgumentException("No detector supplied.");
            }
        }

        public Builder setRequestedFps(float f) {
            if (f > 0.0f) {
                this.mCameraSource.mRequestedFps = f;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid fps: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setFocusMode(String str) {
            this.mCameraSource.mFocusMode = str;
            return this;
        }

        public Builder setFlashMode(String str) {
            this.mCameraSource.mFlashMode = str;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid preview size: ");
                sb.append(i);
                sb.append("x");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            }
            this.mCameraSource.mRequestedPreviewWidth = i;
            this.mCameraSource.mRequestedPreviewHeight = i2;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.mCameraSource.mFacing = i;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid camera: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public CameraSource build() {
            CameraSource cameraSource = this.mCameraSource;
            cameraSource.getClass();
            cameraSource.mFrameProcessor = new FrameProcessingRunnable(this.mDetector);
            return this.mCameraSource;
        }
    }

    private class CameraAutoFocusCallback implements android.hardware.Camera.AutoFocusCallback {
        /* access modifiers changed from: private */
        public AutoFocusCallback mDelegate;

        private CameraAutoFocusCallback() {
        }

        public void onAutoFocus(boolean z, Camera camera) {
            AutoFocusCallback autoFocusCallback = this.mDelegate;
            if (autoFocusCallback != null) {
                autoFocusCallback.onAutoFocus(z);
            }
        }
    }

    @TargetApi(16)
    private class CameraAutoFocusMoveCallback implements android.hardware.Camera.AutoFocusMoveCallback {
        /* access modifiers changed from: private */
        public AutoFocusMoveCallback mDelegate;

        private CameraAutoFocusMoveCallback() {
        }

        public void onAutoFocusMoving(boolean z, Camera camera) {
            AutoFocusMoveCallback autoFocusMoveCallback = this.mDelegate;
            if (autoFocusMoveCallback != null) {
                autoFocusMoveCallback.onAutoFocusMoving(z);
            }
        }
    }

    private class CameraPreviewCallback implements PreviewCallback {
        private CameraPreviewCallback() {
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.mFrameProcessor.setNextFrame(bArr, camera);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface FlashMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface FocusMode {
    }

    private class FrameProcessingRunnable implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean mActive = true;
        private Detector<?> mDetector;
        private final Object mLock = new Object();
        private ByteBuffer mPendingFrameData;
        private int mPendingFrameId = 0;
        private long mPendingTimeMillis;
        private long mStartTimeMillis = SystemClock.elapsedRealtime();

        static {
            Class<CameraSource> cls = CameraSource.class;
        }

        FrameProcessingRunnable(Detector<?> detector) {
            this.mDetector = detector;
        }

        /* access modifiers changed from: 0000 */
        @SuppressLint({"Assert"})
        public void release() {
            this.mDetector.release();
            this.mDetector = null;
        }

        /* access modifiers changed from: 0000 */
        public void setActive(boolean z) {
            synchronized (this.mLock) {
                this.mActive = z;
                this.mLock.notifyAll();
            }
        }

        /* access modifiers changed from: 0000 */
        public void setNextFrame(byte[] bArr, Camera camera) {
            synchronized (this.mLock) {
                if (this.mPendingFrameData != null) {
                    camera.addCallbackBuffer(this.mPendingFrameData.array());
                    this.mPendingFrameData = null;
                }
                if (!CameraSource.this.mBytesToByteBuffer.containsKey(bArr)) {
                    Log.d(CameraSource.TAG, "Skipping frame.  Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.mPendingTimeMillis = SystemClock.elapsedRealtime() - this.mStartTimeMillis;
                this.mPendingFrameId++;
                this.mPendingFrameData = (ByteBuffer) CameraSource.this.mBytesToByteBuffer.get(bArr);
                this.mLock.notifyAll();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r6.mDetector.receiveFrame(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0075, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            android.util.Log.e(com.bitcoin.mwallet.core.qrscan.CameraSource.TAG, "Exception thrown from receiver.", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x008e, code lost:
            com.bitcoin.mwallet.core.qrscan.CameraSource.access$1800(r6.this$0).addCallbackBuffer(r2.array());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x009b, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
            L_0x0000:
                java.lang.Object r0 = r6.mLock
                monitor-enter(r0)
            L_0x0003:
                boolean r1 = r6.mActive     // Catch:{ all -> 0x009c }
                if (r1 == 0) goto L_0x001b
                java.nio.ByteBuffer r1 = r6.mPendingFrameData     // Catch:{ all -> 0x009c }
                if (r1 != 0) goto L_0x001b
                java.lang.Object r1 = r6.mLock     // Catch:{ InterruptedException -> 0x0011 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0011 }
                goto L_0x0003
            L_0x0011:
                r1 = move-exception
                java.lang.String r2 = "OpenCameraSource"
                java.lang.String r3 = "Frame processing loop terminated."
                android.util.Log.d(r2, r3, r1)     // Catch:{ all -> 0x009c }
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                return
            L_0x001b:
                boolean r1 = r6.mActive     // Catch:{ all -> 0x009c }
                if (r1 != 0) goto L_0x0021
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                return
            L_0x0021:
                com.google.android.gms.vision.Frame$Builder r1 = new com.google.android.gms.vision.Frame$Builder     // Catch:{ all -> 0x009c }
                r1.<init>()     // Catch:{ all -> 0x009c }
                java.nio.ByteBuffer r2 = r6.mPendingFrameData     // Catch:{ all -> 0x009c }
                com.bitcoin.mwallet.core.qrscan.CameraSource r3 = com.bitcoin.mwallet.core.qrscan.CameraSource.this     // Catch:{ all -> 0x009c }
                com.google.android.gms.common.images.Size r3 = r3.mPreviewSize     // Catch:{ all -> 0x009c }
                int r3 = r3.getWidth()     // Catch:{ all -> 0x009c }
                com.bitcoin.mwallet.core.qrscan.CameraSource r4 = com.bitcoin.mwallet.core.qrscan.CameraSource.this     // Catch:{ all -> 0x009c }
                com.google.android.gms.common.images.Size r4 = r4.mPreviewSize     // Catch:{ all -> 0x009c }
                int r4 = r4.getHeight()     // Catch:{ all -> 0x009c }
                r5 = 17
                com.google.android.gms.vision.Frame$Builder r1 = r1.setImageData(r2, r3, r4, r5)     // Catch:{ all -> 0x009c }
                int r2 = r6.mPendingFrameId     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setId(r2)     // Catch:{ all -> 0x009c }
                long r2 = r6.mPendingTimeMillis     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setTimestampMillis(r2)     // Catch:{ all -> 0x009c }
                com.bitcoin.mwallet.core.qrscan.CameraSource r2 = com.bitcoin.mwallet.core.qrscan.CameraSource.this     // Catch:{ all -> 0x009c }
                int r2 = r2.mRotation     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setRotation(r2)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame r1 = r1.build()     // Catch:{ all -> 0x009c }
                java.nio.ByteBuffer r2 = r6.mPendingFrameData     // Catch:{ all -> 0x009c }
                r3 = 0
                r6.mPendingFrameData = r3     // Catch:{ all -> 0x009c }
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Detector<?> r0 = r6.mDetector     // Catch:{ Throwable -> 0x0077 }
                r0.receiveFrame(r1)     // Catch:{ Throwable -> 0x0077 }
                com.bitcoin.mwallet.core.qrscan.CameraSource r0 = com.bitcoin.mwallet.core.qrscan.CameraSource.this
                android.hardware.Camera r0 = r0.mCamera
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x0075:
                r0 = move-exception
                goto L_0x008e
            L_0x0077:
                r0 = move-exception
                java.lang.String r1 = "OpenCameraSource"
                java.lang.String r3 = "Exception thrown from receiver."
                android.util.Log.e(r1, r3, r0)     // Catch:{ all -> 0x0075 }
                com.bitcoin.mwallet.core.qrscan.CameraSource r0 = com.bitcoin.mwallet.core.qrscan.CameraSource.this
                android.hardware.Camera r0 = r0.mCamera
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x008e:
                com.bitcoin.mwallet.core.qrscan.CameraSource r1 = com.bitcoin.mwallet.core.qrscan.CameraSource.this
                android.hardware.Camera r1 = r1.mCamera
                byte[] r2 = r2.array()
                r1.addCallbackBuffer(r2)
                throw r0
            L_0x009c:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.qrscan.CameraSource.FrameProcessingRunnable.run():void");
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    private class PictureDoneCallback implements android.hardware.Camera.PictureCallback {
        /* access modifiers changed from: private */
        public PictureCallback mDelegate;

        private PictureDoneCallback() {
        }

        public void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.mDelegate;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.mCameraLock) {
                if (CameraSource.this.mCamera != null) {
                    CameraSource.this.mCamera.startPreview();
                }
            }
        }
    }

    private class PictureStartCallback implements android.hardware.Camera.ShutterCallback {
        /* access modifiers changed from: private */
        public ShutterCallback mDelegate;

        private PictureStartCallback() {
        }

        public void onShutter() {
            ShutterCallback shutterCallback = this.mDelegate;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    public interface ShutterCallback {
        void onShutter();
    }

    private static class SizePair {
        private Size mPicture;
        private Size mPreview;

        public SizePair(Camera.Size size, Camera.Size size2) {
            this.mPreview = new Size(size.width, size.height);
            if (size2 != null) {
                this.mPicture = new Size(size2.width, size2.height);
            }
        }

        public Size previewSize() {
            return this.mPreview;
        }

        public Size pictureSize() {
            return this.mPicture;
        }
    }

    public void release() {
        synchronized (this.mCameraLock) {
            stop();
            this.mFrameProcessor.release();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start() throws IOException {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                return this;
            }
            this.mCamera = createCamera();
            if (VERSION.SDK_INT >= 11) {
                this.mDummySurfaceTexture = new SurfaceTexture(100);
                this.mCamera.setPreviewTexture(this.mDummySurfaceTexture);
            } else {
                this.mDummySurfaceView = new SurfaceView(this.mContext);
                this.mCamera.setPreviewDisplay(this.mDummySurfaceView.getHolder());
            }
            this.mCamera.startPreview();
            this.mProcessingThread = new Thread(this.mFrameProcessor);
            this.mFrameProcessor.setActive(true);
            this.mProcessingThread.start();
            return this;
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                return this;
            }
            this.mCamera = createCamera();
            this.mCamera.setPreviewDisplay(surfaceHolder);
            this.mCamera.startPreview();
            this.mProcessingThread = new Thread(this.mFrameProcessor);
            this.mFrameProcessor.setActive(true);
            this.mProcessingThread.start();
            return this;
        }
    }

    public void stop() {
        synchronized (this.mCameraLock) {
            this.mFrameProcessor.setActive(false);
            if (this.mProcessingThread != null) {
                try {
                    this.mProcessingThread.join();
                } catch (InterruptedException unused) {
                    Log.d(TAG, "Frame processing thread interrupted on release.");
                }
                this.mProcessingThread = null;
            }
            this.mBytesToByteBuffer.clear();
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
                this.mCamera.setPreviewCallbackWithBuffer(null);
                try {
                    if (VERSION.SDK_INT >= 11) {
                        this.mCamera.setPreviewTexture(null);
                    } else {
                        this.mCamera.setPreviewDisplay(null);
                    }
                } catch (Exception e) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to clear camera preview: ");
                    sb.append(e);
                    Log.e(str, sb.toString());
                }
                this.mCamera.release();
                this.mCamera = null;
            }
        }
    }

    public Size getPreviewSize() {
        return this.mPreviewSize;
    }

    public int getCameraFacing() {
        return this.mFacing;
    }

    public int doZoom(float f) {
        synchronized (this.mCameraLock) {
            if (this.mCamera == null) {
                return 0;
            }
            Parameters parameters = this.mCamera.getParameters();
            if (!parameters.isZoomSupported()) {
                Log.w(TAG, "Zoom is not supported on this device");
                return 0;
            }
            int maxZoom = parameters.getMaxZoom();
            int zoom = parameters.getZoom() + 1;
            int round = Math.round(f > 1.0f ? ((float) zoom) + (f * ((float) (maxZoom / 10))) : ((float) zoom) * f) - 1;
            if (round < 0) {
                round = 0;
            } else if (round > maxZoom) {
                round = maxZoom;
            }
            parameters.setZoom(round);
            this.mCamera.setParameters(parameters);
            return round;
        }
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                PictureStartCallback pictureStartCallback = new PictureStartCallback();
                pictureStartCallback.mDelegate = shutterCallback;
                PictureDoneCallback pictureDoneCallback = new PictureDoneCallback();
                pictureDoneCallback.mDelegate = pictureCallback;
                this.mCamera.takePicture(pictureStartCallback, null, null, pictureDoneCallback);
            }
        }
    }

    @Nullable
    public String getFocusMode() {
        return this.mFocusMode;
    }

    public boolean setFocusMode(String str) {
        synchronized (this.mCameraLock) {
            if (!(this.mCamera == null || str == null)) {
                Parameters parameters = this.mCamera.getParameters();
                if (parameters.getSupportedFocusModes().contains(str)) {
                    parameters.setFocusMode(str);
                    this.mCamera.setParameters(parameters);
                    this.mFocusMode = str;
                    return true;
                }
            }
            return false;
        }
    }

    @Nullable
    public String getFlashMode() {
        return this.mFlashMode;
    }

    public boolean setFlashMode(String str) {
        synchronized (this.mCameraLock) {
            if (!(this.mCamera == null || str == null)) {
                Parameters parameters = this.mCamera.getParameters();
                if (parameters.getSupportedFlashModes().contains(str)) {
                    parameters.setFlashMode(str);
                    this.mCamera.setParameters(parameters);
                    this.mFlashMode = str;
                    return true;
                }
            }
            return false;
        }
    }

    public void autoFocus(@Nullable AutoFocusCallback autoFocusCallback) {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                CameraAutoFocusCallback cameraAutoFocusCallback = null;
                if (autoFocusCallback != null) {
                    CameraAutoFocusCallback cameraAutoFocusCallback2 = new CameraAutoFocusCallback();
                    cameraAutoFocusCallback2.mDelegate = autoFocusCallback;
                    cameraAutoFocusCallback = cameraAutoFocusCallback2;
                }
                this.mCamera.autoFocus(cameraAutoFocusCallback);
            }
        }
    }

    public void cancelAutoFocus() {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                this.mCamera.cancelAutoFocus();
            }
        }
    }

    @TargetApi(16)
    public boolean setAutoFocusMoveCallback(@Nullable AutoFocusMoveCallback autoFocusMoveCallback) {
        if (VERSION.SDK_INT < 16) {
            return false;
        }
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                CameraAutoFocusMoveCallback cameraAutoFocusMoveCallback = null;
                if (autoFocusMoveCallback != null) {
                    CameraAutoFocusMoveCallback cameraAutoFocusMoveCallback2 = new CameraAutoFocusMoveCallback();
                    cameraAutoFocusMoveCallback2.mDelegate = autoFocusMoveCallback;
                    cameraAutoFocusMoveCallback = cameraAutoFocusMoveCallback2;
                }
                this.mCamera.setAutoFocusMoveCallback(cameraAutoFocusMoveCallback);
            }
        }
        return true;
    }

    private CameraSource() {
        this.mCameraLock = new Object();
        this.mFacing = 0;
        this.mRequestedFps = 30.0f;
        this.mRequestedPreviewWidth = 1024;
        this.mRequestedPreviewHeight = 768;
        this.mFocusMode = null;
        this.mFlashMode = null;
        this.mBytesToByteBuffer = new HashMap();
    }

    @SuppressLint({"InlinedApi"})
    private Camera createCamera() {
        String str = TAG;
        int idForRequestedCamera = getIdForRequestedCamera(this.mFacing);
        if (idForRequestedCamera != -1) {
            try {
                Camera open = Camera.open(idForRequestedCamera);
                SizePair selectSizePair = selectSizePair(open, this.mRequestedPreviewWidth, this.mRequestedPreviewHeight);
                if (selectSizePair != null) {
                    Size pictureSize = selectSizePair.pictureSize();
                    this.mPreviewSize = selectSizePair.previewSize();
                    int[] selectPreviewFpsRange = selectPreviewFpsRange(open, this.mRequestedFps);
                    if (selectPreviewFpsRange != null) {
                        Parameters parameters = open.getParameters();
                        if (pictureSize != null) {
                            parameters.setPictureSize(pictureSize.getWidth(), pictureSize.getHeight());
                        }
                        parameters.setPreviewSize(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight());
                        parameters.setPreviewFpsRange(selectPreviewFpsRange[0], selectPreviewFpsRange[1]);
                        parameters.setPreviewFormat(17);
                        setRotation(open, parameters, idForRequestedCamera);
                        String str2 = " is not supported on this device.";
                        if (this.mFocusMode != null) {
                            if (parameters.getSupportedFocusModes().contains(this.mFocusMode)) {
                                parameters.setFocusMode(this.mFocusMode);
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Camera focus mode: ");
                                sb.append(this.mFocusMode);
                                sb.append(str2);
                                Log.i(str, sb.toString());
                            }
                        }
                        this.mFocusMode = parameters.getFocusMode();
                        if (!(this.mFlashMode == null || parameters.getSupportedFlashModes() == null)) {
                            if (parameters.getSupportedFlashModes().contains(this.mFlashMode)) {
                                parameters.setFlashMode(this.mFlashMode);
                            } else {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Camera flash mode: ");
                                sb2.append(this.mFlashMode);
                                sb2.append(str2);
                                Log.i(str, sb2.toString());
                            }
                        }
                        this.mFlashMode = parameters.getFlashMode();
                        open.setParameters(parameters);
                        open.setPreviewCallbackWithBuffer(new CameraPreviewCallback());
                        open.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
                        open.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
                        open.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
                        open.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
                        return open;
                    }
                    throw new RuntimeException("Could not find suitable preview frames per second range.");
                }
                throw new RuntimeException("Could not find suitable preview size.");
            } catch (RuntimeException e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Camera.open() caused exception. ");
                sb3.append(e.toString());
                Log.d(str, sb3.toString());
                throw e;
            }
        } else {
            throw new RuntimeException("Could not find requested camera.");
        }
    }

    private static int getIdForRequestedCamera(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return i2;
            }
        }
        return -1;
    }

    private static SizePair selectSizePair(Camera camera, int i, int i2) {
        SizePair sizePair = null;
        int i3 = Integer.MAX_VALUE;
        for (SizePair sizePair2 : generateValidPreviewSizeList(camera)) {
            Size previewSize = sizePair2.previewSize();
            int abs = Math.abs(previewSize.getWidth() - i) + Math.abs(previewSize.getHeight() - i2);
            if (abs < i3) {
                sizePair = sizePair2;
                i3 = abs;
            }
        }
        return sizePair;
    }

    private static List<SizePair> generateValidPreviewSizeList(Camera camera) {
        Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            float f = ((float) size.width) / ((float) size.height);
            Iterator it = supportedPictureSizes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Camera.Size size2 = (Camera.Size) it.next();
                if (Math.abs(f - (((float) size2.width) / ((float) size2.height))) < ASPECT_RATIO_TOLERANCE) {
                    arrayList.add(new SizePair(size, size2));
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            Log.w(TAG, "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size sizePair : supportedPreviewSizes) {
                arrayList.add(new SizePair(sizePair, null));
            }
        }
        return arrayList;
    }

    private int[] selectPreviewFpsRange(Camera camera, float f) {
        int i = (int) (f * 1000.0f);
        int[] iArr = null;
        int i2 = Integer.MAX_VALUE;
        for (int[] iArr2 : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i - iArr2[0]) + Math.abs(i - iArr2[1]);
            if (abs < i2) {
                iArr = iArr2;
                i2 = abs;
            }
        }
        return iArr;
    }

    private void setRotation(Camera camera, Parameters parameters, int i) {
        int i2;
        int i3;
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        int i4 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i4 = 90;
            } else if (rotation == 2) {
                i4 = 180;
            } else if (rotation != 3) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bad rotation value: ");
                sb.append(rotation);
                Log.e(TAG, sb.toString());
            } else {
                i4 = 270;
            }
        }
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i2 = (cameraInfo.orientation + i4) % 360;
            i3 = (360 - i2) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i4) + 360) % 360;
            i3 = i2;
        }
        this.mRotation = i2 / 90;
        camera.setDisplayOrientation(i3);
        parameters.setRotation(i2);
    }

    private byte[] createPreviewBuffer(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((long) ((size.getHeight() * size.getWidth()) * ImageFormat.getBitsPerPixel(17)))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.mBytesToByteBuffer.put(bArr, wrap);
        return bArr;
    }
}
