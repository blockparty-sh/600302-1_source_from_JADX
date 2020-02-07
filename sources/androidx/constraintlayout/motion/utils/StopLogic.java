package androidx.constraintlayout.motion.utils;

import android.util.Log;
import androidx.constraintlayout.motion.widget.MotionInterpolator;

public class StopLogic extends MotionInterpolator {
    private boolean mBackwards = false;
    private float mLastPosition;
    private int mNumberOfStages;
    private float mStage1Duration;
    private float mStage1EndPosition;
    private float mStage1Velocity;
    private float mStage2Duration;
    private float mStage2EndPosition;
    private float mStage2Velocity;
    private float mStage3Duration;
    private float mStage3EndPosition;
    private float mStage3Velocity;
    private float mStartPosition;
    private String mType;

    public void debug(String str, String str2, float f) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(" ===== ");
        sb.append(this.mType);
        Log.v(str, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(this.mBackwards ? "backwards" : "forward ");
        sb2.append(" time = ");
        sb2.append(f);
        sb2.append("  stages ");
        sb2.append(this.mNumberOfStages);
        Log.v(str, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str2);
        String str3 = " dur ";
        sb3.append(str3);
        sb3.append(this.mStage1Duration);
        String str4 = " vel ";
        sb3.append(str4);
        sb3.append(this.mStage1Velocity);
        String str5 = " pos ";
        sb3.append(str5);
        sb3.append(this.mStage1EndPosition);
        Log.v(str, sb3.toString());
        if (this.mNumberOfStages > 1) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            sb4.append(str3);
            sb4.append(this.mStage2Duration);
            sb4.append(str4);
            sb4.append(this.mStage2Velocity);
            sb4.append(str5);
            sb4.append(this.mStage2EndPosition);
            Log.v(str, sb4.toString());
        }
        if (this.mNumberOfStages > 2) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str2);
            sb5.append(str3);
            sb5.append(this.mStage3Duration);
            sb5.append(str4);
            sb5.append(this.mStage3Velocity);
            sb5.append(str5);
            sb5.append(this.mStage3EndPosition);
            Log.v(str, sb5.toString());
        }
        float f2 = this.mStage1Duration;
        if (f <= f2) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(str2);
            sb6.append("stage 0");
            Log.v(str, sb6.toString());
            return;
        }
        int i = this.mNumberOfStages;
        if (i == 1) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(str2);
            sb7.append("end stage 0");
            Log.v(str, sb7.toString());
            return;
        }
        float f3 = f - f2;
        float f4 = this.mStage2Duration;
        if (f3 < f4) {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(str2);
            sb8.append(" stage 1");
            Log.v(str, sb8.toString());
        } else if (i == 2) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append(str2);
            sb9.append("end stage 1");
            Log.v(str, sb9.toString());
        } else if (f3 - f4 < this.mStage3Duration) {
            StringBuilder sb10 = new StringBuilder();
            sb10.append(str2);
            sb10.append(" stage 2");
            Log.v(str, sb10.toString());
        } else {
            StringBuilder sb11 = new StringBuilder();
            sb11.append(str2);
            sb11.append(" end stage 2");
            Log.v(str, sb11.toString());
        }
    }

    public float getVelocity(float f) {
        float f2 = this.mStage1Duration;
        if (f <= f2) {
            float f3 = this.mStage1Velocity;
            return f3 + (((this.mStage2Velocity - f3) * f) / f2);
        }
        int i = this.mNumberOfStages;
        if (i == 1) {
            return 0.0f;
        }
        float f4 = f - f2;
        float f5 = this.mStage2Duration;
        if (f4 < f5) {
            float f6 = this.mStage2Velocity;
            return f6 + (((this.mStage3Velocity - f6) * f4) / f5);
        } else if (i == 2) {
            return this.mStage2EndPosition;
        } else {
            float f7 = f4 - f5;
            float f8 = this.mStage3Duration;
            if (f7 >= f8) {
                return this.mStage3EndPosition;
            }
            float f9 = this.mStage3Velocity;
            return f9 - ((f7 * f9) / f8);
        }
    }

    private float calcY(float f) {
        float f2 = this.mStage1Duration;
        if (f <= f2) {
            float f3 = this.mStage1Velocity;
            return (f3 * f) + ((((this.mStage2Velocity - f3) * f) * f) / (f2 * 2.0f));
        }
        int i = this.mNumberOfStages;
        if (i == 1) {
            return this.mStage1EndPosition;
        }
        float f4 = f - f2;
        float f5 = this.mStage2Duration;
        if (f4 < f5) {
            float f6 = this.mStage1EndPosition;
            float f7 = this.mStage2Velocity;
            return f6 + (f7 * f4) + ((((this.mStage3Velocity - f7) * f4) * f4) / (f5 * 2.0f));
        } else if (i == 2) {
            return this.mStage2EndPosition;
        } else {
            float f8 = f4 - f5;
            float f9 = this.mStage3Duration;
            if (f8 >= f9) {
                return this.mStage3EndPosition;
            }
            float f10 = this.mStage2EndPosition;
            float f11 = this.mStage3Velocity;
            return (f10 + (f11 * f8)) - (((f11 * f8) * f8) / (f9 * 2.0f));
        }
    }

    public void config(float f, float f2, float f3, float f4, float f5, float f6) {
        this.mStartPosition = f;
        this.mBackwards = f > f2;
        if (this.mBackwards) {
            setup(-f3, f - f2, f5, f6, f4);
        } else {
            setup(f3, f2 - f, f5, f6, f4);
        }
    }

    public float getInterpolation(float f) {
        float calcY = calcY(f);
        this.mLastPosition = f;
        return this.mBackwards ? this.mStartPosition - calcY : this.mStartPosition + calcY;
    }

    public float getVelocity() {
        return getVelocity(this.mLastPosition);
    }

    private void setup(float f, float f2, float f3, float f4, float f5) {
        if (f == 0.0f) {
            f = 1.0E-4f;
        }
        this.mStage1Velocity = f;
        float f6 = f / f3;
        float f7 = (f6 * f) / 2.0f;
        if (f < 0.0f) {
            float sqrt = (float) Math.sqrt((double) ((f2 - ((((-f) / f3) * f) / 2.0f)) * f3));
            if (sqrt < f4) {
                this.mType = "backward accelerate, decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f;
                this.mStage2Velocity = sqrt;
                this.mStage3Velocity = 0.0f;
                this.mStage1Duration = (sqrt - f) / f3;
                this.mStage2Duration = sqrt / f3;
                this.mStage1EndPosition = ((f + sqrt) * this.mStage1Duration) / 2.0f;
                this.mStage2EndPosition = f2;
                this.mStage3EndPosition = f2;
                return;
            }
            this.mType = "backward accelerate cruse decelerate";
            this.mNumberOfStages = 3;
            this.mStage1Velocity = f;
            this.mStage2Velocity = f4;
            this.mStage3Velocity = f4;
            this.mStage1Duration = (f4 - f) / f3;
            this.mStage3Duration = f4 / f3;
            float f8 = ((f + f4) * this.mStage1Duration) / 2.0f;
            float f9 = (this.mStage3Duration * f4) / 2.0f;
            this.mStage2Duration = ((f2 - f8) - f9) / f4;
            this.mStage1EndPosition = f8;
            this.mStage2EndPosition = f2 - f9;
            this.mStage3EndPosition = f2;
        } else if (f7 >= f2) {
            this.mType = "hard stop";
            float f10 = (2.0f * f2) / f;
            this.mNumberOfStages = 1;
            this.mStage1Velocity = f;
            this.mStage2Velocity = 0.0f;
            this.mStage1EndPosition = f2;
            this.mStage1Duration = f10;
        } else {
            float f11 = f2 - f7;
            float f12 = f11 / f;
            if (f12 + f6 < f5) {
                this.mType = "cruse decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f;
                this.mStage2Velocity = f;
                this.mStage3Velocity = 0.0f;
                this.mStage1EndPosition = f11;
                this.mStage2EndPosition = f2;
                this.mStage1Duration = f12;
                this.mStage2Duration = f6;
                return;
            }
            float sqrt2 = (float) Math.sqrt((double) ((f3 * f2) + ((f * f) / 2.0f)));
            float f13 = (sqrt2 - f) / f3;
            this.mStage1Duration = f13;
            float f14 = sqrt2 / f3;
            this.mStage2Duration = f14;
            if (sqrt2 < f4) {
                this.mType = "accelerate decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f;
                this.mStage2Velocity = sqrt2;
                this.mStage3Velocity = 0.0f;
                this.mStage1Duration = f13;
                this.mStage2Duration = f14;
                this.mStage1EndPosition = ((f + sqrt2) * this.mStage1Duration) / 2.0f;
                this.mStage2EndPosition = f2;
                return;
            }
            this.mType = "accelerate cruse decelerate";
            this.mNumberOfStages = 3;
            this.mStage1Velocity = f;
            this.mStage2Velocity = f4;
            this.mStage3Velocity = f4;
            this.mStage1Duration = (f4 - f) / f3;
            this.mStage3Duration = f4 / f3;
            float f15 = ((f + f4) * this.mStage1Duration) / 2.0f;
            float f16 = (this.mStage3Duration * f4) / 2.0f;
            this.mStage2Duration = ((f2 - f15) - f16) / f4;
            this.mStage1EndPosition = f15;
            this.mStage2EndPosition = f2 - f16;
            this.mStage3EndPosition = f2;
        }
    }
}
