package com.airbnb.lottie.parser;

import android.util.JsonReader;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.ShapePath;
import java.io.IOException;

class ShapePathParser {
    private ShapePathParser() {
    }

    static ShapePath parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        String str = null;
        AnimatableShapeValue animatableShapeValue = null;
        int i = 0;
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != 3324) {
                if (hashCode != 3432) {
                    if (hashCode != 3519) {
                        if (hashCode == 104415 && nextName.equals("ind")) {
                            c = 1;
                        }
                    } else if (nextName.equals("nm")) {
                        c = 0;
                    }
                } else if (nextName.equals("ks")) {
                    c = 2;
                }
            } else if (nextName.equals("hd")) {
                c = 3;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c == 1) {
                i = jsonReader.nextInt();
            } else if (c == 2) {
                animatableShapeValue = AnimatableValueParser.parseShapeData(jsonReader, lottieComposition);
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                z = jsonReader.nextBoolean();
            }
        }
        return new ShapePath(str, i, animatableShapeValue, z);
    }
}
