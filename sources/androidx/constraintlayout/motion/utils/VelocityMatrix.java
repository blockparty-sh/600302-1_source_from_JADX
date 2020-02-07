package androidx.constraintlayout.motion.utils;

import androidx.constraintlayout.motion.widget.KeyCycleOscillator;
import androidx.constraintlayout.motion.widget.SplineSet;

public class VelocityMatrix {
    private static String TAG = "VelocityMatrix";
    float mDRotate;
    float mDScaleX;
    float mDScaleY;
    float mDTranslateX;
    float mDTranslateY;
    float mRotate;

    public void clear() {
        this.mDRotate = 0.0f;
        this.mDTranslateY = 0.0f;
        this.mDTranslateX = 0.0f;
        this.mDScaleY = 0.0f;
        this.mDScaleX = 0.0f;
    }

    public void setRotationVelocity(SplineSet splineSet, float f) {
        if (splineSet != null) {
            this.mDRotate = splineSet.getSlope(f);
            this.mRotate = splineSet.get(f);
        }
    }

    public void setTranslationVelocity(SplineSet splineSet, SplineSet splineSet2, float f) {
        if (splineSet != null) {
            this.mDTranslateX = splineSet.getSlope(f);
        }
        if (splineSet2 != null) {
            this.mDTranslateY = splineSet2.getSlope(f);
        }
    }

    public void setScaleVelocity(SplineSet splineSet, SplineSet splineSet2, float f) {
        if (splineSet != null) {
            this.mDScaleX = splineSet.getSlope(f);
        }
        if (splineSet2 != null) {
            this.mDScaleY = splineSet2.getSlope(f);
        }
    }

    public void setRotationVelocity(KeyCycleOscillator keyCycleOscillator, float f) {
        if (keyCycleOscillator != null) {
            this.mDRotate = keyCycleOscillator.getSlope(f);
        }
    }

    public void setTranslationVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f) {
        if (keyCycleOscillator != null) {
            this.mDTranslateX = keyCycleOscillator.getSlope(f);
        }
        if (keyCycleOscillator2 != null) {
            this.mDTranslateY = keyCycleOscillator2.getSlope(f);
        }
    }

    public void setScaleVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f) {
        if (keyCycleOscillator != null || keyCycleOscillator2 != null) {
            if (keyCycleOscillator == null) {
                this.mDScaleX = keyCycleOscillator.getSlope(f);
            }
            if (keyCycleOscillator2 == null) {
                this.mDScaleY = keyCycleOscillator2.getSlope(f);
            }
        }
    }

    public void applyTransform(float f, float f2, int i, int i2, float[] fArr) {
        int i3 = i;
        float f3 = fArr[0];
        float f4 = (f - 0.5f) * 2.0f;
        float f5 = (f2 - 0.5f) * 2.0f;
        float radians = (float) Math.toRadians((double) this.mDRotate);
        double radians2 = (double) ((float) Math.toRadians((double) this.mRotate));
        double d = (double) (((float) i2) * f5);
        float cos = fArr[1] + this.mDTranslateY + (this.mDScaleY * f5) + (radians * ((float) ((((double) (((float) i3) * f4)) * Math.cos(radians2)) - (d * Math.sin(radians2)))));
        fArr[0] = f3 + this.mDTranslateX + (this.mDScaleX * f4) + (((float) ((((double) (((float) (-i3)) * f4)) * Math.sin(radians2)) - (Math.cos(radians2) * d))) * radians);
        fArr[1] = cos;
    }
}
