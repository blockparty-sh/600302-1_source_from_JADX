package com.airbnb.lottie.value;

import androidx.annotation.NonNull;
import com.airbnb.lottie.utils.MiscUtils;

public class LottieRelativeFloatValueCallback extends LottieValueCallback<Float> {
    public LottieRelativeFloatValueCallback() {
    }

    public LottieRelativeFloatValueCallback(@NonNull Float f) {
        super(f);
    }

    public Float getValue(LottieFrameInfo<Float> lottieFrameInfo) {
        return Float.valueOf(MiscUtils.lerp(((Float) lottieFrameInfo.getStartValue()).floatValue(), ((Float) lottieFrameInfo.getEndValue()).floatValue(), lottieFrameInfo.getInterpolatedKeyframeProgress()) + getOffset(lottieFrameInfo).floatValue());
    }

    public Float getOffset(LottieFrameInfo<Float> lottieFrameInfo) {
        if (this.value != null) {
            return (Float) this.value;
        }
        throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
    }
}
