package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera.Parameters;

public interface CameraParametersCallback {
    Parameters changeCameraParameters(Parameters parameters);
}
