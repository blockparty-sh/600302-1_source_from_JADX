package com.airbnb.lottie.parser;

import android.graphics.Path.FillType;
import android.util.JsonReader;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeFill;
import java.io.IOException;
import org.bitcoinj.uri.BitcoinURI;

class ShapeFillParser {
    private ShapeFillParser() {
    }

    static ShapeFill parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        String str = null;
        AnimatableColorValue animatableColorValue = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        int i = 1;
        boolean z = false;
        boolean z2 = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != -396065730) {
                if (hashCode != 99) {
                    if (hashCode != 111) {
                        if (hashCode != 114) {
                            if (hashCode != 3324) {
                                if (hashCode == 3519 && nextName.equals("nm")) {
                                    c = 0;
                                }
                            } else if (nextName.equals("hd")) {
                                c = 5;
                            }
                        } else if (nextName.equals(BitcoinURI.FIELD_PAYMENT_REQUEST_URL)) {
                            c = 4;
                        }
                    } else if (nextName.equals("o")) {
                        c = 2;
                    }
                } else if (nextName.equals("c")) {
                    c = 1;
                }
            } else if (nextName.equals("fillEnabled")) {
                c = 3;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c == 1) {
                animatableColorValue = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
            } else if (c == 2) {
                animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else if (c == 3) {
                z = jsonReader.nextBoolean();
            } else if (c == 4) {
                i = jsonReader.nextInt();
            } else if (c != 5) {
                jsonReader.skipValue();
            } else {
                z2 = jsonReader.nextBoolean();
            }
        }
        ShapeFill shapeFill = new ShapeFill(str, z, i == 1 ? FillType.WINDING : FillType.EVEN_ODD, animatableColorValue, animatableIntegerValue, z2);
        return shapeFill;
    }
}
