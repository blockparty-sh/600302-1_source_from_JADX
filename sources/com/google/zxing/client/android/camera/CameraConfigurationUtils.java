package com.google.zxing.client.android.camera;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.journeyapps.barcodescanner.camera.CameraSettings.FocusMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import kotlinx.coroutines.DebugKt;

public final class CameraConfigurationUtils {
    private static final int AREA_PER_1000 = 400;
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final int MAX_FPS = 20;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final int MIN_FPS = 10;
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final String TAG = "CameraConfiguration";

    private CameraConfigurationUtils() {
    }

    public static void setFocus(Parameters parameters, FocusMode focusMode, boolean z) {
        List supportedFocusModes = parameters.getSupportedFocusModes();
        String str = "macro";
        String str2 = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
        String str3 = "focus mode";
        String str4 = (z || focusMode == FocusMode.AUTO) ? findSettableValue(str3, supportedFocusModes, str2) : focusMode == FocusMode.CONTINUOUS ? findSettableValue(str3, supportedFocusModes, "continuous-picture", "continuous-video", str2) : focusMode == FocusMode.INFINITY ? findSettableValue(str3, supportedFocusModes, "infinity") : focusMode == FocusMode.MACRO ? findSettableValue(str3, supportedFocusModes, str) : null;
        if (!z && str4 == null) {
            str4 = findSettableValue(str3, supportedFocusModes, str, "edof");
        }
        if (str4 == null) {
            return;
        }
        if (str4.equals(parameters.getFocusMode())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Focus mode already set to ");
            sb.append(str4);
            Log.i(TAG, sb.toString());
            return;
        }
        parameters.setFocusMode(str4);
    }

    public static void setTorch(Parameters parameters, boolean z) {
        String str;
        List supportedFlashModes = parameters.getSupportedFlashModes();
        String str2 = "flash mode";
        if (z) {
            str = findSettableValue(str2, supportedFlashModes, "torch", DebugKt.DEBUG_PROPERTY_VALUE_ON);
        } else {
            str = findSettableValue(str2, supportedFlashModes, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        }
        if (str != null) {
            boolean equals = str.equals(parameters.getFlashMode());
            String str3 = TAG;
            if (equals) {
                StringBuilder sb = new StringBuilder();
                sb.append("Flash mode already set to ");
                sb.append(str);
                Log.i(str3, sb.toString());
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Setting flash mode to ");
            sb2.append(str);
            Log.i(str3, sb2.toString());
            parameters.setFlashMode(str);
        }
    }

    public static void setBestExposure(Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        String str = TAG;
        if (!(minExposureCompensation == 0 && maxExposureCompensation == 0)) {
            float f = 0.0f;
            if (exposureCompensationStep > 0.0f) {
                if (!z) {
                    f = MAX_EXPOSURE_COMPENSATION;
                }
                int round = Math.round(f / exposureCompensationStep);
                float f2 = exposureCompensationStep * ((float) round);
                int max = Math.max(Math.min(round, maxExposureCompensation), minExposureCompensation);
                String str2 = " / ";
                if (parameters.getExposureCompensation() == max) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Exposure compensation already set to ");
                    sb.append(max);
                    sb.append(str2);
                    sb.append(f2);
                    Log.i(str, sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Setting exposure compensation to ");
                sb2.append(max);
                sb2.append(str2);
                sb2.append(f2);
                Log.i(str, sb2.toString());
                parameters.setExposureCompensation(max);
                return;
            }
        }
        Log.i(str, "Camera does not support exposure compensation");
    }

    public static void setBestPreviewFPS(Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setBestPreviewFPS(Parameters parameters, int i, int i2) {
        List supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        StringBuilder sb = new StringBuilder();
        sb.append("Supported FPS ranges: ");
        sb.append(toString((Collection<int[]>) supportedPreviewFpsRange));
        String sb2 = sb.toString();
        String str = TAG;
        Log.i(str, sb2);
        if (supportedPreviewFpsRange != null && !supportedPreviewFpsRange.isEmpty()) {
            int[] iArr = null;
            Iterator it = supportedPreviewFpsRange.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int[] iArr2 = (int[]) it.next();
                int i3 = iArr2[0];
                int i4 = iArr2[1];
                if (i3 >= i * 1000 && i4 <= i2 * 1000) {
                    iArr = iArr2;
                    break;
                }
            }
            if (iArr == null) {
                Log.i(str, "No suitable FPS range?");
                return;
            }
            int[] iArr3 = new int[2];
            parameters.getPreviewFpsRange(iArr3);
            if (Arrays.equals(iArr3, iArr)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("FPS range already set to ");
                sb3.append(Arrays.toString(iArr));
                Log.i(str, sb3.toString());
                return;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Setting FPS range to ");
            sb4.append(Arrays.toString(iArr));
            Log.i(str, sb4.toString());
            parameters.setPreviewFpsRange(iArr[0], iArr[1]);
        }
    }

    @TargetApi(15)
    public static void setFocusArea(Parameters parameters) {
        int maxNumFocusAreas = parameters.getMaxNumFocusAreas();
        String str = TAG;
        if (maxNumFocusAreas > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Old focus areas: ");
            sb.append(toString((Iterable<Area>) parameters.getFocusAreas()));
            Log.i(str, sb.toString());
            List buildMiddleArea = buildMiddleArea(AREA_PER_1000);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Setting focus area to : ");
            sb2.append(toString((Iterable<Area>) buildMiddleArea));
            Log.i(str, sb2.toString());
            parameters.setFocusAreas(buildMiddleArea);
            return;
        }
        Log.i(str, "Device does not support focus areas");
    }

    @TargetApi(15)
    public static void setMetering(Parameters parameters) {
        int maxNumMeteringAreas = parameters.getMaxNumMeteringAreas();
        String str = TAG;
        if (maxNumMeteringAreas > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Old metering areas: ");
            sb.append(parameters.getMeteringAreas());
            Log.i(str, sb.toString());
            List buildMiddleArea = buildMiddleArea(AREA_PER_1000);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Setting metering area to : ");
            sb2.append(toString((Iterable<Area>) buildMiddleArea));
            Log.i(str, sb2.toString());
            parameters.setMeteringAreas(buildMiddleArea);
            return;
        }
        Log.i(str, "Device does not support metering areas");
    }

    @TargetApi(15)
    private static List<Area> buildMiddleArea(int i) {
        int i2 = -i;
        return Collections.singletonList(new Area(new Rect(i2, i2, i, i), 1));
    }

    @TargetApi(15)
    public static void setVideoStabilization(Parameters parameters) {
        boolean isVideoStabilizationSupported = parameters.isVideoStabilizationSupported();
        String str = TAG;
        if (!isVideoStabilizationSupported) {
            Log.i(str, "This device does not support video stabilization");
        } else if (parameters.getVideoStabilization()) {
            Log.i(str, "Video stabilization already enabled");
        } else {
            Log.i(str, "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
        }
    }

    public static void setBarcodeSceneMode(Parameters parameters) {
        String str = "barcode";
        if (str.equals(parameters.getSceneMode())) {
            Log.i(TAG, "Barcode scene mode already set");
            return;
        }
        String findSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), str);
        if (findSettableValue != null) {
            parameters.setSceneMode(findSettableValue);
        }
    }

    public static void setZoom(Parameters parameters, double d) {
        boolean isZoomSupported = parameters.isZoomSupported();
        String str = TAG;
        if (isZoomSupported) {
            Integer indexOfClosestZoom = indexOfClosestZoom(parameters, d);
            if (indexOfClosestZoom != null) {
                if (parameters.getZoom() == indexOfClosestZoom.intValue()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Zoom is already set to ");
                    sb.append(indexOfClosestZoom);
                    Log.i(str, sb.toString());
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Setting zoom to ");
                    sb2.append(indexOfClosestZoom);
                    Log.i(str, sb2.toString());
                    parameters.setZoom(indexOfClosestZoom.intValue());
                }
            }
        } else {
            Log.i(str, "Zoom is not supported");
        }
    }

    private static Integer indexOfClosestZoom(Parameters parameters, double d) {
        List zoomRatios = parameters.getZoomRatios();
        StringBuilder sb = new StringBuilder();
        sb.append("Zoom ratios: ");
        sb.append(zoomRatios);
        String sb2 = sb.toString();
        String str = TAG;
        Log.i(str, sb2);
        int maxZoom = parameters.getMaxZoom();
        if (zoomRatios == null || zoomRatios.isEmpty() || zoomRatios.size() != maxZoom + 1) {
            Log.w(str, "Invalid zoom ratios!");
            return null;
        }
        double d2 = d * 100.0d;
        double d3 = Double.POSITIVE_INFINITY;
        int i = 0;
        for (int i2 = 0; i2 < zoomRatios.size(); i2++) {
            double abs = Math.abs(((double) ((Integer) zoomRatios.get(i2)).intValue()) - d2);
            if (abs < d3) {
                i = i2;
                d3 = abs;
            }
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Chose zoom ratio of ");
        sb3.append(((double) ((Integer) zoomRatios.get(i)).intValue()) / 100.0d);
        Log.i(str, sb3.toString());
        return Integer.valueOf(i);
    }

    public static void setInvertColor(Parameters parameters) {
        String str = "negative";
        if (str.equals(parameters.getColorEffect())) {
            Log.i(TAG, "Negative effect already set");
            return;
        }
        String findSettableValue = findSettableValue("color effect", parameters.getSupportedColorEffects(), str);
        if (findSettableValue != null) {
            parameters.setColorEffect(findSettableValue);
        }
    }

    private static String findSettableValue(String str, Collection<String> collection, String... strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("Requesting ");
        sb.append(str);
        sb.append(" value from among: ");
        sb.append(Arrays.toString(strArr));
        String sb2 = sb.toString();
        String str2 = TAG;
        Log.i(str2, sb2);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Supported ");
        sb3.append(str);
        sb3.append(" values: ");
        sb3.append(collection);
        Log.i(str2, sb3.toString());
        if (collection != null) {
            for (String str3 : strArr) {
                if (collection.contains(str3)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Can set ");
                    sb4.append(str);
                    sb4.append(" to: ");
                    sb4.append(str3);
                    Log.i(str2, sb4.toString());
                    return str3;
                }
            }
        }
        Log.i(str2, "No supported values match");
        return null;
    }

    private static String toString(Collection<int[]> collection) {
        if (collection == null || collection.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            sb.append(Arrays.toString((int[]) it.next()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @TargetApi(15)
    private static String toString(Iterable<Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Area area : iterable) {
            sb.append(area.rect);
            sb.append(':');
            sb.append(area.weight);
            sb.append(' ');
        }
        return sb.toString();
    }

    public static String collectStats(Parameters parameters) {
        return collectStats((CharSequence) parameters.flatten());
    }

    public static String collectStats(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("BOARD=");
        sb.append(Build.BOARD);
        sb.append(10);
        sb.append("BRAND=");
        sb.append(Build.BRAND);
        sb.append(10);
        sb.append("CPU_ABI=");
        sb.append(Build.CPU_ABI);
        sb.append(10);
        sb.append("DEVICE=");
        sb.append(Build.DEVICE);
        sb.append(10);
        sb.append("DISPLAY=");
        sb.append(Build.DISPLAY);
        sb.append(10);
        sb.append("FINGERPRINT=");
        sb.append(Build.FINGERPRINT);
        sb.append(10);
        sb.append("HOST=");
        sb.append(Build.HOST);
        sb.append(10);
        sb.append("ID=");
        sb.append(Build.ID);
        sb.append(10);
        sb.append("MANUFACTURER=");
        sb.append(Build.MANUFACTURER);
        sb.append(10);
        sb.append("MODEL=");
        sb.append(Build.MODEL);
        sb.append(10);
        sb.append("PRODUCT=");
        sb.append(Build.PRODUCT);
        sb.append(10);
        sb.append("TAGS=");
        sb.append(Build.TAGS);
        sb.append(10);
        sb.append("TIME=");
        sb.append(Build.TIME);
        sb.append(10);
        sb.append("TYPE=");
        sb.append(Build.TYPE);
        sb.append(10);
        sb.append("USER=");
        sb.append(Build.USER);
        sb.append(10);
        sb.append("VERSION.CODENAME=");
        sb.append(VERSION.CODENAME);
        sb.append(10);
        sb.append("VERSION.INCREMENTAL=");
        sb.append(VERSION.INCREMENTAL);
        sb.append(10);
        sb.append("VERSION.RELEASE=");
        sb.append(VERSION.RELEASE);
        sb.append(10);
        sb.append("VERSION.SDK_INT=");
        sb.append(VERSION.SDK_INT);
        sb.append(10);
        if (charSequence != null) {
            String[] split = SEMICOLON.split(charSequence);
            Arrays.sort(split);
            for (String append : split) {
                sb.append(append);
                sb.append(10);
            }
        }
        return sb.toString();
    }
}
