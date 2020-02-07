package com.journeyapps.barcodescanner;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;

public class RotationListener {
    /* access modifiers changed from: private */
    public RotationCallback callback;
    /* access modifiers changed from: private */
    public int lastRotation;
    private OrientationEventListener orientationEventListener;
    /* access modifiers changed from: private */
    public WindowManager windowManager;

    public void listen(Context context, RotationCallback rotationCallback) {
        stop();
        Context applicationContext = context.getApplicationContext();
        this.callback = rotationCallback;
        this.windowManager = (WindowManager) applicationContext.getSystemService("window");
        this.orientationEventListener = new OrientationEventListener(applicationContext, 3) {
            public void onOrientationChanged(int i) {
                WindowManager access$000 = RotationListener.this.windowManager;
                RotationCallback access$100 = RotationListener.this.callback;
                if (RotationListener.this.windowManager != null && access$100 != null) {
                    int rotation = access$000.getDefaultDisplay().getRotation();
                    if (rotation != RotationListener.this.lastRotation) {
                        RotationListener.this.lastRotation = rotation;
                        access$100.onRotationChanged(rotation);
                    }
                }
            }
        };
        this.orientationEventListener.enable();
        this.lastRotation = this.windowManager.getDefaultDisplay().getRotation();
    }

    public void stop() {
        OrientationEventListener orientationEventListener2 = this.orientationEventListener;
        if (orientationEventListener2 != null) {
            orientationEventListener2.disable();
        }
        this.orientationEventListener = null;
        this.windowManager = null;
        this.callback = null;
    }
}
