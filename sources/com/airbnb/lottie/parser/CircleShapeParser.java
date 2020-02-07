package com.airbnb.lottie.parser;

import android.util.JsonReader;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.CircleShape;
import com.leanplum.core.BuildConfig;
import java.io.IOException;

class CircleShapeParser {
    private CircleShapeParser() {
    }

    static CircleShape parse(JsonReader jsonReader, LottieComposition lottieComposition, int i) throws IOException {
        boolean z = i == 3;
        String str = null;
        AnimatableValue animatableValue = null;
        AnimatablePointValue animatablePointValue = null;
        boolean z2 = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != 100) {
                if (hashCode != 112) {
                    if (hashCode != 115) {
                        if (hashCode != 3324) {
                            if (hashCode == 3519 && nextName.equals("nm")) {
                                c = 0;
                            }
                        } else if (nextName.equals("hd")) {
                            c = 3;
                        }
                    } else if (nextName.equals(BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER)) {
                        c = 2;
                    }
                } else if (nextName.equals("p")) {
                    c = 1;
                }
            } else if (nextName.equals("d")) {
                c = 4;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c == 1) {
                animatableValue = AnimatablePathValueParser.parseSplitPath(jsonReader, lottieComposition);
            } else if (c == 2) {
                animatablePointValue = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
            } else if (c == 3) {
                z2 = jsonReader.nextBoolean();
            } else if (c != 4) {
                jsonReader.skipValue();
            } else {
                z = jsonReader.nextInt() == 3;
            }
        }
        CircleShape circleShape = new CircleShape(str, animatableValue, animatablePointValue, z, z2);
        return circleShape;
    }
}
