package com.airbnb.lottie.parser;

import android.util.JsonReader;
import com.airbnb.lottie.model.content.MergePaths;
import com.airbnb.lottie.model.content.MergePaths.MergePathsMode;
import java.io.IOException;

class MergePathsParser {
    private MergePathsParser() {
    }

    static MergePaths parse(JsonReader jsonReader) throws IOException {
        String str = null;
        MergePathsMode mergePathsMode = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != 3324) {
                if (hashCode != 3488) {
                    if (hashCode == 3519 && nextName.equals("nm")) {
                        c = 0;
                    }
                } else if (nextName.equals("mm")) {
                    c = 1;
                }
            } else if (nextName.equals("hd")) {
                c = 2;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c == 1) {
                mergePathsMode = MergePathsMode.forId(jsonReader.nextInt());
            } else if (c != 2) {
                jsonReader.skipValue();
            } else {
                z = jsonReader.nextBoolean();
            }
        }
        return new MergePaths(str, mergePathsMode, z);
    }
}
