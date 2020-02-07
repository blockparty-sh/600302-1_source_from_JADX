package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import kotlinx.coroutines.DebugKt;

public final class AutoFocusManager {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF = new ArrayList(2);
    private static final String TAG = "AutoFocusManager";
    /* access modifiers changed from: private */
    public int MESSAGE_FOCUS = 1;
    private final AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {
        public void onAutoFocus(boolean z, Camera camera) {
            AutoFocusManager.this.handler.post(new Runnable() {
                public void run() {
                    AutoFocusManager.this.focusing = false;
                    AutoFocusManager.this.autoFocusAgainLater();
                }
            });
        }
    };
    private final Camera camera;
    private final Callback focusHandlerCallback = new Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != AutoFocusManager.this.MESSAGE_FOCUS) {
                return false;
            }
            AutoFocusManager.this.focus();
            return true;
        }
    };
    /* access modifiers changed from: private */
    public boolean focusing;
    /* access modifiers changed from: private */
    public Handler handler = new Handler(this.focusHandlerCallback);
    private boolean stopped;
    private final boolean useAutoFocus;

    static {
        FOCUS_MODES_CALLING_AF.add(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
        FOCUS_MODES_CALLING_AF.add("macro");
    }

    public AutoFocusManager(Camera camera2, CameraSettings cameraSettings) {
        boolean z = true;
        this.camera = camera2;
        String focusMode = camera2.getParameters().getFocusMode();
        if (!cameraSettings.isAutoFocusEnabled() || !FOCUS_MODES_CALLING_AF.contains(focusMode)) {
            z = false;
        }
        this.useAutoFocus = z;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Current focus mode '");
        sb.append(focusMode);
        sb.append("'; use auto focus? ");
        sb.append(this.useAutoFocus);
        Log.i(str, sb.toString());
        start();
    }

    /* access modifiers changed from: private */
    public synchronized void autoFocusAgainLater() {
        if (!this.stopped && !this.handler.hasMessages(this.MESSAGE_FOCUS)) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(this.MESSAGE_FOCUS), 2000);
        }
    }

    public void start() {
        this.stopped = false;
        focus();
    }

    /* access modifiers changed from: private */
    public void focus() {
        if (this.useAutoFocus && !this.stopped && !this.focusing) {
            try {
                this.camera.autoFocus(this.autoFocusCallback);
                this.focusing = true;
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while focusing", e);
                autoFocusAgainLater();
            }
        }
    }

    private void cancelOutstandingTask() {
        this.handler.removeMessages(this.MESSAGE_FOCUS);
    }

    public void stop() {
        this.stopped = true;
        this.focusing = false;
        cancelOutstandingTask();
        if (this.useAutoFocus) {
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
    }
}
