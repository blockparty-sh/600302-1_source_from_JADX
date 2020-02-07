package com.airbnb.lottie.parser;

import android.util.JsonReader;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.RectangleShape;
import com.leanplum.core.BuildConfig;
import java.io.IOException;
import org.bitcoinj.uri.BitcoinURI;

class RectangleShapeParser {
    private RectangleShapeParser() {
    }

    static RectangleShape parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        String str = null;
        AnimatableValue animatableValue = null;
        AnimatablePointValue animatablePointValue = null;
        AnimatableFloatValue animatableFloatValue = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != 112) {
                if (hashCode != 3324) {
                    if (hashCode != 3519) {
                        if (hashCode != 114) {
                            if (hashCode == 115 && nextName.equals(BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER)) {
                                c = 2;
                            }
                        } else if (nextName.equals(BitcoinURI.FIELD_PAYMENT_REQUEST_URL)) {
                            c = 3;
                        }
                    } else if (nextName.equals("nm")) {
                        c = 0;
                    }
                } else if (nextName.equals("hd")) {
                    c = 4;
                }
            } else if (nextName.equals("p")) {
                c = 1;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c == 1) {
                animatableValue = AnimatablePathValueParser.parseSplitPath(jsonReader, lottieComposition);
            } else if (c == 2) {
                animatablePointValue = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
            } else if (c == 3) {
                animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
            } else if (c != 4) {
                jsonReader.skipValue();
            } else {
                z = jsonReader.nextBoolean();
            }
        }
        RectangleShape rectangleShape = new RectangleShape(str, animatableValue, animatablePointValue, animatableFloatValue, z);
        return rectangleShape;
    }
}
