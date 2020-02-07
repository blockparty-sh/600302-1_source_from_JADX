package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.Size;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LegacyPreviewScalingStrategy extends PreviewScalingStrategy {
    private static final String TAG = "LegacyPreviewScalingStrategy";

    public Size getBestPreviewSize(List<Size> list, final Size size) {
        if (size == null) {
            return (Size) list.get(0);
        }
        Collections.sort(list, new Comparator<Size>() {
            public int compare(Size size, Size size2) {
                int i = LegacyPreviewScalingStrategy.scale(size, size).width - size.width;
                int i2 = LegacyPreviewScalingStrategy.scale(size2, size).width - size2.width;
                if (i == 0 && i2 == 0) {
                    return size.compareTo(size2);
                }
                if (i == 0) {
                    return -1;
                }
                if (i2 == 0) {
                    return 1;
                }
                if (i < 0 && i2 < 0) {
                    return size.compareTo(size2);
                }
                if (i <= 0 || i2 <= 0) {
                    return i < 0 ? -1 : 1;
                }
                return -size.compareTo(size2);
            }
        });
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Viewfinder size: ");
        sb.append(size);
        Log.i(str, sb.toString());
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Preview in order of preference: ");
        sb2.append(list);
        Log.i(str2, sb2.toString());
        return (Size) list.get(0);
    }

    public static Size scale(Size size, Size size2) {
        Size scale;
        if (!size2.fitsIn(size)) {
            do {
                Size scale2 = size.scale(3, 2);
                size = size.scale(2, 1);
                if (size2.fitsIn(scale2)) {
                    return scale2;
                }
            } while (!size2.fitsIn(size));
            return size;
        }
        while (true) {
            scale = size.scale(2, 3);
            Size scale3 = size.scale(1, 2);
            if (!size2.fitsIn(scale3)) {
                break;
            }
            size = scale3;
        }
        return size2.fitsIn(scale) ? scale : size;
    }

    public Rect scalePreview(Size size, Size size2) {
        Size scale = scale(size, size2);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Preview: ");
        sb.append(size);
        sb.append("; Scaled: ");
        sb.append(scale);
        sb.append("; Want: ");
        sb.append(size2);
        Log.i(str, sb.toString());
        int i = (scale.width - size2.width) / 2;
        int i2 = (scale.height - size2.height) / 2;
        return new Rect(-i, -i2, scale.width - i, scale.height - i2);
    }
}
