package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.client.android.AmbientLightManager;
import com.google.zxing.client.android.camera.CameraConfigurationUtils;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.SourceData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.DebugKt;

public final class CameraManager {
    /* access modifiers changed from: private */
    public static final String TAG = "CameraManager";
    private AmbientLightManager ambientLightManager;
    private AutoFocusManager autoFocusManager;
    private Camera camera;
    private CameraInfo cameraInfo;
    private final CameraPreviewCallback cameraPreviewCallback;
    private Context context;
    private String defaultParameters;
    private DisplayConfiguration displayConfiguration;
    private Size previewSize;
    private boolean previewing;
    private Size requestedPreviewSize;
    private int rotationDegrees = -1;
    private CameraSettings settings = new CameraSettings();

    private final class CameraPreviewCallback implements PreviewCallback {
        private PreviewCallback callback;
        private Size resolution;

        public CameraPreviewCallback() {
        }

        public void setResolution(Size size) {
            this.resolution = size;
        }

        public void setCallback(PreviewCallback previewCallback) {
            this.callback = previewCallback;
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            Size size = this.resolution;
            PreviewCallback previewCallback = this.callback;
            if (size == null || previewCallback == null) {
                Log.d(CameraManager.TAG, "Got preview callback, but no handler or resolution available");
                if (previewCallback != null) {
                    previewCallback.onPreviewError(new Exception("No resolution available"));
                }
            } else if (bArr != null) {
                try {
                    byte[] bArr2 = bArr;
                    SourceData sourceData = new SourceData(bArr2, size.width, size.height, camera.getParameters().getPreviewFormat(), CameraManager.this.getCameraRotation());
                    previewCallback.onPreview(sourceData);
                } catch (RuntimeException e) {
                    Log.e(CameraManager.TAG, "Camera preview failed", e);
                    previewCallback.onPreviewError(e);
                }
            } else {
                throw new NullPointerException("No preview data received");
            }
        }
    }

    public CameraManager(Context context2) {
        this.context = context2;
        this.cameraPreviewCallback = new CameraPreviewCallback();
    }

    public void open() {
        this.camera = OpenCameraInterface.open(this.settings.getRequestedCameraId());
        if (this.camera != null) {
            int cameraId = OpenCameraInterface.getCameraId(this.settings.getRequestedCameraId());
            this.cameraInfo = new CameraInfo();
            Camera.getCameraInfo(cameraId, this.cameraInfo);
            return;
        }
        throw new RuntimeException("Failed to open camera");
    }

    public void configure() {
        if (this.camera != null) {
            setParameters();
            return;
        }
        throw new RuntimeException("Camera not open");
    }

    public void setPreviewDisplay(SurfaceHolder surfaceHolder) throws IOException {
        setPreviewDisplay(new CameraSurface(surfaceHolder));
    }

    public void setPreviewDisplay(CameraSurface cameraSurface) throws IOException {
        cameraSurface.setPreview(this.camera);
    }

    public void startPreview() {
        Camera camera2 = this.camera;
        if (camera2 != null && !this.previewing) {
            camera2.startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.camera, this.settings);
            this.ambientLightManager = new AmbientLightManager(this.context, this, this.settings);
            this.ambientLightManager.start();
        }
    }

    public void stopPreview() {
        AutoFocusManager autoFocusManager2 = this.autoFocusManager;
        if (autoFocusManager2 != null) {
            autoFocusManager2.stop();
            this.autoFocusManager = null;
        }
        AmbientLightManager ambientLightManager2 = this.ambientLightManager;
        if (ambientLightManager2 != null) {
            ambientLightManager2.stop();
            this.ambientLightManager = null;
        }
        Camera camera2 = this.camera;
        if (camera2 != null && this.previewing) {
            camera2.stopPreview();
            this.cameraPreviewCallback.setCallback(null);
            this.previewing = false;
        }
    }

    public void close() {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            camera2.release();
            this.camera = null;
        }
    }

    public boolean isCameraRotated() {
        int i = this.rotationDegrees;
        if (i != -1) {
            return i % 180 != 0;
        }
        throw new IllegalStateException("Rotation not calculated yet. Call configure() first.");
    }

    public int getCameraRotation() {
        return this.rotationDegrees;
    }

    private Parameters getDefaultCameraParameters() {
        Parameters parameters = this.camera.getParameters();
        String str = this.defaultParameters;
        if (str == null) {
            this.defaultParameters = parameters.flatten();
        } else {
            parameters.unflatten(str);
        }
        return parameters;
    }

    private void setDesiredParameters(boolean z) {
        Parameters defaultCameraParameters = getDefaultCameraParameters();
        if (defaultCameraParameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Initial camera parameters: ");
        sb.append(defaultCameraParameters.flatten());
        Log.i(str, sb.toString());
        if (z) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        CameraConfigurationUtils.setFocus(defaultCameraParameters, this.settings.getFocusMode(), z);
        if (!z) {
            CameraConfigurationUtils.setTorch(defaultCameraParameters, false);
            if (this.settings.isScanInverted()) {
                CameraConfigurationUtils.setInvertColor(defaultCameraParameters);
            }
            if (this.settings.isBarcodeSceneModeEnabled()) {
                CameraConfigurationUtils.setBarcodeSceneMode(defaultCameraParameters);
            }
            if (this.settings.isMeteringEnabled() && VERSION.SDK_INT >= 15) {
                CameraConfigurationUtils.setVideoStabilization(defaultCameraParameters);
                CameraConfigurationUtils.setFocusArea(defaultCameraParameters);
                CameraConfigurationUtils.setMetering(defaultCameraParameters);
            }
        }
        List previewSizes = getPreviewSizes(defaultCameraParameters);
        if (previewSizes.size() == 0) {
            this.requestedPreviewSize = null;
        } else {
            this.requestedPreviewSize = this.displayConfiguration.getBestPreviewSize(previewSizes, isCameraRotated());
            defaultCameraParameters.setPreviewSize(this.requestedPreviewSize.width, this.requestedPreviewSize.height);
        }
        if (Build.DEVICE.equals("glass-1")) {
            CameraConfigurationUtils.setBestPreviewFPS(defaultCameraParameters);
        }
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Final camera parameters: ");
        sb2.append(defaultCameraParameters.flatten());
        Log.i(str2, sb2.toString());
        this.camera.setParameters(defaultCameraParameters);
    }

    private static List<Size> getPreviewSizes(Parameters parameters) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        ArrayList arrayList = new ArrayList();
        if (supportedPreviewSizes == null) {
            Camera.Size previewSize2 = parameters.getPreviewSize();
            if (previewSize2 != null) {
                arrayList.add(new Size(previewSize2.width, previewSize2.height));
            }
            return arrayList;
        }
        for (Camera.Size size : supportedPreviewSizes) {
            arrayList.add(new Size(size.width, size.height));
        }
        return arrayList;
    }

    private int calculateDisplayRotation() {
        int i;
        int rotation = this.displayConfiguration.getRotation();
        int i2 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i2 = 90;
            } else if (rotation == 2) {
                i2 = 180;
            } else if (rotation == 3) {
                i2 = 270;
            }
        }
        if (this.cameraInfo.facing == 1) {
            i = (360 - ((this.cameraInfo.orientation + i2) % 360)) % 360;
        } else {
            i = ((this.cameraInfo.orientation - i2) + 360) % 360;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Camera Display Orientation: ");
        sb.append(i);
        Log.i(str, sb.toString());
        return i;
    }

    private void setCameraDisplayOrientation(int i) {
        this.camera.setDisplayOrientation(i);
    }

    private void setParameters() {
        try {
            this.rotationDegrees = calculateDisplayRotation();
            setCameraDisplayOrientation(this.rotationDegrees);
        } catch (Exception unused) {
            Log.w(TAG, "Failed to set rotation.");
        }
        try {
            setDesiredParameters(false);
        } catch (Exception unused2) {
            try {
                setDesiredParameters(true);
            } catch (Exception unused3) {
                Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
            }
        }
        Camera.Size previewSize2 = this.camera.getParameters().getPreviewSize();
        if (previewSize2 == null) {
            this.previewSize = this.requestedPreviewSize;
        } else {
            this.previewSize = new Size(previewSize2.width, previewSize2.height);
        }
        this.cameraPreviewCallback.setResolution(this.previewSize);
    }

    public boolean isOpen() {
        return this.camera != null;
    }

    public Size getNaturalPreviewSize() {
        return this.previewSize;
    }

    public Size getPreviewSize() {
        if (this.previewSize == null) {
            return null;
        }
        if (isCameraRotated()) {
            return this.previewSize.rotate();
        }
        return this.previewSize;
    }

    public void requestPreviewFrame(PreviewCallback previewCallback) {
        Camera camera2 = this.camera;
        if (camera2 != null && this.previewing) {
            this.cameraPreviewCallback.setCallback(previewCallback);
            camera2.setOneShotPreviewCallback(this.cameraPreviewCallback);
        }
    }

    public CameraSettings getCameraSettings() {
        return this.settings;
    }

    public void setCameraSettings(CameraSettings cameraSettings) {
        this.settings = cameraSettings;
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return this.displayConfiguration;
    }

    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration2) {
        this.displayConfiguration = displayConfiguration2;
    }

    public void setTorch(boolean z) {
        if (this.camera != null) {
            try {
                if (z != isTorchOn()) {
                    if (this.autoFocusManager != null) {
                        this.autoFocusManager.stop();
                    }
                    Parameters parameters = this.camera.getParameters();
                    CameraConfigurationUtils.setTorch(parameters, z);
                    if (this.settings.isExposureEnabled()) {
                        CameraConfigurationUtils.setBestExposure(parameters, z);
                    }
                    this.camera.setParameters(parameters);
                    if (this.autoFocusManager != null) {
                        this.autoFocusManager.start();
                    }
                }
            } catch (RuntimeException e) {
                Log.e(TAG, "Failed to set torch", e);
            }
        }
    }

    public void changeCameraParameters(CameraParametersCallback cameraParametersCallback) {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            try {
                camera2.setParameters(cameraParametersCallback.changeCameraParameters(camera2.getParameters()));
            } catch (RuntimeException e) {
                Log.e(TAG, "Failed to change camera parameters", e);
            }
        }
    }

    public boolean isTorchOn() {
        Parameters parameters = this.camera.getParameters();
        if (parameters == null) {
            return false;
        }
        String flashMode = parameters.getFlashMode();
        if (flashMode == null) {
            return false;
        }
        if (DebugKt.DEBUG_PROPERTY_VALUE_ON.equals(flashMode) || "torch".equals(flashMode)) {
            return true;
        }
        return false;
    }

    public Camera getCamera() {
        return this.camera;
    }
}
