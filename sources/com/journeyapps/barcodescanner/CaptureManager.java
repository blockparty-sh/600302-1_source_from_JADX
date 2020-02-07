package com.journeyapps.barcodescanner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.C2220R;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.Intents.Scan;
import com.journeyapps.barcodescanner.CameraPreview.StateListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CaptureManager {
    private static final String SAVED_ORIENTATION_LOCK = "SAVED_ORIENTATION_LOCK";
    /* access modifiers changed from: private */
    public static final String TAG = "CaptureManager";
    private static int cameraPermissionReqCode = 250;
    private Activity activity;
    private boolean askedPermission = false;
    /* access modifiers changed from: private */
    public DecoratedBarcodeView barcodeView;
    /* access modifiers changed from: private */
    public BeepManager beepManager;
    private BarcodeCallback callback = new BarcodeCallback() {
        public void possibleResultPoints(List<ResultPoint> list) {
        }

        public void barcodeResult(final BarcodeResult barcodeResult) {
            CaptureManager.this.barcodeView.pause();
            CaptureManager.this.beepManager.playBeepSoundAndVibrate();
            CaptureManager.this.handler.post(new Runnable() {
                public void run() {
                    CaptureManager.this.returnResult(barcodeResult);
                }
            });
        }
    };
    private boolean destroyed = false;
    /* access modifiers changed from: private */
    public boolean finishWhenClosed = false;
    /* access modifiers changed from: private */
    public Handler handler;
    private InactivityTimer inactivityTimer;
    private int orientationLock = -1;
    private boolean returnBarcodeImagePath = false;
    private final StateListener stateListener = new StateListener() {
        public void previewSized() {
        }

        public void previewStarted() {
        }

        public void previewStopped() {
        }

        public void cameraError(Exception exc) {
            CaptureManager.this.displayFrameworkBugMessageAndExit();
        }

        public void cameraClosed() {
            if (CaptureManager.this.finishWhenClosed) {
                Log.d(CaptureManager.TAG, "Camera closed; finishing activity");
                CaptureManager.this.finish();
            }
        }
    };

    public CaptureManager(Activity activity2, DecoratedBarcodeView decoratedBarcodeView) {
        this.activity = activity2;
        this.barcodeView = decoratedBarcodeView;
        decoratedBarcodeView.getBarcodeView().addStateListener(this.stateListener);
        this.handler = new Handler();
        this.inactivityTimer = new InactivityTimer(activity2, new Runnable() {
            public void run() {
                Log.d(CaptureManager.TAG, "Finishing due to inactivity");
                CaptureManager.this.finish();
            }
        });
        this.beepManager = new BeepManager(activity2);
    }

    public void initializeFromIntent(Intent intent, Bundle bundle) {
        this.activity.getWindow().addFlags(128);
        if (bundle != null) {
            this.orientationLock = bundle.getInt(SAVED_ORIENTATION_LOCK, -1);
        }
        if (intent != null) {
            if (intent.getBooleanExtra(Scan.ORIENTATION_LOCKED, true)) {
                lockOrientation();
            }
            if (Scan.ACTION.equals(intent.getAction())) {
                this.barcodeView.initializeFromIntent(intent);
            }
            if (!intent.getBooleanExtra(Scan.BEEP_ENABLED, true)) {
                this.beepManager.setBeepEnabled(false);
            }
            String str = Scan.TIMEOUT;
            if (intent.hasExtra(str)) {
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        CaptureManager.this.returnResultTimeout();
                    }
                }, intent.getLongExtra(str, 0));
            }
            if (intent.getBooleanExtra(Scan.BARCODE_IMAGE_ENABLED, false)) {
                this.returnBarcodeImagePath = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void lockOrientation() {
        if (this.orientationLock == -1) {
            int rotation = this.activity.getWindowManager().getDefaultDisplay().getRotation();
            int i = this.activity.getResources().getConfiguration().orientation;
            int i2 = 0;
            if (i == 2) {
                if (!(rotation == 0 || rotation == 1)) {
                    i2 = 8;
                }
            } else if (i == 1) {
                i2 = (rotation == 0 || rotation == 3) ? 1 : 9;
            }
            this.orientationLock = i2;
        }
        this.activity.setRequestedOrientation(this.orientationLock);
    }

    public void decode() {
        this.barcodeView.decodeSingle(this.callback);
    }

    public void onResume() {
        if (VERSION.SDK_INT >= 23) {
            openCameraWithPermission();
        } else {
            this.barcodeView.resume();
        }
        this.inactivityTimer.start();
    }

    @TargetApi(23)
    private void openCameraWithPermission() {
        String str = "android.permission.CAMERA";
        if (ContextCompat.checkSelfPermission(this.activity, str) == 0) {
            this.barcodeView.resume();
        } else if (!this.askedPermission) {
            ActivityCompat.requestPermissions(this.activity, new String[]{str}, cameraPermissionReqCode);
            this.askedPermission = true;
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != cameraPermissionReqCode) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            displayFrameworkBugMessageAndExit();
        } else {
            this.barcodeView.resume();
        }
    }

    public void onPause() {
        this.inactivityTimer.cancel();
        this.barcodeView.pauseAndWait();
    }

    public void onDestroy() {
        this.destroyed = true;
        this.inactivityTimer.cancel();
        this.handler.removeCallbacksAndMessages(null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(SAVED_ORIENTATION_LOCK, this.orientationLock);
    }

    public static Intent resultIntent(BarcodeResult barcodeResult, String str) {
        Intent intent = new Intent(Scan.ACTION);
        intent.addFlags(524288);
        intent.putExtra(Scan.RESULT, barcodeResult.toString());
        intent.putExtra(Scan.RESULT_FORMAT, barcodeResult.getBarcodeFormat().toString());
        byte[] rawBytes = barcodeResult.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent.putExtra(Scan.RESULT_BYTES, rawBytes);
        }
        Map resultMetadata = barcodeResult.getResultMetadata();
        if (resultMetadata != null) {
            if (resultMetadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra(Scan.RESULT_UPC_EAN_EXTENSION, resultMetadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Number number = (Number) resultMetadata.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent.putExtra(Scan.RESULT_ORIENTATION, number.intValue());
            }
            String str2 = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str2 != null) {
                intent.putExtra(Scan.RESULT_ERROR_CORRECTION_LEVEL, str2);
            }
            Iterable<byte[]> iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                int i = 0;
                for (byte[] bArr : iterable) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Scan.RESULT_BYTE_SEGMENTS_PREFIX);
                    sb.append(i);
                    intent.putExtra(sb.toString(), bArr);
                    i++;
                }
            }
        }
        if (str != null) {
            intent.putExtra(Scan.RESULT_BARCODE_IMAGE_PATH, str);
        }
        return intent;
    }

    private String getBarcodeImagePath(BarcodeResult barcodeResult) {
        if (this.returnBarcodeImagePath) {
            Bitmap bitmap = barcodeResult.getBitmap();
            try {
                File createTempFile = File.createTempFile("barcodeimage", ".jpg", this.activity.getCacheDir());
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                return createTempFile.getAbsolutePath();
            } catch (IOException e) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to create temporary file and store bitmap! ");
                sb.append(e);
                Log.w(str, sb.toString());
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void finish() {
        this.activity.finish();
    }

    /* access modifiers changed from: protected */
    public void closeAndFinish() {
        if (this.barcodeView.getBarcodeView().isCameraClosed()) {
            finish();
        } else {
            this.finishWhenClosed = true;
        }
        this.barcodeView.pause();
        this.inactivityTimer.cancel();
    }

    /* access modifiers changed from: protected */
    public void returnResultTimeout() {
        Intent intent = new Intent(Scan.ACTION);
        intent.putExtra(Scan.TIMEOUT, true);
        this.activity.setResult(0, intent);
        closeAndFinish();
    }

    /* access modifiers changed from: protected */
    public void returnResult(BarcodeResult barcodeResult) {
        this.activity.setResult(-1, resultIntent(barcodeResult, getBarcodeImagePath(barcodeResult)));
        closeAndFinish();
    }

    /* access modifiers changed from: protected */
    public void displayFrameworkBugMessageAndExit() {
        if (!this.activity.isFinishing() && !this.destroyed && !this.finishWhenClosed) {
            Builder builder = new Builder(this.activity);
            builder.setTitle(this.activity.getString(C2220R.string.zxing_app_name));
            builder.setMessage(this.activity.getString(C2220R.string.zxing_msg_camera_framework_bug));
            builder.setPositiveButton(C2220R.string.zxing_button_ok, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CaptureManager.this.finish();
                }
            });
            builder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    CaptureManager.this.finish();
                }
            });
            builder.show();
        }
    }

    public static int getCameraPermissionReqCode() {
        return cameraPermissionReqCode;
    }

    public static void setCameraPermissionReqCode(int i) {
        cameraPermissionReqCode = i;
    }
}
